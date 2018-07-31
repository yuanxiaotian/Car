package com.xuliucar.me.ui.fragment

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.View
import com.cangmaomao.lib.action.f_showBigImage
import com.cangmaomao.lib.base.BaseNewFragment
import com.cangmaomao.lib.contacturi.GlideApp
import com.cangmaomao.lib.event.AppEvent
import com.cangmaomao.lib.utils.SPUtils.create
import com.xuliucar.me.R
import com.xuliucar.me.contract.PeopleContract
import com.xuliucar.me.model.PeopleDetails
import com.xuliucar.me.presenter.PeopleDetailsPresenter
import kotlinx.android.synthetic.main.staff_detail.*
import org.greenrobot.eventbus.EventBus

@SuppressLint("SetTextI18n")
class PeopleDetail : BaseNewFragment<PeopleContract.DetailsPresenter>(), PeopleContract.DetailsView {

    override fun layViewId(): Int = R.layout.staff_detail
    override fun addViewId(): Int = 0
    override fun context(): Context = _mActivity
    override fun companyId(): String = create("userInfo").get("compid", "") as String
    override fun loginId(): String = create("userInfo").get("loginid", "") as String
    override fun uuid(): String = arguments!!.getString("uuid")
    override fun itemId(): String = arguments!!.getString("itemId")

    override fun initView(savedInstanceState: Bundle?, view: View) {
        initToolbar("详情", null)
        PeopleDetailsPresenter(this)
        p.start()
    }

    override fun showData(dataBean: PeopleDetails.DataBean) {
        staff_name.text = dataBean.name
        staff_Job_number.text = dataBean.badge
        staff_sex.text = dataBean.sex
        staff_Contact_number.text = "${dataBean.phone}|${dataBean.phone2}"
        staff_idcart.text = dataBean.userid
        staff_email.text = dataBean.email
        staff_mark.text = dataBean.mark
        staff_maincharge.text = dataBean.maincharge
        staff_maincharge_author.text = dataBean.maincharge_author
        staff_maincharge_stime.text = dataBean.maincharge_stime
        staff_sdirector.text = dataBean.sdirector
        staff_sdirector_author.text = dataBean.sdirector_author
        staff_sdirector_stime.text = dataBean.sdirector_stime

        GlideApp.with(this).load(dataBean.p1_thumb).into(staff_idCart_z_img)
        GlideApp.with(this).load(dataBean.p2_thumb).into(staff_principal_img)
        GlideApp.with(this).load(dataBean.p3_thumb).into(staff_Safety_Officer_img)

        staff_idCart_z_img.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("imgUrl", dataBean.p1)
            EventBus.getDefault().post(AppEvent(f_showBigImage, bundle))
        }

        staff_principal_img.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("imgUrl", dataBean.p2)
            EventBus.getDefault().post(AppEvent(f_showBigImage, bundle))
        }

        staff_Safety_Officer_img.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("imgUrl", dataBean.p3)
            EventBus.getDefault().post(AppEvent(f_showBigImage, bundle))
        }
    }

    override fun showFail(e: Throwable) {
    }
}
