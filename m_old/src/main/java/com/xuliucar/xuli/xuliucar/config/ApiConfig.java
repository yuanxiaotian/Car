package com.xuliucar.xuli.xuliucar.config;

import com.xuliucar.xuli.xuliucar.bean.AchieveManageBean;
import com.xuliucar.xuli.xuliucar.bean.AssistantDriverBean;
import com.xuliucar.xuli.xuliucar.bean.BusinessInfoBean;
import com.xuliucar.xuli.xuliucar.bean.CarInfoBean;
import com.xuliucar.xuli.xuliucar.bean.CarOwnerDetailBean;
import com.xuliucar.xuli.xuliucar.bean.CarOwnerInfoBean;
import com.xuliucar.xuli.xuliucar.bean.CarsListBean;
import com.xuliucar.xuli.xuliucar.bean.ContractMDetailBean;
import com.xuliucar.xuli.xuliucar.bean.ContractManageBean;
import com.xuliucar.xuli.xuliucar.bean.Counts;
import com.xuliucar.xuli.xuliucar.bean.DoCompanyDocBean;
import com.xuliucar.xuli.xuliucar.bean.ConstructionWasteBean;
import com.xuliucar.xuli.xuliucar.bean.DriverLicenseBean;
import com.xuliucar.xuli.xuliucar.bean.EnvironmentBean;
import com.xuliucar.xuli.xuliucar.bean.HistorySendBean;
import com.xuliucar.xuli.xuliucar.bean.InSuranceBean;
import com.xuliucar.xuli.xuliucar.bean.LimitedBean;
import com.xuliucar.xuli.xuliucar.bean.DoPerpetuaeBean;
import com.xuliucar.xuli.xuliucar.bean.MainDirverBean;
import com.xuliucar.xuli.xuliucar.bean.MessageCountBean;
import com.xuliucar.xuli.xuliucar.bean.PeopleBean;
import com.xuliucar.xuli.xuliucar.bean.StockCarBean;
import com.xuliucar.xuli.xuliucar.bean.YearRoadTBean;
import com.xuliucar.xuli.xuliucar.bean.DoRoadTCBean;
import com.xuliucar.xuli.xuliucar.bean.PassPerPetaueBean;
import com.xuliucar.xuli.xuliucar.bean.TrusteeshipBean;
import com.xuliucar.xuli.xuliucar.bean.YearTicketBean;
import com.xuliucar.xuli.xuliucar.bean.DriverDetailBean;
import com.xuliucar.xuli.xuliucar.bean.HomePageBean;
import com.xuliucar.xuli.xuliucar.bean.InComeBean;
import com.xuliucar.xuli.xuliucar.bean.InfoSumBean;
import com.xuliucar.xuli.xuliucar.bean.LoginInfo;
import com.xuliucar.xuli.xuliucar.bean.MeBean;
import com.xuliucar.xuli.xuliucar.bean.MessageBean;
import com.xuliucar.xuli.xuliucar.bean.NoticeBean;
import com.xuliucar.xuli.xuliucar.bean.OrderManageBean;
import com.xuliucar.xuli.xuliucar.bean.OutComeBean;
import com.xuliucar.xuli.xuliucar.bean.PassPayBean;
import com.xuliucar.xuli.xuliucar.bean.PayInfoBean;
import com.xuliucar.xuli.xuliucar.bean.PendingPayBean;
import com.xuliucar.xuli.xuliucar.bean.StaffDetailBean;
import com.xuliucar.xuli.xuliucar.bean.StatisticsBean;

import java.util.Map;

import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Url;
import rx.Observable;

/**
 * Created by skyward on 2016/11/21.
 */

public interface ApiConfig {

    @FormUrlEncoded
    @POST("app_login")
//登录
    Observable<LoginInfo> getLogin(@Field("compname") String compname, @Field("loginid") String loginid, @Field("password") String password);

    @FormUrlEncoded
    @POST("app_login")
//登录
    Observable<LoginInfo> getLogin(@FieldMap Map<String, Object> map);

    @FormUrlEncoded
    @POST("compinfo")
//首页公司详情
    Observable<HomePageBean> getdata(@Field("loginid") String loginid, @Field("compid") String compid);

    @FormUrlEncoded
    @POST("odalt_count")
//首页事项的数量
    Observable<Counts> getCounts(@Field("loginid") String loginid);

    @FormUrlEncoded
    @POST("notice_list")
//公告列表
    Observable<NoticeBean> getNotices(@Field("loginid") String loginid, @Field("compid") int compid);

    @FormUrlEncoded
    @POST("user_count")
//“我”界面数据
    Observable<MeBean> getMe(@Field("loginid") String loginid, @Field("compid") int compid);

    @FormUrlEncoded
    @POST("carinfo")
//车辆列表
    Observable<CarsListBean> getCarList(@Field("loginid") String loginid, @Field("compid") int compid);

    @FormUrlEncoded
    @POST("carinfo_detail")
//车辆信息
    Observable<CarInfoBean> getCarInfo(@Field("loginid") String loginid, @Field("compid") int compid, @Field("cid") String cid);

