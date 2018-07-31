package com.xuliucar.xuli.xuliucar.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xuliucar.xuli.xuliucar.R;
import com.xuliucar.xuli.xuliucar.bean.PriceRecordBean;
import com.xuliucar.xuli.xuliucar.utils.ViewHolder;

import java.util.List;

/**
 * Created by skyward on 2017/1/3.
 * email：
 */

public class PriceRecordAdapter extends CommonAdapter<PriceRecordBean> {
    public PriceRecordAdapter(Context context, List<PriceRecordBean> Datas) {
        super(context, Datas);
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder = ViewHolder.get(mContext,view,viewGroup, R.layout.price_record_item,i);
        PriceRecordBean bean = mDatas.get(i);
        ((TextView)holder.getView(R.id.car_plate)).setText(bean.getPlate());
        ((TextView)holder.getView(R.id.document_num)).setText(bean.getNum());
        ((TextView)holder.getView(R.id.pay_money)).setText(bean.getPayMoney());
        ((TextView) holder.getView(R.id.pay_status)).setText(bean.getStatus());
        ((TextView)holder.getView(R.id.pay_time)).setText(bean.getPayTime());
        return holder.getmConcertView();
    }
}
