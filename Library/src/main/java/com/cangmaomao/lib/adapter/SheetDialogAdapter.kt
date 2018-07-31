package com.cangmaomao.lib.adapter

import com.cangmaomao.lib.R
import com.cangmaomao.lib.bean.SheetDialogData
import com.cangmaomao.recyclerview.adapter.CMMAdapter
import com.cangmaomao.recyclerview.adapter.CMMViewHolder

class SheetDialogAdapter(layoutId: Int) : CMMAdapter<SheetDialogData>(layoutId) {

    override fun convert(holder: CMMViewHolder, p1: SheetDialogData, p2: Int) {
        holder.setText(R.id.item, p1.name)
        holder.setTextColor(R.id.item, holder.mContext.resources.getColor(R.color.c_1e))
    }

}