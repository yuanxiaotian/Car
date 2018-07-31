package com.xuliucar.xuli.xuliucar.bean;

/**
 * Created by skyward on 2016/7/1.
 *
 */
public class InfoOverviewBean {
    private String carType;
    private String carTypeNum;

    public InfoOverviewBean(String carType, String carTypeNum){
        this.carType =carType;
        this.carTypeNum = carTypeNum;
    }

    public String getCarType() {
        return carType;
    }

    public void setCarType(String carType) {
        this.carType = carType;
    }

    public String getCarTypeNum() {
        return carTypeNum;
    }

    public void setCarTypeNum(String carTypeNum) {
        this.carTypeNum = carTypeNum;
    }
}
