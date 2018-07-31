package com.xuliucar.me.ui.fragment

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.support.v7.widget.AppCompatImageView
import android.view.View
import android.view.ViewGroup
import com.cangmaomao.lib.base.BaseNewFragment
import com.cangmaomao.lib.bean.SheetDialogData
import com.cangmaomao.lib.utils.SPUtils.create
import com.cangmaomao.lib.utils.initRecycler
import com.cangmaomao.lib.utils.toast
import com.cangmaomao.lib.view.SheetDialog
import com.cangmaomao.recyclerview.adapter.CMMAdapter
import com.cangmaomao.recyclerview.adapter.contract.OnItemClick
import com.xuliucar.me.R
import com.xuliucar.me.adapter.InformationAdapter
import com.xuliucar.me.contract.InformationContract
import com.xuliucar.me.model.Noteinfo
import com.xuliucar.me.presenter.InformationPresenter
import com.yanzhenjie.recyclerview.swipe.SwipeMenuItem
import kotlinx.android.synthetic.main.information_center.*

class InformationCenter : BaseNewFragment<InformationContract.Presenter>(), InformationContract.View, OnItemClick {

    private lateinit var toolbar_del: AppCompatImageView
    private lateinit var mAdapter: InformationAdapter

    override fun layViewId(): Int = R.layout.information_center
    override fun addViewId(): Int = 0
    override fun context(): Context = _mActivity
    override fun companyId(): String = create("userInfo").get("compid", "") as String
    override fun loginId(): String = create("userInfo").get("loginid", "") as String

    override fun initView(savedInstanceState: Bundle?, view: View) {
        toolbar_del = initToolbar(getString(R.string.information_center), null, R.drawable.ic_delete)

        //初始化recyclerView 分割线等
        initRecycler(recyclerView)

        //初始化recyclerView侧滑出现删除菜单
        recyclerView.setSwipeMenuCreator { _, swipeRightMenu, _ ->
            val right = SwipeMenuItem(context())
                    .setBackground(R.color.c_reg)
                    .setText("删除")
                    .setTextColor(Color.WHITE)
                    .setHeight(ViewGroup.LayoutParams.MATCH_PARENT)
                    .setWidth(165)
            swipeRightMenu.addMenuItem(right)
        }

        //监听侧滑菜单的点击事件
        recyclerView.setSwipeMenuItemClickListener {
            it.closeMenu()
            val position = it.adapterPosition
            val id = mAdapter.data[position].itemid
            p.delSingle(id)
        }

        //适配器
        mAdapter = InformationAdapter(R.layout.informain_center_item)
        mAdapter.setOnItemClickListener(this)
        recyclerView.adapter = mAdapter


        InformationPresenter(this)
        p.start()

        toolbar_del.setOnClickListener { p.delAll() }
    }

    override fun showData(data: MutableList<Noteinfo.DataBean>) {
        mAdapter.addList(data)
    }

    override fun showState(msg: String) {
        toast(msg)
    }

    override fun notifyDataSetChanged(position: Int, flag: Int) {
        when (flag) {
            1 -> mAdapter.remove(position)
            2 -> mAdapter.clear()
            3 -> {
                mAdapter.data[position].isread = "1"
                mAdapter.notifyItemChanged(position)
            }
        }
    }

    override fun onItemClick(p0: CMMAdapter<*>?, p1: Any?, p2: Int) {
        val itemData = mAdapter.data[p2]
        SheetDialog(context())
                .builder()
                .setTitle("消息操作")
                .addDialogList(listOf(SheetDialogData("标记已读", R.color.c_1e), SheetDialogData("标记未读", R.color.c_1e)), object : OnItemClick {
                    override fun onItemClick(p0: CMMAdapter<*>?, d: Any, p2: Int) {
                        p.noteRead(itemData.itemid, p2)
                    }

                })
                .show()
    }

    override fun showFail(e: Throwable) {

    }

}
