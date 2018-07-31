package com.xuliucar.xuli.xuliucar.mvp.model;

import com.xuliucar.xuli.xuliucar.bean.CarOwnerDetailBean;
import com.xuliucar.xuli.xuliucar.config.ApiManager;
import com.xuliucar.xuli.xuliucar.mvp.ModelInterface.CallBackListener;
import com.xuliucar.xuli.xuliucar.mvp.ModelInterface.ICarOwnerDetail;
import com.xuliucar.xuli.xuliucar.mvp.presenter.BasePresenterImpl;

import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by skyward on 2016/11/24.
 *
 */

public class CarOwnerDetailModel extends BasePresenterImpl implements ICarOwnerDetail {
    private CallBackListener<CarOwnerDetailBean> mListener;

    public CarOwnerDetailModel(CallBackListener<CarOwnerDetailBean> listener){
        this.mListener = listener;
    }

    @Override
    public void getData(String loginid, String oid, int compid) {
        Subscription subscription = ApiManager.getInstance().getCommonApi().getCarOwnerDetail(loginid,oid,compid)
                .map(new Func1<CarOwnerDetailBean, CarOwnerDetailBean>() {
                    @Override
                    public CarOwnerDetailBean call(CarOwnerDetailBean bean) {
                        return bean;
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CarOwnerDetailBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        mListener.OnFailure(e);
                    }

                    @Override
                    public void onNext(CarOwnerDetailBean bean) {
                        mListener.OnSuccess(bean);
                    }
                });
        addSubscription(subscription);
    }

}
