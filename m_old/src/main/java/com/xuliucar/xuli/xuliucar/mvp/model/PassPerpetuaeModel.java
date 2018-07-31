package com.xuliucar.xuli.xuliucar.mvp.model;

import com.xuliucar.xuli.xuliucar.base.App;
import com.xuliucar.xuli.xuliucar.bean.PassPerPetaueBean;
import com.xuliucar.xuli.xuliucar.config.ApiManager;
import com.xuliucar.xuli.xuliucar.mvp.ModelInterface.CallBackListener;
import com.xuliucar.xuli.xuliucar.mvp.ModelInterface.IPassPerpetuae;
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

public class PassPerpetuaeModel extends BasePresenterImpl implements IPassPerpetuae {
    private CallBackListener<PassPerPetaueBean> mListener;

    public PassPerpetuaeModel(CallBackListener<PassPerPetaueBean> listener) {
        mListener = listener;
    }

    @Override
    public void getPassPerpetuae() {
        Subscription subscription = ApiManager.getInstance().getCommonApi().getPassPerpetaue(App.loginid,App.compid)
                .map(new Func1<PassPerPetaueBean, PassPerPetaueBean>() {
                    @Override
                    public PassPerPetaueBean call(PassPerPetaueBean bean) {
                        return bean;
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<PassPerPetaueBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        mListener.OnFailure(e);
                    }

                    @Override
                    public void onNext(PassPerPetaueBean bean) {
                        mListener.OnSuccess(bean);
                    }
                });
        addSubscription(subscription);
    }
}
