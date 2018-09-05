package com.tourismelves.view.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lzy.okserver.download.DownloadInfo;
import com.lzy.okserver.download.DownloadManager;
import com.tourismelves.R;
import com.tourismelves.model.res.ApkDownloadInfoRes;
import com.tourismelves.utils.glide.ShowImageUtils;
import com.tourismelves.view.adapter.base.RecyclerBaseAdapter;
import com.tourismelves.view.adapter.base.ViewHolder;
import com.tourismelves.view.widget.ProgressView;

import java.text.DecimalFormat;
import java.util.List;

public class DownloadingAdapter extends RecyclerBaseAdapter<ApkDownloadInfoRes> {

    private final DownloadManager downloadManager;

    public DownloadingAdapter(@NonNull Context context, @NonNull List<ApkDownloadInfoRes> mDataList) {
        super(context, mDataList);
        downloadManager = DownloadManager.getInstance();
    }

    @Override
    protected void bindDataForView(ViewHolder holder, ApkDownloadInfoRes apkDownloadInfoRes, int position) {
        DownloadInfo downloadInfo = downloadManager.getDownloadInfo(apkDownloadInfoRes.getDownKey());

        AppCompatImageView icon = holder.getView(R.id.i_downloading_icon);
        AppCompatTextView name = holder.getView(R.id.i_downloading_name);
        AppCompatTextView state = holder.getView(R.id.i_downloading_state);
        AppCompatTextView size = holder.getView(R.id.i_downloading_size);
        ProgressView progress = holder.getView(R.id.i_downloading_progress);

        ShowImageUtils.showImageView(getContext(), apkDownloadInfoRes.getImageUrl()
                + "_" + (int) getContext().getResources().getDimension(R.dimen.dp105) + "x" +
                (int) getContext().getResources().getDimension(R.dimen.dp105) + ".jpg", icon);

        name.setText(apkDownloadInfoRes.getFileName().replace("/", ""));
        state.setText("下载完成");

        size.setText(formetFileSize(downloadInfo.getTotalLength()) + "/" +
                formetFileSize(downloadInfo.getDownloadLength()));
        progress.setMaxCount(downloadInfo.getTotalLength());
        progress.setCurrentCount((int) downloadInfo.getDownloadLength());

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.item_downloading, parent, false);
        return new ViewHolder(view);
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
