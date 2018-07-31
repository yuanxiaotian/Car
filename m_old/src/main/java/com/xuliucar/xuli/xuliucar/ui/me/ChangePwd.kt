package com.xuliucar.xuli.xuliucar.ui.me

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.text.TextUtils
import android.view.View
import com.cangmaomao.lib.base.BaseNewFragment
import com.cangmaomao.lib.base.BasePresenter
import com.xuliucar.xuli.xuliucar.R
import com.xuliucar.xuli.xuliucar.base.App
import com.xuliucar.xuli.xuliucar.mvp.presenter.ChangeLPwdPresenter
import com.xuliucar.xuli.xuliucar.mvp.view.ChangePwdView
import com.xuliucar.xuli.xuliucar.utils.ToastUtil
import kotlinx.android.synthetic.main.change_pwd.*


class ChangePwd : BaseNewFragment<BasePresenter>(), ChangePwdView {
    override fun layViewId(): Int = R.layout.change_pwd
    override fun addViewId(): Int = 0

    override fun initView(savedInstanceState: Bundle?, view: View) {
        initToolbar(getString(R.string.Modify_password), null)
        btn_changePwd.setOnClickListener { doData() }
        mPresenter = ChangeLPwdPresenter(this)
    }


    private var mPresenter: ChangeLPwdPresenter? = null


    private fun doData() {
        val opassword = old_pwd.text.toString()
        val npassword = new_pwd.text.toString()
        val npassword2 = new_pwd_again.text.toString()
        if (TextUtils.isEmpty(opassword)) {
            ToastUtil.showShortToast(_mActivity, "旧密码不能为空")
        } else {
            if (TextUtils.isEmpty(npassword)) {
                ToastUtil.showShortToast(_mActivity, "新密码不能为空")
            } else {
                if (TextUtils.equals(npassword, npassword2)) {
                    mPresenter!!.postChange(App.loginid, App.compid, opassword, npassword)
                } else {
                    ToastUtil.showShortToast(_mActivity, "新密码不一致")
                }
            }
        }
    }

    override fun toastMsg(msg: String) {
        ToastUtil.showShortToast(_mActivity, msg)
        mPresenter!!.toLogin(_mActivity)
    }

    override fun changeError(msg: String) {
        ToastUtil.showShortToast(_mActivity, msg)
    }

    override fun showError() {
        Snackbar.make(btn_changePwd, getString(R.string.snack_infor), Snackbar.LENGTH_SHORT).setAction("重试") { doData() }.show()
    }
}
