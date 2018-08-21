package com.tourismelves.view.activity;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.google.gson.Gson;
import com.squareup.okhttp.Request;
import com.tourismelves.R;
import com.tourismelves.model.bean.RechargeBean;
import com.tourismelves.model.net.OkHttpUtils;
import com.tourismelves.utils.pinyin.ApiManager;
import com.tourismelves.utils.system.SPUtils;
import com.tourismelves.view.activity.base.StateBaseActivity;
import com.tourismelves.view.adapter.RechargeAdapter;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

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
        setStatusBar(R.id.recharge_status);
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
        rechargeAdapter.setOnItemClickListener(new RechargeAdapter.OnItemClickListener() {
            @Override
            public void OnItem(View view, int position) {
                com.zhy.http.okhttp.OkHttpUtils.get()
                        .url(ApiManager.ALL_URL+"lyjl/app/createRechargeOrder.do")
                        .addParams("userId", SPUtils.getInstance(RechargeActivity.this).getString("putInt"))
                        .addParams("id",listBeen.get(position).getId()+"")
                        .build()
                        .execute(new StringCallback() {
                            @Override
                            public void onError(Request request, Exception e) {

                            }

                            @Override
                            public void onResponse(String response) {
                                Log.e("生成订单",response);
                                try {
                                    JSONObject jsonObject = new JSONObject(response);
                                    String pk_id = jsonObject.getString("pk_id");
                                    Intent intent = new Intent(RechargeActivity.this,PaymentActivity.class);
                                    intent.putExtra("pk_id",pk_id);
                                    startActivity(intent);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                            }
                        });
            }
        });
    }

    @Override
    protected void initEvent() {

    }


    /**
     * 支付宝支付
     */


//    private void pay(final AliPayInfo mAliPayInfo) {
//
//        Runnable payRunnable = new Runnable() {
//            @Override
//            public void run() {
//                // 构造PayTask 对象
//                PayTask alipay = new PayTask(PaymentActivity.this);
//                // 调用支付接口，获取支付结果
//                String result = alipay.pay(mAliPayInfo.getData().getInfo(),true);
//                Message msg = new Message();
//                msg.what = SDK_PAY_FLAG;
//                msg.obj = result;
//                mHandler.sendMessage(msg);
//            }
//        };
//        // 必须异步调用
//        Thread payThread = new Thread(payRunnable);
//        payThread.start();
//
//    }






}
