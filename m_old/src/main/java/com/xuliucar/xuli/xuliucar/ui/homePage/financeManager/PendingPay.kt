package com.xuliucar.xuli.xuliucar.ui.homePage.financeManager


import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.cangmaomao.lib.base.BaseNewFragment
import com.cangmaomao.lib.base.BasePresenter
import com.cangmaomao.lib.utils.SPUtils.create
import com.xuliucar.xuli.xuliucar.R
import com.xuliucar.xuli.xuliucar.adapter.PendingPayAdapter
import com.xuliucar.xuli.xuliucar.bean.PendingPayBean
import com.xuliucar.xuli.xuliucar.mvp.presenter.PendingPayPresenter
import com.xuliucar.xuli.xuliucar.mvp.view.PendingPayView
import kotlinx.android.synthetic.main.pending_pay.*

class PendingPay : BaseNewFragment<BasePresenter>(), PendingPayView {
    private lateinit var adapter: PendingPayAdapter
    private lateinit var mPresenter: PendingPayPresenter

    override fun layViewId(): Int = R.layout.pending_pay

    override fun initView(savedInstanceState: Bundle?, view: View) {
        recyclerView.layoutManager = LinearLayoutManager(_mActivity)
        adapter = PendingPayAdapter(R.layout.car_loan_item)
        recyclerView.adapter = adapter

        mPresenter = PendingPayPresenter(this)
        mPresenter.getData()
    }

    override fun addViewId(): Int = 0

    override fun loginId(): String = create("userInfo").get("loginid", "") as String

    override fun companyId(): String = create("userInfo").get("compid", "") as String

    override fun showError() {
        Snackbar.make(recyclerView, getString(R.string.snack_infor), Snackbar.LENGTH_SHORT).setAction("重试") { mPresenter.getData() }.show()
    }

    override fun OnSuccess(list: MutableList<PendingPayBean.DataBean.InfoBean>) {
        adapter.addList(list)
    }

    override fun OnFailure(e: Throwable) {
    }

}
