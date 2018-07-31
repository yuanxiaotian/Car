package com.xuliucar.xuli.xuliucar.ui.homePage.illegalQuery

import android.os.Bundle
import android.view.View
import com.cangmaomao.lib.base.BaseNewFragment
import com.cangmaomao.lib.base.BasePresenter
import com.xuliucar.xuli.xuliucar.R
import com.xuliucar.xuli.xuliucar.config.AppPower
import com.xuliucar.xuli.xuliucar.utils.ToastUtil
import kotlinx.android.synthetic.main.illegal_index.*


class IllegalIndex : BaseNewFragment<BasePresenter>(), View.OnClickListener {

    override fun layViewId(): Int {
        return R.layout.illegal_index
    }

    override fun initView(savedInstanceState: Bundle?, view: View) {

        illegal_info.setOnClickListener(this)
        illegal_self_query.setOnClickListener(this)
        illegal_my_order.setOnClickListener(this)
        price_difference_record.setOnClickListener(this)
        price_difference_record.setOnClickListener(this)

        backToolbar()
        setToolbarTitle(getString(R.string.illegal_query))
    }

    override fun addViewId(): Int {
        return 0
    }


    override fun onClick(v: View) {
        val i = v.id
        if (i == R.id.illegal_info) {
            if (AppPower.app_pow34 == "0") {
                ToastUtil.showShortToast(_mActivity, resources.getString(R.string.power_tips))
            } else {
                //                toIntentWithNoClick(illegalInfo, IllegalInfoList.class);
            }

        } else if (i == R.id.illegal_self_query) {
            if (AppPower.app_pow35 == "0") {
                ToastUtil.showShortToast(_mActivity, resources.getString(R.string.power_tips))
            } else {
                //                toIntentWithNoClick(illegalSelfQuery, AutoQuery.class);
            }

        } else if (i == R.id.illegal_my_order) {
            if (AppPower.app_pow36 == "0") {
                ToastUtil.showShortToast(_mActivity, resources.getString(R.string.power_tips))
            } else {
                //                toIntentWithNoClick(illegalMyOrder, IllegalOrder.class);
            }

        } else if (i == R.id.price_difference_record) {
            if (AppPower.app_pow37 == "0") {
                ToastUtil.showShortToast(_mActivity, resources.getString(R.string.power_tips))
            } else {
                //                toIntentWithNoClick(priceDifferenceRecord, PricesRecord.class);
            }

        } else if (i == R.id.save_record) {
            if (AppPower.app_pow38 == "0") {
                ToastUtil.showShortToast(_mActivity, resources.getString(R.string.power_tips))
            } else {
                //                toIntentWithNoClick(saveRecord, SaveRecord.class);
            }


        }
    }


}
