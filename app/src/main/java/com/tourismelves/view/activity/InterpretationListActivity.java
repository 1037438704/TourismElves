package com.tourismelves.view.activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

import com.tourismelves.R;
import com.tourismelves.utils.log.LogUtil;
import com.tourismelves.view.activity.base.StateBaseActivity;
import com.tourismelves.view.adapter.InterpretationListAdapter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 讲解列表 地图
 */

public class InterpretationListActivity extends StateBaseActivity {
    @BindView(R.id.interpretation_list_like_btn)
    AppCompatTextView interpretationListLikeBtn;
    private AppCompatImageView interpretationListComments;
    private LinearLayout interpretationListBottom;
    private int lastY = 0, lastY2 = 0;

    private BottomSheetDialog mBottomSheetDialog;

    @Override
    protected void setContentLayout() {
        setContentView(R.layout.activity_interpretation_list);
    }

    @Override
    protected void initControls() {
        showStateLayout(1);
        setBaseTitle("讲解列表");
        interpretationListComments = findViewById(R.id.interpretation_list_comments);
        interpretationListBottom = findViewById(R.id.interpretation_list_bottom);

        setBehaviorCallback();
    }

    @Override
    protected void obtainData() {

    }

    @Override
    protected void initEvent() {

        interpretationListComments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBottomSheetDialog.show();
            }
        });

        interpretationListBottom.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int dy = 0;
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        //将点下的点的坐标保存
                        lastY2 = (int) event.getRawY();
                        return true;

                    case MotionEvent.ACTION_MOVE:
                        //计算出需要移动的距离
                        dy = (int) event.getRawY() - lastY2;
                        if (dy <= 0) {
                            mBottomSheetDialog.show();
                        }
                        return true;
                }
                return true;
            }
        });
    }

    /**
     * 讲解列表
     */
    private void setBehaviorCallback() {
        mBottomSheetDialog = new BottomSheetDialog(this, R.style.CustomBottomSheetDialogTheme);
        View view = LayoutInflater.from(this).inflate(R.layout.dialog_fragment_interpretation_list, null, false);
        mBottomSheetDialog.setContentView(view);

        AppCompatImageView commentsOff = (AppCompatImageView) view.findViewById(R.id.interpretation_list_comments_off);
        LinearLayout ll = (LinearLayout) view.findViewById(R.id.interpretation_ll);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.interpretation_list_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(new InterpretationListAdapter(getContext(), new ArrayList<String>()));

        commentsOff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBottomSheetDialog.dismiss();
            }
        });

        View rootView = mBottomSheetDialog.getDelegate().findViewById(android.support.design.R.id.design_bottom_sheet);
        rootView.setBackgroundColor(0x00ffffff);

        final BottomSheetBehavior bottomSheetBehavior = BottomSheetBehavior.from(rootView);
        bottomSheetBehavior.setPeekHeight((int) getResources().getDimension(R.dimen.dp364));
        bottomSheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                if (newState == BottomSheetBehavior.STATE_HIDDEN) {
                    mBottomSheetDialog.dismiss();
                    bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                }

            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {
            }
        });

        ll.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int dy = 0;
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        //将点下的点的坐标保存
                        lastY = (int) event.getRawY();
                        LogUtil.i("ACTION_DOWN_" + lastY);
                        return true;

                    case MotionEvent.ACTION_MOVE:
                        //计算出需要移动的距离
                        LogUtil.i("ACTION_MOVE_" + event.getRawY());
                        dy = (int) event.getRawY() - lastY;
                        if (dy <= 0) {
                            mBottomSheetDialog.dismiss();
                            startActivity(new Intent(getContext(), InterpretationList2Activity.class));
                        }
                        return true;
                    case MotionEvent.ACTION_UP:
                        LogUtil.i("ACTION_UP_" + event.getRawY());
                        return true;
                    case MotionEvent.ACTION_CANCEL:
                        //按钮弹起逻辑
                        LogUtil.i("ACTION_CANCEL_" + event.getRawY());
                        return true;
                }
                return true;
            }
        });
    }

    @OnClick({R.id.interpretation_list_like_btn, R.id.interpretation_list_visit_guidance_btn, R.id.interpretation_list_search_around_comments_btn, R.id.interpretation_list_comments_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.interpretation_list_like_btn:
                break;
            case R.id.interpretation_list_visit_guidance_btn:
                startActivity(new Intent(this, VisitGuidanceActivity.class));
                break;
            case R.id.interpretation_list_search_around_comments_btn:
                break;
            case R.id.interpretation_list_comments_btn:
                startActivity(new Intent(this, CommentsActivity.class));
                break;
        }
    }
}
