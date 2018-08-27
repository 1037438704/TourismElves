package com.tourismelves.view.activity;

import android.support.v4.view.ViewCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.alibaba.fastjson.JSON;
import com.tourismelves.R;
import com.tourismelves.model.bean.AlreadyBoughtBean;
import com.tourismelves.model.net.OkHttpUtils;
import com.tourismelves.utils.common.ToastUtil;
import com.tourismelves.utils.system.SPUtils;
import com.tourismelves.view.activity.base.StateBaseActivity;
import com.tourismelves.view.adapter.AlreadyBoughtAdapter;
import com.tourismelves.view.widget.loadlayout.LoadLayout;
import com.tourismelves.view.widget.loadlayout.State;
import com.tourismelves.view.widget.swipetoloadlayout.OnLoadMoreListener;
import com.tourismelves.view.widget.swipetoloadlayout.OnRefreshListener;
import com.tourismelves.view.widget.swipetoloadlayout.SwipeToLoadLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

import static com.tourismelves.app.constant.UrlConstants.orderList;

/**
 * 已购
 */

public class AlreadyBoughtActivity extends StateBaseActivity {

    @BindView(R.id.swipe_target)
    RecyclerView swipeTarget;
    @BindView(R.id.swipeToLoadLayout)
    SwipeToLoadLayout swipeToLoadLayout;
    private AlreadyBoughtAdapter alreadyBoughtAdapter;
    private int page = 1;
    private int totalPage = 1;

    @Override
    protected void setContentLayout() {
        setContentView(R.layout.activity_already_bought);
    }

    @Override
    protected void initControls() {
        showStateLayout(1);
        setBaseTitle("已购");
        showStateRightView(2);
        swipeTarget.setLayoutManager(new LinearLayoutManager(getContext()));
        alreadyBoughtAdapter = new AlreadyBoughtAdapter(getContext(), new ArrayList<AlreadyBoughtBean.DataListBean>());
        swipeTarget.setAdapter(alreadyBoughtAdapter);
    }

    @Override
    protected void obtainData() {
        getLoadLayout().setLayoutState(State.LOADING);
        orderList(true);
    }

    @Override
    protected void initEvent() {
        getLoadLayout().setOnFailedListener(new LoadLayout.OnFailedListener() {
            @Override
            public void onFailed() {
                obtainData();
            }
        });
        swipeToLoadLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh() {
                page = 1;
                orderList(true);
            }
        });

        swipeToLoadLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                page++;
                orderList(false);
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

    private void orderList(final boolean isRefresh) {
        String userId = SPUtils.getInstance(getContext()).getString("putInt");
        OkHttpUtils.get(String.format(orderList, userId, page),
                new OkHttpUtils.ResultCallback<String>() {
                    @Override
                    public void onSuccess(String response) {
                        AlreadyBoughtBean alreadyBoughtBean = JSON.parseObject(response, AlreadyBoughtBean.class);

                        getLoadLayout().setLayoutState(State.SUCCESS);
                        if (alreadyBoughtBean.getCode() == 200) {
                            totalPage = alreadyBoughtBean.getTotalPage();
                            List<AlreadyBoughtBean.DataListBean> dataList = alreadyBoughtBean.getDataList();
                            int size = dataList.size();
                            if (size > 0) {
                                if (isRefresh) {
                                    alreadyBoughtAdapter.replaceData(dataList);
                                } else {
                                    alreadyBoughtAdapter.insertItems(dataList);
                                }
                            } else {
                                getLoadLayout().setLayoutState(State.NO_DATA);
                            }
                        } else {
                            ToastUtil.show(alreadyBoughtBean.getMessage());
                        }
                        if (isRefresh) {
                            swipeToLoadLayout.setRefreshing(false);
                        } else {
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

                    @Override
                    public void onFailure(Exception e) {
                        if (isRefresh) {
                            getLoadLayout().setLayoutState(State.FAILED);
                            swipeToLoadLayout.setRefreshing(false);
                        } else {
                            swipeToLoadLayout.setLoadingMore(false);
                        }
                        ToastUtil.show(R.string.no_found_network);
                    }
                });
    }

}
