package com.tourismelves.view.widget.swipetoloadlayout;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;

import com.tourismelves.R;


/**
 * 刷新布局
 */
public class TwitterRefreshHeaderView extends SwipeRefreshHeaderLayout {
    private AppCompatImageView iv_refresh;
    private int mHeaderHeight;

    private AnimationDrawable animationDrawable;

    public TwitterRefreshHeaderView(Context context) {
        this(context, null);
    }

    public TwitterRefreshHeaderView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TwitterRefreshHeaderView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mHeaderHeight = getResources().getDimensionPixelOffset(R.dimen.dp75);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        iv_refresh = findViewById(R.id.header_refresh);
        animationDrawable = (AnimationDrawable) iv_refresh.getDrawable();
    }

    @Override
    public void onRefresh() { //刷新中
        if (!animationDrawable.isRunning()) {
            animationDrawable.start();
        }
    }

    @Override
    public void onPrepare() {
    }

    @Override
    public void onMove(int y, boolean isComplete, boolean automatic) {
        if (!isComplete) {
            if (y > mHeaderHeight) {
                if (!animationDrawable.isRunning()) {
                    animationDrawable.start();
                }
            } else if (y < mHeaderHeight) {
                if (animationDrawable.isRunning()) {
                    animationDrawable.stop();
                }
            }
        }
    }

    @Override
    public void onRelease() {
    }

    @Override
    public void onComplete() {
        if (animationDrawable.isRunning()) {
            animationDrawable.stop();
        }
    }

    @Override
    public void onReset() {
        iv_refresh.setVisibility(VISIBLE);
    }
}
