package com.xuliucar.xuli.xuliucar.mvp.view

import com.xuliucar.xuli.xuliucar.bean.CarsListBean

/**
 * Created by skyward on 2016/11/23.
 */

interface CarListView {
    fun tagName(): String

    fun companyId(): String

    fun loginId(): String

    fun loadData(list: MutableList<CarsListBean.DataBean.InfoBean>)
}
