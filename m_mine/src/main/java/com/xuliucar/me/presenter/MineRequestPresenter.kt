package com.xuliucar.me.presenter

import com.cangmaomao.network.request.HttpManage
import com.cangmaomao.network.request.utils.RxSchedulers
import com.xuliucar.me.api.ApiConfig
import com.xuliucar.me.contract.MineContract

class MineRequestPresenter(private val presenter: MineContract.Presenter) : MineContract.RequestPresenter {

    override fun start() {
    }

    override fun login(company: String, account: String, pwd: String) {
        HttpManage.getInstance().create(ApiConfig::class.java).getLogin(company, account, pwd)
                .compose(RxSchedulers.io_main())
                .subscribe({
                    presenter.loginSuccess(it)
                }, {
                    presenter.requestFail(it.message)
                })
    }

    override fun reg(tag: String) {
    }


}