package com.tourismelves.view.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.tourismelves.R;
import com.tourismelves.utils.file.RootFile;
import com.tourismelves.view.activity.base.StateBaseActivity;
import com.tourismelves.view.adapter.AllDownloadAdapter;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 全部下载
 */

public class AllDownloadActivity extends StateBaseActivity {
    @BindView(R.id.all_download_recycler)
    RecyclerView allDownloadRecycler;
    private AllDownloadAdapter allDownloadAdapter;

    @Override
    protected void setContentLayout() {
        setContentView(R.layout.activity_all_download);
    }

    @Override
    protected void initControls() {
        showStateLayout(1);
        showStateRightView(10);
        setBaseTitle("全部下载");

        allDownloadRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        allDownloadAdapter = new AllDownloadAdapter(getContext(), new ArrayList<String>());
        allDownloadRecycler.setAdapter(allDownloadAdapter);
    }

    @Override
    protected void obtainData() {
        new Thread() {
            @Override
            public void run() {
                super.run();
                final List<String> fileName = getFileName();
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

    public List<String> getFileName() {
        List<String> vecFile = new ArrayList<>();
        File file = new File(RootFile.getDownloadFiles().getPath());
        File[] subFile = file.listFiles();

        for (int iFileLength = 0; iFileLength < subFile.length; iFileLength++) {
            // 判断是否为文件夹
            if (subFile[iFileLength].isDirectory()) {
                String filename = subFile[iFileLength].getName();
                vecFile.add(filename);
            }
        }
        return vecFile;
    }
}
