package com.xuliucar.xuli.xuliucar.bean;

import com.google.gson.annotations.SerializedName;

/**
 * Created by skyward on 2016/11/7.
 *
 */

public class CarInfoBean {


    private DataBean data;

    private Object error;
    private boolean success;
    private String message;


    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public Object getError() {
        return error;
    }

    public void setError(Object error) {
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
        private String uid;
        private CinfoBean cinfo;

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }

        public CinfoBean getCinfo() {
            return cinfo;
        }

        public void setCinfo(CinfoBean cinfo) {
            this.cinfo = cinfo;
        }

        public static class CinfoBean {
            private String p7;
            private String p6;
            private String p5;
            private String tretime;
            private String p4;
            private String p3;
            private String p2;
            private String p8_thumb;
            private String p1;
            private String isown;
            private String p11_thumb;
            private String p7_thumb;
            private String utype;
            private String p9;
            private String p8;
            private String cwcode;
            private String fnum;
            private String p6_thumb;
            private String filenum;
            private String cwetime;
            private String owidth;
            private String p3_thumb;
            private String oheight;
            private String p9_thumb;
            private String trstime;
            private String ychketime;
            private String cwlevel;
            private String empchkstime;
            private String loadp;
            private String empchketime;
            private String p10;
            private String olength;
            @SerializedName("enum")
            private String enumX;
            private String p1_thumb;
            private String yregtime;
            private String p4_thumb;
            private String carprice;
            private String platetype;
            private String p11;
            private String cwstime;
            private String p2_thumb;
            private String ccolor;
            private String p10_thumb;
            private String p5_thumb;

            public String getP7() {
                return p7;
            }

            public void setP7(String p7) {
                this.p7 = p7;
            }

            public String getP6() {
                return p6;
            }

            public void setP6(String p6) {
                this.p6 = p6;
            }

            public String getP5() {
                return p5;
            }

            public void setP5(String p5) {
                this.p5 = p5;
            }

            public String getTretime() {
                return tretime;
            }

            public void setTretime(String tretime) {
                this.tretime = tretime;
            }

            public String getP4() {
                return p4;
            }

            public void setP4(String p4) {
                this.p4 = p4;
            }

            public String getP3() {
                return p3;
            }

            public void setP3(String p3) {
                this.p3 = p3;
            }

            public String getP2() {
                return p2;
            }

            public void setP2(String p2) {
                this.p2 = p2;
            }

            public String getP8_thumb() {
                return p8_thumb;
            }

            public void setP8_thumb(String p8_thumb) {
                this.p8_thumb = p8_thumb;
            }

            public String getP1() {
                return p1;
            }

            public void setP1(String p1) {
                this.p1 = p1;
            }

            public String getIsown() {
                return isown;
            }

            public void setIsown(String isown) {
                this.isown = isown;
            }

            public String getP11_thumb() {
                return p11_thumb;
            }

            public void setP11_thumb(String p11_thumb) {
                this.p11_thumb = p11_thumb;
            }

            public String getP7_thumb() {
                return p7_thumb;
            }

            public void setP7_thumb(String p7_thumb) {
                this.p7_thumb = p7_thumb;
            }

            public String getUtype() {
                return utype;
            }

            public void setUtype(String utype) {
                this.utype = utype;
            }

            public String getP9() {
                return p9;
            }

            public void setP9(String p9) {
                this.p9 = p9;
            }

            public String getP8() {
                return p8;
            }

            public void setP8(String p8) {
                this.p8 = p8;
            }

            public String getCwcode() {
                return cwcode;
            }

            public void setCwcode(String cwcode) {
                this.cwcode = cwcode;
            }

            public String getFnum() {
                return fnum;
            }

            public void setFnum(String fnum) {
                this.fnum = fnum;
            }

            public String getP6_thumb() {
                return p6_thumb;
            }

            public void setP6_thumb(String p6_thumb) {
                this.p6_thumb = p6_thumb;
            }

            public String getFilenum() {
                return filenum;
            }

            public void setFilenum(String filenum) {
                this.filenum = filenum;
            }

            public String getCwetime() {
                return cwetime;
            }

            public void setCwetime(String cwetime) {
                this.cwetime = cwetime;
            }

            public String getOwidth() {
                return owidth;
            }

            public void setOwidth(String owidth) {
                this.owidth = owidth;
            }

            public String getP3_thumb() {
                return p3_thumb;
            }

            public void setP3_thumb(String p3_thumb) {
                this.p3_thumb = p3_thumb;
            }

            public String getOheight() {
                return oheight;
            }

            public void setOheight(String oheight) {
                this.oheight = oheight;
            }

            public String getP9_thumb() {
                return p9_thumb;
            }

            public void setP9_thumb(String p9_thumb) {
                this.p9_thumb = p9_thumb;
            }

            public String getTrstime() {
                return trstime;
            }

            public void setTrstime(String trstime) {
                this.trstime = trstime;
            }

            public String getYchketime() {
                return ychketime;
            }

            public void setYchketime(String ychketime) {
                this.ychketime = ychketime;
            }

            public String getCwlevel() {
                return cwlevel;
            }

            public void setCwlevel(String cwlevel) {
                this.cwlevel = cwlevel;
            }

            public String getEmpchkstime() {
                return empchkstime;
            }

            public void setEmpchkstime(String empchkstime) {
                this.empchkstime = empchkstime;
            }

            public String getLoadp() {
                return loadp;
            }

            public void setLoadp(String loadp) {
                this.loadp = loadp;
            }

            public String getEmpchketime() {
                return empchketime;
            }

            public void setEmpchketime(String empchketime) {
                this.empchketime = empchketime;
            }

            public String getP10() {
                return p10;
            }

            public void setP10(String p10) {
                this.p10 = p10;
            }

            public String getOlength() {
                return olength;
            }

            public void setOlength(String olength) {
                this.olength = olength;
            }

            public String getEnumX() {
                return enumX;
            }

            public void setEnumX(String enumX) {
                this.enumX = enumX;
            }

            public String getP1_thumb() {
                return p1_thumb;
            }

            public void setP1_thumb(String p1_thumb) {
                this.p1_thumb = p1_thumb;
            }

            public String getYregtime() {
                return yregtime;
            }

            public void setYregtime(String yregtime) {
                this.yregtime = yregtime;
            }

            public String getP4_thumb() {
                return p4_thumb;
            }

            public void setP4_thumb(String p4_thumb) {
                this.p4_thumb = p4_thumb;
            }

            public String getCarprice() {
                return carprice;
            }

            public void setCarprice(String carprice) {
                this.carprice = carprice;
            }

            public String getPlatetype() {
                return platetype;
            }

            public void setPlatetype(String platetype) {
                this.platetype = platetype;
            }

            public String getP11() {
                return p11;
            }

            public void setP11(String p11) {
                this.p11 = p11;
            }

            public String getCwstime() {
                return cwstime;
            }

            public void setCwstime(String cwstime) {
                this.cwstime = cwstime;
            }

            public String getP2_thumb() {
                return p2_thumb;
            }

            public void setP2_thumb(String p2_thumb) {
                this.p2_thumb = p2_thumb;
            }

            public String getCcolor() {
                return ccolor;
            }

            public void setCcolor(String ccolor) {
                this.ccolor = ccolor;
            }

            public String getP10_thumb() {
                return p10_thumb;
            }

            public void setP10_thumb(String p10_thumb) {
                this.p10_thumb = p10_thumb;
            }

            public String getP5_thumb() {
                return p5_thumb;
            }

            public void setP5_thumb(String p5_thumb) {
                this.p5_thumb = p5_thumb;
            }

        }
    }
}
