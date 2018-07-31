package com.xuliucar.me.model;

import com.cangmaomao.lib.utils.LogUtilsKt;

/**
 * Created by skyward on 2016/11/3.
 *
 */

public class LoginInfo {


    /**
     * success : true
     * error : null
     * data : {"compid":"1","utype":"1","ctype":"1","apppower":{"app_pow1":"1","app_pow2":"1","app_pow3":"0","app_pow4":"0","app_pow5":"0","app_pow6":"0","app_pow7":"0","app_pow8":"0","app_pow9":"1","app_pow10":"1","app_pow11":"1","app_pow12":"1","app_pow13":"1","app_pow14":"1","app_pow15":"1","app_pow16":"1","app_pow17":"1","app_pow18":"1","app_pow19":"1","app_pow20":"1","app_pow21":"1","app_pow22":"1","app_pow23":"1","app_pow24":"1","app_pow25":"0","app_pow26":"0","app_pow27":"0","app_pow28":"0","app_pow29":"0","app_pow30":"0","app_pow31":"0","app_pow32":"0","app_pow33":"0","app_pow34":"1","app_pow35":"1","app_pow36":"1","app_pow37":"1","app_pow38":"1"}}
     */

    private boolean success;
    private Object error;
    private DataBean data;
    private String message;
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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static class DataBean {
        /**
         * compid : 1
         * utype : 1
         * ctype : 1
         * apppower : {"app_pow1":"1","app_pow2":"1","app_pow3":"0","app_pow4":"0","app_pow5":"0","app_pow6":"0","app_pow7":"0","app_pow8":"0","app_pow9":"1","app_pow10":"1","app_pow11":"1","app_pow12":"1","app_pow13":"1","app_pow14":"1","app_pow15":"1","app_pow16":"1","app_pow17":"1","app_pow18":"1","app_pow19":"1","app_pow20":"1","app_pow21":"1","app_pow22":"1","app_pow23":"1","app_pow24":"1","app_pow25":"0","app_pow26":"0","app_pow27":"0","app_pow28":"0","app_pow29":"0","app_pow30":"0","app_pow31":"0","app_pow32":"0","app_pow33":"0","app_pow34":"1","app_pow35":"1","app_pow36":"1","app_pow37":"1","app_pow38":"1"}
         */

        private String compid;
        private String utype;
        private String ctype;
        private ApppowerBean apppower;

        public String getCompid() {
            return compid;
        }

        public void setCompid(String compid) {
            this.compid = compid;
        }

        public String getUtype() {
            return utype;
        }

        public void setUtype(String utype) {
            this.utype = utype;
        }

        public String getCtype() {
            return ctype;
        }

        public void setCtype(String ctype) {
            this.ctype = ctype;
        }

        public ApppowerBean getApppower() {
            return apppower;
        }

        public void setApppower(ApppowerBean apppower) {
            this.apppower = apppower;
        }

        public static class ApppowerBean {
            /**
             * app_pow1 : 1
             * app_pow2 : 1
             * app_pow3 : 0
             * app_pow4 : 0
             * app_pow5 : 0
             * app_pow6 : 0
             * app_pow7 : 0
             * app_pow8 : 0
             * app_pow9 : 1
             * app_pow10 : 1
             * app_pow11 : 1
             * app_pow12 : 1
             * app_pow13 : 1
             * app_pow14 : 1
             * app_pow15 : 1
             * app_pow16 : 1
             * app_pow17 : 1
             * app_pow18 : 1
             * app_pow19 : 1
             * app_pow20 : 1
             * app_pow21 : 1
             * app_pow22 : 1
             * app_pow23 : 1
             * app_pow24 : 1
             * app_pow25 : 0
             * app_pow26 : 0
             * app_pow27 : 0
             * app_pow28 : 0
             * app_pow29 : 0
             * app_pow30 : 0
             * app_pow31 : 0
             * app_pow32 : 0
             * app_pow33 : 0
             * app_pow34 : 1
             * app_pow35 : 1
             * app_pow36 : 1
             * app_pow37 : 1
             * app_pow38 : 1
             */

            private String app_pow1;
            private String app_pow2;
            private String app_pow3;
            private String app_pow4;
            private String app_pow5;
            private String app_pow6;
            private String app_pow7;
            private String app_pow8;
            private String app_pow9;
            private String app_pow10;
            private String app_pow11;
            private String app_pow12;
            private String app_pow13;
            private String app_pow14;
            private String app_pow15;
            private String app_pow16;
            private String app_pow17;
            private String app_pow18;
            private String app_pow19;
            private String app_pow20;
            private String app_pow21;
            private String app_pow22;
            private String app_pow23;
            private String app_pow24;
            private String app_pow25;
            private String app_pow26;
            private String app_pow27;
            private String app_pow28;
            private String app_pow29;
            private String app_pow30;
            private String app_pow31;
            private String app_pow32;
            private String app_pow33;
            private String app_pow34;
            private String app_pow35;
            private String app_pow36;
            private String app_pow37;
            private String app_pow38;

            @Override
            public String toString() {
                return "ApppowerBean{" +
                        "app_pow1='" + app_pow1 + '\'' +
                        ", app_pow2='" + app_pow2 + '\'' +
                        ", app_pow3='" + app_pow3 + '\'' +
                        ", app_pow4='" + app_pow4 + '\'' +
                        ", app_pow5='" + app_pow5 + '\'' +
                        ", app_pow6='" + app_pow6 + '\'' +
                        ", app_pow7='" + app_pow7 + '\'' +
                        ", app_pow8='" + app_pow8 + '\'' +
                        ", app_pow9='" + app_pow9 + '\'' +
                        ", app_pow10='" + app_pow10 + '\'' +
                        ", app_pow11='" + app_pow11 + '\'' +
                        ", app_pow12='" + app_pow12 + '\'' +
                        ", app_pow13='" + app_pow13 + '\'' +
                        ", app_pow14='" + app_pow14 + '\'' +
                        ", app_pow15='" + app_pow15 + '\'' +
                        ", app_pow16='" + app_pow16 + '\'' +
                        ", app_pow17='" + app_pow17 + '\'' +
                        ", app_pow18='" + app_pow18 + '\'' +
                        ", app_pow19='" + app_pow19 + '\'' +
                        ", app_pow20='" + app_pow20 + '\'' +
                        ", app_pow21='" + app_pow21 + '\'' +
                        ", app_pow22='" + app_pow22 + '\'' +
                        ", app_pow23='" + app_pow23 + '\'' +
                        ", app_pow24='" + app_pow24 + '\'' +
                        ", app_pow25='" + app_pow25 + '\'' +
                        ", app_pow26='" + app_pow26 + '\'' +
                        ", app_pow27='" + app_pow27 + '\'' +
                        ", app_pow28='" + app_pow28 + '\'' +
                        ", app_pow29='" + app_pow29 + '\'' +
                        ", app_pow30='" + app_pow30 + '\'' +
                        ", app_pow31='" + app_pow31 + '\'' +
                        ", app_pow32='" + app_pow32 + '\'' +
                        ", app_pow33='" + app_pow33 + '\'' +
                        ", app_pow34='" + app_pow34 + '\'' +
                        ", app_pow35='" + app_pow35 + '\'' +
                        ", app_pow36='" + app_pow36 + '\'' +
                        ", app_pow37='" + app_pow37 + '\'' +
                        ", app_pow38='" + app_pow38 + '\'' +
                        '}';
            }

            public String getApp_pow1() {
                return app_pow1;
            }

            public void setApp_pow1(String app_pow1) {
                this.app_pow1 = app_pow1;
            }

            public String getApp_pow2() {
                return app_pow2;
            }

            public void setApp_pow2(String app_pow2) {
                this.app_pow2 = app_pow2;
            }

            public String getApp_pow3() {
                return app_pow3;
            }

            public void setApp_pow3(String app_pow3) {
                this.app_pow3 = app_pow3;
            }

            public String getApp_pow4() {
                return app_pow4;
            }

            public void setApp_pow4(String app_pow4) {
                this.app_pow4 = app_pow4;
            }

            public String getApp_pow5() {
                return app_pow5;
            }

            public void setApp_pow5(String app_pow5) {
                this.app_pow5 = app_pow5;
            }

            public String getApp_pow6() {
                return app_pow6;
            }

            public void setApp_pow6(String app_pow6) {
                this.app_pow6 = app_pow6;
            }

            public String getApp_pow7() {
                return app_pow7;
            }

            public void setApp_pow7(String app_pow7) {
                this.app_pow7 = app_pow7;
            }

            public String getApp_pow8() {
                return app_pow8;
            }

            public void setApp_pow8(String app_pow8) {
                this.app_pow8 = app_pow8;
            }

            public String getApp_pow9() {
                return app_pow9;
            }

            public void setApp_pow9(String app_pow9) {
                this.app_pow9 = app_pow9;
            }

            public String getApp_pow10() {
                return app_pow10;
            }

            public void setApp_pow10(String app_pow10) {
                this.app_pow10 = app_pow10;
            }

            public String getApp_pow11() {
                return app_pow11;
            }

            public void setApp_pow11(String app_pow11) {
                this.app_pow11 = app_pow11;
            }

            public String getApp_pow12() {
                return app_pow12;
            }

            public void setApp_pow12(String app_pow12) {
                this.app_pow12 = app_pow12;
            }

            public String getApp_pow13() {
                return app_pow13;
            }

            public void setApp_pow13(String app_pow13) {
                this.app_pow13 = app_pow13;
            }

            public String getApp_pow14() {
                return app_pow14;
            }

            public void setApp_pow14(String app_pow14) {
                this.app_pow14 = app_pow14;
            }

            public String getApp_pow15() {
                return app_pow15;
            }

            public void setApp_pow15(String app_pow15) {
                this.app_pow15 = app_pow15;
            }

            public String getApp_pow16() {
                return app_pow16;
            }

            public void setApp_pow16(String app_pow16) {
                this.app_pow16 = app_pow16;
            }

            public String getApp_pow17() {
                return app_pow17;
            }

            public void setApp_pow17(String app_pow17) {
                this.app_pow17 = app_pow17;
            }

            public String getApp_pow18() {
                return app_pow18;
            }

            public void setApp_pow18(String app_pow18) {
                this.app_pow18 = app_pow18;
            }

            public String getApp_pow19() {
                return app_pow19;
            }

            public void setApp_pow19(String app_pow19) {
                this.app_pow19 = app_pow19;
            }

            public String getApp_pow20() {
                return app_pow20;
            }

            public void setApp_pow20(String app_pow20) {
                this.app_pow20 = app_pow20;
            }

            public String getApp_pow21() {
                return app_pow21;
            }

            public void setApp_pow21(String app_pow21) {
                this.app_pow21 = app_pow21;
            }

            public String getApp_pow22() {
                return app_pow22;
            }

            public void setApp_pow22(String app_pow22) {
                this.app_pow22 = app_pow22;
            }

            public String getApp_pow23() {
                return app_pow23;
            }

            public void setApp_pow23(String app_pow23) {
                this.app_pow23 = app_pow23;
            }

            public String getApp_pow24() {
                return app_pow24;
            }

            public void setApp_pow24(String app_pow24) {
                this.app_pow24 = app_pow24;
            }

            public String getApp_pow25() {
                return app_pow25;
            }

            public void setApp_pow25(String app_pow25) {
                this.app_pow25 = app_pow25;
            }

            public String getApp_pow26() {
                return app_pow26;
            }

            public void setApp_pow26(String app_pow26) {
                this.app_pow26 = app_pow26;
            }

            public String getApp_pow27() {
                return app_pow27;
            }

            public void setApp_pow27(String app_pow27) {
                this.app_pow27 = app_pow27;
            }

            public String getApp_pow28() {
                return app_pow28;
            }

            public void setApp_pow28(String app_pow28) {
                this.app_pow28 = app_pow28;
            }

            public String getApp_pow29() {
                return app_pow29;
            }

            public void setApp_pow29(String app_pow29) {
                this.app_pow29 = app_pow29;
            }

            public String getApp_pow30() {
                return app_pow30;
            }

            public void setApp_pow30(String app_pow30) {
                this.app_pow30 = app_pow30;
            }

            public String getApp_pow31() {
                return app_pow31;
            }

            public void setApp_pow31(String app_pow31) {
                this.app_pow31 = app_pow31;
            }

            public String getApp_pow32() {
                return app_pow32;
            }

            public void setApp_pow32(String app_pow32) {
                this.app_pow32 = app_pow32;
            }

            public String getApp_pow33() {
                return app_pow33;
            }

            public void setApp_pow33(String app_pow33) {
                this.app_pow33 = app_pow33;
            }

            public String getApp_pow34() {
                return app_pow34;
            }

            public void setApp_pow34(String app_pow34) {
                this.app_pow34 = app_pow34;
            }

            public String getApp_pow35() {
                return app_pow35;
            }

            public void setApp_pow35(String app_pow35) {
                this.app_pow35 = app_pow35;
            }

            public String getApp_pow36() {
                return app_pow36;
            }

            public void setApp_pow36(String app_pow36) {
                this.app_pow36 = app_pow36;
            }

            public String getApp_pow37() {
                return app_pow37;
            }

            public void setApp_pow37(String app_pow37) {
                this.app_pow37 = app_pow37;
            }

            public String getApp_pow38() {
                return app_pow38;
            }

            public void setApp_pow38(String app_pow38) {
                this.app_pow38 = app_pow38;
            }
        }
    }
}
