package com.tourismelves.view.activity;

import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.AppCompatTextView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alipay.sdk.app.PayTask;
import com.tourismelves.R;
import com.tourismelves.model.bean.WXPayBean;
import com.tourismelves.model.net.OkHttpUtils;
import com.tourismelves.utils.WXPayUtils;
import com.tourismelves.utils.common.PayResult;
import com.tourismelves.utils.common.ToastUtil;
import com.tourismelves.utils.system.SPUtils;
import com.tourismelves.view.activity.base.StateBaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

import static com.tourismelves.app.constant.UrlConstants.alipayPay;
import static com.tourismelves.app.constant.UrlConstants.glodPay;
import static com.tourismelves.app.constant.UrlConstants.wxPayPay;

/**
 * 支付
 */
public class PayActivity extends StateBaseActivity {
    @BindView(R.id.pay_zfb)
    AppCompatTextView payZfb;
    @BindView(R.id.pay_wx)
    AppCompatTextView payWx;
    @BindView(R.id.pay_jb)
    AppCompatTextView payJb;
    @BindView(R.id.pay_money)
    AppCompatTextView payMoney;
    private String pk_id;


    @Override
    protected void setContentLayout() {
        setContentView(R.layout.activity_pay);
    }

    @Override
    protected void initControls() {
        showStateLayout(1);
        setBaseTitle("结算");
        showStateRightView(2);

        pk_id = getIntent().getStringExtra("pk_id");
    }

    @Override
    protected void obtainData() {

    }

    @Override
    protected void initEvent() {

    }

    @OnClick({R.id.pay_zfb, R.id.pay_wx, R.id.pay_jb, R.id.pay_sure})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.pay_zfb:
                setPay(0);
                break;
            case R.id.pay_wx:
                setPay(1);
                break;
            case R.id.pay_jb:
                setPay(2);
                break;
            case R.id.pay_sure:
                pay();
                break;
        }
    }

    private void setPay(int payType) {
        payZfb.setSelected(payType == 0);
        payWx.setSelected(payType == 1);
        payJb.setSelected(payType == 2);
    }

    private void pay() {
        if (payZfb.isSelected()) {
            alipayPay();
        } else if (payWx.isSelected()) {
            wxPayPay();
        } else if (payJb.isSelected()) {
            glodPay();
        } else {
            ToastUtil.show("请选择支付方式");
        }
    }


    /**
     * 结算
     */
    private void glodPay() {
        OkHttpUtils.get(glodPay + "userId=" + SPUtils.getInstance(this).getString("putInt") + "&orderId=" + pk_id,
                new OkHttpUtils.ResultCallback<String>() {
                    @Override
                    public void onSuccess(String response) {
                        JSONObject object = JSON.parseObject(response);
                        Integer code = object.getInteger("code");
                        if (code == 200) {

                        } else {
                            ToastUtil.show(JSON.parseObject(response).getString("message"));
                        }
                    }

                    @Override
                    public void onFailure(Exception e) {
                        ToastUtil.show(R.string.no_found_network);
                    }
                });
    }

    private void wxPayPay() {
        OkHttpUtils.get(wxPayPay + "userId=" + SPUtils.getInstance(this).getString("putInt") + "&orderId=" + pk_id,
                new OkHttpUtils.ResultCallback<String>() {
                    @Override
                    public void onSuccess(String response) {
                        JSONObject object = JSON.parseObject(response);
                        Integer code = object.getInteger("code");
                        if (code == 200) {
                            WXPayBean message = JSON.parseObject(object.getString("message"), WXPayBean.class);
                            //假装请求了服务器 获取到了所有的数据,注意参数不能少
                            WXPayUtils.WXPayBuilder builder = new WXPayUtils.WXPayBuilder();
                            builder.setAppId(message.getAppid())
                                    .setPartnerId(message.getPartnerid())
                                    .setPrepayId(message.getPrepayid())
                                    .setNonceStr(message.getNoncestr())
                                    .setTimeStamp(message.getTimestamp())
                                    .setSign(message.getTimestamp())
                                    .build().toWXPayNotSign(getContext());
                        } else {
                            ToastUtil.show(JSON.parseObject(response).getString("message"));
                        }
                    }

                    @Override
                    public void onFailure(Exception e) {
                        ToastUtil.show(R.string.no_found_network);
                    }
                });
    }

    private void alipayPay() {
        OkHttpUtils.get(alipayPay + "userId=" + SPUtils.getInstance(this).getString("putInt") + "&orderId=" + pk_id,
                new OkHttpUtils.ResultCallback<String>() {
                    @Override
                    public void onSuccess(String response) {
                        JSONObject object = JSON.parseObject(response);
                        Integer code = object.getInteger("code");
                        if (code == 200) {
                            final String message = object.getString("message");
                            Runnable payRunnable = new Runnable() {
                                @Override
                                public void run() {
                                    // 构造PayTask 对象
                                    PayTask alipay = new PayTask(PayActivity.this);
                                    // 调用支付接口，获取支付结果
                                    String result = alipay.pay(message, true);
                                    Message msg = new Message();
                                    msg.what = SDK_PAY_FLAG;
                                    msg.obj = result;
                                    mHandler.sendMessage(msg);
                                }
                            };
                            // 必须异步调用
                            Thread payThread = new Thread(payRunnable);
                            payThread.start();
                        } else {
                            ToastUtil.show(JSON.parseObject(response).getString("message"));
                        }
                    }

                    @Override
                    public void onFailure(Exception e) {
                        ToastUtil.show(R.string.no_found_network);
                    }
                });
    }

    private static final int SDK_PAY_FLAG = 1;
    private static final int SDK_CHECK_FLAG = 2;
    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {

            switch (msg.what) {
                case SDK_PAY_FLAG: {
                    PayResult payResult = new PayResult((String) msg.obj);
                    // 支付宝返回此次支付结果及加签，建议对支付宝签名信息拿签约时支付宝提供的公钥做验签
                    String resultInfo = payResult.getResult();
                    String resultStatus = payResult.getResultStatus();
                    // 判断resultStatus 为“9000”则代表支付成功，具体状态码代表含义可参考接口文档
                    Log.v("--------cccc", resultStatus);
                    if (TextUtils.equals(resultStatus, "9000")) {
                        Toast.makeText(PayActivity.this, "支付成功", Toast.LENGTH_SHORT).show();
                        //支付成功操作
                        finish();
                    } else {
                        // 判断resultStatus 为非“9000”则代表可能支付失败
                        // “8000”代表支付结果因为支付渠道原因或者系统原因还在等待支付结果确认，最终交易是否成功以服务端异步通知为准（小概率状态）
                        if (TextUtils.equals(resultStatus, "8000")) {
                            Toast.makeText(PayActivity.this, "支付结果确认中",
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            // 其他值就可以判断为支付失败，包括用户主动取消支付，或者系统返回的错误
                            Toast.makeText(PayActivity.this, "支付失败",
                                    Toast.LENGTH_SHORT).show();
                            //支付成功操作
                        }
                    }
                    break;
                }
                case SDK_CHECK_FLAG: {
                    Toast.makeText(PayActivity.this, "检查结果为：" + msg.obj, Toast.LENGTH_SHORT).show();
                    break;
                }
                default:
                    break;
            }
        }
    };

}
