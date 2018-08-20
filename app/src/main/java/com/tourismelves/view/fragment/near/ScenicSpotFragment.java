package com.tourismelves.view.fragment.near;

import android.support.v4.view.ViewCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.tourismelves.R;
import com.tourismelves.model.res.HomeRes;
import com.tourismelves.view.adapter.NearScenicSpotAdapter;
import com.tourismelves.view.fragment.base.BaseFragment;
import com.tourismelves.view.widget.swipetoloadlayout.OnLoadMoreListener;
import com.tourismelves.view.widget.swipetoloadlayout.OnRefreshListener;
import com.tourismelves.view.widget.swipetoloadlayout.SwipeToLoadLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 景区
 */

public class ScenicSpotFragment extends BaseFragment {
    @BindView(R.id.swipe_target)
    RecyclerView swipeTarget;
    @BindView(R.id.swipeToLoadLayout)
    SwipeToLoadLayout swipeToLoadLayout;
    private NearScenicSpotAdapter nearScenicSpotAdapter;

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
        List<HomeRes> list = new ArrayList<>();

        for (int i = 0; i < 20; i++) {
            list.add(new HomeRes());
        }

        nearScenicSpotAdapter.replaceData(list);
    }

    @Override
    protected void initEvent() {
        swipeToLoadLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                swipeToLoadLayout.setLoadingMore(false);
            }
        });
        swipeToLoadLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeToLoadLayout.setRefreshing(false);
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
}
