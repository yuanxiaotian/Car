package com.xuliucar.xuli.xuliucar.newmvp.adapter

import com.cangmaomao.recyclerview.adapter.CMMAdapter
import com.cangmaomao.recyclerview.adapter.CMMViewHolder
import com.xuliucar.xuli.xuliucar.R
import com.xuliucar.xuli.xuliucar.bean.AchieveManageBean

/**
 * Created by skyward on 2016/7/7.
 *
 */
class AchieveManageAdapter(layoutId: Int) : CMMAdapter<AchieveManageBean.DataBean>(layoutId) {


    override fun convert(cmmViewHolder: CMMViewHolder, dataBean: AchieveManageBean.DataBean, i: Int) {

        cmmViewHolder.setText(R.id.ac_name, dataBean.name)
        cmmViewHolder.setText(R.id.ac_phone, dataBean.phone)
        cmmViewHolder.setText(R.id.ac_ordersum, dataBean.ordersum)
        cmmViewHolder.setText(R.id.ac_suc, dataBean.suc)
        cmmViewHolder.setText(R.id.ac_ing, dataBean.ing)
        cmmViewHolder.setText(R.id.ac_goalsum, dataBean.goalsum)
        cmmViewHolder.setText(R.id.ac_dealsum, dataBean.dealsum.toString())

    }
}
