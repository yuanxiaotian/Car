package com.xuliucar.xuli.xuliucar.newmvp.contract

import android.content.Context
import com.cangmaomao.lib.base.BasePresenter
import com.cangmaomao.lib.base.BaseView
import com.xuliucar.xuli.xuliucar.bean.AchieveManageBean
import com.xuliucar.xuli.xuliucar.bean.ContractMDetailBean
import com.xuliucar.xuli.xuliucar.bean.ContractManageBean
import com.xuliucar.xuli.xuliucar.bean.OrderManageBean
import com.xuliucar.xuli.xuliucar.newmvp.bean.OrderDetails

interface SaleMangeContract {

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

        fun showDate(list: MutableList<OrderManageBean.DataBean>)

    }

    interface MainPresenter : BasePresenter {
        fun loadData()

        fun disposeData(bean: OrderManageBean)
    }


    /**
     * 订单
     */
    interface OrderView : BaseView<OrderPresenter> {

        //获取部分
        fun context(): Context

        fun loginId(): String
        fun companyId(): String

        //显示部分
        fun showFail(e: Throwable)

        fun showDate(list: MutableList<OrderManageBean.DataBean>)

    }

    interface OrderPresenter : BasePresenter {
        fun loadData()

        fun disposeData(bean: OrderManageBean)
    }


    /**
     * 合同
     */
    interface ContractView : BaseView<ContractPresenter> {

        //获取部分
        fun context(): Context

        fun loginId(): String
        fun companyId(): String

        //显示部分
        fun showFail(e: Throwable)

        fun showDate(list: MutableList<ContractManageBean.DataBean>)

    }

    interface ContractPresenter : BasePresenter {
        fun loadData()

        fun disposeData(bean: ContractManageBean)
    }


    /**
     * 业绩
     */
    interface AchievementView : BaseView<AchievementPresenter> {

        //获取部分
        fun context(): Context

        fun loginId(): String
        fun companyId(): String

        //显示部分
        fun showFail(e: Throwable)

        fun showDate(list: MutableList<AchieveManageBean.DataBean>)

    }

    interface AchievementPresenter : BasePresenter {
        fun loadData()

        fun disposeData(bean: AchieveManageBean)
    }


    /**
     * 订单详情
     */
    interface OrderDetailsView : BaseView<OrderDetailsPresenter> {

        //获取部分
        fun context(): Context

        fun loginId(): String
        fun companyId(): String
        fun orderId(): String

        //显示部分
        fun showFail(e: Throwable)

        fun showDate(bean: OrderDetails.DataBean, info: OrderDetails.DataBean.InfoBean)

        fun showMoreCarModel(list: MutableList<OrderDetails.DataBean.InfoBean>)

    }

    interface OrderDetailsPresenter : BasePresenter {

        fun loadData()

        fun disposeData(bean: OrderDetails)
    }

    /**
     * 合同详情
     */
    interface ContractDetailsView : BaseView<ContractDetailsPresenter> {

        //获取部分
        fun context(): Context

        fun loginId(): String
        fun companyId(): String
        fun orderId(): String

        //显示部分
        fun showFail(e: Throwable)

        fun showDate(bean: ContractMDetailBean.DataBean, retainage: String)

    }

    interface ContractDetailsPresenter : BasePresenter {

        fun loadData()

        fun disposeData(bean: ContractMDetailBean)
    }

}