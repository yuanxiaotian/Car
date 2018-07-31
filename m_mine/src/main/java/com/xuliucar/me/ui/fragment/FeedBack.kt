package com.xuliucar.me.ui.fragment

import android.content.Context
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import com.cangmaomao.lib.base.BaseNewFragment
import com.cangmaomao.lib.utils.SPUtils.create
import com.cangmaomao.lib.utils.toast
import com.xuliucar.me.R
import com.xuliucar.me.contract.FeedBackContract
import com.xuliucar.me.model.MessageBean
import com.xuliucar.me.presenter.FeedBackPresenter
import kotlinx.android.synthetic.main.feed_back.*


class FeedBack : BaseNewFragment<FeedBackContract.Presenter>(), FeedBackContract.View {

    override fun layViewId(): Int = R.layout.feed_back
    override fun addViewId(): Int = 0
    override fun context(): Context = _mActivity
    override fun companyId(): String = create("userInfo").get("compid", "") as String
    override fun loginId(): String = create("userInfo").get("loginid", "") as String

    override fun initView(savedInstanceState: Bundle?, view: View) {
        initToolbar(getString(R.string.feedback), null)

        FeedBackPresenter(this)

        bt_submit.setOnClickListener {
            try {
                p.start()
            } catch (e: IllegalStateException) {
                toast(e.message!!)
            }
        }
    }

    override fun showFail(e: Throwable) {
    }

    override fun showData(data: MessageBean) {
        toast(data.message)
    }

    override fun name(): String {
        val it = feed_back_name.text.toString()
        if (!TextUtils.isEmpty(it)) return it else throw IllegalStateException("账号参数不能为空!")
    }

    override fun phone(): String {
        val it = feed_back_phone.text.toString()
        if (!TextUtils.isEmpty(it)) return it else throw IllegalStateException("账号参数不能为空!")
    }

    override fun content(): String {
        val it = feed_back_content.text.toString()
        if (!TextUtils.isEmpty(it)) return it else return ""
    }
}
