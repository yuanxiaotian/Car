package com.xuliucar.me.contract

import android.content.Context
import com.cangmaomao.lib.base.BasePresenter
import com.cangmaomao.lib.base.BaseView
import com.xuliucar.me.model.CompanyInfo
import com.xuliucar.me.model.MeBean
import com.xuliucar.me.model.MessageBean

interface FeedBackContract {


    /**
     * 主
     */
    interface View : BaseView<Presenter> {

        //获取部分
        fun context(): Context

        fun loginId(): String
        fun companyId(): String
        fun name(): String
        fun phone(): String
        fun content(): String

        //显示部分
        fun showFail(e: Throwable)

        //显示请求信息
        fun showData(data: MessageBean)


    }

    interface Presenter : BasePresenter {

        fun loadData()

        fun disposeData(bean: MessageBean)
    }

}