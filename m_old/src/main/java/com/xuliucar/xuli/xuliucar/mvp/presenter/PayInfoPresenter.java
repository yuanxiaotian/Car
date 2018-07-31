package com.xuliucar.xuli.xuliucar.mvp.presenter;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;

import com.google.gson.Gson;
import com.xuliucar.xuli.xuliucar.base.App;
import com.xuliucar.xuli.xuliucar.bean.PayInfoBean;
import com.xuliucar.xuli.xuliucar.config.CacheName;
import com.xuliucar.xuli.xuliucar.mvp.ModelInterface.CallBackListener;
import com.xuliucar.xuli.xuliucar.mvp.model.PayInfoModel;
import com.xuliucar.xuli.xuliucar.mvp.view.PayInfoView;
import com.xuliucar.xuli.xuliucar.ui.Login;
import com.xuliucar.xuli.xuliucar.utils.NetWorkUtil;
import com.xuliucar.xuli.xuliucar.utils.PreferencesUtils;

/**
 * Created by skyward on 2016/11/29.
 * email：
 */

public class PayInfoPresenter extends BasePresenterImpl implements CallBackListener<PayInfoBean> {
    private PayInfoModel mModel;
    private PayInfoView mPayInfoView;
    private Gson mGson = new Gson();
    private Handler mHandler;

    public PayInfoPresenter(PayInfoView payInfoView) {
        this.mPayInfoView = payInfoView;
        mModel = new PayInfoModel(this,mPayInfoView);
    }

    public void getData() {
        mModel.getPayInfo();
    }

    @Override
    public void OnSuccess(PayInfoBean bean) {
        if (bean.isSuccess()) {
            Message message = new Message();
            message.obj = bean;
            mHandler.sendMessage(message);
        } else {
            mPayInfoView.toastMsg(bean.getMessage());
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
                PayInfoBean payInfoBean = (PayInfoBean) msg.obj;
                int size = payInfoBean.getData().size();
                if (count > size) {
                    mPayInfoView.startLoad(payInfoBean.getData().subList(0, size), size);
                } else {
                    mPayInfoView.startLoad(payInfoBean.getData().subList(0, count), size);
                }
                return true;
            }
        });
    }

    @Override
    public void loadMore(int start, int end) {
        super.loadMore(start, end);
        PayInfoBean bean = mGson.fromJson(App.mCache.getAsJSONObject(CacheName.PAYINFO).toString(), PayInfoBean.class);
        int size = bean.getData().size();
        if (start < size && end > size) {
            mPayInfoView.loadMore(bean.getData().subList(start, size), size);
        } else if (start >= size && end >= size) {
            mPayInfoView.loadMore(bean.getData().subList(size, size), size);
        } else {
            mPayInfoView.loadMore(bean.getData().subList(start, end), size);
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
