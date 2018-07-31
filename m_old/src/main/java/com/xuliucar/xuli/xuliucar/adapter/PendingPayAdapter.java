package com.xuliucar.xuli.xuliucar.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cangmaomao.recyclerview.adapter.CMMAdapter;
import com.cangmaomao.recyclerview.adapter.CMMViewHolder;
import com.xuliucar.xuli.xuliucar.R;
import com.xuliucar.xuli.xuliucar.bean.PendingPayBean;
import com.xuliucar.xuli.xuliucar.utils.ViewHolder;

import java.util.List;

/**
 * Created by skyward on 2016/7/5.
 */
public class PendingPayAdapter extends CMMAdapter<PendingPayBean.DataBean.InfoBean> {


    public PendingPayAdapter(int layoutId) {
        super(layoutId);
    }

    @Override
    protected void convert(CMMViewHolder cmmViewHolder, PendingPayBean.DataBean.InfoBean infoBean, int i) {
        ((TextView) cmmViewHolder.getView(R.id.citemname)).setText(infoBean.getItemname());
        ((TextView) cmmViewHolder.getView(R.id.cplates)).setText(infoBean.getPlates());
        ((TextView) cmmViewHolder.getView(R.id.cleft)).setText(infoBean.getLeft());
    }
}