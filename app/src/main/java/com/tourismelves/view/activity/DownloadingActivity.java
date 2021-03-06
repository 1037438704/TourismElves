package com.tourismelves.view.activity;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.lzy.okserver.download.DownloadInfo;
import com.lzy.okserver.download.DownloadManager;
import com.tourismelves.R;
import com.tourismelves.model.res.ApkDownloadInfoRes;
import com.tourismelves.utils.log.LogUtil;
import com.tourismelves.view.activity.base.StateBaseActivity;
import com.tourismelves.view.adapter.DownloadingAdapter;
import com.tourismelves.view.widget.recycler.ItemRemoveRecyclerView;
import com.tourismelves.view.widget.recycler.OnItemClickListener;
import com.tourismelves.view.widget.swipetoloadlayout.OnRefreshListener;
import com.tourismelves.view.widget.swipetoloadlayout.SwipeToLoadLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

import static com.lzy.okserver.download.DownloadManager.FINISH;

/**
 * 正在下载
 */

public class DownloadingActivity extends StateBaseActivity {
    @BindView(R.id.swipe_target)
    ItemRemoveRecyclerView swipeTarget;
    @BindView(R.id.swipeToLoadLayout)
    SwipeToLoadLayout swipeToLoadLayout;

    private DownloadManager downloadManager;
    private List<ApkDownloadInfoRes> apkDownloadInfoRes;
    private DownloadingAdapter downloadingAdapter;

    @Override
    protected void setContentLayout() {
        setContentView(R.layout.activity_downloading);
    }

    @Override
    protected void initControls() {
        showStateLayout(1);
        showStateRightView(0);
        setBaseRightTv("全部下载");
        setBaseTitle("正在下载");
        downloadManager = DownloadManager.getInstance();
        apkDownloadInfoRes = new ArrayList<>();
        swipeTarget.setLayoutManager(new LinearLayoutManager(getContext()));
        downloadingAdapter = new DownloadingAdapter(getContext(), apkDownloadInfoRes);
        swipeTarget.setAdapter(downloadingAdapter);

        swipeToLoadLayout.setLoadMoreEnabled(false);
    }

    @Override
    protected void obtainData() {
        apkDownloadInfoRes.clear();
        List<DownloadInfo> allTask = downloadManager.getAllTask();
        if (allTask != null) {
            for (int i = 0; i < allTask.size(); i++) {
                LogUtil.i(allTask.get(i));
            }
        }

        //查询除了下载完成的
        if (allTask != null) {
            for (int i = allTask.size() - 1; i >= 0; i--) {
                if (allTask.get(i) == null) {
                    continue;
                }
                if (allTask.get(i).getData() == null) {
                    downloadManager.removeTask(allTask.get(i).getTaskKey());
                    continue;
                }
                if (allTask.get(i).getState() == FINISH) {
                    continue;
                }
                if (allTask.get(i).getTaskKey().equals(getContext().getPackageName())) {
                    continue;
                }
                if (allTask.get(i).getData() instanceof ApkDownloadInfoRes) {
                    ApkDownloadInfoRes apkInfo = (ApkDownloadInfoRes) allTask.get(i).getData();
                    apkInfo.setTargetFolder(allTask.get(i).getTargetFolder());
                    apkDownloadInfoRes.add(apkInfo);
                }
            }
        }

        //查询下载完成的
        if (allTask != null) {
            for (int i = allTask.size() - 1; i >= 0; i--) {
                if (allTask.get(i) == null) {
                    continue;
                }
                if (allTask.get(i).getData() == null) {
                    downloadManager.removeTask(allTask.get(i).getTaskKey());
                    continue;
                }
                if (allTask.get(i).getData() instanceof ApkDownloadInfoRes) {
                    ApkDownloadInfoRes apkInfo = (ApkDownloadInfoRes) allTask.get(i).getData();
                    if (apkInfo.getDownKey().equals(getContext().getPackageName())) {
                        continue;
                    }
                    if (allTask.get(i).getState() == FINISH) {
                        apkInfo.setTargetFolder(allTask.get(i).getTargetFolder());
                        apkDownloadInfoRes.add(apkInfo);
                    }
                }
            }
        }
        downloadingAdapter.replaceData(apkDownloadInfoRes);
        swipeTarget.setItemViewCacheSize(apkDownloadInfoRes.size());
    }

    @Override
    protected void initEvent() {
        swipeToLoadLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeToLoadLayout.setRefreshing(false);
            }
        });
        setBaseRightTvListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), AllDownloadActivity.class));
            }
        });
        swipeTarget.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

            }

            @Override
            public void onDeleteClick(int position) {
                ApkDownloadInfoRes item = downloadingAdapter.getItem(position);
                String downKey = item.getDownKey();
                DownloadManager.getInstance().removeTask(downKey, true);
                downloadingAdapter.removeItem(position);
            }
        });
    }

}
