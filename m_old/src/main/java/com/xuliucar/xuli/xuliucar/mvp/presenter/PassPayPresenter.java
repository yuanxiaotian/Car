package com.xuliucar.xuli.xuliucar.mvp.presenter;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;

import com.xuliucar.xuli.xuliucar.bean.PassPayBean;
import com.xuliucar.xuli.xuliucar.mvp.ModelInterface.CallBackListener;
import com.xuliucar.xuli.xuliucar.mvp.model.PassPayModel;
import com.xuliucar.xuli.xuliucar.mvp.view.PassPayView;
import com.xuliucar.xuli.xuliucar.ui.Login;
import com.xuliucar.xuli.xuliucar.utils.PreferencesUtils;

/**
 * Created by skyward on 2016/11/29.
 * email：
 */

public class PassPayPresenter extends BasePresenterImpl implements CallBackListener<PassPayBean> {
    private PassPayModel mModel;
    private PassPayView mView;

    public PassPayPresenter(PassPayView passPayView) {
        this.mView = passPayView;
        mModel = new PassPayModel(this,mView);
    }

    public void getData() {
        mModel.getPassPay();
    }

    @Override
    public void OnSuccess(PassPayBean bean) {
        if (bean.isSuccess()) {
            mView.OnSuccess(bean.getData());
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
