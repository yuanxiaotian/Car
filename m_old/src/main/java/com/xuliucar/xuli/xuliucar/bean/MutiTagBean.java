package com.xuliucar.xuli.xuliucar.bean;

/**
 * Created by skyward on 2016/12/8.
 * email：
 */

public class MutiTagBean {
    private String type;
    private String typename;

    public MutiTagBean(String typename, String type) {
        this.typename = typename;
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTypename() {
        return typename;
    }

    public void setTypename(String typename) {
        this.typename = typename;
    }
}
