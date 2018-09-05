package com.tourismelves.view.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.okhttp.Request;
import com.tourismelves.R;
import com.tourismelves.utils.pinyin.ApiManager;
import com.tourismelves.utils.system.SPUtils;
import com.tourismelves.view.activity.base.StateBaseActivity;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

public class ChangePhoneActivity extends StateBaseActivity {

    EditText ed_phone,ed_smscode;
    Button bt_save;
    TextView tv_smscode;
    String mobile,code;

    @Override
    protected void setContentLayout() {
        setContentView(R.layout.activity_change_phone);
    }

    @Override
    protected void initControls() {
        showStateLayout(1);
        setBaseTitle("绑定手机");
        showStateRightView(2);
        setStatusUi();
    }

    @Override
    protected void obtainData() {

        ed_phone = findViewById(R.id.pet_one);
        ed_smscode = findViewById(R.id.pet_yzm);
        bt_save = (Button) findViewById(R.id.petname_save);
        tv_smscode = findViewById(R.id.tv_smscode);

        tv_smscode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mobile = ed_phone.getText().toString();
                OkHttpUtils.get()
                        .url("http://211.157.162.2/lyjl/web/getAppSMSCode.do")
                        .addParams("mobile",mobile)
                        .addParams("randomCode",123456+"")
                        .build()
                        .execute(new StringCallback() {
                            @Override
                            public void onError(Request request, Exception e) {

                            }

                            @Override
                            public void onResponse(String response) {

                                Log.e("获取验证码",response);
                                Toast.makeText(ChangePhoneActivity.this, "发送成功", Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });

        bt_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                code = ed_smscode.getText().toString();
                OkHttpUtils.get().url(ApiManager.ALL_URL+"lyjl/app/updateUserPhone.do")
                        .addParams("userId",SPUtils.getInstance(ChangePhoneActivity.this).getString("putInt"))
                        .addParams("smsCode",code)
                        .addParams("loginName",mobile)
                        .build()
                        .execute(new StringCallback() {
                            @Override
                            public void onError(Request request, Exception e) {

                            }

                            @Override
                            public void onResponse(String response) {
                                Log.e("修改手机号",response);
                                Toast.makeText(ChangePhoneActivity.this, "修改成功", Toast.LENGTH_SHORT).show();
                                finish();
                            }
                        });
            }
        });
//        bt_save.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // Log.e("token",SPUtils.getInstance().getString(SPConstants.KEY_USER_TOKEN));
//                String userid = SPUtils.getInstance(ChangePhoneActivity.this).getString("putInt");  //取出这个int值
//                name = ed_name.getText().toString();
//                OkHttpUtils.get().url(ApiManager.SET_PERSON)
//                        .addParams("userId",userid)
////                        .addParams("Authorization", SPUtils.getInstance().getString(SPConstants.KEY_USER_TOKEN))
//                        .addParams("nickName",name)
//                        .build()
//                        .execute(new StringCallback() {
//                            @Override
//                            public void onError(Request request, Exception e) {
//
//                            }
//                            @Override
//                            public void onResponse(String response) {
//                                Log.e("个人信息",response);
//                                Toast.makeText(SetNameActivity.this, "修改成功", Toast.LENGTH_SHORT).show();
//                                finish();
//                            }
//                        });
//            }
//        });
    }

    @Override
    protected void initEvent() {

    }
}
