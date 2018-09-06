package com.tourismelves.view.fragment;


import android.support.v4.view.ViewCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.tourismelves.R;
import com.tourismelves.model.net.OkHttpUtils;
import com.tourismelves.model.res.BannerRes;
import com.tourismelves.model.res.HomeRes;
import com.tourismelves.utils.common.ToastUtil;
import com.tourismelves.utils.system.LocationUtil;
import com.tourismelves.view.adapter.HomeAdapter;
import com.tourismelves.view.fragment.base.BaseFragment;
import com.tourismelves.view.widget.loadlayout.State;
import com.tourismelves.view.widget.swipetoloadlayout.OnLoadMoreListener;
import com.tourismelves.view.widget.swipetoloadlayout.OnRefreshListener;
import com.tourismelves.view.widget.swipetoloadlayout.SwipeToLoadLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

import static com.tourismelves.app.constant.CommentConstants.latitude;
import static com.tourismelves.app.constant.CommentConstants.longitude;
import static com.tourismelves.app.constant.UrlConstants.organizationList;
import static com.tourismelves.app.constant.UrlConstants.posterList;
import static com.tourismelves.view.widget.loadlayout.State.FAILED;
import static com.tourismelves.view.widget.loadlayout.State.SUCCESS;

/**
 * 首页
 */
public class HomeFragment extends BaseFragment {

    @BindView(R.id.swipe_target)
    RecyclerView homeRecycler;
    @BindView(R.id.swipeToLoadLayout)
    SwipeToLoadLayout swipeToLoadLayout;

    //适配器
    private HomeAdapter homeAdapter;
    //当前页数
    private int page = 1;
    //总页数
    private int totalPage = 1;
    private List<Object> homes;

    @Override
    protected int setContentLayout() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initView(View view) {
        homes = new ArrayList<>();
        homeRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        homeAdapter = new HomeAdapter(getContext(), homes);
        homeRecycler.setAdapter(homeAdapter);

        //禁止开启加载更多监听
        swipeToLoadLayout.setLoadMoreEnabled(false);

    }

    @Override
    protected void obtainData() {
        getLoadLayout().setLayoutState(State.LOADING);
        posterList();
    }

    @Override
    protected void initEvent() {
        //刷新数据
        swipeToLoadLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh() {
                page = 1;
                posterList();
            }
        });
        //加载更多
        swipeToLoadLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                page++;
                organizationList(false);
            }
        });

        homeRecycler.setOnScrollListener(new RecyclerView.OnScrollListener() {
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

    /**
     * 请求热门景点列表
     */
    private void organizationList(final boolean isRefresh) {
        //网络请求
        OkHttpUtils.get(String.format(organizationList, 20, page),
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
                                    final ArrayList<Object> homeResList = new ArrayList<>();
                                    //获取总页数
                                    totalPage = object.getInteger("totalPage");

                                    //获取当前数据源集合
                                    JSONArray dataList = object.getJSONArray("dataList");
                                    int size = dataList.size();
                                    for (int i = 0; i < size; i++) {
                                        String string = dataList.getJSONObject(i).toString();
                                        HomeRes homeRes = JSON.parseObject(string, HomeRes.class);

                                        String distance = LocationUtil.getInstance(getContext()).getDistance(homeRes.getLongitude(), homeRes.getLatitude(), longitude, latitude);

                                        homeRes.setDistance(distance);
                                        homeResList.add(homeRes);
                                        homes.add(homeRes);
                                    }

                                    getActivity().runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            //是否为刷新操作
                                            if (isRefresh) {
                                                //替换适配器的数据源
                                                homeAdapter.replaceData(homes);
                                                //停止头部刷新动画
                                                swipeToLoadLayout.setRefreshing(false);
                                            } else {
                                                //添加适配器的数据源
                                                homeAdapter.insertItems(homeResList);
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
                                }

                                getActivity().runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        getLoadLayout().setLayoutState(SUCCESS);
                                    }
                                });
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

    /**
     * 请求轮播数据
     */
    private void posterList() {
        homes.clear();
        OkHttpUtils.get(String.format(posterList, 1),
                new OkHttpUtils.ResultCallback<String>() {
                    @Override
                    public void onSuccess(String response) {
                        JSONObject object = JSON.parseObject(response);
                        //获取请求结果的code码
                        Integer code = object.getInteger("code");
                        if (code == 200) {
                            List<BannerRes.BannerBean> bannerResList = new ArrayList<>();
                            JSONArray dataList = object.getJSONArray("dataList");
                            int size = dataList.size();
                            for (int i = 0; i < size; i++) {
                                String string = dataList.getJSONObject(i).toString();
                                BannerRes.BannerBean bannerRes = JSON.parseObject(string, BannerRes.BannerBean.class);
                                bannerResList.add(bannerRes);
                            }
                            BannerRes bannerRes = new BannerRes();
                            bannerRes.setBannerBeans(bannerResList);
                            homes.add(bannerRes);
                        }
                        organizationList(true);
                    }

                    @Override
                    public void onFailure(Exception e) {
                        ToastUtil.show(R.string.no_found_network);
                        getLoadLayout().setLayoutState(FAILED);
                    }
                });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
