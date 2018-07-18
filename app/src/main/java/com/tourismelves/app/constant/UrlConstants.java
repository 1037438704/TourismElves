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


}
