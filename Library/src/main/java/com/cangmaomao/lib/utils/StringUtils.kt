package com.cangmaomao.lib.utils

import android.content.Context
import android.text.TextUtils

fun isEmpty(c: Context, str: String): Boolean {
    if (TextUtils.isEmpty(str)) {
        shortToast(c, "参数错误!")
        return false
    } else {
        return true
    }
}