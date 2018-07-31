package com.xuliucar.xuli.xuliucar.mvp.model

import com.cangmaomao.network.request.HttpManage
import com.cangmaomao.network.request.utils.RxSchedulers
import com.xuliucar.xuli.xuliucar.bean.PassPayBean
import com.xuliucar.xuli.xuliucar.config.ApiConfigTest
import com.xuliucar.xuli.xuliucar.mvp.ModelInterface.CallBackListener
import com.xuliucar.xuli.xuliucar.mvp.ModelInterface.IPassPayModel
import com.xuliucar.xuli.xuliucar.mvp.presenter.BasePresenterImpl
import com.xuliucar.xuli.xuliucar.mvp.view.PassPayView

/**
 * Created by skyward on 2016/11/29.
 * email：
 */

class PassPayModel(private val mListener: CallBackListener<PassPayBean>, val view: PassPayView) : BasePresenterImpl(), IPassPayModel {
    override fun getPassPay() {
        HttpManage.getInstance()
                .create(ApiConfigTest::class.java)
                .getPassPay(view.loginId(), view.companyId())
                .compose(RxSchedulers.io_main())
                .subscribe({ mListener.OnSuccess(it) }, { mListener.OnFailure(it) })
    }
}
