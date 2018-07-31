package com.xuliucar.xuli.xuliucar.mvp.presenter;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;

import com.xuliucar.xuli.xuliucar.bean.Counts;
import com.xuliucar.xuli.xuliucar.mvp.ModelInterface.CallBackListener;
import com.xuliucar.xuli.xuliucar.mvp.model.PassItemIndexModel;
import com.xuliucar.xuli.xuliucar.mvp.view.PassItemindexView;
import com.xuliucar.xuli.xuliucar.ui.Login;
import com.xuliucar.xuli.xuliucar.utils.PreferencesUtils;

/**
 * Created by skyward on 2016/12/5.
 * email：
 */

public class PassItemIndexPresenter extends BasePresenterImpl implements CallBackListener<Counts> {
    private PassItemIndexModel mModel;
    private PassItemindexView mView;

    public PassItemIndexPresenter(PassItemindexView view) {
        mView = view;
        mModel = new PassItemIndexModel(this);
    }

    public void getCounts() {
        mModel.getPassItemIndex(mView.loginId(), mView.companyId());
    }

    @Override
    public void OnSuccess(Counts bean) {
        if (bean.isSuccess()) {
            mView.getCounts(bean.getData().getInfo());
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
