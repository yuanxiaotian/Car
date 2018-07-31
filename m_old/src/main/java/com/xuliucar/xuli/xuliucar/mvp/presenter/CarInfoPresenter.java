package com.xuliucar.xuli.xuliucar.mvp.presenter;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;

import com.xuliucar.xuli.xuliucar.bean.CarInfoBean;
import com.xuliucar.xuli.xuliucar.mvp.ModelInterface.CallBackListener;
import com.xuliucar.xuli.xuliucar.mvp.model.CarInfoModel;
import com.xuliucar.xuli.xuliucar.mvp.view.CarDetailView;
import com.xuliucar.xuli.xuliucar.ui.Login;
import com.xuliucar.xuli.xuliucar.utils.NetWorkUtil;
import com.xuliucar.xuli.xuliucar.utils.PreferencesUtils;

/**
 * Created by skyward on 2016/12/6.
 * email：
 */

public class CarInfoPresenter extends BasePresenterImpl implements CallBackListener<CarInfoBean> {
    private CarInfoModel mModel;
    private CarDetailView<CarInfoBean.DataBean> mView;

    public CarInfoPresenter(CarDetailView<CarInfoBean.DataBean> view) {
        mView = view;
        mModel = new CarInfoModel(this,mView);
    }

    public void getData(String cid) {
        mModel.getCarInfo(cid);
    }

    @Override
    public void OnSuccess(CarInfoBean bean) {
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
//        XGPushManager.registerPush(context, "*");
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
