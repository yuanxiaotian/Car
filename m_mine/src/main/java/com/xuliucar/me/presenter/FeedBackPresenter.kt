package com.xuliucar.me.presenter

import com.cangmaomao.network.request.HttpManage
import com.cangmaomao.network.request.utils.RxSchedulers
import com.xuliucar.me.R
import com.xuliucar.me.api.ApiConfig
import com.xuliucar.me.contract.FeedBackContract
import com.xuliucar.me.model.MessageBean

class FeedBackPresenter(val view: FeedBackContract.View) : FeedBackContract.Presenter {


    init {
        view.setPresenter(this)
    }

    override fun start() {
        loadData()
    }


    override fun loadData() {
        HttpManage.getInstance().create(ApiConfig::class.java)
                .feedBack(view.loginId(), view.companyId(), view.name(), view.phone(), view.content())
                .compose(RxSchedulers.io_main())
                .subscribe({ disposeData(it) }, { view.showFail(it) })
    }

    override fun disposeData(bean: MessageBean) {
        if (bean.isSuccess) view.showData(bean) else view.showFail(Throwable(view.context().getString(R.string.request_err)))
    }
}