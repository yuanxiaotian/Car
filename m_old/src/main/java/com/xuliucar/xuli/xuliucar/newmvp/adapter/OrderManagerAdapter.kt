package com.xuliucar.xuli.xuliucar.newmvp.adapter

import android.widget.TextView
import com.cangmaomao.recyclerview.adapter.CMMAdapter
import com.cangmaomao.recyclerview.adapter.CMMViewHolder
import com.xuliucar.xuli.xuliucar.R
import com.xuliucar.xuli.xuliucar.bean.OrderManageBean

class OrderManagerAdapter(layoutId: Int) : CMMAdapter<OrderManageBean.DataBean>(layoutId) {

    override fun convert(cmmViewHolder: CMMViewHolder, dataBean: OrderManageBean.DataBean, p2: Int) {
        cmmViewHolder.setText(R.id.stime_text, dataBean.getStime())
        cmmViewHolder.setText(R.id.dealtime_text, dataBean.getDealtime())
        cmmViewHolder.setText(R.id.salername_text, dataBean.getSalername())
        cmmViewHolder.setText(R.id.status_text, dataBean.getStatus())
        cmmViewHolder.setText(R.id.ordernum_text, dataBean.getOrdernum())
        cmmViewHolder.setText(R.id.client_text, dataBean.getClient())
        cmmViewHolder.setText(R.id.dealtype_text, dataBean.getDealtype())
        cmmViewHolder.setText(R.id.dealprice_text, dataBean.getDealprice())
    }
}