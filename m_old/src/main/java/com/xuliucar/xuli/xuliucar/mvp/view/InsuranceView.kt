package com.xuliucar.xuli.xuliucar.mvp.view

import com.xuliucar.xuli.xuliucar.bean.InSuranceBean

/**
 * Created by skyward on 2016/12/1.
 * email：
 */

interface InsuranceView {
    fun startLoad(been: MutableList<InSuranceBean.DataBean.InfoBean>, size: Int)
    fun loadMore(been: MutableList<InSuranceBean.DataBean.InfoBean>, size: Int)
    fun loadAll(been: MutableList<InSuranceBean.DataBean.InfoBean>)
    fun toastMsg(msg: String)
    fun showError()
}
