package com.xuliucar.xuli.xuliucar.mvp.model;

import com.xuliucar.xuli.xuliucar.base.App;
import com.xuliucar.xuli.xuliucar.bean.DoPerpetuaeBean;
import com.xuliucar.xuli.xuliucar.config.ApiManager;
import com.xuliucar.xuli.xuliucar.mvp.ModelInterface.CallBackListener;
import com.xuliucar.xuli.xuliucar.mvp.ModelInterface.IDoPerPetuaeModel;
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

public class DoPerpetuaeModel extends BasePresenterImpl implements IDoPerPetuaeModel {
    private CallBackListener<DoPerpetuaeBean> mListener;
    public DoPerpetuaeModel(CallBackListener<DoPerpetuaeBean> listener){
        this.mListener=listener;
    }
    @Override
    public void getDoperpetuae() {
        Subscription subscription = ApiManager.getInstance().getCommonApi().getDoPerpetuae(App.loginid,App.compid)
                .map(new Func1<DoPerpetuaeBean, DoPerpetuaeBean>() {
                    @Override
                    public DoPerpetuaeBean call(DoPerpetuaeBean bean) {
                        return bean;
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<DoPerpetuaeBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        mListener.OnFailure(e);
                    }

                    @Override
                    public void onNext(DoPerpetuaeBean bean) {
                        mListener.OnSuccess(bean);
                    }
                });
        addSubscription(subscription);
    }
}
