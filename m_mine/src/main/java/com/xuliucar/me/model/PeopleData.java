package com.xuliucar.me.model;

import java.util.List;

public class PeopleData {

    /**
     * success : true
     * error : null
     * data : [{"id":"132","name":"测试管理5","phone":"","smstarid":"u_132"},{"id":"131","name":"夏文亮","phone":"18818856403","smstarid":"u_131"},{"id":"128","name":"utest5","phone":"18520129588","smstarid":"u_128"},{"id":"127","name":"utest3","phone":"18520129566","smstarid":"u_127"},{"id":"126","name":"utest2","phone":"18520129512","smstarid":"u_126"},{"id":"125","name":"utest1","phone":"18520129511","smstarid":"u_125"},{"id":"121","name":"测试管理4","phone":"","smstarid":"u_121"},{"id":"120","name":"测试管理","phone":"","smstarid":"u_120"},{"id":"118","name":"测试1","phone":"","smstarid":"u_118"},{"id":"73","name":"周八","phone":"","smstarid":"u_73"},{"id":"72","name":"孙七","phone":"","smstarid":"u_72"},{"id":"71","name":"赵六","phone":"15011829374","smstarid":"u_71"},{"id":"70","name":"王五","phone":"","smstarid":"u_70"},{"id":"69","name":"李四","phone":"15322380032","smstarid":"u_69"},{"id":"66","name":"测试用户2","phone":"","smstarid":"u_66"},{"id":"45","name":"测试销售","phone":"","smstarid":"u_45"},{"id":"2","name":"测试用户1","phone":"15987653647","smstarid":"u_2"}]
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
         * id : 132
         * name : 测试管理5
         * phone :
         * smstarid : u_132
         */

        private String index;
        private String id;
        private String name;
        private String phone;
        private String smstarid;
        private String drivername;
        private String ownername;
        private String phone2;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getSmstarid() {
            return smstarid;
        }

        public void setSmstarid(String smstarid) {
            this.smstarid = smstarid;
        }

        public String getIndex() {
            return index;
        }

        public void setIndex(String index) {
            this.index = index;
        }

        public String getDrivername() {
            return drivername;
        }

        public void setDrivername(String drivername) {
            this.drivername = drivername;
        }

        public String getPhone2() {
            return phone2;
        }

        public void setPhone2(String phone2) {
            this.phone2 = phone2;
        }

        public String getOwnername() {
            return ownername;
        }

        public void setOwnername(String ownername) {
            this.ownername = ownername;
        }
    }
}
