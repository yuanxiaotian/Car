package com.xuliucar.xuli.xuliucar.mvp.view

import com.xuliucar.xuli.xuliucar.bean.DoPerpetuaeBean

/**
 * Created by skyward on 2016/12/1.
 * email：
 */

interface DoPerpetuaeView {
    fun OnSuccess(infoBean: MutableList<DoPerpetuaeBean.DataBean.InfoBean>)
    fun OnFailure(e: Throwable)
}
