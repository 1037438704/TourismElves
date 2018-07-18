package com.tourismelves.view.fragment;


import android.content.Intent;
import android.location.Location;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.tourismelves.R;
import com.tourismelves.model.net.OkHttpUtils;
import com.tourismelves.model.res.BannerRes;
import com.tourismelves.model.res.HomeRes;
import com.tourismelves.utils.common.ToastUtil;
import com.tourismelves.utils.system.LocationUtil;
import com.tourismelves.utils.system.ResolutionUtil;
import com.tourismelves.view.activity.NearScenicSpotActivity;
import com.tourismelves.view.adapter.HomeAdapter;
import com.tourismelves.view.adapter.ImageAdapter;
import com.tourismelves.view.adapter.OnPageChangeAdapter;
import com.tourismelves.view.fragment.base.BaseFragment;
import com.tourismelves.view.widget.custom.CustomRecyclerView;
import com.tourismelves.view.widget.custom.CustomScrollView;
import com.tourismelves.view.widget.loadlayout.State;
import com.tourismelves.view.widget.swipetoloadlayout.OnLoadMoreListener;
import com.tourismelves.view.widget.swipetoloadlayout.OnRefreshListener;
import com.tourismelves.view.widget.swipetoloadlayout.SwipeToLoadLayout;
import com.tourismelves.view.widget.viewpager.AutoPlayViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

import static com.tourismelves.app.constant.UrlConstants.organizationList;
import static com.tourismelves.app.constant.UrlConstants.posterList;
import static com.tourismelves.view.widget.loadlayout.State.SUCCESS;

public class HomeFragment extends BaseFragment {


    @BindView(R.id.home_viewpager)
    AutoPlayViewPager homeViewpager;
    @BindView(R.id.home_indicator)
    LinearLayout homeIndicator;
    @BindView(R.id.home_recycler)
    CustomRecyclerView homeRecycler;
    @BindView(R.id.swipe_target)
    CustomScrollView swipeTarget;
    @BindView(R.id.swipeToLoadLayout)
    SwipeToLoadLayout swipeToLoadLayout;

    //适配器
    private HomeAdapter homeAdapter;
    //当前页数
    private int page = 1;
    //总页数
    private int totalPage = 1;
    private ImageAdapter imageAdapter;
    //存储指示器
    private List<ImageView> indicators;
    private Location location;

    @Override
    protected int setContentLayout() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initView(View view) {
        indicators = new ArrayList<>();
        homeRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        homeAdapter = new HomeAdapter(getContext(), new ArrayList<HomeRes>());
        homeRecycler.setAdapter(homeAdapter);
        //防止Scroll与Recycler滑动冲突出现卡顿
        homeRecycler.setNestedScrollingEnabled(false);

        //禁止开启加载更多监听
        swipeToLoadLayout.setLoadMoreEnabled(false);
        //获取当前经纬度
        location = LocationUtil.getInstance(getContext()).showLocation();
    }

    @Override
    protected void obtainData() {
        getLoadLayout().setLayoutState(State.LOADING);
        organizationList(true);
        posterList();
    }

    @Override
    protected void initEvent() {
        //刷新数据
        swipeToLoadLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh() {
                page = 1;
                organizationList(true);
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

        //viewpager滑动监听，改变指示器
        homeViewpager.setOnPageChangeListener(new OnPageChangeAdapter() {
            @Override
            public void onPageSelected(int position) {
                if (indicators != null && indicators.size() > 0) {
                    for (int i = 0; i < indicators.size(); i++) {
                        indicators.get(i).setSelected(false);
                    }
                    indicators.get(position % indicators.size()).setSelected(true);
                }
            }
        });

        //添加ScrollView是否滑到底部监听
        swipeTarget.getViewTreeObserver().addOnScrollChangedListener(mOnScrollChangedListener);
    }

    /**
     * 监听ScrollView是否滑到底部 。滑倒底部自动加载
     */
    ViewTreeObserver.OnScrollChangedListener mOnScrollChangedListener = new ViewTreeObserver.OnScrollChangedListener() {
        @Override
        public void onScrollChanged() {
            if (swipeTarget.getChildAt(0).getHeight() < swipeTarget.getScrollY() + swipeTarget.getHeight() &&
                    !ViewCompat.canScrollVertically(swipeTarget, 1)) {
                swipeToLoadLayout.setLoadingMore(true);
            }
        }
    };

    @OnClick({R.id.home_near_scenic_spot, R.id.home_my_scenic_spot, R.id.home_activation_code})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.home_near_scenic_spot://附近景点
                startActivity(new Intent(getContext(), NearScenicSpotActivity.class));
                break;
            case R.id.home_my_scenic_spot://我的景点
                break;
            case R.id.home_activation_code://激活码
                break;
        }
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
                                    final ArrayList<HomeRes> homeResList = new ArrayList<>();
                                    //获取总页数
                                    totalPage = object.getInteger("totalPage");

