package com.cangmaomao.lib.utils

import com.google.gson.Gson

inline fun <reified T : Any> Gson.fromJson(json: String): T {
    return fromJson(json, T::class.java)
}

inline fun <reified T : Any> Gson.toJson(): String {
    return toJson(T::class.java)
}