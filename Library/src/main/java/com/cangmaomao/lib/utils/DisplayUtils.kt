@file:Suppress("UNCHECKED_CAST")

package com.cangmaomao.lib.utils

import android.app.Activity
import android.app.Fragment
import android.content.Context
import android.util.DisplayMetrics
import android.view.WindowManager


//将px转换为与之相等的dp
fun <T> Context.px2dp(px: Float): T {
    val scale = this.resources.displayMetrics.density
    return (px / scale + 0.5f) as T
}

//将dp转换为与之相等的px
fun <T> Context.dp2px(dp: Float): T {
    val scale = this.resources.displayMetrics.density
    return (dp * scale + 0.5f) as T
}

//将px转换为与之相等的sp
fun <T> Context.px2sp(px: Float): T {
    val scale = this.resources.displayMetrics.scaledDensity
    return (px / scale + 0.5f) as T
}

//将sp转换为与之相等的px
fun <T> Context.sp2px(sp: Float): T {
    val scale = this.resources.displayMetrics.scaledDensity
    return (sp * scale + 0.5f) as T
}

fun width(): Int {
    return DisplayMetrics().widthPixels
}

fun height(): Int {
    return DisplayMetrics().heightPixels
}

fun Activity.width(): Int {
    return this.windowManager.defaultDisplay.width
}

fun Activity.height(): Int {
    return this.windowManager.defaultDisplay.height
}

fun Context.height(): Int {
    return (this as Activity).windowManager.defaultDisplay.height
}

fun Context.width(): Int {
    return (this as Activity).windowManager.defaultDisplay.width
}





