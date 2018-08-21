package com.tourismelves.view.activity;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.squareup.okhttp.Request;
import com.tencent.connect.UserInfo;
import com.tencent.connect.auth.QQToken;
import com.tencent.connect.common.Constants;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;
import com.tourismelves.R;
import com.tourismelves.model.bean.LoginBean;
import com.tourismelves.model.net.OkHttpUtils;
import com.tourismelves.utils.common.ToastUtil;
import com.tourismelves.utils.pinyin.ApiManager;
import com.tourismelves.utils.system.SPUtils;
import com.tourismelves.view.activity.base.StateBaseActivity;
import com.tourismelves.view.widget.loadlayout.State;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import static com.tourismelves.app.constant.UrlConstants.login;

public class LoginActivity extends StateBaseActivity {

    //QQ登录
    private static final String TAG = "LoginActivity";
    private static final String APP_ID = "1107474298";//官方获取的APPID


    private Tencent mTencent;
    private UserInfo mUserInfo;
    private BaseUiListener mIUiListener;


    TextView tv_title;
    EditText ed_username,ed_pass;
    Button bt_login;
    TextView tv_register;
    ImageView im_qqlogin;
    @Override
    protected void setContentLayout() {
        setContentView(R.layout.activity_login);
        getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        mTencent = Tencent.createInstance(APP_ID,LoginActivity.this.getApplicationContext());
        initView();

    }

    private void initView() {
        tv_register = findViewById(R.id.login_register);
        bt_login = (Button) findViewById(R.id.bt_login_login);
        ed_username = (EditText) findViewById(R.id.login_username);
        ed_pass = (EditText) findViewById(R.id.login_pass);
        im_qqlogin = findViewById(R.id.login_qq);
//        tv_title = findViewById(R.id.title_name);
//        tv_title.setText("登录");
    }
    @Override
    protected void initControls() {
       // setStatusBar(R.id.select_city_status);
        setStatusUi();
    }

    @Override
    protected void obtainData() {

        im_qqlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mTencent.login(LoginActivity.this,"all",new BaseUiListener());
//                com.zhy.http.okhttp.OkHttpUtils.get()
//                        .addParams("qq",APP_ID)
//                        .url(ApiManager.LOGIN)
//                        .build()
//                        .execute(new StringCallback() {
//                            @Override
//                            public void onError(Request request, Exception e) {
//
//                            }
//
//                            @Override
//                            public void onResponse(String response) {
//
//                            }
//                        });
            }
        });

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
                                Gson gson = new Gson();
                                LoginBean loginBean;
                                loginBean = gson.fromJson(response,LoginBean.class);
                                String token = loginBean.getDataList().get(0).getToken();
                                String userid = loginBean.getDataList().get(0).getUserId()+"";
                                Log.e("登陆的token",token);
                                SPUtils.getInstance(LoginActivity.this).put("putInt",userid);//存储一个int值


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

            }
        });
    }

    @Override
    protected void initEvent() {

    }


    //QQ登录授权
    private class BaseUiListener implements IUiListener {

        @Override
        public void onComplete(Object response) {
            Toast.makeText(LoginActivity.this, "授权成功", Toast.LENGTH_SHORT).show();
            Log.e(TAG, "response:" + response);
            JSONObject obj = (JSONObject) response;
            try {
                final String   openID = obj.getString("openid");
                final String accessToken = obj.getString("access_token");
                Log.e("QQ登录",openID);
                String expires = obj.getString("expires_in");
                mTencent.setOpenId(openID);
                mTencent.setAccessToken(accessToken,expires);
                final QQToken qqToken = mTencent.getQQToken();
                mUserInfo = new UserInfo(getApplicationContext(),qqToken);
                mUserInfo.getUserInfo(new IUiListener() {
                    @Override
                    public void onComplete(Object response) {
//                        SPUtils.getInstance().put(SPConstants.KEY_USER_TOKEN, accessToken);
//                        Intent intent = new Intent(LoginActivity.this,MainTwoBanActivity.class);
//                        startActivity(intent);
                        Log.e("QQ登录的","登录成功"+response.toString());

                        com.zhy.http.okhttp.OkHttpUtils.get().url(ApiManager.ALL_URL+"api/login/qqcodeSubmit.jhtml")
                                .addParams("openId",openID)
                                .addParams("appId","1106154527")
                                .addParams("access_token",accessToken)
                                .build()
                                .execute(new StringCallback() {
                                    @Override
                                    public void onError(Request request, Exception e) {

                                    }

                                    @Override
                                    public void onResponse(String response) {


                                    }
                                });




                    }

                    @Override
                    public void onError(UiError uiError) {
                        Log.e(TAG,"登录失败"+uiError.toString());
                    }

                    @Override
                    public void onCancel() {
                        Log.e(TAG,"登录取消");

                    }
                });
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onError(UiError uiError) {
            Toast.makeText(LoginActivity.this, "授权失败", Toast.LENGTH_SHORT).show();

        }

        @Override
        public void onCancel() {
            Toast.makeText(LoginActivity.this, "授权取消", Toast.LENGTH_SHORT).show();

        }


    }
    /**
     * 在调用Login的Activity或者Fragment中重写onActivityResult方法
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == Constants.REQUEST_LOGIN){
            Tencent.onActivityResultData(requestCode,resultCode,data,mIUiListener);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
