package com.cangmaomao.lib.base

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.AppCompatTextView
import android.support.v7.widget.Toolbar
import com.cangmaomao.lib.R
import kotlinx.android.synthetic.main.toolbar_view.*

abstract class BaseNewActivity<T : BasePresenter> : AppCompatActivity(), BaseView<T> {

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

    fun setToolbarTitle(id: AppCompatTextView = toolbar_title, str: String) {
        id.text = str
    }

    fun setToolbarSubtitle(id: AppCompatTextView = toolbar_subtitle, str: String) {
        id.text = str
    }

    fun backToolbar(id: Toolbar = c_toolbar) {
        id.setNavigationOnClickListener { finish() }
    }

    override fun setPresenter(presenter: T) {
        p = presenter
    }
}