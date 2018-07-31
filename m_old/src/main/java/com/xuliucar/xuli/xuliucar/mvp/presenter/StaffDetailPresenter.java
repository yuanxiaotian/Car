package com.xuliucar.xuli.xuliucar.mvp.presenter;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;

import com.xuliucar.xuli.xuliucar.bean.StaffDetailBean;
import com.xuliucar.xuli.xuliucar.mvp.ModelInterface.CallBackListener;
import com.xuliucar.xuli.xuliucar.mvp.model.StaffDetailModel;
import com.xuliucar.xuli.xuliucar.mvp.view.StaffDetailView;
import com.xuliucar.xuli.xuliucar.ui.Login;
import com.xuliucar.xuli.xuliucar.utils.PreferencesUtils;

/**
 * Created by skyward on 2016/11/24.
 * email：
 */

public class StaffDetailPresenter extends BasePresenterImpl implements CallBackListener<StaffDetailBean> {
    private StaffDetailModel mModel;
    private StaffDetailView mView;
    public StaffDetailPresenter(StaffDetailView view){
        this.mView = view;
        mModel = new StaffDetailModel(this);
    }
    public void getData(String loginid,String uid,int compid){
        mModel.getData(loginid,uid,compid);
    }
    @Override
    public void OnSuccess(StaffDetailBean bean) {
        if(bean.isSuccess()){
            StaffDetailBean.DataBean dataBean = bean.getData();
            mView.getData(dataBean);
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
                PreferencesUtils.clearSharePre(context,"userInfo","password");
                context.startActivity(i);
            }
        }, 1000);
    }
}
