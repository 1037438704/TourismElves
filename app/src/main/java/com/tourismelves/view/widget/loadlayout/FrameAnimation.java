package com.tourismelves.view.widget.loadlayout;

import android.graphics.drawable.AnimationDrawable;
import android.support.v7.widget.AppCompatImageView;

/**
 * 帧动画控制类
 */
public class FrameAnimation {

    private AppCompatImageView mIvAnimation;
    private AnimationDrawable mAnimationDrawable;

    public FrameAnimation(AppCompatImageView ivLoading) {
        this.mIvAnimation = ivLoading;
    }

    /**
     * 显示动画
     */
    public void showAnim() {
        if (mIvAnimation != null && (mAnimationDrawable = (AnimationDrawable) mIvAnimation.getBackground()) != null) {
            mAnimationDrawable.start();
        }
    }


    /**
     * 关闭动画
     */
    public void closeAnim() {
        if (mIvAnimation != null && mAnimationDrawable != null) mAnimationDrawable.stop();
    }


}
