package com.tourismelves.view.fragment.near;

import android.support.v4.view.ViewCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.tourismelves.R;
import com.tourismelves.model.net.OkHttpUtils;
import com.tourismelves.model.res.HomeRes;
import com.tourismelves.utils.common.ToastUtil;
import com.tourismelves.utils.system.LocationUtil;
import com.tourismelves.view.adapter.NearScenicSpotAdapter;
import com.tourismelves.view.fragment.base.BaseFragment;
import com.tourismelves.view.widget.loadlayout.State;
import com.tourismelves.view.widget.swipetoloadlayout.OnLoadMoreListener;
import com.tourismelves.view.widget.swipetoloadlayout.OnRefreshListener;
import com.tourismelves.view.widget.swipetoloadlayout.SwipeToLoadLayout;

import java.util.ArrayList;

import butterknife.BindView;

import static com.tourismelves.app.constant.UrlConstants.nearOrganizationList;
import static com.tourismelves.view.activity.MainActivity.latitude;
import static com.tourismelves.view.activity.MainActivity.longitude;
import static com.tourismelves.view.widget.loadlayout.State.LOADING;
import static com.tourismelves.view.widget.loadlayout.State.NO_DATA;
import static com.tourismelves.view.widget.loadlayout.State.SUCCESS;

/**
 * 景区
 */

public class ScenicSpotFragment extends BaseFragment {
    @BindView(R.id.swipe_target)
    RecyclerView swipeTarget;
    @BindView(R.id.swipeToLoadLayout)
    SwipeToLoadLayout swipeToLoadLayout;
    private NearScenicSpotAdapter nearScenicSpotAdapter;

    //当前页数
    private int page = 1;
    //总页数
    private int totalPage = 1;

    @Override
    protected int setContentLayout() {
        return R.layout.fragment_scenic_spot;
    }

    @Override
    protected void initView(View view) {
        swipeTarget.setLayoutManager(new LinearLayoutManager(getContext()));
        nearScenicSpotAdapter = new NearScenicSpotAdapter(getContext(), new ArrayList<HomeRes>());
        swipeTarget.setAdapter(nearScenicSpotAdapter);
        swipeToLoadLayout.setLoadMoreEnabled(false);
    }

    @Override
    protected void obtainData() {
        getLoadLayout().setLayoutState(LOADING);
        nearOrganizationList(true);
    }

    @Override
    protected void initEvent() {
        swipeToLoadLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                page++;
                nearOrganizationList(false);
            }
        });
        swipeToLoadLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh() {
                page = 1;
                nearOrganizationList(true);
            }
        });
        swipeTarget.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    if (!ViewCompat.canScrollVertically(recyclerView, 1)) {
                        swipeToLoadLayout.setLoadingMore(true);
                    }
                }
            }
        });
    }


    private void nearOrganizationList(final boolean isRefresh) {
        OkHttpUtils.get(String.format(nearOrganizationList, latitude, longitude, 20, page),
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
                                    final ArrayList<HomeRes> homeResList = new ArrayList<>();
                                    //获取总页数
                                    totalPage = object.getInteger("totalPage");

                                    //获取当前数据源集合
                                    JSONArray dataList = object.getJSONArray("dataList");
                                    int size = dataList.size();
                                    if (size > 0) {
                                        for (int i = 0; i < size; i++) {
                                            String string = dataList.getJSONObject(i).toString();
                                            HomeRes homeRes = JSON.parseObject(string, HomeRes.class);

                                            int distance = (int) LocationUtil.getInstance(getContext()).getDistance(homeRes.getLongitude(), homeRes.getLatitude(),
                                                    longitude, latitude);

                                            homeRes.setDistance(distance / 1000);
                                            homeResList.add(homeRes);
                                        }
                                        getActivity().runOnUiThread(new Runnable() {
                                            @Override
                                            public void run() {
                                                getLoadLayout().setLayoutState(SUCCESS);
                                            }
                                        });
                                    } else {
                                        getActivity().runOnUiThread(new Runnable() {
                                            @Override
                                            public void run() {
                                                getLoadLayout().setLayoutState(NO_DATA);
                                            }
                                        });
                                    }

                                    getActivity().runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            //是否为刷新操作
                                            if (isRefresh) {
                                                //替换适配器的数据源
                                                nearScenicSpotAdapter.replaceData(homeResList);
                                                //停止头部刷新动画
                                                swipeToLoadLayout.setRefreshing(false);
                                            } else {
                                                //添加适配器的数据源
                                                nearScenicSpotAdapter.insertItems(homeResList);
                                                //停止底部加载动画
                                                swipeToLoadLayout.setLoadingMore(false);
                                            }

                                            //如果总页数超过一条，开启加载监听
                                            if (totalPage > 1) {
                                                swipeToLoadLayout.setLoadMoreEnabled(true);
                                            }
                                            //如果当前访问的是最后一页，关闭加载监听
                                            if (totalPage == page) {
                                                swipeToLoadLayout.setLoadMoreEnabled(false);
                                            }
                                        }
                                    });

                                } else {
                                    ToastUtil.show(object.getString("message"));
                                    getActivity().runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            getLoadLayout().setLayoutState(SUCCESS);
                                        }
                                    });
                                }
                            }
                        }.start();
                    }

                    @Override
                    public void onFailure(Exception e) {
                        getLoadLayout().setLayoutState(State.FAILED);
                        ToastUtil.show(R.string.no_found_network);
                    }
                });
    }
}
