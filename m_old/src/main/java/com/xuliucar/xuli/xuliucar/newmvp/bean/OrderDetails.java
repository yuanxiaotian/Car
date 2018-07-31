package com.xuliucar.xuli.xuliucar.newmvp.bean;

import java.util.List;

public class OrderDetails {

    /**
     * success : true
     * error : null
     * data : {"id":"10","clevel":"A类客户","ordernum":"D158df0cf0ce8ae","client":"及返点","phone":"18818866666","dealprice":"130000.00","dealtype":"裸车","status":"已成交","info":[{"model":"CA5041XXYP40K2L1E4A85-222","num":"1","nprice":"100000","tprice":"130000"}]}
     */

    private boolean success;
    private Object error;
    private DataBean data;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Object getError() {
        return error;
    }

    public void setError(Object error) {
        this.error = error;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 10
         * clevel : A类客户
         * ordernum : D158df0cf0ce8ae
         * client : 及返点
         * phone : 18818866666
         * dealprice : 130000.00
         * dealtype : 裸车
         * status : 已成交
         * info : [{"model":"CA5041XXYP40K2L1E4A85-222","num":"1","nprice":"100000","tprice":"130000"}]
         */

        private String id;
        private String clevel;
        private String ordernum;
        private String client;
        private String phone;
        private String dealprice;
        private String dealtype;
        private String status;
        private List<InfoBean> info;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getClevel() {
            return clevel;
        }

        public void setClevel(String clevel) {
            this.clevel = clevel;
        }

        public String getOrdernum() {
            return ordernum;
        }

        public void setOrdernum(String ordernum) {
            this.ordernum = ordernum;
        }

        public String getClient() {
            return client;
        }

        public void setClient(String client) {
            this.client = client;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getDealprice() {
            return dealprice;
        }

        public void setDealprice(String dealprice) {
            this.dealprice = dealprice;
        }

        public String getDealtype() {
            return dealtype;
        }

        public void setDealtype(String dealtype) {
            this.dealtype = dealtype;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public List<InfoBean> getInfo() {
            return info;
        }

        public void setInfo(List<InfoBean> info) {
            this.info = info;
        }

        public static class InfoBean {
            /**
             * model : CA5041XXYP40K2L1E4A85-222
             * num : 1
             * nprice : 100000
             * tprice : 130000
             */

            private String model;
            private String num;
            private String nprice;
            private String tprice;

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
    }
}
