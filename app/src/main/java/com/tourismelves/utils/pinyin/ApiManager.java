package com.tourismelves.utils.pinyin;

/**
 * Created by 17251 on 2018/3/9.
 */

public class ApiManager {


    public static final String ALL_URL = "http://211.157.162.2/";

    public static String getBaseUrl(){
        return ALL_URL;
    }



    //修改个人信息

    public static final String SET_PERSON = getBaseUrl() + "lyjl/app/updateUserInfo.do";


    //登录接口
    public static final String LOGIN = getBaseUrl()+"lyjl/web/login.do";
}
