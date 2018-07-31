package com.xuliucar.xuli.xuliucar.newmvp.view.messageManage.child

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import com.cangmaomao.lib.base.BaseNewFragment
import com.cangmaomao.lib.base.BasePresenter

import com.google.gson.Gson
import com.xuliucar.xuli.xuliucar.R
import com.xuliucar.xuli.xuliucar.adapter.CustomPeopleAdapter
import com.xuliucar.xuli.xuliucar.base.App
import com.xuliucar.xuli.xuliucar.base.BaseActivity
import com.xuliucar.xuli.xuliucar.bean.CustomPeopleBean
import com.xuliucar.xuli.xuliucar.bean.HistorySendBean
import com.xuliucar.xuli.xuliucar.bean.PeopleBean
import com.xuliucar.xuli.xuliucar.config.CacheName
import com.xuliucar.xuli.xuliucar.newmvp.contract.MessageMangeContract
import com.xuliucar.xuli.xuliucar.newmvp.view.messageManage.MessageManageIndex
import com.xuliucar.xuli.xuliucar.widget.tagView.FlowTagLayout
import com.xuliucar.xuli.xuliucar.widget.tagView.OnTagSelectListener
import com.xuliucar.xuli.xuliucar.ui.search.CustomPeopleS
import com.xuliucar.xuli.xuliucar.utils.PreferencesUtils
import kotlinx.android.synthetic.main.activity_custom_people.*

import java.io.Serializable
import java.util.ArrayList

open class CustomPeople : BaseNewFragment<MessageMangeContract.CustomPeoplePresenter>(), MessageMangeContract.CustomPeopleView {


    override fun showFail(e: Throwable) {
    }

    override fun showDate(list: MutableList<HistorySendBean.DataBean>) {
    }

    override fun layViewId(): Int = R.layout.activity_custom_people
    override fun addViewId(): Int = 0
    override fun context(): Context = _mActivity
    override fun arguments(): Array<out MutableList<PeopleBean.DataBean.Bean>> {
        val list = arguments!!.getSerializable("data")
        if (list != null)
            return list as Array<out MutableList<PeopleBean.DataBean.Bean>>
        else
            throw IllegalArgumentException("参数错误")
    }

    override fun initView(savedInstanceState: Bundle?, view: View) {
        mCustomOwnerTagAdapter = CustomPeopleAdapter(context());
        mCustomDriverTagAdapter = CustomPeopleAdapter(context());
        mCustomUserTagAdapter = CustomPeopleAdapter(context());

        initData()
        setData()

        mCustomOwnerTagAdapter = CustomPeopleAdapter(_mActivity)
        mCustomDriverTagAdapter = CustomPeopleAdapter(_mActivity)
        mCustomUserTagAdapter = CustomPeopleAdapter(_mActivity)

        read_aleady_select.setOnClickListener { alertisSure() }
    }


    private var mCustomOwnerTagAdapter: CustomPeopleAdapter? = null
    private var mCustomDriverTagAdapter: CustomPeopleAdapter? = null
    private var mCustomUserTagAdapter: CustomPeopleAdapter? = null
    private var total_send_count: TextView? = null
    private var ownerCount: Int = 0
    private var driiverCount: Int = 0
    private var userCount: Int = 0
    private lateinit var mOwnerBeanList: MutableList<PeopleBean.DataBean.Bean>
    private lateinit var mDriverBeanList: MutableList<PeopleBean.DataBean.Bean>
    private lateinit var mUserBeanList: MutableList<PeopleBean.DataBean.Bean>
    private lateinit var ownerList: MutableList<PeopleBean.DataBean.Bean>
    private lateinit var driverList: MutableList<PeopleBean.DataBean.Bean>
    private lateinit var userList: MutableList<PeopleBean.DataBean.Bean>
    private var mDialog: Dialog? = null


