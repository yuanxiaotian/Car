package com.xuliucar.xuli.xuliucar.mvp.model;

import com.xuliucar.xuli.xuliucar.base.App;
import com.xuliucar.xuli.xuliucar.bean.Counts;
import com.xuliucar.xuli.xuliucar.config.ApiManager;
import com.xuliucar.xuli.xuliucar.mvp.ModelInterface.CallBackListener;
import com.xuliucar.xuli.xuliucar.mvp.ModelInterface.IDoItemsIndex;
import com.xuliucar.xuli.xuliucar.mvp.presenter.BasePresenterImpl;
import com.xuliucar.xuli.xuliucar.utils.LogUtil;

import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by skyward on 2016/12/5.
 * email：
 */

public class DoItemsIndexModel extends BasePresenterImpl implements IDoItemsIndex {
    private CallBackListener<Counts> mListener;

    public DoItemsIndexModel(CallBackListener<Counts> listener) {
        mListener = listener;
    }

    @Override
    public void getCounts() {
        Subscription subscription = ApiManager.getInstance().getCommonApi().getDoItemsIndexCounts(App.loginid,App.compid)
                .map(new Func1<Counts, Counts>() {
                    @Override
                    public Counts call(Counts bean) {
                        return bean;
                    }
                })
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Counts>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        mListener.OnFailure(e);
                        LogUtil.LogPrint("error "+e.getMessage());
                    }

                    @Override
                    public void onNext(Counts bean) {
                        mListener.OnSuccess(bean);
                    }
                });
        addSubscription(subscription);
    }
}
