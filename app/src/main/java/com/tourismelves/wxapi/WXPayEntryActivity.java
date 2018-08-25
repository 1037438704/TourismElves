package com.tourismelves.wxapi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.tourismelves.R;

public class WXPayEntryActivity extends Activity implements IWXAPIEventHandler {

    public static int statusCode = 0;
    private static final String TAG = "WXPayEntryActivity";

    private IWXAPI api;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pay_result);

        api = WXAPIFactory.createWXAPI(this, getString(R.string.WX_APPID));
        api.handleIntent(getIntent(), this);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        api.handleIntent(intent, this);
    }

    @Override
    public void onResp(BaseResp resp) {
        Log.d(TAG, "onPayFinish, errCode = " + resp.errCode);

        if (resp.getType() == ConstantsAPI.COMMAND_PAY_BY_WX) {
//            AlertDialog.Builder builder = new AlertDialog.Builder(this);
//            builder.setTitle(R.string.app_tip);
//            builder.setMessage(getString(R.string.pay_result_callback_msg, String.valueOf(resp.errCode)));
//            builder.show();
            if (resp.errCode==0){
                Toast.makeText(this,"支付成功",Toast.LENGTH_SHORT).show();
                //写在这个地方就行
                //支付成功操作
            }else {
                Toast.makeText(this,"支付失败",Toast.LENGTH_SHORT).show();
            }

        }


        this.finish();


    }





    @Override
    public void onReq(BaseReq baseReq) {

    }


}