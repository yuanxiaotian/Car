package com.xuliucar.xuli.xuliucar.interfaces;

import android.content.Context;
import android.os.Handler;

/**
 * Created by skyward on 2016/9/8.
 *
 */
public interface IntentDetailData {
    /**
     *
     * @param context 上下文
     * @param url 数据接口
     * @param cid 传入cid
     * @param handler 回调数据
     */
    void getDetailData(Context context, String url, String cid, Handler handler);
}
