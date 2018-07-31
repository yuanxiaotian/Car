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
import android.widget.ImageView;
import android.widget.LinearLayout;

import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xuliucar.xuli.xuliucar.R;
import com.xuliucar.xuli.xuliucar.bean.ContactsBean;
import com.xuliucar.xuli.xuliucar.utils.PhoneMessage;
import com.xuliucar.xuli.xuliucar.utils.ToastUtil;
import com.xuliucar.xuli.xuliucar.utils.ViewHolder;

import java.util.List;

/**
 * Created by skyward on 2016/7/16.
 *
 */
public class ContactsSearch extends BaseAdapter {
    private final Context context;
    private Dialog dialog;
    private List<ContactsBean> contactsBeanList = null;
    private final int [] imageId ={R.drawable.juxing,R.drawable.juxing_16,R.drawable.juxing_55,R.drawable.juxing_95};
    public ContactsSearch(Context context, List<ContactsBean> contactsBeanList) {
        this.context = context;
        this.contactsBeanList = contactsBeanList;
    }

    @Override
    public int getCount() {
        return contactsBeanList.size();
    }

    @Override
    public Object getItem(int i) {
        return contactsBeanList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, final View view, ViewGroup viewGroup) {
        final ViewHolder holder = ViewHolder.get(context,view,viewGroup, R.layout.contacts_item,i);
        ContactsBean contactsBean = contactsBeanList.get(i);
        ((TextView)holder.getView(R.id.contacts_name)).setText(contactsBean.getName());
        ImageView contacts_img = holder.getView(R.id.contacts_img);
        TextView tv_surname = holder.getView(R.id.surname);
        String name = contactsBean.getName();
        if(!name.isEmpty()){
            String surname = name.substring(0,1);
            tv_surname.setText(surname);
        }else{
            tv_surname.setText("");
        }


        TextView catalog = holder.getView(R.id.catalog);
        catalog.setVisibility(View.GONE);
            if(i>=4){
                int jj = i % 4;
                contacts_img.setImageResource(imageId[jj]);
            }else{
                contacts_img.setImageResource(imageId[i]);
            }


        LinearLayout contacts_msg_layout = holder.getView(R.id.contacts_msg_layout);
        LinearLayout contacts_phones_layout = holder.getView(R.id.contacts_phones_layout);
        final String phone = contactsBean.getPhone();
        final String phone2 = contactsBean.getPhone2();

        contacts_msg_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (phone.equals("") && phone2.equals("")) {
                    ToastUtil.showShortToast(context, "暂无号码！");
                } else if (!phone.equals("") && phone2.equals("")) {
                    PhoneMessage.SendMeg(context, phone, "");
                } else if (phone.equals("") && !phone2.equals("")) {
                    PhoneMessage.SendMeg(context, phone2, "");
                } else {
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
                    car_owner_phone_msg.setText(phone);
                    car_owner_phone_msg2.setText(phone2);
                    car_owner_msg_layout.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Uri smsToUri = Uri.parse("smsto:" + phone);
                            Intent it = new Intent(Intent.ACTION_SENDTO, smsToUri);
                            it.putExtra("sms_body", "");
                            context.startActivity(it);
                            dialog.dismiss();
                        }
                    });

                    car_owner_msg_layout2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Uri smsToUri = Uri.parse("smsto:" + phone2);
                            Intent it = new Intent(Intent.ACTION_SENDTO, smsToUri);
                            it.putExtra("sms_body", "");
                            context.startActivity(it);
                            dialog.dismiss();
                        }
                    });

                }
            }
        });

        contacts_phones_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(phone.equals("") && phone2.equals("")){
                    ToastUtil.showShortToast(context,"暂无号码！");
                }else if(!phone.equals("") && phone2.equals("")){
                    PhoneMessage.CallPhone(context, phone);
                }else if(phone.equals("") && !phone2.equals("")){
                    PhoneMessage.CallPhone(context, phone);
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
                    car_owner_phone.setText(phone);
                    car_owner_phone2.setText(phone2);

                    car_owner_phone_layout.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            PhoneMessage.CallPhone(context, phone);
                            dialog.dismiss();
                        }
                    });

                    car_owner_phone_layout2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            PhoneMessage.CallPhone(context, phone2);
                            dialog.dismiss();
                        }
                    });
                }
            }
        });
        return holder.getmConcertView();
    }



}
