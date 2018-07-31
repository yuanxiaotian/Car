package com.xuliucar.xuli.xuliucar.newmvp.presenter.salemanage.detils

import com.cangmaomao.network.request.HttpManage
import com.cangmaomao.network.request.utils.RxSchedulers
import com.xuliucar.xuli.xuliucar.bean.AchieveManageBean
import com.xuliucar.xuli.xuliucar.bean.OrderManageBean
import com.xuliucar.xuli.xuliucar.config.ApiConfigTest
import com.xuliucar.xuli.xuliucar.newmvp.bean.OrderDetails
import com.xuliucar.xuli.xuliucar.newmvp.contract.SaleMangeContract


class OrderManageDetailsPresenter(val view: SaleMangeContract.OrderDetailsView) : SaleMangeContract.OrderDetailsPresenter {

    init {
        view.setPresenter(this)
    }

    override fun start() {
        loadData()
    }

    override fun loadData() {
        HttpManage.getInstance()
                .create(ApiConfigTest::class.java)
                .getOrderDetils(view.loginId(), view.companyId(), view.orderId())
                .compose(RxSchedulers.io_main())
                .subscribe({ disposeData(it) }, { view.showFail(it) })
    }

    override fun disposeData(bean: OrderDetails) {
        if (bean.isSuccess) {
            view.showDate(bean.data, bean.data.info[0])
            view.showMoreCarModel(bean.data.info)
        }
    }


}