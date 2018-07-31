package com.xuliucar.xuli.xuliucar.mvp.model

import com.cangmaomao.network.request.HttpManage
import com.cangmaomao.network.request.utils.RxSchedulers
import com.xuliucar.xuli.xuliucar.base.App
import com.xuliucar.xuli.xuliucar.bean.CarInfoBean
import com.xuliucar.xuli.xuliucar.config.ApiConfigTest
import com.xuliucar.xuli.xuliucar.config.ApiManager
import com.xuliucar.xuli.xuliucar.mvp.ModelInterface.CallBackListener
import com.xuliucar.xuli.xuliucar.mvp.ModelInterface.ICarInfo
import com.xuliucar.xuli.xuliucar.mvp.presenter.BasePresenterImpl
import com.xuliucar.xuli.xuliucar.mvp.view.CarDetailView

import rx.Observer
import rx.Subscription
import rx.android.schedulers.AndroidSchedulers
import rx.functions.Func1
import rx.schedulers.Schedulers

/**
 * Created by skyward on 2016/12/6.
 * email：
 */

class CarInfoModel(private val mListener: CallBackListener<CarInfoBean>, val view: CarDetailView<CarInfoBean.DataBean>) : BasePresenterImpl(), ICarInfo {

    override fun getCarInfo(cid: String) {
        HttpManage.getInstance()
                .create(ApiConfigTest::class.java)
                .getCarInfo(view.loginId(), view.companyId(), cid)
                .compose(RxSchedulers.io_main())
                .subscribe({ mListener.OnSuccess(it) }, { mListener.OnFailure(it) })
    }
}
