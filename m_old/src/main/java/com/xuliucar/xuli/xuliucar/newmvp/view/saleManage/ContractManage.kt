package com.xuliucar.xuli.xuliucar.newmvp.view.saleManage


import android.content.Context
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.cangmaomao.lib.action.f_contractMDetail
import com.cangmaomao.lib.base.BaseNewFragment
import com.cangmaomao.lib.event.AppEvent
import com.cangmaomao.lib.utils.SPUtils.create
import com.cangmaomao.lib.utils.initRecycler
import com.cangmaomao.recyclerview.adapter.CMMAdapter
import com.cangmaomao.recyclerview.adapter.contract.OnItemClick
import com.xuliucar.xuli.xuliucar.R
import com.xuliucar.xuli.xuliucar.bean.ContractManageBean
import com.xuliucar.xuli.xuliucar.newmvp.adapter.ContractManagerAdapter
import com.xuliucar.xuli.xuliucar.newmvp.contract.SaleMangeContract
import com.xuliucar.xuli.xuliucar.newmvp.presenter.salemanage.ContractMangePresenter
import kotlinx.android.synthetic.main.contract_manage.*
import org.greenrobot.eventbus.EventBus

class ContractManage : BaseNewFragment<SaleMangeContract.ContractPresenter>(), OnItemClick, SaleMangeContract.ContractView {

    private lateinit var adapter: ContractManagerAdapter

    override fun layViewId(): Int = R.layout.contract_manage
    override fun addViewId(): Int = 0
    override fun context(): Context = _mActivity
    override fun companyId(): String = create("userInfo").get("compid", "") as String
    override fun loginId(): String = create("userInfo").get("loginid", "") as String

    override fun initView(savedInstanceState: Bundle?, view: View) {
        initRecycler(recyclerView)
        adapter = ContractManagerAdapter(R.layout.contract_manage_item)
        recyclerView.adapter = adapter
        adapter.setOnItemClickListener(this)
        ContractMangePresenter(this)
        p.start()
    }

    override fun showFail(e: Throwable) {
    }

    override fun showDate(list: MutableList<ContractManageBean.DataBean>) {
        adapter.addList(list)
    }

    override fun onItemClick(cmmAdapter: CMMAdapter<*>, o: Any, i: Int) {
        val d = o as ContractManageBean.DataBean
        val bundle = Bundle()
        bundle.putString("id", d.id)
        EventBus.getDefault().post(AppEvent(f_contractMDetail, bundle))
    }
}
