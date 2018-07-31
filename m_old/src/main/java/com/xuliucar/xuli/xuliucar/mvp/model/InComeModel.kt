package com.xuliucar.xuli.xuliucar.mvp.model

import com.cangmaomao.network.request.HttpManage
import com.cangmaomao.network.request.utils.RxSchedulers
import com.xuliucar.xuli.xuliucar.base.App
import com.xuliucar.xuli.xuliucar.bean.InComeBean
import com.xuliucar.xuli.xuliucar.config.ApiConfigTest
import com.xuliucar.xuli.xuliucar.config.ApiManager
import com.xuliucar.xuli.xuliucar.mvp.ModelInterface.CallBackListener
import com.xuliucar.xuli.xuliucar.mvp.ModelInterface.IInCome
import com.xuliucar.xuli.xuliucar.mvp.presenter.BasePresenterImpl
import com.xuliucar.xuli.xuliucar.mvp.view.InComeView

import rx.Observer
import rx.Subscription
import rx.android.schedulers.AndroidSchedulers
import rx.functions.Func1
import rx.schedulers.Schedulers

/**
 * Created by skyward on 2016/11/29.
 * email：
 */

class InComeModel(val mListener: CallBackListener<InComeBean>, val view: InComeView) : BasePresenterImpl(), IInCome {

    override fun getInCome() {
        HttpManage.getInstance()
                .create(ApiConfigTest::class.java)
                .getIncome(view.loginId(), view.companyId())
                .compose(RxSchedulers.io_main())
                .subscribe({
                    mListener.OnSuccess(it)
                }, {
                    mListener.OnFailure(it)
                })
    }

}
