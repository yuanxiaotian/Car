package com.xuliucar.xuli.xuliucar.mvp.view

import com.xuliucar.xuli.xuliucar.bean.InComeBean

/**
 * Created by skyward on 2016/11/29.
 * email：
 */

interface InComeView {
    fun companyId(): String
    fun loginId(): String
    fun startLoad(been: List<InComeBean.DataBean>, size: Int)
    fun loadMore(been: List<InComeBean.DataBean>, size: Int)
    fun toastMsg(msg: String)
    fun showError()
}
