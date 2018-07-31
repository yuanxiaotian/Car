package com.xuliucar.xuli.xuliucar.mvp.view

import com.xuliucar.xuli.xuliucar.bean.DriverDetailBean

/**
 * Created by skyward on 2016/11/24.
 * email：
 */

interface DriverDetailView {
    fun getData(bean: DriverDetailBean)
    fun toastMsg(msg: String)
}
