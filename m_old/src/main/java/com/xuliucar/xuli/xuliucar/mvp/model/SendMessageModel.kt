package com.xuliucar.xuli.xuliucar.mvp.model

import com.cangmaomao.network.request.HttpManage
import com.cangmaomao.network.request.utils.RxSchedulers
import com.xuliucar.xuli.xuliucar.base.App
import com.xuliucar.xuli.xuliucar.bean.MessageCountBean
import com.xuliucar.xuli.xuliucar.bean.PeopleBean
import com.xuliucar.xuli.xuliucar.config.ApiConfigTest
import com.xuliucar.xuli.xuliucar.mvp.presenter.BasePresenterImpl

/**
 * Created by skyward on 2016/12/7.
 * email：
 */

class SendMessageModel(private val mListener: CallBackListener) : BasePresenterImpl() {

    fun getMessageInfo() {
        HttpManage.getInstance()
                .create(ApiConfigTest::class.java)
                .getMessageInfo(App.loginid, App.compid.toString())
                .compose(RxSchedulers.io_main())
                .subscribe({ mListener.getCountInfo(it) }, { mListener.OnFailure(it) })

    }

    fun getPeople() {
        HttpManage.getInstance()
                .create(ApiConfigTest::class.java)
                .getPeople(App.loginid, App.compid.toString())
                .compose(RxSchedulers.io_main())
                .subscribe({ mListener.getPeople(it) }, { mListener.OnFailure(it) })
    }

    interface CallBackListener {
        fun getCountInfo(bean: MessageCountBean)

        fun getPeople(bean: PeopleBean)

        fun OnFailure(e: Throwable)
    }
}
