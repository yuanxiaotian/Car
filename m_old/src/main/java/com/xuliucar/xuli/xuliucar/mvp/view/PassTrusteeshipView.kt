package com.xuliucar.xuli.xuliucar.mvp.view

import com.xuliucar.xuli.xuliucar.bean.TrusteeshipBean

/**
 * Created by skyward on 2016/11/30.
 * email：
 */

interface PassTrusteeshipView {
    fun startLoad(been: MutableList<TrusteeshipBean.DataBean.InfoBean>, size: Int)
    fun loadMore(been: MutableList<TrusteeshipBean.DataBean.InfoBean>, size: Int)
    fun loadAll(been: MutableList<TrusteeshipBean.DataBean.InfoBean>)
    fun toastMsg(msg: String)
    fun showError()
}
