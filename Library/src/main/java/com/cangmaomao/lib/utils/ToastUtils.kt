package com.cangmaomao.lib.utils

import android.content.Context
import android.support.v4.app.Fragment
import android.widget.Toast

fun shortToast(context: Context, msg: String?) {
    Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
}

fun Context.toast(msg: String, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, msg, duration).show()
}

fun Fragment.toast(msg: String, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(activity, msg, duration).show()
}
