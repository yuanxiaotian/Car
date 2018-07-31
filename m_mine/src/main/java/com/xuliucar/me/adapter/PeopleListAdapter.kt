package com.xuliucar.me.adapter

import android.support.v7.widget.AppCompatImageView
import android.support.v7.widget.AppCompatTextView
import android.view.View
import com.cangmaomao.recyclerview.adapter.CMMAdapter
import com.cangmaomao.recyclerview.adapter.CMMViewHolder
import com.xuliucar.me.R
import com.xuliucar.me.model.Noteinfo
import com.xuliucar.me.model.PeopleData

/**
 * Created by skyward on 2016/7/7.
 *
 */
class PeopleListAdapter(layoutId: Int = R.layout.item_contacts) : CMMAdapter<PeopleData.DataBean>(layoutId) {


    override fun convert(cmmViewHolder: CMMViewHolder, dataBean: PeopleData.DataBean, position: Int) {
        val tv_index = cmmViewHolder.getView<AppCompatTextView>(R.id.tv_index)
        if (position == 0 || data[position-1].index != dataBean.index) {
            tv_index.visibility = View.VISIBLE;
            tv_index.text = dataBean.getIndex();
        } else {
            tv_index.visibility = View.GONE;
        }
        cmmViewHolder.setText(R.id.tv_name, dataBean.name)
    }
}
