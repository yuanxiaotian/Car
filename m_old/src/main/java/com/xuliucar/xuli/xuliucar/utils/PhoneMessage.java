package com.xuliucar.xuli.xuliucar.utils;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.util.EventLog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cangmaomao.lib.action.FragmentActionKt;
import com.cangmaomao.lib.config.AppPower;
import com.cangmaomao.lib.event.AppEvent;
import com.xuliucar.xuli.xuliucar.R;
import com.xuliucar.xuli.xuliucar.newmvp.view.messageManage.MessageManageIndex;

import org.greenrobot.eventbus.EventBus;

import java.util.List;


/**
 * Created by skyward on 2016/7/29.
 * 启动打电话发短信
 */
public class PhoneMessage {

    //选择发短信的方式 -->这个是发自定义号码的
    public static void selectSendStyle(final Context context, final String phone) {
        final Dialog mDialog;
        LayoutInflater inflater = LayoutInflater.from(context);
        final ViewGroup nullParent = null;
        View view = inflater.inflate(R.layout.select_send_style, nullParent);
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setView(view);
        builder.setCancelable(false);
        mDialog = builder.show();
        mDialog.setCanceledOnTouchOutside(true);
        RelativeLayout send_by_system = view.findViewById(R.id.send_by_system);
        RelativeLayout send_by_phone = view.findViewById(R.id.send_by_phone);
        if (AppPower.app_pow28.equals("0")) {
            send_by_system.setVisibility(View.GONE);
        } else {
            send_by_system.setVisibility(View.VISIBLE);
        }
        //使用系统发送
        send_by_system.setOnClickListener((View v) -> {
            Bundle bundle = new Bundle();
            bundle.putString("phoneNum", phone + "|");
            EventBus.getDefault().post(new AppEvent(FragmentActionKt.getF_messageManageIndex(), bundle));
            mDialog.dismiss();
        });
        //使用手机发送
        send_by_phone.setOnClickListener(v -> {
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_SENDTO);
            intent.setData(Uri.parse("smsto:" + phone));
            intent.putExtra("sms_body", "");
            context.startActivity(intent);
            mDialog.dismiss();
        });
    }

    //选择发短信的方式 -->这个是发车主或者司机的临期或者过期短信
    public static void selectSendStyle(final Context context, final List<String> idList, final String targetType, final String sendtype, final String modelMsg, final String ptple, final String phone, final String phone2) {
        final Dialog mDialog;

        final LayoutInflater inflater = LayoutInflater.from(context);
        final ViewGroup nullParent = null;
        View view = inflater.inflate(R.layout.select_send_style, nullParent);
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setView(view);
        builder.setCancelable(false);
        mDialog = builder.show();
        mDialog.setCanceledOnTouchOutside(true);
        RelativeLayout send_by_system = (RelativeLayout) view.findViewById(R.id.send_by_system);
        RelativeLayout send_by_phone = (RelativeLayout) view.findViewById(R.id.send_by_phone);
        if (AppPower.app_pow28.equals("0")) {
            send_by_system.setVisibility(View.GONE);
        } else {
            send_by_system.setVisibility(View.VISIBLE);
        }
        //使用系统发送
        send_by_system.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MessageManageIndex.class);
                StringBuilder target = new StringBuilder();
                if (targetType.equals("车主")) {
                    for (int i = 0; i < idList.size(); i++) {
                        if (!TextUtils.equals(idList.get(i), "o_0")) {
                            target.append(idList.get(i)).append("|");
                        }
                    }
                } else if (targetType.equals("司机")) {
                    for (int i = 0; i < idList.size(); i++) {
                        target.append(idList.get(i)).append("|");
                    }
                }
                intent.putExtra("target", target.toString());//发送目标
                intent.putExtra("targetType", targetType);//发送目标的类型，比如车主还是司机
                intent.putExtra("sendtype", sendtype);//发送类型，是临期还是过期
                intent.putExtra("ptple", ptple);
                context.startActivity(intent);
                LogUtil.LogPrint("target " + target + " targetType: " + targetType + " sendtype " + sendtype + " ptple " + ptple);
                mDialog.dismiss();
            }
        });

        //使用手机发送
        send_by_phone.setOnClickListener(new View.OnClickListener() {
            Dialog mDialog2;

            @Override
            public void onClick(View v) {


                if (!phone.equals("") && phone2.equals("")) {
                    sendmsg(context, phone, modelMsg);
                } else if (phone.equals("") && !phone2.equals("")) {
                    sendmsg(context, phone2, modelMsg);
                } else {
                    LayoutInflater inflater = LayoutInflater.from(context);
                    final ViewGroup nullParent = null;
                    View getView = inflater.inflate(R.layout.alert_owner_message, nullParent);
                    android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(context);
                    builder.setView(getView);
                    builder.setCancelable(false);
                    mDialog2 = builder.show();
                    mDialog2.setCanceledOnTouchOutside(true);//触摸到对话框以外自动关闭对话框
                    RelativeLayout car_owner_msg_layout = (RelativeLayout) getView.findViewById(R.id.car_owner_msg_layout);
                    TextView car_owner_phone_msg = (TextView) getView.findViewById(R.id.car_owner_phone_msg);
                    RelativeLayout car_owner_msg_layout2 = (RelativeLayout) getView.findViewById(R.id.car_owner_msg_layout2);
                    TextView car_owner_phone_msg2 = (TextView) getView.findViewById(R.id.car_owner_phone_msg2);

                    car_owner_phone_msg.setText(phone);
                    car_owner_phone_msg2.setText(phone2);

                    car_owner_msg_layout.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Uri smsToUri = Uri.parse("smsto:" + phone);
                            Intent it = new Intent(Intent.ACTION_SENDTO, smsToUri);
                            it.putExtra("sms_body", modelMsg);
                            context.startActivity(it);
                            mDialog2.dismiss();

                        }
                    });

                    car_owner_msg_layout2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Uri smsToUri = Uri.parse("smsto:" + phone2);
                            Intent it = new Intent(Intent.ACTION_SENDTO, smsToUri);
                            it.putExtra("sms_body", modelMsg);
                            context.startActivity(it);
                            mDialog2.dismiss();

                        }
                    });
                }
                mDialog.dismiss();
            }
        });
    }


    public static void sendmsg(Context context, String phone, String modelMsg) {
        Uri smsToUri = Uri.parse("smsto:" + phone);
        Intent it = new Intent(Intent.ACTION_SENDTO, smsToUri);
        it.putExtra("sms_body", modelMsg);
        context.startActivity(it);
    }


    //选择发短信的方式批量 -->这个是发车主或者司机的临期或者过期短信
    public static void selectSendStyle(final Context context, final List<String> phoneList, final List<String> idList, final String targetType, final String sendtype, final String modelMsg, final String ptple) {
        final Dialog mDialog;
        final LayoutInflater inflater = LayoutInflater.from(context);
        final ViewGroup nullParent = null;
        View view = inflater.inflate(R.layout.select_send_style, nullParent);
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setView(view);
        builder.setCancelable(false);
        mDialog = builder.show();
        mDialog.setCanceledOnTouchOutside(true);
        RelativeLayout send_by_system = (RelativeLayout) view.findViewById(R.id.send_by_system);
        RelativeLayout send_by_phone = (RelativeLayout) view.findViewById(R.id.send_by_phone);
        //使用系统发送
        send_by_system.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MessageManageIndex.class);
                StringBuilder target = new StringBuilder();
                if (targetType.equals("车主")) {
                    for (int i = 0; i < idList.size(); i++) {
                        if (!TextUtils.equals(idList.get(i), "o_0")) {
                            target.append(idList.get(i)).append("|");
                        }
                    }
                } else if (targetType.equals("司机")) {
                    for (int i = 0; i < idList.size(); i++) {
                        target.append(idList.get(i)).append("|");
                    }
                }
                intent.putExtra("target", target.toString());//发送目标
                intent.putExtra("targetType", targetType);//发送目标的类型，比如车主还是司机
                intent.putExtra("sendtype", sendtype);//发送类型，是临期还是过期
                intent.putExtra("ptple", ptple);
                context.startActivity(intent);
                LogUtil.LogPrint("target " + target + " targetType: " + targetType + " sendtype " + sendtype + " ptple " + ptple);
                mDialog.dismiss();
            }
        });

        //使用手机发送
        send_by_phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StringBuilder phone = new StringBuilder();
                for (int i = 0; i < phoneList.size(); i++) {
                    phone.append(phoneList.get(i)).append(",");
                }
                String groupPhones = phone.substring(0, phone.length() - 1);
                Uri smsToUri = Uri.parse("smsto:" + groupPhones);
                Intent it = new Intent(Intent.ACTION_SENDTO, smsToUri);
                it.putExtra("sms_body", modelMsg);
                context.startActivity(it);
                mDialog.dismiss();
            }
        });
    }

    public static void SendMeg(Context context, String phone, String msgContent) {
        Uri smsToUri = Uri.parse("smsto:" + phone);
        Intent it = new Intent(Intent.ACTION_SENDTO, smsToUri);
        it.putExtra("sms_body", msgContent);
        context.startActivity(it);
    }

    public static void CallPhone(Context context, final String phone) {
        Uri uri = Uri.parse("tel:" + phone);
        Intent it = new Intent();
        it.setData(uri);
        it.setAction(Intent.ACTION_CALL);
        context.startActivity(it);
    }


    //有车主和司机的号码就弹出两个号码来选择
    public static void SendMeg(final Context context, View views, final String phone, final String msgContent) {
        views.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (phone.equals("null") || phone.equals("")) {
                    ToastUtil.showShortToast(context, "暂无号码");
                } else {
                    String companyName = PreferencesUtils.getSharePreStr(view.getContext(), "userInfo", "companyName");
                    Uri smsToUri = Uri.parse("smsto:" + phone);
                    Intent it = new Intent(Intent.ACTION_SENDTO, smsToUri);
                    it.putExtra("sms_body", msgContent + companyName);
                    context.startActivity(it);
                }

            }
        });
    }

    public static void CallPhone(final Context context, View views, final String phone) {
        views.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (phone.equals("null") || phone.equals("")) {
                    ToastUtil.showShortToast(context, "暂无号码");
                } else {
                    Uri uri = Uri.parse("tel:" + phone);
                    Intent intent = new Intent(Intent.ACTION_DIAL, uri);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                }
            }
        });
    }
}
