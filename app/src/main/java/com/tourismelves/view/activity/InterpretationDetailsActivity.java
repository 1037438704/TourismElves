package com.tourismelves.view.activity;

import com.tourismelves.R;
import com.tourismelves.view.activity.base.StateBaseActivity;

/**
 * 讲解详情
 */

public class InterpretationDetailsActivity extends StateBaseActivity {
    @Override
    protected void setContentLayout() {
        setContentView(R.layout.activity_interpretation_details);
    }

    @Override
    protected void initControls() {
        showStateLayout(1);
        setBaseTitle("讲解详情");
        setBaseRightImage(R.mipmap.shoucang);
    }

    @Override
    protected void obtainData() {

    }

    @Override
    protected void initEvent() {

    }
}
