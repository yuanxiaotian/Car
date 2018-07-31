package com.xuliucar.xuli.xuliucar.mvp.model;

import com.xuliucar.xuli.xuliucar.bean.StaffDetailBean;
import com.xuliucar.xuli.xuliucar.config.ApiManager;
import com.xuliucar.xuli.xuliucar.mvp.ModelInterface.CallBackListener;
import com.xuliucar.xuli.xuliucar.mvp.ModelInterface.IStaffDetail;
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

public class StaffDetailModel extends BasePresenterImpl implements IStaffDetail {
    private CallBackListener<StaffDetailBean> mListener;
    public StaffDetailModel(CallBackListener<StaffDetailBean> listener){
        this.mListener = listener;
    }
    @Override
    public void getData(String loginid, String uid, int compid) {
        Subscription subscription = ApiManager.getInstance().getCommonApi().getStaffDetail(loginid,uid,compid)
                .map(new Func1<StaffDetailBean, StaffDetailBean>() {
                    @Override
                    public StaffDetailBean call(StaffDetailBean bean) {
                        return bean;
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<StaffDetailBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        mListener.OnFailure(e);
                    }

                    @Override
                    public void onNext(StaffDetailBean bean) {
                        mListener.OnSuccess(bean);
                    }
                });
        addSubscription(subscription);
    }
}
