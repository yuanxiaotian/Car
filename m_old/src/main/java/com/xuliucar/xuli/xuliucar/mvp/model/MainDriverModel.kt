package com.xuliucar.xuli.xuliucar.mvp.model

import com.cangmaomao.network.request.HttpManage
import com.cangmaomao.network.request.utils.RxSchedulers
import com.xuliucar.xuli.xuliucar.base.App
import com.xuliucar.xuli.xuliucar.bean.MainDirverBean
import com.xuliucar.xuli.xuliucar.config.ApiConfigTest
import com.xuliucar.xuli.xuliucar.mvp.ModelInterface.CallBackListener
import com.xuliucar.xuli.xuliucar.mvp.ModelInterface.IMainDriver
import com.xuliucar.xuli.xuliucar.mvp.presenter.BasePresenterImpl

/**
 * Created by skyward on 2016/12/6.
 * email：
 */

class MainDriverModel(private val mListener: CallBackListener<MainDirverBean>) : BasePresenterImpl(), IMainDriver {

    override fun getMainDriver(cid: String, loginid: String, compid: String) {
        HttpManage.getInstance()
                .create(ApiConfigTest::class.java)
                .getMainDriver(loginid, compid, cid)
                .compose(RxSchedulers.io_main())
                .subscribe({ mListener.OnSuccess(it) }, { mListener.OnFailure(it) })
    }
}
