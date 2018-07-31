package com.xuliucar.xuli.xuliucar.mvp.view

import com.xuliucar.xuli.xuliucar.bean.MeBean

/**
 * Created by skyward on 2016/11/23.
 */

interface MeView {

    val tagName: String

    val companyId: String

    val loginId: String

    fun getData(bean: MeBean.DataBean)

    fun toastMsg(msg: String)

    fun showError()
}
