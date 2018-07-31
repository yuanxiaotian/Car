package com.xuliucar.xuli.xuliucar.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by skyward on 2016/11/30.
 * email：
 */

public class OrderManageBean implements Serializable{



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
        private String id;
        private String stime;
        private String client;
        private String status;
        private String dealprice;
        private String dealtime;
        private String ordernum;
        private String dealtype;
        private String clevel;
        private String salername;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getStime() {
            return stime;
        }

        public void setStime(String stime) {
            this.stime = stime;
        }

        public String getClient() {
            return client;
        }

        public void setClient(String client) {
            this.client = client;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getDealprice() {
            return dealprice;
        }

        public void setDealprice(String dealprice) {
            this.dealprice = dealprice;
        }

        public String getDealtime() {
            return dealtime;
        }

        public void setDealtime(String dealtime) {
            this.dealtime = dealtime;
        }

        public String getOrdernum() {
            return ordernum;
        }

        public void setOrdernum(String ordernum) {
            this.ordernum = ordernum;
        }

        public String getDealtype() {
            return dealtype;
        }

        public void setDealtype(String dealtype) {
            this.dealtype = dealtype;
        }

        public String getClevel() {
            return clevel;
        }

        public void setClevel(String clevel) {
            this.clevel = clevel;
        }

        public String getSalername() {
            return salername;
        }

        public void setSalername(String salername) {
            this.salername = salername;
        }
    }
}
