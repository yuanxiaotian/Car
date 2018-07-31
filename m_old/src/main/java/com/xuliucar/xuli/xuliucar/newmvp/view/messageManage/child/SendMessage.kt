package com.xuliucar.xuli.xuliucar.newmvp.view.messageManage.child


import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.location.Location
import android.os.Bundle
import android.support.v7.widget.AppCompatButton
import android.support.v7.widget.AppCompatEditText
import android.support.v7.widget.AppCompatTextView
import android.text.TextUtils
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.cangmaomao.lib.base.BaseNewFragment
import com.cangmaomao.lib.utils.SPUtils.create
import com.cangmaomao.lib.utils.toast
import com.cangmaomao.lib.utils.width
import com.xuliucar.xuli.xuliucar.R
import com.xuliucar.xuli.xuliucar.adapter.MultiTagAdapter
import com.xuliucar.xuli.xuliucar.adapter.TagAdapter
import com.xuliucar.xuli.xuliucar.bean.CustomPeopleBean
import com.xuliucar.xuli.xuliucar.bean.MutiTagBean
import com.xuliucar.xuli.xuliucar.bean.PeopleBean
import com.xuliucar.xuli.xuliucar.newmvp.contract.MessageMangeContract
import com.xuliucar.xuli.xuliucar.newmvp.popwindow.messageManage.CustomPeopleView
import com.xuliucar.xuli.xuliucar.newmvp.presenter.messagemanage.SendMsgPresenter
import com.xuliucar.xuli.xuliucar.utils.LogUtil
import com.xuliucar.xuli.xuliucar.utils.PreferencesUtils
import com.xuliucar.xuli.xuliucar.utils.ToastUtil
import com.xuliucar.xuli.xuliucar.utils.Watcher
import com.xuliucar.xuli.xuliucar.widget.MyDialog
import com.xuliucar.xuli.xuliucar.widget.tagView.FlowTagLayout
import kotlinx.android.synthetic.main.send_message.*
import java.io.Serializable
import kotlin.collections.ArrayList

class SendMessage : BaseNewFragment<MessageMangeContract.SendMsgPresenter>(), View.OnClickListener, MessageMangeContract.SendMsgView {

    private lateinit var driver: String
    private lateinit var owner: String
    private lateinit var user: String

