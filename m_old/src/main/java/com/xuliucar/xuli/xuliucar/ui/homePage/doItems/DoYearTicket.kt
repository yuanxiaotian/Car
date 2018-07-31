package com.xuliucar.xuli.xuliucar.ui.homePage.doItems


import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.widget.Toolbar
import android.view.View
import android.widget.*
import com.xuliucar.xuli.xuliucar.R
import com.xuliucar.xuli.xuliucar.adapter.YearTickAdapter
import com.xuliucar.xuli.xuliucar.base.BaseActivity
import com.xuliucar.xuli.xuliucar.bean.YearTicketBean
import com.xuliucar.xuli.xuliucar.mvp.presenter.DoYearTicketPresenter
import com.xuliucar.xuli.xuliucar.mvp.view.DoYearTicketView
import com.xuliucar.xuli.xuliucar.ui.homePage.allCar.CarDetail
import com.xuliucar.xuli.xuliucar.ui.search.DoYearTicketS
import com.xuliucar.xuli.xuliucar.utils.ToastUtil


class DoYearTicket : BaseActivity(), AdapterView.OnItemClickListener, View.OnClickListener, DoYearTicketView {
    private val matter_Group_img: View? = null
    private val matter_Group_text: View? = null
    private var matter_select_all_layout: View? = null
    private var matter_send_msg_layout: View? = null
    private var matter_select_all: CheckBox? = null
    private var matter_listView: ListView? = null
    private var matter_refresh_tip: LinearLayout? = null
    private var matter_refresh_tip_no: LinearLayout? = null
    private var progressBar: ProgressBar? = null
    private var adapter: YearTickAdapter? = null
    private var starts = 10
    private var ends = 10
    private val size = 10
    private var msg: String? = "1"
    private var mInfoBeanList: MutableList<YearTicketBean.DataBean.InfoBean>? = null
    private var mPresenter: DoYearTicketPresenter? = null
    private var length: Int = 0
    private val pullDown = true
    private var mTo_search: LinearLayout? = null
    private val mMatter_toolbar: Toolbar? = null


    override fun initContentView(savedInstanceState: Bundle) {
        setContentView(R.layout.matter_layout)
        mPresenter = DoYearTicketPresenter(this)
    }

    //如果是发短信返回来就清空所有选项的状态
    override fun onResume() {
        super.onResume()
        if (msg == "") {
            initView()
            matter_select_all_layout!!.visibility = View.GONE
            matter_Group_text!!.visibility = View.GONE
            matter_Group_img!!.visibility = View.VISIBLE
            matter_send_msg_layout!!.visibility = View.GONE
            matter_select_all!!.isChecked = false
        }

    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1001 && resultCode == 1002) {
            val bundle = data.extras
            msg = bundle!!.getString("msg")
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mPresenter!!.unSubcrible()
    }

    override fun initView() {
//        matter_refreshLayout = getViewByID(R.id.matter_refreshLayout)
//        matter_select_all_layout = getViewByID(R.id.matter_select_all_layout)
//        matter_select_all = getViewByID(R.id.matter_select_all)
//        matter_listView = getViewByID(R.id.matter_listView)
//        mTo_search = getViewByID(R.id.to_search)
//        mMatter_toolbar!!.setNavigationIcon(R.drawable.back)
//        matter_refreshLayout!!.setRefresh(this)
//        matter_refreshLayout!!.setRefreshViewHolder(NormalRefreshViewHolder(this, true))
//        matter_refreshLayout!!.setIsShowRefreshingTime(true)
//        mInfoBeanList = ArrayList()
//        initData(0)
//        matter_refreshLayout!!.beginRefreshing()
    }

    override fun setListener() {
        super.setListener()
        matter_Group_text!!.setOnClickListener(this)
        matter_Group_img!!.setOnClickListener(this)
        matter_send_msg_layout!!.setOnClickListener(this)
        matter_listView!!.onItemClickListener = this
        mMatter_toolbar!!.setNavigationOnClickListener { finish() }
        matter_select_all!!.setOnCheckedChangeListener { compoundButton, b ->
            if (b) {
                adapter!!.selectAll()
            } else {
                adapter!!.disSelectAll()
            }
        }

        mTo_search!!.setOnClickListener {
            val intent = Intent(this@DoYearTicket, DoYearTicketS::class.java)
            startActivity(intent)
        }
    }

    override fun initLogic() {
        super.initLogic()
        matter_select_all_layout!!.visibility = View.GONE
        matter_Group_text!!.visibility = View.GONE
        matter_Group_img!!.visibility = View.VISIBLE
        matter_send_msg_layout!!.visibility = View.GONE
        adapter!!.disSelectAll()
        matter_select_all!!.isChecked = false
    }

