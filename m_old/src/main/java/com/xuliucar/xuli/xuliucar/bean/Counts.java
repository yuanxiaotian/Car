package com.xuliucar.xuli.xuliucar.bean;

/**
 * Created by skyward on 2016/11/22.
 *
 */

public class Counts {

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
        private int odt_count;
        private int od_count;

        private InfoBean info;

        public int getOdt_count() {
            return odt_count;
        }

        public void setOdt_count(int odt_count) {
            this.odt_count = odt_count;
        }

        public int getOd_count() {
            return od_count;
        }

        public void setOd_count(int od_count) {
            this.od_count = od_count;
        }

        public InfoBean getInfo() {
            return info;
        }

        public void setInfo(InfoBean info) {
            this.info = info;
        }

        public static class InfoBean {
            private int odt_s;
            private int odt_y;
            private int odt_yt;
            private int odt_cw;
            private int odt_is;
            private int odt_tp;
            private int odt_epm;
            private int odt_cl;
            private int odt_dl;
            private int odt_it;
            private int od_dl;
            private int odt_tpychk;
            private int od_tpychk;
            private int od_s;
            private int od_y;
            private int od_yt;
            private int od_cw;
            private int od_is;
            private int od_epm;
            private int od_it;

            public int getOdt_s() {
                return odt_s;
            }

            public void setOdt_s(int odt_s) {
                this.odt_s = odt_s;
            }

            public int getOdt_y() {
                return odt_y;
            }

            public void setOdt_y(int odt_y) {
                this.odt_y = odt_y;
            }

            public int getOdt_yt() {
                return odt_yt;
            }

            public void setOdt_yt(int odt_yt) {
                this.odt_yt = odt_yt;
            }

            public int getOdt_cw() {
                return odt_cw;
            }

            public void setOdt_cw(int odt_cw) {
                this.odt_cw = odt_cw;
            }

            public int getOdt_is() {
                return odt_is;
            }

            public void setOdt_is(int odt_is) {
                this.odt_is = odt_is;
            }

            public int getOdt_tp() {
                return odt_tp;
            }

            public void setOdt_tp(int odt_tp) {
                this.odt_tp = odt_tp;
            }

            public int getOdt_epm() {
                return odt_epm;
            }

            public void setOdt_epm(int odt_epm) {
                this.odt_epm = odt_epm;
            }

            public int getOdt_cl() {
                return odt_cl;
            }

            public void setOdt_cl(int odt_cl) {
                this.odt_cl = odt_cl;
            }

            public int getOdt_dl() {
                return odt_dl;
            }

            public void setOdt_dl(int odt_dl) {
                this.odt_dl = odt_dl;
            }

            public int getOdt_it() {
                return odt_it;
            }

            public void setOdt_it(int odt_it) {
                this.odt_it = odt_it;
            }

            public int getOd_dl() {
                return od_dl;
            }

            public void setOd_dl(int od_dl) {
                this.od_dl = od_dl;
            }

            public int getOdt_tpychk() {
                return odt_tpychk;
            }

            public void setOdt_tpychk(int odt_tpychk) {
                this.odt_tpychk = odt_tpychk;
            }

            public int getOd_tpychk() {
                return od_tpychk;
            }

            public void setOd_tpychk(int od_tpychk) {
                this.od_tpychk = od_tpychk;
            }

            public int getOd_s() {
                return od_s;
            }

            public void setOd_s(int od_s) {
                this.od_s = od_s;
            }

            public int getOd_y() {
                return od_y;
            }

            public void setOd_y(int od_y) {
                this.od_y = od_y;
            }

            public int getOd_yt() {
                return od_yt;
            }

            public void setOd_yt(int od_yt) {
                this.od_yt = od_yt;
            }

            public int getOd_cw() {
                return od_cw;
            }

            public void setOd_cw(int od_cw) {
                this.od_cw = od_cw;
            }

            public int getOd_is() {
                return od_is;
            }

            public void setOd_is(int od_is) {
                this.od_is = od_is;
            }

            public int getOd_epm() {
                return od_epm;
            }

            public void setOd_epm(int od_epm) {
                this.od_epm = od_epm;
            }

            public int getOd_it() {
                return od_it;
            }

            public void setOd_it(int od_it) {
                this.od_it = od_it;
            }
        }
    }
}
