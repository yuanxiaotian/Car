package com.xuliucar.xuli.xuliucar.mvp.presenter;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;

import com.google.gson.Gson;
import com.xuliucar.xuli.xuliucar.base.App;
import com.xuliucar.xuli.xuliucar.bean.InComeBean;
import com.xuliucar.xuli.xuliucar.config.CacheName;
import com.xuliucar.xuli.xuliucar.mvp.ModelInterface.CallBackListener;
import com.xuliucar.xuli.xuliucar.mvp.model.InComeModel;
import com.xuliucar.xuli.xuliucar.mvp.view.InComeView;
import com.xuliucar.xuli.xuliucar.ui.Login;
import com.xuliucar.xuli.xuliucar.utils.NetWorkUtil;
import com.xuliucar.xuli.xuliucar.utils.PreferencesUtils;

/**
 * Created by skyward on 2016/11/29.
 * email：
 */

public class InComePresenter extends BasePresenterImpl implements CallBackListener<InComeBean> {
    private InComeModel mModel;
    private InComeView mView;
    private Gson mGson = new Gson();
    private Handler mHandler;

    public InComePresenter(InComeView inComeView) {
        this.mView = inComeView;
        mModel = new InComeModel(this, mView);
    }

    public void getInCome() {
        mModel.getInCome();
    }

    @Override
    public void OnSuccess(InComeBean inComeBean) {
        if (inComeBean.isSuccess()) {
            Message message = new Message();
            message.obj = inComeBean;
            mHandler.sendMessage(message);
        } else {
            mView.toastMsg(inComeBean.getMessage());
        }
    }

    @Override
    public void OnFailure(Throwable e) {

    }

    public void startLoad(final int count) {
        mHandler = new Handler(msg -> {
            InComeBean bean = (InComeBean) msg.obj;
            int size = bean.getData().size();
            if (count > size) {
                mView.startLoad(bean.getData().subList(0, size), size);
            } else {
                mView.startLoad(bean.getData().subList(0, count), size);
            }
            return true;
        });
    }

    public void loadMore(int start, int end) {
        InComeBean bean = mGson.fromJson(App.mCache.getAsJSONObject(CacheName.INCOMEDETAIL).toString(), InComeBean.class);
        int size = bean.getData().size();
        if (start < size && end > size) {
            mView.loadMore(bean.getData().subList(start, size), size);
        } else if (start >= size && end >= size) {
            mView.loadMore(bean.getData().subList(size, size), size);
        } else {
            mView.loadMore(bean.getData().subList(start, end), size);
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
