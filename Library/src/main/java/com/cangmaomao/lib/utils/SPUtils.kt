package com.cangmaomao.lib.utils

import android.content.Context
import android.content.SharedPreferences
import android.support.v4.app.Fragment
import androidx.core.content.edit

object SPUtils {


    lateinit var sharedPreferences: SharedPreferences

    fun Context.create(name: String): SPUtils {
        sharedPreferences = this.getSharedPreferences(name, Context.MODE_PRIVATE)
        return SPUtils
    }

    fun Fragment.create(name: String): SPUtils {
        sharedPreferences = activity!!.getSharedPreferences(name, Context.MODE_PRIVATE)
        return SPUtils
    }


    fun add(key: String, value: Any): SPUtils {
        sharedPreferences.edit {
            when (value) {
                is String -> putString(key, value)
                is Int -> putInt(key, value)
                is Float -> putFloat(key, value)
                is Boolean -> putBoolean(key, value)
                is Long -> putLong(key, value)
                else -> throw IllegalStateException("传递参数异常!")
            }
        }
        return this
    }


    fun get(key: String, defValue: Any): Any = when (defValue) {
        is String -> sharedPreferences.getString(key, "")
        is Int -> sharedPreferences.getInt(key, 0)
        is Float -> sharedPreferences.getFloat(key, 0.0f)
        is Boolean -> sharedPreferences.getBoolean(key, true)
        is Long -> sharedPreferences.getLong(key, 0L)
        is Set<*> -> sharedPreferences.getStringSet(key, HashSet<String>())
        else -> throw IllegalStateException("传递参数异常!")
    }


    fun removeKey(key: String): SPUtils {
        sharedPreferences.edit {
            remove(key)
        }
        return this
    }

    fun removeAll(): Unit = sharedPreferences.edit { clear() }

}