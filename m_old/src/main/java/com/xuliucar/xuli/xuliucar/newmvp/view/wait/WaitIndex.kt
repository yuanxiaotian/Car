package com.xuliucar.xuli.xuliucar.newmvp.view.wait

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import com.cangmaomao.lib.action.f_childWait
import com.cangmaomao.lib.action.f_doPerpetuae
import com.cangmaomao.lib.action.f_doTrusteeship
import com.cangmaomao.lib.action.f_obsoleteTrusteeship
import com.cangmaomao.lib.base.BaseNewFragment
import com.cangmaomao.lib.config.AppPower
import com.cangmaomao.lib.event.AppEvent
import com.cangmaomao.lib.utils.SPUtils.create
import com.cangmaomao.lib.utils.toast
import com.xuliucar.xuli.xuliucar.R
import com.xuliucar.xuli.xuliucar.bean.Counts
import com.xuliucar.xuli.xuliucar.mvp.presenter.DoItemsIndexPresenter
import com.xuliucar.xuli.xuliucar.newmvp.contract.WaitContract
import com.xuliucar.xuli.xuliucar.newmvp.presenter.wait.WaitPresenter
import com.xuliucar.xuli.xuliucar.ui.homePage.doItems.*
import kotlinx.android.synthetic.main.do_item_index.*
import org.greenrobot.eventbus.EventBus


class WaitIndex : BaseNewFragment<WaitContract.MainPresenter>(), WaitContract.MainView {

    private var bundle: Bundle? = null
    override fun layViewId(): Int = R.layout.do_item_index
    override fun addViewId(): Int = 0
    override fun context(): Context = _mActivity
    override fun companyId(): String = create("userInfo").get("compid", "") as String
    override fun loginId(): String = create("userInfo").get("loginid", "") as String

    companion object {
        val ITEM_1 = "odalt?rtype=odt_it"//托管
        val ITEM_2 = "odalt?rtype=odt_s"//季审
        val ITEM_3 = "odalt?rtype=odt_y"//年审
        val ITEM_4 = "odalt?rtype=odt_yt"//年票
        val ITEM_5 = "odalt?rtype=odt_is"//保险
        val ITEM_6 = "odalt?rtype=odt_epm"//环保标志
        val ITEM_7 = "odalt?rtype=odt_cw"//建废标志
        val ITEM_8 = "odalt?rtype=odt_tp"//道路运输证代办
        val ITEM_9 = "odalt?rtype=odt_tpychk"//运输证年审
        val ITEM_10 = "odalt?rtype=odt_dl"//驾驶证
        val ITEM_11 = "odalt?rtype=odt_cl"//公司营业执照
    }

    override fun initView(savedInstanceState: Bundle?, view: View) {
        initToolbar(getString(R.string.Todo), null)
        toTrusteeship_layout.setOnClickListener { toItemFragment(ITEM_1, AppPower.app_pow13) }
        js_layout.setOnClickListener { toItemFragment(ITEM_2, AppPower.app_pow14) }
        ns_layout.setOnClickListener { toItemFragment(ITEM_3, AppPower.app_pow15) }
        np_layout.setOnClickListener { toItemFragment(ITEM_4, AppPower.app_pow16) }
        bx_layout.setOnClickListener { toItemFragment(ITEM_5, AppPower.app_pow17) }
        dldb_layout.setOnClickListener { toItemFragment(ITEM_8, AppPower.app_pow18) }
        dlns_layout.setOnClickListener { toItemFragment(ITEM_9, AppPower.app_pow19) }
        hb_layout.setOnClickListener { toItemFragment(ITEM_6, AppPower.app_pow20) }
        do_construction_waste_layout.setOnClickListener { toItemFragment(ITEM_7, AppPower.app_pow21) }
        jsz_layout.setOnClickListener { toItemFragment(ITEM_10, AppPower.app_pow22) }
        company_layout.setOnClickListener { toItemFragment(ITEM_11, AppPower.app_pow23) }

        WaitPresenter(this)

        p.start()
    }

    override fun showDate(data: Counts.DataBean.InfoBean) {
        toTrusteeship_layout.setVisibility(data.odt_it > 0, "${data.odt_it}")
        js_layout.setVisibility(data.odt_s > 0, "${data.odt_s}")
        ns_layout.setVisibility(data.odt_y > 0, "${data.odt_y}")
        np_layout.setVisibility(data.odt_yt > 0, "${data.odt_yt}")
        bx_layout.setVisibility(data.odt_is > 0, "${data.odt_is}")
        dldb_layout.setVisibility(data.odt_tp > 0, "${data.odt_tp}")
        dlns_layout.setVisibility(data.odt_tpychk > 0, "${data.odt_tpychk}")
        hb_layout.setVisibility(data.odt_epm > 0, "${data.odt_epm}")
        do_construction_waste_layout.setVisibility(data.odt_cw > 0, "${data.odt_cw}")
        jsz_layout.setVisibility(data.odt_dl > 0, "${data.odt_dl}")
        company_layout.setVisibility(data.odt_cl > 0, "${data.odt_cl}")
    }

    override fun toItemFragment(flag: String, appPower: String) {
        if (appPower == "0")
            toast(getString(R.string.power_tips))
        else
            if (bundle == null) {
                bundle = Bundle()
            }
        bundle!!.putString("flag", flag)
        EventBus.getDefault().post(AppEvent(f_childWait, bundle))
    }

    override fun showFail(e: Throwable) {
    }

}
