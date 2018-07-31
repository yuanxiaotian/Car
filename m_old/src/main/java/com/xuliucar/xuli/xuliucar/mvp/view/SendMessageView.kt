package com.xuliucar.xuli.xuliucar.mvp.view

import com.xuliucar.xuli.xuliucar.bean.PeopleBean

/**
 * Created by skyward on 2016/12/7.
 * email：
 */

interface SendMessageView {
    fun getCountsPrice(s: String)
    fun getTodaySend(s: String)
    fun getTotalSend(s: String)
    fun getOwner(ownerBeen: MutableList<PeopleBean.DataBean.Bean>)
    fun getDriver(driverBeen: MutableList<PeopleBean.DataBean.Bean>)
    fun getUser(userBeen: MutableList<PeopleBean.DataBean.Bean>)
    fun getTips(tips: String)
}
