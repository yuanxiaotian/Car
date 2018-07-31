package com.xuliucar.xuli.xuliucar.ui.homePage.financeManager


import android.os.Handler
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.view.View
import android.widget.LinearLayout
import android.widget.ListView
import android.widget.ProgressBar
import com.cangmaomao.lib.utils.SPUtils.create
import com.xuliucar.xuli.xuliucar.R
import com.xuliucar.xuli.xuliucar.adapter.OutComeAdapter
import com.xuliucar.xuli.xuliucar.base.BaseFragment
import com.xuliucar.xuli.xuliucar.bean.OutComeBean
import com.xuliucar.xuli.xuliucar.mvp.presenter.OutComePresenter
import com.xuliucar.xuli.xuliucar.mvp.view.OutComeView
import com.xuliucar.xuli.xuliucar.utils.Pull
import com.xuliucar.xuli.xuliucar.utils.ToastUtil
import java.util.*

/**
 * A simple [Fragment] subclass.
 */
class OutComeDetail : BaseFragment(), OutComeView {
    override fun companyId(): String = create("userInfo").get("compid", "") as String

    override fun loginId(): String = create("userInfo").get("loginid", "") as String


    private var outCome_listView: ListView? = null
    private var outCome_refresh_tip: LinearLayout? = null
    private var outCome_refresh_tip_no: LinearLayout? = null
    private var progressBar: ProgressBar? = null
    private var adapter: OutComeAdapter? = null
    private var starts: Int = 0
    private var ends: Int = 0
    private var mDataBeanList: MutableList<OutComeBean.DataBean>? = null
    private var mPresenter: OutComePresenter? = null
    private var length: Int = 0

    override fun setContentView() {
        setContentView(R.layout.out_come_detail)
    }


    override fun onDestroy() {
        super.onDestroy()
        mPresenter!!.unSubcrible()
    }


    override fun initRefresh() {
        super.initRefresh()
    }

    override fun initView() {
        mPresenter = OutComePresenter(this)
        outCome_listView = getViewById(R.id.outCome_listView)
        outCome_refresh_tip = getViewById(R.id.outCome_refresh_tip)
        outCome_refresh_tip_no = getViewById(R.id.outCome_refresh_tip_no)
        progressBar = getViewById(R.id.outCome_progressBar)
        progressBar!!.visibility = View.GONE
        mDataBeanList = ArrayList()
        adapter = OutComeAdapter(activity, mDataBeanList)
        outCome_listView!!.adapter = adapter
    }



    override fun startLoad(been: List<OutComeBean.DataBean>, size: Int) {
//        this.length = size
//        mDataBeanList!!.addAll(been)
//        adapter!!.notifyDataSetChanged()
//        Pull.pullToRefreshDown(activity, outCome_refreshLayout, outCome_refresh_tip_no, size)

    }

    override fun loadMore(been: List<OutComeBean.DataBean>, size: Int) {
//        Handler().postDelayed({
//            mDataBeanList!!.addAll(been)
//            adapter!!.notifyDataSetChanged()
//            Pull.pullToRefreshUp(activity, outCome_refreshLayout, outCome_refresh_tip, size, starts, ends)
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
//        outCome_refreshLayout!!.endRefreshing()
//        Snackbar.make(outCome_listView!!, getString(R.string.snack_infor), Snackbar.LENGTH_SHORT).setAction("重试") { mPresenter!!.getData() }.show()
    }


}
