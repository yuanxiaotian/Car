package com.xuliucar.xuli.xuliucar.bean;

import java.math.BigDecimal;
import java.util.List;

public class SaleManagerBean {

    /**
     * success : true
     * error : null
     * data : [{"id":"10","salername":"李四","ordernum":"D158df0cf0ce8ae","client":"及返点","dealprice":"130000.00","clevel":"A类客户","dealtype":"全包","status":"已成交","stime":"2017-04-01","dealtime":"2017-04-01"},{"id":"9","salername":"测试销售","ordernum":"D1569a4b767a999","client":"testclienthunk","dealprice":"1000000.00","clevel":"A类客户","dealtype":"裸车","status":"已成交","stime":"2016-01-16","dealtime":"2016-01-16"},{"id":"8","salername":"测试销售","ordernum":"D1569392f54cd76","client":"夏大人","dealprice":"160000.00","clevel":"A类客户","dealtype":"全包","status":"已成交","stime":"2016-01-11","dealtime":"2016-01-16"},{"id":"7","salername":"测试销售","ordernum":"D156938c64ca874","client":"王二麻子","dealprice":"160000.00","clevel":"A类客户","dealtype":"全包","status":"已成交","stime":"2016-01-11","dealtime":"2016-01-12"},{"id":"6","salername":"测试销售","ordernum":"D1569387b8db01b","client":"尹立志","dealprice":"160000.00","clevel":"A类客户","dealtype":"全包","status":"已成交","stime":"2016-01-11","dealtime":"2016-01-11"},{"id":"5","salername":"测试销售","ordernum":"D1568d13be7a58b","client":"周星驰","dealprice":"125000.00","clevel":"A类客户","dealtype":"全包","status":"已成交","stime":"2016-01-06","dealtime":"2016-01-10"},{"id":"4","salername":"销售A","ordernum":"D15687998838ed8","client":"林小莉","dealprice":"132800.00","clevel":"A类客户","dealtype":"裸车","status":"已成交","stime":"2016-01-02","dealtime":"0"},{"id":"3","salername":"销售A","ordernum":"D15684d95b97414","client":"刘海新","dealprice":"160000.00","clevel":"A类客户","dealtype":"全包","status":"已成交","stime":"2015-12-31","dealtime":"0"},{"id":"2","salername":"销售A","ordernum":"D1567a134c92cab","client":"夏文亮","dealprice":"14400873.00","clevel":"A类客户","dealtype":"全包","status":"已成交","stime":"2015-12-23","dealtime":"0"},{"id":"1","salername":"销售A","ordernum":"D156767c9caacde","client":"测试客户","dealprice":"156002.60","clevel":"B类客户","dealtype":"裸车","status":"已成交","stime":"2015-12-20","dealtime":"0"}]
     */

    private boolean success;
    private Object error;
    private List<DataBean> data;

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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 10
         * salername : 李四
         * ordernum : D158df0cf0ce8ae
         * client : 及返点
         * dealprice : 130000.00
         * clevel : A类客户
         * dealtype : 全包
         * status : 已成交
         * stime : 2017-04-01
         * dealtime : 2017-04-01
         */

        private String id;
        private String salername;
        private String ordernum;
        private String client;
        private String dealprice;
        private String clevel;
        private String dealtype;
        private String status;
        private String stime;
        private String dealtime;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getSalername() {
            return salername;
        }

        public void setSalername(String salername) {
            this.salername = salername;
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

        public String getDealprice() {
            return dealprice;
        }

        public void setDealprice(String dealprice) {
            this.dealprice = dealprice;
        }

        public String getClevel() {
            return clevel;
        }

        public void setClevel(String clevel) {
            this.clevel = clevel;
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

        public String getStime() {
            return stime;
        }

        public void setStime(String stime) {
            this.stime = stime;
        }

        public String getDealtime() {
            return dealtime;
        }

        public void setDealtime(String dealtime) {
            this.dealtime = dealtime;
        }
    }
}
