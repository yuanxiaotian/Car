package com.xuliucar.xuli.xuliucar.mvp.presenter;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;

import com.google.gson.Gson;
import com.xuliucar.xuli.xuliucar.base.App;
import com.xuliucar.xuli.xuliucar.bean.OutComeBean;
import com.xuliucar.xuli.xuliucar.config.CacheName;
import com.xuliucar.xuli.xuliucar.mvp.ModelInterface.CallBackListener;
import com.xuliucar.xuli.xuliucar.mvp.model.OutComeModel;
import com.xuliucar.xuli.xuliucar.mvp.view.OutComeView;
import com.xuliucar.xuli.xuliucar.ui.Login;
import com.xuliucar.xuli.xuliucar.utils.NetWorkUtil;
import com.xuliucar.xuli.xuliucar.utils.PreferencesUtils;

/**
 * Created by skyward on 2016/11/29.
 * email：
 */

public class OutComePresenter extends BasePresenterImpl implements CallBackListener<OutComeBean> {
    private OutComeModel mOutComeModel;
    private OutComeView mOutComeView;
    private Gson mGson = new Gson();

    private Handler mHandler;

    public OutComePresenter(OutComeView outComeView) {
        this.mOutComeView = outComeView;
        mOutComeModel = new OutComeModel(this, mOutComeView);
    }

    public void getData() {
        mOutComeModel.getOutCome();
    }

    @Override
    public void OnSuccess(OutComeBean bean) {
        if (bean.isSuccess()) {
            Message message = new Message();
            message.obj = bean;
            mHandler.sendMessage(message);
        } else {
            mOutComeView.toastMsg(bean.getMessage());
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
                OutComeBean outComeBean = (OutComeBean) msg.obj;
                int size = outComeBean.getData().size();
                if (count > size) {
                    mOutComeView.startLoad(outComeBean.getData().subList(0, size), size);
                } else {
                    mOutComeView.startLoad(outComeBean.getData().subList(0, count), size);
                }
                return true;
            }
        });
    }

    @Override
    public void loadMore(int start, int end) {
        super.loadMore(start, end);
        OutComeBean bean = mGson.fromJson(App.mCache.getAsJSONObject(CacheName.OUTCOMEDETAIL).toString(), OutComeBean.class);
        int size = bean.getData().size();
        if (start < size && end > size) {
            mOutComeView.loadMore(bean.getData().subList(start, size), size);
        } else if (start >= size && end >= size) {
            mOutComeView.loadMore(bean.getData().subList(size, size), size);
        } else {
            mOutComeView.loadMore(bean.getData().subList(start, end), size);
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
