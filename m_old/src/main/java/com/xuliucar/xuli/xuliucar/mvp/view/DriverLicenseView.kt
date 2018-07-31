package com.xuliucar.xuli.xuliucar.mvp.view

import com.xuliucar.xuli.xuliucar.bean.DriverLicenseBean

/**
 * Created by skyward on 2016/12/2.
 * email：
 */

interface DriverLicenseView {
    fun startLoad(been: MutableList<DriverLicenseBean.DataBean.InfoBean>, size: Int)
    fun loadMore(been: MutableList<DriverLicenseBean.DataBean.InfoBean>, size: Int)
    fun loadAll(been: MutableList<DriverLicenseBean.DataBean.InfoBean>)
    fun toastMsg(msg: String)
    fun showError()
}
