package com.xuliucar.xuli.xuliucar.newmvp.popwindow.messageManage

import android.app.Activity
import android.content.Context
import android.graphics.drawable.ColorDrawable
import android.support.v7.widget.AppCompatButton
import android.support.v7.widget.RecyclerView
import android.view.*
import android.widget.LinearLayout
import android.widget.PopupWindow
import com.cangmaomao.lib.utils.height
import com.cangmaomao.lib.utils.initRecycler
import com.cangmaomao.lib.utils.width
import com.xuliucar.xuli.xuliucar.R
import com.xuliucar.xuli.xuliucar.bean.PeopleBean
import com.xuliucar.xuli.xuliucar.newmvp.adapter.CustomPeoplePopViewAdapter

class CustomPeopleView(context: Context, arrayList: MutableList<MutableList<PeopleBean.DataBean.Bean>>, view: View)
    : PopupWindow(view, WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT) {


    init {
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
        context.initRecycler(recyclerView)
        recyclerView.adapter = CustomPeoplePopViewAdapter(R.layout.item_custom_people_pop_view, arrayList)

        //取消事件
        view.findViewById<AppCompatButton>(R.id.bt_cancel).setOnClickListener { this.dismiss() }

        //设置view
        contentView = view

        val c = (context as Activity)

        width = (c.width() / 1.5).toInt()

        height = WindowManager.LayoutParams.MATCH_PARENT


        //设置进入动画
        animationStyle = R.style.pop_style

        //设置背景（消失问题）
        setBackgroundDrawable(ColorDrawable())

        //设置可获取焦点
        isFocusable = true

        //设置点击外部可消失
        isOutsideTouchable = true

        //设置可触摸
        isTouchable = true

        //设置显示灰色背景
        backgroundAlpha(c, 0.5f);

        //处理底部虚拟按键
        softInputMode = WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE

        //设置消失后恢复颜色
        setOnDismissListener { backgroundAlpha(c, 1.0f); }

        //外部消失的方法
        setTouchInterceptor { _, _ -> false }

        showAtLocation(context.layoutInflater.inflate(R.layout.message_manage_index, null).findViewById(R.id.pop_view), Gravity.NO_GRAVITY, c.width(), 0)

    }

    /**
     * 设置添加屏幕的背景透明度
     * @param bgalpha
     */
    private fun backgroundAlpha(context: Activity, bgalpha: Float) {
        val lp = context.window.attributes
        lp.alpha = bgalpha //0.0-1.0
        context.window.attributes = lp
    }


}

