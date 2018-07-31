package com.xuliucar.xuli.xuliucar.bean;

/**
 * Created by skyward on 2016/12/7.
 * email：
 */

public class MessageCountBean {

    /**
     * success : true
     * error  :
     * message :
     * data : {"leftcount":"账户余额","todaycount":"今日已发","totalcount":"累积已发"}
     */

    private boolean success;
    private String error;
    private String message;
    /**
     * leftcount : 账户余额
     * todaycount : 今日已发
     * totalcount : 累积已发
     */

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
        private String leftcount;
        private String todaycount;
        private String totalcount;
        private String tips;
        public String getLeftcount() {
            return leftcount;
        }

        public void setLeftcount(String leftcount) {
            this.leftcount = leftcount;
        }

        public String getTodaycount() {
            return todaycount;
        }

        public void setTodaycount(String todaycount) {
            this.todaycount = todaycount;
        }

        public String getTotalcount() {
            return totalcount;
        }

        public void setTotalcount(String totalcount) {
            this.totalcount = totalcount;
        }

        public String getTips() {
            return tips;
        }

        public void setTips(String tips) {
            this.tips = tips;
        }
    }
}
