package com.xuliucar.xuli.xuliucar.newmvp.adapter

import android.graphics.Color
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import com.cangmaomao.recyclerview.adapter.CMMAdapter
import com.cangmaomao.recyclerview.adapter.CMMViewHolder
import com.xuliucar.xuli.xuliucar.R
import com.xuliucar.xuli.xuliucar.bean.HistorySendBean

/**
 * Created by skyward on 2016/7/7.
 *
 */
class HistoryMsgAdapter(layoutId: Int) : CMMAdapter<HistorySendBean.DataBean>(layoutId) {

    var listener: SendMsgOnClickListener? = null


    override fun convert(cmmViewHolder: CMMViewHolder, dataBean: HistorySendBean.DataBean, i: Int) {

        cmmViewHolder.setText(R.id.send_message_type, dataBean.btype)
        cmmViewHolder.setText(R.id.send_target_phone, dataBean.phone)
        cmmViewHolder.setText(R.id.msg_num, dataBean.count)
        cmmViewHolder.setText(R.id.send_time, dataBean.sendtime)

        val send_status = cmmViewHolder.getView<TextView>(R.id.send_status)
        send_status.text = dataBean.status
        if (dataBean.status == "已送达") {
            send_status.setTextColor(Color.parseColor("#3FA056"))
        } else if (dataBean.status == "发送失败" || dataBean.status == "接收失败") {
            send_status.setTextColor(Color.parseColor("#FF4040"))
        } else if (dataBean.status == "已发送") {
            send_status.setTextColor(Color.parseColor("#6495ED"))
        }

        val historySend_item_content = cmmViewHolder.getView<RelativeLayout>(R.id.historySend_item_content)

        historySend_item_content.setOnClickListener {
            val builder = android.support.v7.app.AlertDialog.Builder(cmmViewHolder.mContext)
            builder.setMessage(dataBean.getContent())
            builder.create().show()
        }
        val send_again = cmmViewHolder.getView<ImageView>(R.id.send_again)
        send_again.setOnClickListener {
            if (listener != null) {
                listener!!.sendMsgOnClick(dataBean.getSmsid())
            }
        }

    }

//    private fun SendAgain(smsid: String) {
//        if (AppPower.app_pow28 == "0") {
//            ToastUtil.showShortToast(mContext, "当前用户没有权限发送短信")
//        } else {
//            val builder = android.support.v7.app.AlertDialog.Builder(mContext)
//            builder.setMessage("确认再次发送吗?")
//            builder.setTitle("温馨提醒")
//            builder.setPositiveButton("确认") { dialogInterface, i -> sureSend(smsid) }
//            builder.setNegativeButton("取消") { dialogInterface, i -> dialogInterface.dismiss() }
//            builder.create().show()
//        }
//    }

//    private fun sureSend(smsid: String) {
//        LogUtil.LogPrint("smsid $smsid")
//        val client = OkHttpClient()
//        val requestBody = FormBody.Builder()
//                .add("loginid", App.loginid)
//                .add("compid", App.compid.toString())
//                .add("smsid", smsid)
//                .build()
//        val request = Request.Builder()
//                .addHeader("cookie", App.cookie)
//                .url("http://www.gzxlxx.com:8866/index.php/Home/App/smsresend")
//                .post(requestBody)
//                .build()
//        client.newCall(request).enqueue(object : Callback {
//            override fun onFailure(call: Call, e: IOException) {
//
//
//            }
//
//            @Throws(IOException::class)
//            override fun onResponse(call: Call, response: Response) {
//                val t = response.body()!!.string()
//                sendAgainCallBack.getResult(t)
//
//            }
//        })
//    }

    interface SendMsgOnClickListener {
        fun sendMsgOnClick(msgId: String)
    }
}
