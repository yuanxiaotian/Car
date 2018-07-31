package com.xuliucar.xuli.xuliucar.bean;

import java.util.List;

/**
 * Created by skyward on 2016/11/30.
 * email：
 */

public class TrusteeshipBean {


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

        public static class InfoBean {
            private String smstarid;
            private String trstime;
            private String tretime;
            private String plates;
            private String ownername;
            private String ownerphone;
            private String ownerphone2;

            public String getSmstarid() {
                return smstarid;
            }

            public void setSmstarid(String smstarid) {
                this.smstarid = smstarid;
            }

            public String getTrstime() {
                return trstime;
            }

            public void setTrstime(String trstime) {
                this.trstime = trstime;
            }

            public String getTretime() {
                return tretime;
            }

            public void setTretime(String tretime) {
                this.tretime = tretime;
            }

            public String getPlates() {
                return plates;
            }

            public void setPlates(String plates) {
                this.plates = plates;
            }

            public String getOwnername() {
                return ownername;
            }

            public void setOwnername(String ownername) {
                this.ownername = ownername;
            }

            public String getOwnerphone() {
                return ownerphone;
            }

            public void setOwnerphone(String ownerphone) {
                this.ownerphone = ownerphone;
            }

            public String getOwnerphone2() {
                return ownerphone2;
            }

            public void setOwnerphone2(String ownerphone2) {
                this.ownerphone2 = ownerphone2;
            }
        }
    }
}
