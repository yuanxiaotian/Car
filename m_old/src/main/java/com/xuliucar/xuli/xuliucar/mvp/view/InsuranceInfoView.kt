package com.xuliucar.xuli.xuliucar.mvp.view

import com.xuliucar.xuli.xuliucar.bean.AchieveManageBean

/**
 * Created by skyward on 2016/11/30.
 * email：
 */

interface InsuranceInfoView<T> {
    fun OnSuccess(t: T)
    fun OnFailure(e: Throwable)
    fun companyId(): String
    fun loginId(): String
    fun cid(): String

}
