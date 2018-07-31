package com.xuliucar.xuli.xuliucar.mvp.presenter;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;

import com.xuliucar.xuli.xuliucar.base.App;
import com.xuliucar.xuli.xuliucar.bean.ContractMDetailBean;
import com.xuliucar.xuli.xuliucar.mvp.ModelInterface.CallBackListener;
import com.xuliucar.xuli.xuliucar.mvp.model.ContractMDetailModel;
import com.xuliucar.xuli.xuliucar.mvp.view.ContractMDetailView;
import com.xuliucar.xuli.xuliucar.ui.Login;
import com.xuliucar.xuli.xuliucar.utils.NetWorkUtil;
import com.xuliucar.xuli.xuliucar.utils.PreferencesUtils;

/**
 * Created by skyward on 2016/11/30.
 * email：
 */

public class ContractMDetailPresenter extends BasePresenterImpl implements CallBackListener<ContractMDetailBean> {
    private ContractMDetailModel mModel;
    private ContractMDetailView mView;
    public ContractMDetailPresenter(ContractMDetailView view){
        mView = view;
        mModel = new ContractMDetailModel(this);
    }

    public void getData(String coid){
        if(!NetWorkUtil.isNetWorkAvailable(App.getContext())){
            mView.showError();
        }else {
            mModel.getContractMDetail(coid);
        }
    }

    @Override
    public void OnSuccess(ContractMDetailBean bean) {
        if(bean.isSuccess()){
            ContractMDetailBean.DataBean dataBean = bean.getData();
            Double IRetainage = Double.parseDouble(dataBean.getDealprice()) - Double.parseDouble(dataBean.getDownpay());
            mView.retainage(String.valueOf(IRetainage));
            mView.loadData(dataBean);
        }else {
            mView.toastMsg(bean.getMessage());
        }
    }

    @Override
    public void OnFailure(Throwable e) {

    }
    @Override
    public void toLogin(Context context) {
        super.toLogin(context);
        Intent intent = new Intent(context, Login.class);
        context.startActivity(intent);
    }

    @Override
    public void alreadyLogin(final Context context) {
        super.alreadyLogin(context);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(context, Login.class);
                PreferencesUtils.clearSharePre(context, "userInfo", "password");
                context.startActivity(i);

            }
        }, 1000);
    }
}
