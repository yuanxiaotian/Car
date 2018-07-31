package com.xuliucar.xuli.xuliucar.mvp.presenter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;

import com.xuliucar.xuli.xuliucar.base.App;
import com.xuliucar.xuli.xuliucar.bean.MessageBean;
import com.xuliucar.xuli.xuliucar.mvp.ModelInterface.CallBackListener;
import com.xuliucar.xuli.xuliucar.mvp.model.ChangePwdModel;
import com.xuliucar.xuli.xuliucar.mvp.view.ChangePwdView;
import com.xuliucar.xuli.xuliucar.ui.Login;
import com.xuliucar.xuli.xuliucar.utils.NetWorkUtil;
import com.xuliucar.xuli.xuliucar.utils.PreferencesUtils;

/**
 * Created by skyward on 2016/11/24.
 * email：
 */

public class ChangeLPwdPresenter extends BasePresenterImpl implements CallBackListener<MessageBean> {
    private ChangePwdModel mModel;
    private ChangePwdView mView;
    public ChangeLPwdPresenter(ChangePwdView view){
        this.mView = view;
        mModel = new ChangePwdModel(this);
    }

    public void postChange(String loginid,int compid,String opassword,String npassword){
        if(!NetWorkUtil.isNetWorkAvailable(App.getContext())){
            mView.showError();
        }else {
            mModel.getData(loginid,compid,opassword,npassword);
        }
    }

    @Override
    public void OnSuccess(MessageBean b) {
        if(b.isSuccess()){
            mView.toastMsg(b.getMessage());
        }else {
            mView.changeError(b.getMessage());
        }
    }

    @Override
    public void OnFailure(Throwable e) {

    }

    @Override
    public void toLogin(final Context context) {
        super.toLogin(context);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(context, Login.class);
                PreferencesUtils.clearSharePre(context,"userInfo","password");
                context.startActivity(intent);
                ((Activity)context).finish();
            }
        },1500);
    }
}
