package com.tourismelves.model.res;

/**
 * 搜索
 */

public class SearchRes {

    /**
     * areaId : 5002
     * parent : 1
     * name : 无锡
     * remark :
     * parentArea : {"areaId":1,"parent":0,"code":"2","name":"中国","postalCode":"中国","remark":"Zhong Guo","orders":1,"isDisplay":true}
     */

    private int areaId;
    private int parent;
    private String name;
    private String remark;
    private ParentAreaBean parentArea;

    public int getAreaId() {
        return areaId;
    }

    public void setAreaId(int areaId) {
        this.areaId = areaId;
    }

    public int getParent() {
        return parent;
    }

    public void setParent(int parent) {
        this.parent = parent;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public ParentAreaBean getParentArea() {
        return parentArea;
    }

    public void setParentArea(ParentAreaBean parentArea) {
        this.parentArea = parentArea;
    }

    public static class ParentAreaBean {
        /**
         * areaId : 1
         * parent : 0
         * code : 2
         * name : 中国
         * postalCode : 中国
         * remark : Zhong Guo
         * orders : 1
         * isDisplay : true
         */

        private int areaId;
        private int parent;
        private String code;
        private String name;
        private String postalCode;
        private String remark;
        private int orders;
        private boolean isDisplay;

        public int getAreaId() {
            return areaId;
        }

        public void setAreaId(int areaId) {
            this.areaId = areaId;
        }

        public int getParent() {
            return parent;
        }

        public void setParent(int parent) {
            this.parent = parent;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPostalCode() {
            return postalCode;
        }

        public void setPostalCode(String postalCode) {
            this.postalCode = postalCode;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public int getOrders() {
            return orders;
        }

        public void setOrders(int orders) {
            this.orders = orders;
        }

        public boolean isIsDisplay() {
            return isDisplay;
        }

        public void setIsDisplay(boolean isDisplay) {
            this.isDisplay = isDisplay;
        }
    }
}
