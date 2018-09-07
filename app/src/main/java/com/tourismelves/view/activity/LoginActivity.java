package com.tourismelves.view.activity;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.squareup.okhttp.Request;
import com.tencent.connect.UserInfo;
import com.tencent.connect.auth.QQToken;
import com.tencent.connect.common.Constants;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;
import com.tourismelves.R;
import com.tourismelves.model.bean.LoginBean;
import com.tourismelves.model.bean.RegisterBean;
import com.tourismelves.model.net.OkHttpUtils;
import com.tourismelves.utils.common.ToastUtil;
import com.tourismelves.utils.pinyin.ApiManager;
import com.tourismelves.utils.system.SPUtils;
import com.tourismelves.view.activity.base.StateBaseActivity;
import com.tourismelves.view.widget.loadlayout.State;
import com.tourismelves.wxapi.WXEntryActivity;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import static com.tourismelves.app.constant.UrlConstants.login;

public class LoginActivity extends StateBaseActivity {

    //QQ登录
    private static final String TAG = "LoginActivity";
    private static final String APP_ID = "1107474298";//官方获取的APPID


    //WXLOGIN
    private static final String APP_IDWX = "wx127d8dd53cf7aabc";
    private static IWXAPI WXapi;
    private Tencent mTencent;
    private UserInfo mUserInfo;
    private BaseUiListener mIUiListener;


    TextView tv_title;
    EditText ed_username,ed_pass;
    Button bt_login;
    TextView tv_register;
    ImageView im_qqlogin,im_wxlogin;

    ImageView im_register;
    LinearLayout ll_login,ll_register;
    TextView tv_login,tv_regi,tv_smscode;
    EditText ed_phone,ed_code,ed_password,ed_name;
    String mobile,code;
    Button bt_register;
    int register=1;


    @Override
    protected void setContentLayout() {
        setContentView(R.layout.activity_login);
        getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        mTencent = Tencent.createInstance(APP_ID,LoginActivity.this.getApplicationContext());
        initView();

    }

    private void initView() {

        im_register = findViewById(R.id.im_register);
        ed_name = findViewById(R.id.login_nickname);
        bt_register = findViewById(R.id.bt_login_register);
        tv_smscode = findViewById(R.id.tv_smscode);
        ed_password = findViewById(R.id.ed_password);
        ed_phone = findViewById(R.id.ed_phone);
        ed_code = findViewById(R.id.ed_smscode);
        tv_login = findViewById(R.id.tv_login);
        tv_regi = findViewById(R.id.tv_register);
        ll_login = findViewById(R.id.login_login);
        ll_register = findViewById(R.id.linear_register);
        im_wxlogin = findViewById(R.id.login_wx);
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
        setStatusBar(R.id.recharge_status);
        setStatusUi();
    }

    @Override
    protected void obtainData() {

        tv_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ll_login.setVisibility(View.VISIBLE);
                ll_register.setVisibility(View.GONE);
            }
        });
        tv_regi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ll_login.setVisibility(View.GONE);
                ll_register.setVisibility(View.VISIBLE);
            }
        });
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
        im_wxlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                WXLogin(1);
            }
        });

        //发送验证码

        tv_smscode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mobile = ed_phone.getText().toString();
                com.zhy.http.okhttp.OkHttpUtils.get()
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
                                Toast.makeText(LoginActivity.this, "发送成功", Toast.LENGTH_SHORT).show();
                                ll_login.setVisibility(View.GONE);
                                ll_register.setVisibility(View.VISIBLE);
                            }
                        });
            }
        });

        im_register.setTag(1);
        im_register.setImageResource(R.mipmap.chioce);
        im_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int isSelect = (int) v.getTag();


                if (isSelect==0){

                    v.setTag(1);
                    im_register.setImageResource(R.mipmap.chioce);
                    register=1;
                }else {

                    v.setTag(0);
                    im_register.setImageResource(R.mipmap.chioce_no);
                    register = 0;
                }

            }



        });

        //注册按钮
        bt_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String pass = ed_password.getText().toString();
                String code = ed_code.getText().toString();
                String name = ed_name.getText().toString();
                if (register==0){
                    Toast.makeText(LoginActivity.this, "请遵守用户协议", Toast.LENGTH_SHORT).show();
                    return;
                }
                com.zhy.http.okhttp.OkHttpUtils.get()
                        .url(ApiManager.ALL_URL+"lyjl/web/register.do")
                        .addParams("loginName",mobile)
                        .addParams("password",pass)
                        .addParams("smsCode",code)
                        .addParams("channel","android")
                        .addParams("shareCode","")
                        .addParams("nikeName",name)
                        .build()
                        .execute(new StringCallback() {
                            @Override
                            public void onError(Request request, Exception e) {

                            }

                            @Override
                            public void onResponse(String response) {

                                Log.e("注册",response);
                                Gson gson = new Gson();
                                RegisterBean registerBean;
                                registerBean = gson.fromJson(response,RegisterBean.class);
                                if (registerBean.getCode()==200){
                                    Toast.makeText(LoginActivity.this, registerBean.getMessage()+"", Toast.LENGTH_SHORT).show();
                                    ll_login.setVisibility(View.VISIBLE);
                                    ll_register.setVisibility(View.GONE);
                                }
                            }
                        });
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
            Log.e("QQ登录", "response:" + response);
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
                        JSONObject obj = (JSONObject) response;
                        try {
                            final String gender = obj.getString("gender");
                            final String nickName = obj.getString("nickname");
                            final String image = obj.getString("figureurl_2");


                            com.zhy.http.okhttp.OkHttpUtils.get().url(ApiManager.ALL_URL+"lyjl/web/login.do")
                                    .addParams("qq",openID)
                                    .addParams("gender",gender)
                                    .addParams("nickName",nickName)
                                    .addParams("image",image)
                                    .build()
                                    .execute(new StringCallback() {
                                        @Override
                                        public void onError(Request request, Exception e) {

                                        }

                                        @Override
                                        public void onResponse(String response) {


                                            Log.e("QQ登录",response);

                                        }
                                    });

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }





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
     * 登录微信
     */
    private void WXLogin(int statusCode) {

        WXEntryActivity.statusCode = statusCode;

        WXapi = WXAPIFactory.createWXAPI(this, "wx127d8dd53cf7aabc", true);
        WXapi.registerApp("wx127d8dd53cf7aabc");
        if (WXapi != null && WXapi.isWXAppInstalled()) {
            SendAuth.Req req = new SendAuth.Req();
            req.scope = "snsapi_userinfo";
            req.state = "wechat_sdk_demo_test_neng";
            boolean checkArgs = req.checkArgs();
            boolean sendReq = WXapi.sendReq(req);

            Log.d(TAG,"checkArgs - " + checkArgs + " sendReq - " + sendReq);


        } else
            Toast.makeText(this, "用户未安装微信", Toast.LENGTH_SHORT).show();
        Log.v("--------huitiao---","WXLogin");
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
