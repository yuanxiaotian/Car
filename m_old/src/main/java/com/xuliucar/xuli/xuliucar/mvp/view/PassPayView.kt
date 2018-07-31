package com.xuliucar.xuli.xuliucar.mvp.view

import com.xuliucar.xuli.xuliucar.bean.PassPayBean

/**
 * Created by skyward on 2016/11/29.
 * email：
 */

interface PassPayView {
    fun companyId(): String
    fun loginId(): String
    fun OnSuccess(list: MutableList<PassPayBean.DataBean>)
    fun OnFailure(e: Throwable)
    fun showError()
}
