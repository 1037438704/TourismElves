package com.tourismelves.view.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import com.tourismelves.R;
import com.tourismelves.utils.system.ResolutionUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 星星评分
 */
public class RatingStar extends LinearLayout implements View.OnClickListener {
    private float starMargin;
    private int starSize;
    private int starCount;
    private float curRating;
    private boolean isClick = false;
    private List<AppCompatImageView> appCompatImageViews;
    private int flagIndex = -1;
    private OnClickStarListener onClickStarListener;

    public void setOnClickStarListener(OnClickStarListener onClickStarListener) {
        this.onClickStarListener = onClickStarListener;
    }

    public RatingStar(Context context) {
        this(context, null);
    }

    public RatingStar(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RatingStar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        final TypedArray a = context.obtainStyledAttributes(
                attrs, R.styleable.RatingStar, defStyleAttr, 0);
        curRating = a.getFloat(R.styleable.RatingStar_curRating, 0);
        starMargin = a.getDimension(R.styleable.RatingStar_starMarginRight, 10);
        starSize = (int) a.getDimension(R.styleable.RatingStar_starSize, ResolutionUtil.getInstance(context).px2dp2pxWidth(24));
        starCount = a.getInteger(R.styleable.RatingStar_starCount, 5);
        a.recycle();

        init();
    }

    public void setClick(boolean click) {
        isClick = click;
    }

    private void init() {
        setOrientation(HORIZONTAL);
        appCompatImageViews = new ArrayList<>();

        for (int i = 0; i < starCount; i++) {
            AppCompatImageView imageView = new AppCompatImageView(getContext());
            if (i < curRating) {
                imageView.setImageResource(R.mipmap.pingfen_s);
            } else {
                if ((i + 1) - curRating < 1) {
                    imageView.setImageResource(R.mipmap.pingfen_s);
                } else {
                    imageView.setImageResource(R.mipmap.pingfen);
                }
            }
            LayoutParams params = new LayoutParams(starSize, starSize);
            if (i != starCount - 1) {
                params.setMargins(0, 0, (int) starMargin, 0);
            }
            imageView.setId(i);
            imageView.setLayoutParams(params);
            imageView.setOnClickListener(this);
            appCompatImageViews.add(imageView);
            addView(imageView);
        }
    }

    private void setRating(int index) {
        if (flagIndex == index)
            return;
        curRating = index + 1;

        if (onClickStarListener != null) {
            onClickStarListener.onStar(index);
        }

        for (int i = 0; i < starCount; i++) {
            if (i < index + 1) {
                appCompatImageViews.get(i).setImageResource(R.mipmap.pingfen_s);
            } else {
                appCompatImageViews.get(i).setImageResource(R.mipmap.pingfen);
            }
        }

        flagIndex = index;
    }

    public void setCurRating(float score) {
        curRating = score;
        for (int i = 0; i < starCount; i++) {
            AppCompatImageView imageView = appCompatImageViews.get(i);
            if (i + 1 <= score) {
                imageView.setImageResource(R.mipmap.pingfen_s);
            } else {
                if (score < i + 1 && score > i) {
                    imageView.setImageResource(R.mipmap.pingfen_s);
                } else {
                    imageView.setImageResource(R.mipmap.pingfen);
                }
            }
        }
    }

    public float getCurRating() {
        return curRating;
    }

    @Override
    public void onClick(View view) {
        if (isClick) {
            setRating(view.getId());
        }
    }

    public interface OnClickStarListener {
        void onStar(int index);
    }
}
