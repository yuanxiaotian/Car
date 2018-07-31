package com.xuliucar.xuli.xuliucar.config;

import com.xuliucar.xuli.xuliucar.base.App;
import com.xuliucar.xuli.xuliucar.utils.PreferencesUtils;

/**
 * Created by skyward on 2016/12/13.
 * email：
 */

public class StringConfig {
    public static final  String COMPANYNAME = PreferencesUtils.getSharePreStr(App.getContext(),"userInfo","companyName");

    public static final String TYPE_INTRUST="type_intrust";
    public static final String TYPE_SEASONCHK="type_seasonchk";
    public static final String TYPE_YEARCHK="type_yearchk";
    public static final String TYPE_YEARTICKT="type_yeartickt";
    public static final String TYPE_INSURE="type_insure";
    public static final String TYPE_TP="type_tp";
    public static final String TYPE_TPCHK="type_tpchk";
    public static final String TYPE_EPM="type_epm";
    public static final String TYPE_CWASTE="type_cwaste";
    public static final String TYPE_DRIVERLIC="type_driverlic";

    public static final String ODT_INTRUST="尊敬的车主，您好！您的委托管理合同即将到期,请尽快与车队联系,谢谢。";
    public static final String ODT_SEASONCHK="尊敬的车主，您好。您的季审即将到期,请尽快与车队联系,谢谢。";
    public static final String ODT_YEARCHK="尊敬的车主，您好。您的年审即将到期,请尽快与车队联系,谢谢。";
    public static final String ODT_YEARTICKT="尊敬的车主，您好。您的年票即将到期,请尽快与车队联系,谢谢。";
    public static final String ODT_TP="尊敬的车主，您好!您的道路运输证尚未办理,请尽快与车队联系,谢谢";
    public static final String ODT_TPCHK="尊敬的车主，您好。您的道路运输证即将过期,请尽快与车队联系,谢谢。";
    public static final String ODT_EPM="尊敬的车主，您好。您的环保标志即将过期,请尽快与车队联系,谢谢。";
    public static final String ODT_CWASTE="尊敬的车主，您好。您的建筑废弃物运输车辆标识即将到期,请尽快与车队联系,谢谢。";
    public static final String ODT_DRIVERLIC="尊敬的车主，您好。您的驾驶证尚未办理,请尽快与车队联系,谢谢。";

    public static final String OD_INTRUST="尊敬的车主，您好！您的委托管理合同已到期,请尽快与车队联系,谢谢。";
    public static final String OD_SEASONCHK="尊敬的车主，您好。您的季审已到期,请尽快与车队联系,谢谢。";
    public static final String OD_YEARCHK="尊敬的车主，您好。您的年审已到期,请尽快与车队联系,谢谢。";
    public static final String OD_YEARTICKT="尊敬的车主，您好。您的年票已到期,请尽快与车队联系,谢谢。";
    public static final String OD_TPCHK="尊敬的车主，您好。您的道路运输证已过期,请尽快与车队联系,谢谢。";
    public static final String OD_EPM="尊敬的车主，您好。您的环保标志已过期,请尽快与车队联系,谢谢。";
    public static final String OD_CWASTE="尊敬的车主，您好。您的建筑废弃物运输车辆标识已到期,请尽快与车队联系,谢谢。";
    public static final String OD_DRIVERLIC="尊敬的车主，您好。您的驾驶证已到期,请尽快与车队联系,谢谢。";
}
