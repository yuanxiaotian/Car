package com.xuliucar.xuli.xuliucar.bean;

import com.google.gson.annotations.SerializedName;

public class InsuranceInfoData {

    /**
     * success : true
     * error : null
     * data : {"uid":"NC-1001-1628","cinfo":{"carprice":"0.00","utype":"营运","platetype":"","fnum":"LVBV3JBB5DE049398","enum":"89612500","loadp":"3","filenum":"0-1 NO2","trstime":"2018-03-01","tretime":"2021-03-01","olength":"5995","owidth":"2150","oheight":"2900","cwcode":"","cwlevel":"","cwstime":"","cwetime":"","p1":"","p1_thumb":"","p6":"","p6_thumb":"","p2":"","p2_thumb":"","p7":"","p7_thumb":"","p3":"","p3_thumb":"","p8":"","p8_thumb":"","p4":"","p4_thumb":"","p5":"","p5_thumb":"","p9":"","p9_thumb":"","p10":"","p10_thumb":"","p11":"","p11_thumb":"","isown":"0","ccolor":"蓝色","0":"柴油","yregtime":"2014-02-24","ychketime":"2019-02","empchkstime":"","empchketime":"2019-02-28"},"isinfo":{"insure1sdate":"","insure1edate":"","is1comp":"","is1num":"","is1amount":"0.00","is1tax":"未选择","p1_thumb":"","p1":"","insure2sdate":"","insure2edate":"","is2comp":"","is2num":"","is2thirdp":"0","is2amount":"0.00","is2infolist":{"childtype_1":{"type_name":"车损险","indemnity_norm":null,"abatement":"未投保","base":null,"coefficient":null,"total":null},"childtype_2":{"type_name":"三者险","indemnity_norm":"0","abatement":"未投保","base":null,"coefficient":null,"total":null},"childtype_3":{"type_name":"盗抢险","indemnity_norm":null,"abatement":"未投保","base":null,"coefficient":null,"total":null},"childtype_4":{"type_name":"人员(司机)险","indemnity_norm":null,"abatement":"未投保","base":null,"coefficient":null,"total":null},"childtype_5":{"type_name":"人员(车主)险","indemnity_norm":null,"abatement":"未投保","base":null,"coefficient":null,"total":null},"childtype_6":{"type_name":"玻璃险","indemnity_norm":"进口玻璃","abatement":"--","base":null,"coefficient":null,"total":null},"childtype_7":{"type_name":"划痕险","indemnity_norm":null,"abatement":"未投保","base":null,"coefficient":null,"total":null},"childtype_8":{"type_name":"自然损失险","indemnity_norm":null,"abatement":"未投保","base":null,"coefficient":null,"total":null},"childtype_9":{"type_name":"新增设备损失险","indemnity_norm":null,"abatement":"未投保","base":null,"coefficient":null,"total":null}},"p2":"","p2_thumb":""},"ctinfo":{"compname":"demo","weight":"4025000","osize":"5995mm * 2150mm * 2900mm","tpdate":"","tpstr":"广州市**区交通管理总站","tpnum":"","p1_thumb":"","p1":"","p2_thumb":"","p2":"","p3_thumb":"","p3":"","modelname":"BJ5049CCY-BG","cartype":""},"oinfo":{"name":"张柏干","sex":"女","oidnum":"441821196508241E17","oidaddr":"广东省佛冈县迳头镇大陂村委下埕村47号","oidnowaddr":"广东省佛冈县迳头镇大陂村委下埕村47号","ophonenum":"13719255254","ophone2num":"","p1_thumb":"","p1":"","p2":"","p2_thumb":"","p3":"","p3_thumb":"","p4":"","p4_thumb":""},"dinfo":{"name":"","sex":"男","dphonenum":"","dphone2num":"","didnum":"","didaddr":"","didnowaddr":"","dlicnum":"","dctnum":"","dctetime":"","dcartype":"","dctstr":"","dlicsdate":"","dcttype":"J-货运","dlicedate":"","p1":"","p1_thumb":"","p2":"","p2_thumb":"","p3_thumb":"","p3":"","p7_thumb":"","p7":"","p4_thumb":"","p4":"","p8_thumb":"","p8":"","p10_thumb":"","p10":"","p5_thumb":"","p5":"","p6_thumb":"","p6":"","p9_thumb":"","p9":""},"d2info":{"name":"","sex":"男","dphonenum":"","dphone2num":"","didnum":"","didaddr":"","didnowaddr":"","dlicnum":"","dctnum":"","dctetime":"","dcartype":"","dctstr":"","dlicsdate":"","dcttype":"J-货运","dlicedate":"","p1":"","p1_thumb":"","p2":"","p2_thumb":"","p3_thumb":"","p3":"","p7_thumb":"","p7":"","p4_thumb":"","p4":"","p8_thumb":"","p8":"","p10_thumb":"","p10":"","p5_thumb":"     ","p5":"","p6_thumb":"","p6":"","p9_thumb":"","p9":""}}
     */

    private boolean success;
    private Object error;
    private DataBean data;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Object getError() {
        return error;
    }

