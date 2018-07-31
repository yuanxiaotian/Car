package com.xuliucar.car.receiver;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;

import com.tencent.android.tpush.XGPushBaseReceiver;
import com.tencent.android.tpush.XGPushClickedResult;
import com.tencent.android.tpush.XGPushManager;
import com.tencent.android.tpush.XGPushRegisterResult;
import com.tencent.android.tpush.XGPushShowedResult;
import com.tencent.android.tpush.XGPushTextMessage;
import com.xuliucar.xuli.xuliucar.base.App;
import com.xuliucar.xuli.xuliucar.ui.homePage.financeManager.PayInfoDetail;
import com.xuliucar.xuli.xuliucar.utils.ToastUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by skyward on 2016/9/8.
 */
public class MessageReceiver extends XGPushBaseReceiver {

    //注册的结果
    @Override
    public void onRegisterResult(Context context, int i, XGPushRegisterResult xgPushRegisterResult) {
        if (context == null || xgPushRegisterResult == null) {
            return;
        }
        if (i == XGPushBaseReceiver.SUCCESS) {
            unbindtoken(context, xgPushRegisterResult.getToken());
            //Log.i("myLog","信鸽注册成功 "+" token "+xgPushRegisterResult.getToken());
        } else if (i == 10100) {
            ToastUtil.showShortToast(context, "当前网络不可用");
        } else if (i == 10101 || i == 10102 || i == 10103 || i == 10104 || i == 10105 || i == 10106 || i == 10107) {
            //Log.i("myLog","信鸽重连");
            XGPushManager.registerPush(context, App.pushCount);
            XGPushManager.setTag(context, App.tag1);
            XGPushManager.setTag(context, App.tag2);
        }
    }

    private void unbindtoken(final Context context, String token) {
        OkHttpClient client = new OkHttpClient();
        RequestBody requestBody = new FormBody.Builder()
                .add("loginid", App.loginid)
                .add("compid", String.valueOf(App.compid))
                .add("token", token)
                .add("devtype", "android")
                .build();
        final Request request = new Request.Builder()
                .addHeader("cookie", App.cookie)
                .url("http://www.gzxlxx.com:8866/index.php/Home/App/app_xgpush_unbindtoken")
                .post(requestBody)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String t = response.body().string();
                new Handler(context.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            JSONObject object = new JSONObject(t);
                            //Log.i("myLog","object "+object);
                            String success = object.getString("success");
                            String msg = object.getString("message");

                            if (success.equals("false")) {
                                if (!msg.equals("UNKOWN_OF_DEVTYPE") || !msg.equals("TOKEN_ERROR")) {
                                    ToastUtil.showShortToast(context, msg);
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });


    }

    //反注册（取消）结果
    @Override
    public void onUnregisterResult(Context context, int i) {

    }

    //设置标签的结果
    @Override
    public void onSetTagResult(Context context, int i, String s) {

    }

    //删除标签的结果
    @Override
    public void onDeleteTagResult(Context context, int i, String s) {

    }

    // 消息透传
    @Override
    public void onTextMessage(Context context, XGPushTextMessage message) {

    }

    // 通知点击回调 actionType=1为该消息被清除，actionType=0为该消息被点击
    @Override
    public void onNotifactionClickedResult(Context context, XGPushClickedResult message) {

        if (message.getCustomContent() == null) {
            return;
        }

        if (message.getActionType() == XGPushClickedResult.NOTIFACTION_CLICKED_TYPE) {
            String customContent = message.getCustomContent();
            try {
                JSONObject obj = new JSONObject(customContent);
                // Log.i("myLog","obj "+obj);
                String mod = obj.getString("mod");
                String index = obj.getString("class");
                String itemid = obj.getString("itemid");
                String cid = obj.getString("cid");
                String year = obj.getString("year");
                String messageid = obj.getString("messageid");
                if (mod.equals("finance") && index.equals("3")) {
                    Intent intent = new Intent(context, PayInfoDetail.class);
                    intent.putExtra("itemid", itemid);
                    intent.putExtra("cid", cid);
                    intent.putExtra("year", year);
                    intent.putExtra("push", "push");
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    postData(messageid, context);
                    context.startActivity(intent);
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else if (message.getActionType() == XGPushClickedResult.NOTIFACTION_DELETED_TYPE) {

        }

    }

    // 通知展示
    @Override
    public void onNotifactionShowedResult(Context context, XGPushShowedResult message) {


    }

    private final String URL = "http://www.gzxlxx.com:8866/index.php/Home/App/noteread";

    private void postData(String messageid, final Context context) {
        OkHttpClient client = new OkHttpClient();
        RequestBody requestBody = new FormBody.Builder()
                .add("loginid", App.loginid)
                .add("compid", String.valueOf(App.compid))
                .add("messageid", messageid)
                .build();
        final Request request = new Request.Builder()
                .addHeader("cookie", App.cookie)
                .url(URL)
                .post(requestBody)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

            }
        });
    }


}
