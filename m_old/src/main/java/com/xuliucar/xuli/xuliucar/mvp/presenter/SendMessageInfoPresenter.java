package com.xuliucar.xuli.xuliucar.mvp.presenter;

import com.google.gson.Gson;
import com.xuliucar.xuli.xuliucar.base.App;
import com.xuliucar.xuli.xuliucar.bean.MessageCountBean;

import com.xuliucar.xuli.xuliucar.bean.PeopleBean;
import com.xuliucar.xuli.xuliucar.config.CacheName;
import com.xuliucar.xuli.xuliucar.mvp.model.SendMessageModel;
import com.xuliucar.xuli.xuliucar.mvp.view.SendMessageView;
import com.xuliucar.xuli.xuliucar.utils.LogUtil;

import java.util.List;

/**
 * Created by skyward on 2016/12/7.
 * email：
 */

public class SendMessageInfoPresenter extends BasePresenterImpl implements SendMessageModel.CallBackListener {
    private SendMessageModel mModel;
    private SendMessageView mView;
    private Gson mGson = new Gson();

    public SendMessageInfoPresenter(SendMessageView view) {
        mView = view;
        mModel = new SendMessageModel(this);
    }

    public void getData() {
        mModel.getMessageInfo();
        mModel.getPeople();
    }

    @Override
    public void getCountInfo(MessageCountBean bean) {
        if (bean.isSuccess()) {
            mView.getCountsPrice(bean.getData().getLeftcount());
            mView.getTodaySend(bean.getData().getTodaycount());
            mView.getTotalSend(bean.getData().getTotalcount());
            mView.getTips(bean.getData().getTips());
        }
    }

    @Override
    public void getPeople(PeopleBean bean) {
        if (bean.isSuccess()) {
            App.mCache.put(CacheName.PEOPLE,mGson.toJson(bean));
        }
    }

    public void getOwners(){
        PeopleBean bean = mGson.fromJson(App.mCache.getAsJSONObject(CacheName.PEOPLE).toString(),PeopleBean.class);
        mView.getOwner(bean.getData().getOwner());
    }
    public void getDrivers(){
        PeopleBean bean = mGson.fromJson(App.mCache.getAsJSONObject(CacheName.PEOPLE).toString(),PeopleBean.class);
        mView.getDriver(bean.getData().getDriver());
    }
    public void getUsers(){
        PeopleBean bean = mGson.fromJson(App.mCache.getAsJSONObject(CacheName.PEOPLE).toString(),PeopleBean.class);
        mView.getUser(bean.getData().getUser());
    }


    @Override
    public void OnFailure(Throwable e) {

    }
}
