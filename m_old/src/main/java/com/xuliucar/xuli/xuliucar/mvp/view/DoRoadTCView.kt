package com.xuliucar.xuli.xuliucar.mvp.view

import com.xuliucar.xuli.xuliucar.bean.DoRoadTCBean

/**
 * Created by skyward on 2016/12/1.
 * email：
 */

interface DoRoadTCView {
    fun startLoad(been: MutableList<DoRoadTCBean.DataBean.InfoBean>, size: Int)
    fun loadMore(been: MutableList<DoRoadTCBean.DataBean.InfoBean>, size: Int)
    fun loadAll(been: MutableList<DoRoadTCBean.DataBean.InfoBean>)
    fun toastMsg(msg: String)
    fun showError()
}
