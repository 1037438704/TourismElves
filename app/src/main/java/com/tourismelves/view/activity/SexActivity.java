package com.tourismelves.view.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.squareup.okhttp.Request;
import com.tourismelves.R;
import com.tourismelves.utils.pinyin.ApiManager;
import com.tourismelves.utils.system.SPUtils;
import com.tourismelves.view.activity.base.StateBaseActivity;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

public class SexActivity extends StateBaseActivity {
    ImageView mImage_girl,mImage_boy;
    LinearLayout mLinear_girl,mLinear_boy;


    @Override
    protected void setContentLayout() {
        setContentView(R.layout.activity_sex);
    }

    @Override
    protected void initControls() {
        showStateLayout(1);
        setBaseTitle("性别");
        showStateRightView(2);
        setStatusUi();
    }

    @Override
    protected void obtainData() {
        mLinear_girl = findViewById(R.id.linear_girl);
        mLinear_boy = findViewById(R.id.linear_boy);
        mImage_boy = findViewById(R.id.sex_boy);
        mImage_girl = findViewById(R.id.sex_girl);
        Intent intent = getIntent();
        String type = intent.getStringExtra("type");
        if (type.equals("女")){
            mImage_girl.setVisibility(View.VISIBLE);
            mImage_boy.setVisibility(View.GONE);
        }if (type.equals("男")){
            mImage_girl.setVisibility(View.GONE);
            mImage_boy.setVisibility(View.VISIBLE);
        }

    }

    @Override
    protected void initEvent() {

        mLinear_girl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OkHttpUtils.get().url(ApiManager.SET_PERSON)
                        .addParams("userId", SPUtils.getInstance(SexActivity.this).getString("putInt"))
//                        .addParams("Authorization", SPUtils.getInstance().getString(SPConstants.KEY_USER_TOKEN))
                        .addParams("gender","女")
                        .build()
                        .execute(new StringCallback() {
                            @Override
                            public void onError(Request request, Exception e) {

                            }
                            @Override
                            public void onResponse(String response) {
                                Log.e("个人信息",response);
                                Toast.makeText(SexActivity.this, "修改成功", Toast.LENGTH_SHORT).show();
                                mImage_girl.setVisibility(View.VISIBLE);
                                mImage_boy.setVisibility(View.GONE);
                                finish();
                            }
                        });
            }
        });


        mLinear_boy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OkHttpUtils.get().url(ApiManager.SET_PERSON)
                        .addParams("userId", SPUtils.getInstance(SexActivity.this).getString("putInt"))
//                        .addParams("Authorization", SPUtils.getInstance().getString(SPConstants.KEY_USER_TOKEN))
                        .addParams("gender","男")
                        .build()
                        .execute(new StringCallback() {
                            @Override
                            public void onError(Request request, Exception e) {

                            }
                            @Override
                            public void onResponse(String response) {
                                Log.e("个人信息",response);
                                Toast.makeText(SexActivity.this, "修改成功", Toast.LENGTH_SHORT).show();
                                mImage_girl.setVisibility(View.GONE);
                                mImage_boy.setVisibility(View.VISIBLE);
                                finish();
                            }
                        });
            }
        });

    }
}
