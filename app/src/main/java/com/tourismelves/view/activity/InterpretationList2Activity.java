package com.tourismelves.view.activity;

import android.content.Intent;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.request.GetRequest;
import com.lzy.okserver.download.DownloadManager;
import com.tourismelves.R;
import com.tourismelves.model.bean.AttractionsBean;
import com.tourismelves.model.net.OkHttpUtils;
import com.tourismelves.model.res.ApkDownloadInfoRes;
import com.tourismelves.utils.common.ToastUtil;
import com.tourismelves.utils.file.RootFile;
import com.tourismelves.utils.system.SPUtils;
import com.tourismelves.view.activity.base.StateBaseActivity;
import com.tourismelves.view.adapter.InterpretationListAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

import static com.tourismelves.app.constant.UrlConstants.port;
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
    @BindView(R.id.interpretation_list2_down)
    AppCompatTextView interpretationList2Down;
    private InterpretationListAdapter interpretationListAdapter;
    private ArrayList<AttractionsBean> attractionsBeans;
    private String name;

    @Override
    protected void setContentLayout() {
        setContentView(R.layout.activity_interpretation_list2);
    }

    @Override
    protected void initControls() {
        showStateLayout(1);
        setBaseTitle("讲解列表");
        setBaseRightImage(R.mipmap.xiazai);
        setBaseRightTv("全选");
        interpretationList2Recycler.setLayoutManager(new LinearLayoutManager(getContext()));
        interpretationListAdapter = new InterpretationListAdapter(getContext(), new ArrayList<AttractionsBean>());
        interpretationList2Recycler.setAdapter(interpretationListAdapter);
    }

    @Override
    protected void obtainData() {
        attractionsBeans = getIntent().getParcelableArrayListExtra("attractionsBeans");
        name = getIntent().getStringExtra("name");
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
                showStateRightView(0);
                interpretationList2Down.setVisibility(View.VISIBLE);
                interpretationListAdapter.setEdit(true);
            }
        });
        setBaseRightTvListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getBaseRightTv().equals("全选")) {
                    setBaseRightTv("全不选");
                    interpretationListAdapter.selectAll(true);
                } else {
                    setBaseRightTv("全选");
                    interpretationListAdapter.selectAll(false);
                }
            }
        });

        interpretationList2Down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StringBuffer sb = new StringBuffer();
                for (AttractionsBean a : interpretationListAdapter.getDataList()) {
                    if (a.isSelect()) {
                        ApkDownloadInfoRes apkInfo = a.getApkDownloadInfoRes();
                        DownloadManager.getInstance().setTargetFolder(RootFile.getDownloadFiles().getPath() + "/" + name);
                        GetRequest request = OkGo.get(apkInfo.getUrl());
                        DownloadManager.getInstance().addTask(apkInfo.getFileName(), apkInfo.getDownKey(),
                                apkInfo, request, null, false);
                    }
                }
                startActivity(new Intent(getContext(), DownloadingActivity.class));
                showStateRightView(1);
                setBaseRightTv("全选");
                interpretationList2Down.setVisibility(View.GONE);
                interpretationListAdapter.setEdit(false);
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
                                        List<AttractionsBean.AudioListBean> audioList = attractionsBean.getAudioList();
                                        if (audioList != null && audioList.size() > 0) {
                                            ApkDownloadInfoRes apkDownloadInfoRes = new ApkDownloadInfoRes();
                                            String photoPath = "";
                                            if (attractionsBean.getPhotoList() != null && attractionsBean.getPhotoList().size() > 0)
                                                photoPath = port + attractionsBean.getPhotoList().get(0).getPhotoPath();

                                            apkDownloadInfoRes.setImageUrl(photoPath);
                                            apkDownloadInfoRes.setDownKey(attractionsBean.getName());
                                            apkDownloadInfoRes.setContent(attractionsBean.getDescription());
                                            apkDownloadInfoRes.setName(attractionsBean.getName());
                                            apkDownloadInfoRes.setUrl(port + audioList.get(0).getAudioPath());
                                            attractionsBean.setApkDownloadInfoRes(apkDownloadInfoRes);

                                        } else
                                            attractionsBean.setApkDownloadInfoRes(null);
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
