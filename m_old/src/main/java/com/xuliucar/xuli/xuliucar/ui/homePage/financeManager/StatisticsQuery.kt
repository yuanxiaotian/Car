package com.xuliucar.xuli.xuliucar.ui.homePage.financeManager


import android.os.Handler
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.widget.TextView
import com.cangmaomao.lib.utils.SPUtils.create
import com.xuliucar.xuli.xuliucar.R
import com.xuliucar.xuli.xuliucar.base.BaseFragment
import com.xuliucar.xuli.xuliucar.bean.StatisticsBean
import com.xuliucar.xuli.xuliucar.mvp.presenter.StatisticsPresenter
import com.xuliucar.xuli.xuliucar.mvp.view.StatisticsView
import com.xuliucar.xuli.xuliucar.utils.ToastUtil

/**
 * A simple [Fragment] subclass.
 */
class StatisticsQuery : BaseFragment(), StatisticsView {

    private var ti_years: TextView? = null
    private var te_years: TextView? = null
    private var y_profits: TextView? = null
    private var ti_months: TextView? = null
    private var te_months: TextView? = null
    private var m_profits: TextView? = null
    private var instlefts: TextView? = null

    private var mPresenter: StatisticsPresenter? = null


    override fun setContentView() {
        setContentView(R.layout.statistics_query)
    }


    override fun onDestroy() {
        super.onDestroy()
        mPresenter!!.unSubcrible()
    }


    override fun loginId(): String = create("userInfo").get("loginid", "") as String

    override fun initView() {
        mPresenter = StatisticsPresenter(this)
        ti_years = getViewById(R.id.ti_year)
        te_years = getViewById(R.id.te_year)
        y_profits = getViewById(R.id.y_profit)
        ti_months = getViewById(R.id.ti_month)
        te_months = getViewById(R.id.te_month)
        m_profits = getViewById(R.id.m_profit)
        instlefts = getViewById(R.id.instleft)
    }

    override fun setListener() {

    }

    override fun getData(bean: StatisticsBean) {
        val dataBean = bean.data
        ti_years!!.text = dataBean.ti_year.toString()
        te_years!!.text = dataBean.te_month.toString()
        y_profits!!.text = dataBean.y_profit.toString()
        ti_months!!.text = dataBean.ti_month.toString()
        te_months!!.text = dataBean.te_month.toString()
        m_profits!!.text = dataBean.m_profit.toString()
        instlefts!!.text = dataBean.instleft.toString()

    }


    override fun toastMsg(msg: String) {
        when (msg) {
            "already logout" -> {
                ToastUtil.showShortToast(activity, msg)
                mPresenter!!.toLogin(activity)
            }
            "未登陆" -> {
                ToastUtil.showShortToast(activity, msg)
                mPresenter!!.toLogin(activity)
            }
            "已登出,或在其它设备上登陆!" -> {
                ToastUtil.showShortToast(activity, msg)
                mPresenter!!.alreadyLogin(activity)
            }
        }

    }

    override fun showError() {
//        Snackbar.make(swipe_sq!!, getString(R.string.snack_infor), Snackbar.LENGTH_SHORT).setAction("重试") { mPresenter!!.getStatistics() }.show()
    }


}
