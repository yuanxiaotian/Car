package com.xuliucar.xuli.xuliucar.ui.homePage.financeManager


import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.support.design.widget.Snackbar
import android.view.View
import android.widget.AdapterView
import android.widget.LinearLayout
import android.widget.ListView
import android.widget.ProgressBar
import com.cangmaomao.lib.utils.SPUtils.create
import com.xuliucar.xuli.xuliucar.R
import com.xuliucar.xuli.xuliucar.adapter.PayInfoAdapter
import com.xuliucar.xuli.xuliucar.base.BaseFragment
import com.xuliucar.xuli.xuliucar.bean.PayInfoBean
import com.xuliucar.xuli.xuliucar.mvp.presenter.PayInfoPresenter
import com.xuliucar.xuli.xuliucar.mvp.view.PayInfoView
import com.xuliucar.xuli.xuliucar.utils.Pull
import com.xuliucar.xuli.xuliucar.utils.ToastUtil
import java.util.*


class PayInfo : BaseFragment(), AdapterView.OnItemClickListener, PayInfoView {
    private var payInfo_listView: ListView? = null
    private var payInfo_refresh_tip: LinearLayout? = null
    private var payInfo_refresh_tip_no: LinearLayout? = null
    private var progressBar: ProgressBar? = null
    private var payInfoBeanList: MutableList<PayInfoBean.DataBean>? = null
    private var adapter: PayInfoAdapter? = null
    private var starts: Int = 0
    private var ends: Int = 0
    private val size = 10
    private var mPresenter: PayInfoPresenter? = null
    private var length: Int = 0

    override fun companyId(): String = create("userInfo").get("compid", "") as String
    override fun loginId(): String = create("userInfo").get("loginid", "") as String


    override fun setContentView() {
        setContentView(R.layout.pay_info)
    }


    override fun onDestroy() {
        super.onDestroy()
        mPresenter!!.unSubcrible()
    }

    override fun initRefresh() {
        super.initRefresh()
//        payInfo_refresh = getViewById(R.id.payInfo_refresh)
//        payInfo_refresh!!.setRefresh(this)
//        payInfo_refresh!!.setRefreshViewHolder(NormalRefreshViewHolder(activity, true))
//        payInfo_refresh!!.setIsShowRefreshingTime(true)
    }

    override fun initView() {
        mPresenter = PayInfoPresenter(this)
        payInfo_listView = getViewById(R.id.payInfo_listView)
        payInfo_refresh_tip = getViewById(R.id.payInfo_refresh_tip)
        payInfo_refresh_tip_no = getViewById(R.id.payInfo_refresh_tip_no)
        progressBar = getViewById(R.id.payInfo_progressBar)
        progressBar!!.visibility = View.GONE
        payInfoBeanList = ArrayList()
        adapter = PayInfoAdapter(activity, payInfoBeanList)
        payInfo_listView!!.adapter = adapter
        payInfo_listView!!.onItemClickListener = this
//        payInfo_refresh!!.beginRefreshing()
    }


    override fun onItemClick(adapterView: AdapterView<*>, view: View, i: Int, l: Long) {
        val payInfoBean = payInfoBeanList!![i]
        val intent = Intent(view.context, PayInfoDetail::class.java)
        val bundle = Bundle()
        bundle.putSerializable("payinfo", payInfoBean)
        intent.putExtras(bundle)
        toIntent(mContentView, intent)
    }



    override fun startLoad(been: MutableList<PayInfoBean.DataBean>, size: Int) {
        this.length = size
//        payInfoBeanList!!.addAll(been)
//        adapter!!.notifyDataSetChanged()
//        Pull.pullToRefreshDown(activity, payInfo_refresh, payInfo_refresh_tip_no, size)

    }

    override fun loadMore(been: MutableList<PayInfoBean.DataBean>, size: Int) {
//        Handler().postDelayed({
//            payInfoBeanList!!.addAll(been)
//            adapter!!.notifyDataSetChanged()
//            Pull.pullToRefreshUp(activity, payInfo_refresh, payInfo_refresh_tip, size, starts, ends)
//        }, 100)
    }

    override fun toastMsg(msg: String) {
        when (msg) {
            "already logout" -> {
                ToastUtil.showShortToast(activity, msg)
                mPresenter!!.toLogin(activity)
            }
            "未登陆" -> {
                ToastUtil.showShortToast(activity, msg)
                mPresenter!!.toLogin(activity)
            }
            "已登出,或在其它设备上登陆!" -> {
                ToastUtil.showShortToast(activity, msg)
                mPresenter!!.alreadyLogin(activity)
            }
        }
    }

    override fun showError() {
        Snackbar.make(payInfo_listView!!, getString(R.string.snack_infor), Snackbar.LENGTH_SHORT).setAction("重试") { mPresenter!!.getData() }.show()
    }


}
