package com.xuliucar.me.contract

import com.cangmaomao.lib.base.BasePresenter
import com.cangmaomao.lib.base.BaseView
import com.xuliucar.me.model.LoginInfo

interface MineContract {

    interface LoginView : BaseView<Presenter> {

        fun showRequestInfo(t: LoginInfo)

        fun showRequestErrInfo(msg: String?)

        fun getCompany(): String

        fun getAccount(): String

        fun getPwd(): String

    }


    interface Presenter : BasePresenter {

        //登陆请求
        fun loginRequest()

        //登陸成功后
        fun loginSuccess(loginInfo: LoginInfo)

        //注册
        fun reg(tag: String)

        //请求失败
        fun requestFail(msg: String?)
    }


    interface RequestPresenter : BasePresenter {
        //登陸
        fun login(company: String, account: String, pwd: String)

        //注册
        fun reg(tag: String)
    }

}