package com.xuliucar.xuli.xuliucar.mvp.model;

import android.util.Log;

import com.cangmaomao.network.request.HttpManage;
import com.cangmaomao.network.request.base.BaseObserver;
import com.cangmaomao.network.request.utils.RxSchedulers;
import com.xuliucar.xuli.xuliucar.bean.LoginInfo;
import com.xuliucar.xuli.xuliucar.config.ApiConfigTest;
import com.xuliucar.xuli.xuliucar.mvp.ModelInterface.CallBackListener;
import com.xuliucar.xuli.xuliucar.mvp.ModelInterface.ILogin;
import com.xuliucar.xuli.xuliucar.mvp.presenter.BasePresenterImpl;
import com.xuliucar.xuli.xuliucar.utils.LogUtil;


import io.reactivex.disposables.Disposable;

/**
 * Created by skyward on 2016/11/22.
 */

public class LoginModel extends BasePresenterImpl implements ILogin {
    private CallBackListener<LoginInfo> mLoginListener;

    public LoginModel(CallBackListener<LoginInfo> onLoginListener) {
        this.mLoginListener = onLoginListener;
    }

    @Override
    public void postLogin(String company, String usercount, String password) {
        HttpManage.getInstance()
                .create(ApiConfigTest.class)
                .getLogin(company,usercount,password)
                .compose(RxSchedulers.<LoginInfo>io_main())
                .subscribe(new BaseObserver<LoginInfo>("") {
                    @Override
                    public void success(LoginInfo loginInfo) {
                        mLoginListener.OnSuccess(loginInfo);
                        LogUtil.LogPrint("myLog: " + loginInfo.getData().getApppower());
                    }

                    @Override
                    public void fail(String s) {
//                        mLoginListener.OnFailure(s);
                    }
                });

    }

}
