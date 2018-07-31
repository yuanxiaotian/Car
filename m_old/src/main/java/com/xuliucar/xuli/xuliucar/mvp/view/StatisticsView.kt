package com.xuliucar.xuli.xuliucar.mvp.view

import com.xuliucar.xuli.xuliucar.bean.StatisticsBean

/**
 * Created by skyward on 2016/11/24.
 * email：
 */

interface StatisticsView {
    fun getData(bean: StatisticsBean)
    fun toastMsg(msg: String)
    fun showError()
    fun loginId(): String
}
