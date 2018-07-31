package com.xuliucar.xuli.xuliucar.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by skyward on 2016/12/2.
 * email：
 */

public class PassPerPetaueBean {

    /**
     * count : 69
     * info : [{"owner":"龙国辉","id":"1301","rphone2":"","plates":"粤AAE977","rphone":"15818156885","chktime":"2016-07","stype":"等级评定"}]
     */

    private DataBean data;
    /**
     * data : {"count":69,"info":[{"owner":"龙国辉","id":"1301","rphone2":"","plates":"粤AAE977","rphone":"15818156885","chktime":"2016-07","stype":"等级评定"}]}
     * error :
     * success : true
     * message :
     */

    private String error;
    private boolean success;
    private String message;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static class DataBean {
        private int count;
        /**
         * owner : 龙国辉
         * id : 1301
         * rphone2 :
         * plates : 粤AAE977
         * rphone : 15818156885
         * chktime : 2016-07
         * stype : 等级评定
         */

        private List<InfoBean> info;

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public List<InfoBean> getInfo() {
            return info;
        }

        public void setInfo(List<InfoBean> info) {
            this.info = info;
        }

        public static class InfoBean implements Serializable {
            private String owner;
            private String smstarid;
            private String id;
            private String rphone2;
            private String plates;
            private String rphone;
            private String chktime;
            private String stype;
            private boolean showAndHide = false;
            private boolean select = false;
            private String trstime;
            private String tretime;
            private String ownername;
            private String ownerphone;
            private String ownerphone2;
            private String chketime;
            private String enddate;
            private String type;
            private String sdate;
            private String chkmonth;
            private String endtime;
            private String sex;
            private String drivername;
            private String phone2;
            private String licnum;
            private String ct;


            public String getOwner() {
                return owner;
            }

            public void setOwner(String owner) {
                this.owner = owner;
            }

            public String getSmstarid() {
                return smstarid;
            }

            public void setSmstarid(String smstarid) {
                this.smstarid = smstarid;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getRphone2() {
                return rphone2;
            }

            public void setRphone2(String rphone2) {
                this.rphone2 = rphone2;
            }

            public String getPlates() {
                return plates;
            }

            public void setPlates(String plates) {
                this.plates = plates;
            }

            public String getRphone() {
                return rphone;
            }

            public void setRphone(String rphone) {
                this.rphone = rphone;
            }

            public String getChktime() {
                return chktime;
            }

            public void setChktime(String chktime) {
                this.chktime = chktime;
            }

            public String getStype() {
                return stype;
            }

            public void setStype(String stype) {
                this.stype = stype;
            }

            public boolean isShowAndHide() {
                return showAndHide;
            }

            public void setShowAndHide(boolean showAndHide) {
                this.showAndHide = showAndHide;
            }

            public boolean isSelect() {
                return select;
            }

            public void setSelect(boolean select) {
                this.select = select;
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

            public String getOwnername() {
                return ownername;
            }

            public void setOwnername(String ownername) {
                this.ownername = ownername;
            }

            public String getOwnerphone() {
                return ownerphone;
            }

            public void setOwnerphone(String ownerphone) {
                this.ownerphone = ownerphone;
            }

            public String getOwnerphone2() {
                return ownerphone2;
            }

            public void setOwnerphone2(String ownerphone2) {
                this.ownerphone2 = ownerphone2;
            }

            public String getChketime() {
                return chketime;
            }

            public void setChketime(String chketime) {
                this.chketime = chketime;
            }

            public String getEnddate() {
                return enddate;
            }

            public void setEnddate(String enddate) {
                this.enddate = enddate;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getSdate() {
                return sdate;
            }

            public void setSdate(String sdate) {
                this.sdate = sdate;
            }

            public String getChkmonth() {
                return chkmonth;
            }

            public void setChkmonth(String chkmonth) {
                this.chkmonth = chkmonth;
            }

            public String getEndtime() {
                return endtime;
            }

            public void setEndtime(String endtime) {
                this.endtime = endtime;
            }

            public String getSex() {
                return sex;
            }

            public void setSex(String sex) {
                this.sex = sex;
            }

            public String getDrivername() {
                return drivername;
            }

            public void setDrivername(String drivername) {
                this.drivername = drivername;
            }

            public String getPhone2() {
                return phone2;
            }

            public void setPhone2(String phone2) {
                this.phone2 = phone2;
            }

            public String getLicnum() {
                return licnum;
            }

            public void setLicnum(String licnum) {
                this.licnum = licnum;
            }

            public String getCt() {
                return ct;
            }

            public void setCt(String ct) {
                this.ct = ct;
            }
        }
    }
}