    override fun layViewId(): Int = R.layout.send_message
    override fun addViewId(): Int = 0
    override fun context(): Context = _mActivity
    override fun companyId(): String = create("userInfo").get("compid", "") as String
    override fun loginId(): String = create("userInfo").get("loginid", "") as String
    override fun initView(savedInstanceState: Bundle?, view: View) {

        mCustomPeopleBeenList = ArrayList()
        Watcher.calculateCounts(custom_content!!, input_msg_count)
        Watcher.IllegalCustomMsg(custom_content!!)
        send_target_content.setOnClickListener(this)
        message_type_content.setOnClickListener(this)
        send_message_btn.setOnClickListener(this)
        custom_content.isEnabled = false
        input_msg_count.text = "当前已输入：0字"

        SendMsgPresenter(this)
        p.start()


        cuslistBuffer = StringBuffer()
        ptplBuffer = StringBuffer()

        mCustomPeopleBeenList = initArguments("list")


        val sendTag = arguments!!.getString("sendTag")
        val customPhone = arguments!!.getString("customPhone")
        val target = arguments!!.getString("target")
        val targetType = arguments!!.getString("targetType")
        val sendtype = arguments!!.getString("sendtype")
        val ptple = arguments!!.getString("ptple")


        if (!TextUtils.isEmpty(sendTag)) {
            select_target!!.text = sendTag
            selectSendTag = getString(R.string.send_custom_staff)

        }
        if (mCustomPeopleBeenList != null) {
            for (i in mCustomPeopleBeenList!!.indices) {
                cuslistBuffer!!.append(mCustomPeopleBeenList!![i].id).append("|")
            }
            cuslistStr = cuslistBuffer!!.toString()
            val owner = PreferencesUtils.getSharePreBoolean(activity!!, "CustomPeopleView", "owner")
            val driver = PreferencesUtils.getSharePreBoolean(activity!!, "CustomPeopleView", "driver")
            val user = PreferencesUtils.getSharePreBoolean(activity!!, "CustomPeopleView", "user")
            if (owner == true && driver == true || owner == true && user == true || driver == true && user == true || owner == true && driver == true && user == true || owner == false && driver == false && user == true) {
                send_target = "4"
                scount = ""
                psendtype = "0"
                selectSendTag = getString(R.string.send_custom_staff)
                select_send_type!!.text = "已选择：自定义"
                custom_content_layout!!.visibility = View.VISIBLE
                custom_content!!.isEnabled = true
            }
        }

        //车辆列表、员工、司机、车主，传递过来的数据
        if (!TextUtils.isEmpty(customPhone)) {
            select_target!!.text = String.format("已选择: %s", getString(R.string.send_custom_phone))
            select_send_type!!.text = String.format("已选择: %s", getString(R.string.send_custom_phone))
            send_target = "5"
            psendtype = "0"
            scount = ""
            ptplBuffer!!.delete(0, ptplBuffer!!.length)
            cuslistBuffer!!.delete(0, cuslistBuffer!!.length)
            custom_content!!.isEnabled = true
        }
        //临期过期传递过来的数据
        if (!TextUtils.isEmpty(target) && !TextUtils.isEmpty(targetType) && !TextUtils.isEmpty(sendtype) && !TextUtils.isEmpty(ptple)) {
            when (sendtype) {
                "临期" -> when (targetType) {
                    "车主" -> {
                        cuslistStr = target
                        psendtype = "odt"
                        scount = "chk"
                        send_target = "4"
                        ptpleStr = ptple
                        selectSendTag = getString(R.string.send_carOwner)
                        select_target!!.text = String.format("已选择: %s", getString(R.string.send_custom_staff))
                        select_send_type!!.text = String.format("已选择: %s", getString(R.string.select_Advent))
                    }
                    "司机" -> {
                        cuslistStr = target
                        psendtype = "odt"
                        scount = "chk"
                        send_target = "4"
                        ptpleStr = ptple
                        selectSendTag = getString(R.string.send_carOwner)
                        select_target!!.text = String.format("已选择: %s", getString(R.string.send_custom_staff))
                        select_send_type!!.text = String.format("已选择: %s", getString(R.string.select_Advent))
                    }
                }
                "过期" -> when (targetType) {
                    "车主" -> {
                        cuslistStr = target
                        psendtype = "od"
                        scount = "chk"
                        send_target = "4"
                        ptpleStr = ptple
                        selectSendTag = getString(R.string.send_carOwner)
                        select_target!!.text = String.format("已选择: %s", getString(R.string.send_custom_staff))
                        select_send_type!!.text = String.format("已选择: %s", getString(R.string.select_pass))
                    }
                    "司机" -> {
                        cuslistStr = target
                        psendtype = "od"
                        scount = "chk"
                        send_target = "4"
                        ptpleStr = ptple
                        selectSendTag = getString(R.string.send_carOwner)
                        select_target!!.text = String.format("已选择: %s", getString(R.string.send_custom_staff))
                        select_send_type!!.text = String.format("已选择: %s", getString(R.string.select_pass))
                    }
                }
            }
        }
    }

    @SuppressLint("SetTextI18n")
    override fun showMsgInfoData(balance: String, todayCount: String, totalCount: String, tips: String) {
        sms_balance.text = "余额\n$balance"
        today_send.text = "今日已发：$todayCount"
        total_send.text = "累计发送：$totalCount"
        input_tips.text = tips
        input_tips.setTextColor(Color.parseColor("#FF4500"))
    }

    override fun showAccountInfoData(driver: String, owner: String, user: String) {
        this.driver = driver
        this.owner = owner
        this.user = user
    }

