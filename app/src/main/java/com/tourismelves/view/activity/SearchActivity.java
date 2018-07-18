package com.tourismelves.view.activity;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.tourismelves.R;
import com.tourismelves.model.net.OkHttpUtils;
import com.tourismelves.model.res.SearchRes;
import com.tourismelves.view.activity.base.StateBaseActivity;

import java.util.ArrayList;
import java.util.List;

import static com.tourismelves.app.constant.UrlConstants.areaList;

/**
 * 搜索
 */

public class SearchActivity extends StateBaseActivity {
    @Override
    protected void setContentLayout() {
        setContentView(R.layout.activity_search);
    }

    @Override
    protected void initControls() {
    }

    @Override
    protected void obtainData() {
        areaList();
    }

    @Override
    protected void initEvent() {

    }

    /**
     * 地区列表
     */
    private void areaList() {
        OkHttpUtils.get(areaList,
                new OkHttpUtils.ResultCallback<String>() {
                    @Override
                    public void onSuccess(String response) {
                        JSONObject object = JSON.parseObject(response);
                        //获取请求结果的code码
                        Integer code = object.getInteger("code");
                        if (code == 200) {
                            List<SearchRes> searchResList = new ArrayList<>();
                            JSONArray dataList = object.getJSONArray("dataList");
                            int size = dataList.size();
                            for (int i = 0; i < size; i++) {
                                String string = dataList.getJSONObject(i).toString();
                                SearchRes searchRes = JSON.parseObject(string, SearchRes.class);
                                searchResList.add(searchRes);
                            }


                        }
                    }

                    @Override
                    public void onFailure(Exception e) {

                    }
                });
    }
}
