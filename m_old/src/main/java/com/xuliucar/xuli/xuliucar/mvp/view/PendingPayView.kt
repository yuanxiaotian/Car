package com.xuliucar.xuli.xuliucar.mvp.view

import com.xuliucar.xuli.xuliucar.bean.PendingPayBean

/**
 * Created by skyward on 2016/11/29.
 * email：
 */

interface PendingPayView {
    fun showError()
    fun loginId(): String
    fun companyId(): String
    fun OnSuccess(list: MutableList<PendingPayBean.DataBean.InfoBean>)
    fun OnFailure(e: Throwable)
}
