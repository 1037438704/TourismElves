package com.tourismelves.view.activity;

import android.support.v7.widget.AppCompatEditText;

import com.tourismelves.R;
import com.tourismelves.view.activity.base.StateBaseActivity;

import butterknife.BindView;

/**
 * 激活码
 */

public class ActivityCodeActivity extends StateBaseActivity {
    @BindView(R.id.code_et)
    AppCompatEditText codeEt;

    @Override
    protected void setContentLayout() {
        setContentView(R.layout.activity_activity_code);
    }

    @Override
    protected void initControls() {
        setBaseTitle("兑换码");

    }

    @Override
    protected void obtainData() {

    }

    @Override
    protected void initEvent() {

    }

}
