package com.xuliucar.xuli.xuliucar.mvp.view

import com.xuliucar.xuli.xuliucar.bean.InfoSumBean
import java.util.ArrayList

/**
 * Created by skyward on 2016/11/29.
 * email：
 */

interface InfoSumView {

    val tagName: String
    val companyId: String
    val loginId: String
    fun getSumCount(count: String)
    fun getCarList(bean: MutableList<InfoSumBean.DataBean.InfoBean>)
    fun toastMsg(msg: String)
    fun showError()
}
