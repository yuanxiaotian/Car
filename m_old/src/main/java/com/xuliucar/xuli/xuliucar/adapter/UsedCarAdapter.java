package com.xuliucar.xuli.xuliucar.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xuliucar.xuli.xuliucar.R;
import com.xuliucar.xuli.xuliucar.bean.StockCarBean;
import com.xuliucar.xuli.xuliucar.utils.ViewHolder;

import java.util.List;

/**
 * Created by skyward on 2016/7/4.
 *
 */
public class UsedCarAdapter extends CommonAdapter<StockCarBean.DataBean.InfoBean>{

    public UsedCarAdapter(Context context, List<StockCarBean.DataBean.InfoBean> stockCarBeanList){
        super(context,stockCarBeanList);
    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder = ViewHolder.get(mContext,view,viewGroup, R.layout.used_car_item,i);
        StockCarBean.DataBean.InfoBean stockCarBean = mDatas.get(i);
        String index = String.valueOf(i+1);
        ((TextView)holder.getView(R.id.usedCar_index)).setText(index);
        ((TextView)holder.getView(R.id.used_car_plate)).setText(stockCarBean.getPlates());
        ((TextView)holder.getView(R.id.used_car_fm)).setText(stockCarBean.getFnum());

        return holder.getmConcertView();
    }
}
