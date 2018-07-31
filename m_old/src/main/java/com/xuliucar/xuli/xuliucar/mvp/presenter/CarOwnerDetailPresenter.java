package com.xuliucar.xuli.xuliucar.mvp.presenter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;

import com.xuliucar.xuli.xuliucar.bean.CarOwnerDetailBean;
import com.xuliucar.xuli.xuliucar.mvp.ModelInterface.CallBackListener;
import com.xuliucar.xuli.xuliucar.mvp.model.CarOwnerDetailModel;
import com.xuliucar.xuli.xuliucar.mvp.view.CarOwnerDetailView;
import com.xuliucar.xuli.xuliucar.ui.Login;
import com.xuliucar.xuli.xuliucar.utils.PreferencesUtils;

/**
 * Created by skyward on 2016/11/24.
 *
 */

public class CarOwnerDetailPresenter extends BasePresenterImpl implements CallBackListener<CarOwnerDetailBean>{
    private CarOwnerDetailModel mModel;
    private CarOwnerDetailView mDetailView;

    public CarOwnerDetailPresenter(CarOwnerDetailView carOwnerDetailView){
        this.mDetailView = carOwnerDetailView;
        mModel = new CarOwnerDetailModel(this);
    }

    public void getData(String loginid,String oid,int compid){
        mModel.getData(loginid,oid,compid);
    }

    @Override
    public void OnSuccess(CarOwnerDetailBean bean) {
        if(bean.isSuccess()){
            mDetailView.getCarOwnerDetailData(bean);
        }else {
            mDetailView.toastMsg(bean.getMessage());
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
//        XGPushManager.registerPush(context, "*");
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
