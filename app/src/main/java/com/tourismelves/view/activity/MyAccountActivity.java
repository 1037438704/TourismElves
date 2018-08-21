package com.tourismelves.view.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.squareup.okhttp.Request;
import com.tourismelves.R;
import com.tourismelves.model.bean.UserBean;
import com.tourismelves.model.net.OkHttpUtils;
import com.tourismelves.utils.system.SPUtils;
import com.tourismelves.view.activity.base.StateBaseActivity;
import com.zhy.http.okhttp.callback.StringCallback;

import static com.tourismelves.app.constant.UrlConstants.userinfo;

public class MyAccountActivity extends StateBaseActivity {



    TextView tv_name,tv_phone,tv_xb;


    @Override
    protected void setContentLayout() {
        setContentView(R.layout.activity_my_account);

        initView();
    }

    private void initView() {

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
        tv_xb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MyAccountActivity.this); //定义一个AlertDialog
                final String[] strarr = {"男","女"};
                builder.setItems(strarr, new DialogInterface.OnClickListener()
                {
                    public void onClick(DialogInterface arg0, int arg1)
                    {
                        String xb = 0+"";
                        String sex = "2";
                        // 自动生成的方法存根
                        if (arg1 == 0) {//男
                            sex = "男";
                            xb = 1+"";
                        }else {//女
                            sex = "女";
                            xb = 0+"";
                        }
                        final String finalSex = sex;
//                        com.zhy.http.okhttp.OkHttpUtils.get().url(ApiManager.ALL_URL + "api/member/profile/edit.jhtml")
//                                .addHeader("token", SPUtils.getInstance().getString(SPConstants.KEY_USER_TOKEN))
//                                .addParams("sex",xb)
//                                .build()
//                                .execute(new StringCallback() {
//                                    @Override
//                                    public void onError(Request request, Exception e) {
//
//                                    }
//
//                                    @Override
//                                    public void onResponse(String response) {
//                                        tv_sex.setText(finalSex);
//                                        Log.e("性别",response);
//
//                                    }
//                                });

                    }
                });
                builder.show();
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
        UserInfo();
    }

    @Override
    protected void initEvent() {

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
                        UserBean userBean;
                        userBean = gson.fromJson(response,UserBean.class);
                        tv_name.setText(userBean.getDataList().get(0).getNickName());
                        tv_phone.setText(userBean.getDataList().get(0).getMobile());
                        tv_xb.setText(userBean.getDataList().get(0).getGender());

                    }

                    @Override
                    public void onFailure(Exception e) {

                    }
                }
        );
    }
}
