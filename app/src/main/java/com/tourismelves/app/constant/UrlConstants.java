package com.tourismelves.app.constant;

/**
 * 接口类
 */
public interface UrlConstants {

    String port = "http://211.157.162.2/";

    //热门景区列表
    String organizationList = port + "lyjl/web/organizationList.do?page.showCount=%s&page.currentPage=%s";
    //首页轮转图
    String posterList = port + "lyjl/web/posterList.do?spaceId=%s";
    //查询景区or精灵说
    String searchOrganizationOrArticle = port + "lyjl/web/searchOrganizationOrArticle.do?key=%s&type=%s&sortType=%s&page.showCount=%s&page.currentPage=%s";
    //地区列表
    String areaList = port + "lyjl/web/areaList.do";
    //附近景区列表
    String nearOrganizationList = port + "lyjl/web/nearOrganizationList.do?latitude=%s&longitude=%s&page.showCount=%s&page.currentPage=%s";
    //获取图形验证码
    String verifycode = port +"lyjl/web/getAppVerifyCode.do";
    //登录接口
    String login = port+"lyjl/web/login.do?loginName=%s&password=%s";
    //注册接口
    String register = port+"lyjl/web/register.do?loginName=%s&password=%s&smsCode=%s&channel=%s&shareCode=%s";
    //获取短信验证码
    String randomCode = port+"lyjl/web/getAppSMSCode.do?randomCode=%s&mobile=%s&code=%s";
    //个人信息
    String userinfo = port+"lyjl/app/getUserInfo.do?userId=%s";
    //金币套餐
    String moneyinfo = port+"lyjl/app/packageList.do";
    //精灵说or景区
    String searcharticle = port+"lyjl/web/searchOrganizationOrArticle.do?key=天津&type=0&sortType=0";
    //获取精灵说
    String elfsaidinfo = port+"lyjl/web/newsList.do?page.showCount=%s&page.currentPage=%s";
    //

}
