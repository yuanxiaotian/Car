package com.xuliucar.xuli.xuliucar.mvp.model

import com.cangmaomao.lib.utils.*
import com.cangmaomao.network.request.HttpManage
import com.cangmaomao.network.request.utils.RxSchedulers
import com.xuliucar.xuli.xuliucar.base.App
import com.xuliucar.xuli.xuliucar.bean.Counts
import com.xuliucar.xuli.xuliucar.config.ApiConfigTest
import com.xuliucar.xuli.xuliucar.config.ApiManager
import com.xuliucar.xuli.xuliucar.mvp.ModelInterface.CallBackListener
import com.xuliucar.xuli.xuliucar.mvp.ModelInterface.IPassItemIndex
import com.xuliucar.xuli.xuliucar.mvp.presenter.BasePresenterImpl

import rx.Observer
import rx.Subscription
import rx.android.schedulers.AndroidSchedulers
import rx.functions.Func1
import rx.schedulers.Schedulers

/**
 * Created by skyward on 2016/12/5.
 * email：
 */

class PassItemIndexModel(private val mListener: CallBackListener<Counts>) : BasePresenterImpl(), IPassItemIndex {

    override fun getPassItemIndex(loginid: String, compid: String) {
    }
}
