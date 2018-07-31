package com.xuliucar.xuli.xuliucar.mvp.model;

import com.xuliucar.xuli.xuliucar.base.App;
import com.xuliucar.xuli.xuliucar.bean.AchieveManageBean;
import com.xuliucar.xuli.xuliucar.config.ApiManager;
import com.xuliucar.xuli.xuliucar.mvp.ModelInterface.CallBackListener;
import com.xuliucar.xuli.xuliucar.mvp.ModelInterface.IAchieveManageModel;
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

public class AchieveManageModel extends BasePresenterImpl implements IAchieveManageModel {
    private CallBackListener<AchieveManageBean> mListener;
    public AchieveManageModel(CallBackListener<AchieveManageBean> listener){
        this.mListener =listener;
    }
    @Override
    public void getAchieveManage() {
        Subscription subscription = ApiManager.getInstance().getCommonApi().getAchieveManage(App.loginid,App.compid)
                .map(new Func1<AchieveManageBean, AchieveManageBean>() {
                    @Override
                    public AchieveManageBean call(AchieveManageBean bean) {
                        return bean;
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<AchieveManageBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        mListener.OnFailure(e);
                    }

                    @Override
                    public void onNext(AchieveManageBean bean) {
                        mListener.OnSuccess(bean);
                    }
                });
        addSubscription(subscription);
    }
}
