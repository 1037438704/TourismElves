package com.tourismelves.view.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;

import com.tourismelves.R;
import com.tourismelves.utils.system.ResolutionUtil;

import java.lang.reflect.Field;

/**
 * 支持自定义下标，自定义tab宽度
 * <p>
 * 自定义下标    --> {@link #setmSlideIcon}
 * 自定义tab宽度 --> 由{@link #COUNT_DEFAULT_VISIBLE_TAB}和{@link #RATIO_DEFAULT_LAST_VISIBLE_TAB}共同决定
 */
public class SlidingTabLayout extends TabLayout {
    /**
     * 每个tab的宽度
     */
    private int tabWidth;
    /**
     * 屏幕宽度
     */
    private int mScreenWidth;
    /**
     * 自定义指示器
     */
    private Bitmap mSlideIcon;
    /**
     * 滑动过程中指示器的水平偏移量
     */
    private int mTranslationX;
    /**
     * 指示器初始X偏移量
     */
    private int mInitTranslationX;
    /**
     * 是否显示最后一个指示器
     */
    private boolean isVisLast = false;
    /**
     * 指示器初始Y偏移量
     */
    private int mInitTranslationY;
    /**
     * 默认的页面可见的tab数量
     */
    private static final int COUNT_DEFAULT_VISIBLE_TAB = 2;
    /**
     * 默认最后一个tab露出百分比
     */
    private static final float RATIO_DEFAULT_LAST_VISIBLE_TAB = 0f;
    /**
     * 页面可见的tab数量
     */
    private int mTabVisibleCount = COUNT_DEFAULT_VISIBLE_TAB;
    /**
     * 最后一个tab露出百分比
     */
    private float mLastTabVisibleRatio = RATIO_DEFAULT_LAST_VISIBLE_TAB;

    public void setTabWhite(int tabWhite) {
        this.tabWidth = tabWhite;
    }

    public SlidingTabLayout(Context context) {
        super(context);
    }

    public SlidingTabLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mSlideIcon = BitmapFactory.decodeResource(getResources(), R.mipmap.line);
        this.mScreenWidth = getResources().getDisplayMetrics().widthPixels;

        //方案1：反射修改Tab宽度
        //reflectiveModifyTabWidth();

        //方案2：异步修改Tab宽度
        post(new Runnable() {
            @Override
            public void run() {
                resetTabParams();
            }
        });
    }

    private void reflectiveModifyTabWidth() {
        final Class<?> clz = TabLayout.class;
        try {
            final Field requestedTabMaxWidthField = clz.getDeclaredField("mRequestedTabMaxWidth");
            final Field requestedTabMinWidthField = clz.getDeclaredField("mRequestedTabMinWidth");

            requestedTabMaxWidthField.setAccessible(true);
            requestedTabMaxWidthField.set(this, (int) (mScreenWidth / (mTabVisibleCount + mLastTabVisibleRatio)));

            requestedTabMinWidthField.setAccessible(true);
            requestedTabMinWidthField.set(this, (int) (mScreenWidth / (mTabVisibleCount + mLastTabVisibleRatio)));
        } catch (final NoSuchFieldException e) {
            e.printStackTrace();
        } catch (final SecurityException e) {
            e.printStackTrace();
        } catch (final IllegalArgumentException e) {
            e.printStackTrace();
        } catch (final IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    /**
     * 重绘下标
     */
    public void redrawIndicator(int position, float positionOffset) {
        int width = getTabStrip().getChildAt(position).getWidth();
        mTranslationX = (int) ((position + positionOffset) * width);
        invalidate();
    }

    public void setIndicator() {
        isVisLast = true;
    }

    public void setmSlideIcon(Bitmap mSlideIcon) {
        this.mSlideIcon = mSlideIcon;
    }

    /**
     * tab的父容器，注意空指针
     */
    @Nullable
    public LinearLayout getTabStrip() {
        Class<?> tabLayout = TabLayout.class;
        Field tabStrip = null;
        try {
            tabStrip = tabLayout.getDeclaredField("mTabStrip");
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

        tabStrip.setAccessible(true);
        LinearLayout llTab = null;
        try {
            llTab = (LinearLayout) tabStrip.get(this);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        llTab.setClipChildren(false);
        return llTab;
    }

    /**
     * 绘制指示器
     */
    @Override
    protected void dispatchDraw(Canvas canvas) {
        if (mSlideIcon == null) {
            return;
        }

        canvas.save();
        // 平移到正确的位置，修正tabs的平移量
        if (isVisLast) {
            if (mTranslationX == ResolutionUtil.getInstance(getContext()).getWidth()) {
                mTranslationX = 0;
            }
            canvas.translate(mInitTranslationX - mTranslationX, this.mInitTranslationY);
        } else {
            canvas.translate(mInitTranslationX + mTranslationX, this.mInitTranslationY);
        }
        canvas.drawBitmap(this.mSlideIcon, 0, 0, null);

        canvas.restore();
        super.dispatchDraw(canvas);
    }

    /**
     * 重设tab宽度
     */
    private void resetTabParams() {
        LinearLayout tabStrip = getTabStrip();
        if (tabStrip == null) {
            return;
        }
        for (int i = 0; i < tabStrip.getChildCount(); i++) {
            LinearLayout tabView = (LinearLayout) tabStrip.getChildAt(i);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(tabWidth, LinearLayout.LayoutParams
                    .MATCH_PARENT);
            if (i == 0) {
                params.leftMargin = ResolutionUtil.getInstance(getContext()).px2dp2pxWidth(25);
            }
            if (i == tabStrip.getChildCount() - 1) {
                params.rightMargin = ResolutionUtil.getInstance(getContext()).px2dp2pxWidth(25);
            }
            tabView.setLayoutParams(params);
            tabView.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.BOTTOM);
            //tab中的图标可以超出父容器
            tabView.setClipChildren(false);
            tabView.setClipToPadding(false);
            tabView.setPadding(ResolutionUtil.getInstance(getContext()).px2dp2pxWidth(5), 0,
                    ResolutionUtil.getInstance(getContext()).px2dp2pxWidth(5),
                    ResolutionUtil.getInstance(getContext()).px2dp2pxWidth(20));
        }
        initTranslationParams(tabStrip, mScreenWidth);
    }

    /**
     * 初始化三角下标的坐标参数
     */
    private void initTranslationParams(LinearLayout llTab, int screenWidth) {
        if (mSlideIcon == null) {
            return;
        }
        View firstView = llTab.getChildAt(0);
        if (firstView != null) {
            if (isVisLast) {
                this.mInitTranslationX = (llTab.getRight() - tabWidth / 2 - this.mSlideIcon.getWidth() / 2);
            } else {
                this.mInitTranslationX = (firstView.getLeft() + tabWidth / 2 - this.mSlideIcon.getWidth() / 2) +
                        ResolutionUtil.getInstance(getContext()).px2dp2pxWidth(25);
            }
            this.mInitTranslationY = (getBottom() - getTop() - this.mSlideIcon.getHeight());
        }
    }
}
