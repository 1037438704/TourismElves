package com.tourismelves.view.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.squareup.okhttp.Request;
import com.tourismelves.R;
import com.tourismelves.model.bean.UserBean;
import com.tourismelves.model.net.OkHttpUtils;
import com.tourismelves.utils.system.SPUtils;
import com.tourismelves.view.activity.base.StateBaseActivity;
import com.tourismelves.view.widget.loadlayout.State;
import com.zhy.http.okhttp.callback.StringCallback;

import static com.tourismelves.app.constant.UrlConstants.userinfo;

public class MyAccountActivity extends StateBaseActivity {



    TextView tv_name,tv_phone,tv_xb,top_name;
    LinearLayout mLinear_sex,mLinear_pass,mLinear_phone;
    UserBean userBean;



    @Override
    protected void setContentLayout() {
        setContentView(R.layout.activity_my_account);

        initView();
    }

    private void initView() {

        top_name = findViewById(R.id.account_topname);
        mLinear_phone = findViewById(R.id.llaccount_phone);
        mLinear_pass = findViewById(R.id.llaccount_pass);
        mLinear_sex = findViewById(R.id.llaccount_xb);
        tv_name = findViewById(R.id.account_name);
        tv_phone = findViewById(R.id.account_phone);
        tv_xb = findViewById(R.id.account_xb);
        //修改昵称
        tv_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MyAccountActivity.this,SetNameActivity.class);
                startActivity(intent);
            }
        });
        //修改性别
        mLinear_sex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MyAccountActivity.this,SexActivity.class);
                intent.putExtra("type",userBean.getDataList().get(0).getGender());
                startActivity(intent);
            }
        });
        //修改密码
        mLinear_pass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MyAccountActivity.this,ChangePassActivity.class);

                startActivity(intent);
            }
        });
        //修改手机号
        mLinear_phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MyAccountActivity.this,ChangePhoneActivity.class);

                startActivity(intent);
            }
        });

    }
    @Override
    protected void initControls() {
       // setStatusBar(R.id.select_city_status);
        showStateLayout(1);
        setBaseTitle("我的账户");
        showStateRightView(2);
        setStatusUi();
    }

    @Override
    protected void obtainData() {
        getLoadLayout().setLayoutState(State.LOADING);
       // UserInfo();
    }

    @Override
    protected void initEvent() {

    }

    @Override
    public void onResume() {
        super.onResume();
        UserInfo();
    }

    //获取个人信息
    private void UserInfo(){
        String userid = SPUtils.getInstance(MyAccountActivity.this).getString("putInt");  //取出这个int值
        Log.e("用户id",userid);
        OkHttpUtils.get(String.format(userinfo, userid+""),
                new OkHttpUtils.ResultCallback<String>() {
                    @Override
                    public void onSuccess(String response) {

                        Log.e("USER",response);
                        Gson gson = new Gson();

                        userBean = gson.fromJson(response,UserBean.class);
                        top_name.setText(userBean.getDataList().get(0).getNickName());
                        tv_name.setText(userBean.getDataList().get(0).getNickName());
                        tv_phone.setText(userBean.getDataList().get(0).getMobile());
                        tv_xb.setText(userBean.getDataList().get(0).getGender());

                        getLoadLayout().setLayoutState(State.SUCCESS);

                    }

                    @Override
                    public void onFailure(Exception e) {
                        getLoadLayout().setLayoutState(State.FAILED);
                    }
                }
        );
    }
}