    public void setError(Object error) {
        this.error = error;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * uid : NC-1001-1628
         * cinfo : {"carprice":"0.00","utype":"营运","platetype":"","fnum":"LVBV3JBB5DE049398","enum":"89612500","loadp":"3","filenum":"0-1 NO2","trstime":"2018-03-01","tretime":"2021-03-01","olength":"5995","owidth":"2150","oheight":"2900","cwcode":"","cwlevel":"","cwstime":"","cwetime":"","p1":"","p1_thumb":"","p6":"","p6_thumb":"","p2":"","p2_thumb":"","p7":"","p7_thumb":"","p3":"","p3_thumb":"","p8":"","p8_thumb":"","p4":"","p4_thumb":"","p5":"","p5_thumb":"","p9":"","p9_thumb":"","p10":"","p10_thumb":"","p11":"","p11_thumb":"","isown":"0","ccolor":"蓝色","0":"柴油","yregtime":"2014-02-24","ychketime":"2019-02","empchkstime":"","empchketime":"2019-02-28"}
         * isinfo : {"insure1sdate":"","insure1edate":"","is1comp":"","is1num":"","is1amount":"0.00","is1tax":"未选择","p1_thumb":"","p1":"","insure2sdate":"","insure2edate":"","is2comp":"","is2num":"","is2thirdp":"0","is2amount":"0.00","is2infolist":{"childtype_1":{"type_name":"车损险","indemnity_norm":null,"abatement":"未投保","base":null,"coefficient":null,"total":null},"childtype_2":{"type_name":"三者险","indemnity_norm":"0","abatement":"未投保","base":null,"coefficient":null,"total":null},"childtype_3":{"type_name":"盗抢险","indemnity_norm":null,"abatement":"未投保","base":null,"coefficient":null,"total":null},"childtype_4":{"type_name":"人员(司机)险","indemnity_norm":null,"abatement":"未投保","base":null,"coefficient":null,"total":null},"childtype_5":{"type_name":"人员(车主)险","indemnity_norm":null,"abatement":"未投保","base":null,"coefficient":null,"total":null},"childtype_6":{"type_name":"玻璃险","indemnity_norm":"进口玻璃","abatement":"--","base":null,"coefficient":null,"total":null},"childtype_7":{"type_name":"划痕险","indemnity_norm":null,"abatement":"未投保","base":null,"coefficient":null,"total":null},"childtype_8":{"type_name":"自然损失险","indemnity_norm":null,"abatement":"未投保","base":null,"coefficient":null,"total":null},"childtype_9":{"type_name":"新增设备损失险","indemnity_norm":null,"abatement":"未投保","base":null,"coefficient":null,"total":null}},"p2":"","p2_thumb":""}
         * ctinfo : {"compname":"demo","weight":"4025000","osize":"5995mm * 2150mm * 2900mm","tpdate":"","tpstr":"广州市**区交通管理总站","tpnum":"","p1_thumb":"","p1":"","p2_thumb":"","p2":"","p3_thumb":"","p3":"","modelname":"BJ5049CCY-BG","cartype":""}
         * oinfo : {"name":"张柏干","sex":"女","oidnum":"441821196508241E17","oidaddr":"广东省佛冈县迳头镇大陂村委下埕村47号","oidnowaddr":"广东省佛冈县迳头镇大陂村委下埕村47号","ophonenum":"13719255254","ophone2num":"","p1_thumb":"","p1":"","p2":"","p2_thumb":"","p3":"","p3_thumb":"","p4":"","p4_thumb":""}
         * dinfo : {"name":"","sex":"男","dphonenum":"","dphone2num":"","didnum":"","didaddr":"","didnowaddr":"","dlicnum":"","dctnum":"","dctetime":"","dcartype":"","dctstr":"","dlicsdate":"","dcttype":"J-货运","dlicedate":"","p1":"","p1_thumb":"","p2":"","p2_thumb":"","p3_thumb":"","p3":"","p7_thumb":"","p7":"","p4_thumb":"","p4":"","p8_thumb":"","p8":"","p10_thumb":"","p10":"","p5_thumb":"","p5":"","p6_thumb":"","p6":"","p9_thumb":"","p9":""}
         * d2info : {"name":"","sex":"男","dphonenum":"","dphone2num":"","didnum":"","didaddr":"","didnowaddr":"","dlicnum":"","dctnum":"","dctetime":"","dcartype":"","dctstr":"","dlicsdate":"","dcttype":"J-货运","dlicedate":"","p1":"","p1_thumb":"","p2":"","p2_thumb":"","p3_thumb":"","p3":"","p7_thumb":"","p7":"","p4_thumb":"","p4":"","p8_thumb":"","p8":"","p10_thumb":"","p10":"","p5_thumb":"     ","p5":"","p6_thumb":"","p6":"","p9_thumb":"","p9":""}
         */

        private String uid;
        private CinfoBean cinfo;
        private IsinfoBean isinfo;
        private CtinfoBean ctinfo;
        private OinfoBean oinfo;
        private DinfoBean dinfo;
        private D2infoBean d2info;

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }

        public CinfoBean getCinfo() {
            return cinfo;
        }

        public void setCinfo(CinfoBean cinfo) {
            this.cinfo = cinfo;
        }

        public IsinfoBean getIsinfo() {
            return isinfo;
        }

        public void setIsinfo(IsinfoBean isinfo) {
            this.isinfo = isinfo;
        }

        public CtinfoBean getCtinfo() {
            return ctinfo;
        }

        public void setCtinfo(CtinfoBean ctinfo) {
            this.ctinfo = ctinfo;
        }

        public OinfoBean getOinfo() {
            return oinfo;
        }

        public void setOinfo(OinfoBean oinfo) {
            this.oinfo = oinfo;
        }

        public DinfoBean getDinfo() {
            return dinfo;
        }

        public void setDinfo(DinfoBean dinfo) {
            this.dinfo = dinfo;
        }

        public D2infoBean getD2info() {
            return d2info;
        }

        public void setD2info(D2infoBean d2info) {
            this.d2info = d2info;
        }

        public static class CinfoBean {
            /**
             * carprice : 0.00
             * utype : 营运
             * platetype :
             * fnum : LVBV3JBB5DE049398
             * enum : 89612500
             * loadp : 3
             * filenum : 0-1 NO2
             * trstime : 2018-03-01
             * tretime : 2021-03-01
             * olength : 5995
             * owidth : 2150
             * oheight : 2900
             * cwcode :
             * cwlevel :
             * cwstime :
             * cwetime :
             * p1 :
             * p1_thumb :
             * p6 :
             * p6_thumb :
             * p2 :
             * p2_thumb :
             * p7 :
             * p7_thumb :
             * p3 :
             * p3_thumb :
             * p8 :
             * p8_thumb :
             * p4 :
             * p4_thumb :
             * p5 :
             * p5_thumb :
             * p9 :
             * p9_thumb :
             * p10 :
             * p10_thumb :
             * p11 :
             * p11_thumb :
             * isown : 0
             * ccolor : 蓝色
             * 0 : 柴油
             * yregtime : 2014-02-24
             * ychketime : 2019-02
             * empchkstime :
             * empchketime : 2019-02-28
             */

