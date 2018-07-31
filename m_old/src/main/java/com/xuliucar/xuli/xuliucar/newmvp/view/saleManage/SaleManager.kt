package com.xuliucar.xuli.xuliucar.newmvp.view.saleManage

import android.graphics.Color
import android.os.Bundle
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import android.view.View
import com.cangmaomao.lib.base.BaseNewFragment
import com.cangmaomao.lib.base.BasePresenter
import com.cangmaomao.lib.utils.SPUtils.create
import com.cangmaomao.lib.utils.toast
import com.xuliucar.xuli.xuliucar.R
import com.xuliucar.xuli.xuliucar.bean.SaleManagerBean
import com.xuliucar.xuli.xuliucar.config.AppPower
import com.xuliucar.xuli.xuliucar.mvp.presenter.SaleManagerPresenter
import com.xuliucar.xuli.xuliucar.mvp.view.SaleManageView
import com.xuliucar.xuli.xuliucar.newmvp.view.saleManage.AchieveManage
import com.xuliucar.xuli.xuliucar.newmvp.view.saleManage.ContractManage
import com.xuliucar.xuli.xuliucar.newmvp.view.saleManage.OrderManage
import kotlinx.android.synthetic.main.sale_manager.*


class SaleManager : BaseNewFragment<BasePresenter>(), View.OnClickListener, SaleManageView {


    private var orderManageFra: OrderManage? = null
    private var contractManageFra: ContractManage? = null
    private var achieveManageFra: AchieveManage? = null
    private lateinit var manager: FragmentManager
    private var num = 0
    private lateinit var presenter: SaleManagerPresenter
//    private val powerList = intArrayOf(Integer.parseInt(AppPower.app_pow25), Integer.parseInt(AppPower.app_pow26), Integer.parseInt(AppPower.app_pow27))

    override fun layViewId(): Int = R.layout.sale_manager
    override fun addViewId(): Int = 0
    override fun loginId(): String = create("userInfo").get("loginid", "") as String

    override fun initView(savedInstanceState: Bundle?, view: View) {
        initToolbar(getString(R.string.Sales_management), null)
        manager = childFragmentManager
        om_layout.setOnClickListener(this)
        cm_layout.setOnClickListener(this)
        ps_layout.setOnClickListener(this)

        presenter = SaleManagerPresenter(this)

        presenter.getData()

        selectMenu(0)
    }


    private fun selectMenu(index: Int) {
        val transaction = manager.beginTransaction()

        when (index) {
            0 -> if (AppPower.app_pow25 == "0") {
                toast(resources.getString(R.string.power_tips))
            } else {
                clearSelect()
                hideFragment(transaction)
                om_layout!!.setBackgroundColor(Color.parseColor("#C3DCFD"))
                if (orderManageFra == null) {
                    orderManageFra = OrderManage()
                    transaction.add(R.id.saleManage_Content, orderManageFra)
                } else {
                    transaction.show(orderManageFra)
                }
            }
            1 -> if (AppPower.app_pow26 == "0") {
                toast(resources.getString(R.string.power_tips))
            } else {
                clearSelect()
                hideFragment(transaction)
                cm_layout!!.setBackgroundColor(Color.parseColor("#C3DCFD"))
                if (contractManageFra == null) {
                    contractManageFra = ContractManage()
                    transaction.add(R.id.saleManage_Content, contractManageFra)
                } else {

                    transaction.show(contractManageFra)
                }
            }
            2 -> if (AppPower.app_pow27 == "0") {
                toast(resources.getString(R.string.power_tips))
            } else {
                clearSelect()
                hideFragment(transaction)
                ps_layout!!.setBackgroundColor(Color.parseColor("#C3DCFD"))
                if (achieveManageFra == null) {
                    achieveManageFra = AchieveManage()
                    transaction.add(R.id.saleManage_Content, achieveManageFra)
                } else {
                    transaction.show(achieveManageFra)
                }
            }
        }
        transaction.commit()
    }

    private fun hideFragment(transaction: FragmentTransaction) {
        if (orderManageFra != null) {
            transaction.hide(orderManageFra)
        }
        if (contractManageFra != null) {
            transaction.hide(contractManageFra)
        }
        if (achieveManageFra != null) {
            transaction.hide(achieveManageFra)
        }
    }

    private fun clearSelect() {
        om_layout!!.setBackgroundColor(Color.parseColor("#FFFFFF"))
        cm_layout!!.setBackgroundColor(Color.parseColor("#FFFFFF"))
        ps_layout!!.setBackgroundColor(Color.parseColor("#FFFFFF"))
    }

    override fun onClick(view: View) {
        val i = view.id
        if (i == R.id.om_layout) {
            selectMenu(0)
            num = 0

        } else if (i == R.id.cm_layout) {
            selectMenu(1)
            num = 1

        } else if (i == R.id.ps_layout) {
            selectMenu(2)
            num = 2

        }
    }

    override fun OnSuccess(bean: SaleManagerBean, price: Float, count: Int) {
        sumPrice.text = "${Math.round(price / 10000f) / 100}"
        Total_Ords.text = "$count"
    }

    override fun OnFailure(e: Throwable) {
    }

}
