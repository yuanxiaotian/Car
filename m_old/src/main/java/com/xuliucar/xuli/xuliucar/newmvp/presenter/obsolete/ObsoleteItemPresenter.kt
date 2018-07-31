package com.xuliucar.xuli.xuliucar.newmvp.presenter.obsolete

import com.cangmaomao.network.request.HttpManage
import com.cangmaomao.network.request.utils.RxSchedulers
import com.xuliucar.xuli.xuliucar.R
import com.xuliucar.xuli.xuliucar.bean.Counts
import com.xuliucar.xuli.xuliucar.bean.OrderManageBean
import com.xuliucar.xuli.xuliucar.config.ApiConfigTest
import com.xuliucar.xuli.xuliucar.newmvp.contract.ObsoleteItemContract
import com.xuliucar.xuli.xuliucar.newmvp.contract.SaleMangeContract


class ObsoleteItemPresenter(val view: ObsoleteItemContract.MainView) : ObsoleteItemContract.MainPresenter {

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
                .getCounts(view.loginId(), view.companyId())
                .compose(RxSchedulers.io_main())
                .subscribe({ disposeData(it) }, { view.showFail(it) })
    }

    override fun disposeData(bean: Counts) {
        if (bean.isSuccess) view.showDate(bean.data.info) else view.showFail(Throwable(view.context().getString(R.string.request_err)))
    }

}