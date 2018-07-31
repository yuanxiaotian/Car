package com.xuliucar.xuli.xuliucar.mvp.presenter;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;

import com.google.gson.Gson;
import com.xuliucar.xuli.xuliucar.base.App;
import com.xuliucar.xuli.xuliucar.bean.DoPerpetuaeBean;
import com.xuliucar.xuli.xuliucar.config.CacheName;
import com.xuliucar.xuli.xuliucar.mvp.ModelInterface.CallBackListener;
import com.xuliucar.xuli.xuliucar.mvp.model.DoPerpetuaeModel;
import com.xuliucar.xuli.xuliucar.mvp.view.DoPerpetuaeView;
import com.xuliucar.xuli.xuliucar.ui.Login;
import com.xuliucar.xuli.xuliucar.utils.NetWorkUtil;
import com.xuliucar.xuli.xuliucar.utils.PreferencesUtils;

/**
 * Created by skyward on 2016/12/1.
 * email：
 */

public class DoPerpetuaePersenter extends BasePresenterImpl implements CallBackListener<DoPerpetuaeBean> {
    private DoPerpetuaeModel mModel;
    private DoPerpetuaeView mView;
    private Gson mGson = new Gson();
    private Handler mHandler;

    public DoPerpetuaePersenter(DoPerpetuaeView view) {
        this.mView = view;
        mModel = new DoPerpetuaeModel(this);
    }

    public void getData() {
        mModel.getDoperpetuae();
    }

    @Override
    public void OnSuccess(DoPerpetuaeBean bean) {
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
