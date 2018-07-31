package com.xuliucar.xuli.xuliucar.mvp.presenter

import com.xuliucar.xuli.xuliucar.bean.SaleManagerBean
import com.xuliucar.xuli.xuliucar.mvp.ModelInterface.CallBackListener
import com.xuliucar.xuli.xuliucar.mvp.model.SaleManagerModel
import com.xuliucar.xuli.xuliucar.mvp.view.SaleManageView
import java.math.BigDecimal

class SaleManagerPresenter(val view: SaleManageView) : CallBackListener<SaleManagerBean> {


    private var mode: SaleManagerModel = SaleManagerModel(this)


    fun getData() {
        mode.getSalesOrder(view.loginId())
    }


    override fun OnSuccess(t: SaleManagerBean) {
        if (t.isSuccess) {
            var price: Float = 0f
            var k = 0
            for (i in t.data) {
                price += i.dealprice.toFloat()
                k++
            }

            view.OnSuccess(t, price, k)
        }
    }

    override fun OnFailure(e: Throwable) {
        view.OnFailure(e)
    }


}