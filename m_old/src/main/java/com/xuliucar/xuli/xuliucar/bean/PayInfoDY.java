package com.xuliucar.xuli.xuliucar.bean;

/**
 * Created by skyward on 2016/7/6.
 *
 */
public class PayInfoDY {
    private String mark;
    private String item;
    private String charge;
    private String apd;
    private String ct;
    private String upd;
    private String itemid;
    public PayInfoDY(String mark,String item,String charge,String apd,String ct,String upd,String itemid){
        this.mark = mark;
        this.item = item;
        this.charge =charge;
        this.apd = apd;
        this.ct =ct;
        this.upd =upd;
        this.itemid = itemid;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getCharge() {
        return charge;
    }

    public void setCharge(String charge) {
        this.charge = charge;
    }

    public String getApd() {
        return apd;
    }

    public void setApd(String apd) {
        this.apd = apd;
    }

    public String getCt() {
        return ct;
    }

    public void setCt(String ct) {
        this.ct = ct;
    }

    public String getUpd() {
        return upd;
    }

    public void setUpd(String upd) {
        this.upd = upd;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getItemid() {
        return itemid;
    }

    public void setItemid(String itemid) {
        this.itemid = itemid;
    }
}
