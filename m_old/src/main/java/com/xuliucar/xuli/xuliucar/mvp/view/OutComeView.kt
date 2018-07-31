package com.xuliucar.xuli.xuliucar.mvp.view

import com.xuliucar.xuli.xuliucar.bean.OutComeBean

/**
 * Created by skyward on 2016/11/29.
 * email：
 */

interface OutComeView {
    fun companyId(): String
    fun loginId(): String
    fun startLoad(been: List<OutComeBean.DataBean>, size: Int)
    fun loadMore(been: List<OutComeBean.DataBean>, size: Int)
    fun toastMsg(msg: String)
    fun showError()
}
