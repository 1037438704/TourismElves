package com.tourismelves.view.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.okhttp.Request;
import com.tourismelves.R;
import com.tourismelves.utils.pinyin.ApiManager;
import com.tourismelves.utils.system.SPUtils;
import com.tourismelves.view.activity.base.StateBaseActivity;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;



public class BundActivity extends StateBaseActivity {

    EditText ed_phone,ed_yzm;
    EditText ed_pass,ed_twopass;
    String phone;
    Button bt_submit;
    TextView tv_yzm;
    String userid;


    @Override
    protected void setContentLayout() {
        setContentView(R.layout.activity_bund);
    }

    @Override
    protected void initControls() {
        ed_phone = (EditText) findViewById(R.id.change_edphone);
        ed_yzm = (EditText) findViewById(R.id.change_yzm);
        tv_yzm = (TextView) findViewById(R.id.tv_smscode);
        bt_submit = (Button) findViewById(R.id.bt_login_register);
        ed_pass = (EditText) findViewById(R.id.change_mm);
        ed_twopass = (EditText) findViewById(R.id.change_zcmm);

    }

    @Override
    protected void obtainData() {

        Intent intent = getIntent();
        final String userid = intent.getStringExtra("userid");
        //发送验证码

        tv_yzm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                phone = ed_phone.getText().toString();
                OkHttpUtils.get()
                        .url("http://211.157.162.2/lyjl/web/getAppSMSCode.do")
                        .addParams("mobile",phone)
                        .addParams("randomCode",123456+"")
                        .build()
                        .execute(new StringCallback() {
                            @Override
                            public void onError(Request request, Exception e) {

                            }

                            @Override
                            public void onResponse(String response) {

                                Log.e("获取验证码",response);
                                Toast.makeText(BundActivity.this, "发送成功", Toast.LENGTH_SHORT).show();

                            }
                        });
            }
        });

        bt_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String pass = ed_pass.getText().toString();
                String code = ed_yzm.getText().toString();
                String name = ed_phone.getText().toString();
                String twopassword = ed_twopass.getText().toString();
                if (!pass.equals(twopassword)) {
                    Toast.makeText(BundActivity.this, "两次输入不一致", Toast.LENGTH_SHORT).show();
                    return;
                }
                OkHttpUtils.get().url(ApiManager.ALL_URL+"lyjl/app/binding.do")
                        .addParams("userId",userid)
                        .addParams("smsCode",code)
                        .addParams("loginName",name)
                        .addParams("password",pass)
                        .build()
                        .execute(new StringCallback() {
                            @Override
                            public void onError(Request request, Exception e) {

                            }

                            @Override
                            public void onResponse(String response) {
                                Log.e("绑定成功",response);
                                SPUtils.getInstance(BundActivity.this).put("putInt",userid);//存储一个int值
                                Intent intent = new Intent(BundActivity.this,MainActivity.class);
                                startActivity(intent);
                            }
                        });
            }
        });
    }

    @Override
    protected void initEvent() {

    }
}
