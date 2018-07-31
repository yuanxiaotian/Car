package com.xuliucar.xuli.xuliucar.mvp.view

import com.xuliucar.xuli.xuliucar.bean.StockCarBean
import java.util.ArrayList

/**
 * Created by skyward on 2016/12/5.
 * email：
 */

interface StockCarView {
    val tagName: String
    val companyId: String
    val loginId: String
    fun getCarCount(count: String)
    fun getStockCar(bean: MutableList<StockCarBean.DataBean.InfoBean>, size: Int)
    fun toastMsg(msg: String)
    fun showError()
}
