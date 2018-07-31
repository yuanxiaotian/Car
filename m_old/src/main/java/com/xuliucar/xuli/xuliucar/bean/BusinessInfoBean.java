package com.xuliucar.xuli.xuliucar.bean;

/**
 * Created by skyward on 2016/11/7.
 *
 */

public class BusinessInfoBean {


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
        private String uid;

        private CtinfoBean ctinfo;

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }

        public CtinfoBean getCtinfo() {
            return ctinfo;
        }

        public void setCtinfo(CtinfoBean ctinfo) {
            this.ctinfo = ctinfo;
        }

        public static class CtinfoBean {
            private String compname;
            private String weight;
            private String osize;
            private String tpdate;
            private String tpstr;
            private String tpnum;
            private String p1_thumb;
            private String p1;
            private String p2_thumb;
            private String p2;
            private String p3_thumb;
            private String p3;
            private String modelname;
            private String cartype;

            public String getCompname() {
                return compname;
            }

            public void setCompname(String compname) {
                this.compname = compname;
            }

            public String getWeight() {
                return weight;
            }

            public void setWeight(String weight) {
                this.weight = weight;
            }

            public String getOsize() {
                return osize;
            }

            public void setOsize(String osize) {
                this.osize = osize;
            }

            public String getTpdate() {
                return tpdate;
            }

            public void setTpdate(String tpdate) {
                this.tpdate = tpdate;
            }

            public String getTpstr() {
                return tpstr;
            }

            public void setTpstr(String tpstr) {
                this.tpstr = tpstr;
            }

            public String getTpnum() {
                return tpnum;
            }

            public void setTpnum(String tpnum) {
                this.tpnum = tpnum;
            }

            public String getP1_thumb() {
                return p1_thumb;
            }

            public void setP1_thumb(String p1_thumb) {
                this.p1_thumb = p1_thumb;
            }

            public String getP1() {
                return p1;
            }

            public void setP1(String p1) {
                this.p1 = p1;
            }

            public String getP2_thumb() {
                return p2_thumb;
            }

            public void setP2_thumb(String p2_thumb) {
                this.p2_thumb = p2_thumb;
            }

            public String getP2() {
                return p2;
            }

            public void setP2(String p2) {
                this.p2 = p2;
            }

            public String getP3_thumb() {
                return p3_thumb;
            }

            public void setP3_thumb(String p3_thumb) {
                this.p3_thumb = p3_thumb;
            }

            public String getP3() {
                return p3;
            }

            public void setP3(String p3) {
                this.p3 = p3;
            }

            public String getModelname() {
                return modelname;
            }

            public void setModelname(String modelname) {
                this.modelname = modelname;
            }

            public String getCartype() {
                return cartype;
            }

            public void setCartype(String cartype) {
                this.cartype = cartype;
            }
        }
    }
}