            private String carprice;
            private String utype;
            private String platetype;
            private String fnum;
            @SerializedName("enum")
            private String enumX;
            private String loadp;
            private String filenum;
            private String trstime;
            private String tretime;
            private String olength;
            private String owidth;
            private String oheight;
            private String cwcode;
            private String cwlevel;
            private String cwstime;
            private String cwetime;
            private String p1;
            private String p1_thumb;
            private String p6;
            private String p6_thumb;
            private String p2;
            private String p2_thumb;
            private String p7;
            private String p7_thumb;
            private String p3;
            private String p3_thumb;
            private String p8;
            private String p8_thumb;
            private String p4;
            private String p4_thumb;
            private String p5;
            private String p5_thumb;
            private String p9;
            private String p9_thumb;
            private String p10;
            private String p10_thumb;
            private String p11;
            private String p11_thumb;
            private String isown;
            private String ccolor;
            @SerializedName("0")
            private String _$0;
            private String yregtime;
            private String ychketime;
            private String empchkstime;
            private String empchketime;

            public String getCarprice() {
                return carprice;
            }

            public void setCarprice(String carprice) {
                this.carprice = carprice;
            }

            public String getUtype() {
                return utype;
            }

            public void setUtype(String utype) {
                this.utype = utype;
            }

            public String getPlatetype() {
                return platetype;
            }

            public void setPlatetype(String platetype) {
                this.platetype = platetype;
            }

            public String getFnum() {
                return fnum;
            }

            public void setFnum(String fnum) {
                this.fnum = fnum;
            }

            public String getEnumX() {
                return enumX;
            }

            public void setEnumX(String enumX) {
                this.enumX = enumX;
            }

            public String getLoadp() {
                return loadp;
            }

            public void setLoadp(String loadp) {
                this.loadp = loadp;
            }

            public String getFilenum() {
                return filenum;
            }

            public void setFilenum(String filenum) {
                this.filenum = filenum;
            }

            public String getTrstime() {
                return trstime;
            }

            public void setTrstime(String trstime) {
                this.trstime = trstime;
            }

            public String getTretime() {
                return tretime;
            }

            public void setTretime(String tretime) {
                this.tretime = tretime;
            }

            public String getOlength() {
                return olength;
            }

            public void setOlength(String olength) {
                this.olength = olength;
            }

            public String getOwidth() {
                return owidth;
            }

            public void setOwidth(String owidth) {
                this.owidth = owidth;
            }

            public String getOheight() {
                return oheight;
            }

            public void setOheight(String oheight) {
                this.oheight = oheight;
            }

            public String getCwcode() {
                return cwcode;
            }

            public void setCwcode(String cwcode) {
                this.cwcode = cwcode;
            }

            public String getCwlevel() {
                return cwlevel;
            }

            public void setCwlevel(String cwlevel) {
                this.cwlevel = cwlevel;
            }

            public String getCwstime() {
                return cwstime;
            }

            public void setCwstime(String cwstime) {
                this.cwstime = cwstime;
            }

            public String getCwetime() {
                return cwetime;
            }

            public void setCwetime(String cwetime) {
                this.cwetime = cwetime;
            }

            public String getP1() {
                return p1;
            }

            public void setP1(String p1) {
                this.p1 = p1;
            }

            public String getP1_thumb() {
                return p1_thumb;
            }

            public void setP1_thumb(String p1_thumb) {
                this.p1_thumb = p1_thumb;
            }

            public String getP6() {
                return p6;
            }

            public void setP6(String p6) {
                this.p6 = p6;
            }

            public String getP6_thumb() {
                return p6_thumb;
            }

            public void setP6_thumb(String p6_thumb) {
                this.p6_thumb = p6_thumb;
            }

            public String getP2() {
                return p2;
            }

            public void setP2(String p2) {
                this.p2 = p2;
            }

            public String getP2_thumb() {
                return p2_thumb;
            }

            public void setP2_thumb(String p2_thumb) {
                this.p2_thumb = p2_thumb;
            }

            public String getP7() {
                return p7;
            }

            public void setP7(String p7) {
                this.p7 = p7;
            }

            public String getP7_thumb() {
                return p7_thumb;
            }

            public void setP7_thumb(String p7_thumb) {
                this.p7_thumb = p7_thumb;
            }

            public String getP3() {
                return p3;
            }

            public void setP3(String p3) {
                this.p3 = p3;
            }

            public String getP3_thumb() {
                return p3_thumb;
            }

            public void setP3_thumb(String p3_thumb) {
                this.p3_thumb = p3_thumb;
            }

            public String getP8() {
                return p8;
            }

            public void setP8(String p8) {
                this.p8 = p8;
            }

            public String getP8_thumb() {
                return p8_thumb;
            }

            public void setP8_thumb(String p8_thumb) {
                this.p8_thumb = p8_thumb;
            }

            public String getP4() {
                return p4;
            }

            public void setP4(String p4) {
                this.p4 = p4;
            }

            public String getP4_thumb() {
                return p4_thumb;
            }

            public void setP4_thumb(String p4_thumb) {
                this.p4_thumb = p4_thumb;
            }

            public String getP5() {
                return p5;
            }

            public void setP5(String p5) {
                this.p5 = p5;
            }

            public String getP5_thumb() {
                return p5_thumb;
            }

            public void setP5_thumb(String p5_thumb) {
                this.p5_thumb = p5_thumb;
            }

            public String getP9() {
                return p9;
            }

            public void setP9(String p9) {
                this.p9 = p9;
            }

