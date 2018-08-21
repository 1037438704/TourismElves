package com.tourismelves.view.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.tourismelves.R;
import com.tourismelves.view.activity.base.StateBaseActivity;

public class InviteActivity extends StateBaseActivity {

    TextView tv_title;

    @Override
    protected void setContentLayout() {
        setContentView(R.layout.activity_invite);
        tv_title = findViewById(R.id.title_name);
        tv_title.setText("邀请好友");
    }

    @Override
    protected void initControls() {

    }

    @Override
    protected void obtainData() {

    }

    @Override
    protected void initEvent() {

    }
}
