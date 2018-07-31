package com.xuliucar.car.presenter

import com.cangmaomao.network.request.HttpManage
import com.cangmaomao.network.request.base.BaseObserver
import com.cangmaomao.network.request.utils.RxSchedulers
import com.xuliucar.car.R
import com.xuliucar.car.contract.MainContract
import com.xuliucar.xuli.xuliucar.bean.Counts
import com.xuliucar.xuli.xuliucar.bean.HomePageBean
import com.xuliucar.xuli.xuliucar.config.ApiConfigTest

class MainPresenter(val view: MainContract.View) : MainContract.Presenter {


    init {
        view.setPresenter(this)
    }


    override fun start() {
        loadData()
    }

    override fun loadData() {
        HttpManage.getInstance().create(ApiConfigTest::class.java)
                .getdata(view.loginId(), view.companyId())
                .compose(RxSchedulers.io_main())
                .subscribe({ disposeData(it) }, { view.showFail(it) })
    }

    override fun disposeData(bean: HomePageBean) {

         view.loginOverdue(bean.data == null)
    }
}