            public String getP9_thumb() {
                return p9_thumb;
            }

            public void setP9_thumb(String p9_thumb) {
                this.p9_thumb = p9_thumb;
            }

            public String getP10() {
                return p10;
            }

            public void setP10(String p10) {
                this.p10 = p10;
            }

            public String getP10_thumb() {
                return p10_thumb;
            }

            public void setP10_thumb(String p10_thumb) {
                this.p10_thumb = p10_thumb;
            }

            public String getP11() {
                return p11;
            }

            public void setP11(String p11) {
                this.p11 = p11;
            }

            public String getP11_thumb() {
                return p11_thumb;
            }

            public void setP11_thumb(String p11_thumb) {
                this.p11_thumb = p11_thumb;
            }

            public String getIsown() {
                return isown;
            }

            public void setIsown(String isown) {
                this.isown = isown;
            }

            public String getCcolor() {
                return ccolor;
            }

            public void setCcolor(String ccolor) {
                this.ccolor = ccolor;
            }

            public String get_$0() {
                return _$0;
            }

            public void set_$0(String _$0) {
                this._$0 = _$0;
            }

            public String getYregtime() {
                return yregtime;
            }

            public void setYregtime(String yregtime) {
                this.yregtime = yregtime;
            }

            public String getYchketime() {
                return ychketime;
            }

            public void setYchketime(String ychketime) {
                this.ychketime = ychketime;
            }

            public String getEmpchkstime() {
                return empchkstime;
            }

            public void setEmpchkstime(String empchkstime) {
                this.empchkstime = empchkstime;
            }

            public String getEmpchketime() {
                return empchketime;
            }

            public void setEmpchketime(String empchketime) {
                this.empchketime = empchketime;
            }
        }

        public static class IsinfoBean {
            /**
             * insure1sdate :
             * insure1edate :
             * is1comp :
             * is1num :
             * is1amount : 0.00
             * is1tax : 未选择
             * p1_thumb :
             * p1 :
             * insure2sdate :
             * insure2edate :
             * is2comp :
             * is2num :
             * is2thirdp : 0
             * is2amount : 0.00
             * is2infolist : {"childtype_1":{"type_name":"车损险","indemnity_norm":null,"abatement":"未投保","base":null,"coefficient":null,"total":null},"childtype_2":{"type_name":"三者险","indemnity_norm":"0","abatement":"未投保","base":null,"coefficient":null,"total":null},"childtype_3":{"type_name":"盗抢险","indemnity_norm":null,"abatement":"未投保","base":null,"coefficient":null,"total":null},"childtype_4":{"type_name":"人员(司机)险","indemnity_norm":null,"abatement":"未投保","base":null,"coefficient":null,"total":null},"childtype_5":{"type_name":"人员(车主)险","indemnity_norm":null,"abatement":"未投保","base":null,"coefficient":null,"total":null},"childtype_6":{"type_name":"玻璃险","indemnity_norm":"进口玻璃","abatement":"--","base":null,"coefficient":null,"total":null},"childtype_7":{"type_name":"划痕险","indemnity_norm":null,"abatement":"未投保","base":null,"coefficient":null,"total":null},"childtype_8":{"type_name":"自然损失险","indemnity_norm":null,"abatement":"未投保","base":null,"coefficient":null,"total":null},"childtype_9":{"type_name":"新增设备损失险","indemnity_norm":null,"abatement":"未投保","base":null,"coefficient":null,"total":null}}
             * p2 :
             * p2_thumb :
             */

            private String insure1sdate;
            private String insure1edate;
            private String is1comp;
            private String is1num;
            private String is1amount;
            private String is1tax;
            private String p1_thumb;
            private String p1;
            private String insure2sdate;
            private String insure2edate;
            private String is2comp;
            private String is2num;
            private String is2thirdp;
            private String is2amount;
            private Is2infolistBean is2infolist;
            private String p2;
            private String p2_thumb;

            public String getInsure1sdate() {
                return insure1sdate;
            }

            public void setInsure1sdate(String insure1sdate) {
                this.insure1sdate = insure1sdate;
            }

            public String getInsure1edate() {
                return insure1edate;
            }

            public void setInsure1edate(String insure1edate) {
                this.insure1edate = insure1edate;
            }

            public String getIs1comp() {
                return is1comp;
            }

            public void setIs1comp(String is1comp) {
                this.is1comp = is1comp;
            }

            public String getIs1num() {
                return is1num;
            }

            public void setIs1num(String is1num) {
                this.is1num = is1num;
            }

            public String getIs1amount() {
                return is1amount;
            }

            public void setIs1amount(String is1amount) {
                this.is1amount = is1amount;
            }

            public String getIs1tax() {
                return is1tax;
            }

            public void setIs1tax(String is1tax) {
                this.is1tax = is1tax;
            }

            public String getP1_thumb() {
                return p1_thumb;
            }

            public void setP1_thumb(String p1_thumb) {
                this.p1_thumb = p1_thumb;
            }

            public String getP1() {
                return p1;
            }

            public void setP1(String p1) {
                this.p1 = p1;
            }

            public String getInsure2sdate() {
                return insure2sdate;
            }

            public void setInsure2sdate(String insure2sdate) {
                this.insure2sdate = insure2sdate;
            }

            public String getInsure2edate() {
                return insure2edate;
            }

            public void setInsure2edate(String insure2edate) {
                this.insure2edate = insure2edate;
            }

            public String getIs2comp() {
                return is2comp;
            }

            public void setIs2comp(String is2comp) {
                this.is2comp = is2comp;
            }

            public String getIs2num() {
                return is2num;
            }

            public void setIs2num(String is2num) {
                this.is2num = is2num;
            }

            public String getIs2thirdp() {
                return is2thirdp;
            }

            public void setIs2thirdp(String is2thirdp) {
                this.is2thirdp = is2thirdp;
            }

            public String getIs2amount() {
                return is2amount;
            }

