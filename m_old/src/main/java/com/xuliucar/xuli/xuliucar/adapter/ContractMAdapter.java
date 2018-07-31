package com.xuliucar.xuli.xuliucar.adapter;

import android.widget.TextView;

import com.cangmaomao.recyclerview.adapter.CMMAdapter;
import com.cangmaomao.recyclerview.adapter.CMMViewHolder;
import com.xuliucar.xuli.xuliucar.R;
import com.xuliucar.xuli.xuliucar.bean.ContractManageBean;

/**
 * Created by skyward on 2016/7/7.
 *
 */
public class ContractMAdapter extends CMMAdapter<ContractManageBean.DataBean> {


    public ContractMAdapter(int layoutId) {
        super(layoutId);
    }


    @Override
    protected void convert(CMMViewHolder cmmViewHolder, ContractManageBean.DataBean dataBean, int i) {
        ((TextView)cmmViewHolder.getView(R.id.c_confirmtime_text)).setText(dataBean.getConfirmtime());
        ((TextView)cmmViewHolder.getView(R.id.c_salername_text)).setText(dataBean.getSalername());
        ((TextView)cmmViewHolder.getView(R.id.c_status_text)).setText(dataBean.getStatus());
        ((TextView)cmmViewHolder.getView(R.id.c_cnum_text)).setText(dataBean.getCnum());
        ((TextView)cmmViewHolder.getView(R.id.c_clientname_text)).setText(dataBean.getClientname());
        ((TextView)cmmViewHolder.getView(R.id.c_dealtype_text)).setText(dataBean.getDealtype());
        ((TextView)cmmViewHolder.getView(R.id.c_dealprice_text)).setText(dataBean.getDealprice());
    }
}
