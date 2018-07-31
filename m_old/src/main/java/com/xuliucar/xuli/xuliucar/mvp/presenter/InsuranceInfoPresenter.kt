package com.xuliucar.xuli.xuliucar.mvp.presenter

import com.xuliucar.xuli.xuliucar.bean.InsuranceInfoData
import com.xuliucar.xuli.xuliucar.mvp.ModelInterface.CallBackListener
import com.xuliucar.xuli.xuliucar.mvp.model.InsuranceInfoModel
import com.xuliucar.xuli.xuliucar.mvp.view.InsuranceInfoView

class InsuranceInfoPresenter(val view: InsuranceInfoView<InsuranceInfoData>) : CallBackListener<InsuranceInfoData> {

    private var mode: InsuranceInfoModel = InsuranceInfoModel(this)


    fun getData() {
        mode.getInsurance(view.cid(), view.loginId(), view.companyId())
    }


    override fun OnSuccess(t: InsuranceInfoData) {
        view.OnSuccess(t)
    }

    override fun OnFailure(e: Throwable) {
        view.OnFailure(e)
    }


}