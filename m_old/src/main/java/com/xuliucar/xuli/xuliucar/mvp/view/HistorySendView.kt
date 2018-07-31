package com.xuliucar.xuli.xuliucar.mvp.view

import com.xuliucar.xuli.xuliucar.bean.HistorySendBean
import com.xuliucar.xuli.xuliucar.bean.PendingPayBean

/**
 * Created by skyward on 2016/12/7.
 * email：
 */

interface HistorySendView {
    fun startLoad(been: MutableList<HistorySendBean.DataBean>, size: Int)
    fun loadMore(been: MutableList<HistorySendBean.DataBean>, size: Int)
}
