package com.xuliucar.xuli.xuliucar.mvp.view

import com.xuliucar.xuli.xuliucar.bean.HomePageBean

/**
 * Created by skyward on 2016/11/22.
 */

interface HomePageView {

    fun loginId(): String

    fun companyId(): String

    fun getData(bean: HomePageBean)

    fun showToast(msg: String)

    fun odCount(num: Int)

    fun odtCount(num: Int)

    fun showBanner(bannerList: List<String>)

    fun showError()
}
