package com.xuliucar.xuli.xuliucar.bean;

/**
 * Created by skyward on 2016/11/5.
 */

public class CarOwnerDetailBean {



    private boolean success;
    private String error;
    private String message;


    private CarOwnerDetailBeanDataBean data;

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

    public CarOwnerDetailBeanDataBean getData() {
        return data;
    }

    public void setData(CarOwnerDetailBeanDataBean data) {
        this.data = data;
    }

    public static class CarOwnerDetailBeanDataBean {
        private String ownername;
        private String sex;
        private String idaddr;
        private String nowaddr;
        private String phone;
        private String phone2;
        private String ownertmpidb;
        private String fid;
        private String mark;
        private String ownerid;
        private String p1_thumb;
        private String p1;
        private String p2_thumb;
        private String p2;
        private String p3_thumb;
        private String p3;
        private String p4_thumb;
        private String p4;

        public String getOwnername() {
            return ownername;
        }

        public void setOwnername(String ownername) {
            this.ownername = ownername;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public String getIdaddr() {
            return idaddr;
        }

        public void setIdaddr(String idaddr) {
            this.idaddr = idaddr;
        }

        public String getNowaddr() {
            return nowaddr;
        }

        public void setNowaddr(String nowaddr) {
            this.nowaddr = nowaddr;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getPhone2() {
            return phone2;
        }

        public void setPhone2(String phone2) {
            this.phone2 = phone2;
        }

        public String getOwnertmpidb() {
            return ownertmpidb;
        }

        public void setOwnertmpidb(String ownertmpidb) {
            this.ownertmpidb = ownertmpidb;
        }

        public String getFid() {
            return fid;
        }

        public void setFid(String fid) {
            this.fid = fid;
        }

        public String getMark() {
            return mark;
        }

        public void setMark(String mark) {
            this.mark = mark;
        }

        public String getOwnerid() {
            return ownerid;
        }

        public void setOwnerid(String ownerid) {
            this.ownerid = ownerid;
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
    }
}
