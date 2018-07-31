package com.xuliucar.xuli.xuliucar.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by skyward on 2016/12/1.
 * email：
 */

public class DoRoadTCBean {


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
            private String rphone;
            private String id;
            private String smstarid;
            private String rphone2;
            private String plates;

            public String getRphone() {
                return rphone;
            }

            public void setRphone(String rphone) {
                this.rphone = rphone;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getSmstarid() {
                return smstarid;
            }

            public void setSmstarid(String smstarid) {
                this.smstarid = smstarid;
            }

            public String getRphone2() {
                return rphone2;
            }

            public void setRphone2(String rphone2) {
                this.rphone2 = rphone2;
            }

            public String getPlates() {
                return plates;
            }

            public void setPlates(String plates) {
                this.plates = plates;
            }
        }
    }
}
