package com.yfkj.myox.bean;

public class AddBean {

    /**
     * code : 200
     * msg : success
     * result : {"id":"20200917094541227529","parentIds":null,"path":"D:/upload/牛四"}
     */

    private int code;
    private String msg;
    private ResultBean result;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * id : 20200917094541227529
         * parentIds : null
         * path : D:/upload/牛四
         */

        private String id;
        private Object parentIds;
        private String path;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public Object getParentIds() {
            return parentIds;
        }

        public void setParentIds(Object parentIds) {
            this.parentIds = parentIds;
        }

        public String getPath() {
            return path;
        }

        public void setPath(String path) {
            this.path = path;
        }
    }
}
