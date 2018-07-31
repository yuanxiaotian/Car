package com.xuliucar.xuli.xuliucar.mvp.presenter;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;

import com.xuliucar.xuli.xuliucar.base.App;
import com.xuliucar.xuli.xuliucar.bean.MainDirverBean;
import com.xuliucar.xuli.xuliucar.mvp.ModelInterface.CallBackListener;
import com.xuliucar.xuli.xuliucar.mvp.model.MainDriverModel;
import com.xuliucar.xuli.xuliucar.mvp.view.CarDetailView;
import com.xuliucar.xuli.xuliucar.ui.Login;
import com.xuliucar.xuli.xuliucar.utils.NetWorkUtil;
import com.xuliucar.xuli.xuliucar.utils.PreferencesUtils;

/**
 * Created by skyward on 2016/12/6.
 * email：
 */

public class MainDriverPresenter extends BasePresenterImpl implements CallBackListener<MainDirverBean> {
    private MainDriverModel mModel;
    private CarDetailView<MainDirverBean.DataBean> mView;

    public MainDriverPresenter(CarDetailView<MainDirverBean.DataBean> view) {
        mView = view;
        mModel = new MainDriverModel(this);
    }

    public void getData(String cid) {
        mModel.getMainDriver(cid, mView.loginId(), mView.companyId());
    }

    @Override
    public void OnSuccess(MainDirverBean bean) {
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
