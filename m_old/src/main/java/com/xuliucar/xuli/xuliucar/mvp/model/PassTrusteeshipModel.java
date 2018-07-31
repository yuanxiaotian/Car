package com.xuliucar.xuli.xuliucar.mvp.model;

import com.xuliucar.xuli.xuliucar.base.App;
import com.xuliucar.xuli.xuliucar.bean.TrusteeshipBean;
import com.xuliucar.xuli.xuliucar.config.ApiManager;
import com.xuliucar.xuli.xuliucar.mvp.ModelInterface.CallBackListener;
import com.xuliucar.xuli.xuliucar.mvp.ModelInterface.IPassTrusteeshipModel;
import com.xuliucar.xuli.xuliucar.mvp.presenter.BasePresenterImpl;

import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by skyward on 2016/11/30.
 * email：
 */

public class PassTrusteeshipModel extends BasePresenterImpl implements IPassTrusteeshipModel {
    private CallBackListener<TrusteeshipBean> mListener;
    public PassTrusteeshipModel(CallBackListener<TrusteeshipBean> listener){
        this.mListener = listener;
    }
    @Override
    public void getPassTrusteeship() {
        Subscription subscription = ApiManager.getInstance().getCommonApi().getPassTrusteeship(App.loginid,App.compid)
                .map(new Func1<TrusteeshipBean, TrusteeshipBean>() {
                    @Override
                    public TrusteeshipBean call(TrusteeshipBean bean) {
                        return bean;
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<TrusteeshipBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        mListener.OnFailure(e);
                    }

                    @Override
                    public void onNext(TrusteeshipBean bean) {
                        mListener.OnSuccess(bean);
                    }
                });
        addSubscription(subscription);
    }
}
