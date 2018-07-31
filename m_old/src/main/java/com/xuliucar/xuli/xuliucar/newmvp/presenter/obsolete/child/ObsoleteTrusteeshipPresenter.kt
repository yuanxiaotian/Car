package com.xuliucar.xuli.xuliucar.newmvp.presenter.obsolete.child

import com.cangmaomao.network.request.HttpManage
import com.cangmaomao.network.request.utils.RxSchedulers
import com.xuliucar.xuli.xuliucar.R
import com.xuliucar.xuli.xuliucar.bean.PassPerPetaueBean
import com.xuliucar.xuli.xuliucar.config.ApiConfigTest
import com.xuliucar.xuli.xuliucar.newmvp.contract.ObsoleteItemContract
import com.xuliucar.xuli.xuliucar.newmvp.view.obsolete.ObsoleteItem


class ObsoleteTrusteeshipPresenter(val view: ObsoleteItemContract.TrusteeshipView) : ObsoleteItemContract.TrusteeshipPresenter {

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
                .obsolete(view.itemFlag(), view.loginId(), view.companyId())
                .compose(RxSchedulers.io_main())
                .subscribe({ disposeData(it) }, { view.showFail(it) })
    }

    override fun disposeData(bean: PassPerPetaueBean) {
        if (bean.isSuccess) view.showDate(bean.data.info) else view.showFail(Throwable(view.context().getString(R.string.request_err)))
    }


    override fun showCheckBox(data: MutableList<PassPerPetaueBean.DataBean.InfoBean>, flag: Boolean) {
        for (i in data) {
            i.isShowAndHide = flag
        }
        view.notifyDataSetChanged()
        view.showAllSelect(flag)
    }

    override fun allCheck(data: MutableList<PassPerPetaueBean.DataBean.InfoBean>) {
        data.forEach { it.isSelect = !it.isSelect }
        view.notifyDataSetChanged()
    }

    override fun cancelCheck(data: MutableList<PassPerPetaueBean.DataBean.InfoBean>, flag: Boolean) {
        data.forEach {
            it.isShowAndHide = flag
            it.isSelect = flag
        }

        view.notifyDataSetChanged()
        view.showAllSelect(flag)
    }

    override fun checkUpSelect(data: MutableList<PassPerPetaueBean.DataBean.InfoBean>): Boolean {
        var k = 0
        data.forEach { if (it.isSelect) k++ }
        return k >= 2
    }


}