package com.xuliucar.xuli.xuliucar.bean;

import java.util.List;

public class HomeBanner {


    /**
     * success : true
     * error : null
     * data : [{"b1":"http://img.fexeak.com/indexbanner/banner1.png"},{"b2":"http://img.fexeak.com/indexbanner/574ff4dc65629.png"},{"b3":"http://img.fexeak.com/indexbanner/57b562a6921f4.jpg"},{"b4":"http://img.fexeak.com/indexbanner/57c6bbdec5bf2.jpg"},{"b5":"http://img.fexeak.com/indexbanner/57c6bc1aaf95f.jpg"},{"b6":"http://img.fexeak.com/indexbanner/57c6bc343d96e.jpg"},{"b7":"http://img.fexeak.com/indexbanner/57c6bc41779b4.jpg"}]
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
         * b1 : http://img.fexeak.com/indexbanner/banner1.png
         * b2 : http://img.fexeak.com/indexbanner/574ff4dc65629.png
         * b3 : http://img.fexeak.com/indexbanner/57b562a6921f4.jpg
         * b4 : http://img.fexeak.com/indexbanner/57c6bbdec5bf2.jpg
         * b5 : http://img.fexeak.com/indexbanner/57c6bc1aaf95f.jpg
         * b6 : http://img.fexeak.com/indexbanner/57c6bc343d96e.jpg
         * b7 : http://img.fexeak.com/indexbanner/57c6bc41779b4.jpg
         */

        private String b1;
        private String b2;
        private String b3;
        private String b4;
        private String b5;
        private String b6;
        private String b7;

        public String getB1() {
            return b1;
        }

        public void setB1(String b1) {
            this.b1 = b1;
        }

        public String getB2() {
            return b2;
        }

        public void setB2(String b2) {
            this.b2 = b2;
        }

        public String getB3() {
            return b3;
        }

        public void setB3(String b3) {
            this.b3 = b3;
        }

        public String getB4() {
            return b4;
        }

        public void setB4(String b4) {
            this.b4 = b4;
        }

        public String getB5() {
            return b5;
        }

        public void setB5(String b5) {
            this.b5 = b5;
        }

        public String getB6() {
            return b6;
        }

        public void setB6(String b6) {
            this.b6 = b6;
        }

        public String getB7() {
            return b7;
        }

        public void setB7(String b7) {
            this.b7 = b7;
        }
    }
}
