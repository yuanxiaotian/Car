package com.cangmaomao.lib.base

import android.os.Bundle
import android.support.v7.widget.AppCompatImageView
import android.support.v7.widget.AppCompatTextView
import android.support.v7.widget.Toolbar
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.tbruyelle.rxpermissions2.RxPermissions
import kotlinx.android.synthetic.main.toolbar_view.*
import me.yokeyword.fragmentation.SupportFragment

@Suppress("UNCHECKED_CAST")
abstract class BaseNewFragment<T : BasePresenter> : SupportFragment(), BaseView<T> {

    lateinit var p: T
    private lateinit var mContainer: View
    lateinit var rxPermissions: RxPermissions

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mContainer = inflater.inflate(layViewId(), container, false)
        return mContainer
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        rxPermissions = RxPermissions(this)
        initView(savedInstanceState)
    }


    //布局ID
    abstract fun layViewId(): Int

    //初始化
    abstract fun initView(savedInstanceState: Bundle?, view: View = mContainer)

    //fragment
    abstract fun addViewId(): Int


    fun setToolbarTitle(str: String, id: AppCompatTextView = toolbar_title) {
        id.text = str
    }

    fun setToolbarSubtitle(str: String, id: AppCompatTextView = toolbar_subtitle) {
        id.text = str
    }

    fun backToolbar(id: Toolbar = c_toolbar) {
        id.setNavigationOnClickListener {
            pop()
        }
    }

    fun initToolbar(title: String?, title2: String?, toolbar: Toolbar = c_toolbar) {
        toolbar_title.text = title
        if (!TextUtils.isEmpty(title2)) {
            toolbar_subtitle.text = title2
        }
        toolbar.setNavigationOnClickListener { pop() }
    }

    fun initToolbar(title: String?, title2: String?, id: Int, toolbar: Toolbar = c_toolbar, imageView: AppCompatImageView = toolbar_search): AppCompatImageView {
        toolbar_title.text = title
        if (!TextUtils.isEmpty(title2)) {
            toolbar_subtitle.text = title2
        }
        imageView.visibility = View.VISIBLE
        imageView.setImageResource(id)
        toolbar.setNavigationOnClickListener { pop() }
        return imageView
    }


    fun hideShowToolbarSearch(flag: Boolean = false, id: AppCompatImageView = toolbar_search): AppCompatImageView {
        id.visibility = if (flag) View.VISIBLE else View.GONE
        return toolbar_search
    }

    override fun setPresenter(presenter: T) {
        p = presenter
    }

    fun <K> initArguments(key: String): K {
        var data: K? = null
        when (data) {
            is String -> data = arguments!!.getString(key) as K
            is Int -> data = arguments!!.getInt(key) as K
            is Float -> data = arguments!!.getFloat(key) as K
            is Byte -> data = arguments!!.getByte(key) as K
            is Char -> data = arguments!!.getChar(key) as K
            is Double -> data = arguments!!.getDouble(key) as K
            is Boolean -> data = arguments!!.getBundle(key) as K
            is MutableList<*> -> arguments!!.getSerializable(key) as K
        }
        return data as K
    }

}