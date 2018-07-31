package com.xuliucar.xuli.xuliucar.mvp.view

import com.xuliucar.xuli.xuliucar.bean.ConstructionWasteBean

/**
 * Created by skyward on 2016/12/2.
 * email：
 */

interface ConstructionWastView {
    fun startLoad(been: MutableList<ConstructionWasteBean.DataBean.InfoBean>, size: Int)
    fun loadMore(been: MutableList<ConstructionWasteBean.DataBean.InfoBean>, size: Int)
    fun loadAll(been: MutableList<ConstructionWasteBean.DataBean.InfoBean>)
    fun toastMsg(msg: String)
    fun showError()
}
