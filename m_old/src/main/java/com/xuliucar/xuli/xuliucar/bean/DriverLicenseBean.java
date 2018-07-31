package com.xuliucar.xuli.xuliucar.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by skyward on 2016/12/2.
 * email：
 */

public class DriverLicenseBean {



    private DataBean data;

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

        public static class InfoBean implements Serializable{
            private String endtime;
            private String smstarid;
            private String sex;
            private String drivername;
            private String rphone2;
            private String phone2;
            private String rphone;
            private String licnum;
            private String ct;

            public String getEndtime() {
                return endtime;
            }

            public void setEndtime(String endtime) {
                this.endtime = endtime;
            }

            public String getSmstarid() {
                return smstarid;
            }

            public void setSmstarid(String smstarid) {
                this.smstarid = smstarid;
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

            public String getRphone2() {
                return rphone2;
            }

            public void setRphone2(String rphone2) {
                this.rphone2 = rphone2;
            }

            public String getPhone2() {
                return phone2;
            }

            public void setPhone2(String phone2) {
                this.phone2 = phone2;
            }

            public String getRphone() {
                return rphone;
            }

            public void setRphone(String rphone) {
                this.rphone = rphone;
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
