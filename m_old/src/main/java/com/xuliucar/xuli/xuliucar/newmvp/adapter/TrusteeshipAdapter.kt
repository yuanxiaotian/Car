package com.xuliucar.xuli.xuliucar.newmvp.adapter

import android.view.View
import android.widget.CheckBox
import com.cangmaomao.recyclerview.adapter.CMMAdapter
import com.cangmaomao.recyclerview.adapter.CMMViewHolder
import com.xuliucar.xuli.xuliucar.R
import com.xuliucar.xuli.xuliucar.bean.PassPerPetaueBean

class TrusteeshipAdapter(layoutId: Int) : CMMAdapter<PassPerPetaueBean.DataBean.InfoBean>(layoutId) {

    override fun convert(holder: CMMViewHolder, dataBean: PassPerPetaueBean.DataBean.InfoBean, p2: Int) {
        holder.setText(R.id.season_plates, dataBean.plates)
        holder.setText(R.id.season_owner, dataBean.owner)
        holder.setText(R.id.season_stype, dataBean.stype)
        holder.setText(R.id.season_chktime, dataBean.chktime)

        val season_checkbox = holder.getView<CheckBox>(R.id.season_checkbox)
        if (dataBean.isShowAndHide) season_checkbox.visibility = View.VISIBLE else season_checkbox.visibility = View.GONE
        season_checkbox.isChecked = dataBean.isSelect

    }
}