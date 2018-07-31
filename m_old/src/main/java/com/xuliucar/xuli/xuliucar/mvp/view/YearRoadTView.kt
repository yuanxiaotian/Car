package com.xuliucar.xuli.xuliucar.mvp.view

import com.xuliucar.xuli.xuliucar.bean.YearRoadTBean

/**
 * Created by skyward on 2016/12/2.
 * email：
 */

interface YearRoadTView {
    fun startLoad(been: MutableList<YearRoadTBean.DataBean.InfoBean>, size: Int)
    fun loadMore(been: MutableList<YearRoadTBean.DataBean.InfoBean>, size: Int)
    fun loadAll(been: MutableList<YearRoadTBean.DataBean.InfoBean>)
    fun toastMsg(msg: String)
    fun showError()
}
