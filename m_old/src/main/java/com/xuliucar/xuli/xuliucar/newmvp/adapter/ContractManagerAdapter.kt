package com.xuliucar.xuli.xuliucar.newmvp.adapter

import android.widget.TextView
import com.cangmaomao.recyclerview.adapter.CMMAdapter
import com.cangmaomao.recyclerview.adapter.CMMViewHolder
import com.xuliucar.xuli.xuliucar.R
import com.xuliucar.xuli.xuliucar.bean.ContractManageBean
import com.xuliucar.xuli.xuliucar.bean.OrderManageBean

class ContractManagerAdapter(layoutId: Int) : CMMAdapter<ContractManageBean.DataBean>(layoutId) {

    override fun convert(cmmViewHolder: CMMViewHolder, dataBean: ContractManageBean.DataBean, p2: Int) {
        cmmViewHolder.setText(R.id.c_confirmtime_text, dataBean.getConfirmtime())
        cmmViewHolder.setText(R.id.c_salername_text, dataBean.salername)
        cmmViewHolder.setText(R.id.c_status_text, dataBean.status)
        cmmViewHolder.setText(R.id.c_cnum_text, dataBean.getCnum())
        cmmViewHolder.setText(R.id.c_clientname_text, dataBean.getClientname())
        cmmViewHolder.setText(R.id.c_dealtype_text, dataBean.dealtype)
        cmmViewHolder.setText(R.id.c_dealprice_text, dataBean.dealprice)

    }
}