            public void setIs2amount(String is2amount) {
                this.is2amount = is2amount;
            }

            public Is2infolistBean getIs2infolist() {
                return is2infolist;
            }

            public void setIs2infolist(Is2infolistBean is2infolist) {
                this.is2infolist = is2infolist;
            }

            public String getP2() {
                return p2;
            }

            public void setP2(String p2) {
                this.p2 = p2;
            }

            public String getP2_thumb() {
                return p2_thumb;
            }

            public void setP2_thumb(String p2_thumb) {
                this.p2_thumb = p2_thumb;
            }

            public static class Is2infolistBean {
                /**
                 * childtype_1 : {"type_name":"车损险","indemnity_norm":null,"abatement":"未投保","base":null,"coefficient":null,"total":null}
                 * childtype_2 : {"type_name":"三者险","indemnity_norm":"0","abatement":"未投保","base":null,"coefficient":null,"total":null}
                 * childtype_3 : {"type_name":"盗抢险","indemnity_norm":null,"abatement":"未投保","base":null,"coefficient":null,"total":null}
                 * childtype_4 : {"type_name":"人员(司机)险","indemnity_norm":null,"abatement":"未投保","base":null,"coefficient":null,"total":null}
                 * childtype_5 : {"type_name":"人员(车主)险","indemnity_norm":null,"abatement":"未投保","base":null,"coefficient":null,"total":null}
                 * childtype_6 : {"type_name":"玻璃险","indemnity_norm":"进口玻璃","abatement":"--","base":null,"coefficient":null,"total":null}
                 * childtype_7 : {"type_name":"划痕险","indemnity_norm":null,"abatement":"未投保","base":null,"coefficient":null,"total":null}
                 * childtype_8 : {"type_name":"自然损失险","indemnity_norm":null,"abatement":"未投保","base":null,"coefficient":null,"total":null}
                 * childtype_9 : {"type_name":"新增设备损失险","indemnity_norm":null,"abatement":"未投保","base":null,"coefficient":null,"total":null}
                 */

                private ChildBean childtype_1;
                private ChildBean childtype_2;
                private ChildBean childtype_3;
                private ChildBean childtype_4;
                private ChildBean childtype_5;
                private ChildBean childtype_6;
                private ChildBean childtype_7;
                private ChildBean childtype_8;
                private ChildBean childtype_9;

                public ChildBean getChildtype_1() {
                    return childtype_1;
                }

                public void setChildtype_1(ChildBean childtype_1) {
                    this.childtype_1 = childtype_1;
                }

                public ChildBean getChildtype_2() {
                    return childtype_2;
                }

                public void setChildtype_2(ChildBean childtype_2) {
                    this.childtype_2 = childtype_2;
                }

                public ChildBean getChildtype_3() {
                    return childtype_3;
                }

                public void setChildtype_3(ChildBean childtype_3) {
                    this.childtype_3 = childtype_3;
                }

                public ChildBean getChildtype_4() {
                    return childtype_4;
                }

                public void setChildtype_4(ChildBean childtype_4) {
                    this.childtype_4 = childtype_4;
                }

                public ChildBean getChildtype_5() {
                    return childtype_5;
                }

                public void setChildtype_5(ChildBean childtype_5) {
                    this.childtype_5 = childtype_5;
                }

                public ChildBean getChildtype_6() {
                    return childtype_6;
                }

                public void setChildtype_6(ChildBean childtype_6) {
                    this.childtype_6 = childtype_6;
                }

                public ChildBean getChildtype_7() {
                    return childtype_7;
                }

                public void setChildtype_7(ChildBean childtype_7) {
                    this.childtype_7 = childtype_7;
                }

                public ChildBean getChildtype_8() {
                    return childtype_8;
                }

                public void setChildtype_8(ChildBean childtype_8) {
                    this.childtype_8 = childtype_8;
                }

                public ChildBean getChildtype_9() {
                    return childtype_9;
                }

                public void setChildtype_9(ChildBean childtype_9) {
                    this.childtype_9 = childtype_9;
                }


                public static class ChildBean {
                    /**
                     * type_name : 车损险
                     * indemnity_norm : null
                     * abatement : 未投保
                     * base : null
                     * coefficient : null
                     * total : null
                     */

                    private String type_name;
                    private String indemnity_norm;
                    private String abatement;
                    private String base;
                    private String coefficient;
                    private String total;

                    public String getType_name() {
                        return type_name;
                    }

                    public void setType_name(String type_name) {
                        this.type_name = type_name;
                    }

                    public String getIndemnity_norm() {
                        return indemnity_norm;
                    }

                    public void setIndemnity_norm(String indemnity_norm) {
                        this.indemnity_norm = indemnity_norm;
                    }

                    public String getAbatement() {
                        return abatement;
                    }

                    public void setAbatement(String abatement) {
                        this.abatement = abatement;
                    }

                    public String getBase() {
                        return base;
                    }

                    public void setBase(String base) {
                        this.base = base;
                    }

                    public String getCoefficient() {
                        return coefficient;
                    }

                    public void setCoefficient(String coefficient) {
                        this.coefficient = coefficient;
                    }

                    public String getTotal() {
                        return total;
                    }

                    public void setTotal(String total) {
                        this.total = total;
                    }
                }
            }
        }

        public static class CtinfoBean {
            /**
             * compname : demo
             * weight : 4025000
             * osize : 5995mm * 2150mm * 2900mm
             * tpdate :
             * tpstr : 广州市**区交通管理总站
             * tpnum :
             * p1_thumb :
             * p1 :
             * p2_thumb :
             * p2 :
             * p3_thumb :
             * p3 :
             * modelname : BJ5049CCY-BG
             * cartype :
             */

            private String compname;
            private String weight;
            private String osize;
            private String tpdate;
            private String tpstr;
            private String tpnum;
            private String p1_thumb;
            private String p1;
            private String p2_thumb;
            private String p2;
            private String p3_thumb;
            private String p3;
            private String modelname;
            private String cartype;

