package com.xuliucar.xuli.xuliucar.mvp.model

import com.cangmaomao.network.request.HttpManage
import com.cangmaomao.network.request.base.BaseObserver
import com.cangmaomao.network.request.utils.RxSchedulers
import com.xuliucar.xuli.xuliucar.base.App
import com.xuliucar.xuli.xuliucar.bean.TrusteeshipBean
import com.xuliucar.xuli.xuliucar.config.ApiConfigTest
import com.xuliucar.xuli.xuliucar.config.ApiManager
import com.xuliucar.xuli.xuliucar.mvp.ModelInterface.CallBackListener
import com.xuliucar.xuli.xuliucar.mvp.ModelInterface.IDoTrusteeshipModel
import com.xuliucar.xuli.xuliucar.mvp.presenter.BasePresenterImpl

import rx.Observer
import rx.Subscription
import rx.android.schedulers.AndroidSchedulers
import rx.functions.Func1
import rx.schedulers.Schedulers

/**
 * Created by skyward on 2016/12/1.
 * email：
 */

class DoTrusteeshipModel(private val mListener: CallBackListener<TrusteeshipBean>) : BasePresenterImpl(), IDoTrusteeshipModel {
    override fun getDoTrusteeship() {
        HttpManage.getInstance().create(ApiConfigTest::class.java)
                .getDoTrusteeship(App.loginid, App.compid)
                .compose(RxSchedulers.io_main())
                .subscribe({mListener.OnSuccess(it)},{mListener.OnFailure(it)})
    }
}
