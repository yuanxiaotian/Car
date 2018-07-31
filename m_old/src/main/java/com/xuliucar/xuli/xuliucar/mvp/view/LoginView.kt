package com.xuliucar.xuli.xuliucar.mvp.view

/**
 * Created by skyward on 2016/11/22.
 *
 */

interface LoginView {
    val company: String
    val username: String
    val password: String
    fun showToast(msg: String)
    fun showError(msg: String)
    fun showError()
}
