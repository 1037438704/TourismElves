package com.tourismelves.utils.system;

import android.content.Context;
import android.util.DisplayMetrics;

/**
 * 屏幕适配
 */
public class ResolutionUtil {
    private Context context;
    /**
     * 设备屏幕的基准宽度,高度
     */
    public static final int WINDOWS_STANDARD_SIZE_WIDTH = 720;
    public static final int WINDOWS_STANDARD_SIZE_HIGH = 1280;

    /**
     * 标准密度
     */
    private static final float DEFAULTDENSITY = 160;

    /**
     * 标准缩放大小
     */
    private static final float DEFAULTFONTDESITY = 1.0F;

    /**
     * 屏幕密度
     */
    private float density;

    /**
     * 当前屏幕的字体缩放比例
     */
    private float fontDesity;

    /**
     * 当前屏幕和标准屏幕(720P)的比例
     */
    private float scale;

    /**
     * 当前设备宽度
     */
    private int deviceWidth;

    /**
     * 当前设备高度
     */
    private int deviceHeight;

    /**
     * 横向屏幕比例
     */
    private float scaleWidth;

    /**
     * 竖向屏幕比例
     */
    private float scaleHeight;

    /* 持有私有静态实例，防止被引用，此处赋值为null，目的是实现延迟加载 */
    private static ResolutionUtil instance = null;

    /* 私有构造方法，防止被实例化 */
    private ResolutionUtil() {
    }

    /* 1:懒汉式，静态工程方法，创建实例 */
    public static ResolutionUtil getInstance(Context ctx) {
        if (instance == null) {
            instance = new ResolutionUtil(ctx);
        }
        return instance;
    }

    public ResolutionUtil(Context ctx) {
        context = ctx;
        DisplayMetrics dm = ctx.getResources().getDisplayMetrics();
        this.deviceWidth = dm.widthPixels;
        this.deviceHeight = dm.heightPixels;
        density = dm.densityDpi;
        fontDesity = dm.scaledDensity;
        if (deviceWidth > deviceHeight) {
            scaleWidth = (float) deviceWidth / WINDOWS_STANDARD_SIZE_HIGH;
            scaleHeight = (float) deviceHeight / WINDOWS_STANDARD_SIZE_WIDTH;
        } else {
            scaleWidth = (float) deviceWidth / WINDOWS_STANDARD_SIZE_WIDTH;
            scaleHeight = (float) deviceHeight / WINDOWS_STANDARD_SIZE_HIGH;
        }

    }

    public int getWidth() {
        return deviceWidth;
    }

    public int getHeight() {
        return deviceHeight;
    }

    /**
     * 根据屏幕宽高比与密度获取适配当前屏幕px
     *
     * @param pxVlaue px
     * @return
     */
    public int px2dp2pxWidth(float pxVlaue) {
        float dp = pxVlaue / (density / DEFAULTDENSITY);
        return (int) (dp * (density / DEFAULTDENSITY) * scaleWidth + 0.5);

    }

    /**
     * 根据屏幕宽高比与密度获取适配当前屏幕px
     *
     * @param pxVlaue px
     * @return
     */
    public int px2dp2pxHeight(float pxVlaue) {
        float dp = pxVlaue / (density / DEFAULTDENSITY);
        return (int) (dp * (density / DEFAULTDENSITY) * scaleHeight + 0.5);
    }

    /**
     * 根据屏幕宽度与字体缩放比获取适配当前屏幕字体大小
     *
     * @param spVlaue px
     * @return
     */
    public int px2sp2px(float spVlaue) {
        float dp = spVlaue / fontDesity;
        int px = (int) (dp * (fontDesity / DEFAULTFONTDESITY) / fontDesity * scaleWidth);
        return px;
    }

    /**
     * 将dip转为px值
     *
     * @param dipValue
     * @return
     */
    public int dip2px(float dipValue) {
        return (int) ((int) (dipValue * scale) * density + 0.5);
    }

    public int px2dip(float pxValue) {
        return (int) ((int) (pxValue * scale) / density + 0.5);
    }

    // 将px值转换为sp值
    public int px2sp(float pxValue) {
        return (int) (pxValue / fontDesity + 0.5f);
    }
}