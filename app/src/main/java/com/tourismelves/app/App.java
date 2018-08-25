package com.tourismelves.app;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.tourismelves.R;
import com.tourismelves.utils.common.ToastUtil;
import com.tourismelves.utils.log.LogUtil;
import com.tourismelves.utils.system.LocationUtil;

//keytool -exportcert -keystore /Users/jyhd/Desktop/TourismElves/app.jks -list -v

//MD5: 2f31408314c3fd9edaef89d9b0709c2f
//SHA1: 46:DF:DF:AA:3E:6F:40:31:D4:81:AD:B8:9B:6C:86:6C:4A:CA:BB:C7     正式
//SHA1: 84:AA:10:E4:05:4B:FC:14:7D:9B:81:DA:88:8F:74:B6:D1:25:49:5A  测试
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

        IWXAPI iwxapi = WXAPIFactory.createWXAPI(this, getString(R.string.WX_APPID), true);
        iwxapi.registerApp(getString(R.string.WX_APPID));

    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(base);
    }

    private void initCommon() {
        mContext = getApplicationContext();
        ToastUtil.init(this);
        LogUtil.init(true);

        LocationUtil instance = LocationUtil.getInstance(getContext());
    }


}
