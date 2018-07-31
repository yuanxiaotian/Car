package com.xuliucar.xuli.xuliucar.mvp.model;

import com.xuliucar.xuli.xuliucar.base.App;
import com.xuliucar.xuli.xuliucar.bean.DoRoadTCBean;
import com.xuliucar.xuli.xuliucar.config.ApiManager;
import com.xuliucar.xuli.xuliucar.mvp.ModelInterface.CallBackListener;
import com.xuliucar.xuli.xuliucar.mvp.ModelInterface.IDoRoadTCModel;
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

public class DoRoadTCModel extends BasePresenterImpl implements IDoRoadTCModel {
    private CallBackListener<DoRoadTCBean> mListener;

    public DoRoadTCModel(CallBackListener<DoRoadTCBean> listener) {
        mListener = listener;
    }

    @Override
    public void getDoRoadTC() {
        Subscription subscription = ApiManager.getInstance().getCommonApi().getDoRoadTC(App.loginid,App.compid)
                .map(new Func1<DoRoadTCBean, DoRoadTCBean>() {
                    @Override
                    public DoRoadTCBean call(DoRoadTCBean bean) {
                        return bean;
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<DoRoadTCBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        mListener.OnFailure(e);
                    }

                    @Override
                    public void onNext(DoRoadTCBean bean) {
                        mListener.OnSuccess(bean);
                    }
                });
        addSubscription(subscription);
    }
}
