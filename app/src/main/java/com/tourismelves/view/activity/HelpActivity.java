package com.tourismelves.view.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.tourismelves.R;
import com.tourismelves.view.activity.base.StateBaseActivity;

public class HelpActivity extends StateBaseActivity {


    @Override
    protected void setContentLayout() {
        setContentView(R.layout.activity_help);
    }

    @Override
    protected void initControls() {

        showStateLayout(1);
        setBaseTitle("帮助与反馈");
        showStateRightView(2);
        setStatusUi();
        setStatusUi();
    }

    @Override
    protected void obtainData() {

    }

    @Override
    protected void initEvent() {

    }
}
