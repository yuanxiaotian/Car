package com.xuliucar.xuli.xuliucar.newmvp.view.messageManage.child


import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.View
import com.cangmaomao.lib.base.BaseNewFragment
import com.cangmaomao.lib.utils.SPUtils.create
import com.cangmaomao.lib.utils.initRecycler
import com.cangmaomao.lib.utils.toast
import com.xuliucar.xuli.xuliucar.R
import com.xuliucar.xuli.xuliucar.bean.HistorySendBean
import com.xuliucar.xuli.xuliucar.newmvp.adapter.HistoryMsgAdapter
import com.xuliucar.xuli.xuliucar.newmvp.contract.MessageMangeContract
import com.xuliucar.xuli.xuliucar.newmvp.presenter.messagemanage.HistoryMsgPresenter
import kotlinx.android.synthetic.main.history_send.*

/**
 * A simple [Fragment] subclass.
 */
class HistorySend : BaseNewFragment<MessageMangeContract.HistoryMsgPresenter>(), MessageMangeContract.HistoryMsgView, HistoryMsgAdapter.SendMsgOnClickListener {

    private var msgId: String = ""
    private lateinit var mAdapter: HistoryMsgAdapter
    override fun layViewId(): Int = R.layout.history_send
    override fun addViewId(): Int = 0
    override fun context(): Context = _mActivity
    override fun companyId(): String = create("userInfo").get("compid", "") as String
    override fun loginId(): String = create("userInfo").get("loginid", "") as String
    override fun msgId(): String = msgId


    override fun initView(savedInstanceState: Bundle?, view: View) {
        initRecycler(recyclerView)
        mAdapter = HistoryMsgAdapter(R.layout.history_send_item)
        mAdapter.listener = this
        recyclerView.adapter = mAdapter
        HistoryMsgPresenter(this)
        p.start()
    }

    override fun showFail(e: Throwable) {
    }

    override fun showDate(list: MutableList<HistorySendBean.DataBean>) {
        mAdapter.addList(list)
    }

    override fun sendMsgOnClick(msgId: String) {
        this.msgId = msgId
        p.sendMsg()
    }

    override fun showSendMsgStart(start: String) {
        toast(start)
    }

}
