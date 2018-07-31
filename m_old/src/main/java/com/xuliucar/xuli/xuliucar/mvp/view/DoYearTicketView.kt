package com.xuliucar.xuli.xuliucar.mvp.view

import com.xuliucar.xuli.xuliucar.bean.YearTicketBean

/**
 * Created by skyward on 2016/12/1.
 * email：
 */

interface DoYearTicketView {
    fun startLoad(been: MutableList<YearTicketBean.DataBean.InfoBean>, size: Int)
    fun loadMore(been: MutableList<YearTicketBean.DataBean.InfoBean>, size: Int)
    fun loadAll(been: MutableList<YearTicketBean.DataBean.InfoBean>)
    fun toastMsg(msg: String)
    fun showError()
}
