package com.tourismelves.view.fragment.near;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.amap.api.services.core.AMapException;
import com.amap.api.services.core.PoiItem;
import com.amap.api.services.poisearch.PoiResult;
import com.amap.api.services.poisearch.PoiSearch;
import com.tourismelves.R;
import com.tourismelves.utils.log.LogUtil;
import com.tourismelves.view.activity.NearScenicSpotActivity;
import com.tourismelves.view.adapter.NearScenicSpotAdapter;
import com.tourismelves.view.fragment.base.BaseFragment;
import com.tourismelves.view.widget.loadlayout.State;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

import static com.tourismelves.app.constant.CommentConstants.address;

/**
 * 购物
 */

public class ShoppingFragment extends BaseFragment implements PoiSearch.OnPoiSearchListener {
    @BindView(R.id.shop_recycler)
    RecyclerView shopRecycler;
    private int currentPage = 0;
    private PoiSearch.Query query;
    private PoiResult poiResult;
    private NearScenicSpotAdapter nearScenicSpotAdapter;
    private NearScenicSpotActivity nearScenicSpotActivity;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        nearScenicSpotActivity = (NearScenicSpotActivity) context;
    }

    @Override
    protected int setContentLayout() {
        return R.layout.fragment_shopping;
    }

    @Override
    protected void initView(View view) {
        shopRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        nearScenicSpotAdapter = new NearScenicSpotAdapter(getContext(), new ArrayList<PoiItem>());
        shopRecycler.setAdapter(nearScenicSpotAdapter);
    }

    /***
     * POI搜索变
     */
    @Override
    protected void obtainData() {
        getLoadLayout().setLayoutState(State.LOADING);
        // 第一个参数表示搜索字符串，第二个参数表示poi搜索类型，第三个参数表示poi搜索区域（空字符串代表全国）
        query = new PoiSearch.Query("", "购物服务", nearScenicSpotActivity.mAddress);
        //这里没有做分页加载了,默认给50条数据
        query.setPageSize(50);// 设置每页最多返回多少条poiitem
        query.setPageNum(currentPage);// 设置查第一页

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
