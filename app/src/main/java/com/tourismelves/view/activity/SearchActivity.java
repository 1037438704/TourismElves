package com.tourismelves.view.activity;

import android.content.Intent;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.widget.RelativeLayout;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.tourismelves.R;
import com.tourismelves.model.event.SelectCityBus;
import com.tourismelves.model.net.OkHttpUtils;
import com.tourismelves.model.res.SearchedRes;
import com.tourismelves.model.res.SelectCity2Res;
import com.tourismelves.model.res.SelectCityRes;
import com.tourismelves.utils.common.EventBusUtil;
import com.tourismelves.utils.common.ToastUtil;
import com.tourismelves.utils.pinyin.PinyinUtils;
import com.tourismelves.utils.pinyin.SelectCityComparator;
import com.tourismelves.utils.system.PreferenceUtil;
import com.tourismelves.view.activity.base.StateBaseActivity;
import com.tourismelves.view.adapter.OnTextWatcherAdapter;
import com.tourismelves.view.adapter.SearchAdapter;
import com.tourismelves.view.adapter.SearchedAdapter;
import com.tourismelves.view.widget.SideBar;
import com.tourismelves.view.widget.loadlayout.State;
import com.tourismelves.view.widget.swipetoloadlayout.OnRefreshListener;
import com.tourismelves.view.widget.swipetoloadlayout.SwipeToLoadLayout;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

import static com.tourismelves.app.constant.CommentConstants.latitude;
import static com.tourismelves.app.constant.CommentConstants.longitude;
import static com.tourismelves.app.constant.UrlConstants.areaList;
import static com.tourismelves.app.constant.UrlConstants.searchOrganizationOrArticle;
import static com.tourismelves.view.activity.MainActivity.city;

/**
 * 搜索
 */

public class SearchActivity extends StateBaseActivity {
    @BindView(R.id.search_edit)
    AppCompatEditText searchEdit;
    @BindView(R.id.search_recycler)
    RecyclerView searchRecycler;
    @BindView(R.id.search_positioning)
    AppCompatTextView searchPositioning;
    @BindView(R.id.search_city_sideBar)
    SideBar searchCitySideBar;
    @BindView(R.id.search_city_dialog)
    AppCompatTextView searchCityDialog;
    @BindView(R.id.search_rl)
    RelativeLayout searchRl;
    @BindView(R.id.swipe_target)
    RecyclerView searchedRecycler;
    @BindView(R.id.swipeToLoadLayout)
    SwipeToLoadLayout swipeToLoadLayout;

    private String SP_SEARCH_HISTORY = "searchHistory";

    private List<SelectCityRes> provinces;
    private List<SelectCityRes> citys;
    private List<Object> mData;

    private SearchAdapter searchAdapter;
    private SelectCityComparator selectCityComparator;
    private LinearLayoutManager manager;
    private ArrayList<SearchedRes> searchedResArrayList;
    private SearchedAdapter searchedAdapter;


    @Override
    protected void setContentLayout() {
        setContentView(R.layout.activity_search);
    }

    @Override
    protected void initControls() {
        EventBusUtil.register(this);
        showStateLayout(-1);
        if (!city.equals("")) {
            searchPositioning.setText(city);
        }

        provinces = new ArrayList<>();
        citys = new ArrayList<>();
        mData = new ArrayList<>();
        selectCityComparator = new SelectCityComparator();

        swipeToLoadLayout.setLoadMoreEnabled(false);
        searchedRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        searchedResArrayList = new ArrayList<>();
        searchedAdapter = new SearchedAdapter(getContext(), searchedResArrayList);
        searchedRecycler.setAdapter(searchedAdapter);

        manager = new LinearLayoutManager(getContext());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        searchRecycler.setLayoutManager(manager);

        searchAdapter = new SearchAdapter(getContext(), new ArrayList<Object>());
        searchRecycler.setAdapter(searchAdapter);

        searchCitySideBar.setTextView(searchCityDialog);
    }

    @Override
    protected void obtainData() {
        searchAdapter.insertItem(new Object());
        searchAdapter.historyData.clear();
        String strHistory = PreferenceUtil.getString(getContext(), SP_SEARCH_HISTORY, "");
        if (!strHistory.equals("")) {
            String[] split = strHistory.split(",");
            searchAdapter.historyData.addAll(Arrays.asList(split));
        }
        areaList();
    }