    @FormUrlEncoded
    @POST("carinfo_detail")
//营运信息
    Observable<BusinessInfoBean> getBusinessInfo(@Field("loginid") String loginid, @Field("compid") int compid, @Field("cid") String cid);

    @FormUrlEncoded
    @POST("carinfo_detail")
//车主信息
    Observable<CarOwnerInfoBean> getCarOwnerInfo(@Field("loginid") String loginid, @Field("compid") int compid, @Field("cid") String cid);

    @FormUrlEncoded
    @POST("carinfo_detail")
//主班司机
    Observable<MainDirverBean> getMainDriver(@Field("loginid") String loginid, @Field("compid") int compid, @Field("cid") String cid);

    @FormUrlEncoded
    @POST("carinfo_detail")
//副班司机
    Observable<AssistantDriverBean> getAssistantDriver(@Field("loginid") String loginid, @Field("compid") int compid, @Field("cid") String cid);


    @FormUrlEncoded
    @POST("cartypeinfo")
//信息总览
    Observable<InfoSumBean> getInfoSum(@Field("loginid") String loginid, @Field("compid") int compid);

    //库存车辆
    @FormUrlEncoded
    @POST("storage")
    Observable<StockCarBean> getStockCar(@Field("loginid") String loginid, @Field("compid") int compid);

    /*************************************************财务管理*******************************************************************/
    @FormUrlEncoded
    @POST("finance?ftype=income")
//收入明细
    Observable<InComeBean> getIncome(@Field("loginid") String loginid, @Field("compid") int compid);

    @FormUrlEncoded
    @POST("finance?ftype=expense")
//支出明细
    Observable<OutComeBean> getOutCome(@Field("loginid") String loginid, @Field("compid") int compid);

    @FormUrlEncoded
    @POST("finance?ftype=statistics")
//统计查询
    Observable<StatisticsBean> getStatistics(@Field("loginid") String loginid);

    @FormUrlEncoded
    @POST("finance?ftype=payinfo")
//缴费信息
    Observable<PayInfoBean> getPayInfo(@Field("loginid") String loginid, @Field("compid") int compid);

    @FormUrlEncoded
    @POST("finance?ftype=inst_left_m")
//待收款项
    Observable<PendingPayBean> getPendingPay(@Field("loginid") String loginid, @Field("compid") int compid);

    @FormUrlEncoded
    @POST("finance?ftype=inst_od")
//过期款项
    Observable<PassPayBean> getPassPay(@Field("loginid") String loginid, @Field("compid") int compid);
    /*****************************************************************************************************************************/

    /*************************************************销售管理*******************************************************************/
    @FormUrlEncoded
    @POST("sales_order")
//订单管理
    Observable<OrderManageBean> getOrderManage(@Field("loginid") String loginid, @Field("compid") int compid);

    @FormUrlEncoded
    @POST("contract_list")
//合同管理
    Observable<ContractManageBean> getContractManage(@Field("loginid") String loginid, @Field("compid") int compid);

    @FormUrlEncoded
    @POST("contract_detail")
//合同详情
    Observable<ContractMDetailBean> getContractMDetail(@Field("loginid") String loginid, @Field("coid") String coid, @Field("compid") int compid);

    @FormUrlEncoded
    @POST("sales_sum")
//业绩汇总
    Observable<AchieveManageBean> getAchieveManage(@Field("loginid") String loginid, @Field("compid") int compid);

    /*****************************************************************************************************************************/

    /*************************************************待办事项*******************************************************************/

    @FormUrlEncoded
    @POST("odalt_count")
//待办事项数量接口
    Observable<Counts> getDoItemsIndexCounts(@Field("loginid") String loginid, @Field("compid") int compid);

    @FormUrlEncoded
    @POST("odalt?rtype=odt_it")
//托管待办
    Observable<TrusteeshipBean> getDoTrusteeship(@Field("loginid") String loginid, @Field("compid") int compid);

    @FormUrlEncoded
    @POST("odalt?rtype=odt_s")
//季审代办
    Observable<DoPerpetuaeBean> getDoPerpetuae(@Field("loginid") String loginid, @Field("compid") int compid);

    @FormUrlEncoded
    @POST("odalt?rtype=odt_y")
//年审待办
    Observable<LimitedBean> getDoLimited(@Field("loginid") String loginid, @Field("compid") int compid);

    @FormUrlEncoded
    @POST("odalt?rtype=odt_yt")
//年票待办
    Observable<YearTicketBean> getDoYearTicket(@Field("loginid") String loginid, @Field("compid") int compid);

    @FormUrlEncoded
    @POST("odalt?rtype=odt_is")
//保险待办
    Observable<InSuranceBean> getDoInsurance(@Field("loginid") String loginid, @Field("compid") int compid);

    @FormUrlEncoded
    @POST("odalt?rtype=odt_tp")
//道路运输证待办
    Observable<DoRoadTCBean> getDoRoadTC(@Field("loginid") String loginid, @Field("compid") int compid);

    @FormUrlEncoded
    @POST("odalt?rtype=odt_tpychk")
//道路运输证年审
    Observable<YearRoadTBean> getDoradRTCL(@Field("loginid") String loginid, @Field("compid") int compid);

