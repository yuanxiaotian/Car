package com.xuliucar.me.ui.fragment

import android.os.Bundle
import android.view.View
import com.cangmaomao.lib.base.BaseNewFragment
import com.cangmaomao.lib.utils.StatusBarUtil
import com.xuliucar.me.R
import com.xuliucar.me.contract.MineContract

class RegFragment : BaseNewFragment<MineContract.Presenter>() {

    override fun layViewId(): Int {
        return R.layout.a_reg
    }

    override fun addViewId(): Int {
        return 0
    }

    override fun initView(savedInstanceState: Bundle?, view: View) {
        setToolbarTitle("注册")
        backToolbar()
    }

}
