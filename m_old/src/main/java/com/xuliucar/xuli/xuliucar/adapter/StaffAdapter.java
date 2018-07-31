package com.xuliucar.xuli.xuliucar.adapter;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SectionIndexer;
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
public class StaffAdapter extends CommonAdapter<ContactsBean> implements SectionIndexer {

    private final int[] imageId = {R.drawable.juxing, R.drawable.juxing_16, R.drawable.juxing_55, R.drawable.juxing_95};
    private Dialog dialog;
    public StaffAdapter(Context context, List<ContactsBean> contactsBeanList) {
        super(context, contactsBeanList);
    }

    /**
     * 当ListView数据发生变化时,调用此方法来更新ListView
     */
//    public void updateListView(List<ContactsBean> contactsBeanList){
//        this.contactsBeanList = contactsBeanList;
//        notifyDataSetChanged();
//    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder = ViewHolder.get(mContext, view, viewGroup, R.layout.contacts_item, i);
        ContactsBean contactsBean = mDatas.get(i);
        final String phone = contactsBean.getPhone();
        ((TextView) holder.getView(R.id.contacts_name)).setText(contactsBean.getName());
        ImageView contacts_img = holder.getView(R.id.contacts_img);
        TextView tv_surname = holder.getView(R.id.surname);
        String name = contactsBean.getName();
        if (name.equals("") || name.isEmpty()) {
            tv_surname.setText(name);
        } else {
            String surname = name.substring(0, 1);
            tv_surname.setText(surname);
        }

        TextView catalog = holder.getView(R.id.catalog);
        if (i >= 4) {
            int jj = i % 4;
            contacts_img.setImageResource(imageId[jj]);
        } else {
            contacts_img.setImageResource(imageId[i]);
        }

        //根据position获取分类的首字母的Char ascii值
        int section = getSectionForPosition(i);

//        如果当前位置等于该分类首字母的Char的位置 ，则认为是第一次出现
        if (i == getPositionForSection(section)) {
            catalog.setVisibility(View.VISIBLE);
            catalog.setText(contactsBean.getSortLetters());
        } else {
            catalog.setVisibility(View.GONE);
        }

        LinearLayout contacts_msg_layout = holder.getView(R.id.contacts_msg_layout);
        LinearLayout contacts_phones_layout = holder.getView(R.id.contacts_phones_layout);

        contacts_msg_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(phone.equals("")){
                    ToastUtil.showShortToast(mContext,"暂无号码！");
                }else {
                    PhoneMessage.selectSendStyle(mContext, phone);
                }
            }
        });

        contacts_phones_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(phone.equals("")){
                    ToastUtil.showShortToast(mContext,"暂无号码！");
                }else {
                    PhoneMessage.CallPhone(mContext, phone);
                }
            }
        });


        return holder.getmConcertView();
    }


    @Override
    public Object[] getSections() {
        return new Object[0];
    }

    @Override
    public int getPositionForSection(int i) {
        for (int j = 0; j < getCount(); j++) {
            String sortStr = mDatas.get(j).getSortLetters();
            char firstChar = sortStr.toUpperCase().charAt(0);
            if (firstChar == i) {
                return j;
            }
        }

        return -1;
    }

    @Override
    public int getSectionForPosition(int i) {
        return mDatas.get(i).getSortLetters().charAt(0);
    }

    /**
     * 提取英文的首字母，非英文字母用#代替。
     *
     */
    private String getAlpha(String str) {
        String sortStr = str.trim().substring(0, 1).toUpperCase();
        // 正则表达式，判断首字母是否是英文字母
        if (sortStr.matches("[A-Z]")) {
            return sortStr;
        } else {
            return "#";
        }
    }
}
