package com.tourismelves.view.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.tourismelves.R;
import com.tourismelves.view.activity.base.StateBaseActivity;

public class InviteActivity extends StateBaseActivity {



    @Override
    protected void setContentLayout() {
        setContentView(R.layout.activity_invite);

    }

    @Override
    protected void initControls() {

        showStateLayout(1);
        setBaseTitle("邀请好友");
        showStateRightView(2);
    }

    @Override
    protected void obtainData() {

    }

    @Override
    protected void initEvent() {

    }
}