                                    //如果总页数超过一条，开启加载监听
                                    if (totalPage > 1) {
                                        swipeToLoadLayout.setLoadMoreEnabled(true);
                                    }
                                    //如果当前访问的是最后一页，关闭加载监听
                                    if (totalPage == page) {
                                        swipeToLoadLayout.setLoadMoreEnabled(false);
                                    }

                                    //获取当前数据源集合
                                    JSONArray dataList = object.getJSONArray("dataList");
                                    int size = dataList.size();
                                    for (int i = 0; i < size; i++) {
                                        String string = dataList.getJSONObject(i).toString();
                                        HomeRes homeRes = JSON.parseObject(string, HomeRes.class);

                                        int distance = 0;
                                        if (location != null)
                                            distance = (int) LocationUtil.getInstance(getContext()).getDistance(homeRes.getLongitude(), homeRes.getLatitude(),
                                                    location.getLongitude(), location.getLatitude());

                                        homeRes.setDistance(distance / 1000);
                                        homeResList.add(homeRes);
                                    }

                                    getActivity().runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            //是否为刷新操作
                                            if (isRefresh) {
                                                //替换适配器的数据源
                                                homeAdapter.replaceData(homeResList);
                                                //停止头部刷新动画
                                                swipeToLoadLayout.setRefreshing(false);
                                            } else {
                                                //添加适配器的数据源
                                                homeAdapter.insertItems(homeResList);
                                                //停止底部加载动画
                                                swipeToLoadLayout.setLoadingMore(false);
                                            }
                                        }
                                    });
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
        OkHttpUtils.get(String.format(posterList, 1),
                new OkHttpUtils.ResultCallback<String>() {
                    @Override
                    public void onSuccess(String response) {
                        JSONObject object = JSON.parseObject(response);
                        //获取请求结果的code码
                        Integer code = object.getInteger("code");
                        if (code == 200) {
                            List<BannerRes> bannerResList = new ArrayList<>();
                            JSONArray dataList = object.getJSONArray("dataList");
                            int size = dataList.size();
                            for (int i = 0; i < size; i++) {
                                String string = dataList.getJSONObject(i).toString();
                                BannerRes bannerRes = JSON.parseObject(string, BannerRes.class);
                                bannerResList.add(bannerRes);
                            }

                            setBanner(bannerResList);
                        }
                        getLoadLayout().setLayoutState(SUCCESS);
                    }

                    @Override
                    public void onFailure(Exception e) {
                        ToastUtil.show(R.string.no_found_network);
                    }
                });
    }

    /**
     * 设置banner适配器
     */
    private void setBanner(List<BannerRes> bannerResList) {
        int size = bannerResList.size();
        if (size == 0) {
            homeViewpager.setVisibility(View.GONE);
            homeIndicator.setVisibility(View.GONE);
            return;
        }
        homeViewpager.setVisibility(View.VISIBLE);
        if (imageAdapter == null) {
            imageAdapter = new ImageAdapter(getActivity(), bannerResList);
            homeViewpager.setAdapter(imageAdapter);
        } else {
            imageAdapter.replaceData(bannerResList);
        }

        if (size == 1) {
            homeIndicator.setVisibility(View.GONE);
            homeViewpager.setScroll(false);
        } else {
            homeIndicator.setVisibility(View.VISIBLE);
            addIndicatorsImage(size);
            homeViewpager.setScroll(true);
            homeViewpager.start(); // 开始轮播
            indicators.get(0).setSelected(true);
            homeViewpager.setCurrentItem(imageAdapter.getCount() / size);
        }

        imageAdapter.setOnItemClickListener(new ImageAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BannerRes bannerRes) {

            }
        });
    }

    /**
     * 动态添加banner指示器
     */
    private void addIndicatorsImage(int size) {
        indicators.clear();
        homeIndicator.removeAllViews();
        for (int i = 0; i < size; i++) {
            AppCompatImageView imageView = new AppCompatImageView(getContext());
            imageView.setMinimumHeight(ResolutionUtil.getInstance(getContext()).px2dp2pxWidth(15));
            imageView.setMinimumWidth(ResolutionUtil.getInstance(getContext()).px2dp2pxWidth(15));
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            params.setMargins(ResolutionUtil.getInstance(getContext()).px2dp2pxWidth(7), 0,
                    ResolutionUtil.getInstance(getContext()).px2dp2pxWidth(7), 0);
            imageView.setLayoutParams(params);  //设置图片宽高
            imageView.setImageResource(R.drawable.banner_indicators_checked); //图片资源
            imageView.setSelected(false);
            indicators.add(imageView);
            homeIndicator.addView(imageView); //动态添加图片
        }
    }

    @Override
    public void onDestroyView() {
        //移除事件
        swipeTarget.getViewTreeObserver().removeOnScrollChangedListener(mOnScrollChangedListener);
        super.onDestroyView();
    }

}