    @FormUrlEncoded
    @POST("odalt?rtype=odt_epm")
//环保标志待办
    Observable<EnvironmentBean> getDoEnvironment(@Field("loginid") String loginid, @Field("compid") int compid);

    @FormUrlEncoded
    @POST("odalt?rtype=odt_cw")
//建废标识待办
    Observable<ConstructionWasteBean> getDoConstructionWast(@Field("loginid") String loginid, @Field("compid") int compid);

    @FormUrlEncoded
    @POST("odalt?rtype=odt_dl")
//驾驶证待办
    Observable<DriverLicenseBean> getDoDriverLicense(@Field("loginid") String loginid, @Field("compid") int compid);

    @FormUrlEncoded
    @POST("odalt?rtype=odt_cl")
    Observable<DoCompanyDocBean> getDoCompanyDoc(@Field("loginid") String loginid, @Field("compid") int compid);

    /*****************************************************************************************************************************/

    /******************************************************过期事项****************************************************************/

    @FormUrlEncoded
    @POST("odalt_count")
//过期事项数量接口
    Observable<Counts> getPassItemIndexCounts(@Field("loginid") String loginid, @Field("compid") int compid);

    @FormUrlEncoded
    @POST("odalt?rtype=od_it")
//托管过期
    Observable<TrusteeshipBean> getPassTrusteeship(@Field("loginid") String loginid, @Field("compid") int compid);

    @FormUrlEncoded
    @POST("odalt?rtype=od_s")
//季审过期
    Observable<PassPerPetaueBean> getPassPerpetaue(@Field("loginid") String loginid, @Field("compid") int compid);

    @FormUrlEncoded
    @POST("odalt?rtype=od_y")
//年审过期
    Observable<LimitedBean> getPassLimited(@Field("loginid") String loginid, @Field("compid") int compid);

    @FormUrlEncoded
    @POST("odalt?rtype=od_yt")
//年票过期
    Observable<YearTicketBean> getPassYearTicket(@Field("loginid") String loginid, @Field("compid") int compid);

    @FormUrlEncoded
    @POST("odalt?rtype=od_is")
//保险过期
    Observable<InSuranceBean> getPassInsurance(@Field("loginid") String loginid, @Field("compid") int compid);

    @FormUrlEncoded
    @POST("odalt?rtype=od_epm")
//环保标志过期
    Observable<EnvironmentBean> getPassEnvironment(@Field("loginid") String loginid, @Field("compid") int compid);

    @FormUrlEncoded
    @POST("odalt?rtype=od_cw")
//建废标识过期
    Observable<ConstructionWasteBean> getPassConstructionWast(@Field("loginid") String loginid, @Field("compid") int compid);

    @FormUrlEncoded
    @POST("odalt?rtype=od_tpychk")
//道路运输证年审过期
    Observable<YearRoadTBean> getPassYearRoadT(@Field("loginid") String loginid, @Field("compid") int compid);

    @FormUrlEncoded
    @POST("odalt?rtype=od_dl")
    Observable<DriverLicenseBean> getPassDriverLicense(@Field("loginid") String loginid, @Field("compid") int compid);

    @FormUrlEncoded
    @POST
    Observable<DriverLicenseBean> Obsolete(@Url String url, @Field("loginid") String loginid, @Field("compid") int compid);


    /*****************************************************************************************************************************/

    @FormUrlEncoded
    @POST("smscount")
    Observable<MessageCountBean> getMessageInfo(@Field("loginid") String loginid, @Field("compid") int compid);

    @FormUrlEncoded
    @POST("smscbn")
    Observable<PeopleBean> getPeople(@Field("loginid") String loginid, @Field("compid") int compid);

    @FormUrlEncoded
    @POST("smshistory")
    Observable<HistorySendBean> getHistorySend(@Field("loginid") String loginid, @Field("compid") int compid);


    @FormUrlEncoded//员工详情
    @POST("user_detail?utype=user")
    Observable<StaffDetailBean> getStaffDetail(@Field("loginid") String loginid, @Field("uid") String uid, @Field("compid") int compid);

    @FormUrlEncoded//司机详情
    @POST("user_detail?utype=driver")
    Observable<DriverDetailBean> getDriverDetail(@Field("loginid") String loginid, @Field("did") String did, @Field("compid") int compid);

    @FormUrlEncoded//车主详情
    @POST("user_detail?utype=owner")
    Observable<CarOwnerDetailBean> getCarOwnerDetail(@Field("loginid") String loginid, @Field("oid") String oid, @Field("compid") int compid);

    @FormUrlEncoded//修改密码
    @POST("cpwd")
    Observable<MessageBean> changpwd(@Field("loginid") String loginid, @Field("compid") int compid, @Field("opassword") String opassword, @Field("npassword") String npassword);

    @FormUrlEncoded//反馈
    @POST("feedback")
    Observable<MessageBean> feedBack(@Field("loginid") String loginid, @Field("compid") int compid, @Field("name") String name, @Field("phone") String phone, @Field("content") String content);

}
