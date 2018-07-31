package com.xuliucar.xuli.xuliucar.dataHandlers;

import android.content.Context;
import android.os.Handler;

import com.xuliucar.xuli.xuliucar.interfaces.InternetData;

/**
 * Created by skyward on 2016/9/26.
 *
 */

public abstract class DataWrapper implements InternetData{

    @Override
    public void getDetailData(Context context, String url, String cid, Handler handler) {

    }

    @Override
    public void getDatas(Context context, String url, String cacheName) {

    }
}
