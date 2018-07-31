package com.xuliucar.me.model;

/**
 * Created by skyward on 2016/11/24.
 * email：
 */

public class MessageBean {


    private String message;
    private String error;
    private boolean success;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
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
}
