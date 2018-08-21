package com.tourismelves.view.activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.google.gson.Gson;
import com.squareup.okhttp.Request;
import com.tourismelves.R;
import com.tourismelves.model.bean.AliPayInfo;
import com.tourismelves.utils.common.PayResult;
import com.tourismelves.utils.pinyin.ApiManager;
import com.tourismelves.utils.system.SPUtils;
import com.tourismelves.view.activity.base.StateBaseActivity;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

public class PaymentActivity extends StateBaseActivity {

    TextView tv_title;
    private String flagString = "1";
    LinearLayout ll_zfbpay,ll_wxpay;
    TextView tv_submit;
    ImageView im_zfb,im_wx;
    private static final int SDK_PAY_FLAG = 1;
    private static final int SDK_CHECK_FLAG = 2;
    String sk_id;
    private AliPayInfo aliPayInfo;



    @Override
    protected void setContentLayout() {
        setContentView(R.layout.activity_payment);
        tv_title = findViewById(R.id.title_name);
        ll_zfbpay =  findViewById(R.id.ll_zfbpay);
        ll_wxpay =  findViewById(R.id.ll_wxpay);
        tv_submit = findViewById(R.id.payment_save);
        im_zfb = findViewById(R.id.im_zfb);
        im_wx = findViewById(R.id.im_zfb);
        tv_title.setText("充值");
    }

    @Override
    protected void initControls() {

    }

    @Override
    protected void obtainData() {

        Intent intent = getIntent();
        sk_id = intent.getStringExtra("pk_id");
        ll_wxpay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv_submit.setText("微信支付￥");
//                im_wx.setVisibility(View.VISIBLE);
//                im_zfb.setVisibility(View.GONE);
                 im_wx.setSelected(true);
                 im_zfb.setSelected(false);
                 flagString = "1";
            }
        });
        ll_zfbpay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv_submit.setText("支付宝支付￥");
//                im_zfb.setVisibility(View.VISIBLE);
//                im_wx.setVisibility(View.GONE);
                im_zfb.setSelected(true);
                im_wx.setSelected(false);
                flagString = "2";
                gotoPay(flagString);
            }
        });
    }



    private  void gotoPay(String flag){

        OkHttpUtils.get().url(ApiManager.ALL_URL+"lyjl/app/alipayPay.do")
                .addParams("userId", SPUtils.getInstance(PaymentActivity.this).getString("putInt"))
                .addParams("orderId",sk_id)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Request request, Exception e) {
                        Log.v("------------ccc","Request");
                    }
                    @Override
                    public void onResponse(String response) {
                        Log.v("------------ccc",response);
                        //调用微信支付
                        if (response!=null&&"1".equals(flagString)){
                            Gson gson = new Gson();
//                            payInfo = gson.fromJson(response,PayInfo.class);
//                            if (payInfo!=null&&"1".equals(flagString)){  //微信支付
//                                fudaitype = getIntent().getStringExtra("fudaitype");
//                                fudaiid = getIntent().getStringExtra("fudaiid");
//                                sendPayReq(payInfo,fudaiid,fudaitype);
//                            }
                        }else if (response!=null&&"2".equals(flagString)){
                            Gson gson = new Gson();
                            aliPayInfo = gson.fromJson(response,AliPayInfo.class);
                            if (aliPayInfo!=null&&"2".equals(flagString)){  //支付
                                if (aliPayInfo.getMessage()!=null&&!TextUtils.isEmpty(aliPayInfo.getMessage()))
                                    pay(aliPayInfo);
                                else
                                    Toast.makeText(PaymentActivity.this,"参数错误",Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });
    }



    @Override
    protected void initEvent() {

    }

    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {

            switch (msg.what) {
                case SDK_PAY_FLAG: {
                    PayResult payResult = new PayResult((String) msg.obj);
                    // 支付宝返回此次支付结果及加签，建议对支付宝签名信息拿签约时支付宝提供的公钥做验签
                    String resultInfo = payResult.getResult();
                    String resultStatus = payResult.getResultStatus();
                    // 判断resultStatus 为“9000”则代表支付成功，具体状态码代表含义可参考接口文档
                    Log.v("--------cccc",resultStatus);
                    if (TextUtils.equals(resultStatus, "9000")) {
                        Toast.makeText(PaymentActivity.this, "支付成功",Toast.LENGTH_SHORT).show();
                        //支付成功操作
                        finish();
                    } else {
                        // 判断resultStatus 为非“9000”则代表可能支付失败
                        // “8000”代表支付结果因为支付渠道原因或者系统原因还在等待支付结果确认，最终交易是否成功以服务端异步通知为准（小概率状态）
                        if (TextUtils.equals(resultStatus, "8000")) {
                            Toast.makeText(PaymentActivity.this, "支付结果确认中",
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            // 其他值就可以判断为支付失败，包括用户主动取消支付，或者系统返回的错误
                            Toast.makeText(PaymentActivity.this, "支付失败",
                                    Toast.LENGTH_SHORT).show();
                            //支付成功操作
                        }
                    }
                    break;
                }



                case SDK_CHECK_FLAG: {
                    Toast.makeText(PaymentActivity.this, "检查结果为：" + msg.obj,Toast.LENGTH_SHORT).show();
                    break;
                }
                default:
                    break;
            }
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mHandler.removeCallbacksAndMessages(null);

    }


    /**
     * 支付宝支付
     */


    private void pay(final AliPayInfo mAliPayInfo) {

        Runnable payRunnable = new Runnable() {
            @Override
            public void run() {
                // 构造PayTask 对象
                PayTask alipay = new PayTask(PaymentActivity.this);
                // 调用支付接口，获取支付结果
                String result = alipay.pay(mAliPayInfo.getMessage(),true);
                Message msg = new Message();
                msg.what = SDK_PAY_FLAG;
                msg.obj = result;
                mHandler.sendMessage(msg);
            }
        };
        // 必须异步调用
        Thread payThread = new Thread(payRunnable);
        payThread.start();

    }
}