    private fun initData(index: Int) {
        when (index) {
            0 -> {
                adapter = YearTickAdapter(this@DoYearTicket, mInfoBeanList, 0)
                matter_listView!!.adapter = adapter
            }
            1 -> {
                adapter = YearTickAdapter(this@DoYearTicket, mInfoBeanList!!, 1, YearTickAdapter.OnSelectedItemChanged { })
                matter_listView!!.adapter = adapter
            }
        }
    }

//    override fun onRefreshLayoutBeginRefreshing(refreshLayout: RefreshLayout) {
//        Handler().postDelayed({
//            mInfoBeanList!!.clear()
//            mPresenter!!.getData()
//            mPresenter!!.startLoad(size)
//            ends = 10
//            starts = ends
//        }, 500)
//        matter_refreshLayout!!.setOnLastListViewItemVisibleListener(null)
//    }
//
//    override fun onRefreshLayoutBeginLoadingMore(refreshLayout: RefreshLayout): Boolean {
//        if (ends >= length) {
//            return false
//        } else {
//            starts = ends
//            ends = ends + 10
//            mPresenter!!.loadMore(starts, ends)
//        }
//        return pullDown
//    }


    override fun onItemClick(adapterView: AdapterView<*>, view: View, i: Int, l: Long) {
        val commonBean = mInfoBeanList!![i]
        val intent = Intent(view.context, CarDetail::class.java)
        val bundle = Bundle()
        bundle.putSerializable("yearticketData", commonBean)
        bundle.putString("page", "0")
        bundle.putString("pageName", "yearticket")
        intent.putExtras(bundle)
        toIntent(view, intent, 1001)
    }

    override fun onClick(view: View) {
        val i1 = view.id
//        if (i1 == R.id.matter_send_msg_layout) {
//            val sb = StringBuilder()
//            val phoeList = ArrayList<String>()
//            val idList = ArrayList<String>()
//            for (i in mInfoBeanList!!.indices) {
//                val phone = PreferencesUtils.getSharePreStr(applicationContext, "GroupMsg", "phone$i")
//                if (!TextUtils.isEmpty(phone) || phone != "null") {
//                    sb.append(phone)
//                    phoeList.add(phone)
//                }
//
//            }
//            val gruopPhone = sb.toString()
//            for (i in mInfoBeanList!!.indices) {
//                val id = PreferencesUtils.getSharePreStr(applicationContext, "GroupMsg", "id$i")
//                idList.add(id)
//            }
//
//            if (gruopPhone == "" || gruopPhone == "null") {
//                ToastUtil.showShortToast(applicationContext, "联系人号码不能为空")
//            } else {
//                PhoneMessage.selectSendStyle(this@DoYearTicket, phoeList, idList, "车主", "临期", StringConfig.ODT_YEARTICKT + StringConfig.COMPANYNAME, StringConfig.TYPE_YEARTICKT)
//            }
//
//
//        }
    }

    override fun startLoad(been: MutableList<YearTicketBean.DataBean.InfoBean>, size: Int) {
        this.length = size
//        Handler().postDelayed({
//            mInfoBeanList!!.addAll(been)
//            adapter!!.notifyDataSetChanged()
//            Pull.pullToRefreshDown(applicationContext, matter_refreshLayout, matter_refresh_tip_no, size)
//        }, 200)
    }

    override fun loadMore(been: MutableList<YearTicketBean.DataBean.InfoBean>, size: Int) {
//        Handler().postDelayed({
//            mInfoBeanList!!.addAll(been)
//            adapter!!.notifyDataSetChanged()
//            Pull.pullToRefreshUp(applicationContext, matter_refreshLayout, matter_refresh_tip, size, starts, ends)
//        }, 200)
    }

    override fun loadAll(been: MutableList<YearTicketBean.DataBean.InfoBean>) {
        mInfoBeanList!!.addAll(been)
        adapter!!.notifyDataSetChanged()
    }

    override fun toastMsg(msg: String) {
        when (msg) {
            "already logout" -> {
                ToastUtil.showShortToast(applicationContext, msg)
                mPresenter!!.toLogin(applicationContext)
            }
            "未登陆" -> {
                ToastUtil.showShortToast(applicationContext, msg)
                mPresenter!!.toLogin(applicationContext)
            }
            "已登出,或在其它设备上登陆!" -> {
                ToastUtil.showShortToast(applicationContext, msg)
                mPresenter!!.alreadyLogin(applicationContext)
            }
        }
    }

    override fun showError() {
        Snackbar.make(matter_listView!!, getString(R.string.snack_infor), Snackbar.LENGTH_SHORT).setAction("重试") { mPresenter!!.getData() }.show()
    }


}
