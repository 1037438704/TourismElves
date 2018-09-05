package com.tourismelves.utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import java.io.File;

/**
 *
 */

public class MapUtils {
    private Context context;
    /* 持有私有静态实例，防止被引用，此处赋值为null，目的是实现延迟加载 */
    private static MapUtils instance = null;

    /* 私有构造方法，防止被实例化 */
    private MapUtils() {
    }

    /* 1:懒汉式，静态工程方法，创建实例 */
    public static MapUtils getInstance(Context ctx) {
        if (instance == null) {
            instance = new MapUtils(ctx);
        }
        return instance;
    }

    public MapUtils(Context ctx) {
        context = ctx;
    }

    public void openMap(double lat, double lng) {
        boolean b = openGaoDeNavi(lat, lng);
        if (!b){
            boolean b1 = openBaiduNavi(lat, lng);
            if (!b1){
                boolean b2 = openGoogleNavi(lat, lng);
                if (!b2){
                    openWebGoogleNavi(lat, lng);
                }
            }
        }
    }


    private boolean isInstallByread(String packageName) {
        return new File("/data/data/" + packageName).exists();
    }

    /**
     * 启动高德App进行导航
     */
    private boolean openGaoDeNavi(double lat, double lng) {
        String pg = "com.autonavi.minimap";
        if (!isInstallByread(pg)) {
            return false;
        }
        StringBuffer stringBuffer = new StringBuffer("androidamap://navi?sourceApplication=")
                .append("com.tourismelves").append("&lat=").append(lat)
                .append("&lon=").append(lng)
                .append("&dev=").append(1)
                .append("&style=").append(0);
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(stringBuffer.toString()));
        intent.addCategory(Intent.CATEGORY_DEFAULT);
        intent.setPackage(pg);
        context.startActivity(intent);
        return true;
    }

    /**
     * 打开百度地图导航客户端
     */
    private boolean openBaiduNavi(double lat, double lng) {
        String pg = "com.baidu.BaiduMap";
        if (!isInstallByread(pg)) {
            return false;
        }
        StringBuffer stringBuffer = new StringBuffer("baidumap://map/navi?location=")
                .append(lat).append(",").append(lng).append("&type=TIME");
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(stringBuffer.toString()));
        intent.setPackage(pg);
        context.startActivity(intent);
        return true;
    }

    /**
     * 打开google地图客户端开始导航
     */
    private boolean openGoogleNavi(double lat, double lng) {
        String pg = "com.google.android.apps.maps";
        if (!isInstallByread(pg)) {
            return false;
        }
        StringBuffer stringBuffer = new StringBuffer("google.navigation:q=").append(lat).append(",").append(lng).append("&mode=d");
        Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(stringBuffer.toString()));
        i.setPackage(pg);
        context.startActivity(i);
        return true;
    }

    /**
     * 打开google Web地图导航
     */
    private void openWebGoogleNavi(double lat, double lng) {
        StringBuffer stringBuffer = new StringBuffer("http://ditu.google.cn/maps?hl=zh&mrt=loc&q=").append(lat).append(",").append(lng);
        Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(stringBuffer.toString()));
        context.startActivity(i);
    }
}
