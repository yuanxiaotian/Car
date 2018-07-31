package com.cangmaomao.lib.view

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Path
import android.graphics.RectF
import android.util.AttributeSet
import android.widget.ImageView

class RoundImageView: ImageView {
    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)


    private val rids = listOf(10f, 10f, 10f, 10f, 10f, 10f, 10f, 10f)

    @SuppressLint("DrawAllocation")
    override fun onDraw(canvas: Canvas?) {

        val path = Path()

        val w = this.width.toFloat()
        val h = this.height.toFloat()


        path.addRoundRect(RectF(0f, 0f, w, h), rids.toFloatArray(), Path.Direction.CW)

        canvas!!.clipPath(path)

        super.onDraw(canvas)
    }


}