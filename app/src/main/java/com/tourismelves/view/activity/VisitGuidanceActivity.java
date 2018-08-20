package com.tourismelves.view.activity;

import com.tourismelves.R;
import com.tourismelves.view.activity.base.StateBaseActivity;

/**
 * 游玩指引
 */

public class VisitGuidanceActivity extends StateBaseActivity {
    @Override
    protected void setContentLayout() {
        setContentView(R.layout.activity_visit_guidance);
    }

    @Override
    protected void initControls() {
        showStateLayout(1);
        setBaseTitle("游玩指引");
        showStateRightView(2);
    }

    @Override
    protected void obtainData() {

    }

    @Override
    protected void initEvent() {

    }
}