    @Override
    protected void initEvent() {

        searchEdit.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_ENTER &&
                        event.getAction() == KeyEvent.ACTION_UP) {
                    sortType = 0;
                    searchOrganizationOrArticle(searchEdit.getText().toString());
                    addSP(searchEdit.getText().toString());

                }
                return false;
            }
        });
        searchEdit.addTextChangedListener(new OnTextWatcherAdapter() {
            @Override
            public void onTextChanged(CharSequence charSequence, int length, int i1, int i2) {
                if (charSequence.length() > 0) {
//                    searchOrganizationOrArticle(charSequence.toString());
                } else {
                    searchRl.setVisibility(View.VISIBLE);
                    swipeToLoadLayout.setVisibility(View.GONE);

                    searchAdapter.historyData.clear();
                    String strHistory = PreferenceUtil.getString(getContext(), SP_SEARCH_HISTORY, "");
                    if (!strHistory.equals("")) {
                        String[] split = strHistory.split(",");
                        searchAdapter.historyData.addAll(Arrays.asList(split));
                        searchAdapter.notifyItemChanged(0);
                    }
                }
            }
        });

        searchAdapter.setOnSearchListener(new SearchAdapter.OnSearchListener() {
            @Override
            public void onSearch(String search) {
                searchEdit.setText(search);
                searchEdit.setSelection(search.length());
            }
        });

        //设置右侧SideBar触摸监听
        searchCitySideBar.setOnTouchingLetterChangedListener(new SideBar.OnTouchingLetterChangedListener() {

            @Override
            public void onTouchingLetterChanged(String s) {
                //该字母首次出现的位置
                int position = searchAdapter.getPositionForSection(s.charAt(0));
                if (position != -1) {
                    manager.scrollToPositionWithOffset(position, 0);
                }

            }
        });
        swipeToLoadLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (sortType == 0) {
                    sortType = 1;
                } else {
                    sortType = 0;
                }
                searchOrganizationOrArticle(searchEdit.getText().toString());
            }
        });
    }

    @OnClick({R.id.search_cancel, R.id.search_positioning})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.search_cancel:
                finish();
                break;
            case R.id.search_positioning:
                startActivity(new Intent(this, SelectCityActivity.class));
                break;
        }
    }

    private int sortType = 0;

    /**
     * 请求景区
     */
    private void searchOrganizationOrArticle(final String strAddress) {
        searchedResArrayList.clear();
        searchRl.setVisibility(View.GONE);
        swipeToLoadLayout.setVisibility(View.VISIBLE);
        final boolean[] isLoaded = {false, false};

        String latlng = "";
        if (sortType == 0) {
            latlng = "&latitude=" + latitude + "&longitude=" + longitude;
        }

        OkHttpUtils.get(String.format(searchOrganizationOrArticle, strAddress, 0, sortType, 20, 1) + latlng,
                new OkHttpUtils.ResultCallback<String>() {
                    @Override
                    public void onSuccess(final String response) {
                        isLoaded[0] = true;
                        SearchedRes searchedRes = JSON.parseObject(response, SearchedRes.class);
                        if (searchedRes.getCode() == 200) {
                            List<SearchedRes.DataListBean> dataList = searchedRes.getDataList();
                            if (dataList.size() > 0) {
                                searchedRes.setName("景区");
                                searchedResArrayList.add(searchedRes);
                            }
                        } else {
                            ToastUtil.show(searchedRes.getMessage());
                        }
                        if (isLoaded[0] && isLoaded[1]) {
                            searchedAdapter.replaceData(searchedResArrayList);
                            swipeToLoadLayout.setRefreshing(false);
                        }
                    }

                    @Override
                    public void onFailure(Exception e) {
                        isLoaded[0] = true;
                        if (isLoaded[1]) {
                            swipeToLoadLayout.setRefreshing(false);
                        }
                        ToastUtil.show(R.string.no_found_network);
                    }
                });
        OkHttpUtils.get(String.format(searchOrganizationOrArticle, strAddress, 1, sortType, 20, 1) + latlng,
                new OkHttpUtils.ResultCallback<String>() {
                    @Override
                    public void onSuccess(final String response) {
                        isLoaded[1] = true;
                        SearchedRes searchedRes = JSON.parseObject(response, SearchedRes.class);
                        if (searchedRes.getCode() == 200) {
                            List<SearchedRes.DataListBean> dataList = searchedRes.getDataList();
                            if (dataList.size() > 0) {
                                searchedRes.setName("精灵说");
                                searchedResArrayList.add(searchedRes);
                            }
                        } else {
                            ToastUtil.show(searchedRes.getMessage());
                        }
                        if (isLoaded[0] && isLoaded[1]) {
                            searchedAdapter.replaceData(searchedResArrayList);
                            swipeToLoadLayout.setRefreshing(false);
                        }
                    }

                    @Override
                    public void onFailure(Exception e) {
                        isLoaded[1] = true;
                        if (isLoaded[0]) {
                            swipeToLoadLayout.setRefreshing(false);
                        }
                        ToastUtil.show(R.string.no_found_network);
                    }
                });
    }


    /**
     * 地区列表
     */
    private void areaList() {
        try {
            OkHttpUtils.get(areaList,
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
                                        JSONArray dataList = object.getJSONArray("dataList");
                                        int size = dataList.size();
                                        for (int i = 0; i < size; i++) {
                                            JSONObject jsonObject = dataList.getJSONObject(i);
                                            int parent = jsonObject.getIntValue("parent");
                                            JSONObject parentArea = jsonObject.getJSONObject("parentArea");
                                            if (parent != 0) {
                                                if (parentArea != null) {
                                                    int parent2 = parentArea.getIntValue("parent");
                                                    if (parent2 != 0) { //省、市
                                                        String province = parentArea.getString("name");
                                                        String pinyinProvince = PinyinUtils.getPingYin(province).substring(0, 1).toUpperCase();
                                                        provinces.add(new SelectCityRes(province, pinyinProvince));

                                                        String city = jsonObject.getString("name");
                                                        String pinyinCity = PinyinUtils.getPingYin(city).substring(0, 1).toUpperCase();
                                                        citys.add(new SelectCityRes(city, pinyinCity));
                                                    } else {//省
                                                        String province = jsonObject.getString("name");
                                                        String pinyinProvince = PinyinUtils.getPingYin(province).substring(0, 1).toUpperCase();
                                                        provinces.add(new SelectCityRes(province, pinyinProvince));
                                                    }
                                                }
                                            }
                                        }
                                        provinces.addAll(citys);
                                        Collections.sort(provinces, selectCityComparator);
                                        SelectCity2Res selectCity2Res = new SelectCity2Res();
                                        List<String> citys = new ArrayList<>();
                                        for (int i = 0; i < provinces.size(); i++) {
                                            if (i == 0) {
                                                selectCity2Res.setRemark(provinces.get(i).getRemark());
                                                citys.add(provinces.get(i).getName());
                                            } else {
                                                String remarkLast = provinces.get(i - 1).getRemark();
                                                String remarkNew = provinces.get(i).getRemark();
                                                if (remarkLast.equals(remarkNew)) {
                                                    citys.add(provinces.get(i).getName());
                                                } else {
                                                    selectCity2Res.setCitys(citys);
                                                    mData.add(selectCity2Res);
                                                    selectCity2Res = new SelectCity2Res();
                                                    citys = new ArrayList<>();
                                                    selectCity2Res.setRemark(provinces.get(i).getRemark());
                                                    citys.add(provinces.get(i).getName());
                                                }
                                            }
                                            if (i == provinces.size() - 1) {
                                                selectCity2Res.setCitys(citys);
                                                mData.add(selectCity2Res);
                                            }

                                        }

                                        runOnUiThread(new Runnable() {
                                            @Override
                                            public void run() {
                                                searchAdapter.replaceData(mData);
                                            }
                                        });
                                    }

                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            getLoadLayout().setLayoutState(com.tourismelves.view.widget.loadlayout.State.SUCCESS);
                                        }
                                    });
                                }
                            }.start();
                        }

                        @Override
                        public void onFailure(Exception e) {
                            ToastUtil.show(R.string.no_found_network);
                            getLoadLayout().setLayoutState(State.FAILED);
                        }
                    });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 添加到历史搜索
     */
    private boolean addSP(String value) {
        StringBuilder string = new StringBuilder(PreferenceUtil.getString(getContext(), SP_SEARCH_HISTORY, ""));
        if (!string.toString().contains(value)) {
            String[] split = string.toString().split(",");
            if (split.length >= 10) {
                string = new StringBuilder();
                string.append(value);
                for (int i = 0; i < 9; i++) {
                    string.append(",").append(split[i]);
                }
                PreferenceUtil.putString(getContext(), SP_SEARCH_HISTORY, string.toString());
            } else if (split.length == 0) {
                PreferenceUtil.putString(getContext(), SP_SEARCH_HISTORY, value);
            } else {
                PreferenceUtil.putString(getContext(), SP_SEARCH_HISTORY, value + "," + string.toString());
            }
            return true;
        }

        return false;
    }

    @Override
    public void onDestroy() {
        //移除事件
        EventBusUtil.unregister(this);
        super.onDestroy();
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getSelectCityBus(final SelectCityBus selectCityBus) {
        searchPositioning.setText(selectCityBus.getCity());
    }
}
