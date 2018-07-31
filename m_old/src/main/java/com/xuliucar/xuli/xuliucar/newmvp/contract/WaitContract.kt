package com.xuliucar.xuli.xuliucar.newmvp.contract

import android.content.Context
import com.cangmaomao.lib.base.BasePresenter
import com.cangmaomao.lib.base.BaseView
import com.xuliucar.xuli.xuliucar.bean.Counts
import com.xuliucar.xuli.xuliucar.bean.PassPerPetaueBean

interface WaitContract {

    /**
     * 主
     */
    interface MainView : BaseView<MainPresenter> {

        //获取部分
        fun context(): Context

        fun loginId(): String
        fun companyId(): String

        //显示部分
        fun showFail(e: Throwable)

        fun showDate(data: Counts.DataBean.InfoBean)

        fun toItemFragment(flag: String, appPower: String)

    }

    interface MainPresenter : BasePresenter {
        fun loadData()

        fun disposeData(bean: Counts)
    }

    /**
     * 子公用所有
     */
    interface ChildView : BaseView<ChildPresenter> {

        //获取部分
        fun context(): Context

        fun loginId(): String
        fun companyId(): String

        fun itemFlag(): String

        //显示部分
        fun showFail(e: Throwable)

        fun showDate(data: MutableList<PassPerPetaueBean.DataBean.InfoBean>)

        fun showAllSelect(flag: Boolean = false)

        fun notifyDataSetChanged(position: Int = 0)

    }

    interface ChildPresenter : BasePresenter {
        fun loadData()

        fun disposeData(bean: PassPerPetaueBean)

        fun showCheckBox(data: MutableList<PassPerPetaueBean.DataBean.InfoBean>, flag: Boolean)

        fun allCheck(data: MutableList<PassPerPetaueBean.DataBean.InfoBean>)

        fun cancelCheck(data: MutableList<PassPerPetaueBean.DataBean.InfoBean>, flag: Boolean)

        fun checkUpSelect(data: MutableList<PassPerPetaueBean.DataBean.InfoBean>): Boolean
    }

}