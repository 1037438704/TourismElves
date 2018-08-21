package com.tourismelves.view.fragment;

import android.content.Context;
import android.location.Location;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.tourismelves.R;
import com.tourismelves.model.event.SelectCityBus;
import com.tourismelves.model.net.OkHttpUtils;
import com.tourismelves.model.res.HomeRes;
import com.tourismelves.utils.common.EventBusUtil;
import com.tourismelves.utils.common.ToastUtil;
import com.tourismelves.utils.system.LocationUtil;
import com.tourismelves.view.activity.MainActivity;
import com.tourismelves.view.adapter.ScenicSpotAdapter;
import com.tourismelves.view.fragment.base.BaseFragment;
import com.tourismelves.view.widget.loadlayout.State;
import com.tourismelves.view.widget.swipetoloadlayout.OnLoadMoreListener;
import com.tourismelves.view.widget.swipetoloadlayout.OnRefreshListener;
import com.tourismelves.view.widget.swipetoloadlayout.SwipeToLoadLayout;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;

import butterknife.BindView;

import static com.tourismelves.app.constant.UrlConstants.searchOrganizationOrArticle;
import static com.tourismelves.view.widget.loadlayout.State.SUCCESS;

/**
 * 景区
 */
public class ScenicSpotFragment extends BaseFragment {
    @BindView(R.id.swipe_target)
    RecyclerView swipeTarget;
    @BindView(R.id.swipeToLoadLayout)
    SwipeToLoadLayout swipeToLoadLayout;
    @BindView(R.id.layout_null_data)
    RelativeLayout layoutNullData;

    //当前页数
    private int page = 1;
    //总页数
    private int totalPage = 1;
    //获取当前经纬度
    private Location location;
    private ScenicSpotAdapter homeAdapter;
    private MainActivity mainActivity;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mainActivity = (MainActivity) context;
    }

    @Override
    protected int setContentLayout() {
        return R.layout.fragment_scenic_spot;
    }

    @Override
    protected void initView(View view) {
        EventBusUtil.register(this);
        swipeTarget.setLayoutManager(new LinearLayoutManager(getContext()));
        homeAdapter = new ScenicSpotAdapter(getContext(), new ArrayList<HomeRes>(), mainActivity.getBasePositioning());
        swipeTarget.setAdapter(homeAdapter);
        swipeToLoadLayout.setLoadMoreEnabled(false);
        //获取当前经纬度
        location = LocationUtil.getInstance(getContext()).showLocation();
    }

    @Override
    protected void obtainData() {
        getLoadLayout().setLayoutState(State.LOADING);
        searchOrganizationOrArticle(true);
    }

    @Override
    protected void initEvent() {
        //刷新数据
        swipeToLoadLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh() {
                page = 1;
                searchOrganizationOrArticle(true);
            }
        });
        //加载更多
        swipeToLoadLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                page++;
                searchOrganizationOrArticle(false);
            }
        });

        //滑到底部自动加载
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

    /**
     * 请求景区
     */
    private void searchOrganizationOrArticle(final boolean isRefresh) {
        OkHttpUtils.get(String.format(searchOrganizationOrArticle, mainActivity.getBasePositioning(), 0, 0, 20, page),
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

                                                if (homeResList.size() == 0) {
                                                    layoutNullData.setVisibility(View.VISIBLE);
                                                } else {
                                                    layoutNullData.setVisibility(View.GONE);
                                                }
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
                        getLoadLayout().setLayoutState(State.LOADING);
                        ToastUtil.show(R.string.no_found_network);
                    }
                });
    }


    @Override
    public void onDestroyView() {
        //移除事件
        EventBusUtil.unregister(this);
        super.onDestroyView();
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getSelectCityBus(final SelectCityBus selectCityBus) {
        mainActivity.setBasePositioning(selectCityBus.getCity());
        swipeToLoadLayout.setRefreshing(true);
    }
}
