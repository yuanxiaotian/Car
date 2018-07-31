package com.xuliucar.me.presenter

import com.cangmaomao.network.request.HttpManage
import com.cangmaomao.network.request.base.BaseObserver
import com.cangmaomao.network.request.utils.RxSchedulers
import com.xuliucar.me.R
import com.xuliucar.me.api.ApiConfig
import com.xuliucar.me.contract.MeContract
import com.xuliucar.me.model.CompanyInfo
import com.xuliucar.me.model.MeBean

class MePresenter(val view: MeContract.View) : MeContract.Presenter {


    init {
        view.setPresenter(this)
    }

    override fun start() {
        loadData()
    }


    override fun loadData() {
        val manage = HttpManage.getInstance();
        val api = manage.create(ApiConfig::class.java)
        manage.concat(api.getMe(view.loginId(), view.companyId()), api.companyInfo(view.loginId(), view.companyId()), object : BaseObserver<Any>("") {
            override fun success(p0: Any) {
                disposeData(p0)
            }

            override fun fail(p0: String?) {
                view.showFail(Throwable(view.context().getString(R.string.request_err)))
            }

        })
    }

    override fun disposeData(bean: Any) {
        when (bean) {
            is MeBean -> if (bean.isSuccess) view.showData(bean.data) else view.showFail(Throwable(view.context().getString(R.string.request_err)))
            is CompanyInfo -> if (bean.isSuccess) view.showCompanyInfo(bean.data) else view.showFail(Throwable(view.context().getString(R.string.request_err)))
        }
    }
}