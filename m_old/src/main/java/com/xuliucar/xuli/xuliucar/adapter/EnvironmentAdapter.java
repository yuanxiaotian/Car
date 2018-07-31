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
import com.xuliucar.xuli.xuliucar.bean.EnvironmentBean;
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
 * email
 */
public class EnvironmentAdapter extends BaseAdapter {
    private final Context context;
    private List<EnvironmentBean.DataBean.InfoBean> eProtectBeanList;
    private Map<Integer, Boolean> map;
    private OnSelectedItemChanged listener;
    private int isdisplay;
    private Dialog dialog;
    private List<String> phoeList;
    private List<String> idList;

    public EnvironmentAdapter(Context context, List<EnvironmentBean.DataBean.InfoBean> eProtectBeanList) {

        this.context = context;
        this.eProtectBeanList = eProtectBeanList;
        phoeList = new ArrayList<>();
        idList = new ArrayList<>();
    }

    public EnvironmentAdapter(Context context, List<EnvironmentBean.DataBean.InfoBean> eProtectBeanList, int isdisplay) {
        this.context = context;
        this.eProtectBeanList = eProtectBeanList;
        this.isdisplay = isdisplay;
        phoeList = new ArrayList<>();
        idList = new ArrayList<>();
    }

    public EnvironmentAdapter(Context context, List<EnvironmentBean.DataBean.InfoBean> eProtectBeanList, int isdisplay, OnSelectedItemChanged listener) {
        this.context = context;
        this.eProtectBeanList = eProtectBeanList;
        this.isdisplay = isdisplay;
        map = new HashMap<>();
        for (int i = 0; i < eProtectBeanList.size(); i++) {
            map.put(i, false);
        }
        this.listener = listener;
        phoeList = new ArrayList<>();
        idList = new ArrayList<>();
    }

    @Override
    public int getCount() {
        if (isdisplay == 0) {

            return eProtectBeanList.size();
        } else {
            return eProtectBeanList.size();
        }
    }

    @Override
    public Object getItem(int i) {
        if (isdisplay == 0) {

            return eProtectBeanList.get(i);
        } else {
            return eProtectBeanList.get(i);
        }
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        ViewHolder holder = ViewHolder.get(context, view, viewGroup, R.layout.eprotection_item, i);
        LinearLayout hb_msg_layout = holder.getView(R.id.hb_msg_layout);
        LinearLayout hb_phones_layout = holder.getView(R.id.hb_phones_layout);
        final CheckBox checkBox = holder.getView(R.id.hb_checkbox);
        if (isdisplay == 0) {
            final EnvironmentBean.DataBean.InfoBean eProtectBean = eProtectBeanList.get(i);
            ((TextView) holder.getView(R.id.hb_plates)).setText(eProtectBean.getPlates());
            ((TextView) holder.getView(R.id.hb_phone)).setText(eProtectBean.getRphone());
            ((TextView) holder.getView(R.id.hb_chketime)).setText(eProtectBean.getChketime());
            final String rphone = eProtectBean.getRphone();
            final String rphone2 = eProtectBean.getRphone2();
            checkBox.setVisibility(View.GONE);
            idList.add(eProtectBean.getSmstarid());
            hb_msg_layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (rphone.equals("") && rphone2.equals("")) {
                        ToastUtil.showShortToast(context, "暂无号码");
                    }else {
                        PhoneMessage.selectSendStyle(context, idList, "车主", "临期", StringConfig.ODT_EPM + StringConfig.COMPANYNAME, StringConfig.TYPE_EPM, rphone, rphone2);
                    }

                }
            });

            hb_phones_layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (rphone.equals("") && rphone2.equals("")) {
                        ToastUtil.showShortToast(context, "暂无号码");
                    } else if (!rphone.equals("") && rphone2.equals("")) {
                        PhoneMessage.CallPhone(context, rphone);
                    } else if (rphone.equals("") && !rphone2.equals("")) {
                        PhoneMessage.CallPhone(context, rphone2);

                    } else {
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
                                PhoneMessage.CallPhone(context, rphone);
                                dialog.dismiss();
                            }
                        });

                        car_owner_phone_layout2.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                PhoneMessage.CallPhone(context, rphone2);
                                dialog.dismiss();
                            }
                        });
                    }
                }
            });

        } else if (isdisplay == 1) {
            final EnvironmentBean.DataBean.InfoBean fullLoad = eProtectBeanList.get(i);
            ((TextView) holder.getView(R.id.hb_plates)).setText(fullLoad.getPlates());
            ((TextView) holder.getView(R.id.hb_phone)).setText(fullLoad.getRphone());
            ((TextView) holder.getView(R.id.hb_chketime)).setText(fullLoad.getChketime());
            final String phone = fullLoad.getRphone();
            final String phone2 = fullLoad.getRphone2();
            phoeList.add(phone);
            idList.add(fullLoad.getSmstarid());
            checkBox.setVisibility(View.VISIBLE);
            checkBox.setChecked(map.get(i));
            checkBox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (checkBox.isChecked()) {
                        map.put(i, true);
                        PreferencesUtils.putSharePre(context, "GroupMsg", "phone" + i, fullLoad.getRphone());
                    } else {
                        map.put(i, false);
                        PreferencesUtils.clearSharePre(context, "GroupMsg", "phone" + i);
                    }
                    listener.selectedItemChange(getSelectedCount(map));
                }
            });
            hb_msg_layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(phone.equals("null") || phone.equals("")){
                        ToastUtil.showShortToast(context,"暂无号码");
                    }else {
                        PhoneMessage.selectSendStyle(context, idList, "车主", "临期", StringConfig.ODT_EPM + StringConfig.COMPANYNAME, StringConfig.TYPE_EPM, phone, phone2);
                    }
                }
            });
            PhoneMessage.CallPhone(context, hb_phones_layout, phone);
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
     */
    public interface OnSelectedItemChanged {
        void selectedItemChange(int count);
    }

    public void selectAll() { // 全选

        for (int i = 0; i < eProtectBeanList.size(); i++) {
            EnvironmentBean.DataBean.InfoBean fullLoad = eProtectBeanList.get(i);
            map.put(i, true);
            PreferencesUtils.putSharePre(context,"GroupMsg","phone"+i, fullLoad.getRphone());
            PreferencesUtils.putSharePre(context,"GroupMsg","id"+i,fullLoad.getSmstarid());
        }
        notifyDataSetChanged();
    }

    public void disSelectAll() { // 全不选
        if (isdisplay != 0) {
            for (int i = 0; i < eProtectBeanList.size(); i++) {
                map.put(i, false);
            }
        }
        PreferencesUtils.clearSharePre(context, "GroupMsg");
        notifyDataSetChanged();
    }

    public void switchSelect() { // 反选
        for (int i = 0; i < eProtectBeanList.size(); i++) {
            boolean select = map.get(i);
            map.put(i, !select);
        }
        notifyDataSetChanged();
    }
}
