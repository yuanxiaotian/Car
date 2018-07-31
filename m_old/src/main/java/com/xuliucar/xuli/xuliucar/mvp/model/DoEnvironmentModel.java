package com.xuliucar.xuli.xuliucar.mvp.model;

import com.xuliucar.xuli.xuliucar.base.App;
import com.xuliucar.xuli.xuliucar.bean.EnvironmentBean;
import com.xuliucar.xuli.xuliucar.config.ApiManager;
import com.xuliucar.xuli.xuliucar.mvp.ModelInterface.CallBackListener;
import com.xuliucar.xuli.xuliucar.mvp.ModelInterface.IDoEnvironment;
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

public class DoEnvironmentModel extends BasePresenterImpl implements IDoEnvironment {
    private CallBackListener<EnvironmentBean> mListener;

    public DoEnvironmentModel(CallBackListener<EnvironmentBean> listener) {
        mListener = listener;
    }

    @Override
    public void getDoEnvironment() {
        Subscription subscription = ApiManager.getInstance().getCommonApi().getDoEnvironment(App.loginid,App.compid)
                .map(new Func1<EnvironmentBean, EnvironmentBean>() {
                    @Override
                    public EnvironmentBean call(EnvironmentBean bean) {
                        return bean;
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<EnvironmentBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        mListener.OnFailure(e);
                    }

                    @Override
                    public void onNext(EnvironmentBean bean) {
                        mListener.OnSuccess(bean);
                    }
                });
        addSubscription(subscription);
    }
}
