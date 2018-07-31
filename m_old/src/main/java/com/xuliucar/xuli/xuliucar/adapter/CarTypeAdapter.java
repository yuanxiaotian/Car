package com.xuliucar.xuli.xuliucar.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xuliucar.xuli.xuliucar.R;
import com.xuliucar.xuli.xuliucar.utils.ViewHolder;

import java.util.List;

/**
 * Created by skyward on 2017/1/4.
 * email：
 */

public class CarTypeAdapter extends CommonAdapter<String> {

    public CarTypeAdapter(Context context, List<String> Datas) {
        super(context, Datas);
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder = ViewHolder.get(mContext,view,viewGroup, R.layout.alert_car_type_item,i);
        ((TextView)holder.getView(R.id.car_type_text)).setText(mDatas.get(i));
        return holder.getmConcertView();
    }
}
