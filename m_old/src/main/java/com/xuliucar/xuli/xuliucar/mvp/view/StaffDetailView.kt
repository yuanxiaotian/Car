package com.xuliucar.xuli.xuliucar.mvp.view

import com.xuliucar.xuli.xuliucar.bean.StaffDetailBean

/**
 * Created by skyward on 2016/11/24.
 * email：
 */

interface StaffDetailView {
    fun getData(bean: StaffDetailBean.DataBean)
    fun toastMsg(msg: String)
}
