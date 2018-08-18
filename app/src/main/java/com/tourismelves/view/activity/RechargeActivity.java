package com.tourismelves.view.activity;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import com.google.gson.Gson;
import com.tourismelves.R;
import com.tourismelves.model.bean.RechargeBean;
import com.tourismelves.model.net.OkHttpUtils;
import com.tourismelves.view.activity.base.StateBaseActivity;
import com.tourismelves.view.adapter.RechargeAdapter;

import java.util.ArrayList;
import java.util.List;

import static com.tourismelves.app.constant.UrlConstants.moneyinfo;

public class RechargeActivity extends StateBaseActivity {


//    TextView tv_title;
    RecyclerView recyclerView;
    RechargeAdapter rechargeAdapter;
    RechargeBean rechargeBean;
    List<RechargeBean.DataListBean> listBeen;

    @Override
    protected void setContentLayout() {
        setContentView(R.layout.activity_recharge);
    }

    @Override
    protected void initControls() {
//        tv_title = findViewById(R.id.title_name);
//        tv_title.setText("充值金币");
        recyclerView = findViewById(R.id.recharge_recy);
        listBeen = new ArrayList<>();
    }

    @Override
    protected void obtainData() {
       // setStatusBar(R.id.select_city_status);
//        setStatusUi();
        rechargeAdapter = new RechargeAdapter(RechargeActivity.this,listBeen);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(rechargeAdapter);

        OkHttpUtils.get(String.format(moneyinfo),
                new OkHttpUtils.ResultCallback<String>() {
                    @Override
                    public void onSuccess(String response) {

                        Log.e("JINBI",response);
                        Gson gson = new Gson();
                        rechargeBean = gson.fromJson(response,RechargeBean.class);
                       // listBeen = rechargeBean.getDataList();
                        listBeen.addAll(rechargeBean.getDataList());

                    }

                    @Override
                    public void onFailure(Exception e) {

                    }
                });
    }

    @Override
    protected void initEvent() {



    }

}
