package com.xuliucar.xuli.xuliucar.mvp.presenter;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;


import com.google.gson.Gson;
import com.xuliucar.xuli.xuliucar.base.App;
import com.xuliucar.xuli.xuliucar.bean.AchieveManageBean;
import com.xuliucar.xuli.xuliucar.config.CacheName;
import com.xuliucar.xuli.xuliucar.mvp.model.AchieveManageModel;
import com.xuliucar.xuli.xuliucar.mvp.ModelInterface.CallBackListener;
import com.xuliucar.xuli.xuliucar.mvp.view.AchieveManageView;
import com.xuliucar.xuli.xuliucar.ui.Login;
import com.xuliucar.xuli.xuliucar.utils.NetWorkUtil;
import com.xuliucar.xuli.xuliucar.utils.PreferencesUtils;

/**
 * Created by skyward on 2016/11/30.
 * email：
 */

public class AchieveManagePresenter extends BasePresenterImpl implements CallBackListener<AchieveManageBean> {
    private AchieveManageModel mModel;
    private AchieveManageView mView;
    private Gson mGson = new Gson();
    private Handler mHandler;
    public AchieveManagePresenter(AchieveManageView view){
        this.mView = view;
        mModel = new AchieveManageModel(this);
    }
    public void getData(){
        if(!NetWorkUtil.isNetWorkAvailable(App.getContext())){
            mView.showError();
        }else {
            mModel.getAchieveManage();
        }
    }
    @Override
    public void OnSuccess(AchieveManageBean bean) {
        if(bean.isSuccess()){
            App.mCache.put(CacheName.ACHIEVEMANAGE,mGson.toJson(bean));
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
                AchieveManageBean bean = (AchieveManageBean) msg.obj;
                int size = bean.getData().size();
                if(count > size){
                    mView.startLoad(bean.getData().subList(0,size),size);
                }else {
                    mView.startLoad(bean.getData().subList(0,count),size);
                }
                return true;
            }
        });
    }

    @Override
    public void loadMore(int start, int end) {
        super.loadMore(start, end);
        AchieveManageBean bean = mGson.fromJson(App.mCache.getAsJSONObject(CacheName.ACHIEVEMANAGE).toString(),AchieveManageBean.class);
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
