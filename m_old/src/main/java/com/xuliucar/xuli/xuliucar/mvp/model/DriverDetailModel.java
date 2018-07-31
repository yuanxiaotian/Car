package com.xuliucar.xuli.xuliucar.mvp.model;

import com.xuliucar.xuli.xuliucar.bean.DriverDetailBean;
import com.xuliucar.xuli.xuliucar.config.ApiManager;
import com.xuliucar.xuli.xuliucar.mvp.ModelInterface.CallBackListener;
import com.xuliucar.xuli.xuliucar.mvp.ModelInterface.IDriverDetail;
import com.xuliucar.xuli.xuliucar.mvp.presenter.BasePresenterImpl;

import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by skyward on 2016/11/24.
 * email：
 */

public class DriverDetailModel extends BasePresenterImpl implements IDriverDetail {
    private CallBackListener<DriverDetailBean> mListener;
    public DriverDetailModel(CallBackListener<DriverDetailBean> listener){
        this.mListener = listener;
    }
    @Override
    public void getData(String loginid, String did, int compin) {
        Subscription subscription = ApiManager.getInstance().getCommonApi().getDriverDetail(loginid,did,compin)
                .map(new Func1<DriverDetailBean, DriverDetailBean>() {
                    @Override
                    public DriverDetailBean call(DriverDetailBean detailBean) {
                        return detailBean;
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<DriverDetailBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        mListener.OnFailure(e);
                    }

                    @Override
                    public void onNext(DriverDetailBean detailBean) {
                        mListener.OnSuccess(detailBean);
                    }
                });
        addSubscription(subscription);
    }
}