    private var mSelectSendTypeAdapter: TagAdapter<String>? = null
    private var mSelectSendTypeDetailAdapter: MultiTagAdapter? = null
    private var mSelectDriverSendTypeDetailAdapter: MultiTagAdapter? = null
    private var send_target: String? = null//发送目标
    private var psendtype: String? = null//临期/过期标识
    private var scount: String? = null
    private var cuslistBuffer: StringBuffer? = null
    private var mSelectAdventData: MutableList<MutiTagBean>? = null
    private var ptplBuffer: StringBuffer? = null
    private var mCustomPeopleBeenList: List<CustomPeopleBean>? = null
    private var mCustomMsg: String? = null
    private var selectSendTag: String? = null//根据这个标签来确定通知类型的选择
    private var cuslistStr = ""
    private var ptpleStr = ""
    private lateinit var custom_phones_num: AppCompatEditText
    private lateinit var phone_count: TextView
    private lateinit var arrayList: MutableList<MutableList<PeopleBean.DataBean.Bean>>


    override fun onClick(v: View) {
        val i = v.id
        if (i == R.id.send_target_content) {
            select_send_type!!.text = ""
            showSendMsgTarget()

        } else if (i == R.id.message_type_content) {
            if (!TextUtils.isEmpty(selectSendTag)) {
                if (selectSendTag == getString(R.string.send_carOwner)) {
                    clearAgr()
                    showSendMsgType()
                } else if (selectSendTag == getString(R.string.send_driver)) {
                    clearAgr()
                    showSendMsgType()
                } else if (selectSendTag == getString(R.string.send_staff)) {
                    clearAgr()
                    select_send_type!!.text = "已选择：自定义"
                    custom_content!!.isEnabled = true
                } else if (selectSendTag == getString(R.string.send_custom_staff)) {
                    val owner = PreferencesUtils.getSharePreBoolean(activity!!, "CustomPeopleView", "owner")
                    val driver = PreferencesUtils.getSharePreBoolean(activity!!, "CustomPeopleView", "driver")
                    val user = PreferencesUtils.getSharePreBoolean(activity!!, "CustomPeopleView", "user")
                    LogUtil.LogPrint("选择类型： owner $owner driver $driver user $user")
                    if (owner == true && driver == false && user == false) {
                        send_target = "4"
                        selectSendTag = getString(R.string.send_carOwner)
                        showSendMsgType()
                    } else if (owner == false && driver == true && user == false) {
                        send_target = "4"
                        selectSendTag = getString(R.string.send_driver)
                        showSendMsgType()
                    } else if (owner == false && driver == false && user == false) {
                        val intent = Intent(activity, CustomPeople::class.java)
                        startActivity(intent)
                    } else {
                        send_target = "4"
                        scount = ""
                        psendtype = "0"
                        selectSendTag = "自定义"
                        select_send_type!!.text = "已选择：自定义"
                        custom_content!!.isEnabled = true
                    }

                } else if (selectSendTag == getString(R.string.send_custom_phone)) {
                    select_send_type!!.text = "已选择：自定义号码"
                    cuslistBuffer!!.delete(0, cuslistBuffer!!.length)
                    clearAgr()
                    showCustomPhone()
                    psendtype = "0"
                    custom_content!!.isEnabled = true
                } else {
                    ToastUtil.showShortToast(activity, "请选择发送目标")
                }
            } else {
                ToastUtil.showShortToast(activity, "请选择发送目标")
            }


        } else if (i == R.id.send_message_btn) {
            mCustomMsg = custom_content!!.text.toString().trim { it <= ' ' }
            isSureSend()

        }
    }

    private fun isSureSend() {
        val builder = android.support.v7.app.AlertDialog.Builder(activity!!)
        builder.setMessage("确认发送吗?")
        builder.setTitle("温馨提醒")
        builder.setPositiveButton("确认") { _, i ->
            p.sendMsg()
        }
        builder.setNegativeButton("取消") { dialogInterface, _
            ->
            dialogInterface.dismiss()
        }
        builder.create().show()
    }

    private fun clearAgr() {
        psendtype = ""//临期/过期标识
        ptplBuffer!!.delete(0, ptplBuffer!!.length)//业务类型
        mCustomMsg = ""//短信内容
        scount = ""//是否使用模板
    }

