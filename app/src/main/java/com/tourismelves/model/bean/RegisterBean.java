package com.tourismelves.model.bean;

import java.util.List;

/**
 * Created by fanhui on 2018/9/5.
 */

public class RegisterBean {

    /**
     * type : json
     * code : 200
     * message : 注册成功！
     * dataList : [{"userId":453,"gold":0,"invitationGold":0,"type":0,"loginName":"17320239690","nickName":"173****9690","image":"lyjl/public/images/userImg.png","gender":"女","mobile":"17320239690","status":1,"insertTime":"2018-09-04 21:52:32","updateTime":"2018-09-04 21:52:32","insertIp":"111.164.178.33","updateIp":"111.164.178.33","channel":"android","shareCode":"","smsCode":"7901","token":"1f4b7545-22af-4f68-80ad-76d380c19b36"}]
     */

    private String type;
    private int code;
    private String message;
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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<DataListBean> getDataList() {
        return dataList;
    }

    public void setDataList(List<DataListBean> dataList) {
        this.dataList = dataList;
    }

    public static class DataListBean {
        /**
         * userId : 453
         * gold : 0
         * invitationGold : 0
         * type : 0
         * loginName : 17320239690
         * nickName : 173****9690
         * image : lyjl/public/images/userImg.png
         * gender : 女
         * mobile : 17320239690
         * status : 1
         * insertTime : 2018-09-04 21:52:32
         * updateTime : 2018-09-04 21:52:32
         * insertIp : 111.164.178.33
         * updateIp : 111.164.178.33
         * channel : android
         * shareCode :
         * smsCode : 7901
         * token : 1f4b7545-22af-4f68-80ad-76d380c19b36
         */

        private int userId;
        private int gold;
        private int invitationGold;
        private int type;
        private String loginName;
        private String nickName;
        private String image;
        private String gender;
        private String mobile;
        private int status;
        private String insertTime;
        private String updateTime;
        private String insertIp;
        private String updateIp;
        private String channel;
        private String shareCode;
        private String smsCode;
        private String token;

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public int getGold() {
            return gold;
        }

        public void setGold(int gold) {
            this.gold = gold;
        }

        public int getInvitationGold() {
            return invitationGold;
        }

        public void setInvitationGold(int invitationGold) {
            this.invitationGold = invitationGold;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getLoginName() {
            return loginName;
        }

        public void setLoginName(String loginName) {
            this.loginName = loginName;
        }

        public String getNickName() {
            return nickName;
        }

        public void setNickName(String nickName) {
            this.nickName = nickName;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
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

        public String getChannel() {
            return channel;
        }

        public void setChannel(String channel) {
            this.channel = channel;
        }

        public String getShareCode() {
            return shareCode;
        }

        public void setShareCode(String shareCode) {
            this.shareCode = shareCode;
        }

        public String getSmsCode() {
            return smsCode;
        }

        public void setSmsCode(String smsCode) {
            this.smsCode = smsCode;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }
    }
}
