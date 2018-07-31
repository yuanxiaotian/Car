package com.xuliucar.xuli.xuliucar.bean;

/**
 * Created by skyward on 2017/1/3.
 * email：
 */

public class PriceRecordBean {
    private String plate;
    private String num;
    private String payMoney;
    private String status;
    private String payTime;

    public PriceRecordBean(String plate, String num, String payMoney, String status, String payTime) {
        this.plate = plate;
        this.num = num;
        this.payMoney = payMoney;
        this.status = status;
        this.payTime = payTime;
    }

    public String getPlate() {
        return plate;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getPayMoney() {
        return payMoney;
    }

    public void setPayMoney(String payMoney) {
        this.payMoney = payMoney;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPayTime() {
        return payTime;
    }

    public void setPayTime(String payTime) {
        this.payTime = payTime;
    }
}
