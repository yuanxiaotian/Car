package com.xuliucar.me.contract

import android.content.Context
import com.cangmaomao.lib.base.BasePresenter
import com.cangmaomao.lib.base.BaseView
import com.xuliucar.me.model.CompanyInfo
import com.xuliucar.me.model.MeBean

interface MeContract {


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

        //退出登录
        fun exitLogin()

        //去其他页面
        fun toItemFragment(appPower: String, fragmentId: Int, itemId: String)

        //显示请求信息
        fun showData(data: MeBean.DataBean)

        //显示部分公司信息
        fun showCompanyInfo(data: CompanyInfo.DataBean)


    }

    interface Presenter : BasePresenter {

        fun loadData()

        fun disposeData(bean: Any)
    }

}