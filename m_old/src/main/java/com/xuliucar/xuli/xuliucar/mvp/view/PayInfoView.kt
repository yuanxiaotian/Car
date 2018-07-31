package com.xuliucar.xuli.xuliucar.mvp.view

import com.xuliucar.xuli.xuliucar.bean.PayInfoBean

/**
 * Created by skyward on 2016/11/29.
 * email：
 */

interface PayInfoView {
    fun startLoad(been: MutableList<PayInfoBean.DataBean>, size: Int)
    fun loadMore(been: MutableList<PayInfoBean.DataBean>, size: Int)
    fun toastMsg(msg: String)
    fun showError()
    fun companyId(): String
    fun loginId(): String
}
