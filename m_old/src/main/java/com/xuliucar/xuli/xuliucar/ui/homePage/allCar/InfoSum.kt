package com.xuliucar.xuli.xuliucar.ui.homePage.allCar


import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.TextView
import com.cangmaomao.lib.base.BaseNewFragment
import com.cangmaomao.lib.utils.SPUtils.create
import com.cangmaomao.recyclerview.adapter.CMMAdapter
import com.cangmaomao.recyclerview.adapter.CMMViewHolder
import com.xuliucar.xuli.xuliucar.R
import com.xuliucar.xuli.xuliucar.bean.InfoSumBean
import com.xuliucar.xuli.xuliucar.mvp.presenter.InfoSumPresenter
import com.xuliucar.xuli.xuliucar.mvp.view.InfoSumView
import com.xuliucar.xuli.xuliucar.utils.ToastUtil
import kotlinx.android.synthetic.main.info_sum.*


class InfoSum : BaseNewFragment<com.cangmaomao.lib.base.BasePresenter>(), InfoSumView {
    private lateinit var adapter: CMMAdapter<InfoSumBean.DataBean.InfoBean>
    private lateinit var mPresenter: InfoSumPresenter


    override val loginId: String
        get() = create("userInfo").get("loginid", "") as String

    override val tagName: String
        get() = ""

    override val companyId: String
        get() = create("userInfo").get("compid", "") as String

    override fun layViewId(): Int {
        return R.layout.info_sum
    }

    override fun initView(savedInstanceState: Bundle?, view: View) {
        recyclerView.layoutManager = LinearLayoutManager(_mActivity)
        adapter = object : CMMAdapter<InfoSumBean.DataBean.InfoBean>(R.layout.info_sum_item) {
            override fun convert(cmmViewHolder: CMMViewHolder, infoBean: InfoSumBean.DataBean.InfoBean, i: Int) {
                (cmmViewHolder.getView<View>(R.id.car_type) as TextView).text = infoBean.name
                (cmmViewHolder.getView<View>(R.id.car_type_count) as TextView).text = infoBean.count
            }
        }
        recyclerView.adapter = adapter

        mPresenter = InfoSumPresenter(this)
        mPresenter.getData()
    }

    override fun addViewId(): Int {
        return 0
    }


    override fun onDestroy() {
        super.onDestroy()
        mPresenter.unSubcrible()
    }

    override fun getSumCount(count: String) {
        sum_car_num.text = count//车辆总数
    }

    override fun getCarList(bean: MutableList<InfoSumBean.DataBean.InfoBean>) {
        adapter.addList(bean)
    }

    override fun toastMsg(msg: String) {
        when (msg) {
            "already logout" -> {
                ToastUtil.showShortToast(activity, msg)
                mPresenter.toLogin(activity)
            }
            "未登陆" -> {
                ToastUtil.showShortToast(activity, msg)
                mPresenter.toLogin(activity)
            }
            "已登出,或在其它设备上登陆!" -> {
                ToastUtil.showShortToast(activity, msg)
                mPresenter.alreadyLogin(activity)
            }
        }
    }

    override fun showError() {}


}
