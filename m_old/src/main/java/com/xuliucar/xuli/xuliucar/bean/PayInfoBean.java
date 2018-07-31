package com.xuliucar.xuli.xuliucar.bean;


import java.io.Serializable;
import java.util.List;

/**
 * Created by skyward on 2016/7/4.
 *
 */
public class PayInfoBean implements Serializable {



    private String error;
    private boolean success;
    private String message;


    private List<DataBean> data;

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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean implements Serializable{
        private String deleft;
        private String phone;
        private String charge;
        private String pay_year;
        private String plates;
        private String apd;
        private String owner;
        private String left;
        private String ownerid;
        private String cid;

        public String getDeleft() {
            return deleft;
        }

        public void setDeleft(String deleft) {
            this.deleft = deleft;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getCharge() {
            return charge;
        }

        public void setCharge(String charge) {
            this.charge = charge;
        }

        public String getPay_year() {
            return pay_year;
        }

        public void setPay_year(String pay_year) {
            this.pay_year = pay_year;
        }

        public String getPlates() {
            return plates;
        }

        public void setPlates(String plates) {
            this.plates = plates;
        }

        public String getApd() {
            return apd;
        }

        public void setApd(String apd) {
            this.apd = apd;
        }

        public String getOwner() {
            return owner;
        }

        public void setOwner(String owner) {
            this.owner = owner;
        }

        public String getLeft() {
            return left;
        }

        public void setLeft(String left) {
            this.left = left;
        }

        public String getOwnerid() {
            return ownerid;
        }

        public void setOwnerid(String ownerid) {
            this.ownerid = ownerid;
        }

        public String getCid() {
            return cid;
        }

        public void setCid(String cid) {
            this.cid = cid;
        }
    }
}
