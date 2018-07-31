package com.xuliucar.xuli.xuliucar.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;

import com.xuliucar.xuli.xuliucar.R;
import com.xuliucar.xuli.xuliucar.bean.OrderManageBean;
import com.xuliucar.xuli.xuliucar.utils.ViewHolder;

import java.util.List;

/**
 * Created by skyward on 2016/7/7.
 *
 */
public class OrderMAdapter extends CommonAdapter<OrderManageBean.DataBean> {

    public OrderMAdapter(Context context,List<OrderManageBean.DataBean> saleMBeanList){
        super(context,saleMBeanList);
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder = ViewHolder.get(mContext,view,viewGroup, R.layout.order_manage_item,i);
        OrderManageBean.DataBean saleMBean = mDatas.get(i);
        ((TextView)holder.getView(R.id.stime_text)).setText(saleMBean.getStime());
        ((TextView)holder.getView(R.id.dealtime_text)).setText(saleMBean.getDealtime());
        ((TextView)holder.getView(R.id.salername_text)).setText(saleMBean.getSalername());
        ((TextView)holder.getView(R.id.status_text)).setText(saleMBean.getStatus());
        ((TextView)holder.getView(R.id.ordernum_text)).setText(saleMBean.getOrdernum());
        ((TextView)holder.getView(R.id.client_text)).setText(saleMBean.getClient());
        ((TextView)holder.getView(R.id.dealtype_text)).setText(saleMBean.getDealtype());
        ((TextView)holder.getView(R.id.dealprice_text)).setText(saleMBean.getDealprice());
        return holder.getmConcertView();
    }
}
