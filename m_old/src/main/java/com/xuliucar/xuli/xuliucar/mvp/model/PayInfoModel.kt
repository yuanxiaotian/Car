package com.xuliucar.xuli.xuliucar.mvp.model

import com.cangmaomao.network.request.HttpManage
import com.cangmaomao.network.request.utils.RxSchedulers
import com.xuliucar.xuli.xuliucar.base.App
import com.xuliucar.xuli.xuliucar.bean.PayInfoBean
import com.xuliucar.xuli.xuliucar.config.ApiConfigTest
import com.xuliucar.xuli.xuliucar.config.ApiManager
import com.xuliucar.xuli.xuliucar.mvp.ModelInterface.CallBackListener
import com.xuliucar.xuli.xuliucar.mvp.ModelInterface.IPayInfoModel
import com.xuliucar.xuli.xuliucar.mvp.presenter.BasePresenterImpl
import com.xuliucar.xuli.xuliucar.mvp.view.PayInfoView

import rx.Observer
import rx.Subscription
import rx.android.schedulers.AndroidSchedulers
import rx.functions.Func1
import rx.schedulers.Schedulers

/**
 * Created by skyward on 2016/11/29.
 * email：
 */

class PayInfoModel(private val listener: CallBackListener<PayInfoBean>,val view: PayInfoView) : BasePresenterImpl(), IPayInfoModel {
    override fun getPayInfo() {
        HttpManage.getInstance()
                .create(ApiConfigTest::class.java)
                .getPayInfo(view.loginId(), view.companyId())
                .compose(RxSchedulers.io_main())
                .subscribe({listener.OnSuccess(it)}, {listener.OnFailure(it)})
    }
}
