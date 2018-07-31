package com.xuliucar.xuli.xuliucar.adapter;


import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cangmaomao.recyclerview.adapter.CMMAdapter;
import com.cangmaomao.recyclerview.adapter.CMMViewHolder;
import com.xuliucar.xuli.xuliucar.R;
import com.xuliucar.xuli.xuliucar.bean.CarsListBean;

import com.xuliucar.xuli.xuliucar.utils.PhoneMessage;
import com.xuliucar.xuli.xuliucar.utils.ToastUtil;
import com.xuliucar.xuli.xuliucar.utils.ViewHolder;

import java.util.List;


/**
 * Created by skyward on 2016/6/30.
 */
public class CarListAdapter extends CMMAdapter<CarsListBean.DataBean.InfoBean> {

    private Dialog dialog;

    public CarListAdapter(int layoutId) {
        super(layoutId);
    }


    @Override
    protected void convert(CMMViewHolder cmmViewHolder, CarsListBean.DataBean.InfoBean infoBean, int i) {
        final String ownername = infoBean.getOwner();//车主姓名
        final String rphone = infoBean.getRphone();//车主号码1
        final String rphone2 = infoBean.getRphone2();//车主号码2
        final String driverName = infoBean.getDriver();//主司机姓名
        final String driverPhone = infoBean.getDrphone();//主司机号码1
        final String driverPhone2 = infoBean.getDrphone2();//主司机号码2
        final String driverName2 = infoBean.getDriver2();//副司机姓名
        final String d2rphone = infoBean.getD2rphone();//副司机号码1
        final String d2rphone2 = infoBean.getD2rphone2();//副司机号码2

        ((TextView) cmmViewHolder.getView(R.id.plates)).setText(infoBean.getPlates());
        TextView owner = cmmViewHolder.getView(R.id.owner);
        ((TextView) cmmViewHolder.getView(R.id.owner)).setText(infoBean.getOwner());
        ((ImageView) cmmViewHolder.getView(R.id.phones_img)).setImageResource(R.drawable.phones);
        ((ImageView) cmmViewHolder.getView(R.id.msg_img)).setImageResource(R.drawable.xiaoxis);

        if (ownername.equals("null")) {
            owner.setText("");
        } else {
            owner.setText(ownername);
        }


        final AppCompatImageView msg_img = cmmViewHolder.getView(R.id.msg_img);
        //发短信
        msg_img.setOnClickListener(view -> {
            if (rphone.equals("") && rphone2.equals("") && driverPhone.equals("") && driverPhone2.equals("") && d2rphone.equals("") && d2rphone2.equals("")) {

                ToastUtil.showShortToast(cmmViewHolder.mContext, "暂无号码");

            } else if ((!rphone.equals("")) && (rphone2.equals("")) && driverPhone.equals("") && driverPhone2.equals("") && d2rphone.equals("") && d2rphone2.equals("")) {
                //只有车主号码点击直接去到发短信界面
                PhoneMessage.selectSendStyle(cmmViewHolder.mContext, rphone);
            } else if (rphone.equals("") && (!rphone2.equals("")) && driverPhone.equals("") && driverPhone2.equals("") && d2rphone.equals("") && d2rphone2.equals("")) {
                PhoneMessage.selectSendStyle(cmmViewHolder.mContext, rphone2);
            } else if (rphone.equals("") && rphone2.equals("") && (!driverPhone.equals("")) && driverPhone2.equals("") && d2rphone.equals("") && d2rphone2.equals("")) {
                PhoneMessage.selectSendStyle(cmmViewHolder.mContext, driverPhone);
            } else if (rphone.equals("") && rphone2.equals("") && driverPhone.equals("") && (!driverPhone2.equals("")) && d2rphone.equals("") && d2rphone2.equals("")) {
                PhoneMessage.selectSendStyle(cmmViewHolder.mContext, driverPhone2);
            } else if (rphone.equals("") && rphone2.equals("") && driverPhone.equals("") && driverPhone2.equals("") && (!d2rphone.equals("")) && d2rphone2.equals("")) {
                PhoneMessage.selectSendStyle(cmmViewHolder.mContext, d2rphone);
            } else if (rphone.equals("") && rphone2.equals("") && driverPhone.equals("") && driverPhone2.equals("") && d2rphone.equals("") && (!d2rphone2.equals(""))) {
                PhoneMessage.selectSendStyle(cmmViewHolder.mContext, d2rphone2);
            } else {

                LayoutInflater inflater = LayoutInflater.from(cmmViewHolder.mContext);

                final ViewGroup nullParent = null;
                View getView = inflater.inflate(R.layout.alert_message, nullParent);
                AlertDialog.Builder builder = new AlertDialog.Builder(cmmViewHolder.mContext);
                builder.setView(getView);
                builder.setCancelable(false);
                dialog = builder.show();
                dialog.setCanceledOnTouchOutside(true);//触摸到对话框以外自动关闭对话框

                CardView car_owner_phone_layout = (CardView) getView.findViewById(R.id.car_owner_msg_layout);
                CardView car_owner_msg_layout2 = (CardView) getView.findViewById(R.id.car_owner_msg_layout2);
                CardView car_driver_msg_layout = (CardView) getView.findViewById(R.id.car_driver_msg_layout);
                CardView car_driver_msg_layout2 = (CardView) getView.findViewById(R.id.car_driver_msg_layout2);
                CardView car_driver_msg_layout_double = (CardView) getView.findViewById(R.id.car_driver_msg_layout_double);
                CardView car_driver_msg_layout2_double = (CardView) getView.findViewById(R.id.car_driver_msg_layout2_double);


                TextView car_owner_name = (TextView) getView.findViewById(R.id.car_owner_name_msg);
                TextView car_owner_phone = (TextView) getView.findViewById(R.id.car_owner_phone_msg);
                TextView car_owner_name2 = (TextView) getView.findViewById(R.id.car_owner_name_msg2);
                TextView car_owner_phone2 = (TextView) getView.findViewById(R.id.car_owner_phone_msg2);

                final TextView car_driver_name = (TextView) getView.findViewById(R.id.car_driver_name_msg);
                final TextView car_driver_phone = (TextView) getView.findViewById(R.id.car_driver_phone_msg);
                TextView car_driver_name2 = (TextView) getView.findViewById(R.id.car_driver_name_msg2);
                TextView car_driver_phone2 = (TextView) getView.findViewById(R.id.car_driver_phone_msg2);

                TextView car_driver_name_F = (TextView) getView.findViewById(R.id.car_driver_name_msg_double);
                TextView car_driver_phone_F = (TextView) getView.findViewById(R.id.car_driver_phone_msg_double);
                TextView car_driver_name2_F = (TextView) getView.findViewById(R.id.car_driver_name_msg2_double);
                TextView car_driver_phone_msg2_double = (TextView) getView.findViewById(R.id.car_driver_phone_msg2_double);

                if (rphone.equals("")) {
                    car_owner_phone_layout.setVisibility(View.GONE);
                } else {
                    car_owner_phone_layout.setVisibility(View.VISIBLE);
                    car_owner_name.setText(ownername);
                    car_owner_phone.setText(rphone);
                }
                if (rphone2.equals("")) {
                    car_owner_msg_layout2.setVisibility(View.GONE);
                } else {
                    car_owner_msg_layout2.setVisibility(View.VISIBLE);
                    car_owner_name2.setText(ownername);
                    car_owner_phone2.setText(rphone2);
                }

                if (driverPhone.equals("")) {
                    car_driver_msg_layout.setVisibility(View.GONE);
                } else {
                    car_driver_msg_layout.setVisibility(View.VISIBLE);
                    car_driver_name.setText(driverName);
                    car_driver_phone.setText(driverPhone);
                }

                if (driverPhone2.equals("")) {
                    car_driver_msg_layout2.setVisibility(View.GONE);
                } else {
                    car_driver_msg_layout2.setVisibility(View.VISIBLE);
                    car_driver_name2.setText(driverName);
                    car_driver_phone2.setText(driverPhone2);
                }

                if (d2rphone.equals("")) {
                    car_driver_msg_layout_double.setVisibility(View.GONE);
                } else {
                    car_driver_msg_layout_double.setVisibility(View.VISIBLE);
                    car_driver_name_F.setText(driverName2);
                    car_driver_phone_F.setText(d2rphone);
                }

                if (d2rphone2.equals("")) {
                    car_driver_msg_layout2_double.setVisibility(View.GONE);
                } else {
                    car_driver_msg_layout2_double.setVisibility(View.VISIBLE);
                    car_driver_name2_F.setText(driverName2);
                    car_driver_phone_msg2_double.setText(d2rphone2);
                }


                car_owner_phone_layout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        PhoneMessage.selectSendStyle(cmmViewHolder.mContext, rphone);
                        dialog.dismiss();
                    }
                });

                car_owner_msg_layout2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        PhoneMessage.selectSendStyle(cmmViewHolder.mContext, rphone2);
                        dialog.dismiss();
                    }
                });

                car_driver_msg_layout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        PhoneMessage.selectSendStyle(cmmViewHolder.mContext, driverPhone);
                        dialog.dismiss();
                    }
                });

                car_driver_msg_layout2.setOnClickListener(view17 -> {
                    PhoneMessage.selectSendStyle(cmmViewHolder.mContext, driverPhone2);
                    dialog.dismiss();
                });


                car_driver_msg_layout_double.setOnClickListener(view18 -> {
                    PhoneMessage.selectSendStyle(cmmViewHolder.mContext, d2rphone);
                    dialog.dismiss();
                });

                car_driver_msg_layout2_double.setOnClickListener(view19 -> {
                    PhoneMessage.selectSendStyle(cmmViewHolder.mContext, d2rphone2);
                    dialog.dismiss();
                });

            }
        });


        //  拨打电话
        AppCompatImageView phones_layout = cmmViewHolder.getView(R.id.phones_img);
        phones_layout.setOnClickListener(view -> {
            if (rphone.equals("") && rphone2.equals("") && driverPhone.equals("") && driverPhone2.equals("") && d2rphone.equals("") && d2rphone2.equals("")) {

                ToastUtil.showShortToast(cmmViewHolder.mContext, "暂无号码");

            } else if ((!rphone.equals("")) && (rphone2.equals("")) && driverPhone.equals("") && driverPhone2.equals("") && d2rphone.equals("") && d2rphone2.equals("")) {
                PhoneMessage.CallPhone(cmmViewHolder.mContext, rphone);

            } else if (rphone.equals("") && (!rphone2.equals("")) && driverPhone.equals("") && driverPhone2.equals("") && d2rphone.equals("") && d2rphone2.equals("")) {
                PhoneMessage.CallPhone(cmmViewHolder.mContext, rphone2);
            } else if (rphone.equals("") && rphone2.equals("") && (!driverPhone.equals("")) && driverPhone2.equals("") && d2rphone.equals("") && d2rphone2.equals("")) {
                PhoneMessage.CallPhone(cmmViewHolder.mContext, driverPhone);
            } else if (rphone.equals("") && rphone2.equals("") && driverPhone.equals("") && (!driverPhone2.equals("")) && d2rphone.equals("") && d2rphone2.equals("")) {

                PhoneMessage.CallPhone(cmmViewHolder.mContext, driverPhone2);
            } else if (rphone.equals("") && rphone2.equals("") && driverPhone.equals("") && driverPhone2.equals("") && (!d2rphone.equals("")) && d2rphone2.equals("")) {
                PhoneMessage.CallPhone(cmmViewHolder.mContext, d2rphone);
            } else if (rphone.equals("") && rphone2.equals("") && driverPhone.equals("") && driverPhone2.equals("") && d2rphone.equals("") && (!d2rphone2.equals(""))) {
                PhoneMessage.CallPhone(cmmViewHolder.mContext, d2rphone2);
            } else {

                LayoutInflater inflater = LayoutInflater.from(cmmViewHolder.mContext);
                final ViewGroup nullParent = null;
                View getView = inflater.inflate(R.layout.alert_phone, nullParent);
                AlertDialog.Builder builder = new AlertDialog.Builder(cmmViewHolder.mContext);
                builder.setView(getView);
                builder.setCancelable(false);
                dialog = builder.show();
                dialog.setCanceledOnTouchOutside(true);//触摸到对话框以外自动关闭对话框
                CardView car_owner_phone_layout = (CardView) getView.findViewById(R.id.car_owner_phone_layout);
                CardView car_owner_phone_layout2 = (CardView) getView.findViewById(R.id.car_owner_phone_layout2);
                CardView car_driver_msg_layout = (CardView) getView.findViewById(R.id.car_driver_phone_layout);
                CardView car_driver_phone_layout2 = (CardView) getView.findViewById(R.id.car_driver_phone_layout2);
                CardView car_driver_phone_layout_F = (CardView) getView.findViewById(R.id.car_driver_phone_layout_F);
                CardView car_driver_phone_layout2_F = (CardView) getView.findViewById(R.id.car_driver_phone_layout2_F);

                TextView car_owner_name = (TextView) getView.findViewById(R.id.car_owner_name);
                TextView car_owner_phone = (TextView) getView.findViewById(R.id.car_owner_phone);
                TextView car_owner_name2 = (TextView) getView.findViewById(R.id.car_owner_name2);
                TextView car_owner_phone2 = (TextView) getView.findViewById(R.id.car_owner_phone2);
                TextView car_driver_name = (TextView) getView.findViewById(R.id.car_driver_name);
                TextView car_driver_phone = (TextView) getView.findViewById(R.id.car_driver_phone);
                TextView car_driver_name2 = (TextView) getView.findViewById(R.id.car_driver_name2);
                TextView car_driver_phone2 = (TextView) getView.findViewById(R.id.car_driver_phone2);
                TextView car_driver_name_F = (TextView) getView.findViewById(R.id.car_driver_name_F);
                TextView car_driver_phone_F = (TextView) getView.findViewById(R.id.car_driver_phone_F);
                TextView car_driver_name2_F = (TextView) getView.findViewById(R.id.car_driver_name2_F);
                TextView car_driver_phone2_F = (TextView) getView.findViewById(R.id.car_driver_phone2_F);

                if (rphone.equals("")) {
                    car_owner_phone_layout.setVisibility(View.GONE);
                } else {
                    car_owner_phone_layout.setVisibility(View.VISIBLE);
                    car_owner_name.setText(ownername);
                    car_owner_phone.setText(rphone);
                }
                if (rphone2.equals("")) {
                    car_owner_phone_layout2.setVisibility(View.GONE);
                } else {
                    car_owner_phone_layout2.setVisibility(View.VISIBLE);
                    car_owner_name2.setText(ownername);
                    car_owner_phone2.setText(rphone2);
                }

                if (driverPhone.equals("")) {
                    car_driver_msg_layout.setVisibility(View.GONE);
                } else {
                    car_driver_msg_layout.setVisibility(View.VISIBLE);
                    car_driver_name.setText(driverName);
                    car_driver_phone.setText(driverPhone);
                }
                if (driverPhone2.equals("")) {
                    car_driver_phone_layout2.setVisibility(View.GONE);
                } else {
                    car_driver_phone_layout2.setVisibility(View.VISIBLE);
                    car_driver_name2.setText(driverName);
                    car_driver_phone2.setText(driverPhone2);
                }
                if (d2rphone.equals("")) {
                    car_driver_phone_layout_F.setVisibility(View.GONE);
                } else {
                    car_driver_phone_layout_F.setVisibility(View.VISIBLE);
                    car_driver_name_F.setText(driverName2);
                    car_driver_phone_F.setText(d2rphone);
                }
                if (d2rphone2.equals("")) {
                    car_driver_phone_layout2_F.setVisibility(View.GONE);
                } else {
                    car_driver_phone_layout2_F.setVisibility(View.VISIBLE);
                    car_driver_name2_F.setText(driverName2);
                    car_driver_phone2_F.setText(d2rphone2);
                }


                car_owner_phone_layout.setOnClickListener(view16 -> {
                    PhoneMessage.CallPhone(cmmViewHolder.mContext, rphone);
                    dialog.dismiss();
                });

                car_owner_phone_layout2.setOnClickListener(view15 -> {
                    PhoneMessage.CallPhone(cmmViewHolder.mContext, rphone2);
                    dialog.dismiss();
                });

                car_driver_msg_layout.setOnClickListener(view14 -> {
                    PhoneMessage.CallPhone(cmmViewHolder.mContext, driverPhone);
                    dialog.dismiss();

                });

                car_driver_phone_layout2.setOnClickListener(view13 -> {
                    PhoneMessage.CallPhone(cmmViewHolder.mContext, driverPhone2);
                    dialog.dismiss();
                });
                car_driver_phone_layout_F.setOnClickListener(view12 -> {
                    PhoneMessage.CallPhone(cmmViewHolder.mContext, d2rphone);
                    dialog.dismiss();
                });

                car_driver_phone_layout2_F.setOnClickListener(view1 -> {
                    PhoneMessage.CallPhone(cmmViewHolder.mContext, d2rphone2);
                    dialog.dismiss();
                });


            }
        });

    }


}
