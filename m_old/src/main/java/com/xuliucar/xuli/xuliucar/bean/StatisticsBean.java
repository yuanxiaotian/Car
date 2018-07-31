package com.xuliucar.xuli.xuliucar.bean;

/**
 * Created by skyward on 2016/11/24.
 * email：
 */

public class StatisticsBean {




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
        private int instleft;
        private int m_profit;
        private int ti_month;
        private int y_profit;
        private int te_year;
        private int ti_year;
        private int te_month;

        public int getInstleft() {
            return instleft;
        }

        public void setInstleft(int instleft) {
            this.instleft = instleft;
        }

        public int getM_profit() {
            return m_profit;
        }

        public void setM_profit(int m_profit) {
            this.m_profit = m_profit;
        }

        public int getTi_month() {
            return ti_month;
        }

        public void setTi_month(int ti_month) {
            this.ti_month = ti_month;
        }

        public int getY_profit() {
            return y_profit;
        }

        public void setY_profit(int y_profit) {
            this.y_profit = y_profit;
        }

        public int getTe_year() {
            return te_year;
        }

        public void setTe_year(int te_year) {
            this.te_year = te_year;
        }

        public int getTi_year() {
            return ti_year;
        }

        public void setTi_year(int ti_year) {
            this.ti_year = ti_year;
        }

        public int getTe_month() {
            return te_month;
        }

        public void setTe_month(int te_month) {
            this.te_month = te_month;
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "instleft=" + instleft +
                    ", m_profit=" + m_profit +
                    ", ti_month=" + ti_month +
                    ", y_profit=" + y_profit +
                    ", te_year=" + te_year +
                    ", ti_year=" + ti_year +
                    ", te_month=" + te_month +
                    '}';
        }
    }
}
