package com.xuliucar.xuli.xuliucar.mvp.view

import com.xuliucar.xuli.xuliucar.bean.EnvironmentBean

/**
 * Created by skyward on 2016/12/2.
 * email：
 */

interface EnvironmentView {
    fun startLoad(been: MutableList<EnvironmentBean.DataBean.InfoBean>, size: Int)
    fun loadMore(been: MutableList<EnvironmentBean.DataBean.InfoBean>, size: Int)
    fun loadAll(been: MutableList<EnvironmentBean.DataBean.InfoBean>)
    fun toastMsg(msg: String)
    fun showError()
}
