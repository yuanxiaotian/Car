package com.cangmaomao.lib.view

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.support.v7.widget.AppCompatTextView
import android.support.v7.widget.RecyclerView
import android.view.Gravity
import android.view.LayoutInflater
import android.view.ViewGroup
import com.cangmaomao.lib.R
import com.cangmaomao.lib.adapter.SheetDialogAdapter
import com.cangmaomao.lib.bean.SheetDialogData
import com.cangmaomao.lib.utils.initRecycler
import com.cangmaomao.recyclerview.adapter.contract.OnItemClick

class SheetDialog(val context: Context) {

    private lateinit var dialog: Dialog
    private lateinit var tv_title: AppCompatTextView
    private lateinit var recyclerView: RecyclerView

    private lateinit var itemList: List<SheetDialogData>

    @SuppressLint("RtlHardcoded")
    fun builder(): SheetDialog {
        val view = LayoutInflater.from(context).inflate(R.layout.sheet_dialog_view, null)

        tv_title = view.findViewById(R.id.tv_title)
        recyclerView = view.findViewById(R.id.recyclerView)

        //取消
        view.findViewById<AppCompatTextView>(R.id.tv_cancel).setOnClickListener {
            dialog.dismiss()
        }


        dialog = Dialog(context, R.style.DialogTheme)
        dialog.setContentView(view)
        dialog.window.setGravity(Gravity.BOTTOM)
        val lp = dialog.window.attributes
        lp.width = ViewGroup.LayoutParams.MATCH_PARENT
        dialog.window.attributes = lp
        return this
    }

    //标题名字
    fun setTitle(str: String): SheetDialog {
        tv_title.text = str
        return this
    }

    //点击dialog是否允许消失
    fun setCancelable(cancel: Boolean): SheetDialog {
        dialog.setCancelable(cancel)
        return this
    }

    //添加数据
    fun addDialogList(itemList: List<SheetDialogData>, onItemClick: OnItemClick): SheetDialog {
        this.itemList = itemList
        context.initRecycler(recyclerView, R.color.c_e8)
        val mAdapter = SheetDialogAdapter(R.layout.item_sheet_dialog_view)
        recyclerView.adapter = mAdapter
        mAdapter.addList(itemList)
        mAdapter.setOnItemClickListener(onItemClick)
        return this
    }

    fun show() {
        dialog.show()
    }

}