package com.xuliucar.xuli.xuliucar.mvp.model;

import com.xuliucar.xuli.xuliucar.base.App;
import com.xuliucar.xuli.xuliucar.bean.HistorySendBean;
import com.xuliucar.xuli.xuliucar.config.ApiManager;
import com.xuliucar.xuli.xuliucar.mvp.ModelInterface.CallBackListener;
import com.xuliucar.xuli.xuliucar.mvp.ModelInterface.IHistorySend;
import com.xuliucar.xuli.xuliucar.mvp.presenter.BasePresenterImpl;
import com.xuliucar.xuli.xuliucar.utils.LogUtil;

import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by skyward on 2016/12/7.
 * email：
 */

public class HistorySendModel extends BasePresenterImpl implements IHistorySend{
    private CallBackListener<HistorySendBean> mListener;
    private long mStart2;

    public HistorySendModel(CallBackListener<HistorySendBean> listener) {
        mListener = listener;
    }

    @Override
    public void getHistorySend() {
        Subscription subscription = ApiManager.getInstance().getCommonApi().getHistorySend(App.loginid,App.compid)
                .map(new Func1<HistorySendBean, HistorySendBean>() {
                    @Override
                    public HistorySendBean call(HistorySendBean s) {
                        return s;
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HistorySendBean>() {
                    @Override
                    public void onCompleted() {
                        LogUtil.LogPrint("HistorySendModel:" + (System.currentTimeMillis() - mStart2));
                    }

                    @Override
                    public void onError(Throwable e) {
                        mListener.OnFailure(e);
                    }

                    @Override
                    public void onNext(HistorySendBean bean) {

                        mStart2 = System.currentTimeMillis();
                        mListener.OnSuccess(bean);
                    }
                });
        addSubscription(subscription);
    }
}
