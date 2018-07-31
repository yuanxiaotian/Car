package com.xuliucar.xuli.xuliucar.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xuliucar.xuli.xuliucar.R;
import com.xuliucar.xuli.xuliucar.bean.InfoSumBean;
import com.xuliucar.xuli.xuliucar.utils.ViewHolder;

import java.util.List;

/**
 *
 * Created by skyward on 2016/7/1.
 */
public class InfoOverviewAdapter extends CommonAdapter<InfoSumBean.DataBean.InfoBean> {

    public InfoOverviewAdapter(Context context,List<InfoSumBean.DataBean.InfoBean> infoBeanList){
        super(context,infoBeanList);
    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder = ViewHolder.get(mContext,view,viewGroup, R.layout.info_sum_item,i);
        InfoSumBean.DataBean.InfoBean infoBean = mDatas.get(i);
        ((TextView)holder.getView(R.id.car_type)).setText(infoBean.getName());
        ((TextView)holder.getView(R.id.car_type_count)).setText(infoBean.getCount());
        return holder.getmConcertView();
    }
}
