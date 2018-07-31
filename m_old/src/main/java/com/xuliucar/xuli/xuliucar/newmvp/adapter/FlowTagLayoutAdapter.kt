package com.xuliucar.xuli.xuliucar.newmvp.adapter

import android.content.Context
import com.xuliucar.xuli.xuliucar.R
import com.xuliucar.xuli.xuliucar.bean.PeopleBean
import com.zhy.adapter.abslistview.CommonAdapter
import com.zhy.adapter.abslistview.ViewHolder

class FlowTagLayoutAdapter(context: Context?, layoutId: Int, datas: MutableList<PeopleBean.DataBean.Bean>?) : CommonAdapter<PeopleBean.DataBean.Bean>(context, layoutId, datas) {

    override fun convert(viewHolder: ViewHolder, item: PeopleBean.DataBean.Bean, position: Int) {
        viewHolder.setText(R.id.tv_tag, item.name)
    }

}