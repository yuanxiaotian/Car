package com.xuliucar.xuli.xuliucar.newmvp.presenter.salemanage

import com.cangmaomao.network.request.HttpManage
import com.cangmaomao.network.request.utils.RxSchedulers
import com.xuliucar.xuli.xuliucar.bean.AchieveManageBean
import com.xuliucar.xuli.xuliucar.bean.OrderManageBean
import com.xuliucar.xuli.xuliucar.config.ApiConfigTest
import com.xuliucar.xuli.xuliucar.newmvp.contract.SaleMangeContract


class AchievementMangePresenter(val view: SaleMangeContract.AchievementView) : SaleMangeContract.AchievementPresenter {

    init {
        view.setPresenter(this)
    }

    override fun start() {
        loadData()
    }

    override fun loadData() {
        HttpManage.getInstance()
                .create(ApiConfigTest::class.java)
                .getAchieveManage(view.loginId(), view.companyId())
                .compose(RxSchedulers.io_main())
                .subscribe({ disposeData(it) }, { view.showFail(it) })
    }

    override fun disposeData(bean: AchieveManageBean) {
        if (bean.isSuccess) {
            view.showDate(bean.data)
        }
    }


}