            public String getCompname() {
                return compname;
            }

            public void setCompname(String compname) {
                this.compname = compname;
            }

            public String getWeight() {
                return weight;
            }

            public void setWeight(String weight) {
                this.weight = weight;
            }

            public String getOsize() {
                return osize;
            }

            public void setOsize(String osize) {
                this.osize = osize;
            }

            public String getTpdate() {
                return tpdate;
            }

            public void setTpdate(String tpdate) {
                this.tpdate = tpdate;
            }

            public String getTpstr() {
                return tpstr;
            }

            public void setTpstr(String tpstr) {
                this.tpstr = tpstr;
            }

            public String getTpnum() {
                return tpnum;
            }

            public void setTpnum(String tpnum) {
                this.tpnum = tpnum;
            }

            public String getP1_thumb() {
                return p1_thumb;
            }

            public void setP1_thumb(String p1_thumb) {
                this.p1_thumb = p1_thumb;
            }

            public String getP1() {
                return p1;
            }

            public void setP1(String p1) {
                this.p1 = p1;
            }

            public String getP2_thumb() {
                return p2_thumb;
            }

            public void setP2_thumb(String p2_thumb) {
                this.p2_thumb = p2_thumb;
            }

            public String getP2() {
                return p2;
            }

            public void setP2(String p2) {
                this.p2 = p2;
            }

            public String getP3_thumb() {
                return p3_thumb;
            }

            public void setP3_thumb(String p3_thumb) {
                this.p3_thumb = p3_thumb;
            }

            public String getP3() {
                return p3;
            }

            public void setP3(String p3) {
                this.p3 = p3;
            }

            public String getModelname() {
                return modelname;
            }

            public void setModelname(String modelname) {
                this.modelname = modelname;
            }

            public String getCartype() {
                return cartype;
            }

            public void setCartype(String cartype) {
                this.cartype = cartype;
            }
        }

        public static class OinfoBean {
            /**
             * name : 张柏干
             * sex : 女
             * oidnum : 441821196508241E17
             * oidaddr : 广东省佛冈县迳头镇大陂村委下埕村47号
             * oidnowaddr : 广东省佛冈县迳头镇大陂村委下埕村47号
             * ophonenum : 13719255254
             * ophone2num :
             * p1_thumb :
             * p1 :
             * p2 :
             * p2_thumb :
             * p3 :
             * p3_thumb :
             * p4 :
             * p4_thumb :
             */

            private String name;
            private String sex;
            private String oidnum;
            private String oidaddr;
            private String oidnowaddr;
            private String ophonenum;
            private String ophone2num;
            private String p1_thumb;
            private String p1;
            private String p2;
            private String p2_thumb;
            private String p3;
            private String p3_thumb;
            private String p4;
            private String p4_thumb;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getSex() {
                return sex;
            }

            public void setSex(String sex) {
                this.sex = sex;
            }

            public String getOidnum() {
                return oidnum;
            }

            public void setOidnum(String oidnum) {
                this.oidnum = oidnum;
            }

            public String getOidaddr() {
                return oidaddr;
            }

            public void setOidaddr(String oidaddr) {
                this.oidaddr = oidaddr;
            }

            public String getOidnowaddr() {
                return oidnowaddr;
            }

            public void setOidnowaddr(String oidnowaddr) {
                this.oidnowaddr = oidnowaddr;
            }

            public String getOphonenum() {
                return ophonenum;
            }

            public void setOphonenum(String ophonenum) {
                this.ophonenum = ophonenum;
            }

            public String getOphone2num() {
                return ophone2num;
            }

            public void setOphone2num(String ophone2num) {
                this.ophone2num = ophone2num;
            }

            public String getP1_thumb() {
                return p1_thumb;
            }

            public void setP1_thumb(String p1_thumb) {
                this.p1_thumb = p1_thumb;
            }

            public String getP1() {
                return p1;
            }

            public void setP1(String p1) {
                this.p1 = p1;
            }

            public String getP2() {
                return p2;
            }

            public void setP2(String p2) {
                this.p2 = p2;
            }

            public String getP2_thumb() {
                return p2_thumb;
            }

            public void setP2_thumb(String p2_thumb) {
                this.p2_thumb = p2_thumb;
            }

            public String getP3() {
                return p3;
            }

            public void setP3(String p3) {
                this.p3 = p3;
            }

            public String getP3_thumb() {
                return p3_thumb;
            }

            public void setP3_thumb(String p3_thumb) {
                this.p3_thumb = p3_thumb;
            }

            public String getP4() {
                return p4;
            }

            public void setP4(String p4) {
                this.p4 = p4;
            }

            public String getP4_thumb() {
                return p4_thumb;
            }

            public void setP4_thumb(String p4_thumb) {
                this.p4_thumb = p4_thumb;
            }
        }

        public static class DinfoBean {
            /**
             * name :
             * sex : 男
             * dphonenum :
             * dphone2num :
             * didnum :
             * didaddr :
             * didnowaddr :
             * dlicnum :
             * dctnum :
             * dctetime :
             * dcartype :
             * dctstr :
             * dlicsdate :
             * dcttype : J-货运
             * dlicedate :
             * p1 :
             * p1_thumb :
             * p2 :
             * p2_thumb :
             * p3_thumb :
             * p3 :
             * p7_thumb :
             * p7 :
             * p4_thumb :
             * p4 :
             * p8_thumb :
             * p8 :
             * p10_thumb :
             * p10 :
             * p5_thumb :
             * p5 :
             * p6_thumb :
             * p6 :
             * p9_thumb :
             * p9 :
             */

