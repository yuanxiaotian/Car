package com.xuliucar.xuli.xuliucar.bean;

import java.util.List;

/**
 * Created by skyward on 2016/12/7.
 * email：
 */

public class HistorySendBean {

    /**
     * success : true
     * error :
     * message :
     * data : [{"smsid":"记录索引","phone":"目标号码","status":"状态","content":"发送内容","count":"发送条数","sendtime":"发送时间"}]
     */

    private boolean success;
    private String error;
    private String message;
    /**
     * smsid : 记录索引
     * phone : 目标号码
     * status : 状态
     * content : 发送内容
     * count : 发送条数
     * sendtime : 发送时间
     */

    private List<DataBean> data;

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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        private String smsid;
        private String phone;
        private String status;
        private String content;
        private String count;
        private String sendtime;
        private String btype;

        public String getSmsid() {
            return smsid;
        }

        public void setSmsid(String smsid) {
            this.smsid = smsid;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getCount() {
            return count;
        }

        public void setCount(String count) {
            this.count = count;
        }

        public String getSendtime() {
            return sendtime;
        }

        public void setSendtime(String sendtime) {
            this.sendtime = sendtime;
        }

        public String getBtype() {
            return btype;
        }

        public void setBtype(String btype) {
            this.btype = btype;
        }
    }

}
