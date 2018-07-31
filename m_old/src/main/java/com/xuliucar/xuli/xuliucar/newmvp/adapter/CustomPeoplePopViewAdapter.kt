package com.xuliucar.xuli.xuliucar.newmvp.adapter

import android.widget.TextView
import com.cangmaomao.recyclerview.adapter.CMMAdapter
import com.cangmaomao.recyclerview.adapter.CMMViewHolder
import com.xuliucar.xuli.xuliucar.R
import com.xuliucar.xuli.xuliucar.adapter.TagAdapter
import com.xuliucar.xuli.xuliucar.bean.PeopleBean
import com.xuliucar.xuli.xuliucar.widget.tagView.FlowTagLayout

class CustomPeoplePopViewAdapter(layoutId: Int, mData: MutableList<MutableList<PeopleBean.DataBean.Bean>>?) : CMMAdapter<MutableList<PeopleBean.DataBean.Bean>>(layoutId, mData) {

    override fun convert(holder: CMMViewHolder, p1: MutableList<PeopleBean.DataBean.Bean>, p2: Int) {
        val list = ArrayList<String>()
        for (i in p1) {
            list.add(i.name)
        }
        val flowTagLayout = holder.getView<FlowTagLayout>(R.id.custom_people_owners)
        val adapter = TagAdapter<String>(holder.mContext)
        flowTagLayout.adapter = adapter
        adapter.onlyAddAll(list.toMutableList())
        flowTagLayout.setTagCheckedMode(FlowTagLayout.FLOW_TAG_CHECKED_MULTI);
        flowTagLayout.setOnTagSelectListener { parent, selectedList, position -> }

        val view = holder.getView<TextView>(R.id.tv_name)
        when (p2) {
            0 -> view.text = "司机"
            1 -> view.text = "车主"
            2 -> view.text = "用户"
        }

    }

}
