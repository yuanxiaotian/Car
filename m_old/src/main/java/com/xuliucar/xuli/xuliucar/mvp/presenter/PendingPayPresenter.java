package com.xuliucar.xuli.xuliucar.mvp.presenter;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;

import com.xuliucar.xuli.xuliucar.bean.PendingPayBean;
import com.xuliucar.xuli.xuliucar.mvp.ModelInterface.CallBackListener;
import com.xuliucar.xuli.xuliucar.mvp.model.PendingPayModel;
import com.xuliucar.xuli.xuliucar.mvp.view.PendingPayView;
import com.xuliucar.xuli.xuliucar.ui.Login;
import com.xuliucar.xuli.xuliucar.utils.PreferencesUtils;

/**
 * Created by skyward on 2016/11/29.
 * email：
 */

public class PendingPayPresenter extends BasePresenterImpl implements CallBackListener<PendingPayBean> {
    private PendingPayModel mModel;
    private PendingPayView mView;

    public PendingPayPresenter(PendingPayView pendingPayView) {
        this.mView = pendingPayView;
        mModel = new PendingPayModel(this, mView);
    }

    public void getData() {
        mModel.getPendingPay();
    }

    @Override
    public void OnSuccess(PendingPayBean bean) {
        if (bean.isSuccess()) {
            mView.OnSuccess(bean.getData().getInfo());
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
