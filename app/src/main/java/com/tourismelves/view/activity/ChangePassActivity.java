package com.tourismelves.view.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.squareup.okhttp.Request;
import com.tourismelves.R;
import com.tourismelves.utils.pinyin.ApiManager;
import com.tourismelves.utils.system.SPUtils;
import com.tourismelves.view.activity.base.StateBaseActivity;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

public class ChangePassActivity extends StateBaseActivity {


    EditText mEdit_one,mEdit_two,mEdit_three;
    Button bt_save;

    @Override
    protected void setContentLayout() {
        setContentView(R.layout.activity_change_pass);
    }

    @Override
    protected void initControls() {
        showStateLayout(1);
        setBaseTitle("修改密码");
        showStateRightView(2);
        setStatusUi();
    }

    @Override
    protected void obtainData() {

        bt_save = findViewById(R.id.petname_save);
        mEdit_one = findViewById(R.id.pet_one);
        mEdit_two = findViewById(R.id.pet_two);
        mEdit_three = findViewById(R.id.pet_three);
        


        bt_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String pass_one = mEdit_one.getText().toString();
                String pass_two = mEdit_two.getText().toString();
                String pass_three = mEdit_three.getText().toString();
                if (!pass_three.equals(pass_two)){
                    Toast.makeText(ChangePassActivity.this, "两次输入的密码不一致", Toast.LENGTH_SHORT).show();
                    return;
                }
                    OkHttpUtils.get().url(ApiManager.CHANGEPASS)
                            .addParams("userId", SPUtils.getInstance(ChangePassActivity.this).getString("putInt"))
//                        .addParams("Authorization", SPUtils.getInstance().getString(SPConstants.KEY_USER_TOKEN))
                            .addParams("password",pass_one)
                            .addParams("newPassword",pass_three)
                            .build()
                            .execute(new StringCallback() {
                                @Override
                                public void onError(Request request, Exception e) {

                                }
                                @Override
                                public void onResponse(String response) {
                                    Log.e("修改密码",response);
                                    Toast.makeText(ChangePassActivity.this, "修改成功", Toast.LENGTH_SHORT).show();
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
