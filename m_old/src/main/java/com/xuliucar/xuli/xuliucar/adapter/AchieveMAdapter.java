package com.xuliucar.xuli.xuliucar.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cangmaomao.recyclerview.adapter.CMMAdapter;
import com.cangmaomao.recyclerview.adapter.CMMViewHolder;
import com.xuliucar.xuli.xuliucar.R;
import com.xuliucar.xuli.xuliucar.bean.AchieveManageBean;
import com.xuliucar.xuli.xuliucar.utils.ViewHolder;

import java.util.List;

/**
 * Created by skyward on 2016/7/7.
 *
 */
public class AchieveMAdapter extends CMMAdapter<AchieveManageBean.DataBean> {


    public AchieveMAdapter(int layoutId) {
        super(layoutId);
    }


    @Override
    protected void convert(CMMViewHolder cmmViewHolder, AchieveManageBean.DataBean dataBean, int i) {
        ((TextView)cmmViewHolder.getView(R.id.ac_name)).setText(dataBean.getName());
        ((TextView)cmmViewHolder.getView(R.id.ac_phone)).setText(dataBean.getPhone());
        ((TextView)cmmViewHolder.getView(R.id.ac_ordersum)).setText(dataBean.getOrdersum());
        ((TextView)cmmViewHolder.getView(R.id.ac_suc)).setText(dataBean.getSuc());
        ((TextView)cmmViewHolder.getView(R.id.ac_ing)).setText(dataBean.getIng());
        ((TextView)cmmViewHolder.getView(R.id.ac_goalsum)).setText(dataBean.getGoalsum());
        ((TextView)cmmViewHolder.getView(R.id.ac_dealsum)).setText(String.valueOf(dataBean.getDealsum()));
    }
}
