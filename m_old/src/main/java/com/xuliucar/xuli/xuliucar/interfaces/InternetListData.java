package com.xuliucar.xuli.xuliucar.interfaces;

import android.content.Context;



/**
 * Created by skyward on 2016/9/2.
 *
 */
public interface InternetListData {
    /**
     *
     * @param context 上下文
     * @param url 获取数据的url
     * @param cacheName 缓存的key值
     */
    void getDatas(Context context, String url, String cacheName);
}
