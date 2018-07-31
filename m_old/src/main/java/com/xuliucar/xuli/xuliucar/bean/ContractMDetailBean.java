package com.xuliucar.xuli.xuliucar.bean;

/**
 * Created by skyward on 2016/11/30.
 * email：
 */

public class ContractMDetailBean {

    private DataBean data;


    private String error;
    private boolean success;
    private String message;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static class DataBean {
        private String model;
        private String status;
        private String dealprice;
        private String downpay;
        private String framenum;
        private String paydate;
        private String dealtype;
        private String deposits;
        private String cartype;
        private String carytpe;
        private String clientname;
        private String id;
        private String insttl;
        private String principal;
        private String interest;
        private String enginenum;
        private String instnum;
        private String cnum;

        public String getModel() {
            return model;
        }

        public void setModel(String model) {
            this.model = model;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getDealprice() {
            return dealprice;
        }

        public void setDealprice(String dealprice) {
            this.dealprice = dealprice;
        }

        public String getDownpay() {
            return downpay;
        }

        public void setDownpay(String downpay) {
            this.downpay = downpay;
        }

        public String getFramenum() {
            return framenum;
        }

        public void setFramenum(String framenum) {
            this.framenum = framenum;
        }

        public String getPaydate() {
            return paydate;
        }

        public void setPaydate(String paydate) {
            this.paydate = paydate;
        }

        public String getDealtype() {
            return dealtype;
        }

        public void setDealtype(String dealtype) {
            this.dealtype = dealtype;
        }

        public String getDeposits() {
            return deposits;
        }

        public void setDeposits(String deposits) {
            this.deposits = deposits;
        }

        public String getCartype() {
            return cartype;
        }

        public void setCartype(String cartype) {
            this.cartype = cartype;
        }

        public String getCarytpe() {
            return carytpe;
        }

        public void setCarytpe(String carytpe) {
            this.carytpe = carytpe;
        }

        public String getClientname() {
            return clientname;
        }

        public void setClientname(String clientname) {
            this.clientname = clientname;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getInsttl() {
            return insttl;
        }

        public void setInsttl(String insttl) {
            this.insttl = insttl;
        }

        public String getPrincipal() {
            return principal;
        }

        public void setPrincipal(String principal) {
            this.principal = principal;
        }

        public String getInterest() {
            return interest;
        }

        public void setInterest(String interest) {
            this.interest = interest;
        }

        public String getEnginenum() {
            return enginenum;
        }

        public void setEnginenum(String enginenum) {
            this.enginenum = enginenum;
        }

        public String getInstnum() {
            return instnum;
        }

        public void setInstnum(String instnum) {
            this.instnum = instnum;
        }

        public String getCnum() {
            return cnum;
        }

        public void setCnum(String cnum) {
            this.cnum = cnum;
        }
    }
}
