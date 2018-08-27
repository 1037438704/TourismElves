package com.tourismelves.app.constant;

/**
 * 接口类
 */
public interface UrlConstants {

    String port = "http://211.157.162.2/";

    //热门景区列表
    String organizationList = port + "lyjl/web/organizationList.do?page.showCount=%s&page.currentPage=%s";
    //热门景区详情
    String organizationList2 = port + "/lyjl/web/organizationList.do?orgId=%s";
    //首页轮转图
    String posterList = port + "lyjl/web/posterList.do?spaceId=%s";
    //查询景区or精灵说
    String searchOrganizationOrArticle = port + "lyjl/web/searchOrganizationOrArticle.do?key=%s&type=%s&sortType=%s&page.showCount=%s&page.currentPage=%s";
    //景点列表
    String sceneryList = port + "lyjl/web/sceneryList.do?orgId=%s&userId=%s";
    //地区列表
    String areaList = port + "lyjl/web/areaList.do";
    //附近景区列表
    String nearOrganizationList = port + "lyjl/web/nearOrganizationList.do?latitude=%s&longitude=%s&page.showCount=%s&page.currentPage=%s";
    //获取图形验证码
    String verifycode = port + "lyjl/web/getAppVerifyCode.do";
    //登录接口
    String login = port + "lyjl/web/login.do?loginName=%s&password=%s";
    //注册接口
    String register = port + "lyjl/web/register.do?loginName=%s&password=%s&smsCode=%s&channel=%s&shareCode=%s";
    //获取短信验证码
    String randomCode = port + "lyjl/web/getAppSMSCode.do?randomCode=%s&mobile=%s&code=%s";
    //个人信息
    String userinfo = port + "lyjl/app/getUserInfo.do?userId=%s";
    //金币套餐
    String moneyinfo = port + "lyjl/app/packageList.do";
    //精灵说or景区
    String searcharticle = port + "lyjl/web/searchOrganizationOrArticle.do?key=天津&type=0&sortType=0";
    //获取精灵说
    String elfsaidinfo = port + "lyjl/web/newsList.do?page.showCount=%s&page.currentPage=%s";
    //激活码
    String useActivationCode = port + "lyjl/app/useActivationCode.do";
    //订单列表
    String orderList = port + "lyjl/app/orderList.do?userId=%s&page.currentPage=%s";
    // 添加购物车
    String addCart = port + "lyjl/app/addCart.do?";
    //购物车列表
    String cartList = port + "lyjl/app/cartList.do?";
    //购物车结算
    String settlement = port + "lyjl/app/settlement.do?";
    //微信支付
    String wxPayPay = port + "lyjl/app/wxPayPay.do?";
    //支付宝支付
    String alipayPay = port + "lyjl/app/alipayPay.do?";
    //金币支付
    String glodPay = port + "lyjl/app/goldPay.do?";
    //收藏
    String saveFavorite = port + "lyjl/app/saveFavorite.do?";
    //删除收藏
    String delFavorite = port + "lyjl/app/delFavorite.do?";
    //是否收藏
    String isFavorite = port + "lyjl/web/isFavorite.do?";
    //评论列表
    String getComment = port + "lyjl/web/getComment.do?";

}
