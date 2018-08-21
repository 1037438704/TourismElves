package com.tourismelves.view.activity;

import android.widget.TextView;

import com.tourismelves.R;
import com.tourismelves.view.activity.base.StateBaseActivity;

public class SetupActivity extends StateBaseActivity {



    @Override
    protected void setContentLayout() {
        setContentView(R.layout.activity_setup);
        initView();
    }
    private void initView() {

    }


    @Override
    protected void initControls() {
      //  setStatusBar(R.id.select_city_status);
        showStateLayout(1);
        setBaseTitle("设置");
        showStateRightView(2);
        setStatusUi();
    }

    @Override
    protected void obtainData() {

    }

    @Override
    protected void initEvent() {

    }
}
