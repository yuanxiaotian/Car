package com.xuliucar.xuli.xuliucar.newmvp.contract

import android.content.Context
import com.cangmaomao.lib.base.BasePresenter
import com.cangmaomao.lib.base.BaseView
import com.xuliucar.xuli.xuliucar.bean.ContractManageBean
import com.xuliucar.xuli.xuliucar.bean.HistorySendBean
import com.xuliucar.xuli.xuliucar.bean.PeopleBean

interface MessageMangeContract {

    /**
     * 主
     */
    interface MainView : BaseView<MainPresenter> {

    }

    interface MainPresenter : BasePresenter {
        fun loadData()
    }


    /**
     * 发送消息
     */
    interface SendMsgView : BaseView<SendMsgPresenter> {

        //获取部分
        fun context(): Context

        fun loginId(): String
        fun companyId(): String


        //显示部分
        fun showFail(e: Throwable)

        fun showMsgInfoData(balance: String, todayCount: String, totalCount: String, tips: String)

        fun customPeopleList(vararg list: MutableList<PeopleBean.DataBean.Bean>)

        fun showAccountInfoData(driver: String, owner: String, user: String)

        fun showSendMsgTarget()

        fun showCustomPhone()

        fun showSendMsgType()

        fun showSendMsgTypeDetails()

        fun showCheckPhone(phone: String, num: Int, appendPhone: String)

        fun initSendMsgMap(): Map<String, String>

        fun showSendMsgStart(msg: String)

    }

    interface SendMsgPresenter : BasePresenter {
        fun loadData()

        fun disposeData(bean: Any)

        fun appendId(list: MutableList<PeopleBean.DataBean.Bean>): String

        fun checkPhone(phone: String)

        fun checkRepeatPhone(list: MutableList<String>)

        fun sendMsg()

    }


    /**
     * 历史发送消息
     */
    interface HistoryMsgView : BaseView<HistoryMsgPresenter> {

        //获取部分
        fun context(): Context

        fun loginId(): String
        fun companyId(): String
        fun msgId(): String

        //显示部分
        fun showFail(e: Throwable)

        fun showDate(list: MutableList<HistorySendBean.DataBean>)

        fun showSendMsgStart(start: String)

    }

    interface HistoryMsgPresenter : BasePresenter {
        fun loadData()

        fun sendMsg()

        fun disposeData(bean: HistorySendBean)

    }


    /**
     * 自定义人员
     */
    interface CustomPeopleView : BaseView<CustomPeoplePresenter> {

        //获取部分
        fun context(): Context

        fun arguments(): Array<out MutableList<PeopleBean.DataBean.Bean>>

        //显示部分
        fun showFail(e: Throwable)

        fun showDate(list: MutableList<HistorySendBean.DataBean>)

    }

    interface CustomPeoplePresenter : BasePresenter {
        fun loadData()

        fun sendMsg()

        fun disposeData(bean: HistorySendBean)

    }
}