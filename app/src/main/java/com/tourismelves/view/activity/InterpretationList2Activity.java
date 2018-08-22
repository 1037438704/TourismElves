package com.tourismelves.view.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.tourismelves.R;
import com.tourismelves.model.bean.AttractionsBean;
import com.tourismelves.model.net.OkHttpUtils;
import com.tourismelves.utils.common.ToastUtil;
import com.tourismelves.utils.system.SPUtils;
import com.tourismelves.view.activity.base.StateBaseActivity;
import com.tourismelves.view.adapter.InterpretationListAdapter;

import java.util.ArrayList;

import butterknife.BindView;

import static com.tourismelves.app.constant.UrlConstants.sceneryList;
import static com.tourismelves.view.widget.loadlayout.State.FAILED;
import static com.tourismelves.view.widget.loadlayout.State.LOADING;
import static com.tourismelves.view.widget.loadlayout.State.NO_DATA;
import static com.tourismelves.view.widget.loadlayout.State.SUCCESS;

/**
 * 讲解列表 详情
 */
public class InterpretationList2Activity extends StateBaseActivity {
    @BindView(R.id.interpretation_list2_recycler)
    RecyclerView interpretationList2Recycler;
    private InterpretationListAdapter interpretationListAdapter;
    private ArrayList<AttractionsBean> attractionsBeans;

    @Override
    protected void setContentLayout() {
        setContentView(R.layout.activity_interpretation_list2);
    }

    @Override
    protected void initControls() {
        showStateLayout(1);
        setBaseTitle("讲解列表");
        setBaseRightImage(R.mipmap.xiazai);
        interpretationList2Recycler.setLayoutManager(new LinearLayoutManager(getContext()));
        interpretationListAdapter = new InterpretationListAdapter(getContext(), new ArrayList<AttractionsBean>());
        interpretationList2Recycler.setAdapter(interpretationListAdapter);
    }

    @Override
    protected void obtainData() {
        attractionsBeans = getIntent().getParcelableArrayListExtra("attractionsBeans");
        if (attractionsBeans != null) {
            interpretationListAdapter.replaceData(attractionsBeans);
        } else {
            getLoadLayout().setLayoutState(LOADING);
            int ordId = getIntent().getIntExtra("ordId", 0);
            sceneryList(ordId + "");
        }
    }

    @Override
    protected void initEvent() {
        setBaseRightListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    /**
     * 景点列表
     */
    private void sceneryList(String ordId) {
        attractionsBeans = new ArrayList<>();
        String userId = SPUtils.getInstance(getContext()).getString("putInt");
        OkHttpUtils.get(String.format(sceneryList, ordId, userId),
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
                                    //获取当前数据源集合
                                    JSONArray dataList = object.getJSONArray("dataList");
                                    int size = dataList.size();
                                    for (int i = 0; i < size; i++) {
                                        String string = dataList.getJSONObject(i).toString();
                                        AttractionsBean attractionsBean = JSON.parseObject(string, AttractionsBean.class);
                                        attractionsBeans.add(attractionsBean);
                                    }

                                    if (size > 0) {
                                        runOnUiThread(new Runnable() {
                                            @Override
                                            public void run() {
                                                getLoadLayout().setLayoutState(SUCCESS);
                                            }
                                        });
                                    } else {
                                        runOnUiThread(new Runnable() {
                                            @Override
                                            public void run() {
                                                getLoadLayout().setLayoutState(NO_DATA);
                                            }
                                        });
                                    }
                                } else {
                                    ToastUtil.show(object.getString("message"));
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            getLoadLayout().setLayoutState(SUCCESS);
                                        }
                                    });
                                }

                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        interpretationListAdapter.replaceData(attractionsBeans);
                                    }
                                });
                            }
                        }.start();
                    }

                    @Override
                    public void onFailure(Exception e) {
                        getLoadLayout().setLayoutState(FAILED);
                        ToastUtil.show(R.string.no_found_network);
                    }
                });
    }
}
