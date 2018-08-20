package com.tourismelves.view.activity;

import android.content.Intent;
import android.os.Handler;

import com.tourismelves.R;
import com.tourismelves.view.activity.base.StateBaseActivity;

/**
 * 启动页
 */

public class SplashActivity extends StateBaseActivity {
    @Override
    protected void setContentLayout() {
        setContentView(R.layout.activity_splash);
    }

    @Override
    protected void initControls() {
    }

    @Override
    protected void obtainData() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(getContext(), MainActivity.class));
                finish();
            }
        }, 1000);
    }

    @Override
    protected void initEvent() {

    }
}
