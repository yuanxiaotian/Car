package com.xuliucar.xuli.xuliucar.ui.homePage.financeManager

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import android.view.View
import com.cangmaomao.lib.base.BaseNewFragment
import com.cangmaomao.lib.base.BasePresenter
import com.cangmaomao.lib.config.AppPower
import com.cangmaomao.lib.utils.toast
import com.xuliucar.xuli.xuliucar.R
import com.xuliucar.xuli.xuliucar.ui.search.*
import com.xuliucar.xuli.xuliucar.utils.ToastUtil
import kotlinx.android.synthetic.main.money_mamager_top.*


class MoneyManager : BaseNewFragment<BasePresenter>() {

    private var inComeDetailFra: InComeDetail? = null
    private var outComeDetailFra: OutComeDetail? = null
    private var statisticsQueryFra: StatisticsQuery? = null
    private var payInfoFra: PayInfo? = null
    private var pendingPayFra: PendingPay? = null
    private var passPayFra: PassPay? = null

    private lateinit var manager: FragmentManager
    private var num: Int = 0
    private val powerList = intArrayOf(Integer.parseInt(AppPower.app_pow7), Integer.parseInt(AppPower.app_pow8), Integer.parseInt(AppPower.app_pow9), Integer.parseInt(AppPower.app_pow10), Integer.parseInt(AppPower.app_pow11), Integer.parseInt(AppPower.app_pow12))


    override fun layViewId(): Int = R.layout.money_manager
    override fun addViewId(): Int = 0

    override fun initView(savedInstanceState: Bundle?, view: View) {
        backToolbar()
        setToolbarTitle(getString(R.string.financial_management))
        hideShowToolbarSearch(true).setOnClickListener {
            when (num) {
                0 -> {
                    val intent = Intent(_mActivity, InComeDetailS::class.java)
                    startActivity(intent)
                }
                1 -> {
                    val intent1 = Intent(_mActivity, OutComeDetailS::class.java)
                    startActivity(intent1)
                }
                2 -> ToastUtil.showShortToast(_mActivity, "当前搜索不可用！")
                3 -> {
                    val intent2 = Intent(_mActivity, PayInfoS::class.java)
                    startActivity(intent2)
                }
                4 -> {
                    val intent3 = Intent(_mActivity, PendingPayS::class.java)
                    startActivity(intent3)
                }
                5 -> {
                    val intent4 = Intent(_mActivity, PassPayS::class.java)
                    startActivity(intent4)
                }
            }
        }
        initLogic()

        Income_detail_layout.setOnClickListener {
            num = 0
            selectMenu(num)
        }
        Expenditure_detail_layout.setOnClickListener {
            num = 1
            selectMenu(num)
        }
        Statistics_query_layout.setOnClickListener {
            num = 2
            selectMenu(num)
        }
        payment_layout.setOnClickListener {
            num = 3
            selectMenu(num)
        }
        Car_loan_layout.setOnClickListener {
            num =4
            selectMenu(num)
        }
        Overdue_car_loan_layout.setOnClickListener {
            num = 5
            selectMenu(num)
        }
    }


    fun initLogic() {
        manager = childFragmentManager
        for (i in powerList.indices) {
            if (powerList[i] == 1) {
                selectMenu(i)
                break
            }
        }

    }

    private fun selectMenu(index: Int) {
        val transaction = fragmentManager!!.beginTransaction()
        when (index) {
            0 -> if (AppPower.app_pow7 == "0") {
                toast(getString(R.string.power_tips))
            } else {
                clearSelect()
                hideFragment(transaction)
                Income_detail_layout!!.setBackgroundColor(Color.parseColor("#C3DCFD"))
                if (inComeDetailFra == null) {
                    inComeDetailFra = InComeDetail()
                    transaction.add(R.id.momeyMan_content, inComeDetailFra)
                } else {
                    transaction.show(inComeDetailFra)
                }
            }
            1 -> if (AppPower.app_pow8 == "0") {
                toast(getString(R.string.power_tips))
            } else {
                clearSelect()
                hideFragment(transaction)
                Expenditure_detail_layout!!.setBackgroundColor(Color.parseColor("#C3DCFD"))
                if (outComeDetailFra == null) {
                    outComeDetailFra = OutComeDetail()
                    transaction.add(R.id.momeyMan_content, outComeDetailFra)
                } else {
                    transaction.show(outComeDetailFra)
                }
            }
            2 -> if (AppPower.app_pow9 == "0") {
                toast(getString(R.string.power_tips))
            } else {
                clearSelect()
                hideFragment(transaction)
                Statistics_query_layout!!.setBackgroundColor(Color.parseColor("#C3DCFD"))
                if (statisticsQueryFra == null) {
                    statisticsQueryFra = StatisticsQuery()
                    transaction.add(R.id.momeyMan_content, statisticsQueryFra)
                } else {
                    transaction.show(statisticsQueryFra)
                }
            }
            3 -> if (AppPower.app_pow10 == "0") {
                toast(getString(R.string.power_tips))
            } else {
                clearSelect()
                hideFragment(transaction)
                payment_layout!!.setBackgroundColor(Color.parseColor("#C3DCFD"))
                if (payInfoFra == null) {
                    payInfoFra = PayInfo()
                    transaction.add(R.id.momeyMan_content, payInfoFra)
                } else {
                    transaction.show(payInfoFra)
                }
            }
            4 -> if (AppPower.app_pow11 == "0") {
                toast(getString(R.string.power_tips))
            } else {
                clearSelect()
                hideFragment(transaction)
                Car_loan_layout!!.setBackgroundColor(Color.parseColor("#C3DCFD"))
                if (pendingPayFra == null) {
                    pendingPayFra = PendingPay()
                    transaction.add(R.id.momeyMan_content, pendingPayFra)
                } else {
                    transaction.show(pendingPayFra)
                }
            }
            5 -> if (AppPower.app_pow12 == "0") {
                toast(getString(R.string.power_tips))
            } else {
                clearSelect()
                hideFragment(transaction)
                Overdue_car_loan_layout!!.setBackgroundColor(Color.parseColor("#C3DCFD"))
                if (passPayFra == null) {
                    passPayFra = PassPay()
                    transaction.add(R.id.momeyMan_content, passPayFra)
                } else {
                    transaction.show(passPayFra)
                }
            }
        }
        transaction.commit()
    }

    private fun hideFragment(transaction: FragmentTransaction) {
        if (inComeDetailFra != null) {
            transaction.hide(inComeDetailFra)
        }
        if (outComeDetailFra != null) {
            transaction.hide(outComeDetailFra)
        }
        if (statisticsQueryFra != null) {
            transaction.hide(statisticsQueryFra)
        }
        if (payInfoFra != null) {
            transaction.hide(payInfoFra)
        }
        if (pendingPayFra != null) {
            transaction.hide(pendingPayFra)
        }
        if (passPayFra != null) {
            transaction.hide(passPayFra)
        }
    }

    private fun clearSelect() {
        Income_detail_layout!!.setBackgroundColor(Color.parseColor("#FFFFFF"))
        Expenditure_detail_layout!!.setBackgroundColor(Color.parseColor("#FFFFFF"))
        Statistics_query_layout!!.setBackgroundColor(Color.parseColor("#FFFFFF"))
        payment_layout!!.setBackgroundColor(Color.parseColor("#FFFFFF"))
        Car_loan_layout!!.setBackgroundColor(Color.parseColor("#FFFFFF"))
        Overdue_car_loan_layout!!.setBackgroundColor(Color.parseColor("#FFFFFF"))
    }

}
