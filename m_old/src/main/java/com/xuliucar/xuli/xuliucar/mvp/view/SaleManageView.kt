package com.xuliucar.xuli.xuliucar.mvp.view

import com.xuliucar.xuli.xuliucar.bean.SaleManagerBean
import java.math.BigDecimal

/**
 * Created by skyward on 2016/11/30.
 * email：
 */

interface SaleManageView {
    fun OnSuccess(bean: SaleManagerBean, price: Float, count: Int)
    fun OnFailure(e: Throwable)
    fun loginId(): String

}
