package com.tourismelves.model.bean;

import java.util.List;

/**
 * Created by fanhui on 2018/8/20.
 */

public class CouponBean {

    /**
     * type : json
     * code : 200
     * dataList : [{"couponId":6,"userId":14,"type":1,"createDate":"2018-07-22 00:00:37","expiryTime":"2018-08-22 00:00:37","status":0}]
     */

    private String type;
    private int code;
    private List<DataListBean> dataList;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public List<DataListBean> getDataList() {
        return dataList;
    }

    public void setDataList(List<DataListBean> dataList) {
        this.dataList = dataList;
    }

    public static class DataListBean {
        /**
         * couponId : 6
         * userId : 14
         * type : 1
         * createDate : 2018-07-22 00:00:37
         * expiryTime : 2018-08-22 00:00:37
         * status : 0
         */

        private int couponId;
        private int userId;
        private int type;
        private String createDate;
        private String expiryTime;
        private int status;

        public int getCouponId() {
            return couponId;
        }

        public void setCouponId(int couponId) {
            this.couponId = couponId;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getCreateDate() {
            return createDate;
        }

        public void setCreateDate(String createDate) {
            this.createDate = createDate;
        }

        public String getExpiryTime() {
            return expiryTime;
        }

        public void setExpiryTime(String expiryTime) {
            this.expiryTime = expiryTime;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }
    }
}
