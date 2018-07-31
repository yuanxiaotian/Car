package com.xuliucar.xuli.xuliucar.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xuliucar.xuli.xuliucar.R;
import com.xuliucar.xuli.xuliucar.base.App;
import com.xuliucar.xuli.xuliucar.bean.HistorySendBean;
import com.xuliucar.xuli.xuliucar.config.AppPower;
import com.xuliucar.xuli.xuliucar.utils.LogUtil;
import com.xuliucar.xuli.xuliucar.utils.ToastUtil;
import com.xuliucar.xuli.xuliucar.utils.ViewHolder;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by skyward on 2016/12/7.
 * email：
 */

public class HistorySendAdapter extends CommonAdapter<HistorySendBean.DataBean> {
    private sendAgainCallBack sendAgainCallBack;
    public HistorySendAdapter(Context context, List<HistorySendBean.DataBean> historySendBeanList,sendAgainCallBack callBack){
        super(context,historySendBeanList);
        this.sendAgainCallBack = callBack;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder = ViewHolder.get(mContext,view,viewGroup, R.layout.history_send_item,i);
        final HistorySendBean.DataBean bean = mDatas.get(i);
        ((TextView)holder.getView(R.id.send_message_type)).setText(bean.getBtype());
        ((TextView)holder.getView(R.id.send_target_phone)).setText(bean.getPhone());
        ((TextView)holder.getView(R.id.msg_num)).setText(bean.getCount());
        ((TextView)holder.getView(R.id.send_time)).setText(bean.getSendtime());
        TextView send_status = holder.getView(R.id.send_status);
        send_status.setText(bean.getStatus());
        if(bean.getStatus().equals("已送达")){
            send_status.setTextColor(Color.parseColor("#3FA056"));
        }else if(bean.getStatus().equals("发送失败") || bean.getStatus().equals("接收失败")){
            send_status.setTextColor(Color.parseColor("#FF4040"));
        }else if(bean.getStatus().equals("已发送")){
            send_status.setTextColor(Color.parseColor("#6495ED"));
        }


        RelativeLayout historySend_item_content = holder.getView(R.id.historySend_item_content);
        historySend_item_content.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(mContext);
                builder.setMessage(bean.getContent());
                builder.create().show();
            }
        });

        LinearLayout send_again = holder.getView(R.id.send_again);
        send_again.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               SendAgain(bean.getSmsid());
            }
        });

        return holder.getmConcertView();
    }

    private void SendAgain(final String smsid) {

        if(AppPower.app_pow28.equals("0")){
            ToastUtil.showShortToast(mContext,"当前用户没有权限发送短信");
        }else {
            android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(mContext);
            builder.setMessage("确认再次发送吗?");
            builder.setTitle("温馨提醒");
            builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    sureSend(smsid);
                }
            });
            builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                }
            });
            builder.create().show();
        }
    }

    private void sureSend(String smsid) {
        LogUtil.LogPrint("smsid "+smsid);
        OkHttpClient client = new OkHttpClient();
        RequestBody requestBody = new FormBody.Builder()
                .add("loginid",App.loginid)
                .add("compid", String.valueOf(App.compid))
                .add("smsid",smsid)
                .build();
        Request request = new Request.Builder()
                .addHeader("cookie", App.cookie)
                .url("http://www.gzxlxx.com:8866/index.php/Home/App/smsresend")
                .post(requestBody)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {


            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String t = response.body().string();
                sendAgainCallBack.getResult(t);

            }
        });
    }

    public interface sendAgainCallBack{
        void getResult(String s);
    }
}
