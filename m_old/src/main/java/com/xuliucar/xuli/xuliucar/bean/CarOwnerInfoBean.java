package com.xuliucar.xuli.xuliucar.bean;

/**
 * Created by skyward on 2016/11/7.
 *
 */

public class CarOwnerInfoBean {

    private boolean success;
    private String error;
    private String message;
    private DataBean data;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private String uid;
        private OinfoBean oinfo;

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }

        public OinfoBean getOinfo() {
            return oinfo;
        }

        public void setOinfo(OinfoBean oinfo) {
            this.oinfo = oinfo;
        }

        public static class OinfoBean {
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
    }
}