    @SuppressLint("RtlHardcoded")
    override fun showSendMsgTarget() {
        val view = View.inflate(_mActivity, R.layout.alert_send_target, null)
        val dialog = AlertDialog.Builder(_mActivity).create()
        dialog.setView(view)
        dialog.show()
        view.findViewById<AppCompatTextView>(R.id.send_target_carOwner).setOnClickListener {
            message_type_content_super.visibility = View.VISIBLE
            select_target!!.text = String.format("已选择: %s", getString(R.string.send_carOwner))
            selectSendTag = getString(R.string.send_carOwner)
            send_target = "1"
            cuslistStr = owner
            custom_content!!.isEnabled = false
            dialog.dismiss()
        }
        view.findViewById<AppCompatTextView>(R.id.send_target_driver).setOnClickListener {
            message_type_content_super.visibility = View.VISIBLE
            select_target!!.text = String.format("已选择: %s", getString(R.string.send_driver))
            selectSendTag = getString(R.string.send_driver)
            cuslistStr = driver
            send_target = "2"
            custom_content!!.isEnabled = false
            dialog.dismiss()
        }
        view.findViewById<AppCompatTextView>(R.id.send_target_staff).setOnClickListener {
            message_type_content_super.visibility = View.VISIBLE
            select_target!!.text = String.format("已选择: %s", getString(R.string.send_staff))
            selectSendTag = getString(R.string.send_staff)
            scount = ""
            cuslistStr = user
            ptplBuffer!!.delete(0, ptplBuffer!!.length)
            send_target = "3"
            psendtype = "0"
            select_send_type!!.text = "已选择：自定义"
            custom_content!!.isEnabled = true
            dialog.dismiss()
        }
        view.findViewById<AppCompatTextView>(R.id.send_target_custom_staff).setOnClickListener {
            message_type_content_super.visibility = View.GONE
            select_target!!.text = String.format("已选择: %s", getString(R.string.send_custom_staff))
            selectSendTag = getString(R.string.send_custom_staff)
            send_target = "4"
            val pop = View.inflate(_mActivity, R.layout.custom_people_pop_view, null)
            CustomPeopleView(_mActivity, arrayList, pop)
            select_send_type!!.text = "已选择：自定义人员"
            custom_content!!.isEnabled = false
            dialog.dismiss()
        }
        view.findViewById<AppCompatTextView>(R.id.send_target_custom_phone).setOnClickListener {
            message_type_content_super.visibility = View.GONE
            select_target!!.text = String.format("已选择: %s", getString(R.string.send_custom_phone))
            selectSendTag = getString(R.string.send_custom_phone)
            send_target = "5"
            scount = ""
            psendtype = "0"
            ptplBuffer!!.delete(0, ptplBuffer!!.length)
            cuslistBuffer!!.delete(0, cuslistBuffer!!.length)
            select_send_type!!.text = "自定义号码"
            showCustomPhone()
            custom_content!!.isEnabled = true
            dialog.dismiss()
        }
    }

    override fun showCustomPhone() {
        val view = View.inflate(_mActivity, R.layout.alert_custom_phone, null)
        val dialog = AlertDialog.Builder(_mActivity).create()
        dialog.setView(view)
        dialog.show()
        custom_phones_num = view.findViewById(R.id.custom_phones_num)
        phone_count = view.findViewById(R.id.phone_count)
        view.findViewById<AppCompatButton>(R.id.customPhone_check_btn).setOnClickListener { p.checkPhone(custom_phones_num.text.toString()) }
        view.findViewById<AppCompatButton>(R.id.sure_customPhone_btn).setOnClickListener {
            dialog.dismiss()
        }
    }

    override fun showSendMsgType() {
        mSelectSendTypeAdapter = TagAdapter(activity)
        val view = View.inflate(_mActivity, R.layout.alert_send_type, null)
        val dialog = AlertDialog.Builder(_mActivity).create()
        dialog.setView(view)
        dialog.setCancelable(false)
        dialog.show()
        val selectSendType = view.findViewById<View>(R.id.select_type_target_tag) as FlowTagLayout
        selectSendType.adapter = mSelectSendTypeAdapter
        selectSendType.setOnTagClickListener { parent, _, position ->
            select_send_type!!.text = String.format("已选择: %s", parent.adapter.getItem(position))
            if (parent.adapter.getItem(position) == getString(R.string.select_Advent)) {//临期
                psendtype = "odt"
                scount = "chk"
                //临期 车主和司机的选择
                if (selectSendTag == getString(R.string.send_driver)) {
                    selectDriverSendTypeDetail()
                } else if (selectSendTag == getString(R.string.send_carOwner)) {
                    showSendMsgTypeDetails()
                }
                custom_content!!.isEnabled = false
            } else if (parent.adapter.getItem(position) == getString(R.string.select_pass)) {//过期
                psendtype = "od"
                scount = "chk"
                //过期 车主和司机的选择
                if (selectSendTag == getString(R.string.send_driver)) {
                    selectDriverSendTypeDetail()
                } else if (selectSendTag == getString(R.string.send_carOwner)) {
                    showSendMsgTypeDetails()
                }
                custom_content!!.isEnabled = false
            } else if (parent.adapter.getItem(position) == getString(R.string.select_custom)) {//自定义
                scount = ""
                psendtype = "0"
                custom_content!!.isEnabled = true
            }
            dialog.dismiss()
        }
        initSelectSendType()
    }

