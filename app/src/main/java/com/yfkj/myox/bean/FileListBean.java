package com.yfkj.myox.bean;

import java.util.List;

public class FileListBean {

    /**
     * code : 200
     * msg : success
     * result : [{"id":"20200917093305943792","typeName":"牛牛","parentId":"0","typeLevel":null,"sort":1,"path":"D:/upload/牛牛"}]
     */

    private int code;
    private String msg;
    private List<ResultBean> result;

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

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * id : 20200917093305943792
         * typeName : 牛牛
         * parentId : 0
         * typeLevel : null
         * sort : 1
         * path : D:/upload/牛牛
         */

        private String id;
        private String typeName;
        private String parentId;
        private Object typeLevel;
        private int sort;
        private String path;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTypeName() {
            return typeName;
        }

        public void setTypeName(String typeName) {
            this.typeName = typeName;
        }

        public String getParentId() {
            return parentId;
        }

        public void setParentId(String parentId) {
            this.parentId = parentId;
        }

        public Object getTypeLevel() {
            return typeLevel;
        }

        public void setTypeLevel(Object typeLevel) {
            this.typeLevel = typeLevel;
        }

        public int getSort() {
            return sort;
        }

        public void setSort(int sort) {
            this.sort = sort;
        }

        public String getPath() {
            return path;
        }

        public void setPath(String path) {
            this.path = path;
        }
    }
}
