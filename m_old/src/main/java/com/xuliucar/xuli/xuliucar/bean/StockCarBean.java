package com.xuliucar.xuli.xuliucar.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by skyward on 2016/7/4.
 *
 */
public class StockCarBean {

    /**
     * count : 2
     * info : [{"id":"332","fnum":"FADF3434234598798","modelname":"GTM7160LVCE","enum":"748593dd989","plates":"粤A44434"},{"id":"421","fnum":"HADFG675463332","modelname":"","enum":"Y5634657","plates":"粤A"}]
     */

    private DataBean data;
    /**
     * data : {"count":2,"info":[{"id":"332","fnum":"FADF3434234598798","modelname":"GTM7160LVCE","enum":"748593dd989","plates":"粤A44434"},{"id":"421","fnum":"HADFG675463332","modelname":"","enum":"Y5634657","plates":"粤A"}]}
     * error :
     * success : true
     * message :
     */

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
        /**
         * id : 332
         * fnum : FADF3434234598798
         * modelname : GTM7160LVCE
         * enum : 748593dd989
         * plates : 粤A44434
         */

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
            private String id;
            private String fnum;
            private String modelname;
            @SerializedName("enum")
            private String enumX;
            private String plates;

            public InfoBean(String fnum, String enumX) {
                this.fnum = fnum;
                this.enumX = enumX;
            }

            public InfoBean(String fnum, String enumX, String plates) {
                this.fnum = fnum;
                this.enumX = enumX;
                this.plates = plates;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getFnum() {
                return fnum;
            }

            public void setFnum(String fnum) {
                this.fnum = fnum;
            }

            public String getModelname() {
                return modelname;
            }

            public void setModelname(String modelname) {
                this.modelname = modelname;
            }

            public String getEnumX() {
                return enumX;
            }

            public void setEnumX(String enumX) {
                this.enumX = enumX;
            }

            public String getPlates() {
                return plates;
            }

            public void setPlates(String plates) {
                this.plates = plates;
            }

            @Override
            public String toString() {
                return "{" +
                        "id='" + id + '\'' +
                        ", fnum='" + fnum + '\'' +
                        ", modelname='" + modelname + '\'' +
                        ", enumX='" + enumX + '\'' +
                        ", plates='" + plates + '\'' +
                        '}';
            }
        }

    }
}
