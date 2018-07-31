package com.xuliucar.xuli.xuliucar.mvp.view

import com.xuliucar.xuli.xuliucar.bean.Counts

/**
 * Created by skyward on 2016/12/5.
 * email：
 */

interface PassItemindexView {
    fun getCounts(bean: Counts.DataBean.InfoBean)
    fun companyId(): String
    fun loginId(): String
}
