package com.xuliucar.xuli.xuliucar.mvp.view

import com.xuliucar.xuli.xuliucar.bean.DoCompanyDocBean

/**
 * Created by skyward on 2016/12/2.
 * email：
 */

interface DoCompanyDocView {
    fun startLoad(been: MutableList<DoCompanyDocBean.DataBean.InfoBean>, size: Int)
    fun loadMore(been: MutableList<DoCompanyDocBean.DataBean.InfoBean>, size: Int)
    fun toastMsg(msg: String)
    fun showError()
}
