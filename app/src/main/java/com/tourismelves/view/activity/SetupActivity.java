package com.tourismelves.view.activity;

import android.widget.TextView;

import com.tourismelves.R;
import com.tourismelves.view.activity.base.StateBaseActivity;

public class SetupActivity extends StateBaseActivity {

    TextView tv_title;

    @Override
    protected void setContentLayout() {
        setContentView(R.layout.activity_setup);
        initView();
    }
    private void initView() {
        tv_title = findViewById(R.id.title_name);
        tv_title.setText("设置");
    }


    @Override
    protected void initControls() {
      //  setStatusBar(R.id.select_city_status);
        setStatusUi();
    }

    @Override
    protected void obtainData() {

    }

    @Override
    protected void initEvent() {

    }
}
