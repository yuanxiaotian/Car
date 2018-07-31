package com.xuliucar.xuli.xuliucar.mvp.model

import com.cangmaomao.network.request.HttpManage
import com.cangmaomao.network.request.utils.RxSchedulers
import com.xuliucar.xuli.xuliucar.bean.InsuranceInfoData
import com.xuliucar.xuli.xuliucar.config.ApiConfigTest
import com.xuliucar.xuli.xuliucar.mvp.ModelInterface.CallBackListener
import com.xuliucar.xuli.xuliucar.mvp.ModelInterface.IAchieveManageModel
import com.xuliucar.xuli.xuliucar.mvp.ModelInterface.InsuranceModel
import com.xuliucar.xuli.xuliucar.mvp.presenter.BasePresenterImpl

/**
 * Created by skyward on 2016/11/30.
 * email：
 */

class InsuranceInfoModel(private val mListener: CallBackListener<InsuranceInfoData>) : BasePresenterImpl(), InsuranceModel {

    override fun getInsurance(cid: String?, login: String?, companyId: String?) {
        HttpManage.getInstance()
                .create(ApiConfigTest::class.java)
                .getInsurance(login, companyId, cid)
                .compose(RxSchedulers.io_main())
                .subscribe({ mListener.OnSuccess(it) }, { mListener.OnFailure(it) })
    }

}
