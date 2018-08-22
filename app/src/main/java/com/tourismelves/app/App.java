package com.tourismelves.app;

import android.app.Application;
import android.content.Context;

import com.tourismelves.utils.common.ToastUtil;
import com.tourismelves.utils.log.LogUtil;
import com.tourismelves.utils.system.LocationUtil;

//keytool -exportcert -keystore /Users/jyhd/Desktop/TourismElves/app.jks -list -v

//MD5: 2f31408314c3fd9edaef89d9b0709c2f
//SHA1: 46:DF:DF:AA:3E:6F:40:31:D4:81:AD:B8:9B:6C:86:6C:4A:CA:BB:C7
//SHA256: BD:EE:A1:6B:E5:C3:CF:48:1D:DF:29:88:0F:7F:E2:D1:6E:AC:F8:DD:FD:BF:4F:C4:64:2A:FC:F2:8D:44:20:F4
//
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

        LocationUtil instance = LocationUtil.getInstance(getContext());
    }
}
