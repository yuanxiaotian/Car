package com.xuliucar.me.contract

import android.content.Context
import com.cangmaomao.lib.base.BasePresenter
import com.cangmaomao.lib.base.BaseView
import com.xuliucar.me.model.CompanyInfo
import com.xuliucar.me.model.MeBean
import com.xuliucar.me.model.Noteinfo

interface InformationContract {


    /**
     * 主
     */
    interface View : BaseView<Presenter> {

        //获取部分
        fun context(): Context

        fun loginId(): String
        fun companyId(): String

        //显示部分
        fun showFail(e: Throwable)

        //显示请求信息
        fun showData(data: MutableList<Noteinfo.DataBean>)

        //显示操作后状态
        fun showState(msg: String)

        //单个删除flag =1   全部删除 flag =2  未读标记 flag3
        fun notifyDataSetChanged(position: Int = 0, flag: Int=0)

    }

    interface Presenter : BasePresenter {

        fun loadData()

        //单个删除flag =1   全部删除 flag =2  未读标记 flag3
        fun disposeData(bean: Any, position: Int = -1, flag: Int=0)

        fun noteRead(id: String, position: Int = -1)

        fun delSingle(id: String, position: Int = -1)

        fun delAll()
    }

}