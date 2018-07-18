package com.tourismelves.view.activity.base;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;

/**
 * BaseActivity接口
 */

public interface IBaseActivity {

    /**
     * 获取上下文
     *
     * @return
     */
    Context getContext();

    /**
     * 从Resource中获取颜色
     *
     * @param colorId colorId
     * @return 颜色值
     */
    int getResourceColor(@ColorRes int colorId);

    /**
     * 从Resource中获取字符串
     *
     * @param stringId stringId
     * @return string
     */
    String getResourceString(@StringRes int stringId);

    /**
     * 从Resource中获取Drawable
     *
     * @param id DrawableRes
     * @return Drawable
     */
    Drawable getResourceDrawable(@DrawableRes int id);


}
