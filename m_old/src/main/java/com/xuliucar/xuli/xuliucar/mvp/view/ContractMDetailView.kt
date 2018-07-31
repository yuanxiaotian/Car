package com.xuliucar.xuli.xuliucar.mvp.view

import com.xuliucar.xuli.xuliucar.bean.ContractMDetailBean

/**
 * Created by skyward on 2016/11/30.
 * email：
 */

interface ContractMDetailView {
    fun loadData(bean: ContractMDetailBean.DataBean)
    fun retainage(retainage: String)
    fun toastMsg(msg: String)
    fun showError()
}
