package com.xuliucar.xuli.xuliucar.newmvp.view.obsolete

import android.content.Context
import android.os.Bundle
import android.view.View
import com.cangmaomao.lib.action.f_obsoleteTrusteeship
import com.cangmaomao.lib.base.BaseNewFragment
import com.cangmaomao.lib.event.AppEvent
import com.cangmaomao.lib.utils.SPUtils.create
import com.xuliucar.xuli.xuliucar.R
import com.xuliucar.xuli.xuliucar.bean.Counts
import com.xuliucar.xuli.xuliucar.newmvp.contract.ObsoleteItemContract
import com.xuliucar.xuli.xuliucar.newmvp.presenter.obsolete.ObsoleteItemPresenter
import kotlinx.android.synthetic.main.pass_items_index.*
import org.greenrobot.eventbus.EventBus

class ObsoleteItem : BaseNewFragment<ObsoleteItemContract.MainPresenter>(), ObsoleteItemContract.MainView {

    private var bundle: Bundle? = null

    override fun layViewId(): Int = R.layout.pass_items_index
    override fun addViewId(): Int = 0
    override fun context(): Context = _mActivity
    override fun companyId(): String = create("userInfo").get("compid", "") as String
    override fun loginId(): String = create("userInfo").get("loginid", "") as String

    override fun initView(savedInstanceState: Bundle?, view: View) {
        initToolbar(getString(R.string.Overdue_items), null)
        ObsoleteItemPresenter(this)
        p.start()
        od_it_layout.setOnClickListener { toItemFragment(ITEM_1) }
        js_overdue_layout.setOnClickListener { toItemFragment(ITEM_2) }
        ns_overdue_layout.setOnClickListener { toItemFragment(ITEM_3) }
        np_over_layout.setOnClickListener { toItemFragment(ITEM_4) }
        bx_overdue_layout.setOnClickListener { toItemFragment(ITEM_5) }
        hb_overdue_layout.setOnClickListener { toItemFragment(ITEM_6) }
        dlns_overdue_layout.setOnClickListener { toItemFragment(ITEM_8) }
        jsz_overdue_layout.setOnClickListener { toItemFragment(ITEM_9) }
        pass_construction_waste_layout.setOnClickListener { toItemFragment(ITEM_7) }
    }

    override fun toItemFragment(flag: String) {
        if (bundle == null) {
            bundle = Bundle()
        }
        bundle!!.putString("flag", flag)
        EventBus.getDefault().post(AppEvent(f_obsoleteTrusteeship, bundle))
    }

    override fun showFail(e: Throwable) {
    }

    override fun showDate(data: Counts.DataBean.InfoBean) {
        od_it_layout.setVisibility(data.od_it > 0, "${data.od_it}")
        js_overdue_layout.setVisibility(data.od_s > 0, "${data.od_s}")
        ns_overdue_layout.setVisibility(data.od_y > 0, "${data.od_y}")
        np_over_layout.setVisibility(data.od_yt > 0, "${data.od_yt}")
        bx_overdue_layout.setVisibility(data.od_is > 0, "${data.od_is}")
        hb_overdue_layout.setVisibility(data.od_epm > 0, "${data.od_epm}")
        pass_construction_waste_layout.setVisibility(data.od_cw > 0, "${data.od_cw}")
        dlns_overdue_layout.setVisibility(data.od_tpychk > 0, "${data.od_tpychk}")
        jsz_overdue_layout.setVisibility(data.od_dl > 0, "${data.od_dl}")
    }

    companion object {
        val ITEM_1 = "odalt?rtype=od_it"//托管
        val ITEM_2 = "odalt?rtype=od_s"//季审
        val ITEM_3 = "odalt?rtype=od_y"//年审
        val ITEM_4 = "odalt?rtype=od_yt"//年票
        val ITEM_5 = "odalt?rtype=od_is"//保险
        val ITEM_6 = "odalt?rtype=od_epm"//环保标志
        val ITEM_7 = "odalt?rtype=od_cw"//建废标志
        val ITEM_8 = "odalt?rtype=od_tpych"//道路运输证
        val ITEM_9 = "odalt?rtype=od_dl"//驾驶证
    }
}
