package com.tourismelves.view.activity;

import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import com.tourismelves.R;
import com.tourismelves.view.activity.base.StateBaseActivity;
import com.tourismelves.view.widget.RatingStar;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 评论
 */

public class CommentsActivity extends StateBaseActivity {
    @BindView(R.id.comments_rating)
    RatingStar commentsRating;
    @BindView(R.id.comments_tv)
    AppCompatTextView commentsTv;
    @BindView(R.id.comments_et)
    AppCompatEditText commentsEt;
    @BindView(R.id.comments_anonymous)
    AppCompatTextView commentsAnonymous;

    @Override
    protected void setContentLayout() {
        setContentView(R.layout.activity_comments);
    }

    @Override
    protected void initControls() {
        showStateLayout(1);
        showStateRightView(0);
        setBaseTitle("评论");
        setBaseRightTv("发布");

        commentsRating.setClick(true);
    }

    @Override
    protected void obtainData() {

    }

    @Override
    protected void initEvent() {
        setBaseRightTvListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        commentsRating.setOnClickStarListener(new RatingStar.OnClickStarListener() {
            @Override
            public void onStar(int index) {
                commentsTv.setText(index + "");
            }
        });
    }

    @OnClick({R.id.comments_picture, R.id.comments_video, R.id.comments_anonymous})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.comments_picture:
                break;
            case R.id.comments_video:
                break;
            case R.id.comments_anonymous:
                commentsAnonymous.setSelected(!commentsAnonymous.isSelected());
                break;
        }
    }
}
