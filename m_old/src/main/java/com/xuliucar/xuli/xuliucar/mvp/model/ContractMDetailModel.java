package com.xuliucar.xuli.xuliucar.mvp.model;

import com.xuliucar.xuli.xuliucar.base.App;
import com.xuliucar.xuli.xuliucar.bean.ContractMDetailBean;
import com.xuliucar.xuli.xuliucar.config.ApiManager;
import com.xuliucar.xuli.xuliucar.mvp.ModelInterface.CallBackListener;
import com.xuliucar.xuli.xuliucar.mvp.ModelInterface.IContractMDetailModel;
import com.xuliucar.xuli.xuliucar.mvp.presenter.BasePresenterImpl;

import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by skyward on 2016/11/30.
 * email：
 */

public class ContractMDetailModel extends BasePresenterImpl implements IContractMDetailModel {
    private CallBackListener<ContractMDetailBean> mListener;
    public ContractMDetailModel(CallBackListener<ContractMDetailBean> listener){
        this.mListener = listener;
    }
    @Override
    public void getContractMDetail(String coid) {
        Subscription subscription = ApiManager.getInstance().getCommonApi().getContractMDetail(App.loginid,coid,App.compid)
                .map(new Func1<ContractMDetailBean, ContractMDetailBean>() {
                    @Override
                    public ContractMDetailBean call(ContractMDetailBean bean) {
                        return bean;
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ContractMDetailBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        mListener.OnFailure(e);
                    }

                    @Override
                    public void onNext(ContractMDetailBean bean) {
                        mListener.OnSuccess(bean);
                    }
                });
        addSubscription(subscription);
    }
}
