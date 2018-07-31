package com.xuliucar.me.model;

public class CompanyInfo {
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
        private String compname;
        private String user;
        private String userp;
        private String compoptype;
        private String cnum;
        private String complicnum;
        private String comptype;
        private String compestdate;
        private String address;
        private String owner;
        private String regcapital;
        private String tel;
        private String raddress;
        private String tpstr;
        private String tpedate;
        private String mb;
        private String p1;
        private String p1_thumb;
        private String p2;
        private String p2_thumb;
        private String p3;
        private String p3_thumb;
        private String p4;
        private String p4_thumb;
        private String p5;
        private String p5_thumb;
        private String strnum;
        private String stretime;

        public String getCompname() {
            return compname;
        }

        public void setCompname(String compname) {
            this.compname = compname;
        }

        public String getUser() {
            return user;
        }

        public void setUser(String user) {
            this.user = user;
        }

        public String getUserp() {
            return userp;
        }

        public void setUserp(String userp) {
            this.userp = userp;
        }

        public String getCompoptype() {
            return compoptype;
        }

        public void setCompoptype(String compoptype) {
            this.compoptype = compoptype;
        }

        public String getCnum() {
            return cnum;
        }

        public void setCnum(String cnum) {
            this.cnum = cnum;
        }

        public String getComplicnum() {
            return complicnum;
        }

        public void setComplicnum(String complicnum) {
            this.complicnum = complicnum;
        }

        public String getComptype() {
            return comptype;
        }

        public void setComptype(String comptype) {
            this.comptype = comptype;
        }

        public String getCompestdate() {
            return compestdate;
        }

        public void setCompestdate(String compestdate) {
            this.compestdate = compestdate;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getOwner() {
            return owner;
        }

        public void setOwner(String owner) {
            this.owner = owner;
        }

        public String getRegcapital() {
            return regcapital;
        }

        public void setRegcapital(String regcapital) {
            this.regcapital = regcapital;
        }

        public String getTel() {
            return tel;
        }

        public void setTel(String tel) {
            this.tel = tel;
        }

        public String getRaddress() {
            return raddress;
        }

        public void setRaddress(String raddress) {
            this.raddress = raddress;
        }

        public String getTpstr() {
            return tpstr;
        }

        public void setTpstr(String tpstr) {
            this.tpstr = tpstr;
        }

        public String getTpedate() {
            return tpedate;
        }

        public void setTpedate(String tpedate) {
            this.tpedate = tpedate;
        }

        public String getMb() {
            return mb;
        }

        public void setMb(String mb) {
            this.mb = mb;
        }

        public String getP1() {
            return p1;
        }

        public void setP1(String p1) {
            this.p1 = p1;
        }

        public String getP1_thumb() {
            return p1_thumb;
        }

        public void setP1_thumb(String p1_thumb) {
            this.p1_thumb = p1_thumb;
        }

        public String getP2() {
            return p2;
        }

        public void setP2(String p2) {
            this.p2 = p2;
        }

        public String getP2_thumb() {
            return p2_thumb;
        }

        public void setP2_thumb(String p2_thumb) {
            this.p2_thumb = p2_thumb;
        }

        public String getP3() {
            return p3;
        }

        public void setP3(String p3) {
            this.p3 = p3;
        }

        public String getP3_thumb() {
            return p3_thumb;
        }

        public void setP3_thumb(String p3_thumb) {
            this.p3_thumb = p3_thumb;
        }

        public String getP4() {
            return p4;
        }

        public void setP4(String p4) {
            this.p4 = p4;
        }

        public String getP4_thumb() {
            return p4_thumb;
        }

        public void setP4_thumb(String p4_thumb) {
            this.p4_thumb = p4_thumb;
        }

        public String getP5() {
            return p5;
        }

        public void setP5(String p5) {
            this.p5 = p5;
        }

        public String getP5_thumb() {
            return p5_thumb;
        }

        public void setP5_thumb(String p5_thumb) {
            this.p5_thumb = p5_thumb;
        }

        public String getStrnum() {
            return strnum;
        }

        public void setStrnum(String strnum) {
            this.strnum = strnum;
        }

        public String getStretime() {
            return stretime;
        }

        public void setStretime(String stretime) {
            this.stretime = stretime;
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "compname='" + compname + '\'' +
                    ", user='" + user + '\'' +
                    ", userp='" + userp + '\'' +
                    ", compoptype='" + compoptype + '\'' +
                    ", cnum='" + cnum + '\'' +
                    ", complicnum='" + complicnum + '\'' +
                    ", comptype='" + comptype + '\'' +
                    ", compestdate='" + compestdate + '\'' +
                    ", address='" + address + '\'' +
                    ", owner='" + owner + '\'' +
                    ", regcapital='" + regcapital + '\'' +
                    ", tel='" + tel + '\'' +
                    ", raddress='" + raddress + '\'' +
                    ", tpstr='" + tpstr + '\'' +
                    ", tpedate='" + tpedate + '\'' +
                    ", mb='" + mb + '\'' +
                    ", p1='" + p1 + '\'' +
                    ", p1_thumb='" + p1_thumb + '\'' +
                    ", p2='" + p2 + '\'' +
                    ", p2_thumb='" + p2_thumb + '\'' +
                    ", p3='" + p3 + '\'' +
                    ", p3_thumb='" + p3_thumb + '\'' +
                    ", p4='" + p4 + '\'' +
                    ", p4_thumb='" + p4_thumb + '\'' +
                    ", p5='" + p5 + '\'' +
                    ", p5_thumb='" + p5_thumb + '\'' +
                    ", strnum='" + strnum + '\'' +
                    ", stretime='" + stretime + '\'' +
                    '}';
        }
    }
}
