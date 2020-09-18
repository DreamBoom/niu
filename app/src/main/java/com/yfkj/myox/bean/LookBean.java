package com.yfkj.myox.bean;

import java.util.List;

public class LookBean {


    /**
     * code : 200
     * msg : success
     * result : {"totalCount":9,"totalPage":1,"list":[{"id":126,"pictureId":"20200918113922369345","virtualUrl":"20200918113843920607","absoluteUrl":"hnyfkj-file-server/plugins//牛一/牛一4422_0.jpg","pictureName":"牛一4422_0","uploadDate":"2020-09-18 11:39:22","remark1":"png","remark2":null,"remark3":null},{"id":127,"pictureId":"20200918113922765358","virtualUrl":"20200918113843920607","absoluteUrl":"hnyfkj-file-server/plugins//牛一/牛一3285_1.jpg","pictureName":"牛一3285_1","uploadDate":"2020-09-18 11:39:22","remark1":"png","remark2":null,"remark3":null},{"id":128,"pictureId":"20200918113922949441","virtualUrl":"20200918113843920607","absoluteUrl":"hnyfkj-file-server/plugins//牛一/牛一968_2.jpg","pictureName":"牛一968_2","uploadDate":"2020-09-18 11:39:22","remark1":"png","remark2":null,"remark3":null},{"id":129,"pictureId":"20200918113922594336","virtualUrl":"20200918113843920607","absoluteUrl":"hnyfkj-file-server/plugins//牛一/牛一2664_3.jpg","pictureName":"牛一2664_3","uploadDate":"2020-09-18 11:39:22","remark1":"png","remark2":null,"remark3":null},{"id":130,"pictureId":"20200918113922103181","virtualUrl":"20200918113843920607","absoluteUrl":"hnyfkj-file-server/plugins//牛一/牛一3795_4.jpg","pictureName":"牛一3795_4","uploadDate":"2020-09-18 11:39:22","remark1":"png","remark2":null,"remark3":null},{"id":131,"pictureId":"20200918113956671208","virtualUrl":"20200918113843920607","absoluteUrl":"hnyfkj-file-server/plugins//牛一/牛一1722_0.jpg","pictureName":"牛一1722_0","uploadDate":"2020-09-18 11:39:56","remark1":"png","remark2":null,"remark3":null},{"id":132,"pictureId":"20200918113956355804","virtualUrl":"20200918113843920607","absoluteUrl":"hnyfkj-file-server/plugins//牛一/牛一7419_1.jpg","pictureName":"牛一7419_1","uploadDate":"2020-09-18 11:39:56","remark1":"png","remark2":null,"remark3":null},{"id":133,"pictureId":"20200918113956983581","virtualUrl":"20200918113843920607","absoluteUrl":"hnyfkj-file-server/plugins//牛一/牛一7048_2.jpg","pictureName":"牛一7048_2","uploadDate":"2020-09-18 11:39:56","remark1":"png","remark2":null,"remark3":null},{"id":134,"pictureId":"20200918113957224073","virtualUrl":"20200918113843920607","absoluteUrl":"hnyfkj-file-server/plugins//牛一/牛一7421_3.jpg","pictureName":"牛一7421_3","uploadDate":"2020-09-18 11:39:57","remark1":"png","remark2":null,"remark3":null}]}
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
         * totalCount : 9
         * totalPage : 1
         * list : [{"id":126,"pictureId":"20200918113922369345","virtualUrl":"20200918113843920607","absoluteUrl":"hnyfkj-file-server/plugins//牛一/牛一4422_0.jpg","pictureName":"牛一4422_0","uploadDate":"2020-09-18 11:39:22","remark1":"png","remark2":null,"remark3":null},{"id":127,"pictureId":"20200918113922765358","virtualUrl":"20200918113843920607","absoluteUrl":"hnyfkj-file-server/plugins//牛一/牛一3285_1.jpg","pictureName":"牛一3285_1","uploadDate":"2020-09-18 11:39:22","remark1":"png","remark2":null,"remark3":null},{"id":128,"pictureId":"20200918113922949441","virtualUrl":"20200918113843920607","absoluteUrl":"hnyfkj-file-server/plugins//牛一/牛一968_2.jpg","pictureName":"牛一968_2","uploadDate":"2020-09-18 11:39:22","remark1":"png","remark2":null,"remark3":null},{"id":129,"pictureId":"20200918113922594336","virtualUrl":"20200918113843920607","absoluteUrl":"hnyfkj-file-server/plugins//牛一/牛一2664_3.jpg","pictureName":"牛一2664_3","uploadDate":"2020-09-18 11:39:22","remark1":"png","remark2":null,"remark3":null},{"id":130,"pictureId":"20200918113922103181","virtualUrl":"20200918113843920607","absoluteUrl":"hnyfkj-file-server/plugins//牛一/牛一3795_4.jpg","pictureName":"牛一3795_4","uploadDate":"2020-09-18 11:39:22","remark1":"png","remark2":null,"remark3":null},{"id":131,"pictureId":"20200918113956671208","virtualUrl":"20200918113843920607","absoluteUrl":"hnyfkj-file-server/plugins//牛一/牛一1722_0.jpg","pictureName":"牛一1722_0","uploadDate":"2020-09-18 11:39:56","remark1":"png","remark2":null,"remark3":null},{"id":132,"pictureId":"20200918113956355804","virtualUrl":"20200918113843920607","absoluteUrl":"hnyfkj-file-server/plugins//牛一/牛一7419_1.jpg","pictureName":"牛一7419_1","uploadDate":"2020-09-18 11:39:56","remark1":"png","remark2":null,"remark3":null},{"id":133,"pictureId":"20200918113956983581","virtualUrl":"20200918113843920607","absoluteUrl":"hnyfkj-file-server/plugins//牛一/牛一7048_2.jpg","pictureName":"牛一7048_2","uploadDate":"2020-09-18 11:39:56","remark1":"png","remark2":null,"remark3":null},{"id":134,"pictureId":"20200918113957224073","virtualUrl":"20200918113843920607","absoluteUrl":"hnyfkj-file-server/plugins//牛一/牛一7421_3.jpg","pictureName":"牛一7421_3","uploadDate":"2020-09-18 11:39:57","remark1":"png","remark2":null,"remark3":null}]
         */

