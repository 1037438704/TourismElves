package com.tourismelves.view.activity;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.tourismelves.R;
import com.tourismelves.model.bean.LoginBean;
import com.tourismelves.model.net.OkHttpUtils;
import com.tourismelves.utils.common.ToastUtil;
import com.tourismelves.utils.system.SPUtils;
import com.tourismelves.view.activity.base.StateBaseActivity;
import com.tourismelves.view.widget.loadlayout.State;

import static com.tourismelves.app.constant.UrlConstants.login;

public class LoginActivity extends StateBaseActivity {

    TextView tv_title;
    EditText ed_username,ed_pass;
    Button bt_login;
    TextView tv_register;
    @Override
    protected void setContentLayout() {
        setContentView(R.layout.activity_login);
        initView();

    }

    private void initView() {
        tv_register = findViewById(R.id.login_register);
        bt_login = (Button) findViewById(R.id.bt_login_login);
        ed_username = (EditText) findViewById(R.id.login_username);
        ed_pass = (EditText) findViewById(R.id.login_pass);
        tv_title = findViewById(R.id.title_name);
        tv_title.setText("登录");
    }
    @Override
    protected void initControls() {
       // setStatusBar(R.id.select_city_status);
        setStatusUi();
    }

    @Override
    protected void obtainData() {

        tv_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });


        bt_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String user = ed_username.getText().toString();
                final String pass = ed_pass.getText().toString();
                OkHttpUtils.get(String.format(login, user, pass),
                        new OkHttpUtils.ResultCallback<String>(){
                            @Override
                            public void onSuccess(String  response) {

                                Log.e("登录接口",response);
//                                JSONObject object = JSON.parseObject(response);
//                                JSONArray list = object.getJSONArray("datalist");
                                //String token = list.get
                                Gson gson = new Gson();
                                LoginBean loginBean;
                                loginBean = gson.fromJson(response,LoginBean.class);
                                String token = loginBean.getDataList().get(0).getToken();
                                String userid = loginBean.getDataList().get(0).getUserId()+"";
                                Log.e("登陆的token",token);
                                SPUtils.getInstance(LoginActivity.this).put("putInt",userid);//存储一个int值
//                                SharedPreferences sp = getSharedPreferences("SP_PEOPLE", MODE_PRIVATE);
//                                SharedPreferences.Editor editor = sp.edit();
//                                editor.putString("token",token);
//                                editor.putString("userid",userid);
//
//                                editor.commit();

                                Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                                startActivity(intent);


                            }

                            @Override
                            public void onFailure(Exception e) {
                                getLoadLayout().setLayoutState(State.LOADING);
                                ToastUtil.show(R.string.no_found_network);
                            }
                        }
                );
//                com.zhy.http.okhttp.OkHttpUtils.get()
//                        .url("http://211.157.162.2/lyjl/web/login.do")
//                        .addParams("loginName",user)
//                        .addParams("password",pass)
//                        .build()
//                        .execute(new StringCallback() {
//                            @Override
//                            public void onError(Request request, Exception e) {
//
//                            }
//
//                            @Override
//                            public void onResponse(String response) {
//                                Log.e("登录接口",response);
//                            }
//                        });
            }
        });
    }

    @Override
    protected void initEvent() {

    }
}
