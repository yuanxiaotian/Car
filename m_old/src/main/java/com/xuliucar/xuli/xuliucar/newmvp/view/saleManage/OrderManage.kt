package com.xuliucar.xuli.xuliucar.newmvp.view.saleManage


import android.content.Context
import android.os.Bundle
import android.view.View
import com.cangmaomao.lib.action.f_orderDetail
import com.cangmaomao.lib.base.BaseNewFragment
import com.cangmaomao.lib.event.AppEvent
import com.cangmaomao.lib.utils.SPUtils.create
import com.cangmaomao.lib.utils.initRecycler
import com.cangmaomao.recyclerview.adapter.CMMAdapter
import com.cangmaomao.recyclerview.adapter.contract.OnItemClick
import com.xuliucar.xuli.xuliucar.R
import com.xuliucar.xuli.xuliucar.bean.OrderManageBean
import com.xuliucar.xuli.xuliucar.newmvp.adapter.OrderManagerAdapter
import com.xuliucar.xuli.xuliucar.newmvp.contract.SaleMangeContract
import com.xuliucar.xuli.xuliucar.newmvp.presenter.salemanage.OrderMangePresenter
import kotlinx.android.synthetic.main.order_manage.*
import org.greenrobot.eventbus.EventBus

class OrderManage : BaseNewFragment<SaleMangeContract.OrderPresenter>(), OnItemClick, SaleMangeContract.OrderView {

    private lateinit var adapter: OrderManagerAdapter
    override fun layViewId(): Int = R.layout.order_manage
    override fun addViewId(): Int = 0
    override fun context(): Context = _mActivity
    override fun companyId(): String = create("userInfo").get("compid", "") as String
    override fun loginId(): String = create("userInfo").get("loginid", "") as String

    override fun initView(savedInstanceState: Bundle?, view: View) {
        initRecycler(recyclerView)
        adapter = OrderManagerAdapter(R.layout.order_manage_item)
        adapter.setOnItemClickListener(this)
        recyclerView.adapter = adapter
        OrderMangePresenter(this)
        p.start()
    }

    override fun showFail(e: Throwable) {
    }

    override fun showDate(list: MutableList<OrderManageBean.DataBean>) {
        adapter.addList(list)
    }

    override fun onItemClick(p0: CMMAdapter<*>?, data: Any?, p2: Int) {
        val d = data as OrderManageBean.DataBean
        val bundle = Bundle()
        bundle.putString("id", d.id)
        EventBus.getDefault().post(AppEvent(f_orderDetail, bundle))
    }

}
