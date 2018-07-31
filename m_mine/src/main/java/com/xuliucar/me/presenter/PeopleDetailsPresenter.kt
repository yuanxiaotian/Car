package com.xuliucar.me.presenter

import com.cangmaomao.network.request.HttpManage
import com.cangmaomao.network.request.utils.RxSchedulers
import com.xuliucar.me.R
import com.xuliucar.me.api.ApiConfig
import com.xuliucar.me.contract.PeopleContract
import com.xuliucar.me.model.PeopleData
import com.xuliucar.me.model.PeopleDetails
import com.xuliucar.me.ui.fragment.Me
import com.xuliucar.me.ui.fragment.PeopleDetail
import com.xuliucar.me.ui.fragment.PeopleList

class PeopleDetailsPresenter(val view: PeopleContract.DetailsView) : PeopleContract.DetailsPresenter {


    init {
        view.setPresenter(this)
    }

    override fun start() {
        loadData()
    }


    override fun loadData() {
        when (view.itemId()) {
            Me.ITEM_1 -> {
                HttpManage.getInstance().create(ApiConfig::class.java)
                        .userDetail(view.loginId(), view.companyId(), view.uuid())
                        .compose(RxSchedulers.io_main())
                        .subscribe({ disposeData(it) }, { view.showFail(it) })
            }
            Me.ITEM_2 -> {
                HttpManage.getInstance().create(ApiConfig::class.java)
                        .driverDetail(view.loginId(), view.companyId(), view.uuid())
                        .compose(RxSchedulers.io_main())
                        .subscribe({ disposeData(it) }, { view.showFail(it) })
            }
            Me.ITEM_3 -> {
                HttpManage.getInstance().create(ApiConfig::class.java)
                        .ownerDetail(view.loginId(), view.companyId(), view.uuid())
                        .compose(RxSchedulers.io_main())
                        .subscribe({ disposeData(it) }, { view.showFail(it) })
            }
        }


    }

    override fun disposeData(bean: PeopleDetails) {
        when (view.itemId()) {
            Me.ITEM_2 -> bean.data.name = bean.data.drivername
            Me.ITEM_3 -> bean.data.name = bean.data.ownername
        }
        if (bean.isSuccess) view.showData(bean.data) else view.showFail(Throwable(view.context().getString(R.string.request_err)))
    }
}