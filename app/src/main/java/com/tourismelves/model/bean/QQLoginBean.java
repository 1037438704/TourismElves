package com.tourismelves.model.bean;

import java.util.List;

/**
 * Created by fanhui on 2018/9/7.
 */

public class QQLoginBean {

    /**
     * type : json
     * code : 200
     * message : 恭喜登陆成功！
     * dataList : [{"userId":458,"gold":0,"invitationGold":0,"invitationed":0,"type":0,"nickName":"　","image":"http://qzapp.qlogo.cn/qzapp/1107474298/93655B0CD64996AE3F420A6FF233501A/100","gender":"男","qq":"93655B0CD64996AE3F420A6FF233501A","status":1,"insertTime":"2018-09-06 22:22:57","updateTime":"2018-09-06 22:22:57","insertIp":"111.164.178.33","updateIp":"111.164.178.33","admin":0,"token":"adbe2822-3627-4e76-a696-6090e15f6704"}]
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
         * userId : 458
         * gold : 0
         * invitationGold : 0
         * invitationed : 0
         * type : 0
         * nickName : 　
         * image : http://qzapp.qlogo.cn/qzapp/1107474298/93655B0CD64996AE3F420A6FF233501A/100
         * gender : 男
         * qq : 93655B0CD64996AE3F420A6FF233501A
         * status : 1
         * insertTime : 2018-09-06 22:22:57
         * updateTime : 2018-09-06 22:22:57
         * insertIp : 111.164.178.33
         * updateIp : 111.164.178.33
         * admin : 0
         * token : adbe2822-3627-4e76-a696-6090e15f6704
         */

        private int userId;
        private int gold;
        private int invitationGold;
        private int invitationed;
        private int type;
        private String nickName;
        private String image;
        private String gender;
        private String qq;
        private int status;
        private String insertTime;
        private String updateTime;
        private String insertIp;
        private String updateIp;
        private int admin;
        private String token;
        private String loginName;

        public String getLoginName() {
            return loginName;
        }

        public void setLoginName(String loginName) {
            this.loginName = loginName;
        }

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

        public String getQq() {
            return qq;
        }

        public void setQq(String qq) {
            this.qq = qq;
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

        public int getAdmin() {
            return admin;
        }

        public void setAdmin(int admin) {
            this.admin = admin;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }
    }
}
