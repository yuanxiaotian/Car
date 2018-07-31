package com.xuliucar.xuli.xuliucar.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cangmaomao.recyclerview.adapter.CMMAdapter;
import com.cangmaomao.recyclerview.adapter.CMMViewHolder;
import com.xuliucar.xuli.xuliucar.R;

import com.xuliucar.xuli.xuliucar.bean.PassPayBean;
import com.xuliucar.xuli.xuliucar.utils.ViewHolder;

import java.util.List;

/**
 * Created by skyward on 2016/11/29.
 * email：
 */

public class PassPayAdapter extends CMMAdapter<PassPayBean.DataBean> {


    public PassPayAdapter(int layoutId) {
        super(layoutId);
    }

    @Override
    protected void convert(CMMViewHolder cmmViewHolder, PassPayBean.DataBean dataBean, int i) {
        ((TextView) cmmViewHolder.getView(R.id.citemname)).setText(dataBean.getItemname());
        ((TextView) cmmViewHolder.getView(R.id.cplates)).setText(dataBean.getPlates());
        ((TextView) cmmViewHolder.getView(R.id.cleft)).setText(dataBean.getLeft());
    }
}

