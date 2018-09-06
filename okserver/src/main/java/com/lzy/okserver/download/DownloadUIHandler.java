package com.lzy.okserver.download;

import android.os.Handler;
import android.os.Message;

import com.lzy.okgo.utils.OkLogger;
import com.lzy.okserver.listener.DownloadListener;

/**
 * ================================================
 * 作    者：廖子尧
 * 版    本：1.0
 * 创建日期：2016/1/19
 * 描    述：用于在主线程回调下载UI
 * 修订历史：
 * ================================================
 */
public class DownloadUIHandler extends Handler {

    private DownloadListener mGlobalDownloadListener;

    @Override
    public void handleMessage(Message msg) {
        MessageBean messageBean = (MessageBean) msg.obj;
        if (messageBean != null) {
            DownloadInfo info = messageBean.downloadInfo;
            DownloadListener listener = info.getListener();


            String errorMsg = messageBean.errorMsg;
            Exception e = messageBean.e;
            if (mGlobalDownloadListener != null) {
                executeListener(mGlobalDownloadListener, info, errorMsg, e);
            }

            if (listener != null) {
                OkLogger.i("listener", "执行回掉"+listener);
                executeListener(listener, info, errorMsg, e);
            }else {
                OkLogger.i("listener", "无法执行回掉"+listener);
            }
        } else {
            OkLogger.e("DownloadUIHandler DownloadInfo null");
        }
    }

    private void executeListener(DownloadListener listener, DownloadInfo info, String errorMsg, Exception e) {
        int state = info.getState();
        OkLogger.i("executeListener","executeListener: "+state+"\t"+info.toString());
        switch (state) {
            case DownloadManager.NONE:
                listener.onProgress(info);
                break;
            case DownloadManager.WAITING:
                listener.onWait(info);
                break;
            case DownloadManager.DOWNLOADING:
                listener.onProgress(info);
                break;
            case DownloadManager.PAUSE:
                listener.onProgress(info);
                listener.onPause(info);
                break;
            case DownloadManager.FINISH:
                listener.onProgress(info);   //结束前再次回调进度，避免最后一点数据没有刷新
                listener.onFinish(info);
                break;
            case DownloadManager.ERROR:
                listener.onProgress(info);   //结束前再次回调进度，避免最后一点数据没有刷新
                listener.onError(info, errorMsg, e);
                break;
        }
    }

    public void setGlobalDownloadListener(DownloadListener downloadListener) {
        this.mGlobalDownloadListener = downloadListener;
    }

    public static class MessageBean {
        public DownloadInfo downloadInfo;
        public String errorMsg;
        public Exception e;
    }
}