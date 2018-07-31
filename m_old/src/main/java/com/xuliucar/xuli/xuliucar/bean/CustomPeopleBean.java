package com.xuliucar.xuli.xuliucar.bean;

import java.io.Serializable;

/**
 * Created by skyward on 2016/12/8.
 */

public class CustomPeopleBean implements Serializable{
    private int index;
    private String id;
    private String name;

    public CustomPeopleBean(int index, String id, String name) {
        this.index = index;
        this.id = id;
        this.name = name;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
