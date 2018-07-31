package com.xuliucar.car.presenter

import android.text.TextUtils
import com.cangmaomao.network.request.HttpManage
import com.cangmaomao.network.request.base.BaseObserver
import com.xuliucar.car.R
import com.xuliucar.car.contract.HomePageContract
import com.xuliucar.xuli.xuliucar.bean.Counts
import com.xuliucar.xuli.xuliucar.bean.HomeBanner
import com.xuliucar.xuli.xuliucar.bean.NoticeBean
import com.xuliucar.xuli.xuliucar.config.ApiConfigTest
import java.util.ArrayList


class HomePagePresenter(val view: HomePageContract.View) : HomePageContract.Presenter {

    init {
        view.setPresenter(this)
    }

    override fun start() {
        loadData()
    }

    override fun loadData() {
        val manage = HttpManage.getInstance()
        val config = manage.create(ApiConfigTest::class.java)
        manage.concat(config.getNotices(view.loginId(), view.companyId()), config.getCounts(view.loginId()), config.indexbanner(view.loginId()),
                object : BaseObserver<Any>("home") {
                    override fun success(data: Any?) {
                        when (data) {
                            is HomeBanner -> disposeBanner(data)
                            is NoticeBean -> disposeNotice(data)
                            is Counts -> disposeMsgCount(data)
                        }
                    }

                    override fun fail(p0: String?) {
                        if (TextUtils.isEmpty(p0)) view.showFail(Throwable(view.context().getString(R.string.request_err))) else view.showFail(Throwable(p0))
                    }

                })
    }

    override fun disposeBanner(bean: HomeBanner) {
        if (bean.isSuccess) {
            if (bean.data != null) {
                val list = bean.data
                val bannerList = ArrayList<String>()
                for (dataBean in list) {
                    if (!TextUtils.isEmpty(dataBean.b1)) {
                        bannerList.add(dataBean.b1)
                        continue
                    }
                    if (!TextUtils.isEmpty(dataBean.b2)) {
                        bannerList.add(dataBean.b2)
                        continue
                    }
                    if (!TextUtils.isEmpty(dataBean.b3)) {
                        bannerList.add(dataBean.b3)
                        continue
                    }
                    if (!TextUtils.isEmpty(dataBean.b4)) {
                        bannerList.add(dataBean.b4)
                        continue
                    }
                    if (!TextUtils.isEmpty(dataBean.b5)) {
                        bannerList.add(dataBean.b5)
                        continue
                    }
                    if (!TextUtils.isEmpty(dataBean.b6)) {
                        bannerList.add(dataBean.b6)
                        continue
                    }
                    if (!TextUtils.isEmpty(dataBean.b7)) {
                        bannerList.add(dataBean.b7)
                    }
                }
                view.showBanner(bannerList)
                return
            }
        }
        view.showFail(Throwable(view.context().getString(R.string.request_err)))
    }

    override fun disposeNotice(bean: NoticeBean) {
        if (bean.success) {
            val list = bean.data
            if (!list.isEmpty()) {
                bean.newList = ArrayList<NoticeBean.NewNoticeData>()
                var k = 0

                lateinit var data: NoticeBean.NewNoticeData
                for (i in list) {
                    if (k <= 8) {
                        if (k % 3 == 0) {
                            data = NoticeBean.NewNoticeData()
                        }

                        if (k % 3 == 0) {
                            data.title_zf = i.title
                        }

                        if (k % 3 == 1) {
                            data.title_xl = i.title
                        }

                        if (k % 3 == 2) {
                            data.title_xt = i.title
                        }

                        if (k > 0 && k % 3 == 0) {
                            bean.newList.add(data)
                        }

                        if (k == 8) {
                            view.showNotice(bean.newList)
                            return
                        }
                        k++
                        continue
                    } else {
                        return
                    }
                }


            }
        }
    }

    override fun disposeMsgCount(bean: Counts) {

    }


}