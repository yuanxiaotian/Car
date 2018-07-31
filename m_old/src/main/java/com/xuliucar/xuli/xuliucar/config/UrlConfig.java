package com.xuliucar.xuli.xuliucar.config;

/**
 * Created by skyward on 2016/10/14.
 *
 * url配置文件
 */

public class UrlConfig {



    public static final String register_url="http://www.gzxlxx.com:8866/index.php/Home/Account/request_c_account";//注册
    public static final String checkReg_url="http://www.gzxlxx.com:8866/index.php/Home/Account/checkapply";//注册检测
    public static final String uploadRegImg_url="http://www.gzxlxx.com:8866/index.php/Home/Upload/regup";//注册的营业执照上传

    //所有车辆模块
    public static final String insuranceInfo_url="http://www.gzxlxx.com:8866/index.php/Home/App/carinfo_detail";//保险信息

    //财务管理模块
    public static final String payInfoDetail_url="http://www.gzxlxx.com:8866/index.php/Home/App/payinfo_detail";//缴费信息详情

    //销售管理模块
    public static final String orderDetail_url="http://www.gzxlxx.com:8866/index.php/Home/App/sales_order_detail";//订单详情

    //'me'模块
    public static final String staff_url="http://www.gzxlxx.com:8866/index.php/Home/App/user_list?utype=user";//员工
    public static final String meDriver_url="http://www.gzxlxx.com:8866/index.php/Home/App/user_list?utype=driver";//司机
    public static final String carOwner_url="http://www.gzxlxx.com:8866/index.php/Home/App/user_list?utype=owner";//车主
    public static final String informationCenter_url="http://www.gzxlxx.com:8866/index.php/Home/App/noteinfo";//信息中心


