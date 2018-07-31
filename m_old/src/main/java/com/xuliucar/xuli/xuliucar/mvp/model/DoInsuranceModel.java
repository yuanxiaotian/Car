package com.xuliucar.xuli.xuliucar.mvp.model;

import com.xuliucar.xuli.xuliucar.base.App;
import com.xuliucar.xuli.xuliucar.bean.InSuranceBean;
import com.xuliucar.xuli.xuliucar.config.ApiManager;
import com.xuliucar.xuli.xuliucar.mvp.ModelInterface.CallBackListener;
import com.xuliucar.xuli.xuliucar.mvp.ModelInterface.IDoInsuranceModel;
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

public class DoInsuranceModel extends BasePresenterImpl implements IDoInsuranceModel {
    private CallBackListener<InSuranceBean> mListener;
    public DoInsuranceModel(CallBackListener<InSuranceBean> listener){
        this.mListener=listener;
    }
    @Override
    public void getDoInsurance() {
        Subscription subscription = ApiManager.getInstance().getCommonApi().getDoInsurance(App.loginid,App.compid)
                .map(new Func1<InSuranceBean, InSuranceBean>() {
                    @Override
                    public InSuranceBean call(InSuranceBean bean) {
                        return bean;
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<InSuranceBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        mListener.OnFailure(e);
                    }

                    @Override
                    public void onNext(InSuranceBean bean) {
                        mListener.OnSuccess(bean);
                    }
                });
        addSubscription(subscription);
    }
}
