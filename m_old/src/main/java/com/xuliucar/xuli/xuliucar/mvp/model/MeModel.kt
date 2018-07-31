package com.xuliucar.xuli.xuliucar.mvp.model

import com.cangmaomao.network.request.HttpManage
import com.cangmaomao.network.request.base.BaseObserver
import com.cangmaomao.network.request.utils.RxSchedulers
import com.xuliucar.xuli.xuliucar.base.App
import com.xuliucar.xuli.xuliucar.bean.MeBean
import com.xuliucar.xuli.xuliucar.config.ApiConfigTest
import com.xuliucar.xuli.xuliucar.config.ApiManager
import com.xuliucar.xuli.xuliucar.mvp.ModelInterface.CallBackListener
import com.xuliucar.xuli.xuliucar.mvp.ModelInterface.IMe
import com.xuliucar.xuli.xuliucar.mvp.presenter.BasePresenterImpl

import rx.Observer
import rx.Subscription
import rx.android.schedulers.AndroidSchedulers
import rx.functions.Func1
import rx.schedulers.Schedulers

/**
 * Created by skyward on 2016/11/23.
 */

class MeModel(private val mOnMeListener: CallBackListener<MeBean>) : BasePresenterImpl(), IMe {

    override fun getMe(tag: String, loginId: String, companyId: String) {
        HttpManage.getInstance()
                .create(ApiConfigTest::class.java)
                .getMe(loginId, companyId)
                .compose(RxSchedulers.io_main())
                .subscribe({ mOnMeListener.OnSuccess(it) }, { mOnMeListener.OnFailure(it) })
    }
}
