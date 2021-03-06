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
import com.xuliucar.xuli.xuliucar.bean.InSuranceBean;
import com.xuliucar.xuli.xuliucar.config.StringConfig;
import com.xuliucar.xuli.xuliucar.utils.PhoneMessage;
import com.xuliucar.xuli.xuliucar.utils.PreferencesUtils;
import com.xuliucar.xuli.xuliucar.utils.ToastUtil;
import com.xuliucar.xuli.xuliucar.utils.ViewHolder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by skyward on 2016/7/9.
 *
 */
public class InsuranceAdapter extends BaseAdapter {
    private final Context context;
    private List<InSuranceBean.DataBean.InfoBean> commonBeanList;
    private Map<Integer, Boolean> map;
    private OnSelectedItemChanged listener;
    private int isdisplay;
    private Dialog dialog;
    private List<String> phoeList;
    private List<String> idList;
    public InsuranceAdapter(Context context, List<InSuranceBean.DataBean.InfoBean> commonBeanList) {
        this.context = context;
        this.commonBeanList = commonBeanList;
        phoeList = new ArrayList<>();
        idList = new ArrayList<>();
    }
    public InsuranceAdapter(Context context, List<InSuranceBean.DataBean.InfoBean> commonBeanList, int isdisplay){
        this.context = context;
        this.commonBeanList = commonBeanList;
        this.isdisplay = isdisplay;
        phoeList = new ArrayList<>();
        idList = new ArrayList<>();

    }
    public InsuranceAdapter(Context context, List<InSuranceBean.DataBean.InfoBean> commonBeanList, int isdisplay, OnSelectedItemChanged listener){
        this.context = context;
        this.commonBeanList = commonBeanList;
        this.isdisplay = isdisplay;
        map = new HashMap<>();
        for (int i = 0; i < commonBeanList.size(); i++) {
            map.put(i, false);
        }
        this.listener = listener;
        phoeList = new ArrayList<>();
        idList = new ArrayList<>();
    }
    @Override
    public int getCount() {
        if(isdisplay == 0){
            return commonBeanList.size();
        }else {
            return commonBeanList.size();
        }
    }

    @Override
    public Object getItem(int i) {
        if(isdisplay == 0){
            return commonBeanList.get(i);

        }else {
            return commonBeanList.get(i);
        }
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        ViewHolder holder = ViewHolder.get(context,view,viewGroup, R.layout.do_insurance_item,i);
        LinearLayout bx_msg_layout = holder.getView(R.id.bx_msg_layout);
        LinearLayout bx_phones_layout = holder.getView(R.id.bx_phones_layout);
        final CheckBox bx_checkbox = holder.getView(R.id.bx_checkbox);
        if(isdisplay == 0){
            final InSuranceBean.DataBean.InfoBean commonBean = commonBeanList.get(i);
            ((TextView)holder.getView(R.id.bx_plates)).setText(commonBean.getPlates());
            ((TextView)holder.getView(R.id.bx_rphone)).setText(commonBean.getRphone());
            ((TextView)holder.getView(R.id.bx_stype)).setText(commonBean.getType());
            ((TextView)holder.getView(R.id.bx_enddate)).setText(commonBean.getEnddate());
            final String type = commonBean.getType();
            bx_checkbox.setVisibility(View.GONE);
            final String  rphone = commonBean.getRphone();
            final String rphone2 = commonBean.getRphone2();
            idList.add(commonBean.getSmstarid());
            bx_msg_layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(rphone.equals("") && rphone2.equals("")){
                        ToastUtil.showShortToast(context,"暂无号码！");
                    }else {
                        PhoneMessage.selectSendStyle(context, idList, "车主", "临期", "尊敬的车主，您好。您的"+type+"即将到期,请尽快与车队联系,谢谢。"+ StringConfig.COMPANYNAME, StringConfig.TYPE_INSURE, rphone, rphone2);
                    }
                }
            });

            bx_phones_layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(rphone.equals("") && rphone2.equals("")){
                        ToastUtil.showShortToast(context,"暂无号码！");
                    }else if(!rphone.equals("") && rphone2.equals("")){
                        PhoneMessage.CallPhone(context,rphone);
                    }else if(rphone.equals("") && !rphone2.equals("")){
                        PhoneMessage.CallPhone(context,rphone);
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
            final InSuranceBean.DataBean.InfoBean fullLoad = commonBeanList.get(i);
            ((TextView)holder.getView(R.id.bx_plates)).setText(fullLoad.getPlates());
            ((TextView)holder.getView(R.id.bx_rphone)).setText(fullLoad.getRphone());
            ((TextView)holder.getView(R.id.bx_stype)).setText(fullLoad.getType());
            ((TextView)holder.getView(R.id.bx_enddate)).setText(fullLoad.getEnddate());
            final String  phone = fullLoad.getRphone();
            final String phone2 = fullLoad.getRphone2();
            final String type = fullLoad.getType();
            phoeList.add(phone);
            idList.add(fullLoad.getSmstarid());
            bx_checkbox.setVisibility(View.VISIBLE);
            bx_checkbox.setChecked(map.get(i));
            bx_checkbox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(bx_checkbox.isChecked()){
                        map.put(i,true);
                        PreferencesUtils.putSharePre(context,"GroupMsg","phone"+i, fullLoad.getRphone());
                    }else{
                        map.put(i,false);
                        PreferencesUtils.clearSharePre(context,"GroupMsg","phone"+i);
                    }
                    listener.selectedItemChange(getSelectedCount(map));
                }
            });
            bx_msg_layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(phone.equals("null") || phone.equals("")){
                        ToastUtil.showShortToast(context,"暂无号码");
                    }else {
                        PhoneMessage.selectSendStyle(context, idList, "车主", "临期", "尊敬的车主，您好。您的"+type+"即将到期,请尽快与车队联系,谢谢。"+ StringConfig.COMPANYNAME, StringConfig.TYPE_INSURE, phone, phone2);
                    }
                }
            });
            PhoneMessage.CallPhone(context,bx_phones_layout,phone);
        }
        return holder.getmConcertView();
    }

    /**
     * 获取选择的项的数目
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

        for (int i = 0; i < commonBeanList.size(); i++) {
            InSuranceBean.DataBean.InfoBean fullLoad = commonBeanList.get(i);
            map.put(i, true);
            PreferencesUtils.putSharePre(context,"GroupMsg","phone"+i, fullLoad.getRphone());
            PreferencesUtils.putSharePre(context,"GroupMsg","id"+i,fullLoad.getSmstarid());
        }
        notifyDataSetChanged();
    }

    public void disSelectAll() { // 全不选
        if(isdisplay != 0){
            for (int i = 0; i < commonBeanList.size(); i++) {
                map.put(i, false);
            }
        }
        PreferencesUtils.clearSharePre(context,"GroupMsg");
        notifyDataSetChanged();
    }

    public void switchSelect() { // 反选
        for (int i = 0; i < commonBeanList.size(); i++) {
            boolean select = map.get(i);
            map.put(i, !select);
        }
        notifyDataSetChanged();
    }
}
