package com.xuliucar.xuli.xuliucar.mvp.presenter;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;

import com.google.gson.Gson;
import com.xuliucar.xuli.xuliucar.base.App;
import com.xuliucar.xuli.xuliucar.bean.YearRoadTBean;
import com.xuliucar.xuli.xuliucar.config.CacheName;
import com.xuliucar.xuli.xuliucar.mvp.ModelInterface.CallBackListener;
import com.xuliucar.xuli.xuliucar.mvp.model.DoRoadRTLModel;
import com.xuliucar.xuli.xuliucar.mvp.view.YearRoadTView;
import com.xuliucar.xuli.xuliucar.ui.Login;
import com.xuliucar.xuli.xuliucar.utils.NetWorkUtil;
import com.xuliucar.xuli.xuliucar.utils.PreferencesUtils;

/**
 * Created by skyward on 2016/12/2.
 * email：
 */

public class DoYearRoadTPresenter extends BasePresenterImpl implements CallBackListener<YearRoadTBean> {
    private DoRoadRTLModel mModel;
    private YearRoadTView mView;
    private Gson mGson = new Gson();
    private Handler mHandler;
    public DoYearRoadTPresenter(YearRoadTView view) {
        mView = view;
        mModel = new DoRoadRTLModel(this);
    }

    public void getData(){
        if(!NetWorkUtil.isNetWorkAvailable(App.getContext())){
            mView.showError();
        }else {
            mModel.getDoRoadTCL();
        }
    }

    @Override
    public void OnSuccess(YearRoadTBean bean) {
        if(bean.isSuccess()){
            App.mCache.put(CacheName.DOYEARROADT,mGson.toJson(bean));
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
                YearRoadTBean bean = (YearRoadTBean) msg.obj;
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
        YearRoadTBean bean = mGson.fromJson(App.mCache.getAsJSONObject(CacheName.DOYEARROADT).toString(),YearRoadTBean.class);
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
        YearRoadTBean bean = mGson.fromJson(App.mCache.getAsJSONObject(CacheName.DOYEARROADT).toString(),YearRoadTBean.class);
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