    /**
     * 下面是上传数据的
     */
    //车辆信息的
    public static final String ownerregistration1_url="http://www.gzxlxx.com:8866/index.php/Home/Upload/index?ptype=ownerregistration1";//行驶证正本正面
    public static final String ownerregistration2_url="http://www.gzxlxx.com:8866/index.php/Home/Upload/index?ptype=ownerregistration2";//行驶证正本反面
    public static final String ownerregistration3_url="http://www.gzxlxx.com:8866/index.php/Home/Upload/index?ptype=ownerregistration3";//行驶证副本正面
    public static final String ownerregistration4_url="http://www.gzxlxx.com:8866/index.php/Home/Upload/index?ptype=ownerregistration4";//行驶证副本反面
    public static final String carreg_url="http://www.gzxlxx.com:8866/index.php/Home/Upload/index?ptype=carreg";//机动车登记书首页
    public static final String carregtransfer_url="http://www.gzxlxx.com:8866/index.php/Home/Upload/index?ptype=carregtransfer";//机动车登记证书过户页
    public static final String carcert_url="http://www.gzxlxx.com:8866/index.php/Home/Upload/index?ptype=carcert";//机动车合格证
    public static final String invoice_url="http://www.gzxlxx.com:8866/index.php/Home/Upload/index?ptype=invoice";//购发车票
    public static final String tax_url="http://www.gzxlxx.com:8866/index.php/Home/Upload/index?ptype=tax";//完税证明
    public static final String cwastef_url="http://www.gzxlxx.com:8866/index.php/Home/Upload/index?ptype=cwastef";//建废运输标识正面
    public static final String cwasteb_url="http://www.gzxlxx.com:8866/index.php/Home/Upload/index?ptype=cwasteb";//建废运输标识反面
    //保险信息的
    public static final String is1_url="http://www.gzxlxx.com:8866/index.php/Home/Upload/index?ptype=is1";//交强险保单
    public static final String is2_url="http://www.gzxlxx.com:8866/index.php/Home/Upload/index?ptype=is2";//商业险保单
    //营运信息的
    public static final String certf_url="http://www.gzxlxx.com:8866/index.php/Home/Upload/index?ptype=certf";//运营证正面照
    public static final String certb_url="http://www.gzxlxx.com:8866/index.php/Home/Upload/index?ptype=certb";//运营证反面照
    public static final String pic45d_url="http://www.gzxlxx.com:8866/index.php/Home/Upload/index?ptype=pic45d";//车身45°灭火器照片
    //车主信息的
    public static final String ownerid1_url="http://www.gzxlxx.com:8866/index.php/Home/Upload/index?ptype=ownerid1";//身份证正面照
    public static final String ownerid2_url="http://www.gzxlxx.com:8866/index.php/Home/Upload/index?ptype=ownerid2";//身份证反面照
    public static final String ownertmpid_url="http://www.gzxlxx.com:8866/index.php/Home/Upload/index?ptype=ownertmpid";//暂住证正面照
    public static final String ownertmpidbb_url="http://www.gzxlxx.com:8866/index.php/Home/Upload/index?ptype=ownertmpidbb";//暂住证反面照片
    //主班司机
    public static final String driverid1_url="http://www.gzxlxx.com:8866/index.php/Home/Upload/index?ptype=driverid1";//身份证正面
    public static final String driverid2_url="http://www.gzxlxx.com:8866/index.php/Home/Upload/index?ptype=driverid2";//身份证反面
    public static final String driverlic1_url="http://www.gzxlxx.com:8866/index.php/Home/Upload/index?ptype=driverlic1";//驾驶证正本正面照片
    public static final String driverlic3_url="http://www.gzxlxx.com:8866/index.php/Home/Upload/index?ptype=driverlic3";//驾驶证副本正面照
    public static final String drivercert_url="http://www.gzxlxx.com:8866/index.php/Home/Upload/index?ptype=drivercert";//从业资格证正面照片
    public static final String drivercertb_url="http://www.gzxlxx.com:8866/index.php/Home/Upload/index?ptype=drivercertb";//从业资格证反面照片
    public static final String driversafe_url="http://www.gzxlxx.com:8866/index.php/Home/Upload/index?ptype=driversafe";//驾驶员行车安全承诺书
    public static final String drivertmpid_url="http://www.gzxlxx.com:8866/index.php/Home/Upload/index?ptype=drivertmpid";//暂住证正面
    public static final String drivertmpidb_url="http://www.gzxlxx.com:8866/index.php/Home/Upload/index?ptype=drivertmpidb";//暂住证反面
    public static final String driverlic4_url="http://www.gzxlxx.com:8866/index.php/Home/Upload/index?ptype=driverlic4";//驾驶证副本反面
    //副班司机
    public static final String driver2id1_url="http://www.gzxlxx.com:8866/index.php/Home/Upload/index?ptype=driver2id1";//身份证正面
    public static final String driver2id2_url="http://www.gzxlxx.com:8866/index.php/Home/Upload/index?ptype=driver2id2";//身份证反面
    public static final String driver2lic1_url="http://www.gzxlxx.com:8866/index.php/Home/Upload/index?ptype=driver2lic1";//驾驶证正本正面照片
    public static final String driver2lic3_url="http://www.gzxlxx.com:8866/index.php/Home/Upload/index?ptype=driver2lic3";//驾驶证副本正面照片
    public static final String driver2cert_url="http://www.gzxlxx.com:8866/index.php/Home/Upload/index?ptype=driver2cert";//从业资格证正面
    public static final String driver2certb_url="http://www.gzxlxx.com:8866/index.php/Home/Upload/index?ptype=driver2certb";//从业资格证反面
    public static final String driver2safe_url="http://www.gzxlxx.com:8866/index.php/Home/Upload/index?ptype=driver2safe";//驾驶员行车安全承诺证书
    public static final String driver2tmpid_url="http://www.gzxlxx.com:8866/index.php/Home/Upload/index?ptype=driver2tmpid";//暂住证正面
    public static final String driver2tmpidb_url="http://www.gzxlxx.com:8866/index.php/Home/Upload/index?ptype=driver2tmpidb";//暂住证反面
    public static final String driver2lic4_url="http://www.gzxlxx.com:8866/index.php/Home/Upload/index?ptype=driver2lic4";//驾驶证副本反面
    //其它证件
    public static final String carpics_url="http://www.gzxlxx.com:8866/index.php/Home/Upload/index?ptype=carpics";//车辆照片
    //人员登记
    public static final String userid1_url="http://www.gzxlxx.com:8866/index.php/Home/Upload/index?ptype=userid1";//身份证正面
    public static final String mc1_url="http://www.gzxlxx.com:8866/index.php/Home/Upload/index?ptype=mc1";//主要负责人证书
    public static final String sd1_url="http://www.gzxlxx.com:8866/index.php/Home/Upload/index?ptype=sd1";//安全主任照片
    //公司证件
    public static final String compstr_url="http://www.gzxlxx.com:8866/index.php/Home/Upload/index?ptype=compstr";//组织机构代码副本
    public static final String ltax_url="http://www.gzxlxx.com:8866/index.php/Home/Upload/index?ptype=ltax";//地税证副本照片
    public static final String ctax_url="http://www.gzxlxx.com:8866/index.php/Home/Upload/index?ptype=ctax";//国税证副本照片
    public static final String tpermit_url="http://www.gzxlxx.com:8866/index.php/Home/Upload/index?ptype=tpermit";//道路运输许可证副本



}
