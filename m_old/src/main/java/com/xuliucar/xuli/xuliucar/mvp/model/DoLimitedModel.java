package com.xuliucar.xuli.xuliucar.mvp.model;

import com.xuliucar.xuli.xuliucar.base.App;
import com.xuliucar.xuli.xuliucar.bean.LimitedBean;
import com.xuliucar.xuli.xuliucar.config.ApiManager;
import com.xuliucar.xuli.xuliucar.mvp.ModelInterface.CallBackListener;
import com.xuliucar.xuli.xuliucar.mvp.ModelInterface.IDoLimitedModel;
import com.xuliucar.xuli.xuliucar.mvp.presenter.BasePresenterImpl;

import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by skyward on 2016/12/1.
 * email：
 */

public class DoLimitedModel extends BasePresenterImpl implements IDoLimitedModel {
    private CallBackListener<LimitedBean> mListener;
    public DoLimitedModel(CallBackListener<LimitedBean> listener){
        this.mListener= listener;
    }
    @Override
    public void getDoLimited() {
        Subscription subscription = ApiManager.getInstance().getCommonApi().getDoLimited(App.loginid,App.compid)
                .map(new Func1<LimitedBean, LimitedBean>() {
                    @Override
                    public LimitedBean call(LimitedBean bean) {
                        return bean;
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<LimitedBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        mListener.OnFailure(e);
                    }

                    @Override
                    public void onNext(LimitedBean bean) {
                        mListener.OnSuccess(bean);
                    }
                });
        addSubscription(subscription);
    }
}
