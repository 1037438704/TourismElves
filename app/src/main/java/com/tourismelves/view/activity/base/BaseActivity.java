package com.tourismelves.view.activity.base;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.view.ViewCompat;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.tourismelves.utils.log.LogUtil;

import static com.tourismelves.utils.system.StatusBarUtil.getStatusHeight;


public abstract class BaseActivity extends AbstractActivity implements IBaseActivity {
    //屏幕的宽高
    protected int mWidth;
    protected int mHeight;
    //当前页面是否可见
    protected boolean isResume;
    protected FragmentManager fm;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();

        setContentLayout();//由具体的activity实现，设置内容布局ID
        translucentStatusBar(true);

        initControls();//由具体的activity实现，做视图相关的初始化
        obtainData();//由具体的activity实现，做数据的初始化
        initEvent();//由具体的activity实现，做事件监听的初始化
    }

    private void init() {
        WindowManager manager = this.getWindowManager();
        DisplayMetrics outMetrics = new DisplayMetrics();
        manager.getDefaultDisplay().getMetrics(outMetrics);
        mWidth = outMetrics.widthPixels;
        mHeight = outMetrics.heightPixels;
        fm = getSupportFragmentManager();
    }

    /*------------------------------------------ 状态栏和虚拟键盘  -------------------------------------*/

    /**
     * 将一个布局延长状态栏的高度
     */
    protected void setStatusBar(@IdRes int id) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            final View linear_bar = findViewById(id);
            final int statusHeight = getStatusHeight(getContext());
            linear_bar.post(new Runnable() {
                @Override
                public void run() {
                    ViewGroup.LayoutParams params = linear_bar.getLayoutParams();
                    params.height = statusHeight;
                    linear_bar.setLayoutParams(params);
                }
            });
        }
    }

    /**
     * 状态栏全透明
     */
    protected void translucentStatusBar(boolean hideStatusBarBackground) {
        Window window = getWindow();
        //添加Flag把状态栏设为可绘制模式
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        if (hideStatusBarBackground) {
            //如果为全透明模式，取消设置Window半透明的Flag
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //设置状态栏为透明
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                window.setStatusBarColor(Color.TRANSPARENT);
            }
            //设置window的状态栏不可见
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        } else {
            //如果为半透明模式，添加设置Window半透明的Flag
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //设置系统状态栏处于可见状态
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_VISIBLE);
        }
        //view不根据系统窗口来调整自己的布局
        ViewGroup mContentView = (ViewGroup) window.findViewById(Window.ID_ANDROID_CONTENT);
        View mChildView = mContentView.getChildAt(0);
        if (mChildView != null) {
            ViewCompat.setFitsSystemWindows(mChildView, false);
            ViewCompat.requestApplyInsets(mChildView);
        }
    }

    /**
     * 黑色状态栏
     */
    protected void setStatusUi() {
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
    }

    /*-------------------------------------------------------------------------------*/

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    public void onResume() {
        super.onResume();
        isResume = true;
    }

    public void onPause() {
        super.onPause();
        isResume = false;
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public int getResourceColor(@ColorRes int colorId) {
        return ResourcesCompat.getColor(getResources(), colorId, null);
    }

    @Override
    public String getResourceString(@StringRes int stringId) {
        return getResources().getString(stringId);
    }

    @Override
    public Drawable getResourceDrawable(@DrawableRes int id) {
        return ResourcesCompat.getDrawable(getResources(), id, null);
    }

    @Override
    public void onLowMemory() {
        LogUtil.e("内存不足");
        super.onLowMemory();
    }

}