    override fun showSendMsgTypeDetails() {
        mSelectSendTypeDetailAdapter = MultiTagAdapter(activity)
        val view = View.inflate(_mActivity, R.layout.alert_select_advent, null)
        val dialog = AlertDialog.Builder(_mActivity).create()
        dialog.setView(view)
        dialog.setCancelable(false)
        dialog.show()
        val selectSendAdvent = view.findViewById<View>(R.id.select_advent_tag) as FlowTagLayout
        val selectAll_btn = view.findViewById<View>(R.id.selectAll_btn) as Button
        val sure_select_btn = view.findViewById<View>(R.id.sure_select_btn) as Button
        selectSendAdvent.setTagCheckedMode(FlowTagLayout.FLOW_TAG_CHECKED_MULTI)//多选
        selectSendAdvent.adapter = mSelectSendTypeDetailAdapter
        selectSendAdvent.setOnTagSelectListener { parent, selectedList, position ->
            //每点一次都会遍历数组，所以每次点击都要清空前一次的数据以防数据重复
            ptplBuffer!!.delete(0, ptplBuffer!!.length)
            for (i in selectedList) {
                val bean = parent.adapter.getItem(i) as MutiTagBean
                ptplBuffer!!.append(bean.type).append("|")
                //LogUtil.LogPrint("ptplBuffer "+ptplBuffer.toString());
            }
            ptpleStr = ptplBuffer!!.toString()
        }
        selectAll_btn.setOnClickListener {
            selectSendAdvent.selectAllOption()
            ptplBuffer!!.delete(0, ptplBuffer!!.length)//全选之前也要清空之前的数据
            for (i in mSelectAdventData!!.indices) {
                val bean = selectSendAdvent.adapter.getItem(i) as MutiTagBean
                ptplBuffer!!.append(bean.type).append("|")
            }
            ptpleStr = ptplBuffer!!.toString()
        }
        sure_select_btn.setOnClickListener {
            // LogUtil.LogPrint("最后结果 "+ptplBuffer);
            dialog.dismiss()
        }
        initSelectAdvent()
    }

    @SuppressLint("SetTextI18n")
    override fun showCheckPhone(phone: String, num: Int, appendPhone: String) {
        phone_count.text = "当前有效号码${num}条"
        custom_phones_num.setText(phone)
        this.send_target = appendPhone
    }

    override fun customPeopleList(vararg list: MutableList<PeopleBean.DataBean.Bean>) {
        arrayList = list.toMutableList()
    }

    override fun showSendMsgStart(msg: String) {
        toast(msg)
    }

