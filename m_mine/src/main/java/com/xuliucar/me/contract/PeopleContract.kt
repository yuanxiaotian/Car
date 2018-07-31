package com.xuliucar.me.contract

import android.content.Context
import com.cangmaomao.lib.base.BasePresenter
import com.cangmaomao.lib.base.BaseView
import com.xuliucar.me.model.*

interface PeopleContract {


    /**
     * 主
     */
    interface View : BaseView<Presenter> {

        //获取部分
        fun context(): Context

        fun loginId(): String
        fun companyId(): String

        fun itemId(): String

        //显示部分
        fun showFail(e: Throwable)

        //显示请求信息
        fun showData(list: MutableList<PeopleData.DataBean>)


    }

    interface Presenter : BasePresenter {

        fun loadData()

        fun disposeData(bean: PeopleData)
    }


    /**
     * 详情
     */
    interface DetailsView : BaseView<DetailsPresenter> {

        //获取部分
        fun context(): Context

        fun loginId(): String
        fun companyId(): String

        fun uuid(): String

        fun itemId(): String

        //显示请求信息
        fun showData(dataBean: PeopleDetails.DataBean)

        //显示部分
        fun showFail(e: Throwable)

    }

    interface DetailsPresenter : BasePresenter {

        fun loadData()

        fun disposeData(bean: PeopleDetails)
    }

}