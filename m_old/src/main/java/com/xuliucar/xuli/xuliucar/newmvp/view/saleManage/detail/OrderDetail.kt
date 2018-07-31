package com.xuliucar.xuli.xuliucar.newmvp.view.saleManage.detail

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import com.cangmaomao.lib.base.BaseNewFragment
import com.cangmaomao.lib.utils.SPUtils.create
import com.xuliucar.xuli.xuliucar.R
import com.xuliucar.xuli.xuliucar.newmvp.adapter.IntentModelAdapter
import com.xuliucar.xuli.xuliucar.newmvp.bean.OrderDetails
import com.xuliucar.xuli.xuliucar.newmvp.contract.SaleMangeContract
import com.xuliucar.xuli.xuliucar.newmvp.presenter.salemanage.detils.OrderManageDetailsPresenter
import kotlinx.android.synthetic.main.order_detail.*


class OrderDetail : BaseNewFragment<SaleMangeContract.OrderDetailsPresenter>(), SaleMangeContract.OrderDetailsView {

    private lateinit var dialog: AlertDialog
    override fun layViewId(): Int = R.layout.order_detail
    override fun addViewId(): Int = 0
    override fun context(): Context = _mActivity
    override fun companyId(): String = create("userInfo").get("compid", "") as String
    override fun loginId(): String = create("userInfo").get("loginid", "") as String
    override fun orderId(): String = arguments!!.getString("id")

    override fun initView(savedInstanceState: Bundle?, view: View) {
        initToolbar(getString(R.string.Order_details), null)
        OrderManageDetailsPresenter(this)
        p.start()
    }

    override fun showFail(e: Throwable) {
    }

    override fun showDate(bean: OrderDetails.DataBean, info: OrderDetails.DataBean.InfoBean) {
        d_ordernum.text = bean.ordernum
        d_client.text = bean.client
        d_clevel.text = bean.clevel
        d_phone.text = bean.phone
        d_dealtype.text = bean.dealtype
        d_dealprice.text = bean.dealprice
        d_status.text = bean.status
        d_model.text = info.model
        d_num.text = info.num
        d_nprice.text = info.nprice
        d_tprice.text = info.tprice
        d_more.setOnClickListener { dialog.show() }
    }

    override fun showMoreCarModel(list: MutableList<OrderDetails.DataBean.InfoBean>) {
        val dialog = AlertDialog.Builder(context())
        val view = LayoutInflater.from(context()).inflate(R.layout.intent_model_list, null)
        dialog.setView(view)
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(context())
        val adapter = IntentModelAdapter(R.layout.intent_model_list_item)
        adapter.setOnItemClickListener { cmmAdapter, any, i ->
            val d = any as OrderDetails.DataBean.InfoBean
            d_model.text = d.model
            d_num.text = d.num
            d_nprice.text = d.nprice
            d_tprice.text = d.tprice
            this.dialog.dismiss()
        }
        adapter.addList(list)
        recyclerView.adapter = adapter
        this.dialog = dialog.create()

    }

}
