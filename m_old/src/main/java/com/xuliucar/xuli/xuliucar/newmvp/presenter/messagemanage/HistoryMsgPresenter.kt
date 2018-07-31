package com.xuliucar.xuli.xuliucar.newmvp.presenter.messagemanage

import com.cangmaomao.network.request.HttpManage
import com.cangmaomao.network.request.base.BaseObserver
import com.cangmaomao.network.request.utils.RxSchedulers
import com.xuliucar.xuli.xuliucar.R
import com.xuliucar.xuli.xuliucar.bean.HistorySendBean
import com.xuliucar.xuli.xuliucar.bean.MessageCountBean
import com.xuliucar.xuli.xuliucar.config.ApiConfigTest
import com.xuliucar.xuli.xuliucar.newmvp.contract.MessageMangeContract


class HistoryMsgPresenter(val view: MessageMangeContract.HistoryMsgView) : MessageMangeContract.HistoryMsgPresenter {

    init {
        view.setPresenter(this)
    }

    override fun start() {
        loadData()
    }

    override fun loadData() {
        HttpManage
                .getInstance()
                .create(ApiConfigTest::class.java)
                .getHistorySend(view.loginId(), view.companyId())
                .compose(RxSchedulers.io_main())
                .subscribe({ disposeData(it) }, { view.showFail(it) })
    }

    override fun sendMsg() {
        HttpManage
                .getInstance()
                .create(ApiConfigTest::class.java)
                .smsresend(view.loginId(), view.companyId(), view.msgId())
                .compose(RxSchedulers.io_main())
                .subscribe({  view.showSendMsgStart(it.message)  }, { view.showFail(it) })
    }

    override fun disposeData(bean: HistorySendBean) {
        if (bean.isSuccess) view.showDate(bean.data) else view.showFail(Throwable(view.context().getString(R.string.request_err)))
    }
}