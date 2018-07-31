package com.xuliucar.xuli.xuliucar.mvp.model;

import com.xuliucar.xuli.xuliucar.base.App;
import com.xuliucar.xuli.xuliucar.bean.ConstructionWasteBean;
import com.xuliucar.xuli.xuliucar.config.ApiManager;
import com.xuliucar.xuli.xuliucar.mvp.ModelInterface.CallBackListener;
import com.xuliucar.xuli.xuliucar.mvp.ModelInterface.IPassConstructionWaste;
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

public class PassConstructionWastModel extends BasePresenterImpl implements IPassConstructionWaste {
    private CallBackListener<ConstructionWasteBean> mListener;

    public PassConstructionWastModel(CallBackListener<ConstructionWasteBean> listener) {
        mListener = listener;
    }

    @Override
    public void getPassConstructionWaste() {
        Subscription subscription = ApiManager.getInstance().getCommonApi().getPassConstructionWast(App.loginid,App.compid)
                .map(new Func1<ConstructionWasteBean, ConstructionWasteBean>() {
                    @Override
                    public ConstructionWasteBean call(ConstructionWasteBean bean) {
                        return bean;
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ConstructionWasteBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        mListener.OnFailure(e);
                    }

                    @Override
                    public void onNext(ConstructionWasteBean bean) {
                        mListener.OnSuccess(bean);
                    }
                });
        addSubscription(subscription);
    }
}
