package com.xuliucar.xuli.xuliucar.dataHandlers;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;

import com.xuliucar.xuli.xuliucar.base.App;
import com.xuliucar.xuli.xuliucar.ui.Login;
import com.xuliucar.xuli.xuliucar.utils.PreferencesUtils;
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
 *
 */
public class GetDetailData extends DataWrapper {

    private static class  InnerGetDetailData{
        private static final GetDetailData instance = new GetDetailData();
    }

    private GetDetailData(){

    }

    public static GetDetailData getInstance(){
        return InnerGetDetailData.instance;
    }


    public void getDetailData(final Activity activity, String url, String cid, final Handler handler) {

        OkHttpClient client= new OkHttpClient();
        RequestBody requestBody = new FormBody.Builder()
                .add("cid", cid)
                .add("loginid", App.loginid)
                .add("compid", String.valueOf(App.compid))
                .build();
        Request request = new Request.Builder()
                .addHeader("cookie", App.cookie)
                .url(url)
                .post(requestBody)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        ToastUtil.showShortToast(activity.getApplication(),"网络连接异常！");
                    }
                });

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String t = response.body().string();
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            JSONObject object = new JSONObject(t);
                            String success = object.getString("success");
                            if(success.equals("true")){

                                Message msg = new Message();
                                msg.obj = t;
                                handler.sendMessage(msg);

                            }else if (success.equals("false")) {
                                String msg = object.getString("message");
                                if (msg.equals("already logout")) {
                                    ToastUtil.showShortToast(activity.getApplication(), msg);
                                    Intent intent = new Intent(activity.getApplication(), Login.class);
                                    activity.startActivity(intent);
                                } else if (msg.equals("未登陆")) {
                                    Intent intent = new Intent(activity.getApplication(), Login.class);
                                    activity.startActivity(intent);
                                }
                            }

                            String msg = object.getString("message");
                            if (msg.equals("已登出,或在其它设备上登陆!")) {
                                ToastUtil.showShortToast(activity.getApplication(), msg);
                                new Handler().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        Intent i = new Intent(activity.getApplication(), Login.class);
                                        PreferencesUtils.clearSharePre(activity.getApplication(),"userInfo","password");
                                        activity.startActivity(i);
                                    }
                                }, 1000);

                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });
    }
}
