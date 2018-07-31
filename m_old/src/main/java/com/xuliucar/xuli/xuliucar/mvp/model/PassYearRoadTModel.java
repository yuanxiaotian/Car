package com.xuliucar.xuli.xuliucar.mvp.model;

import com.xuliucar.xuli.xuliucar.base.App;
import com.xuliucar.xuli.xuliucar.bean.YearRoadTBean;
import com.xuliucar.xuli.xuliucar.config.ApiManager;
import com.xuliucar.xuli.xuliucar.mvp.ModelInterface.CallBackListener;
import com.xuliucar.xuli.xuliucar.mvp.ModelInterface.IPassYearRoadT;
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

public class PassYearRoadTModel extends BasePresenterImpl implements IPassYearRoadT {
    private CallBackListener<YearRoadTBean> mListener;

    public PassYearRoadTModel(CallBackListener<YearRoadTBean> listener) {
        mListener = listener;
    }

    @Override
    public void getPassYearRoadT() {
        Subscription subscription = ApiManager.getInstance().getCommonApi().getPassYearRoadT(App.loginid,App.compid)
                .map(new Func1<YearRoadTBean, YearRoadTBean>() {
                    @Override
                    public YearRoadTBean call(YearRoadTBean bean) {
                        return bean;
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<YearRoadTBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        mListener.OnFailure(e);
                    }

                    @Override
                    public void onNext(YearRoadTBean bean) {
                        mListener.OnSuccess(bean);
                    }
                });
        addSubscription(subscription);
    }
}
