package com.xuliucar.xuli.xuliucar.mvp.presenter;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;

import com.google.gson.Gson;
import com.xuliucar.xuli.xuliucar.base.App;
import com.xuliucar.xuli.xuliucar.bean.DoRoadTCBean;
import com.xuliucar.xuli.xuliucar.config.CacheName;
import com.xuliucar.xuli.xuliucar.mvp.ModelInterface.CallBackListener;
import com.xuliucar.xuli.xuliucar.mvp.model.DoRoadTCModel;
import com.xuliucar.xuli.xuliucar.mvp.view.DoRoadTCView;
import com.xuliucar.xuli.xuliucar.ui.Login;
import com.xuliucar.xuli.xuliucar.utils.NetWorkUtil;
import com.xuliucar.xuli.xuliucar.utils.PreferencesUtils;

/**
 * Created by skyward on 2016/12/1.
 * email：
 */

public class DoRoadTCPresenter extends BasePresenterImpl implements CallBackListener<DoRoadTCBean> {
    private DoRoadTCModel mModel;
    private DoRoadTCView mView;
    private Gson mGson = new Gson();
    private Handler mHandler;

    public DoRoadTCPresenter(DoRoadTCView view) {
        mView = view;
        mModel = new DoRoadTCModel(this);
    }

    public void getData(){
        if(!NetWorkUtil.isNetWorkAvailable(App.getContext())){
            mView.showError();
        }else {
            mModel.getDoRoadTC();
        }
    }

    @Override
    public void OnSuccess(DoRoadTCBean bean) {
        if(bean.isSuccess()){
            App.mCache.put(CacheName.DOROADTC,mGson.toJson(bean));
            Message message = new Message();
            message.obj = bean;
            mHandler.sendMessage(message);
        }else {
            mView.toastMsg(bean.getMessage());
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
                DoRoadTCBean bean = (DoRoadTCBean) msg.obj;
                int size = bean.getData().getInfo().size();
                if(count >size){
                    mView.startLoad(bean.getData().getInfo().subList(0,size),size);
                }else {
                    mView.startLoad(bean.getData().getInfo().subList(0, count), size);
                }
                return true;
            }
        });
    }

    @Override
    public void loadMore(int start, int end) {
        super.loadMore(start, end);
        DoRoadTCBean bean = mGson.fromJson(App.mCache.getAsJSONObject(CacheName.DOROADTC).toString(),DoRoadTCBean.class);
        int size = bean.getData().getInfo().size();
        if(start < size && end > size){
            mView.loadMore(bean.getData().getInfo().subList(start,size),size);
        }else if(start >= size && end >= size){
            mView.loadMore(bean.getData().getInfo().subList(size,size),size);
        }else {
            mView.loadMore(bean.getData().getInfo().subList(start,end),size);
        }
    }
    public void loadAll(){
        DoRoadTCBean bean = mGson.fromJson(App.mCache.getAsJSONObject(CacheName.DOROADTC).toString(),DoRoadTCBean.class);
        mView.loadAll(bean.getData().getInfo());
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