    private fun alertisSure() {
        val mselectOwnerAdapter = CustomPeopleAdapter(_mActivity)
        val mselectDriverAdapter = CustomPeopleAdapter(_mActivity)
        val mselectUserAdapter = CustomPeopleAdapter(_mActivity)
        val inflater = LayoutInflater.from(_mActivity)
        val view = inflater.inflate(R.layout.alert_select_custom_all_people, null)
        val builder = AlertDialog.Builder(_mActivity)
        builder.setView(view)
        builder.setCancelable(false)
        mDialog = builder.show()
        val selectOwner = view.findViewById<View>(R.id.custom__select_owner_tag) as FlowTagLayout
        val selectDriver = view.findViewById<View>(R.id.custom_select_driver_tag) as FlowTagLayout
        val selectUser = view.findViewById<View>(R.id.custom_select_user_tag) as FlowTagLayout
        val sure_select_btn = view.findViewById<View>(R.id.custom_all_select_btn) as Button
        selectOwner.setTagCheckedMode(FlowTagLayout.FLOW_TAG_CHECKED_MULTI)//多选
        selectDriver.setTagCheckedMode(FlowTagLayout.FLOW_TAG_CHECKED_MULTI)//多选
        selectUser.setTagCheckedMode(FlowTagLayout.FLOW_TAG_CHECKED_MULTI)//多选
        selectOwner.adapter = mselectOwnerAdapter
        selectDriver.adapter = mselectDriverAdapter
        selectUser.adapter = mselectUserAdapter

        selectOwner.setOnTagSelectListener { parent, selectedList, position ->
            ownerList.clear()
            ownerCount = selectedList.size
            val length = selectedList.size + driiverCount + userCount
            total_send_count!!.text = String.format("%s人", length.toString())
            val pos = parent.adapter.getItem(position) as PeopleBean.DataBean.Bean
            custom_people_owners.updataSingle(position)
            for (i in selectedList) {
                val bean = parent.adapter.getItem(i) as PeopleBean.DataBean.Bean
                ownerList.add(bean)
            }
            if (selectedList.size > 0) {
                PreferencesUtils.putSharePre(_mActivity, "CustomPeopleView", "owner", true)
            } else {
                PreferencesUtils.putSharePre(_mActivity, "CustomPeopleView", "owner", false)
            }
        }

        selectDriver.setOnTagSelectListener { parent, selectedList, position ->
            driverList.clear()
            driiverCount = selectedList.size
            val length = selectedList.size + ownerCount + userCount
            total_send_count!!.text = String.format("%s人", length.toString())
            val pos = parent.adapter.getItem(position) as PeopleBean.DataBean.Bean
            custom_people_drivers.updataSingle(position)
            for (i in selectedList) {
                val bean = parent.adapter.getItem(i) as PeopleBean.DataBean.Bean
                driverList.add(bean)
            }
            if (selectedList.size > 0) {
                PreferencesUtils.putSharePre(_mActivity, "CustomPeopleView", "driver", true)
            } else {
                PreferencesUtils.putSharePre(_mActivity, "CustomPeopleView", "driver", false)
            }
        }



        selectUser.setOnTagSelectListener { parent, selectedList, position ->
            userList.clear()
            userCount = selectedList.size
            val length = selectedList.size + ownerCount + driiverCount
            total_send_count!!.text = String.format("%s人", length.toString())
            val pos = parent.adapter.getItem(position) as PeopleBean.DataBean.Bean
            custom_people_users.updataSingle(position)
            for (i in selectedList) {
                val bean = parent.adapter.getItem(i) as PeopleBean.DataBean.Bean
                userList.add(bean)
            }
            if (selectedList.size > 0) {
                PreferencesUtils.putSharePre(_mActivity, "CustomPeopleView", "user", true)
            } else {
                PreferencesUtils.putSharePre(_mActivity, "CustomPeopleView", "user", false)
            }
        }

        mselectOwnerAdapter.onlyAddAll(ownerList)
        selectOwner.selectAllOption()
        mselectDriverAdapter.onlyAddAll(driverList)
        selectDriver.selectAllOption()
        mselectUserAdapter.onlyAddAll(userList)
        selectUser.selectAllOption()

        sure_select_btn.setOnClickListener { mDialog!!.dismiss() }

    }

    private fun setData() {
        custom_people_owners.setTagCheckedMode(FlowTagLayout.FLOW_TAG_CHECKED_MULTI)//多选
        custom_people_drivers.setTagCheckedMode(FlowTagLayout.FLOW_TAG_CHECKED_MULTI)//多选
        custom_people_users.setTagCheckedMode(FlowTagLayout.FLOW_TAG_CHECKED_MULTI)//多选
        custom_people_owners.adapter = mCustomOwnerTagAdapter
        custom_people_drivers.adapter = mCustomDriverTagAdapter
        custom_people_users.adapter = mCustomUserTagAdapter
        //车主
        custom_people_owners.setOnTagSelectListener { parent, selectedList, position ->
            ownerList.clear()
            ownerCount = selectedList.size
            val length = selectedList.size + driiverCount + userCount
            total_send_count!!.text = String.format("%s人", length.toString())
            for (i in selectedList) {
                val ownerBean = parent.adapter.getItem(i) as PeopleBean.DataBean.Bean
                ownerList.add(ownerBean)
            }

            if (selectedList.size > 0) {
                PreferencesUtils.putSharePre(_mActivity, "CustomPeopleView", "owner", true)
            } else {
                PreferencesUtils.putSharePre(_mActivity, "CustomPeopleView", "owner", false)
            }

            custom_people_owners.saveOption(position)
        }
        //司机
        custom_people_drivers.setOnTagSelectListener { parent, selectedList, position ->
            driverList.clear()
            driiverCount = selectedList.size
            val length = selectedList.size + ownerCount + userCount
            total_send_count!!.text = String.format("%s人", length.toString())
            for (i in selectedList) {
                val driverBean = parent.adapter.getItem(i) as PeopleBean.DataBean.Bean
                driverList.add(driverBean)
            }
            if (selectedList.size > 0) {
                PreferencesUtils.putSharePre(_mActivity, "CustomPeopleView", "driver", true)
            } else {
                PreferencesUtils.putSharePre(_mActivity, "CustomPeopleView", "driver", false)
            }
            custom_people_drivers.saveOption(position)
        }
        //员工
        custom_people_users.setOnTagSelectListener { parent, selectedList, position ->
            userList.clear()
            userCount = selectedList.size
            val length = selectedList.size + ownerCount + driiverCount
            total_send_count!!.text = String.format("%s人", length.toString())
            for (i in selectedList) {
                val userBean = parent.adapter.getItem(i) as PeopleBean.DataBean.Bean
                userList.add(userBean)
            }
            if (selectedList.size > 0) {
                PreferencesUtils.putSharePre(_mActivity, "CustomPeopleView", "user", true)
            } else {
                PreferencesUtils.putSharePre(_mActivity, "CustomPeopleView", "user", false)
            }
        }
        mCustomOwnerTagAdapter!!.onlyAddAll(mOwnerBeanList)
        mCustomDriverTagAdapter!!.onlyAddAll(mDriverBeanList)
        mCustomUserTagAdapter!!.onlyAddAll(mUserBeanList)
    }

    fun initData() {
        mOwnerBeanList = arguments()[0]
        mDriverBeanList = arguments()[1]
        mUserBeanList = arguments()[2]
        ownerList = ArrayList()
        driverList = ArrayList()
        userList = ArrayList()
    }


}
