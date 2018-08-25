package com.tourismelves.view.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
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
import com.tourismelves.view.activity.base.BaseActivity;
import com.tourismelves.view.activity.base.StateBaseActivity;
import com.tourismelves.view.adapter.CouponAdapter;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

public class SetNameActivity extends StateBaseActivity {

    EditText ed_name;
    Button bt_save;
    String name;

    @Override
    protected void setContentLayout() {
        setContentView(R.layout.activity_set_name);
        initView();
    }

    private void initView() {


        ed_name = (EditText) findViewById(R.id.pet_et_name);
        bt_save = (Button) findViewById(R.id.petname_save);

    }
    @Override
    protected void initControls() {
        showStateLayout(1);
        setBaseTitle("修改昵称");
        showStateRightView(2);
    }

    @Override
    protected void obtainData() {

        bt_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // Log.e("token",SPUtils.getInstance().getString(SPConstants.KEY_USER_TOKEN));
                String userid = SPUtils.getInstance(SetNameActivity.this).getString("putInt");  //取出这个int值
                name = ed_name.getText().toString();
                OkHttpUtils.get().url(ApiManager.SET_PERSON)
                        .addParams("userId",userid)
//                        .addParams("Authorization", SPUtils.getInstance().getString(SPConstants.KEY_USER_TOKEN))
                        .addParams("nickName",name)
                        .build()
                        .execute(new StringCallback() {
                            @Override
                            public void onError(Request request, Exception e) {

                            }
                            @Override
                            public void onResponse(String response) {
                                Log.e("个人信息",response);
                                Toast.makeText(SetNameActivity.this, "修改成功", Toast.LENGTH_SHORT).show();
                                finish();
                            }
                        });
            }
        });
    }

    @Override
    protected void initEvent() {

    }
}