            private String name;
            private String sex;
            private String dphonenum;
            private String dphone2num;
            private String didnum;
            private String didaddr;
            private String didnowaddr;
            private String dlicnum;
            private String dctnum;
            private String dctetime;
            private String dcartype;
            private String dctstr;
            private String dlicsdate;
            private String dcttype;
            private String dlicedate;
            private String p1;
            private String p1_thumb;
            private String p2;
            private String p2_thumb;
            private String p3_thumb;
            private String p3;
            private String p7_thumb;
            private String p7;
            private String p4_thumb;
            private String p4;
            private String p8_thumb;
            private String p8;
            private String p10_thumb;
            private String p10;
            private String p5_thumb;
            private String p5;
            private String p6_thumb;
            private String p6;
            private String p9_thumb;
            private String p9;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getSex() {
                return sex;
            }

            public void setSex(String sex) {
                this.sex = sex;
            }

            public String getDphonenum() {
                return dphonenum;
            }

            public void setDphonenum(String dphonenum) {
                this.dphonenum = dphonenum;
            }

            public String getDphone2num() {
                return dphone2num;
            }

            public void setDphone2num(String dphone2num) {
                this.dphone2num = dphone2num;
            }

            public String getDidnum() {
                return didnum;
            }

            public void setDidnum(String didnum) {
                this.didnum = didnum;
            }

            public String getDidaddr() {
                return didaddr;
            }

            public void setDidaddr(String didaddr) {
                this.didaddr = didaddr;
            }

            public String getDidnowaddr() {
                return didnowaddr;
            }

            public void setDidnowaddr(String didnowaddr) {
                this.didnowaddr = didnowaddr;
            }

            public String getDlicnum() {
                return dlicnum;
            }

            public void setDlicnum(String dlicnum) {
                this.dlicnum = dlicnum;
            }

            public String getDctnum() {
                return dctnum;
            }

            public void setDctnum(String dctnum) {
                this.dctnum = dctnum;
            }

            public String getDctetime() {
                return dctetime;
            }

            public void setDctetime(String dctetime) {
                this.dctetime = dctetime;
            }

            public String getDcartype() {
                return dcartype;
            }

            public void setDcartype(String dcartype) {
                this.dcartype = dcartype;
            }

            public String getDctstr() {
                return dctstr;
            }

            public void setDctstr(String dctstr) {
                this.dctstr = dctstr;
            }

            public String getDlicsdate() {
                return dlicsdate;
            }

            public void setDlicsdate(String dlicsdate) {
                this.dlicsdate = dlicsdate;
            }

            public String getDcttype() {
                return dcttype;
            }

            public void setDcttype(String dcttype) {
                this.dcttype = dcttype;
            }

            public String getDlicedate() {
                return dlicedate;
            }

            public void setDlicedate(String dlicedate) {
                this.dlicedate = dlicedate;
            }

            public String getP1() {
                return p1;
            }

            public void setP1(String p1) {
                this.p1 = p1;
            }

            public String getP1_thumb() {
                return p1_thumb;
            }

            public void setP1_thumb(String p1_thumb) {
                this.p1_thumb = p1_thumb;
            }

            public String getP2() {
                return p2;
            }

            public void setP2(String p2) {
                this.p2 = p2;
            }

            public String getP2_thumb() {
                return p2_thumb;
            }

            public void setP2_thumb(String p2_thumb) {
                this.p2_thumb = p2_thumb;
            }

            public String getP3_thumb() {
                return p3_thumb;
            }

            public void setP3_thumb(String p3_thumb) {
                this.p3_thumb = p3_thumb;
            }

            public String getP3() {
                return p3;
            }

            public void setP3(String p3) {
                this.p3 = p3;
            }

            public String getP7_thumb() {
                return p7_thumb;
            }

            public void setP7_thumb(String p7_thumb) {
                this.p7_thumb = p7_thumb;
            }

            public String getP7() {
                return p7;
            }

            public void setP7(String p7) {
                this.p7 = p7;
            }

            public String getP4_thumb() {
                return p4_thumb;
            }

            public void setP4_thumb(String p4_thumb) {
                this.p4_thumb = p4_thumb;
            }

            public String getP4() {
                return p4;
            }

            public void setP4(String p4) {
                this.p4 = p4;
            }

            public String getP8_thumb() {
                return p8_thumb;
            }

            public void setP8_thumb(String p8_thumb) {
                this.p8_thumb = p8_thumb;
            }

            public String getP8() {
                return p8;
            }

            public void setP8(String p8) {
                this.p8 = p8;
            }

            public String getP10_thumb() {
                return p10_thumb;
            }

            public void setP10_thumb(String p10_thumb) {
                this.p10_thumb = p10_thumb;
            }

            public String getP10() {
                return p10;
            }

            public void setP10(String p10) {
                this.p10 = p10;
            }

            public String getP5_thumb() {
                return p5_thumb;
            }

            public void setP5_thumb(String p5_thumb) {
                this.p5_thumb = p5_thumb;
            }

            public String getP5() {
                return p5;
            }

            public void setP5(String p5) {
                this.p5 = p5;
            }

            public String getP6_thumb() {
                return p6_thumb;
            }

            public void setP6_thumb(String p6_thumb) {
                this.p6_thumb = p6_thumb;
            }

            public String getP6() {
                return p6;
            }

            public void setP6(String p6) {
                this.p6 = p6;
            }

            public String getP9_thumb() {
                return p9_thumb;
            }

            public void setP9_thumb(String p9_thumb) {
                this.p9_thumb = p9_thumb;
            }

            public String getP9() {
                return p9;
            }

            public void setP9(String p9) {
                this.p9 = p9;
            }
        }

        public static class D2infoBean {
            /**
             * name :
             * sex : 男
             * dphonenum :
             * dphone2num :
             * didnum :
             * didaddr :
             * didnowaddr :
             * dlicnum :
             * dctnum :
             * dctetime :
             * dcartype :
             * dctstr :
             * dlicsdate :
             * dcttype : J-货运
             * dlicedate :
             * p1 :
             * p1_thumb :
             * p2 :
             * p2_thumb :
             * p3_thumb :
             * p3 :
             * p7_thumb :
             * p7 :
             * p4_thumb :
             * p4 :
             * p8_thumb :
             * p8 :
             * p10_thumb :
             * p10 :
             * p5_thumb :
             * p5 :
             * p6_thumb :
             * p6 :
             * p9_thumb :
             * p9 :
             */

