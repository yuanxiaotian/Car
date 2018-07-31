package com.xuliucar.xuli.xuliucar.adapter;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
public class StaffSearch extends BaseAdapter {
    private final Context context;
    private Dialog dialog;
    private List<ContactsBean> contactsBeanList = null;
    private final int [] imageId ={R.drawable.juxing,R.drawable.juxing_16,R.drawable.juxing_55,R.drawable.juxing_95};
    public StaffSearch(Context context, List<ContactsBean> contactsBeanList) {
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


        contacts_msg_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (phone.equals("")) {
                    ToastUtil.showShortToast(context, "暂无号码！");
                } else {
                    PhoneMessage.SendMeg(context, phone, "");
                }
            }
        });

        contacts_phones_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(phone.equals("")){
                    ToastUtil.showShortToast(context,"暂无号码！");
                }else {
                    PhoneMessage.CallPhone(context, phone);

                }
            }
        });
        return holder.getmConcertView();
    }



}
