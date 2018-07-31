package com.xuliucar.xuli.xuliucar.mvp.model

import com.cangmaomao.network.request.HttpManage
import com.cangmaomao.network.request.utils.RxSchedulers
import com.xuliucar.xuli.xuliucar.base.App
import com.xuliucar.xuli.xuliucar.bean.AssistantDriverBean
import com.xuliucar.xuli.xuliucar.config.ApiConfigTest
import com.xuliucar.xuli.xuliucar.config.ApiManager
import com.xuliucar.xuli.xuliucar.mvp.ModelInterface.CallBackListener
import com.xuliucar.xuli.xuliucar.mvp.ModelInterface.IAssistantDriver
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

class AssistantDriverModel(private val mListener: CallBackListener<AssistantDriverBean>) : BasePresenterImpl(), IAssistantDriver {

    override fun getAssistant(cid: String,loginid:String,compid:String) {
        HttpManage
                .getInstance()
                .create(ApiConfigTest::class.java)
                .getAssistantDriver(loginid, compid, cid)
                .compose(RxSchedulers.io_main())
                .subscribe({ mListener.OnSuccess(it) }, { mListener.OnFailure(it) })
    }
}
