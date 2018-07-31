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
import com.xuliucar.xuli.xuliucar.adapter.InComeAdapter
import com.xuliucar.xuli.xuliucar.base.BaseFragment
import com.xuliucar.xuli.xuliucar.bean.InComeBean
import com.xuliucar.xuli.xuliucar.mvp.presenter.InComePresenter
import com.xuliucar.xuli.xuliucar.mvp.view.InComeView
import com.xuliucar.xuli.xuliucar.utils.Pull
import com.xuliucar.xuli.xuliucar.utils.ToastUtil
import java.util.*

/**
 * A simple [Fragment] subclass.
 */
class InComeDetail : BaseFragment(), InComeView {
    override fun companyId(): String= create("userInfo").get("compid", "") as String

    override fun loginId(): String =create("userInfo").get("loginid", "") as String

    private var inCome_listView: ListView? = null
    private var inCome_refresh_tip: LinearLayout? = null
    private var inCome_refresh_tip_no: LinearLayout? = null
    private var progressBar: ProgressBar? = null
    private var mDataBeanList: MutableList<InComeBean.DataBean>? = null
    private var adapter: InComeAdapter? = null
    private var starts: Int = 0
    private var ends: Int = 0
    private val size = 10
    private var mInComePresenter: InComePresenter? = null
    private var length: Int = 0


    override fun onDestroy() {
        super.onDestroy()
        mInComePresenter!!.unSubcrible()
    }

    override fun setContentView() {
        setContentView(R.layout.in_come_detail)
    }

    override fun initRefresh() {
        super.initRefresh()
//        inComeDetail_refreshLayout = getViewById(R.id.inComeDetail_refreshLayout)
//        inComeDetail_refreshLayout!!.setRefresh(this)
//        inComeDetail_refreshLayout!!.setRefreshViewHolder(NormalRefreshViewHolder(activity, true))
//        inComeDetail_refreshLayout!!.setIsShowRefreshingTime(true)
    }


    override fun initView() {
        mInComePresenter = InComePresenter(this)
        inCome_listView = getViewById(R.id.inCome_listView)
        inCome_refresh_tip = getViewById(R.id.inCome_refresh_tip)
        inCome_refresh_tip_no = getViewById(R.id.inCome_refresh_tip_no)
        progressBar = getViewById(R.id.inCome_progressBar)
        progressBar!!.visibility = View.GONE
        mDataBeanList = ArrayList()
        adapter = InComeAdapter(activity, mDataBeanList)
        inCome_listView!!.adapter = adapter
//        inComeDetail_refreshLayout!!.beginRefreshing()
    }

//    override fun onRefreshLayoutBeginRefreshing(refreshLayout: RefreshLayout) {
//        Handler().postDelayed({
//            mDataBeanList!!.clear()
//            mInComePresenter!!.getInCome()
//            mInComePresenter!!.startLoad(size)
//            starts = 10
//            ends = 10
//        }, 500)
//        /**
//         * 每次下拉都要初始化监听不然每次下拉的时候在最后一页的上拉中，没拉到底就会提示“没有更多数据了”
//         */
//        inComeDetail_refreshLayout!!.setOnLastListViewItemVisibleListener(null)
//    }
//
//    override fun onRefreshLayoutBeginLoadingMore(refreshLayout: RefreshLayout): Boolean {
//        if (ends >= length) {
//            return false
//        } else {
//            starts = ends
//            ends = ends + 20
//            mInComePresenter!!.loadMore(starts, ends)
//        }
//        return true
//    }


    override fun startLoad(been: List<InComeBean.DataBean>, size: Int) {
        this.length = size
//        mDataBeanList!!.addAll(been)
//        adapter!!.notifyDataSetChanged()
//        Pull.pullToRefreshDown(activity, inComeDetail_refreshLayout, inCome_refresh_tip_no, size)

    }

    override fun loadMore(been: List<InComeBean.DataBean>, size: Int) {
//        Handler().postDelayed({
//            mDataBeanList!!.addAll(been)
//            Pull.pullToRefreshUp(activity, inComeDetail_refreshLayout, inCome_refresh_tip, size, starts, ends)
//            adapter!!.notifyDataSetChanged()
//        }, 100)


    }

    override fun toastMsg(msg: String) {
        when (msg) {
            "already logout" -> {
                ToastUtil.showShortToast(activity, msg)
                mInComePresenter!!.toLogin(activity)
            }
            "未登陆" -> {
                ToastUtil.showShortToast(activity, msg)
                mInComePresenter!!.toLogin(activity)
            }
            "已登出,或在其它设备上登陆!" -> {
                ToastUtil.showShortToast(activity, msg)
                mInComePresenter!!.alreadyLogin(activity)
            }
        }
    }

    override fun showError() {
//        inComeDetail_refreshLayout!!.endRefreshing()
//        Snackbar.make(inCome_listView!!, getString(R.string.snack_infor), Snackbar.LENGTH_SHORT).setAction("重试") { mInComePresenter!!.getInCome() }.show()
    }


}
