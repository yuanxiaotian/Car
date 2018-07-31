package com.xuliucar.xuli.xuliucar.mvp.presenter;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;

import com.xuliucar.xuli.xuliucar.base.App;
import com.xuliucar.xuli.xuliucar.bean.BusinessInfoBean;
import com.xuliucar.xuli.xuliucar.mvp.ModelInterface.CallBackListener;
import com.xuliucar.xuli.xuliucar.mvp.model.BussinessInfoModel;
import com.xuliucar.xuli.xuliucar.mvp.view.CarDetailView;
import com.xuliucar.xuli.xuliucar.ui.Login;
import com.xuliucar.xuli.xuliucar.utils.NetWorkUtil;
import com.xuliucar.xuli.xuliucar.utils.PreferencesUtils;

/**
 * Created by skyward on 2016/12/6.
 * email：
 */

public class BusinessInfoPresenter extends BasePresenterImpl implements CallBackListener<BusinessInfoBean> {
    private BussinessInfoModel mModel;
    private CarDetailView<BusinessInfoBean.DataBean> mView;

    public BusinessInfoPresenter(CarDetailView<BusinessInfoBean.DataBean> view) {
        mView = view;
        mModel = new BussinessInfoModel(this);
    }

    public void getData(String cid) {
        mModel.getBusinessInfo(cid, mView.loginId(), mView.companyId());
    }

    @Override
    public void OnSuccess(BusinessInfoBean bean) {
        if (bean.isSuccess()) {
            mView.getData(bean.getData());
        } else {
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
