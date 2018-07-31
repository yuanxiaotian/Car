package com.xuliucar.xuli.xuliucar.adapter;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xuliucar.xuli.xuliucar.R;
import com.xuliucar.xuli.xuliucar.bean.CommonBean;
import com.xuliucar.xuli.xuliucar.bean.FullLoad;
import com.xuliucar.xuli.xuliucar.utils.PhoneMessage;
import com.xuliucar.xuli.xuliucar.utils.PreferencesUtils;
import com.xuliucar.xuli.xuliucar.utils.ToastUtil;
import com.xuliucar.xuli.xuliucar.utils.ViewHolder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by skyward on 2016/7/9.
 *
 */
public class AnnualTAdapter extends BaseAdapter {
    private final Context context;
    private List<FullLoad> fullLoadList;
    private List<CommonBean> commonBeanList;
    private Map<Integer, Boolean> map;
    private OnSelectedItemChanged listener;
    private int isdisplay;
    private Dialog dialog;

    public AnnualTAdapter(Context context, List<CommonBean> commonBeanList) {
        this.context = context;
        this.commonBeanList = commonBeanList;
    }
    public AnnualTAdapter(Context context, List<CommonBean> commonBeanList, int isdisplay){
        this.context = context;
        this.commonBeanList = commonBeanList;
        this.isdisplay = isdisplay;


    }
    public AnnualTAdapter(Context context, List<FullLoad> fullLoadList, int isdisplay, OnSelectedItemChanged listener){
        this.context = context;
        this.fullLoadList = fullLoadList;
        this.isdisplay = isdisplay;
        map = new HashMap<>();
        for (int i = 0; i < fullLoadList.size(); i++) {
            map.put(i, false);
        }
        this.listener = listener;
    }

    @Override
    public int getCount() {
        if(isdisplay == 0){

            return commonBeanList.size();
        }else {
            return fullLoadList.size();
        }
    }

    @Override
    public Object getItem(int i) {
        if(isdisplay == 0){

            return commonBeanList.get(i);
        }else {
            return fullLoadList.get(i);
        }
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        ViewHolder holder = ViewHolder.get(context,view,viewGroup, R.layout.limited_item,i);
        ((TextView)holder.getView(R.id.ns_chktime_title)).setText("到期日期");
        LinearLayout ns_msg_layout = holder.getView(R.id.ns_msg_layout);
        LinearLayout ns_phones_layout = holder.getView(R.id.ns_phones_layout);
        final CheckBox checkBox = holder.getView(R.id.ns_checkbox);

        if(isdisplay == 0){
            final CommonBean commonBean = commonBeanList.get(i);
            ((TextView)holder.getView(R.id.ns_plates)).setText(commonBean.getStr2());
            ((TextView)holder.getView(R.id.ns_phone)).setText(commonBean.getStr3());
            ((TextView)holder.getView(R.id.ns_chktime)).setText(commonBean.getStr5());
            final String rphone = commonBean.getStr3();
            final String rphone2 = commonBean.getStr4();
            checkBox.setVisibility(View.GONE);

            ns_msg_layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(rphone.equals("") && rphone2.equals("")){
                        ToastUtil.showShortToast(context,"暂无号码");
                    } else if(!rphone.equals("") && rphone2.equals("")){
                        PhoneMessage.SendMeg(context,rphone,"尊敬的车主，您好。您的年票即将到期,请尽快与车队联系,谢谢。");
                    }else if(rphone.equals("") && !rphone2.equals("")){
                        PhoneMessage.SendMeg(context,rphone2,"尊敬的车主，您好。您的年票即将到期,请尽快与车队联系,谢谢。");
                    }else {
                        LayoutInflater inflater = LayoutInflater.from(context);
                        final ViewGroup nullParent = null;
                        View getView = inflater.inflate(R.layout.alert_owner_message, nullParent);
                        AlertDialog.Builder builder = new AlertDialog.Builder(context);
                        builder.setView(getView);
                        builder.setCancelable(false);
                        dialog = builder.show();
                        dialog.setCanceledOnTouchOutside(true);//触摸到对话框以外自动关闭对话框
                        RelativeLayout car_owner_msg_layout = (RelativeLayout) getView.findViewById(R.id.car_owner_msg_layout);
                        TextView car_owner_phone_msg = (TextView) getView.findViewById(R.id.car_owner_phone_msg);
                        RelativeLayout car_owner_msg_layout2 = (RelativeLayout) getView.findViewById(R.id.car_owner_msg_layout2);
                        TextView car_owner_phone_msg2 = (TextView) getView.findViewById(R.id.car_owner_phone_msg2);
                        car_owner_phone_msg.setText(rphone);
                        car_owner_phone_msg2.setText(rphone2);
                        car_owner_msg_layout.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Uri smsToUri = Uri.parse("smsto:" + rphone);
                                Intent it = new Intent(Intent.ACTION_SENDTO, smsToUri);
                                it.putExtra("sms_body", "尊敬的车主，您好。您的年票即将到期,请尽快与车队联系,谢谢。");
                                context.startActivity(it);
                                dialog.dismiss();
                            }
                        });

