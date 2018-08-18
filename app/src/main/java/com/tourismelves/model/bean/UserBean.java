package com.tourismelves.model.bean;

import java.util.List;

/**
 * Created by fanhui on 2018/8/9.
 */

public class UserBean {

    /**
     * type : json
     * code : 200
     * dataList : [{"userId":37,"gold":0,"invitationGold":0,"invitationCode":"WAOFAH","invitationed":0,"type":0,"loginName":"17320239690","password":"17351a9011930a202da7c7012f4e8fea","nickName":"17320239690","image":"public/images/userImg.png","gender":"女","mobile":"17320239690","status":1,"insertTime":"2018-07-16 01:05:08","updateTime":"2018-08-09 08:58:37","insertIp":"106.47.225.233","updateIp":"59.109.131.12","channel":"android","admin":0}]
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
         * userId : 37
         * gold : 0
         * invitationGold : 0
         * invitationCode : WAOFAH
         * invitationed : 0
         * type : 0
         * loginName : 17320239690
         * password : 17351a9011930a202da7c7012f4e8fea
         * nickName : 17320239690
         * image : public/images/userImg.png
         * gender : 女
         * mobile : 17320239690
         * status : 1
         * insertTime : 2018-07-16 01:05:08
         * updateTime : 2018-08-09 08:58:37
         * insertIp : 106.47.225.233
         * updateIp : 59.109.131.12
         * channel : android
         * admin : 0
         */

        private int userId;
        private int gold;
        private int invitationGold;
        private String invitationCode;
        private int invitationed;
        private int type;
        private String loginName;
        private String password;
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
        private int admin;

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

        public String getInvitationCode() {
            return invitationCode;
        }

        public void setInvitationCode(String invitationCode) {
            this.invitationCode = invitationCode;
        }

        public int getInvitationed() {
            return invitationed;
        }

        public void setInvitationed(int invitationed) {
            this.invitationed = invitationed;
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

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
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

        public int getAdmin() {
            return admin;
        }

        public void setAdmin(int admin) {
            this.admin = admin;
        }
    }
}
