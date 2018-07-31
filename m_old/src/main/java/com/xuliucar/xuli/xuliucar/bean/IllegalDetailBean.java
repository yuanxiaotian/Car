package com.xuliucar.xuli.xuliucar.bean;

/**
 * Created by skyward on 2017/1/3.
 * email：
 */

public class IllegalDetailBean {
    private String  docNum;
    private String place;
    private String illegalCode;
    private String fine;
    private String lateFine;
    private String servicePay;
    private String totalMoney;

    public IllegalDetailBean(String docNum, String place, String illegalCode, String fine, String lateFine, String servicePay, String totalMoney) {
        this.docNum = docNum;
        this.place = place;
        this.illegalCode = illegalCode;
        this.fine = fine;
        this.lateFine = lateFine;
        this.servicePay = servicePay;
        this.totalMoney = totalMoney;
    }

    public String getDocNum() {
        return docNum;
    }

    public void setDocNum(String docNum) {
        this.docNum = docNum;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getIllegalCode() {
        return illegalCode;
    }

    public void setIllegalCode(String illegalCode) {
        this.illegalCode = illegalCode;
    }

    public String getFine() {
        return fine;
    }

    public void setFine(String fine) {
        this.fine = fine;
    }

    public String getLateFine() {
        return lateFine;
    }

    public void setLateFine(String lateFine) {
        this.lateFine = lateFine;
    }

    public String getServicePay() {
        return servicePay;
    }

    public void setServicePay(String servicePay) {
        this.servicePay = servicePay;
    }

    public String getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(String totalMoney) {
        this.totalMoney = totalMoney;
    }
}
