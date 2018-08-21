package com.tourismelves.view.activity;

import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.tourismelves.R;
import com.tourismelves.model.net.OkHttpUtils;
import com.tourismelves.model.res.SelectCity2Res;
import com.tourismelves.model.res.SelectCityRes;
import com.tourismelves.utils.common.ToastUtil;
import com.tourismelves.utils.pinyin.PinyinUtils;
import com.tourismelves.utils.pinyin.SelectCityComparator;
import com.tourismelves.view.activity.base.StateBaseActivity;
import com.tourismelves.view.adapter.SelectCityAdapter;
import com.tourismelves.view.widget.SideBar;
import com.tourismelves.view.widget.loadlayout.LoadLayout;
import com.tourismelves.view.widget.loadlayout.State;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;

import static com.tourismelves.app.constant.UrlConstants.areaList;

/**
 * 选择城市
 */

public class SelectCityActivity extends StateBaseActivity {
    @BindView(R.id.select_city_list)
    RecyclerView selectCityList;
    @BindView(R.id.select_city_dialog)
    AppCompatTextView selectCityDialog;
    @BindView(R.id.select_city_sideBar)
    SideBar selectCitySideBar;

    private List<SelectCityRes> provinces;
    private List<SelectCityRes> citys;
    private List<SelectCity2Res> mData;

    private LinearLayoutManager manager;
    private SelectCityComparator selectCityComparator;
    private SelectCityAdapter selectCityAdapter;

    @Override
    protected void setContentLayout() {
        setContentView(R.layout.activity_select_city);
    }

    @Override
    protected void initControls() {
        showStateLayout(1);
        setBaseTitle("选择城市");
        showStateRightView(2);

        provinces = new ArrayList<>();
        citys = new ArrayList<>();
        mData = new ArrayList<>();

        manager = new LinearLayoutManager(getContext());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        selectCityList.setLayoutManager(manager);
        selectCityAdapter = new SelectCityAdapter(getContext(), new ArrayList<SelectCity2Res>());
        selectCityList.setAdapter(selectCityAdapter);

        selectCityComparator = new SelectCityComparator();
    }

    @Override
    protected void obtainData() {
        getLoadLayout().setLayoutState(State.LOADING);
        selectCitySideBar.setTextView(selectCityDialog);
        areaList();
    }

    @Override
    protected void initEvent() {
        getLoadLayout().setOnFailedListener(new LoadLayout.OnFailedListener() {
            @Override
            public void onFailed() {
                getLoadLayout().setLayoutState(State.LOADING);
                areaList();
            }
        });

        //设置右侧SideBar触摸监听
        selectCitySideBar.setOnTouchingLetterChangedListener(new SideBar.OnTouchingLetterChangedListener() {

            @Override
            public void onTouchingLetterChanged(String s) {
                //该字母首次出现的位置
                int position = selectCityAdapter.getPositionForSection(s.charAt(0));
                if (position != -1) {
                    manager.scrollToPositionWithOffset(position, 0);
                }

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
                                                selectCityAdapter.replaceData(mData);
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

}
