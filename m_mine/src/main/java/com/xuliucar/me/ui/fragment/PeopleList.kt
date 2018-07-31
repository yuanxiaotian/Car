package com.xuliucar.me.ui.fragment


import android.content.Context
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.cangmaomao.lib.action.f_peopleDetail
import com.cangmaomao.lib.base.BaseNewFragment
import com.cangmaomao.lib.event.AppEvent
import com.cangmaomao.lib.utils.SPUtils.create
import com.cangmaomao.lib.utils.initRecycler
import com.cangmaomao.recyclerview.adapter.CMMAdapter
import com.cangmaomao.recyclerview.adapter.contract.OnItemClick
import com.gjiazhe.wavesidebar.WaveSideBar
import com.xuliucar.me.R
import com.xuliucar.me.adapter.PeopleListAdapter
import com.xuliucar.me.contract.PeopleContract
import com.xuliucar.me.model.PeopleData
import com.xuliucar.me.presenter.PeopleListPresenter
import kotlinx.android.synthetic.main.contacts.*
import org.greenrobot.eventbus.EventBus


class PeopleList : BaseNewFragment<PeopleContract.Presenter>(), PeopleContract.View, OnItemClick {

    private lateinit var mAdapter: PeopleListAdapter

    override fun layViewId(): Int = R.layout.contacts
    override fun addViewId(): Int = 0
    override fun context(): Context = _mActivity
    override fun itemId(): String = arguments!!.getString("id")
    override fun companyId(): String = create("userInfo").get("compid", "") as String
    override fun loginId(): String = create("userInfo").get("loginid", "") as String

    override fun initView(savedInstanceState: Bundle?, view: View) {
        initToolbar("员工", null)
        initRecycler(rv_contacts)
        mAdapter = PeopleListAdapter()
        mAdapter.setOnItemClickListener(this)
        rv_contacts.adapter = mAdapter
        side_bar.setOnSelectIndexItemListener(WaveSideBar.OnSelectIndexItemListener { index ->
            for (i in 0 until mAdapter.data.size) {
                if (mAdapter.data[i].index == index) {
                    (rv_contacts.layoutManager as LinearLayoutManager).scrollToPositionWithOffset(i, 0)
                    return@OnSelectIndexItemListener
                }
            }
        })
        PeopleListPresenter(this)
        p.start()
    }

    override fun onItemClick(p0: CMMAdapter<*>?, data: Any?, p2: Int) {
        val dataBean = data as PeopleData.DataBean
        val bundle = Bundle()
        bundle.putString("itemId", itemId())
        bundle.putString("uuid", dataBean.id)
        EventBus.getDefault().post(AppEvent(f_peopleDetail, bundle))
    }


    override fun showData(list: MutableList<PeopleData.DataBean>) {
        mAdapter.addList(list)
    }

    override fun showFail(e: Throwable) {
    }

}
