package com.xuliucar.xuli.xuliucar.mvp.model

import com.cangmaomao.network.request.HttpManage
import com.cangmaomao.network.request.utils.RxSchedulers
import com.xuliucar.xuli.xuliucar.base.App
import com.xuliucar.xuli.xuliucar.bean.CarOwnerInfoBean
import com.xuliucar.xuli.xuliucar.config.ApiConfigTest
import com.xuliucar.xuli.xuliucar.config.ApiManager
import com.xuliucar.xuli.xuliucar.mvp.ModelInterface.CallBackListener
import com.xuliucar.xuli.xuliucar.mvp.ModelInterface.ICarOwnerInfo
import com.xuliucar.xuli.xuliucar.mvp.presenter.BasePresenterImpl

import rx.Observer
import rx.Subscription
import rx.android.schedulers.AndroidSchedulers
import rx.functions.Func1
import rx.schedulers.Schedulers

/**
 * Created by skyward on 2016/12/6.
 * email：
 */

class CarOwnerInfoModel(private val mListener: CallBackListener<CarOwnerInfoBean>) : BasePresenterImpl(), ICarOwnerInfo {

    override fun getCarOwnerInfo(cid: String, loginid: String, compid: String) {
        HttpManage
                .getInstance()
                .create(ApiConfigTest::class.java)
                .getCarOwnerInfo(loginid, compid, cid)
                .compose(RxSchedulers.io_main())
                .subscribe({}, {})
    }
}
