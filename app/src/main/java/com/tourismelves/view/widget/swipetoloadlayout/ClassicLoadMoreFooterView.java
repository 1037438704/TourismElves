package com.tourismelves.view.widget.swipetoloadlayout;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;

import com.tourismelves.R;


/**
 * 加载布局
 */
public class ClassicLoadMoreFooterView extends SwipeLoadMoreFooterLayout {
    private AppCompatImageView iv_refresh;

    private int mFooterHeight;
    private AnimationDrawable animationDrawable;

    public ClassicLoadMoreFooterView(Context context) {
        this(context, null);
    }

    public ClassicLoadMoreFooterView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ClassicLoadMoreFooterView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mFooterHeight = getResources().getDimensionPixelOffset(R.dimen.dp60);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        iv_refresh = findViewById(R.id.footer_refresh);
        animationDrawable = (AnimationDrawable) iv_refresh.getDrawable();
    }

    @Override
    public void onPrepare() {
    }

    @Override
    public void onMove(int y, boolean isComplete, boolean automatic) {
        if (!isComplete) {
            if (-y >= mFooterHeight) {
                if (!animationDrawable.isRunning()) {
                    animationDrawable.start();
                }
            } else {
                if (animationDrawable.isRunning()) {
                    animationDrawable.stop();
                }
            }
        }
    }

    @Override
    public void onLoadMore() {
        if (!animationDrawable.isRunning()) {
            animationDrawable.start();
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
    }
}
