package com.cangmaomao.lib.utils

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.cangmaomao.lib.R
import com.cangmaomao.lib.view.RecycleViewDivider


fun Fragment.initRecycler(view: RecyclerView, orientation: Int = 0, spanCount: Int = 2, colorId: Int = R.color.c_e8) {
    view.layoutManager = if (orientation == 0) LinearLayoutManager(activity) else GridLayoutManager(activity, spanCount)
    view.addItemDecoration(RecycleViewDivider(activity, orientation, activity!!.dp2px(0.8f), resources.getColor(colorId)))
}

fun Context.initRecycler(view: RecyclerView, colorId: Int = resources.getColor(R.color.c_w), orientation: Int = 1, spanCount: Int = 2) {
    view.layoutManager = LinearLayoutManager(this)
    val i = if (orientation == 0) LinearLayoutManager.HORIZONTAL else LinearLayoutManager.VERTICAL
    view.addItemDecoration(RecycleViewDivider(this, i, this.dp2px(0.8f), colorId))
}
