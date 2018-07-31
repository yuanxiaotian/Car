package com.xuliucar.xuli.xuliucar.newmvp.view.wait.child

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.AppCompatImageView
import android.view.View
import com.cangmaomao.lib.base.BaseNewFragment
import com.cangmaomao.lib.bean.SheetDialogData
import com.cangmaomao.lib.utils.SPUtils.create
import com.cangmaomao.lib.utils.initRecycler
import com.cangmaomao.lib.utils.toast
import com.cangmaomao.lib.view.SheetDialog
import com.cangmaomao.recyclerview.adapter.CMMAdapter
import com.cangmaomao.recyclerview.adapter.contract.OnItemClick
import com.xuliucar.xuli.xuliucar.R
import com.xuliucar.xuli.xuliucar.bean.PassPerPetaueBean
import com.xuliucar.xuli.xuliucar.newmvp.adapter.TrusteeshipAdapter
import com.xuliucar.xuli.xuliucar.newmvp.contract.ObsoleteItemContract
import com.xuliucar.xuli.xuliucar.newmvp.contract.WaitContract
import com.xuliucar.xuli.xuliucar.newmvp.presenter.wait.child.ChildWaitPresenter
import kotlinx.android.synthetic.main.matter_layout.*

class ChildWait : BaseNewFragment<WaitContract.ChildPresenter>(), View.OnClickListener, OnItemClick, WaitContract.ChildView {

    lateinit var adapter: TrusteeshipAdapter
    private var msg: String? = "1"
    private lateinit var toolbar_view: AppCompatImageView

    override fun layViewId(): Int = R.layout.matter_layout
    override fun addViewId(): Int = 0
    override fun context(): Context = _mActivity
    override fun itemFlag(): String = arguments!!.getString("flag")
    override fun companyId(): String = create("userInfo").get("compid", "") as String
    override fun loginId(): String = create("userInfo").get("loginid", "") as String

    override fun initView(savedInstanceState: Bundle?, view: View) {
        toolbar_view = initToolbar(getString(R.string.Todo), null, R.drawable.qunfa)
        toolbar_view.setOnClickListener(this)
        matter_select_all.setOnClickListener(this)
        bt_cancel.setOnClickListener(this)
        sure.setOnClickListener(this)
        initRecycler(recyclerView)
        adapter = TrusteeshipAdapter(R.layout.perpetuae_item)
        recyclerView.adapter = adapter
        adapter.setOnItemClickListener(this)
        ChildWaitPresenter(this)
        p.start()

        adapter.notifyDataSetChanged()
    }

    override fun showDate(data: MutableList<PassPerPetaueBean.DataBean.InfoBean>) {
        adapter.addList(data)
    }

    override fun showFail(e: Throwable) {
    }

    override fun showAllSelect(flag: Boolean) {
        if (flag) {
            bt_cancel.visibility = View.VISIBLE
            sure.visibility = View.VISIBLE
            matter_select_all_layout.visibility = View.VISIBLE
        } else {
            bt_cancel.visibility = View.GONE
            sure.visibility = View.GONE
            matter_select_all_layout.visibility = View.GONE
        }
    }

    override fun notifyDataSetChanged(position: Int) {
        if (position <= 0) adapter.notifyDataSetChanged() else adapter.notifyItemChanged(position)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1001 && resultCode == 1002) {
            val bundle = data.extras
            msg = bundle!!.getString("msg")
        }
    }

    override fun onClick(view: View) {
        when (view) {
            toolbar_view -> p.showCheckBox(adapter.data, true)
            matter_select_all -> p.allCheck(adapter.data)
            bt_cancel -> p.cancelCheck(adapter.data, false)
            sure -> {
                if (p.checkUpSelect(adapter.data))
                    SheetDialog(_mActivity).builder()
                            .setTitle("发送短信")
                            .setCancelable(true)
                            .addDialogList(listOf(SheetDialogData("本地短信", R.color.c_1e), SheetDialogData("系统短信", R.color.c_1e)), OnItemClick { p0, p1, p2 -> })
                            .show()
                else
                    toast("请选择2个或以上后进行操作!")

            }
        }
    }

    override fun onItemClick(p0: CMMAdapter<*>?, p1: Any?, p2: Int) {
        val data = p1 as PassPerPetaueBean.DataBean.InfoBean
        data.isSelect = !data.isSelect
        notifyDataSetChanged(p2)
    }


}
