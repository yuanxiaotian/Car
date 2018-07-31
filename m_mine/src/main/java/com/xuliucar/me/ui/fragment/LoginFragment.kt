package com.xuliucar.me.ui.fragment

import android.os.Bundle
import android.text.TextUtils
import android.view.View
import com.cangmaomao.lib.action.f_main
import com.cangmaomao.lib.action.f_reg
import com.cangmaomao.lib.base.BaseNewFragment
import com.cangmaomao.lib.event.AppEvent
import com.cangmaomao.lib.utils.SPUtils.create
import com.cangmaomao.lib.utils.StatusBarUtil
import com.cangmaomao.lib.utils.shortToast
import com.cangmaomao.lib.utils.toast
import com.xuliucar.me.R
import com.xuliucar.me.contract.MineContract
import com.xuliucar.me.model.LoginInfo
import com.xuliucar.me.presenter.MinePresenter
import kotlinx.android.synthetic.main.a_login.*
import org.greenrobot.eventbus.EventBus

class LoginFragment : BaseNewFragment<MineContract.Presenter>(), MineContract.LoginView {

    override fun layViewId(): Int  = R.layout.a_login
    override fun addViewId(): Int =0

    override fun initView(savedInstanceState: Bundle?, view: View) {
        MinePresenter(this)
        login.setOnClickListener {
            try {
                p.loginRequest()
            } catch (e: IllegalStateException) {
                _mActivity.toast("错误:${e.message}")
            }
        }
        register.setOnClickListener { EventBus.getDefault().post(AppEvent(f_reg,null)) }
    }

    override fun showRequestInfo(t: LoginInfo) {
        val dataBean = t.data
        _mActivity.create("userInfo").add("compid", dataBean.compid).add("loginid", getAccount())
        EventBus.getDefault().post(AppEvent(f_main,null))
    }

    override fun showRequestErrInfo(msg: String?) {
        _mActivity.toast("异常：$msg")
    }

    override fun getCompany(): String {
        val company = et_company.text.toString()
        return if (TextUtils.isEmpty(company)) throw IllegalStateException("公司名称不能为空!") else company
    }

    override fun getAccount(): String {
        val account = et_account.text.toString()
        return if (TextUtils.isEmpty(account)) throw IllegalStateException("账号参数不能为空!") else account
    }

    override fun getPwd(): String {
        val pwd = et_pwd.text.toString()
        return if (TextUtils.isEmpty(pwd)) throw IllegalStateException("密码参数不能为空!") else pwd
    }

}
