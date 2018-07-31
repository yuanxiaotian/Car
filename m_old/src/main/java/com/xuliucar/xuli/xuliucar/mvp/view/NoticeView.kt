package com.xuliucar.xuli.xuliucar.mvp.view

import com.xuliucar.xuli.xuliucar.bean.NoticeBean

/**
 * Created by skyward on 2016/11/23.
 */

interface NoticeView {

    val loginId: String

    val companyId: String

    fun getdata(bean: MutableList<NoticeBean.NoticeList>)

    fun showToast(msg: String)

    fun showError()
}
