package com.xuliucar.xuli.xuliucar.mvp.view

import com.xuliucar.xuli.xuliucar.bean.CarOwnerDetailBean

/**
 * Created by skyward on 2016/11/24.
 *
 */

interface CarOwnerDetailView {
    fun getCarOwnerDetailData(bean: CarOwnerDetailBean)
    fun toastMsg(msg: String)
    fun showError(msg: String)
}
