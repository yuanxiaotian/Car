package com.xuliucar.xuli.xuliucar.mvp.view

import com.xuliucar.xuli.xuliucar.bean.ContractManageBean

import java.io.Serializable

/**
 * Created by skyward on 2016/11/30.
 * email：
 */

interface ContractManageView {
    fun startLoad(been: MutableList<ContractManageBean.DataBean>, size: Int)
    fun loadMore(been: MutableList<ContractManageBean.DataBean>, size: Int)
    fun toastMsg(msg: String)
    fun showError()
}
