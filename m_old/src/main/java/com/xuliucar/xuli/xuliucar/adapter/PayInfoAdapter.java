package com.xuliucar.xuli.xuliucar.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;

import com.xuliucar.xuli.xuliucar.R;
import com.xuliucar.xuli.xuliucar.bean.PayInfoBean;
import com.xuliucar.xuli.xuliucar.utils.ViewHolder;

import java.util.List;

/**
 * Created by skyward on 2016/7/4.
 *
 */
public class PayInfoAdapter extends CommonAdapter<PayInfoBean.DataBean> {

    public PayInfoAdapter(Context context,List<PayInfoBean.DataBean> payInfoBeanList){
       super(context,payInfoBeanList);
    }


    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder = ViewHolder.get(mContext,view,viewGroup, R.layout.pay_info_item,i);
        PayInfoBean.DataBean payInfoBean = mDatas.get(i);
        ((TextView)holder.getView(R.id.powners)).setText(payInfoBean.getOwner());
        ((TextView)holder.getView(R.id.pphones)).setText(payInfoBean.getPhone());
        ((TextView)holder.getView(R.id.pCharge)).setText(payInfoBean.getCharge());
        ((TextView)holder.getView(R.id.papd)).setText(payInfoBean.getApd());
        ((TextView)holder.getView(R.id.pleft)).setText(payInfoBean.getLeft());
        ((TextView)holder.getView(R.id.pdeleft)).setText(payInfoBean.getDeleft());
        return holder.getmConcertView();
    }
}
