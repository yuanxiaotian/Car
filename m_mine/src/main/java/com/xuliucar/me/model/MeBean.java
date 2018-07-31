package com.xuliucar.me.model;

/**
 * Created by skyward on 2016/11/4.
 *
 */

public class MeBean {

    /**
     * user_count : 10
     * driver_count : 41
     * unread_count : 0
     * total_count : 144
     * owner_count : 93
     */

    private DataBean data;
    /**
     * data : {"user_count":"10","driver_count":"41","unread_count":"0","total_count":"144","owner_count":"93"}
     * error : null
     * success : true
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
        private String user_count;
        private String driver_count;
        private String unread_count;
        private String total_count;
        private String owner_count;

        public String getUser_count() {
            return user_count;
        }

        public void setUser_count(String user_count) {
            this.user_count = user_count;
        }

        public String getDriver_count() {
            return driver_count;
        }

        public void setDriver_count(String driver_count) {
            this.driver_count = driver_count;
        }

        public String getUnread_count() {
            return unread_count;
        }

        public void setUnread_count(String unread_count) {
            this.unread_count = unread_count;
        }

        public String getTotal_count() {
            return total_count;
        }

        public void setTotal_count(String total_count) {
            this.total_count = total_count;
        }

        public String getOwner_count() {
            return owner_count;
        }

        public void setOwner_count(String owner_count) {
            this.owner_count = owner_count;
        }
    }
}
