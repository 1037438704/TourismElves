package com.tourismelves.view.activity;

import com.tourismelves.R;
import com.tourismelves.view.activity.base.StateBaseActivity;

/**
 * 结算
 */

public class SettlementActivity extends StateBaseActivity {
    @Override
    protected void setContentLayout() {
        setContentView(R.layout.activity_settlement);
    }

    @Override
    protected void initControls() {
        showStateLayout(1);
        setBaseTitle("结算");
        showStateRightView(2);

    }

    @Override
    protected void obtainData() {

    }

    @Override
    protected void initEvent() {

    }
}
