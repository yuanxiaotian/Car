package com.cangmaomao.lib.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.support.v7.widget.AppCompatTextView
import android.util.AttributeSet
import com.cangmaomao.lib.utils.dp2px
import com.cangmaomao.lib.utils.e
import com.cangmaomao.lib.utils.sp2px

class CustomTextView : AppCompatTextView {

    private var flag = false
    private var str = ""
    private val paint = Paint()
    private val paint1 = Paint()
    private val mBound = Rect()

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    fun setVisibility(flag: Boolean, str: String) {
        this.flag = flag
        this.str = str
        this.invalidate()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        if (flag) {
            paint.color = Color.RED
            paint1.style = Paint.Style.FILL
            paint.isAntiAlias = true
            val r = context.dp2px<Int>(10f)

            val cx = (width - r) - paddingTop / 2f
            val cy = r + paddingRight / 2f

            e("$cx")

            canvas.drawCircle(cx, cy, context.dp2px(10f), paint)


            paint1.textSize = context.sp2px(10f)
            paint1.style = Paint.Style.FILL
            paint1.isAntiAlias = true
            paint1.color = Color.WHITE

            paint1.getTextBounds(str, 0, str.length, mBound);
            //防止内容太多造成的显示不全
            if (mBound.width() >= r * 2 || mBound.height() >= r * 2) {
                text = "...";
            }
            canvas.drawText(str, cx - (mBound.width() / 2), cy + (mBound.height() / 2), paint1)
        }

    }


}