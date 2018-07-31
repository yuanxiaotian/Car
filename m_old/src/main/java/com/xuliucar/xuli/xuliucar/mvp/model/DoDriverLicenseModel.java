package com.xuliucar.xuli.xuliucar.mvp.model;

import com.xuliucar.xuli.xuliucar.base.App;
import com.xuliucar.xuli.xuliucar.bean.DriverLicenseBean;
import com.xuliucar.xuli.xuliucar.config.ApiManager;
import com.xuliucar.xuli.xuliucar.mvp.ModelInterface.CallBackListener;
import com.xuliucar.xuli.xuliucar.mvp.ModelInterface.IDoDriverLicense;
import com.xuliucar.xuli.xuliucar.mvp.presenter.BasePresenterImpl;

import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by skyward on 2016/12/2.
 * email：
 */

public class DoDriverLicenseModel extends BasePresenterImpl implements IDoDriverLicense {
    private CallBackListener<DriverLicenseBean> mListener;

    public DoDriverLicenseModel(CallBackListener<DriverLicenseBean> listener) {
        mListener = listener;
    }

    @Override
    public void getDoDriverLicense() {
        Subscription subscription = ApiManager.getInstance().getCommonApi().getDoDriverLicense(App.loginid,App.compid)
                .map(new Func1<DriverLicenseBean, DriverLicenseBean>() {
                    @Override
                    public DriverLicenseBean call(DriverLicenseBean bean) {
                        return bean;
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<DriverLicenseBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        mListener.OnFailure(e);
                    }

                    @Override
                    public void onNext(DriverLicenseBean bean) {
                        mListener.OnSuccess(bean);
                    }
                });
        addSubscription(subscription);
    }
}
