package com.xuliucar.xuli.xuliucar.ui.homePage.allCar


import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.cangmaomao.lib.action.f_carDetail
import com.cangmaomao.lib.base.BaseNewFragment
import com.cangmaomao.lib.base.BasePresenter
import com.cangmaomao.lib.event.AppEvent
import com.cangmaomao.lib.utils.SPUtils.create
import com.cangmaomao.recyclerview.adapter.CMMAdapter
import com.cangmaomao.recyclerview.adapter.contract.OnItemClick
import com.xuliucar.xuli.xuliucar.R
import com.xuliucar.xuli.xuliucar.adapter.CarListAdapter
import com.xuliucar.xuli.xuliucar.bean.CarsListBean
import com.xuliucar.xuli.xuliucar.mvp.presenter.CarListPresenter
import com.xuliucar.xuli.xuliucar.mvp.view.CarListView
import kotlinx.android.synthetic.main.car_list.*
import org.greenrobot.eventbus.EventBus


class CarList : BaseNewFragment<BasePresenter>(), OnItemClick, CarListView {
    override fun tagName(): String = ""
    private lateinit var adapter: CarListAdapter
    private lateinit var mCarListPresenter: CarListPresenter

    override fun loginId(): String = create("userInfo").get("loginid", "") as String
    override fun companyId(): String = create("userInfo").get("compid", "") as String
    override fun layViewId(): Int = R.layout.car_list
    override fun addViewId(): Int = 0
    override fun initView(savedInstanceState: Bundle?, view: View) {
        val manager = LinearLayoutManager(_mActivity)
        recyclerView.layoutManager = manager
        recyclerView.addItemDecoration(DividerItemDecoration(_mActivity, manager.orientation))
        adapter = CarListAdapter(R.layout.car_list_item)
        recyclerView.adapter = adapter
        adapter.setOnItemClickListener(this)
        mCarListPresenter = CarListPresenter(this)
        mCarListPresenter.getCarListData()
        smartRefreshLayout.setOnRefreshListener {
            it.finishRefresh(2000)
        }
        smartRefreshLayout.setOnLoadMoreListener {
            it.finishLoadMore(2000)
        }
    }

    override fun onItemClick(cmmAdapter: CMMAdapter<*>, o: Any, i: Int) {


        val infoBean = (cmmAdapter.data as List<CarsListBean.DataBean.InfoBean>)[i]
        val bundle = Bundle()
        bundle.putSerializable("carInfoBean", infoBean)
        bundle.putString("page", "0")
        EventBus.getDefault().post(AppEvent(f_carDetail, bundle))
    }

    override fun loadData(list: MutableList<CarsListBean.DataBean.InfoBean>) {
        adapter.addList(list)
    }
}
