package com.xuliucar.xuli.xuliucar.mvp.view

import com.xuliucar.xuli.xuliucar.bean.PassPerPetaueBean

/**
 * Created by skyward on 2016/12/2.
 * email：
 */

interface PassPerpetuaeView {
    fun startLoad(been: MutableList<PassPerPetaueBean.DataBean.InfoBean>, size: Int)
    fun loadMore(been: MutableList<PassPerPetaueBean.DataBean.InfoBean>, size: Int)
    fun loadAll(been: MutableList<PassPerPetaueBean.DataBean.InfoBean>)
    fun toastMsg(msg: String)
    fun showError()
}
