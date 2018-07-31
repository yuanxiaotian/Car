package com.xuliucar.xuli.xuliucar.ui.homePage.financeManager


import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.cangmaomao.lib.base.BaseNewFragment
import com.cangmaomao.lib.base.BasePresenter
import com.cangmaomao.lib.utils.SPUtils.create
import com.xuliucar.xuli.xuliucar.R
import com.xuliucar.xuli.xuliucar.adapter.PassPayAdapter
import com.xuliucar.xuli.xuliucar.bean.PassPayBean
import com.xuliucar.xuli.xuliucar.mvp.presenter.PassPayPresenter
import com.xuliucar.xuli.xuliucar.mvp.view.PassPayView
import kotlinx.android.synthetic.main.pass_pay.*

class PassPay : BaseNewFragment<BasePresenter>(), PassPayView {

    private lateinit var adapter: PassPayAdapter
    private lateinit var mPresenter: PassPayPresenter

    override fun companyId(): String = create("userInfo").get("compid", "") as String
    override fun loginId(): String = create("userInfo").get("loginid", "") as String

    override fun layViewId(): Int = R.layout.pass_pay
    override fun addViewId(): Int = 0

    override fun initView(savedInstanceState: Bundle?, view: View) {
        recyclerView.layoutManager = LinearLayoutManager(_mActivity)
        adapter = PassPayAdapter(R.layout.car_loan_item)
        recyclerView.adapter = adapter

        mPresenter = PassPayPresenter(this)
        mPresenter.getData()

    }

    override fun OnSuccess(list: MutableList<PassPayBean.DataBean>) {
        adapter.addList(list)
    }

    override fun OnFailure(e: Throwable) {
    }

    override fun showError() {
        Snackbar.make(recyclerView, getString(R.string.snack_infor), Snackbar.LENGTH_SHORT).setAction("重试") { mPresenter.getData() }.show()
    }

}
