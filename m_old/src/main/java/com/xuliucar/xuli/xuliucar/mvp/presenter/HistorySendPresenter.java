package com.xuliucar.xuli.xuliucar.mvp.presenter;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;

import com.google.gson.Gson;
import com.xuliucar.xuli.xuliucar.base.App;
import com.xuliucar.xuli.xuliucar.bean.HistorySendBean;
import com.xuliucar.xuli.xuliucar.config.CacheName;
import com.xuliucar.xuli.xuliucar.mvp.ModelInterface.CallBackListener;
import com.xuliucar.xuli.xuliucar.mvp.model.HistorySendModel;
import com.xuliucar.xuli.xuliucar.mvp.view.HistorySendView;
import com.xuliucar.xuli.xuliucar.ui.Login;
import com.xuliucar.xuli.xuliucar.utils.PreferencesUtils;

/**
 * Created by skyward on 2016/12/7.
 * email：
 */

public class HistorySendPresenter extends BasePresenterImpl implements CallBackListener<HistorySendBean>{
    private HistorySendModel mModel;
    private HistorySendView mView;
    private Gson mGson = new Gson();
    private Handler mHandler;
    public HistorySendPresenter(HistorySendView view) {
        mView = view;
        mModel = new HistorySendModel(this);
    }

    public void getData(){
        mModel.getHistorySend();
    }

    @Override
    public void OnSuccess(HistorySendBean bean) {
        if(bean.isSuccess()){
            App.mCache.put(CacheName.HISTORYSEND,mGson.toJson(bean));
            Message message = new Message();
            message.obj = bean;
            mHandler.sendMessage(message);
        }
    }

    @Override
    public void OnFailure(Throwable e) {

    }

    @Override
    public void startLoad(final int count) {
        super.startLoad(count);
        mHandler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {
                HistorySendBean bean = (HistorySendBean) msg.obj;
                int size = bean.getData().size();
                if(count >size){
                    mView.startLoad(bean.getData().subList(0,size),size);
                }else {
                    mView.startLoad(bean.getData().subList(0, count), size);
                }
                return true;
            }
        });
    }

    @Override
    public void loadMore(int start, int end) {
        super.loadMore(start, end);
        HistorySendBean bean =mGson.fromJson (App.mCache.getAsJSONObject(CacheName.HISTORYSEND).toString(),HistorySendBean.class);
            int size = bean.getData().size();
            if(start < size && end > size){
                mView.loadMore(bean.getData().subList(start,size),size);
            }else if(start >= size && end >= size){
                mView.loadMore(bean.getData().subList(size,size),size);
            }else {
                mView.loadMore(bean.getData().subList(start,end),size);
            }
    }

    @Override
    public void toLogin(Context context) {
        super.toLogin(context);
        Intent intent = new Intent(context, Login.class);
        context.startActivity(intent);
    }

    @Override
    public void alreadyLogin(final Context context) {
        super.alreadyLogin(context);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(context, Login.class);
                PreferencesUtils.clearSharePre(context, "userInfo", "password");
                context.startActivity(i);

            }
        }, 1000);
    }
}
