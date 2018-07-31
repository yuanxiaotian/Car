package com.xuliucar.xuli.xuliucar.mvp.model

import com.cangmaomao.network.request.HttpManage
import com.cangmaomao.network.request.utils.RxSchedulers
import com.xuliucar.xuli.xuliucar.bean.SaleManagerBean
import com.xuliucar.xuli.xuliucar.config.ApiConfigTest
import com.xuliucar.xuli.xuliucar.mvp.ModelInterface.CallBackListener
import com.xuliucar.xuli.xuliucar.mvp.ModelInterface.SaleModel

/**
 * Created by skyward on 2016/11/30.
 * email：
 */

class SaleManagerModel(val mListener: CallBackListener<SaleManagerBean>) : SaleModel {

    override fun getSalesOrder(login: String?) {
        HttpManage.getInstance()
                .create(ApiConfigTest::class.java)
                .getSaleManager(login)
                .compose(RxSchedulers.io_main())
                .subscribe({ mListener.OnSuccess(it) }, { mListener.OnFailure(it) })
    }


}
