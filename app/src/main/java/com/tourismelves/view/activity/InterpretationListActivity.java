package com.tourismelves.view.activity;

import android.annotation.SuppressLint;
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
import android.widget.RelativeLayout;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.tourismelves.R;
import com.tourismelves.model.bean.AttractionsBean;
import com.tourismelves.model.net.OkHttpUtils;
import com.tourismelves.utils.common.ToastUtil;
import com.tourismelves.utils.log.LogUtil;
import com.tourismelves.utils.system.SPUtils;
import com.tourismelves.view.activity.base.StateBaseActivity;
import com.tourismelves.view.adapter.InterpretationListAdapter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

import static com.tourismelves.app.constant.UrlConstants.sceneryList;

/**
 * 讲解列表 地图
 */

public class InterpretationListActivity extends StateBaseActivity {
    @BindView(R.id.interpretation_list_like_btn)
    AppCompatTextView interpretationListLikeBtn;
    @BindView(R.id.interpretation_list_name)
    AppCompatTextView interpretationListName;
    private AppCompatImageView interpretationListComments;
    private LinearLayout interpretationListBottom;
    private int lastY = 0, lastY2 = 0;

    private BottomSheetDialog mBottomSheetDialog;
    private int ordId;
    private String name;
    private ArrayList<AttractionsBean> attractionsBeans;

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
    }

    @Override
    protected void obtainData() {
        Intent intent = getIntent();
        ordId = intent.getIntExtra("ordId", 0);
        name = intent.getStringExtra("name");
        interpretationListName.setText(name);
        sceneryList(ordId + "");
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
    @SuppressLint("ClickableViewAccessibility")
    private void setBehaviorCallback() {
        mBottomSheetDialog = new BottomSheetDialog(this, R.style.CustomBottomSheetDialogTheme);
        View view = LayoutInflater.from(this).inflate(R.layout.dialog_fragment_interpretation_list, null, false);
        mBottomSheetDialog.setContentView(view);

        AppCompatImageView commentsOff = (AppCompatImageView) view.findViewById(R.id.interpretation_list_comments_off);
        AppCompatTextView tvName = (AppCompatTextView) view.findViewById(R.id.interpretation_list_name);
        LinearLayout ll = (LinearLayout) view.findViewById(R.id.interpretation_ll);
        RelativeLayout noData = (RelativeLayout) view.findViewById(R.id.layout_null_data);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.interpretation_list_recycler);
        if (attractionsBeans.size() > 0) {
            recyclerView.setVisibility(View.VISIBLE);
            noData.setVisibility(View.GONE);
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            InterpretationListAdapter interpretationListAdapter = new InterpretationListAdapter(getContext(), attractionsBeans);
            recyclerView.setAdapter(interpretationListAdapter);
        } else {
            recyclerView.setVisibility(View.GONE);
            noData.setVisibility(View.VISIBLE);
        }

        tvName.setText(name);
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
                            Intent intent = new Intent(getContext(), InterpretationList2Activity.class);
                            intent.putParcelableArrayListExtra("attractionsBeans", attractionsBeans);
                            startActivity(intent);
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
//                startActivity(new Intent(this, VisitGuidanceActivity.class));
                break;
            case R.id.interpretation_list_search_around_comments_btn:
                break;
            case R.id.interpretation_list_comments_btn:
//                startActivity(new Intent(this, CommentsActivity.class));
                break;
        }
    }

    /**
     * 景点列表
     */
    private void sceneryList(String ordId) {
        attractionsBeans = new ArrayList<>();
        String userId = SPUtils.getInstance(getContext()).getString("putInt");
        OkHttpUtils.get(String.format(sceneryList, ordId, userId),
                new OkHttpUtils.ResultCallback<String>() {
                    @Override
                    public void onSuccess(final String response) {
                        new Thread() {
                            @Override
                            public void run() {
                                super.run();
                                JSONObject object = JSON.parseObject(response);
                                //获取请求结果的code码
                                Integer code = object.getInteger("code");
                                if (code == 200) {
                                    //获取当前数据源集合
                                    JSONArray dataList = object.getJSONArray("dataList");
                                    int size = dataList.size();
                                    for (int i = 0; i < size; i++) {
                                        String string = dataList.getJSONObject(i).toString();
                                        AttractionsBean attractionsBean = JSON.parseObject(string, AttractionsBean.class);
                                        attractionsBeans.add(attractionsBean);
                                    }
                                } else {
                                    ToastUtil.show(object.getString("message"));
                                }

                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        setBehaviorCallback();
                                    }
                                });
                            }
                        }.start();
                    }

                    @Override
                    public void onFailure(Exception e) {
                        ToastUtil.show(R.string.no_found_network);
                    }
                });
    }
}
