package com.xuliucar.xuli.xuliucar.mvp.presenter;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;

import com.google.gson.Gson;
import com.xuliucar.xuli.xuliucar.base.App;
import com.xuliucar.xuli.xuliucar.bean.OrderManageBean;
import com.xuliucar.xuli.xuliucar.config.CacheName;
import com.xuliucar.xuli.xuliucar.mvp.ModelInterface.CallBackListener;
import com.xuliucar.xuli.xuliucar.mvp.model.OrderManageModel;
import com.xuliucar.xuli.xuliucar.mvp.view.OrderManageView;
import com.xuliucar.xuli.xuliucar.ui.Login;
import com.xuliucar.xuli.xuliucar.utils.NetWorkUtil;
import com.xuliucar.xuli.xuliucar.utils.PreferencesUtils;

/**
 * Created by skyward on 2016/11/30.
 * email：
 */

public class OrderManagePresenter extends BasePresenterImpl implements CallBackListener<OrderManageBean> {
    private OrderManageModel mModel;
    private OrderManageView mView;
    private Gson mGson = new Gson();
    private Handler mHandler;
    private int TotalAmount = 0;
    private int TotalOrder = 0;

    public OrderManagePresenter(OrderManageView view) {
        this.mView = view;
        mModel = new OrderManageModel(this);
    }

    public void getData() {
        mModel.getOrderManage(mView.loginId(), mView.companyId());
    }

    @Override
    public void OnSuccess(OrderManageBean bean) {
        if (bean.isSuccess()) {
            App.mCache.put(CacheName.ORDERMANAGE, mGson.toJson(bean));
            Message message = new Message();
            message.obj = bean;
            mHandler.sendMessage(message);
        } else {
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
                OrderManageBean bean = (OrderManageBean) msg.obj;
                int size = bean.getData().size();
                for (int i = 0; i < size; i++) {
                    String status = bean.getData().get(i).getStatus();
                    if (status.equals("已成交")) {
                        double dealprice = Double.parseDouble(bean.getData().get(i).getDealprice());
                        TotalAmount += dealprice;
                        TotalOrder += 1;
                    }
                }
                String sp = String.valueOf(TotalAmount / 10000);
                String tp = String.valueOf(TotalOrder);
                PreferencesUtils.putSharePre(App.getContext(), "orderSale", "sp", sp);
                PreferencesUtils.putSharePre(App.getContext(), "orderSale", "tp", tp);
                if (count > size) {
                    mView.startLoad(bean.getData().subList(0, size), size);
                } else {
                    mView.startLoad(bean.getData().subList(0, count), size);
                }
                return true;
            }
        });
    }

    @Override
    public void loadMore(int start, int end) {
        super.loadMore(start, end);
        OrderManageBean bean = mGson.fromJson(App.mCache.getAsJSONObject(CacheName.ORDERMANAGE).toString(), OrderManageBean.class);
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
