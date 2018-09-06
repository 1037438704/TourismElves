package com.tourismelves.view.adapter;


import android.annotation.SuppressLint;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.lzy.okserver.download.DownloadInfo;
import com.lzy.okserver.download.DownloadManager;
import com.tourismelves.R;
import com.tourismelves.model.res.ApkDownloadInfoRes;
import com.tourismelves.view.adapter.base.ViewHolder;
import com.tourismelves.view.widget.ProgressView;

import java.text.DecimalFormat;

public class DownloadingHolder extends ViewHolder {

    private DownloadInfo downloadInfo;
    private ApkDownloadInfoRes apkInfo;
    private AppCompatImageView play;
    AppCompatImageView icon;
    AppCompatTextView name;
    private AppCompatTextView state;
    private AppCompatTextView size;
    private ProgressView progress;
    RelativeLayout rl;
    public LinearLayout layout;

    public DownloadingHolder(View itemView) {
        super(itemView);
        icon = getView(R.id.i_downloading_icon);
        name = getView(R.id.i_downloading_name);
        state = getView(R.id.i_downloading_state);
        size = getView(R.id.i_downloading_size);
        progress = getView(R.id.i_downloading_progress);
        play = getView(R.id.i_interpretation_play);
        rl = getView(R.id.rl);
        layout = getView(R.id.item_layout);
    }


    public void refresh(DownloadInfo downloadInfo, ApkDownloadInfoRes apkInfo) {
        this.downloadInfo = downloadInfo;
        this.apkInfo = apkInfo;
        refresh();
    }

    @SuppressLint("SetTextI18n")
    public void refresh() {
        if (downloadInfo == null) {
            return;
        }
        switch (downloadInfo.getState()) {
            case DownloadManager.ERROR:
                none();
                break;
            case DownloadManager.NONE:
                if (downloadInfo.getProgress() > 0) {
                    pause();
                } else {
                    none();
                }
                break;
            case DownloadManager.PAUSE:
                pause();
                break;
            case DownloadManager.DOWNLOADING:
                state.setText("下载中");
                play.setImageResource(R.mipmap.zanting);
                break;
            case DownloadManager.WAITING:
                waiting();
                break;
            case DownloadManager.FINISH:
                finish();
                break;
        }
        size.setText(formetFileSize(downloadInfo.getDownloadLength()) + "/" + formetFileSize(downloadInfo.getTotalLength()));
        progress.setMaxCount(downloadInfo.getTotalLength());
        progress.setCurrentCount((int) downloadInfo.getDownloadLength());
    }

    public void complite() {
        try {
            finish();
            apkInfo.setTargetFolder(downloadInfo.getTargetFolder());
            apkInfo.setLocalUrl(apkInfo.getFilePath());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void none() {
        state.setText("无状态");
        play.setImageResource(R.mipmap.bofang);
    }

    public void waiting() {
        state.setText("等待下载");
        play.setImageResource(R.mipmap.bofang);
    }

    public void finish() {
        state.setText("下载完成");
        play.setImageResource(R.mipmap.bofang);
    }

    public void pause() {
        state.setText("暂停");
        play.setImageResource(R.mipmap.bofang);
    }


    private String formetFileSize(long fileS) {
        DecimalFormat df = new DecimalFormat("#.00");
        String fileSizeString = "";
        if (fileS < 1024) {
            fileSizeString = df.format((double) fileS) + "B";
        } else if (fileS < 1048576) {
            fileSizeString = df.format((double) fileS / 1024) + "K";
        } else if (fileS < 1073741824) {
            fileSizeString = df.format((double) fileS / 1048576) + "M";
        } else {
            fileSizeString = df.format((double) fileS / 1073741824) + "G";
        }
        return fileSizeString;
    }
}
