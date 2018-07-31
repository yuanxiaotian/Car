package com.xuliucar.me.model;

import android.text.TextUtils;

import com.cangmaomao.lib.utils.DateUtil;

import java.util.Date;
import java.util.Map;

public class Noteinfo {

    /**
     * success : true
     * error : null
     * message :
     * data : {"{9AEFBF1E-8B65-E915-6D1B-227D377C85AD}":{"mod":"finance","class":"3","itemid":"151","cid":"1328","year":"2018","content":"您设置的车辆:[粤C3G9L7]的[123]费用提醒时间已到,请留意处理。","timestamp":1532506141,"isread":0}}
     */

    /**
     * "mod": "finance",
     * "class": "3",
     * "itemid": "152",
     * "cid": "1556",
     * "year": "2018",
     * "content": "\u60a8\u8bbe\u7f6e\u7684\u8f66\u8f86:[\u7ca4AY666Y]\u7684[123]\u8d39\u7528\u63d0\u9192\u65f6\u95f4\u5df2\u5230,\u8bf7\u7559\u610f\u5904\u7406\u3002",
     * "timestamp": 1532509141,
     * "isread": "1"
     */

    private boolean success;
    private String error;
    private String message;

    private Map<String, Noteinfo.DataBean> data;

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

    public Map<String, Noteinfo.DataBean> getData() {
        return data;
    }

    public void setData(Map<String, Noteinfo.DataBean> data) {
        this.data = data;
    }


    public static class DataBean {
        private String mod;
        private String itemid;
        private String year;
        private String content;
        private String timestamp;
        private String isread;

        public String getMod() {
            return mod;
        }

        public void setMod(String mod) {
            this.mod = mod;
        }

        public String getItemid() {
            return itemid;
        }

        public void setItemid(String itemid) {
            this.itemid = itemid;
        }

        public String getYear() {
            return year;
        }

        public void setYear(String year) {
            this.year = year;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getTimestamp() {
            Date d = new Date(Integer.parseInt(timestamp) * 1000L);
            String d1 = DateUtil.parseDateToStr(d, DateUtil.DATE_FORMAT_YYYY_MM_DD);
            if (TextUtils.isEmpty(d1)) {
                return "";
            }
            return d1;
        }

        public void setTimestamp(String timestamp) {
            this.timestamp = timestamp;
        }

        public String getIsread() {
            return isread;
        }

        public void setIsread(String isread) {
            this.isread = isread;
        }
    }

}