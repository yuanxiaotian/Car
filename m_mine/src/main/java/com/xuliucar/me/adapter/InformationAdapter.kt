package com.xuliucar.me.adapter

import android.support.v7.widget.AppCompatImageView
import android.view.View
import com.cangmaomao.recyclerview.adapter.CMMAdapter
import com.cangmaomao.recyclerview.adapter.CMMViewHolder
import com.xuliucar.me.R
import com.xuliucar.me.model.Noteinfo

/**
 * Created by skyward on 2016/7/7.
 *
 */
class InformationAdapter(layoutId: Int) : CMMAdapter<Noteinfo.DataBean>(layoutId) {


    override fun convert(cmmViewHolder: CMMViewHolder, dataBean: Noteinfo.DataBean, i: Int) {
        cmmViewHolder.setText(R.id.info_center_content, dataBean.content)
        cmmViewHolder.setText(R.id.info_center_time, dataBean.timestamp)
        val dot = cmmViewHolder.getView<AppCompatImageView>(R.id.dot)
        if (dataBean.isread == "0") dot.visibility = View.VISIBLE else dot.visibility = View.GONE
    }
}
