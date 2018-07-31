package com.cangmaomao.lib.base

import android.os.Bundle
import android.support.v7.widget.AppCompatImageView
import android.support.v7.widget.Toolbar
import android.text.TextUtils
import android.view.View
import kotlinx.android.synthetic.main.toolbar_view.*
import me.yokeyword.fragmentation.SupportActivity

abstract class BaseActivity<T : BasePresenter> : SupportActivity(), BaseView<T> {


    lateinit var p: T

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layViewId())
        initView()
    }


    //布局ID
    abstract fun layViewId(): Int

    //初始化
    abstract fun initView()

    //fragment
    abstract fun addViewId(): Int

    override fun setPresenter(presenter: T) {
        p = presenter
    }

    fun initToolbar(title: String?, title2: String?, toolbar: Toolbar = c_toolbar) {
        toolbar_title.text = title
        if (!TextUtils.isEmpty(title2)) {
            toolbar_subtitle.text = title2
        }
        toolbar.setNavigationOnClickListener { finish() }
    }

    fun initToolbar(title: String?, title2: String?, id: Int, toolbar: Toolbar = c_toolbar, imageView: AppCompatImageView = toolbar_search): AppCompatImageView {
        toolbar_title.text = title
        if (!TextUtils.isEmpty(title2)) {
            toolbar_subtitle.text = title2
        }
        imageView.visibility = View.VISIBLE
        imageView.setImageResource(id)
        toolbar.setNavigationOnClickListener { finish() }
        return imageView
    }


}