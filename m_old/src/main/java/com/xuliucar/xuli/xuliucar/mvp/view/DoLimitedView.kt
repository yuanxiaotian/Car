package com.xuliucar.xuli.xuliucar.mvp.view

import com.xuliucar.xuli.xuliucar.bean.LimitedBean

/**
 * Created by skyward on 2016/12/1.
 * email：
 */

interface DoLimitedView {
    fun startLoad(been: MutableList<LimitedBean.DataBean.InfoBean>, size: Int)
    fun loadMore(been: MutableList<LimitedBean.DataBean.InfoBean>, size: Int)
    fun loadAll(been: MutableList<LimitedBean.DataBean.InfoBean>)
    fun toastMsg(msg: String)
    fun showError()
}
