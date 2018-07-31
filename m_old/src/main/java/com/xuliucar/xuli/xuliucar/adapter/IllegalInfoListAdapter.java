package com.xuliucar.xuli.xuliucar.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xuliucar.xuli.xuliucar.R;
import com.xuliucar.xuli.xuliucar.bean.IllegalInfoListBean;
import com.xuliucar.xuli.xuliucar.utils.ViewHolder;

import java.util.List;

/**
 * Created by skyward on 2017/1/3.
 * email：
 */

public class IllegalInfoListAdapter extends CommonAdapter<IllegalInfoListBean> {

    public IllegalInfoListAdapter(Context context, List<IllegalInfoListBean> Datas) {
        super(context, Datas);
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder = ViewHolder.get(mContext,view,viewGroup, R.layout.illegal_info_list_item,i);
        IllegalInfoListBean bean = mDatas.get(i);
        ((TextView)holder.getView(R.id.car_plate)).setText(bean.getOne());
        ((TextView)holder.getView(R.id.the_num_of_cases)).setText(bean.getTwo());
        ((TextView)holder.getView(R.id.total_penalty)).setText(bean.getThree());
        ((TextView)holder.getView(R.id.total_fines)).setText(bean.getFour());
        return holder.getmConcertView();
    }
}
