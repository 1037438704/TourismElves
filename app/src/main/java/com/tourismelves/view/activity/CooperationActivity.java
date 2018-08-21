package com.tourismelves.view.activity;

import android.widget.TextView;

import com.tourismelves.R;
import com.tourismelves.view.activity.base.StateBaseActivity;

public class CooperationActivity extends StateBaseActivity {



    @Override
    protected void setContentLayout() {
        setContentView(R.layout.activity_cooperation);
        initView();
    }

    private void initView() {

    }

    @Override
    protected void initControls() {
      //  setStatusBar(R.id.select_city_status);
        showStateLayout(1);
        setBaseTitle("商务合作");
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
