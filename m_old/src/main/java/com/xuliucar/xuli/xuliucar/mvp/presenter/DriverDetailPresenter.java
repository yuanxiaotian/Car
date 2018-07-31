package com.xuliucar.xuli.xuliucar.mvp.presenter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;

import com.xuliucar.xuli.xuliucar.bean.DriverDetailBean;
import com.xuliucar.xuli.xuliucar.mvp.ModelInterface.CallBackListener;
import com.xuliucar.xuli.xuliucar.mvp.model.DriverDetailModel;
import com.xuliucar.xuli.xuliucar.mvp.view.DriverDetailView;
import com.xuliucar.xuli.xuliucar.ui.Login;
import com.xuliucar.xuli.xuliucar.utils.PreferencesUtils;

/**
 * Created by skyward on 2016/11/24.
 * email：
 */

public class DriverDetailPresenter extends BasePresenterImpl implements CallBackListener<DriverDetailBean> {
    private DriverDetailModel mModel;
    private DriverDetailView mView;
    public DriverDetailPresenter(DriverDetailView view){
        this.mView = view;
        mModel = new DriverDetailModel(this);
    }
    public void getData(String loginid,String did,int compid){
        mModel.getData(loginid,did,compid);
    }

    @Override
    public void OnSuccess(DriverDetailBean detailBean) {
        if(detailBean.isSuccess()){
            mView.getData(detailBean);
        }else {
            mView.toastMsg(detailBean.getMessage());
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
        ((Activity)context).finish();
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
                ((Activity)context).finish();
            }
        }, 1000);
    }
}
