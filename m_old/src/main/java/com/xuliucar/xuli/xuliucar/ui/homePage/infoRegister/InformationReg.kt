package com.xuliucar.xuli.xuliucar.ui.homePage.infoRegister

import android.os.Bundle
import android.view.View
import com.cangmaomao.lib.action.*
import com.cangmaomao.lib.base.BaseNewFragment
import com.cangmaomao.lib.base.BasePresenter
import com.cangmaomao.lib.config.AppPower
import com.cangmaomao.lib.event.AppEvent
import com.cangmaomao.lib.utils.toast
import com.xuliucar.xuli.xuliucar.R
import kotlinx.android.synthetic.main.information_reg.*
import org.greenrobot.eventbus.EventBus


class InformationReg : BaseNewFragment<BasePresenter>() {

    override fun layViewId(): Int {
        return R.layout.information_reg
    }

    override fun initView(savedInstanceState: Bundle?, view: View) {
        backToolbar()
        setToolbarTitle("信息登记")

        car_reg_layout.setOnClickListener {
            if (AppPower.app_pow2 == "0") toast(getString(R.string.power_tips)) else EventBus.getDefault().post(AppEvent(f_carReg, null))
        }

        people_reg_layout.setOnClickListener {
            if (AppPower.app_pow5 == "0") toast(getString(R.string.power_tips)) else EventBus.getDefault().post(AppEvent(f_peopleReg, null))
        }

        car_owner_reg_layout.setOnClickListener {
            if (AppPower.app_pow3 == "0") toast(getString(R.string.power_tips)) else EventBus.getDefault().post(AppEvent(f_carOwnerReg, null))
        }

        driver_reg_layout.setOnClickListener {
            if (AppPower.app_pow4 == "0") toast(getString(R.string.power_tips)) else EventBus.getDefault().post(AppEvent(f_driverReg, null))
        }

        company_zj_layout.setOnClickListener {
            if (AppPower.app_pow6 == "0") toast(getString(R.string.power_tips)) else EventBus.getDefault().post(AppEvent(f_companyDoc, null))
        }
    }

    override fun addViewId(): Int {
        return 0
    }
}
