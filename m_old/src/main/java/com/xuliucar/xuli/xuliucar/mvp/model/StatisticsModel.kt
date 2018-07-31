package com.xuliucar.xuli.xuliucar.mvp.model

import com.cangmaomao.network.request.HttpManage
import com.cangmaomao.network.request.utils.RxSchedulers
import com.xuliucar.xuli.xuliucar.base.App
import com.xuliucar.xuli.xuliucar.bean.StatisticsBean
import com.xuliucar.xuli.xuliucar.config.ApiConfigTest
import com.xuliucar.xuli.xuliucar.config.ApiManager
import com.xuliucar.xuli.xuliucar.mvp.ModelInterface.CallBackListener
import com.xuliucar.xuli.xuliucar.mvp.ModelInterface.IStatistics
import com.xuliucar.xuli.xuliucar.mvp.presenter.BasePresenterImpl
import com.xuliucar.xuli.xuliucar.mvp.view.StatisticsView

import rx.Observer
import rx.Subscription
import rx.android.schedulers.AndroidSchedulers
import rx.functions.Func1
import rx.schedulers.Schedulers

/**
 * Created by skyward on 2016/11/24.
 * email：
 */

class StatisticsModel(private val listener: CallBackListener<StatisticsBean>,val view: StatisticsView) : BasePresenterImpl(), IStatistics {
    override fun getData() {
        HttpManage.getInstance()
                .create(ApiConfigTest::class.java)
                .getStatistics(view.loginId())
                .compose(RxSchedulers.io_main())
                .subscribe({ listener.OnSuccess(it) }, { listener.OnFailure(it) })

    }
}
