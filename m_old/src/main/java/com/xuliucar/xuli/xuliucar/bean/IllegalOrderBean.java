package com.xuliucar.xuli.xuliucar.bean;

/**
 * Created by skyward on 2017/1/3.
 * email：
 */

public class IllegalOrderBean {
    private String plate;
    private String stauts;
    private String points;
    private String illegalAct;

    public IllegalOrderBean(String plate, String stauts, String points, String illegalAct) {
        this.plate = plate;
        this.stauts = stauts;
        this.points = points;
        this.illegalAct = illegalAct;
    }

    public String getPlate() {
        return plate;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }

    public String getStauts() {
        return stauts;
    }

    public void setStauts(String stauts) {
        this.stauts = stauts;
    }

    public String getPoints() {
        return points;
    }

    public void setPoints(String points) {
        this.points = points;
    }

    public String getIllegalAct() {
        return illegalAct;
    }

    public void setIllegalAct(String illegalAct) {
        this.illegalAct = illegalAct;
    }
}
