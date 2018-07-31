package com.xuliucar.me.presenter

import com.cangmaomao.network.request.HttpManage
import com.cangmaomao.network.request.utils.RxSchedulers
import com.xuliucar.me.R
import com.xuliucar.me.api.ApiConfig
import com.xuliucar.me.contract.InformationContract
import com.xuliucar.me.model.MessageBean
import com.xuliucar.me.model.NoteBean
import com.xuliucar.me.model.Noteinfo

class InformationPresenter(val view: InformationContract.View) : InformationContract.Presenter {


    init {
        view.setPresenter(this)
    }

    override fun start() {
        loadData()
    }


    override fun loadData() {
        HttpManage.getInstance().create(ApiConfig::class.java)
                .noteInfo(view.loginId(), view.companyId())
                .compose(RxSchedulers.io_main())
                .subscribe({ disposeData(it) }, { view.showFail(it) })

    }

    override fun delAll() {
        HttpManage.getInstance().create(ApiConfig::class.java)
                .noteInfoDelAll(view.loginId(), view.companyId())
                .compose(RxSchedulers.io_main())
                .subscribe({ disposeData(it,0,1) }, { view.showFail(it) })
    }

    override fun delSingle(id: String, position: Int) {
        HttpManage.getInstance().create(ApiConfig::class.java)
                .noteInfoDel(view.loginId(), view.companyId(), id)
                .compose(RxSchedulers.io_main())
                .subscribe({ disposeData(it, position,2) }, { view.showFail(it) })
    }

    override fun noteRead(id: String, position: Int) {
        HttpManage.getInstance().create(ApiConfig::class.java)
                .noteread(view.loginId(), view.companyId(), id)
                .compose(RxSchedulers.io_main())
                .subscribe({ disposeData(it, position,3) }, { view.showFail(it) })
    }

    override fun disposeData(bean: Any, position: Int, flag: Int) {
        when (bean) {
            is Noteinfo -> {
                if (bean.isSuccess)
                    if (!bean.data.isEmpty()) {
                        val list = ArrayList<Noteinfo.DataBean>()
                        bean.data.forEach { _, u ->
                            list.add(u)
                        }
                        view.showData(list)
                    } else
                        view.showFail(Throwable(view.context().getString(R.string.request_err)))
            }
            is NoteBean -> {
                if (bean.isSuccess) {
                    view.showState(bean.message)
                    if (position != -1) {
                        view.notifyDataSetChanged(position, 3)
                    }
                } else {
                    view.showFail(Throwable(view.context().getString(R.string.request_err)))
                }
            }
        }
    }


}