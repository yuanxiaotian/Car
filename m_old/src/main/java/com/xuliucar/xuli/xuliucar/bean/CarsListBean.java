package com.xuliucar.xuli.xuliucar.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by skyward on 2016/11/23.
 */

public class CarsListBean implements Serializable{

    public CarsListBean(){

    }

    private boolean success;
    private String error;
    private String message;

    private DataBean data;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private int count;

        private List<InfoBean> info;

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public List<InfoBean> getInfo() {
            return info;
        }

        public void setInfo(List<InfoBean> info) {
            this.info = info;
        }

        public static class InfoBean implements Serializable{
            private String id;
            private String owner;
            private String driver;
            private String driver2;
            private String cartype;
            private String fueltype;
            private String isown;
            private String plates;
            private String drphone;
            private String drphone2;
            private String d2rphone;
            private String d2rphone2;
            private String phone;
            private String rphone;
            private String rphone2;

            public InfoBean(String id, String owner, String driver, String driver2, String cartype, String fueltype, String isown, String plates, String drphone, String drphone2, String d2rphone, String d2rphone2, String phone, String rphone, String rphone2) {
                this.id = id;
                this.owner = owner;
                this.driver = driver;
                this.driver2 = driver2;
                this.cartype = cartype;
                this.fueltype = fueltype;
                this.isown = isown;
                this.plates = plates;
                this.drphone = drphone;
                this.drphone2 = drphone2;
                this.d2rphone = d2rphone;
                this.d2rphone2 = d2rphone2;
                this.phone = phone;
                this.rphone = rphone;
                this.rphone2 = rphone2;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getOwner() {
                return owner;
            }

            public void setOwner(String owner) {
                this.owner = owner;
            }

            public String getDriver() {
                return driver;
            }

            public void setDriver(String driver) {
                this.driver = driver;
            }

            public String getDriver2() {
                return driver2;
            }

            public void setDriver2(String driver2) {
                this.driver2 = driver2;
            }

            public String getCartype() {
                return cartype;
            }

            public void setCartype(String cartype) {
                this.cartype = cartype;
            }

            public String getFueltype() {
                return fueltype;
            }

            public void setFueltype(String fueltype) {
                this.fueltype = fueltype;
            }

            public String getIsown() {
                return isown;
            }

            public void setIsown(String isown) {
                this.isown = isown;
            }

            public String getPlates() {
                return plates;
            }

            public void setPlates(String plates) {
                this.plates = plates;
            }

            public String getDrphone() {
                return drphone;
            }

            public void setDrphone(String drphone) {
                this.drphone = drphone;
            }

            public String getDrphone2() {
                return drphone2;
            }

            public void setDrphone2(String drphone2) {
                this.drphone2 = drphone2;
            }

            public String getD2rphone() {
                return d2rphone;
            }

            public void setD2rphone(String d2rphone) {
                this.d2rphone = d2rphone;
            }

            public String getD2rphone2() {
                return d2rphone2;
            }

            public void setD2rphone2(String d2rphone2) {
                this.d2rphone2 = d2rphone2;
            }

            public String getPhone() {
                return phone;
            }

            public void setPhone(String phone) {
                this.phone = phone;
            }

            public String getRphone() {
                return rphone;
            }

            public void setRphone(String rphone) {
                this.rphone = rphone;
            }

            public String getRphone2() {
                return rphone2;
            }

            public void setRphone2(String rphone2) {
                this.rphone2 = rphone2;
            }
        }
    }
}
