package com.xuliucar.xuli.xuliucar.newmvp.adapter

import com.cangmaomao.recyclerview.adapter.CMMAdapter
import com.cangmaomao.recyclerview.adapter.CMMViewHolder
import com.xuliucar.xuli.xuliucar.R
import com.xuliucar.xuli.xuliucar.newmvp.bean.OrderDetails

class IntentModelAdapter(layoutId: Int) : CMMAdapter<OrderDetails.DataBean.InfoBean>(layoutId) {


    override fun convert(cmmViewHolder: CMMViewHolder, infoBean: OrderDetails.DataBean.InfoBean, i: Int) {
        cmmViewHolder.setText(R.id.intent_model_title, infoBean.model)
    }
}
