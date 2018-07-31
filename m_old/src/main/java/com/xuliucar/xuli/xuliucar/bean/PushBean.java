package com.xuliucar.xuli.xuliucar.bean;

import java.io.Serializable;

/**
 * Created by skyward on 2016/9/20.
 */
public class PushBean implements Serializable{
    private String messageid;
    private String timestamp;
    private String content;
    private String isread;
    private String mod;
    private String classes;
    private String year;
    private String itemid;
    private String cid;

    public PushBean(String messageid,String timestamp,String content,String isread, String mod, String classes, String year, String itemid, String cid) {
        this.messageid = messageid;
        this.timestamp = timestamp;
        this.content = content;
        this.isread = isread;
        this.mod = mod;
        this.classes = classes;
        this.year = year;
        this.itemid = itemid;
        this.cid = cid;
    }

    public String getMessageid() {
        return messageid;
    }

    public void setMessageid(String messageid) {
        this.messageid = messageid;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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

    public String getMod() {
        return mod;
    }

    public void setMod(String mod) {
        this.mod = mod;
    }

    public String getClasses() {
        return classes;
    }

    public void setClasses(String classes) {
        this.classes = classes;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getItemid() {
        return itemid;
    }

    public void setItemid(String itemid) {
        this.itemid = itemid;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }
}
