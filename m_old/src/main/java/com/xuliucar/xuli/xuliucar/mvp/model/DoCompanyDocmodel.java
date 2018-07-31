package com.xuliucar.xuli.xuliucar.mvp.model;

import com.xuliucar.xuli.xuliucar.base.App;
import com.xuliucar.xuli.xuliucar.bean.DoCompanyDocBean;
import com.xuliucar.xuli.xuliucar.config.ApiManager;
import com.xuliucar.xuli.xuliucar.mvp.ModelInterface.CallBackListener;
import com.xuliucar.xuli.xuliucar.mvp.ModelInterface.IDoCompanyDoc;
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

public class DoCompanyDocmodel extends BasePresenterImpl implements IDoCompanyDoc {
    private CallBackListener<DoCompanyDocBean> mListener;

    public DoCompanyDocmodel(CallBackListener<DoCompanyDocBean> listener) {
        mListener = listener;
    }

    @Override
    public void getDoCompanyDoc() {
        Subscription subscription = ApiManager.getInstance().getCommonApi().getDoCompanyDoc(App.loginid,App.compid)
                .map(new Func1<DoCompanyDocBean, DoCompanyDocBean>() {
                    @Override
                    public DoCompanyDocBean call(DoCompanyDocBean bean) {
                        return bean;
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<DoCompanyDocBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        mListener.OnFailure(e);
                    }

                    @Override
                    public void onNext(DoCompanyDocBean bean) {
                        mListener.OnSuccess(bean);
                    }
                });
        addSubscription(subscription);
    }
}
