package com.xuliucar.xuli.xuliucar.ui.homePage.passItems

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.widget.Toolbar
import android.view.View
import android.widget.CheckBox
import android.widget.LinearLayout
import android.widget.ListView
import android.widget.ProgressBar
import com.xuliucar.xuli.xuliucar.R
import com.xuliucar.xuli.xuliucar.adapter.DriverLicenseAdapterO
import com.xuliucar.xuli.xuliucar.base.BaseActivity
import com.xuliucar.xuli.xuliucar.bean.DriverLicenseBean
import com.xuliucar.xuli.xuliucar.mvp.presenter.PassDriverLicensePresenter
import com.xuliucar.xuli.xuliucar.mvp.view.DriverLicenseView
import com.xuliucar.xuli.xuliucar.ui.search.PassDriverLicenseS
import com.xuliucar.xuli.xuliucar.utils.ToastUtil

class PassDriverLicense : BaseActivity(), View.OnClickListener, DriverLicenseView {
    private val matter_Group_img: LinearLayout? = null
    private val matter_Group_text: LinearLayout? = null
    private var matter_select_all_layout: LinearLayout? = null
    private var matter_refresh_tip: LinearLayout? = null
    private var matter_send_msg_layout: LinearLayout? = null
    private var matter_refresh_tip_no: LinearLayout? = null
    private var matter_select_all: CheckBox? = null
    private var matter_listView: ListView? = null
    private var progressBar: ProgressBar? = null
    private var commentBeanList: MutableList<DriverLicenseBean.DataBean.InfoBean>? = null
    private var adapter: DriverLicenseAdapterO? = null
    private var starts = 10
    private var ends = 10
    private val size = 10
    private var msg: String? = "1"
    private var mPresenter: PassDriverLicensePresenter? = null
    private var length: Int = 0
    private val pullDown = true
    private val mMatter_toolbar: Toolbar? = null
    private var mTo_search: LinearLayout? = null

    override fun initContentView(savedInstanceState: Bundle) {
        setContentView(R.layout.matter_layout)
        mPresenter = PassDriverLicensePresenter(this)
    }

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
//        mMatter_toolbar!!.setNavigationIcon(R.drawable.back)
//        commentBeanList = ArrayList()
//        matter_refreshLayout!!.setRefresh(this)
//        matter_refreshLayout!!.setRefreshViewHolder(NormalRefreshViewHolder(this, true))
//        matter_refreshLayout!!.setIsShowRefreshingTime(true)
//        mTo_search = findViewById<View>(R.id.to_search) as LinearLayout
//        initData(0)
//        matter_refreshLayout!!.beginRefreshing()
    }

    override fun setListener() {
        super.setListener()
        matter_Group_text!!.setOnClickListener(this)
        matter_Group_img!!.setOnClickListener(this)
        matter_send_msg_layout!!.setOnClickListener(this)
        mMatter_toolbar!!.setNavigationOnClickListener { finish() }
        matter_select_all!!.setOnCheckedChangeListener { compoundButton, b ->
            if (b) {
                adapter!!.selectAll()
            } else {
                adapter!!.disSelectAll()
            }
        }
        mTo_search!!.setOnClickListener {
            val intent = Intent(this@PassDriverLicense, PassDriverLicenseS::class.java)
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
                adapter = DriverLicenseAdapterO(this@PassDriverLicense, commentBeanList, 0)
                matter_listView!!.adapter = adapter
            }
            1 -> {
                adapter = DriverLicenseAdapterO(this@PassDriverLicense, commentBeanList!!, 1, DriverLicenseAdapterO.OnSelectedItemChanged { })
                matter_listView!!.adapter = adapter
            }
        }
    }



    override fun onClick(view: View) {
        val i1 = view.id
//        if (i1 == R.id.matter_send_msg_layout) {
//            val sb = StringBuilder()
//            val phoeList = ArrayList<String>()
//            val idList = ArrayList<String>()
//            for (i in commentBeanList!!.indices) {
//                val phone = PreferencesUtils.getSharePreStr(applicationContext, "GroupMsg", "phone$i")
//                if (!TextUtils.isEmpty(phone) || phone != "null") {
//                    sb.append(phone)
//                    phoeList.add(phone)
//                }
//
//            }
//            val gruopPhone = sb.toString()
//            for (i in commentBeanList!!.indices) {
//                val id = PreferencesUtils.getSharePreStr(applicationContext, "GroupMsg", "id$i")
//                idList.add(id)
//            }
//
//
//            if (gruopPhone == "" || gruopPhone == "null") {
//                ToastUtil.showShortToast(applicationContext, "联系人号码不能为空")
//            } else {
//                PhoneMessage.selectSendStyle(this@PassDriverLicense, phoeList, idList, "车主", "过期", StringConfig.OD_DRIVERLIC + StringConfig.COMPANYNAME, StringConfig.TYPE_DRIVERLIC)
//            }
//
//        }
    }

    override fun startLoad(been: MutableList<DriverLicenseBean.DataBean.InfoBean>, size: Int) {
        this.length = size
//        Handler().postDelayed({
//            commentBeanList!!.addAll(been)
//            adapter!!.notifyDataSetChanged()
//            Pull.pullToRefreshDown(applicationContext, matter_refreshLayout, matter_refresh_tip_no, size)
//        }, 200)
    }

    override fun loadMore(been: MutableList<DriverLicenseBean.DataBean.InfoBean>, size: Int) {
//        Handler().postDelayed({
//            commentBeanList!!.addAll(been)
//            adapter!!.notifyDataSetChanged()
//            Pull.pullToRefreshUp(applicationContext, matter_refreshLayout, matter_refresh_tip, size, starts, ends)
//        }, 200)
    }

    override fun loadAll(been: MutableList<DriverLicenseBean.DataBean.InfoBean>) {
        commentBeanList!!.addAll(been)
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
