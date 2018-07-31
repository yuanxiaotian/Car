package com.xuliucar.xuli.xuliucar.mvp.model;

import com.xuliucar.xuli.xuliucar.bean.MessageBean;
import com.xuliucar.xuli.xuliucar.config.ApiManager;
import com.xuliucar.xuli.xuliucar.mvp.ModelInterface.CallBackListener;
import com.xuliucar.xuli.xuliucar.mvp.ModelInterface.IFeedBack;
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

public class FeedBackModel extends BasePresenterImpl implements IFeedBack {
    private CallBackListener<MessageBean> mListener;
    public FeedBackModel(CallBackListener<MessageBean> listener){
        this.mListener = listener;
    }
    @Override
    public void getResutl(String loginid, int compid, String name, String phone, String content) {
        Subscription subscription = ApiManager.getInstance().getCommonApi().feedBack(loginid,compid,name,phone,content)
                .map(new Func1<MessageBean, MessageBean>() {
                    @Override
                    public MessageBean call(MessageBean bean) {
                        return bean;
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MessageBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        mListener.OnFailure(e);
                    }

                    @Override
                    public void onNext(MessageBean bean) {
                        mListener.OnSuccess(bean);
                    }
                });
        addSubscription(subscription);
    }
}
