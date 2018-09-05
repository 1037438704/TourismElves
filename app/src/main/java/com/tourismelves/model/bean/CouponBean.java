package com.tourismelves.model.bean;

import java.util.List;

/**
 * Created by fanhui on 2018/8/20.
 */

public class CouponBean {


    /**
     * type : json
     * code : 200
     * dataList : [{"couponId":8,"userId":37,"type":1,"createDate":"2018-09-03 00:00:00","expiryTime":"2018-09-30 00:00:00","status":0,"name":"名称","conditions":"使用条件","description":"同时购买景区大于或等于两个景区时，可以抵用其中一个景区"},{"couponId":9,"userId":37,"type":1,"createDate":"2018-09-03 00:00:00","expiryTime":"2018-09-30 00:00:00","status":0,"name":"名称","conditions":"使用条件","description":"同时购买景区大于或等于两个景区时，可以抵用其中一个景区"},{"couponId":10,"userId":37,"type":1,"createDate":"2018-09-03 00:00:00","expiryTime":"2018-09-30 00:00:00","status":0,"name":"名称","conditions":"使用条件","description":"同时购买景区大于或等于两个景区时，可以抵用其中一个景区"}]
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
         * couponId : 8
         * userId : 37
         * type : 1
         * createDate : 2018-09-03 00:00:00
         * expiryTime : 2018-09-30 00:00:00
         * status : 0
         * name : 名称
         * conditions : 使用条件
         * description : 同时购买景区大于或等于两个景区时，可以抵用其中一个景区
         */

        private int couponId;
        private int userId;
        private int type;
        private String createDate;
        private String expiryTime;
        private int status;
        private String name;
        private String conditions;
        private String description;

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

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getConditions() {
            return conditions;
        }

        public void setConditions(String conditions) {
            this.conditions = conditions;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }
    }
}
