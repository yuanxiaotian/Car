package com.xuliucar.xuli.xuliucar.newmvp.presenter.salemanage.detils

import com.cangmaomao.network.request.HttpManage
import com.cangmaomao.network.request.utils.RxSchedulers
import com.xuliucar.xuli.xuliucar.bean.AchieveManageBean
import com.xuliucar.xuli.xuliucar.bean.ContractMDetailBean
import com.xuliucar.xuli.xuliucar.bean.OrderManageBean
import com.xuliucar.xuli.xuliucar.config.ApiConfigTest
import com.xuliucar.xuli.xuliucar.newmvp.bean.OrderDetails
import com.xuliucar.xuli.xuliucar.newmvp.contract.SaleMangeContract


class ContractManageDetailsPresenter(val view: SaleMangeContract.ContractDetailsView) : SaleMangeContract.ContractDetailsPresenter {

    init {
        view.setPresenter(this)
    }

    override fun start() {
        loadData()
    }

    override fun loadData() {
        HttpManage.getInstance()
                .create(ApiConfigTest::class.java)
                .getContractMDetail(view.loginId(), view.orderId(), view.companyId())
                .compose(RxSchedulers.io_main())
                .subscribe({ disposeData(it) }, { view.showFail(it) })
    }

    override fun disposeData(bean: ContractMDetailBean) {
        if (bean.isSuccess) {
            val math = bean.data.dealprice.toDouble() - bean.data.downpay.toDouble()
            view.showDate(bean.data, math.toString())
        }
    }


}