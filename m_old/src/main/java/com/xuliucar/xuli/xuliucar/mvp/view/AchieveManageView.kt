package com.xuliucar.xuli.xuliucar.mvp.view

import com.xuliucar.xuli.xuliucar.bean.AchieveManageBean

/**
 * Created by skyward on 2016/11/30.
 * email：
 */

interface AchieveManageView {
    fun startLoad(been: MutableList<AchieveManageBean.DataBean>, size: Int)
    fun loadMore(been: MutableList<AchieveManageBean.DataBean>, size: Int)
    fun toastMsg(msg: String)
    fun showError()
}