        private int totalCount;
        private int totalPage;
        private List<ListBean> list;

        public int getTotalCount() {
            return totalCount;
        }

        public void setTotalCount(int totalCount) {
            this.totalCount = totalCount;
        }

        public int getTotalPage() {
            return totalPage;
        }

        public void setTotalPage(int totalPage) {
            this.totalPage = totalPage;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * id : 126
             * pictureId : 20200918113922369345
             * virtualUrl : 20200918113843920607
             * absoluteUrl : hnyfkj-file-server/plugins//牛一/牛一4422_0.jpg
             * pictureName : 牛一4422_0
             * uploadDate : 2020-09-18 11:39:22
             * remark1 : png
             * remark2 : null
             * remark3 : null
             */

            private int id;
            private String pictureId;
            private String virtualUrl;
            private String absoluteUrl;
            private String pictureName;
            private String uploadDate;
            private String remark1;
            private Object remark2;
            private Object remark3;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getPictureId() {
                return pictureId;
            }

            public void setPictureId(String pictureId) {
                this.pictureId = pictureId;
            }

            public String getVirtualUrl() {
                return virtualUrl;
            }

            public void setVirtualUrl(String virtualUrl) {
                this.virtualUrl = virtualUrl;
            }

            public String getAbsoluteUrl() {
                return absoluteUrl;
            }

            public void setAbsoluteUrl(String absoluteUrl) {
                this.absoluteUrl = absoluteUrl;
            }

            public String getPictureName() {
                return pictureName;
            }

            public void setPictureName(String pictureName) {
                this.pictureName = pictureName;
            }

            public String getUploadDate() {
                return uploadDate;
            }

            public void setUploadDate(String uploadDate) {
                this.uploadDate = uploadDate;
            }

            public String getRemark1() {
                return remark1;
            }

            public void setRemark1(String remark1) {
                this.remark1 = remark1;
            }

            public Object getRemark2() {
                return remark2;
            }

            public void setRemark2(Object remark2) {
                this.remark2 = remark2;
            }

            public Object getRemark3() {
                return remark3;
            }

            public void setRemark3(Object remark3) {
                this.remark3 = remark3;
            }
        }
    }
}
