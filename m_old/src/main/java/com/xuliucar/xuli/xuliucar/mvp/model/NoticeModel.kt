package com.xuliucar.xuli.xuliucar.mvp.model

import com.cangmaomao.network.request.HttpManage
import com.cangmaomao.network.request.base.BaseObserver
import com.cangmaomao.network.request.utils.RxSchedulers
import com.xuliucar.xuli.xuliucar.bean.NoticeBean
import com.xuliucar.xuli.xuliucar.config.ApiConfigTest
import com.xuliucar.xuli.xuliucar.mvp.ModelInterface.CallBackListener
import com.xuliucar.xuli.xuliucar.mvp.ModelInterface.INotice
import com.xuliucar.xuli.xuliucar.mvp.presenter.BasePresenterImpl

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by skyward on 2016/11/23.
 */

class NoticeModel(private val mOnNoticeListener: CallBackListener<NoticeBean>) : BasePresenterImpl(), INotice {

    override fun getNoticeList(loginId: String, companyId: String) {
        HttpManage.getInstance()
                .create(ApiConfigTest::class.java)
                .getNotices(loginId, companyId)
                .compose(RxSchedulers.io_main())
                .subscribe({ mOnNoticeListener.OnSuccess(it) }, { mOnNoticeListener.OnFailure(it) })
    }
}
