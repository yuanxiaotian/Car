package com.xuliucar.xuli.xuliucar.mvp.model;

import com.xuliucar.xuli.xuliucar.base.App;
import com.xuliucar.xuli.xuliucar.bean.YearTicketBean;
import com.xuliucar.xuli.xuliucar.config.ApiManager;
import com.xuliucar.xuli.xuliucar.mvp.ModelInterface.CallBackListener;
import com.xuliucar.xuli.xuliucar.mvp.ModelInterface.IDoYearTicketModel;
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

public class DoYearTicketModel extends BasePresenterImpl implements IDoYearTicketModel {
    private CallBackListener<YearTicketBean> mListener;
    public DoYearTicketModel(CallBackListener<YearTicketBean> listener){
        this.mListener = listener;
    }
    @Override
    public void getDoYearTicket() {
        Subscription subscription = ApiManager.getInstance().getCommonApi().getDoYearTicket(App.loginid,App.compid)
                .map(new Func1<YearTicketBean, YearTicketBean>() {
                    @Override
                    public YearTicketBean call(YearTicketBean bean) {
                        return bean;
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<YearTicketBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        mListener.OnFailure(e);
                    }

                    @Override
                    public void onNext(YearTicketBean bean) {
                        mListener.OnSuccess(bean);
                    }
                });
        addSubscription(subscription);
    }
}
