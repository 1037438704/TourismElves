package com.tourismelves.view.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import com.google.gson.Gson;
import com.squareup.okhttp.Request;
import com.tourismelves.R;
import com.tourismelves.model.bean.CouponBean;
import com.tourismelves.utils.pinyin.ApiManager;
import com.tourismelves.utils.system.SPUtils;
import com.tourismelves.view.activity.base.StateBaseActivity;
import com.tourismelves.view.adapter.CouponAdapter;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

public class CouponActivity extends StateBaseActivity {


    RecyclerView recyclerView;
    CouponAdapter couponAdapter;
    CouponBean couponBean;
    List<CouponBean.DataListBean> listBeen;




    @Override
    protected void setContentLayout() {
        setContentView(R.layout.activity_coupon);
        initView();
    }
    private void initView() {

        recyclerView = findViewById(R.id.coupon_recy);


    }
    @Override
    protected void initControls() {
     //   setStatusBar(R.id.select_city_status);
        showStateLayout(1);
        setBaseTitle("我的优惠券");
        showStateRightView(2);
        setStatusUi();
        setStatusUi();
    }

    @Override
    protected void obtainData() {
        OkHttpUtils.get()
                .url(ApiManager.ALL_URL+"lyjl/app/getUserCoupon.do")
                .addParams("userId", SPUtils.getInstance(CouponActivity.this).getString("putInt"))
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Request request, Exception e) {

                    }

                    @Override
                    public void onResponse(String response) {


                        Log.e("我的优惠券",response);
                        Gson gson = new Gson();
                        couponBean = gson.fromJson(response,CouponBean.class);
                        listBeen = couponBean.getDataList();
                       // listBeen.addAll(couponBean.getDataList());
                        recyclerView.setLayoutManager(new LinearLayoutManager(CouponActivity.this));
                        couponAdapter = new CouponAdapter(CouponActivity.this,listBeen);
                        recyclerView.setAdapter(couponAdapter);
                        couponAdapter.notifyDataSetChanged();

                    }
                });
    }

    @Override
    protected void initEvent() {

    }
}
