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
import com.xuliucar.xuli.xuliucar.bean.DoPerpetuaeBean;
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
 * Created by skyward on 2016/7/8.
 *
 */
public class PerpetuaeAdapter extends BaseAdapter {
    private final Context context;
    private Map<Integer, Boolean> map;
    private OnSelectedItemChanged listener;
    private int isdisplay;
    private List<DoPerpetuaeBean.DataBean.InfoBean> commonBeanList;
    private Dialog dialog;
    private List<String> phoeList;
    private List<String> idList;

    public PerpetuaeAdapter(Context context,List<DoPerpetuaeBean.DataBean.InfoBean> commonBeanList){
        this.context = context;
        this.commonBeanList = commonBeanList;
        phoeList = new ArrayList<>();
        idList = new ArrayList<>();
    }

    public PerpetuaeAdapter(Context context, List<DoPerpetuaeBean.DataBean.InfoBean> commonBeanList, int isdisplay) {
        this.context = context;
        this.commonBeanList = commonBeanList;
        this.isdisplay = isdisplay;
        phoeList = new ArrayList<>();
        idList = new ArrayList<>();
    }

    public PerpetuaeAdapter(Context context, List<DoPerpetuaeBean.DataBean.InfoBean> commonBeanList, int isdisplay, OnSelectedItemChanged listener) {
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
        if (isdisplay == 0) {

            return commonBeanList.size();
        } else {
            return commonBeanList.size();
        }
    }

    @Override
    public Object getItem(int i) {
        if (isdisplay == 0) {

            return commonBeanList.get(i);
        } else {
            return commonBeanList.get(i);
        }
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        ViewHolder holder = ViewHolder.get(context, view, viewGroup, R.layout.perpetuae_item, i);
        TextView season_plates = holder.getView(R.id.season_plates);
        TextView season_owner = holder.getView(R.id.season_owner);
        TextView season_stype = holder.getView(R.id.season_stype);
        TextView season_chktime = holder.getView(R.id.season_chktime);
        final CheckBox checkBoxs = holder.getView(R.id.season_checkbox);
        LinearLayout season_msg_layout = holder.getView(0);
        LinearLayout season_phones_layout = holder.getView(0);
        if (isdisplay == 0) {
            final DoPerpetuaeBean.DataBean.InfoBean commentBean = commonBeanList.get(i);
            season_plates.setText(commentBean.getPlates());
            season_owner.setText(commentBean.getOwner());
            season_stype.setText(commentBean.getStype());
            season_chktime.setText(commentBean.getChktime());
            final String rphone = commentBean.getRphone();
            final String rphone2 = commentBean.getRphone2();
            checkBoxs.setVisibility(View.GONE);
            idList.add(commentBean.getSmstarid());
            season_msg_layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (rphone.equals("") && rphone2.equals("")) {
                        ToastUtil.showShortToast(context, "暂无号码");
                    }else {
                        PhoneMessage.selectSendStyle(context, idList, "车主", "临期", StringConfig.ODT_SEASONCHK + StringConfig.COMPANYNAME, StringConfig.TYPE_SEASONCHK, rphone, rphone2);
                    }
                }
            });

            season_phones_layout.setOnClickListener(new View.OnClickListener() {
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
            final DoPerpetuaeBean.DataBean.InfoBean fullLoad = commonBeanList.get(i);
            season_plates.setText(fullLoad.getPlates());
            season_owner.setText(fullLoad.getOwner());
            season_stype.setText(fullLoad.getStype());
            season_chktime.setText(fullLoad.getChktime());
            final String phone = fullLoad.getRphone();
            final String phone2 = fullLoad.getRphone2();
            phoeList.add(phone);
            idList.add(fullLoad.getSmstarid());
            checkBoxs.setVisibility(View.VISIBLE);
            checkBoxs.setChecked(map.get(i));
            checkBoxs.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (checkBoxs.isChecked()) {
                        map.put(i, true);
                        PreferencesUtils.putSharePre(context,"GroupMsg","phone"+i, fullLoad.getRphone());
                        PreferencesUtils.putSharePre(context,"GroupMsg","id"+i,fullLoad.getSmstarid());
                    } else {
                        map.put(i, false);
                        PreferencesUtils.clearSharePre(context,"GroupMsg","phone"+i);
                        PreferencesUtils.clearSharePre(context,"GroupMsg","id"+i);
                    }
                    listener.selectedItemChange(getSelectedCount(map));
                }
            });
            season_msg_layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(phone.equals("null") || phone.equals("")){
                        ToastUtil.showShortToast(context,"暂无号码");
                    }else {
                        PhoneMessage.selectSendStyle(context, idList, "车主", "临期", StringConfig.ODT_SEASONCHK + StringConfig.COMPANYNAME, StringConfig.TYPE_SEASONCHK, phone, phone2);
                    }
                }
            });
            PhoneMessage.CallPhone(context, season_phones_layout, phone);

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

        for (int i = 0; i < commonBeanList.size(); i++) {
            DoPerpetuaeBean.DataBean.InfoBean fullLoad = commonBeanList.get(i);
            map.put(i, true);
            PreferencesUtils.putSharePre(context, "GroupMsg", "phone" + i, fullLoad.getRphone());
            PreferencesUtils.putSharePre(context,"GroupMsg","id"+i,fullLoad.getSmstarid());
        }
        notifyDataSetChanged();
    }

    public void disSelectAll() { // 全不选
        if (isdisplay != 0) {
            for (int i = 0; i < commonBeanList.size(); i++) {
                map.put(i, false);
            }
        }
        PreferencesUtils.clearSharePre(context, "GroupMsg");
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
