package com.xuliucar.xuli.xuliucar.bean;

import java.util.List;

/**
 * Created by skyward on 2016/11/30.
 * email：
 */

public class AchieveManageBean {



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

    public static class DataBean {
        private String id;
        private String phone;
        private String suc;
        private String fail;
        private String goalsum;
        private String name;
        private String ing;
        private String ordersum;
        private int dealsum;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getSuc() {
            return suc;
        }

        public void setSuc(String suc) {
            this.suc = suc;
        }

        public String getFail() {
            return fail;
        }

        public void setFail(String fail) {
            this.fail = fail;
        }

        public String getGoalsum() {
            return goalsum;
        }

        public void setGoalsum(String goalsum) {
            this.goalsum = goalsum;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getIng() {
            return ing;
        }

        public void setIng(String ing) {
            this.ing = ing;
        }

        public String getOrdersum() {
            return ordersum;
        }

        public void setOrdersum(String ordersum) {
            this.ordersum = ordersum;
        }

        public int getDealsum() {
            return dealsum;
        }

        public void setDealsum(int dealsum) {
            this.dealsum = dealsum;
        }
    }
}