                        car_owner_msg_layout2.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Uri smsToUri = Uri.parse("smsto:" + rphone2);
                                Intent it = new Intent(Intent.ACTION_SENDTO, smsToUri);
                                it.putExtra("sms_body", "尊敬的车主，您好。您的年票即将到期,请尽快与车队联系,谢谢。");
                                context.startActivity(it);
                                dialog.dismiss();
                            }
                        });


                    }
                }
            });

            ns_phones_layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(rphone.equals("") && rphone2.equals("")){
                        ToastUtil.showShortToast(context,"暂无号码");
                    } else if(!rphone.equals("") && rphone2.equals("")){
                        PhoneMessage.CallPhone(context,rphone);
                    }else if(rphone.equals("") && !rphone2.equals("")){
                        PhoneMessage.CallPhone(context,rphone2);

                    }else {
                        LayoutInflater inflater = LayoutInflater.from(context);
                        final ViewGroup nullParent = null;
                        View getView = inflater.inflate(R.layout.alert_owner_phone, nullParent);
                        AlertDialog.Builder builder = new AlertDialog.Builder(context);
                        builder.setView(getView);
                        builder.setCancelable(false);
                        dialog = builder.show();
                        dialog.setCanceledOnTouchOutside(true);//触摸到对话框以外自动关闭对话框
                        RelativeLayout car_owner_phone_layout = (RelativeLayout) getView.findViewById(R.id.car_owner_phone_layout);
                        RelativeLayout car_owner_phone_layout2 = (RelativeLayout) getView.findViewById(R.id.car_owner_phone_layout2);
                        TextView car_owner_phone = (TextView) getView.findViewById(R.id.car_owner_phone);
                        TextView car_owner_phone2 = (TextView) getView.findViewById(R.id.car_owner_phone2);
                        car_owner_phone.setText(rphone);
                        car_owner_phone2.setText(rphone2);

                        car_owner_phone_layout.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                PhoneMessage.CallPhone(context,rphone);
                                dialog.dismiss();
                            }
                        });

                        car_owner_phone_layout2.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                PhoneMessage.CallPhone(context,rphone2);
                                dialog.dismiss();
                            }
                        });
                    }
                }
            });
        }else if(isdisplay == 1){
            final FullLoad fullLoad = fullLoadList.get(i);
            ((TextView)holder.getView(R.id.ns_plates)).setText(fullLoad.getArg2());
            ((TextView)holder.getView(R.id.ns_phone)).setText(fullLoad.getArg3());
            ((TextView)holder.getView(R.id.ns_chktime)).setText(fullLoad.getArg5());
            String   rphone = fullLoad.getArg3();

            checkBox.setVisibility(View.VISIBLE);
            checkBox.setChecked(map.get(i));
            checkBox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(checkBox.isChecked()){
                        map.put(i,true);
                        // Log.i("myLog","checkbox " + "选中");
                        PreferencesUtils.putSharePre(context,"GruopMsg","phone"+i, fullLoad.getArg3());
                    }else{
                        map.put(i,false);
                        // Log.i("myLog","checkbox " + "没选中");
                        PreferencesUtils.clearSharePre(context,"GruopMsg","phone"+i);
                    }
                    listener.selectedItemChange(getSelectedCount(map));
                }
            });
            PhoneMessage.SendMeg(context,ns_msg_layout,rphone,"尊敬的车主，您好。您的年票即将到期,请尽快与车队联系,谢谢。");
            PhoneMessage.CallPhone(context,ns_phones_layout,rphone);
        }



        return holder.getmConcertView();
    }

    /**
     * 获取选择的项的数目
     *
     */
    private int getSelectedCount(Map<Integer, Boolean> map) {
        int i = 0;
        for (Map.Entry<Integer, Boolean> entry : map.entrySet()) {
            if (entry.getValue()) {
                i++;
            }

        }
        return i;
    }



    /**
     * 向Activity暴露选择了多少项
     *
     * @author cj
     *
     */
    public interface OnSelectedItemChanged {
       void selectedItemChange(int count);
    }

    public void selectAll() { // 全选

        for (int i = 0; i < fullLoadList.size(); i++) {
            FullLoad fullLoad = fullLoadList.get(i);
            map.put(i, true);
            PreferencesUtils.putSharePre(context,"GruopMsg","phone"+i,fullLoad.getArg3());
        }
        notifyDataSetChanged();
    }

    public void disSelectAll() { // 全不选
        if(isdisplay == 0){
            for (int i = 0; i < 999; i++) {
                //map.put(i, false);
            }
        }else {
            for (int i = 0; i < fullLoadList.size(); i++) {
                map.put(i, false);
            }
        }
        PreferencesUtils.clearSharePre(context,"GruopMsg");
        notifyDataSetChanged();
    }

    public void switchSelect() { // 反选
        for (int i = 0; i < fullLoadList.size(); i++) {
            boolean select = map.get(i);
            map.put(i, !select);
        }
        notifyDataSetChanged();
    }
}
