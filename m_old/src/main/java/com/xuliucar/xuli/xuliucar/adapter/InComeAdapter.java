package com.xuliucar.xuli.xuliucar.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xuliucar.xuli.xuliucar.R;
import com.xuliucar.xuli.xuliucar.bean.InComeBean;
import com.xuliucar.xuli.xuliucar.utils.ViewHolder;

import java.util.List;

/**
 * Created by skyward on 2016/7/4.
 *
 */
public class InComeAdapter extends CommonAdapter<InComeBean.DataBean> {

    public InComeAdapter(Context context,List<InComeBean.DataBean> dataBeanList){
        super(context,dataBeanList);
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder = ViewHolder.get(mContext,view,viewGroup, R.layout.in_come_detail_item,i);
        InComeBean.DataBean dataBean = mDatas.get(i);
        ((TextView)holder.getView(R.id.inCome_item_name)).setText(dataBean.getItem());
        ((TextView)holder.getView(R.id.operator)).setText(dataBean.getOperator());
        ((TextView)holder.getView(R.id.inCome_rtime)).setText(dataBean.getRtime());
        ((TextView)holder.getView(R.id.inComeMoney)).setText(dataBean.getSum());
        return holder.getmConcertView();
    }
}