            private String name;
            private String sex;
            private String dphonenum;
            private String dphone2num;
            private String didnum;
            private String didaddr;
            private String didnowaddr;
            private String dlicnum;
            private String dctnum;
            private String dctetime;
            private String dcartype;
            private String dctstr;
            private String dlicsdate;
            private String dcttype;
            private String dlicedate;
            private String p1;
            private String p1_thumb;
            private String p2;
            private String p2_thumb;
            private String p3_thumb;
            private String p3;
            private String p7_thumb;
            private String p7;
            private String p4_thumb;
            private String p4;
            private String p8_thumb;
            private String p8;
            private String p10_thumb;
            private String p10;
            private String p5_thumb;
            private String p5;
            private String p6_thumb;
            private String p6;
            private String p9_thumb;
            private String p9;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getSex() {
                return sex;
            }

            public void setSex(String sex) {
                this.sex = sex;
            }

            public String getDphonenum() {
                return dphonenum;
            }

            public void setDphonenum(String dphonenum) {
                this.dphonenum = dphonenum;
            }

            public String getDphone2num() {
                return dphone2num;
            }

            public void setDphone2num(String dphone2num) {
                this.dphone2num = dphone2num;
            }

            public String getDidnum() {
                return didnum;
            }

            public void setDidnum(String didnum) {
                this.didnum = didnum;
            }

            public String getDidaddr() {
                return didaddr;
            }

            public void setDidaddr(String didaddr) {
                this.didaddr = didaddr;
            }

            public String getDidnowaddr() {
                return didnowaddr;
            }

            public void setDidnowaddr(String didnowaddr) {
                this.didnowaddr = didnowaddr;
            }

            public String getDlicnum() {
                return dlicnum;
            }

            public void setDlicnum(String dlicnum) {
                this.dlicnum = dlicnum;
            }

            public String getDctnum() {
                return dctnum;
            }

            public void setDctnum(String dctnum) {
                this.dctnum = dctnum;
            }

            public String getDctetime() {
                return dctetime;
            }

            public void setDctetime(String dctetime) {
                this.dctetime = dctetime;
            }

            public String getDcartype() {
                return dcartype;
            }

            public void setDcartype(String dcartype) {
                this.dcartype = dcartype;
            }

            public String getDctstr() {
                return dctstr;
            }

            public void setDctstr(String dctstr) {
                this.dctstr = dctstr;
            }

            public String getDlicsdate() {
                return dlicsdate;
            }

            public void setDlicsdate(String dlicsdate) {
                this.dlicsdate = dlicsdate;
            }

            public String getDcttype() {
                return dcttype;
            }

            public void setDcttype(String dcttype) {
                this.dcttype = dcttype;
            }

            public String getDlicedate() {
                return dlicedate;
            }

            public void setDlicedate(String dlicedate) {
                this.dlicedate = dlicedate;
            }

            public String getP1() {
                return p1;
            }

            public void setP1(String p1) {
                this.p1 = p1;
            }

            public String getP1_thumb() {
                return p1_thumb;
            }

            public void setP1_thumb(String p1_thumb) {
                this.p1_thumb = p1_thumb;
            }

            public String getP2() {
                return p2;
            }

            public void setP2(String p2) {
                this.p2 = p2;
            }

            public String getP2_thumb() {
                return p2_thumb;
            }

            public void setP2_thumb(String p2_thumb) {
                this.p2_thumb = p2_thumb;
            }

            public String getP3_thumb() {
                return p3_thumb;
            }

            public void setP3_thumb(String p3_thumb) {
                this.p3_thumb = p3_thumb;
            }

            public String getP3() {
                return p3;
            }

            public void setP3(String p3) {
                this.p3 = p3;
            }

            public String getP7_thumb() {
                return p7_thumb;
            }

            public void setP7_thumb(String p7_thumb) {
                this.p7_thumb = p7_thumb;
            }

            public String getP7() {
                return p7;
            }

            public void setP7(String p7) {
                this.p7 = p7;
            }

            public String getP4_thumb() {
                return p4_thumb;
            }

            public void setP4_thumb(String p4_thumb) {
                this.p4_thumb = p4_thumb;
            }

            public String getP4() {
                return p4;
            }

            public void setP4(String p4) {
                this.p4 = p4;
            }

            public String getP8_thumb() {
                return p8_thumb;
            }

            public void setP8_thumb(String p8_thumb) {
                this.p8_thumb = p8_thumb;
            }

            public String getP8() {
                return p8;
            }

            public void setP8(String p8) {
                this.p8 = p8;
            }

            public String getP10_thumb() {
                return p10_thumb;
            }

            public void setP10_thumb(String p10_thumb) {
                this.p10_thumb = p10_thumb;
            }

            public String getP10() {
                return p10;
            }

            public void setP10(String p10) {
                this.p10 = p10;
            }

            public String getP5_thumb() {
                return p5_thumb;
            }

            public void setP5_thumb(String p5_thumb) {
                this.p5_thumb = p5_thumb;
            }

            public String getP5() {
                return p5;
            }

            public void setP5(String p5) {
                this.p5 = p5;
            }

            public String getP6_thumb() {
                return p6_thumb;
            }

            public void setP6_thumb(String p6_thumb) {
                this.p6_thumb = p6_thumb;
            }

            public String getP6() {
                return p6;
            }

            public void setP6(String p6) {
                this.p6 = p6;
            }

            public String getP9_thumb() {
                return p9_thumb;
            }

            public void setP9_thumb(String p9_thumb) {
                this.p9_thumb = p9_thumb;
            }

            public String getP9() {
                return p9;
            }

            public void setP9(String p9) {
                this.p9 = p9;
            }
        }
    }
}
