package com.xuliucar.me.presenter

import com.cangmaomao.lib.config.AppPower
import com.xuliucar.me.contract.MineContract
import com.xuliucar.me.model.LoginInfo

class MinePresenter(val view: MineContract.LoginView) : MineContract.Presenter {

    override fun start() {
    }

    private var requestPresenter: MineRequestPresenter

    init {
        this.view.setPresenter(this)
        requestPresenter = MineRequestPresenter(this)
    }


    override fun loginRequest() {
        requestPresenter.login(view.getCompany(), view.getAccount(), view.getPwd())
    }

    override fun loginSuccess(loginInfo: LoginInfo) {
        AppPower.app_pow1 = loginInfo.data.apppower.app_pow1
        AppPower.app_pow2 = loginInfo.data.apppower.app_pow2
        AppPower.app_pow3 = loginInfo.data.apppower.app_pow3
        AppPower.app_pow4 = loginInfo.data.apppower.app_pow4
        AppPower.app_pow5 = loginInfo.data.apppower.app_pow5
        AppPower.app_pow6 = loginInfo.data.apppower.app_pow6
        AppPower.app_pow7 = loginInfo.data.apppower.app_pow7
        AppPower.app_pow8 = loginInfo.data.apppower.app_pow8
        AppPower.app_pow9 = loginInfo.data.apppower.app_pow9
        AppPower.app_pow10 = loginInfo.data.apppower.app_pow10
        AppPower.app_pow11 = loginInfo.data.apppower.app_pow11
        AppPower.app_pow12 = loginInfo.data.apppower.app_pow12
        AppPower.app_pow13 = loginInfo.data.apppower.app_pow13
        AppPower.app_pow14 = loginInfo.data.apppower.app_pow14
        AppPower.app_pow15 = loginInfo.data.apppower.app_pow15
        AppPower.app_pow16 = loginInfo.data.apppower.app_pow16
        AppPower.app_pow17 = loginInfo.data.apppower.app_pow17
        AppPower.app_pow18 = loginInfo.data.apppower.app_pow18
        AppPower.app_pow19 = loginInfo.data.apppower.app_pow19
        AppPower.app_pow20 = loginInfo.data.apppower.app_pow20
        AppPower.app_pow21 = loginInfo.data.apppower.app_pow21
        AppPower.app_pow22 = loginInfo.data.apppower.app_pow22
        AppPower.app_pow23 = loginInfo.data.apppower.app_pow23
        AppPower.app_pow24 = loginInfo.data.apppower.app_pow24
        AppPower.app_pow25 = loginInfo.data.apppower.app_pow25
        AppPower.app_pow26 = loginInfo.data.apppower.app_pow26
        AppPower.app_pow27 = loginInfo.data.apppower.app_pow27
        AppPower.app_pow28 = loginInfo.data.apppower.app_pow28
        AppPower.app_pow29 = loginInfo.data.apppower.app_pow29
        AppPower.app_pow30 = loginInfo.data.apppower.app_pow30
        AppPower.app_pow31 = loginInfo.data.apppower.app_pow31
        AppPower.app_pow32 = loginInfo.data.apppower.app_pow32
        AppPower.app_pow33 = loginInfo.data.apppower.app_pow33
        AppPower.app_pow34 = loginInfo.data.apppower.app_pow34
        AppPower.app_pow35 = loginInfo.data.apppower.app_pow35
        AppPower.app_pow36 = loginInfo.data.apppower.app_pow36
        AppPower.app_pow37 = loginInfo.data.apppower.app_pow37
        AppPower.app_pow38 = loginInfo.data.apppower.app_pow38
        view.showRequestInfo(loginInfo)
    }

    override fun reg(tag: String) {
    }

    override fun requestFail(msg: String?) {
        view.showRequestErrInfo(msg)
    }
}