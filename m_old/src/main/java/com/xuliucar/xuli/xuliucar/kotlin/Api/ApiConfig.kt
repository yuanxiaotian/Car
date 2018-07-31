package com.xuliucar.xuli.xuliucar.kotlin.Api

import com.xuliucar.xuli.xuliucar.bean.Counts
import com.xuliucar.xuli.xuliucar.bean.HomePageBean
import io.reactivex.Observable
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ApiConfig {

    @FormUrlEncoded
    @POST("odalt_count")
    fun getCounts(@Field("loginid") loginid: String): Observable<Counts>


    @FormUrlEncoded
    @POST("compinfo")
    fun getdata(@Field("loginid") loginid: String, @Field("compid") compid: String): Observable<HomePageBean>

}