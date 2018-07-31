package com.xuliucar.xuli.xuliucar.newmvp.presenter.messagemanage

import com.cangmaomao.lib.config.PHPNE_REGULAR
import com.cangmaomao.network.request.HttpManage
import com.cangmaomao.network.request.base.BaseObserver
import com.cangmaomao.network.request.utils.RxSchedulers
import com.xuliucar.xuli.xuliucar.R
import com.xuliucar.xuli.xuliucar.bean.MessageCountBean
import com.xuliucar.xuli.xuliucar.bean.PeopleBean
import com.xuliucar.xuli.xuliucar.config.ApiConfigTest
import com.xuliucar.xuli.xuliucar.newmvp.contract.MessageMangeContract
import java.util.ArrayList
import kotlin.collections.HashSet
import kotlin.collections.MutableList
import kotlin.collections.indices


class SendMsgPresenter(val view: MessageMangeContract.SendMsgView) : MessageMangeContract.SendMsgPresenter {

    init {
        view.setPresenter(this)
    }

    override fun start() {
        loadData()
    }

    override fun loadData() {
        val manage = HttpManage.getInstance()
        val ob1 = manage.create(ApiConfigTest::class.java).getMessageInfo(view.loginId(), view.companyId())
        val ob2 = manage.create(ApiConfigTest::class.java).getPeople(view.loginId(), view.companyId())
        manage.concat(ob1, ob2, object : BaseObserver<Any>(view.loginId()) {
            override fun success(bean: Any?) {
                if (bean != null) {
                    disposeData(bean)
                } else {
                    view.showFail(Throwable(view.context().getString(R.string.request_err)))
                }
            }

            override fun fail(p0: String?) {
                view.showFail(Throwable(view.context().getString(R.string.request_err)))
            }
        })
    }

    override fun disposeData(bean: Any) {
        if (bean is MessageCountBean) {
            val msgData = bean as MessageCountBean?
            if (msgData != null) {
                val d = msgData.data
                view.showMsgInfoData(d.leftcount, d.todaycount, d.totalcount, d.tips)
                return
            }
        } else {
            val peopleBean = bean as PeopleBean?
            if (peopleBean != null) {
                val d = peopleBean.data
                val dList = d.driver
                val oList = d.owner
                val uList = d.user
                view.customPeopleList(dList, oList, uList)
                view.showAccountInfoData(appendId(dList), appendId(oList), appendId(uList))
                return
            }
        }
        view.showFail(Throwable(view.context().getString(R.string.request_err)))
    }

    override fun appendId(list: MutableList<PeopleBean.DataBean.Bean>): String {
        if (list.isEmpty()) {
            val id = StringBuffer()
            for (i in list) {
                id.append(i).append("|")
            }
            return id.toString()
        }
        return ""
    }

    override fun checkPhone(phone: String) {
        val allData = ArrayList<String>()
        val buffer = StringBuilder()
        val cuslistBuffer = StringBuilder()
        val format = StringBuilder()
        val phoneBuffer = StringBuilder()
        val errorStr = "[0-9]"
        /**
         * 获取输入字符的总的长度，把不符合0~9数字的字符全部替换为空，即去掉，然后再拼接
         */
        for (i in 0 until phone.length) {
            var subStr = phone.substring(i, i + 1)
            if (!subStr.matches(errorStr.toRegex())) {
                subStr = subStr.replace(subStr, "")
            }
            buffer.append(subStr)
        }
        var count = 0//有效号码的条数
        /**
         * 再拆分拼接后的数字，遍历拼接，每拼接到十一位，就验证是否是有效的手机号码，如果是，就放进数组里面
         */
        for (i in 0 until buffer.length) {
            val singal = buffer.toString().substring(i, i + 1)
            phoneBuffer.append(singal)
            if ((i + 1) % 11 == 0) {
                if (phoneBuffer.toString().matches(PHPNE_REGULAR.toRegex())) {
                    allData.add(phoneBuffer.toString())
                    phoneBuffer.delete(0, phoneBuffer.length)//符合号码后出去时要清空，不然长度会累加
                }
            }
        }

        checkRepeatPhone(allData)
        //拼接发送格式的有效号码
        for (i in allData.indices) {
            cuslistBuffer.append(allData[i]).append("|")
        }

        for (i in allData.indices) {
            count++
            format.append(allData[i]).append("\n")
        }
        view.showCheckPhone(format.toString(), count, cuslistBuffer.toString())
    }

    override fun checkRepeatPhone(list: MutableList<String>) {
        if (list.isEmpty()) return
        val set = HashSet<String>()
        for (phone in list) {
            set.add(phone)
        }
        list.clear()
        list.addAll(set)
    }

    /**
     * @param cuslist     已选择的所有自定义人员索引（人员信息接口中id）或自定义号码，由 “|” 分割，拼接为字符串；
     * @param send_target 发送目标。1=》车主  ，2=》司机，3=》员工，4=》自定义人员，5=》自定义号码
     * @param psendtype   临期/过期标识，od=》过期   ， odt=》临期
     * @param ptpl        业务类型。
     * Type_intrust：托管业务
     * Type_seasonchk：季审业务
     * Type_yearchk：年审业务
     * Type_yeartickt：年票业务
     * Type_insure：保险业务
     * Type_tp: 道路运输证办理
     * Type_tpchk：道路运输证年审
     * Type_epm：环保标志
     * Type_cwaste：建废标识
     * Type_driverlic：驾照相关
     * Type_carloan ：车辆贷款
     * @param msg         短信内容
     * @param scount      自定义内容标识；chk=》使用系统模板 ，“ ”=》自定义短信内容
     */
    override fun sendMsg() {
        HttpManage.getInstance().create(ApiConfigTest::class.java)
                .smssend(view.initSendMsgMap())
                .compose(RxSchedulers.io_main())
                .subscribe({ view.showSendMsgStart(it.message) }, { view.showFail(Throwable("")) })
    }

}