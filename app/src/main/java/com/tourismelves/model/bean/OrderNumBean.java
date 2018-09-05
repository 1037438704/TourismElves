package com.tourismelves.model.bean;

import java.util.List;

/**
 * Created by fanhui on 2018/8/29.
 */

public class OrderNumBean {

    /**
     * type : json
     * code : 200
     * dataList : [{"type":0,"userId":37,"cartNum":1,"unPayOrderNum":0,"couponNum":0,"favoriteNum":3,"payedOrderNum":1}]
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
         * type : 0
         * userId : 37
         * cartNum : 1
         * unPayOrderNum : 0
         * couponNum : 0
         * favoriteNum : 3
         * payedOrderNum : 1
         */

        private int type;
        private int userId;
        private int cartNum;
        private int unPayOrderNum;
        private int couponNum;
        private int favoriteNum;
        private int payedOrderNum;

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public int getCartNum() {
            return cartNum;
        }

        public void setCartNum(int cartNum) {
            this.cartNum = cartNum;
        }

        public int getUnPayOrderNum() {
            return unPayOrderNum;
        }

        public void setUnPayOrderNum(int unPayOrderNum) {
            this.unPayOrderNum = unPayOrderNum;
        }

        public int getCouponNum() {
            return couponNum;
        }

        public void setCouponNum(int couponNum) {
            this.couponNum = couponNum;
        }

        public int getFavoriteNum() {
            return favoriteNum;
        }

        public void setFavoriteNum(int favoriteNum) {
            this.favoriteNum = favoriteNum;
        }

        public int getPayedOrderNum() {
            return payedOrderNum;
        }

        public void setPayedOrderNum(int payedOrderNum) {
            this.payedOrderNum = payedOrderNum;
        }
    }
}
