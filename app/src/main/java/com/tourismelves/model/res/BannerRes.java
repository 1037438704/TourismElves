package com.tourismelves.model.res;

/**
 * 首页banner
 */

public class BannerRes {

    /**
     * posterId : 7
     * spaceId : 1
     * name : 1
     * type : 1
     * path : M00/00/11/052iAlsCtIaAUGsaAAE1BoVlTec645.jpg
     * url :
     * beginTime : 2018-05-01 00:00:00
     * endTime : 2019-06-01 00:00:00
     * target : _blank
     * status : true
     * insertTime : 2018-05-21 07:59:51
     * addUserId : 1
     */

    private int posterId;
    private int spaceId;
    private String name;
    private String type;
    private String path;
    private String url;
    private String beginTime;
    private String endTime;
    private String target;
    private boolean status;
    private String insertTime;
    private int addUserId;

    public int getPosterId() {
        return posterId;
    }

    public void setPosterId(int posterId) {
        this.posterId = posterId;
    }

    public int getSpaceId() {
        return spaceId;
    }

    public void setSpaceId(int spaceId) {
        this.spaceId = spaceId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(String beginTime) {
        this.beginTime = beginTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getInsertTime() {
        return insertTime;
    }

    public void setInsertTime(String insertTime) {
        this.insertTime = insertTime;
    }

    public int getAddUserId() {
        return addUserId;
    }

    public void setAddUserId(int addUserId) {
        this.addUserId = addUserId;
    }
}
