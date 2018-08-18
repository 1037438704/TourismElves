package com.tourismelves.model.bean;

import java.util.List;

/**
 * Created by fanhui on 2018/8/13.
 */

public class RechargeBean {

    /**
     * type : json
     * code : 200
     * dataList : [{"id":1,"name":"50金币","gold":50,"give":10,"money":50,"status":1},{"id":2,"name":"100金币","gold":100,"give":50,"money":100,"status":1},{"id":3,"name":"200金币","gold":200,"give":150,"money":200,"status":1},{"id":4,"name":"300金币","gold":300,"give":250,"money":300,"status":1},{"id":5,"name":"500金币","gold":500,"give":500,"money":500,"status":1}]
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
         * id : 1
         * name : 50金币
         * gold : 50
         * give : 10
         * money : 50.0
         * status : 1
         */

        private int id;
        private String name;
        private int gold;
        private int give;
        private double money;
        private int status;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getGold() {
            return gold;
        }

        public void setGold(int gold) {
            this.gold = gold;
        }

        public int getGive() {
            return give;
        }

        public void setGive(int give) {
            this.give = give;
        }

        public double getMoney() {
            return money;
        }

        public void setMoney(double money) {
            this.money = money;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }
    }
}
