package com.xuliucar.xuli.xuliucar.newmvp.view.saleManage


import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.cangmaomao.lib.base.BaseNewFragment
import com.cangmaomao.lib.utils.SPUtils.create
import com.xuliucar.xuli.xuliucar.R
import com.xuliucar.xuli.xuliucar.bean.AchieveManageBean
import com.xuliucar.xuli.xuliucar.newmvp.adapter.AchieveManageAdapter
import com.xuliucar.xuli.xuliucar.newmvp.contract.SaleMangeContract
import com.xuliucar.xuli.xuliucar.newmvp.presenter.salemanage.AchievementMangePresenter
import kotlinx.android.synthetic.main.achieve_manage.*

/**
 * A simple [Fragment] subclass.
 */
class AchieveManage : BaseNewFragment<SaleMangeContract.AchievementPresenter>(), SaleMangeContract.AchievementView {

    private lateinit var adapter: AchieveManageAdapter
    override fun layViewId(): Int = R.layout.achieve_manage
    override fun addViewId(): Int = 0
    override fun context(): Context = _mActivity
    override fun companyId(): String = create("userInfo").get("compid", "") as String
    override fun loginId(): String = create("userInfo").get("loginid", "") as String

    override fun initView(savedInstanceState: Bundle?, view: View) {
        recyclerView.layoutManager = LinearLayoutManager(_mActivity)
        adapter = AchieveManageAdapter(R.layout.achieve_manage_item)
        recyclerView.adapter = adapter
        AchievementMangePresenter(this)
        p.start()
    }

    override fun showFail(e: Throwable) {
    }

    override fun showDate(list: MutableList<AchieveManageBean.DataBean>) {
        adapter.addList(list)
    }
}
