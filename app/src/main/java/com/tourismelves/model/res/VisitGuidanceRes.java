package com.tourismelves.model.res;

import java.util.List;

/**
 * 游玩指引
 */

public class VisitGuidanceRes {

    /**
     * scenicSpotInfoId : 1
     * scenicSpotId : 149
     * langagesId : 0
     * price : 50元
     * openingHours : 9:00-18:00
     * favouredPolicy : 小孩免费
     * season : 春季、秋季
     * playtime : 12:00
     * address : 南京
     * traffic : 公交
     * tips : 小心台阶
     * introduction : 景区简介
     * insertTime : 2018-08-07 22:44:37
     * updateTime : 2018-08-07 22:46:10
     * insertIp : 127.0.0.1
     * updateIp : 127.0.0.1
     * orders : 1
     * status : 1
     * creator : 1
     * editor : 1
     * imagelist : [{"scenicSpotImageId":1,"scenicSpotId":149,"scenicSpotInfoId":1,"type":1,"name":"Koala","imagePath":"M00/00/18/052iAltp2NWAOB5rAAvqH_kipG8307.jpg","imageAlt":"1","imageUrl":"2","imageText":"3","info":"4","imageSize":780831,"width":1024,"height":768,"suffix":"jpg","insertTime":"2018-08-08 01:37:28","updateTime":"2018-08-08 01:40:31","insertIp":"127.0.0.1","updateIp":"127.0.0.1","orders":5}]
     */

    private int scenicSpotInfoId;
    private int scenicSpotId;
    private int langagesId;
    private String price;
    private String openingHours;
    private String favouredPolicy;
    private String season;
    private String playtime;
    private String address;
    private String traffic;
    private String tips;
    private String introduction;
    private String insertTime;
    private String updateTime;
    private String insertIp;
    private String updateIp;
    private int orders;
    private int status;
    private int creator;
    private int editor;
    private List<ImagelistBean> imagelist;

    public int getScenicSpotInfoId() {
        return scenicSpotInfoId;
    }

    public void setScenicSpotInfoId(int scenicSpotInfoId) {
        this.scenicSpotInfoId = scenicSpotInfoId;
    }

    public int getScenicSpotId() {
        return scenicSpotId;
    }

    public void setScenicSpotId(int scenicSpotId) {
        this.scenicSpotId = scenicSpotId;
    }

    public int getLangagesId() {
        return langagesId;
    }

    public void setLangagesId(int langagesId) {
        this.langagesId = langagesId;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getOpeningHours() {
        return openingHours;
    }

    public void setOpeningHours(String openingHours) {
        this.openingHours = openingHours;
    }

    public String getFavouredPolicy() {
        return favouredPolicy;
    }

    public void setFavouredPolicy(String favouredPolicy) {
        this.favouredPolicy = favouredPolicy;
    }

    public String getSeason() {
        return season;
    }

    public void setSeason(String season) {
        this.season = season;
    }

    public String getPlaytime() {
        return playtime;
    }

    public void setPlaytime(String playtime) {
        this.playtime = playtime;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTraffic() {
        return traffic;
    }

    public void setTraffic(String traffic) {
        this.traffic = traffic;
    }

    public String getTips() {
        return tips;
    }

    public void setTips(String tips) {
        this.tips = tips;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getInsertTime() {
        return insertTime;
    }

    public void setInsertTime(String insertTime) {
        this.insertTime = insertTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getInsertIp() {
        return insertIp;
    }

    public void setInsertIp(String insertIp) {
        this.insertIp = insertIp;
    }

    public String getUpdateIp() {
        return updateIp;
    }

    public void setUpdateIp(String updateIp) {
        this.updateIp = updateIp;
    }

    public int getOrders() {
        return orders;
    }

    public void setOrders(int orders) {
        this.orders = orders;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getCreator() {
        return creator;
    }

    public void setCreator(int creator) {
        this.creator = creator;
    }

    public int getEditor() {
        return editor;
    }

    public void setEditor(int editor) {
        this.editor = editor;
    }

    public List<ImagelistBean> getImagelist() {
        return imagelist;
    }

    public void setImagelist(List<ImagelistBean> imagelist) {
        this.imagelist = imagelist;
    }

    public static class ImagelistBean {
        /**
         * scenicSpotImageId : 1
         * scenicSpotId : 149
         * scenicSpotInfoId : 1
         * type : 1
         * name : Koala
         * imagePath : M00/00/18/052iAltp2NWAOB5rAAvqH_kipG8307.jpg
         * imageAlt : 1
         * imageUrl : 2
         * imageText : 3
         * info : 4
         * imageSize : 780831
         * width : 1024
         * height : 768
         * suffix : jpg
         * insertTime : 2018-08-08 01:37:28
         * updateTime : 2018-08-08 01:40:31
         * insertIp : 127.0.0.1
         * updateIp : 127.0.0.1
         * orders : 5
         */

        private int scenicSpotImageId;
        private int scenicSpotId;
        private int scenicSpotInfoId;
        private int type;
        private String name;
        private String imagePath;
        private String imageAlt;
        private String imageUrl;
        private String imageText;
        private String info;
        private int imageSize;
        private int width;
        private int height;
        private String suffix;
        private String insertTime;
        private String updateTime;
        private String insertIp;
        private String updateIp;
        private int orders;

        public int getScenicSpotImageId() {
            return scenicSpotImageId;
        }

        public void setScenicSpotImageId(int scenicSpotImageId) {
            this.scenicSpotImageId = scenicSpotImageId;
        }

        public int getScenicSpotId() {
            return scenicSpotId;
        }

        public void setScenicSpotId(int scenicSpotId) {
            this.scenicSpotId = scenicSpotId;
        }

        public int getScenicSpotInfoId() {
            return scenicSpotInfoId;
        }

        public void setScenicSpotInfoId(int scenicSpotInfoId) {
            this.scenicSpotInfoId = scenicSpotInfoId;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getImagePath() {
            return imagePath;
        }

        public void setImagePath(String imagePath) {
            this.imagePath = imagePath;
        }

        public String getImageAlt() {
            return imageAlt;
        }

        public void setImageAlt(String imageAlt) {
            this.imageAlt = imageAlt;
        }

        public String getImageUrl() {
            return imageUrl;
        }

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }

        public String getImageText() {
            return imageText;
        }

        public void setImageText(String imageText) {
            this.imageText = imageText;
        }

        public String getInfo() {
            return info;
        }

        public void setInfo(String info) {
            this.info = info;
        }

        public int getImageSize() {
            return imageSize;
        }

        public void setImageSize(int imageSize) {
            this.imageSize = imageSize;
        }

        public int getWidth() {
            return width;
        }

        public void setWidth(int width) {
            this.width = width;
        }

        public int getHeight() {
            return height;
        }

        public void setHeight(int height) {
            this.height = height;
        }

        public String getSuffix() {
            return suffix;
        }

        public void setSuffix(String suffix) {
            this.suffix = suffix;
        }

        public String getInsertTime() {
            return insertTime;
        }

        public void setInsertTime(String insertTime) {
            this.insertTime = insertTime;
        }

        public String getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(String updateTime) {
            this.updateTime = updateTime;
        }

        public String getInsertIp() {
            return insertIp;
        }

        public void setInsertIp(String insertIp) {
            this.insertIp = insertIp;
        }

        public String getUpdateIp() {
            return updateIp;
        }

        public void setUpdateIp(String updateIp) {
            this.updateIp = updateIp;
        }

        public int getOrders() {
            return orders;
        }

        public void setOrders(int orders) {
            this.orders = orders;
        }
    }
}
