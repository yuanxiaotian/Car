package com.xuliucar.xuli.xuliucar.mvp.model;

import com.xuliucar.xuli.xuliucar.bean.MessageBean;
import com.xuliucar.xuli.xuliucar.config.ApiManager;
import com.xuliucar.xuli.xuliucar.mvp.ModelInterface.CallBackListener;
import com.xuliucar.xuli.xuliucar.mvp.ModelInterface.IChangePwd;
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

public class ChangePwdModel extends BasePresenterImpl implements IChangePwd {
    private CallBackListener<MessageBean> mListener;
    public ChangePwdModel(CallBackListener<MessageBean> onChangePwdListener){
        this.mListener = onChangePwdListener;
    }
    @Override
    public void getData(String loginid, int compid, String opassword, String npassword) {
        Subscription subscription = ApiManager.getInstance().getCommonApi().changpwd(loginid,compid,opassword,npassword)
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
