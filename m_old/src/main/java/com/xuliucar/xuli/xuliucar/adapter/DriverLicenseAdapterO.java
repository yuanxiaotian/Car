package com.xuliucar.xuli.xuliucar.adapter;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xuliucar.xuli.xuliucar.R;
import com.xuliucar.xuli.xuliucar.bean.DriverLicenseBean;
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
public class DriverLicenseAdapterO extends BaseAdapter {
    private final Context context;
    private List<DriverLicenseBean.DataBean.InfoBean> commonBeanList;
    private Map<Integer, Boolean> map;
    private OnSelectedItemChanged listener;
    private int isdisplay;
    private Dialog dialog;
    private List<String> phoeList;
    private List<String> idList;

    public DriverLicenseAdapterO(Context context, List<DriverLicenseBean.DataBean.InfoBean> commonBeanList) {
        this.context = context;
        this.commonBeanList = commonBeanList;
        phoeList = new ArrayList<>();
        idList = new ArrayList<>();
    }

    public DriverLicenseAdapterO(Context context, List<DriverLicenseBean.DataBean.InfoBean> commonBeanList, int isdisplay) {
        this.context = context;
        this.commonBeanList = commonBeanList;
        this.isdisplay = isdisplay;
        phoeList = new ArrayList<>();
        idList = new ArrayList<>();
    }

    public DriverLicenseAdapterO(Context context, List<DriverLicenseBean.DataBean.InfoBean> commonBeanList, int isdisplay, OnSelectedItemChanged listener) {
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
            return commonBeanList.size();
        }
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        ViewHolder holder = ViewHolder.get(context, view, viewGroup, R.layout.do_dlicense_item, i);
        LinearLayout dod_msg_layout = holder.getView(R.id.dod_msg_layout);
        LinearLayout dod_phones_layout = holder.getView(R.id.dod_phones_layout);
        final CheckBox checkBox = holder.getView(R.id.ns_checkbox);
        if (isdisplay == 0) {
            final DriverLicenseBean.DataBean.InfoBean commonBean = commonBeanList.get(i);
            ((TextView) holder.getView(R.id.dod_drivername)).setText(commonBean.getDrivername());
            ((TextView) holder.getView(R.id.dod_sex)).setText(commonBean.getSex());
            ((TextView) holder.getView(R.id.dod_licnum)).setText(commonBean.getLicnum());
            ((TextView) holder.getView(R.id.dod_endtime)).setText(commonBean.getEndtime());
            final String rphone = commonBean.getRphone();
            final String rphone2 = commonBean.getRphone2();
            idList.add(commonBean.getSmstarid());
            //发短信
            dod_msg_layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (rphone.equals("") && rphone2.equals("")) {
                        ToastUtil.showShortToast(context, "暂无号码！");
                    }else {
                        PhoneMessage.selectSendStyle(context, idList, "司机", "过期", StringConfig.OD_DRIVERLIC + StringConfig.COMPANYNAME, StringConfig.TYPE_DRIVERLIC, rphone, rphone2);
                    }
                }
            });


            dod_phones_layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (rphone.equals("") && rphone2.equals("")) {
                        ToastUtil.showShortToast(context, "暂无号码！");
                    } else if (!rphone.equals("") && rphone2.equals("")) {
                        PhoneMessage.CallPhone(context, rphone);
                    } else if (rphone.equals("") && !rphone2.equals("")) {
                        PhoneMessage.CallPhone(context, rphone);
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


            checkBox.setVisibility(View.GONE);

        } else if (isdisplay == 1) {
            final DriverLicenseBean.DataBean.InfoBean fullLoad = commonBeanList.get(i);
            ((TextView) holder.getView(R.id.dod_drivername)).setText(fullLoad.getDrivername());
            ((TextView) holder.getView(R.id.dod_sex)).setText(fullLoad.getSex());
            ((TextView) holder.getView(R.id.dod_licnum)).setText(fullLoad.getLicnum());
            ((TextView) holder.getView(R.id.dod_endtime)).setText(fullLoad.getEndtime());
            final String phone = fullLoad.getRphone();
            final  String phone2 = fullLoad.getRphone2();
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
            dod_msg_layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(phone.equals("null") || phone.equals("")){
                        ToastUtil.showShortToast(context,"暂无号码");
                    }else {
                        PhoneMessage.selectSendStyle(context, idList, "司机", "过期", StringConfig.OD_DRIVERLIC + StringConfig.COMPANYNAME, StringConfig.TYPE_DRIVERLIC, phone, phone2);
                    }
                }
            });

            PhoneMessage.CallPhone(context, dod_phones_layout, phone);
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
            DriverLicenseBean.DataBean.InfoBean fullLoad = commonBeanList.get(i);
            map.put(i, true);
            PreferencesUtils.putSharePre(context,"GroupMsg","phone"+i, fullLoad.getRphone());
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
