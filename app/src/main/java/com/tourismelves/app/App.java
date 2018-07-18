package com.tourismelves.app;

import android.app.Application;
import android.content.Context;

import com.tourismelves.utils.common.ToastUtil;
import com.tourismelves.utils.log.LogUtil;


public class App extends Application {
    private static Context mContext;

    public static Context getContext() {
        return mContext;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        initCommon();
    }

    private void initCommon() {
        mContext = getApplicationContext();
        ToastUtil.init(this);
        LogUtil.init(true);
    }
}
