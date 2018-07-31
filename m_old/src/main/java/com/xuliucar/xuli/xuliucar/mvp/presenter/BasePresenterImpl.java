package com.xuliucar.xuli.xuliucar.mvp.presenter;

import android.content.Context;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by skyward on 2016/11/21.
 *
 */

public class BasePresenterImpl implements BasePresenter {
    private CompositeSubscription mCompositeSubscription;

    protected  void addSubscription(Subscription s){
        if(this.mCompositeSubscription == null){
            this.mCompositeSubscription = new CompositeSubscription();
        }
        this.mCompositeSubscription.add(s);
    }

    @Override
    public void unSubcrible() {
        if (this.mCompositeSubscription != null) {
            this.mCompositeSubscription.unsubscribe();
        }
    }

    @Override
    public void toLogin(Context context) {

    }

    @Override
    public void alreadyLogin(Context context) {

    }

    @Override
    public void loadData() {

    }

    @Override
    public void startLoad(int count) {

    }

    @Override
    public void loadMore(int start, int end) {

    }


}
