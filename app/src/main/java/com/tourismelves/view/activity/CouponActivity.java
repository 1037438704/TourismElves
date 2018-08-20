package com.tourismelves.view.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.tourismelves.R;
import com.tourismelves.view.activity.base.StateBaseActivity;
import com.tourismelves.view.adapter.CouponAdapter;

public class CouponActivity extends StateBaseActivity {

    TextView tv_title;
    RecyclerView recyclerView;
    CouponAdapter couponAdapter;



    @Override
    protected void setContentLayout() {
        setContentView(R.layout.activity_coupon);
        initView();
    }
    private void initView() {
        tv_title = findViewById(R.id.title_name);
        tv_title.setText("我的优惠券");
        recyclerView = findViewById(R.id.coupon_recy);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        couponAdapter = new CouponAdapter(this);
        recyclerView.setAdapter(couponAdapter);
    }


    @Override
    protected void initControls() {
     //   setStatusBar(R.id.select_city_status);
        setStatusUi();
    }

    @Override
    protected void obtainData() {

    }

    @Override
    protected void initEvent() {

    }
}
