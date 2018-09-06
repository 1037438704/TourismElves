package com.tourismelves.view.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.lzy.okserver.download.DownloadInfo;
import com.lzy.okserver.download.DownloadManager;
import com.tourismelves.R;
import com.tourismelves.model.res.ApkDownloadInfoRes;
import com.tourismelves.utils.file.RootFile;
import com.tourismelves.view.activity.base.StateBaseActivity;
import com.tourismelves.view.adapter.AllDownload2Adapter;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 全部下载
 */

public class AllDownload2Activity extends StateBaseActivity {
    @BindView(R.id.all_download_recycler)
    RecyclerView allDownloadRecycler;
    private AllDownload2Adapter allDownloadAdapter;
    private String name;
    private DownloadManager downloadManager;

    @Override
    protected void setContentLayout() {
        setContentView(R.layout.activity_all_download);
    }

    @Override
    protected void initControls() {
        showStateLayout(1);
        showStateRightView(10);
        setBaseTitle("全部下载");
        name = getIntent().getStringExtra("name");

        downloadManager = DownloadManager.getInstance();

        allDownloadRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        allDownloadAdapter = new AllDownload2Adapter(getContext(), new ArrayList<ApkDownloadInfoRes>());
        allDownloadRecycler.setAdapter(allDownloadAdapter);
    }

    @Override
    protected void obtainData() {
        new Thread() {
            @Override
            public void run() {
                super.run();
                final List<ApkDownloadInfoRes> fileName = getFileName();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        allDownloadAdapter.replaceData(fileName);
                    }
                });

            }
        }.start();
    }

    @Override
    protected void initEvent() {

    }

    public List<ApkDownloadInfoRes> getFileName() {
        List<ApkDownloadInfoRes> vecFile = new ArrayList<>();
        File file = new File(RootFile.getDownloadFiles().getPath() + "/" + name);
        File[] subFile = file.listFiles();

        for (int iFileLength = 0; iFileLength < subFile.length; iFileLength++) {
            // 判断是否为文件夹
            if (!subFile[iFileLength].isDirectory()) {
                String filename = subFile[iFileLength].getName().replace(".mp3", "");
                DownloadInfo downloadInfo = downloadManager.getDownloadInfo(filename);
                if (downloadInfo != null) {
                    ApkDownloadInfoRes apkDownloadInfoRes = (ApkDownloadInfoRes) downloadInfo.getData();
                    if (apkDownloadInfoRes != null) {
                        vecFile.add(apkDownloadInfoRes);
                    }
                }
            }
        }
        return vecFile;
    }
}
