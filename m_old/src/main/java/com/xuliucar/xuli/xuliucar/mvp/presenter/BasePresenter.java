package com.xuliucar.xuli.xuliucar.mvp.presenter;

import android.content.Context;

/**
 * Created by skyward on 2016/11/21.
 *
 */

public interface BasePresenter {
    void unSubcrible();
    void toLogin(Context context);
    void alreadyLogin(Context context);
    void loadData();
    void startLoad(int count);
    void loadMore(int start,int end);
}
