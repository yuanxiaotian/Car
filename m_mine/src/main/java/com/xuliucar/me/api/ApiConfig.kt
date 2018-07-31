package com.xuliucar.me.api

import com.xuliucar.me.model.*
import io.reactivex.Observable
import retrofit2.http.Field
import retrofit2.http.POST
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Url


interface ApiConfig {

    //登录
    @FormUrlEncoded
    @POST("app_login")
    fun getLogin(@Field("compname") compname: String, @Field("loginid") loginid: String, @Field("password") password: String): Observable<LoginInfo>

    //“我”界面数据
    @FormUrlEncoded
    @POST("user_count")
    fun getMe(@Field("loginid") loginid: String, @Field("compid") compid: String): Observable<MeBean>

    //首页公司详情
    @FormUrlEncoded
    @POST("compinfo")
    fun companyInfo(@Field("loginid") loginid: String, @Field("compid") compid: String): Observable<CompanyInfo>

    //通知消息 -未读
    @FormUrlEncoded
    @POST("noteread")
    fun noteread(@Field("loginid") loginid: String, @Field("compid") compid: String, @Field("messageid") messageid: String): Observable<NoteBean>

    //通知消息
    @FormUrlEncoded
    @POST("noteinfo")
    fun noteInfo(@Field("loginid") loginid: String, @Field("compid") compid: String): Observable<Noteinfo>

    //通知消息-删除
    @FormUrlEncoded
    @POST("notedel")
    fun noteInfoDel(@Field("loginid") loginid: String, @Field("compid") compid: String, @Field("messageid") messageid: String): Observable<NoteBean>

    //通知消息-全部删除
    @FormUrlEncoded
    @POST("notedel/deltype/all")
    fun noteInfoDelAll(@Field("loginid") loginid: String, @Field("compid") compid: String): Observable<NoteBean>

    @FormUrlEncoded//反馈
    @POST("feedback")
    fun feedBack(@Field("loginid") loginid: String, @Field("compid") compid: String, @Field("name") name: String, @Field("phone") phone: String, @Field("content") content: String): Observable<MessageBean>

    //人员 司机 车主
    @FormUrlEncoded
    @POST
    fun peopleInfo(@Url url: String, @Field("loginid") loginid: String, @Field("compid") compid: String): Observable<PeopleData>

    //员工详情
    @FormUrlEncoded
    @POST("user_detail?utype=user")
    fun userDetail(@Field("loginid") loginid: String, @Field("compid") compid: String, @Field("uid") uid: String): Observable<PeopleDetails>

    //司机详情
    @FormUrlEncoded
    @POST("user_detail?utype=driver")
    fun driverDetail(@Field("loginid") loginid: String, @Field("compid") compid: String, @Field("did") did: String): Observable<PeopleDetails>

    //车主详情
    @FormUrlEncoded
    @POST("user_detail?utype=owner")
    fun ownerDetail(@Field("loginid") loginid: String, @Field("compid") compid: String, @Field("oid") oid: String): Observable<PeopleDetails>

}