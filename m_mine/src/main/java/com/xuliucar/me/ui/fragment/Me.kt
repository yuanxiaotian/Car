package com.xuliucar.me.ui.fragment

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.view.View
import com.cangmaomao.lib.action.*
import com.cangmaomao.lib.base.BaseNewFragment
import com.cangmaomao.lib.config.AppPower
import com.cangmaomao.lib.config.REQUEST_CAMERA_PERMISSION
import com.cangmaomao.lib.event.AppEvent
import com.cangmaomao.lib.event.MainEvent
import com.cangmaomao.lib.permission.PermissionUtils
import com.cangmaomao.lib.utils.SPUtils.create
import com.cangmaomao.lib.utils.toast
import com.xuliucar.me.R
import com.xuliucar.me.contract.MeContract
import com.xuliucar.me.model.CompanyInfo
import com.xuliucar.me.model.MeBean
import com.xuliucar.me.presenter.MePresenter
import kotlinx.android.synthetic.main.me.*
import org.greenrobot.eventbus.EventBus

/**
 * Created by skyward on 2016/6/29.
 */
class Me : BaseNewFragment<MeContract.Presenter>(), MeContract.View {
    override fun layViewId(): Int = R.layout.me
    override fun addViewId(): Int = 0
    override fun context(): Context = _mActivity
    override fun companyId(): String = create("userInfo").get("compid", "") as String
    override fun loginId(): String = create("userInfo").get("loginid", "") as String


    override fun initView(savedInstanceState: Bundle?, view: View) {
        MePresenter(this)
        p.start()
        tv_info_c.setOnClickListener { EventBus.getDefault().post(AppEvent(f_informationCenter, null)) }
        tv_pwd.setOnClickListener { EventBus.getDefault().post(AppEvent(f_changePwd, null)) }
        tv_feedback.setOnClickListener { EventBus.getDefault().post(AppEvent(f_feedBack, null)) }
        tv_logout.setOnClickListener { exitLogin() }

        user.setOnClickListener { toItemFragment(AppPower.app_pow31, f_peopleList, ITEM_1) }
        driver.setOnClickListener { toItemFragment(AppPower.app_pow32, f_peopleList, ITEM_2) }
        owner.setOnClickListener { toItemFragment(AppPower.app_pow33, f_peopleList, ITEM_3) }
    }


    override fun toItemFragment(appPower: String, fragmentId: Int, itemId: String) {
        if (appPower == "0") {
            toast(getString(R.string.power_tips))
        } else {
            val bundle = Bundle()
            bundle.putString("id", itemId)
            EventBus.getDefault().post(AppEvent(fragmentId, bundle))
        }
    }

    override fun exitLogin() {
        val builder = AlertDialog.Builder(context())
        builder.setMessage("确认退出登陆吗？")
        builder.setTitle("温馨提醒")
        builder.setPositiveButton("确认") { dialogInterface, _ ->
            dialogInterface.dismiss()
            EventBus.getDefault().post(MainEvent(0))
            create("userInfo").removeAll()
            EventBus.getDefault().post(AppEvent(f_login, null))
        }
        builder.setNegativeButton("取消") { dialogInterface, _ -> dialogInterface.dismiss() }
        builder.create().show()
    }

    @SuppressLint("SetTextI18n")
    override fun showData(data: MeBean.DataBean) {
        user.text = "员工(${data.user_count})"
        driver.text = "司机(${data.driver_count})"
        owner.text = "车主(${data.owner_count})"
        company_carNum.text = "车辆总数：(${data.total_count})"
    }

    @SuppressLint("SetTextI18n")
    override fun showCompanyInfo(data: CompanyInfo.DataBean) {
        company_name.text = data.compname
        company_info.text = "公司性质：${data.compoptype}"
        when (data.userp) {
            "1" -> userp.text = "(管理员)"
            "2" -> userp.text = "(非管理员)"
            "3" -> userp.text = "(销售人员)"
            "10" -> userp.text = "(超级管理员)"
        }
    }

    override fun showFail(e: Throwable) {
    }

    companion object {
        val ITEM_1 = "user_list?utype=user"//人员
        val ITEM_2 = "user_list?utype=driver"//司机
        val ITEM_3 = "user_list?utype=owner"//车主
    }

}
