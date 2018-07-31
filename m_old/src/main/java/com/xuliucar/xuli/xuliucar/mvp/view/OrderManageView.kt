package com.xuliucar.xuli.xuliucar.mvp.view

import com.xuliucar.xuli.xuliucar.bean.OrderManageBean

/**
 * Created by skyward on 2016/11/30.
 * email：
 */

interface OrderManageView {
    fun startLoad(been: MutableList<OrderManageBean.DataBean>, size: Int)
    fun loadMore(been: MutableList<OrderManageBean.DataBean>, size: Int)
    fun toastMsg(msg: String)
    fun showError()
    fun companyId(): String
    fun loginId(): String
}