    //司机临期和过期发送类型选择
    private fun selectDriverSendTypeDetail() {
        mSelectDriverSendTypeDetailAdapter = MultiTagAdapter(activity)
        val inflater = LayoutInflater.from(activity)
        val nullParent: ViewGroup? = null
        val view = inflater.inflate(R.layout.alert_select_driver_option, nullParent)
        val dialog = MyDialog(activity, 0, 0, view, R.style.DialogTheme)
        dialog.setCancelable(false)
        dialog.show()
        val m = activity!!.windowManager
        val d = m.defaultDisplay // 为获取屏幕宽、高
        val dialogWindow = dialog.window
        val lp = dialogWindow!!.attributes
        dialogWindow.setGravity(Gravity.CENTER)
        lp.x = -1 // 新位置X坐标 负值表示这个被忽略不起作用
        lp.y = -1 // 新位置Y坐标
        lp.width = (d.width * 0.7).toInt() // 宽度
        lp.height = (d.height * 0.07).toInt()
        lp.alpha = 1f // 透明度
        dialogWindow.attributes = lp
        dialog.setCanceledOnTouchOutside(true)//触摸到对话框以外自动关闭对话框
        val selectDriverSend = view.findViewById<View>(R.id.select_driver_tag) as FlowTagLayout
        selectDriverSend.setTagCheckedMode(FlowTagLayout.FLOW_TAG_CHECKED_MULTI)//多选
        selectDriverSend.adapter = mSelectDriverSendTypeDetailAdapter
        selectDriverSend.setOnTagSelectListener { parent, selectedList, position ->
            ptplBuffer!!.delete(0, ptplBuffer!!.length)
            for (i in selectedList) {
                val bean = parent.adapter.getItem(i) as MutiTagBean
                ptplBuffer!!.append(bean.type).append("|")
            }
            ptpleStr = ptplBuffer!!.toString()
            dialog.dismiss()
        }
        initDriverSelect()
    }

    //司机临期和过期选择标签数据
    private fun initDriverSelect() {
        val driverSelect = ArrayList<MutiTagBean>()
        driverSelect.add(MutiTagBean("驾照相关", "type_driverlic"))
        mSelectDriverSendTypeDetailAdapter!!.onlyAddAll(driverSelect)
    }

    //车主临期和过期选择标签数据
    private fun initSelectAdvent() {
        mSelectAdventData = ArrayList()
        mSelectAdventData!!.add(MutiTagBean("托管业务", "type_intrust"))
        mSelectAdventData!!.add(MutiTagBean("季审业务", "type_seasonchk"))
        mSelectAdventData!!.add(MutiTagBean("年审业务", "type_yearchk"))
        mSelectAdventData!!.add(MutiTagBean("年票业务", "type_yeartickt"))
        mSelectAdventData!!.add(MutiTagBean("保险业务", "type_insure"))
        mSelectAdventData!!.add(MutiTagBean("道路运输证办理", "type_tp"))
        mSelectAdventData!!.add(MutiTagBean("道路运输证年审", "type_tpchk"))
        mSelectAdventData!!.add(MutiTagBean("环保标志", "type_epm"))
        mSelectAdventData!!.add(MutiTagBean("建废标识", "type_cwaste"))
        mSelectAdventData!!.add(MutiTagBean("车辆贷款", "type_carloan"))
        mSelectSendTypeDetailAdapter!!.onlyAddAll(mSelectAdventData)
    }

    //通知类型选择标签数据
    private fun initSelectSendType() {
        val selectSendType = ArrayList<String>()
        selectSendType.add(getString(R.string.select_Advent))
        selectSendType.add(getString(R.string.select_pass))
        selectSendType.add(getString(R.string.select_custom))
        mSelectSendTypeAdapter!!.onlyAddAll(selectSendType)
    }

    override fun initSendMsgMap(): Map<String, String> {
        return mapOf(
                "loginid" to create("userInfo").get("loginid", "") as String,
                "compid" to create("userInfo").get("compid", "") as String,
                "cuslist" to cuslistStr,
                "send_target" to send_target!!,
                "psendtype" to psendtype!!,
                "ptpl" to ptpleStr,
                "msg" to mCustomMsg!!,
                "scount" to scount!!)
    }

    override fun showFail(e: Throwable) {
    }

    companion object {

        //自定义人员返回的值
        fun newInstance(list: List<CustomPeopleBean>?, sendTag: String? = "", mCustomPhone: String? = "", target: String? = "", targetType: String? = "", sendtype: String? = "", ptple: String? = ""): SendMessage {
            val args = Bundle()
            if (list != null) {
                args.putSerializable("list", list as Serializable)
            }
            args.putString("sendTag", sendTag)
            args.putString("customPhone", mCustomPhone)
            args.putString("target", target)
            args.putString("targetType", targetType)
            args.putString("sendtype", sendtype)
            args.putString("ptple", ptple)
            val sendMessage = SendMessage()
            sendMessage.arguments = args
            return sendMessage
        }
    }

}

