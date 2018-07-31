package com.xuliucar.xuli.xuliucar.newmvp.view.saleManage.detail

import android.content.Context
import android.os.Bundle
import android.view.View
import com.cangmaomao.lib.base.BaseNewFragment
import com.cangmaomao.lib.utils.SPUtils.create
import com.xuliucar.xuli.xuliucar.R
import com.xuliucar.xuli.xuliucar.bean.ContractMDetailBean
import com.xuliucar.xuli.xuliucar.newmvp.contract.SaleMangeContract
import com.xuliucar.xuli.xuliucar.newmvp.presenter.salemanage.detils.ContractManageDetailsPresenter
import kotlinx.android.synthetic.main.contract_mdetail.*

class ContractMDetail : BaseNewFragment<SaleMangeContract.ContractDetailsPresenter>(), SaleMangeContract.ContractDetailsView {

    override fun layViewId(): Int = R.layout.contract_mdetail
    override fun addViewId(): Int = 0
    override fun context(): Context = _mActivity
    override fun companyId(): String = create("userInfo").get("compid", "") as String
    override fun loginId(): String = create("userInfo").get("loginid", "") as String
    override fun orderId(): String = arguments!!.getString("id")

    override fun initView(savedInstanceState: Bundle?, view: View) {
        initToolbar(getString(R.string.Contract_details), null)
        ContractManageDetailsPresenter(this)
        p.start()
    }

    override fun showDate(bean: ContractMDetailBean.DataBean, retainage: String) {
        cd_clientname.text = bean.clientname
        cd_cartype.text = bean.cartype
        cd_model.text = bean.model
        cd_framenum.text = bean.framenum
        cd_enginenum.text = bean.enginenum
        cd_dealtype.text = bean.dealtype
        cd_dealprice.text = bean.dealprice
        cd_deposits.text = bean.deposits
        cd_downpay.text = bean.downpay
        cd_insttl.text = bean.insttl
        cd_instnum.text = bean.instnum
        cd_paydate.text = bean.paydate
        cd_principal.text = bean.principal
        cd_interest.text = bean.interest
        cd_retainage.text = retainage
    }

    override fun showFail(e: Throwable) {
    }

}
