package com.tourismelves.view.fragment.near;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.amap.api.services.core.AMapException;
import com.amap.api.services.core.PoiItem;
import com.amap.api.services.poisearch.PoiResult;
import com.amap.api.services.poisearch.PoiSearch;
import com.tourismelves.R;
import com.tourismelves.utils.log.LogUtil;
import com.tourismelves.view.adapter.NearScenicSpotAdapter;
import com.tourismelves.view.fragment.base.BaseFragment;
import com.tourismelves.view.widget.loadlayout.State;
import com.tourismelves.view.widget.swipetoloadlayout.SwipeToLoadLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

import static com.tourismelves.app.constant.CommentConstants.address;

/**
 * 景区
 */

public class ScenicSpotFragment extends BaseFragment implements PoiSearch.OnPoiSearchListener {
    @BindView(R.id.swipe_target)
    RecyclerView swipeTarget;
    @BindView(R.id.swipeToLoadLayout)
    SwipeToLoadLayout swipeToLoadLayout;
    private NearScenicSpotAdapter nearScenicSpotAdapter;

    //当前页数
    private int page = 1;
    private PoiSearch.Query query;
    private PoiResult poiResult;

    @Override
    protected int setContentLayout() {
        return R.layout.fragment_scenic_spot;
    }

    @Override
    protected void initView(View view) {
        swipeTarget.setLayoutManager(new LinearLayoutManager(getContext()));
        nearScenicSpotAdapter = new NearScenicSpotAdapter(getContext(), new ArrayList<PoiItem>());
        swipeTarget.setAdapter(nearScenicSpotAdapter);
        swipeToLoadLayout.setLoadMoreEnabled(false);
        swipeToLoadLayout.setRefreshEnabled(false);
    }

    @Override
    protected void obtainData() {
        getLoadLayout().setLayoutState(State.LOADING);
        query = new PoiSearch.Query("", "风景名胜", address);
        //这里没有做分页加载了,默认给50条数据
        query.setPageSize(50);// 设置每页最多返回多少条poiitem
        query.setPageNum(page);// 设置查第一页

        PoiSearch poiSearch = new PoiSearch(getActivity(), query);
        poiSearch.setOnPoiSearchListener(this);
        poiSearch.searchPOIAsyn();
    }

    @Override
    protected void initEvent() {
    }

    /**
     * POI信息查询回调方法
     */
    @Override
    public void onPoiSearched(PoiResult result, int rCode) {
        if (rCode == AMapException.CODE_AMAP_SUCCESS) {
            if (result != null && result.getQuery() != null) {  // 搜索poi的结果
                if (result.getQuery().equals(query)) {  // 是否是同一条
                    poiResult = result;
                    List<PoiItem> poiItems = poiResult.getPois();// 取得第一页的poiitem数据，页数从数字0开始
                    nearScenicSpotAdapter.replaceData(poiItems);

                    if (poiItems.size() > 0) {
                        getLoadLayout().setLayoutState(State.SUCCESS);
                        return;
                    }
                }
            }
        }
        getLoadLayout().setLayoutState(State.NO_DATA);
        LogUtil.e("onPoiSearched: " + rCode);
    }

    /**
     * POI信息查询回调方法
     */
    @Override
    public void onPoiItemSearched(PoiItem poiItem, int i) {
    }
}
