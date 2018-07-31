package com.xuliucar.xuli.xuliucar.bean;

import java.util.List;

/**
 * Created by skyward on 2016/11/29.
 * email：
 */

public class PendingPayBean {

    private PendingPayBean.DataBean data;

    private String error;
    private boolean success;
    private String message;

    public PendingPayBean.DataBean getData() {
        return data;
    }

    public void setData(PendingPayBean.DataBean data) {
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
        private String left_m;

        private List<InfoBean> info;

        public String getLeft_m() {
            return left_m;
        }

        public void setLeft_m(String left_m) {
            this.left_m = left_m;
        }

        public List<InfoBean> getInfo() {
            return info;
        }

        public void setInfo(List<InfoBean> info) {
            this.info = info;
        }

        public static class InfoBean {
            private String itemname;
            private String cid;
            private String left;
            private String plates;

            public String getItemname() {
                return itemname;
            }

            public void setItemname(String itemname) {
                this.itemname = itemname;
            }

            public String getCid() {
                return cid;
            }

            public void setCid(String cid) {
                this.cid = cid;
            }

            public String getLeft() {
                return left;
            }

            public void setLeft(String left) {
                this.left = left;
            }

            public String getPlates() {
                return plates;
            }

            public void setPlates(String plates) {
                this.plates = plates;
            }
        }
    }
}
