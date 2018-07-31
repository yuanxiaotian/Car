package com.xuliucar.me.presenter

import com.cangmaomao.network.request.HttpManage
import com.cangmaomao.network.request.utils.RxSchedulers
import com.github.promeg.pinyinhelper.Pinyin
import com.xuliucar.me.api.ApiConfig
import com.xuliucar.me.contract.PeopleContract
import com.xuliucar.me.model.PeopleData
import com.xuliucar.me.ui.fragment.Me

class PeopleListPresenter(val view: PeopleContract.View) : PeopleContract.Presenter {


    init {
        view.setPresenter(this)
    }

    override fun start() {
        loadData()
    }


    override fun loadData() {
        HttpManage.getInstance().create(ApiConfig::class.java)
                .peopleInfo(view.itemId(), view.loginId(), view.companyId())
                .compose(RxSchedulers.io_main())
                .subscribe({ disposeData(it) }, { view.showFail(it) })
    }

    override fun disposeData(bean: PeopleData) {
        if (bean.isSuccess) {
            val list = bean.data
            list.forEach {
                var c: String = ""
                when (view.itemId()) {
                    Me.ITEM_2 -> it.name = it.drivername
                    Me.ITEM_3 -> it.name = it.ownername
                }
                c = Pinyin.toPinyin(it.name.toCharArray()[0])
                var i = c[0]
                if (i in 'a'..'z') {
                    i -= 32
                }
                it.index = i.toString()
            }
            var i = 0
            while (i < list.size) {
                var j = 1
                while (j < list.size - i) {
                    if (list[j - 1].index[0] > list[j].index[0]) {
                        val temp: PeopleData.DataBean = list[j - 1]
                        list[j - 1] = list[j]
                        list[j] = temp
                    }
                    j++
                }
                i++
            }
            view.showData(bean.data)
        }
    }
}