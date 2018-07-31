package com.xuliucar.xuli.xuliucar.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xuliucar.xuli.xuliucar.R;
import com.xuliucar.xuli.xuliucar.bean.OutComeBean;
import com.xuliucar.xuli.xuliucar.utils.ViewHolder;

import java.util.List;

/**
 * Created by skyward on 2016/7/4.
 *
 */
public class OutComeAdapter extends CommonAdapter<OutComeBean.DataBean> {

    public OutComeAdapter(Context context, List<OutComeBean.DataBean> outComeBeen){
       super(context,outComeBeen);
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder = ViewHolder.get(mContext,view,viewGroup, R.layout.out_come_detail_item,i);
        OutComeBean.DataBean dataBean = mDatas.get(i);
        ((TextView)holder.getView(R.id.outCome_item_name)).setText(dataBean.getItem());
        ((TextView)holder.getView(R.id.outCome_operator)).setText(dataBean.getOperator());
        ((TextView)holder.getView(R.id.outCome_rtime)).setText(dataBean.getPtime());
        ((TextView)holder.getView(R.id.outComeMoney)).setText(dataBean.getSum());
        return holder.getmConcertView();
    }
}
