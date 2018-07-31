package com.xuliucar.xuli.xuliucar.mvp.view

/**
 * Created by skyward on 2016/12/6.
 * email：
 */

interface CarDetailView<T> {
    fun getData(t: T)
    fun toastMsg(msg: String)
    fun showError()
    fun companyId(): String
    fun loginId(): String
}
