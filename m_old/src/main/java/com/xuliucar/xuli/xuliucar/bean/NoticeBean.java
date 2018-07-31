package com.xuliucar.xuli.xuliucar.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by skyward on 2016/7/15.
 */
public class NoticeBean implements Serializable {
    private String error;
    private boolean success;
    private String message;
    private List<NoticeList> data;

    private List<NewNoticeData> newList;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public boolean getSuccess() {
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

    public List<NoticeList> getData() {
        return data;
    }

    public void setData(List<NoticeList> data) {
        this.data = data;
    }

    public List<NewNoticeData> getNewList() {
        return newList;
    }

    public void setNewList(List<NewNoticeData> newList) {
        this.newList = newList;
    }


    public static class NewNoticeData {
        private String title_zf;
        private String title_xl;
        private String title_xt;

        public String getTitle_zf() {
            return title_zf;
        }

        public void setTitle_zf(String title_zf) {
            this.title_zf = title_zf;
        }

        public String getTitle_xl() {
            return title_xl;
        }

        public void setTitle_xl(String title_xl) {
            this.title_xl = title_xl;
        }

        public String getTitle_xt() {
            return title_xt;
        }

        public void setTitle_xt(String title_xt) {
            this.title_xt = title_xt;
        }
    }


    public class NoticeList implements Serializable {
        private String title;
        private String scon;
        private String url;
        private String con;
        private String type;
        private String time;


        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getScon() {
            return scon;
        }

        public void setScon(String scon) {
            this.scon = scon;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getCon() {
            return con;
        }

        public void setCon(String con) {
            this.con = con;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        @Override
        public String toString() {
            return "{" +
                    "title='" + title + '\'' +
                    ", scon='" + scon + '\'' +
                    ", url='" + url + '\'' +
                    ", con='" + con + '\'' +
                    ", type='" + type + '\'' +
                    ", time='" + time + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "NoticeBean{" +
                "error='" + error + '\'' +
                ", success='" + success + '\'' +
                ", data=" + data +
                '}';
    }
}
