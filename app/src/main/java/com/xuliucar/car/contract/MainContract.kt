package com.xuliucar.car.contract

import android.content.Context
import com.cangmaomao.lib.base.BasePresenter
import com.cangmaomao.lib.base.BaseView
import com.xuliucar.xuli.xuliucar.bean.Counts
import com.xuliucar.xuli.xuliucar.bean.HomePageBean

interface MainContract {


    /**
     * 主
     */
    interface View : BaseView<Presenter> {

        //获取部分
        fun context(): Context

        fun loginId(): String
        fun companyId(): String

        //显示部分
        fun showFail(e: Throwable)

        fun loginOverdue(overdue: Boolean)

    }

    interface Presenter : BasePresenter {
        fun loadData()

        fun disposeData(bean: HomePageBean)
    }

}