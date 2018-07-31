package com.xuliucar.xuli.xuliucar.bean;

/**
 * Created by skyward on 2016/7/7.
 *
 */
public class IntentModelBean {
    private String model;
    private String num;
    private String nprice;
    private String tprice;
    public IntentModelBean(String model,String num,String nprice,String tprice){
        this.model = model;
        this.num = num;
        this.nprice = nprice;
        this.tprice = tprice;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getNprice() {
        return nprice;
    }

    public void setNprice(String nprice) {
        this.nprice = nprice;
    }

    public String getTprice() {
        return tprice;
    }

    public void setTprice(String tprice) {
        this.tprice = tprice;
    }
}
