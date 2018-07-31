package com.xuliucar.me.ui.fragment

import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import com.cangmaomao.lib.base.BaseNewFragment
import com.cangmaomao.lib.base.BasePresenter
import com.cangmaomao.lib.contacturi.GlideApp
import com.xuliucar.me.R
import kotlinx.android.synthetic.main.show_big_image.*

class ShowBigImage : BaseNewFragment<BasePresenter>() {

    override fun layViewId(): Int = R.layout.show_big_image

    override fun initView(savedInstanceState: Bundle?, view: View) {

        _mActivity.window.setBackgroundDrawable(ColorDrawable(_mActivity.resources.getColor(R.color.c_t_2c_99)))

        GlideApp.with(this).load(arguments!!.getString("imgUrl")).into(imageView)
        imageView.setOnClickListener { pop() }
    }

    override fun addViewId(): Int = 0


}