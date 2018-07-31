package com.xuliucar.xuli.xuliucar.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xuliucar.xuli.xuliucar.R;
import com.xuliucar.xuli.xuliucar.bean.IllegalOrderBean;
import com.xuliucar.xuli.xuliucar.utils.ViewHolder;

import java.util.List;

/**
 * Created by skyward on 2017/1/3.
 * email：
 */

public class IllegalOrderAdapter extends CommonAdapter<IllegalOrderBean> {


    public IllegalOrderAdapter(Context context, List<IllegalOrderBean> Datas) {
        super(context, Datas);
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder = ViewHolder.get(mContext,view,viewGroup, R.layout.illegal_order_item,i);
        IllegalOrderBean bean = mDatas.get(i);
        ((TextView)holder.getView(R.id.illegal_order_plate)).setText(bean.getPlate());
        ((TextView)holder.getView(R.id.illegal_order_status)).setText(bean.getStauts());
        ((TextView)holder.getView(R.id.illegal_order_points)).setText(bean.getPoints());
        ((TextView)holder.getView(R.id.illegal_order_illegal_activity)).setText(bean.getIllegalAct());
        return holder.getmConcertView();
    }
}
