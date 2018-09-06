package com.tourismelves.view.adapter;

import android.Manifest;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.request.GetRequest;
import com.lzy.okserver.download.DownloadInfo;
import com.lzy.okserver.download.DownloadManager;
import com.lzy.okserver.listener.DownloadListener;
import com.tourismelves.R;
import com.tourismelves.model.res.ApkDownloadInfoRes;
import com.tourismelves.utils.file.RootFile;
import com.tourismelves.utils.glide.ShowImageUtils;
import com.tourismelves.utils.system.PermissionUtil;
import com.tourismelves.view.adapter.base.RecyclerBaseAdapter;
import com.tourismelves.view.adapter.base.ViewHolder;

import java.util.List;

import static com.lzy.okserver.download.DownloadManager.FINISH;

public class DownloadingAdapter extends RecyclerBaseAdapter<ApkDownloadInfoRes> {

    private final DownloadManager downloadManager;

    public DownloadingAdapter(@NonNull Context context, @NonNull List<ApkDownloadInfoRes> mDataList) {
        super(context, mDataList);
        downloadManager = DownloadManager.getInstance();
    }

    @Override
    protected void bindDataForView(ViewHolder holder, final ApkDownloadInfoRes apkDownloadInfoRes, int position) {
        DownloadInfo downloadInfo = downloadManager.getDownloadInfo(apkDownloadInfoRes.getDownKey());
        DownloadingHolder downloadHolder = (DownloadingHolder) holder;

        ShowImageUtils.showImageView(getContext(), apkDownloadInfoRes.getImageUrl()
                + "_" + (int) getContext().getResources().getDimension(R.dimen.dp105) + "x" +
                (int) getContext().getResources().getDimension(R.dimen.dp105) + ".jpg", downloadHolder.icon);
        downloadHolder.name.setText(apkDownloadInfoRes.getFileName().replace("/", ""));

        if (downloadInfo != null) {
            if (!RootFile.isExit(apkDownloadInfoRes.getFilePath())
                    && downloadInfo.getState() == FINISH) {
                //已经下载完成
                downloadHolder.finish();
            }
            downloadHolder.refresh(downloadInfo, apkDownloadInfoRes);
            DownloadListener downloadListener = new MyDownloadListener();
            downloadListener.setUserTag(downloadHolder);
            downloadInfo.setListener(downloadListener);
            downloadHolder.refresh();
        } else {
            downloadHolder.none();
        }

        downloadHolder.rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                download(apkDownloadInfoRes);
            }
        });

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.item_downloading, parent, false);
        return new DownloadingHolder(view);
    }

    /**
     * 下载
     */
    public void download(ApkDownloadInfoRes apkInfo) {
        if (!PermissionUtil.hasPermissons(getContext(), Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            return;
        }
        //设置下载路径
        DownloadInfo downloadInfo = downloadManager.getDownloadInfo(apkInfo.getDownKey());
        if (downloadInfo != null) {
            //这里你自己做一些逻辑判断  比如点击暂停，继续下载等
            switch (downloadInfo.getState()) {
                case DownloadManager.NONE:
                    if (downloadInfo.getProgress() > 0) {
                        downloadManager.addTask(apkInfo.getFileName(), apkInfo.getDownKey(),
                                apkInfo, downloadInfo.getRequest(), null, false);
                    } else {
                        downloadManager.addTask(apkInfo.getFileName(), apkInfo.getDownKey(),
                                apkInfo, downloadInfo.getRequest(), null, true);
                    }
                    break;
                case DownloadManager.ERROR:
                    downloadManager.restartTask(apkInfo.getDownKey());
                    break;
                case DownloadManager.PAUSE:
                    downloadManager.addTask(apkInfo.getFileName(), apkInfo.getDownKey(),
                            apkInfo, downloadInfo.getRequest(), null, false);
                    break;
                case FINISH:

                    break;
                case DownloadManager.DOWNLOADING:
                    downloadManager.pauseTask(apkInfo.getDownKey());
                    break;
            }
            notifyDataSetChanged();
            return;
        }
        DownloadManager.getInstance().setTargetFolder(RootFile.getDownloadFiles().getPath());
        GetRequest request = OkGo.get(apkInfo.getUrl());
        downloadManager.addTask(apkInfo.getFileName(), apkInfo.getDownKey(),
                apkInfo, request, null, false,
                true, true);
        notifyDataSetChanged();
    }

    class MyDownloadListener extends DownloadListener {
        @Override
        public void onProgress(DownloadInfo downloadInfo) {
            if (getUserTag() == null) {
                return;
            }
            DownloadingHolder holder = (DownloadingHolder) getUserTag();
            holder.refresh();  //这里不能使用传递进来的 DownloadInfo，否者会出现条目错乱的问题
        }

        @Override
        public void onPause(DownloadInfo downloadInfo) {
            if (getUserTag() == null) {
                return;
            }
            DownloadingHolder holder = (DownloadingHolder) getUserTag();
            holder.pause();
        }

        @Override
        public void onWait(DownloadInfo downloadInfo) {

        }

        @Override
        public void onFinish(DownloadInfo downloadInfo) {
            if (getUserTag() == null) {
                return;
            }
            DownloadingHolder holder = (DownloadingHolder) getUserTag();
            holder.complite();
            notifyDataSetChanged();
        }

        @Override
        public void onError(DownloadInfo downloadInfo, String errorMsg, Exception e) {

        }
    }

}
