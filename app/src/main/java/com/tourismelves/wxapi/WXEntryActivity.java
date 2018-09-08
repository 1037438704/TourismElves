package com.tourismelves.wxapi;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.widget.Toast;

import com.squareup.okhttp.Request;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.tourismelves.utils.pinyin.ApiManager;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by 17251 on 2018/3/14.
 */

public class WXEntryActivity extends Activity implements IWXAPIEventHandler {

    //这个是状态标记
    public static int statusCode = 0;

   private static final String APP_SECRET = "Jls628bc32a7ed5210c31bb05f7162c0";
   // private static final String APP_SECRET = "aa47ffa84ddc5fa5786a34937e38ec67";
    private IWXAPI mWeixinAPI;
    public static final String WEIXIN_APP_ID = "wx127d8dd53cf7aabc";
    private static String uuid;
    public static String weixinCode="";


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mWeixinAPI = WXAPIFactory.createWXAPI(this, WEIXIN_APP_ID, true);
        mWeixinAPI.handleIntent(this.getIntent(), this);
    }




    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        mWeixinAPI.handleIntent(intent, this);//必须调用此句话
    }




    //微信发送的请求将回调到onReq方法
    @Override
    public void onReq(BaseReq req) {
       // LogUtils.log("onReq");
        Log.v("--------huitiao---","zoulereq");
    }

    //发送到微信请求的响应结果
    @Override
    public void onResp(BaseResp resp) {
       // LogUtils.log("onResp");



        Log.v("--------huitiao---","zoule");
        switch (resp.errCode) {
            case BaseResp.ErrCode.ERR_OK:
               // LogUtils.log("ERR_OK");
                //发送成功
                SendAuth.Resp sendResp = (SendAuth.Resp) resp;
                if (sendResp != null) {
                     weixinCode = sendResp.code;

                    doWXLogin(weixinCode);
//                    getAccess_token(code);
                }
                Log.v("--------huitiao---weixinCode",weixinCode);
                Log.e("jiji","codezhi--"+weixinCode);

                Log.v("--------huitiao---","ok");
                break;
            case BaseResp.ErrCode.ERR_USER_CANCEL:
              //  LogUtils.log("ERR_USER_CANCEL");
              //  LogUtils.log("ERR_USER_CANCEL");
                //发送取消
                Toast.makeText(this,"用户取消", Toast.LENGTH_SHORT).show();
                Log.v("--------huitiao---","ERR_USER_CANCEL");
                break;
            case BaseResp.ErrCode.ERR_AUTH_DENIED:
               // LogUtils.log("ERR_AUTH_DENIED");
                //发送被拒绝
                Toast.makeText(this,"用户拒绝授权", Toast.LENGTH_SHORT).show();
                Log.v("--------huitiao---","ERR_AUTH_DENIED");
                break;
            default:
                //发送返回
                break;

        }

    }

    /**
     * 微信授权后，登陆，传code给服务器
     * @param weixinCode
     */
    private void doWXLogin(final String weixinCode) {

//
//        OkHttpUtils.get()
//                .url(ApiManager.ALL_URL+"lyjl/web/login.do")
//                .addParams()



//        if (statusCode==1){
//            OkHttpUtils.get().url(ApiManager.WEIXIN_LOGIN)
//                    //weixinCode
//                    .addParams("code",weixinCode)
//                    .build()
//                    .execute(new StringCallback() {
//                        @Override
//                        public void onError(Request request, Exception e) {
//                            Toast.makeText(WXEntryActivity.this,"onError", Toast.LENGTH_LONG).show();
//                        }
//                        @Override
//                        public void onResponse(String response) {
//                        Gson gson = new Gson();
//                        WxBean wxBean = gson.fromJson(response,WxBean.class);
//                        String token = wxBean.getData().getToken();
//                        final String logincode = wxBean.getData().getCode();
//                        final String accountId = wxBean.getData().getAccountId();
//                        String code = wxBean.getCode();
//                        Log.e("微信登录，",response);
//
//                        if (code.equals("1")){
//                            WXEntryActivity.this.finish();
//                            if (logincode.equals("9001")){
//                                AlertDialog alertDialog = new AlertDialog.Builder(WXEntryActivity.this).setTitle("提示").setMessage("第三方登录需要绑定手机号，前往绑定？")
//                                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
//                                            @Override
//                                            public void onClick(DialogInterface dialogInterface, int i) {
//
//                                            }
//                                        }).setPositiveButton("确定", new DialogInterface.OnClickListener() {
//                                            @Override
//                                            public void onClick(DialogInterface dialogInterface, int i) {
//                                                Intent intent = new Intent(WXEntryActivity.this,ChangePhoneActivity.class);
//                                                intent.putExtra("logincode",logincode);
//                                                intent.putExtra("type",1+"");
//                                                intent.putExtra("accountId",accountId);
//                                                startActivity(intent);
//                                            }
//                                        }).create();
//                                alertDialog.show();
//                            }
//                            else {
//
//                                Log.e("weixincode",weixinCode);
//                                Intent intent = new Intent(WXEntryActivity.this,MainTwoBanActivity.class);
//                                SPUtils.getInstance().put(SPConstants.KEY_USER_TOKEN, token);
//                                startActivity(intent);
//                                finish();
//                            }
//                        }
                            ///状态码多少表示成功？1
//                            Log.e("微信登录",response);
//                            try {
//                                JSONObject jsonObject = new JSONObject(response);
//                                String msg = jsonObject.getString("msg");
//                                String code = jsonObject.getString("code");
//                                JSONObject dataObject = jsonObject.getJSONObject("data");
//                                final String logincode = dataObject.getString("code");
//                                final String accountId = dataObject.getString("accountId");
//                                String token = dataObject.getString("token");
//                                SPUtils.getInstance().put(SPConstants.KEY_USER_TOKEN, token);
//                                if (code.equals("1")){
//                                    if (logincode.equals("9001")){
//
//                                        AlertDialog alertDialog = new AlertDialog.Builder(WXEntryActivity.this).setTitle("提示").setMessage("第三方登录需要绑定手机号，前往绑定？")
//                                                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
//                                                    @Override
//                                                    public void onClick(DialogInterface dialogInterface, int i) {
//
//                                                        finish();
//
//
//
//                                                    }
//                                                }).setPositiveButton("确定", new DialogInterface.OnClickListener() {
//                                                    @Override
//                                                    public void onClick(DialogInterface dialogInterface, int i) {
//
//                                                        finish();
//                                                        Intent intent = new Intent(WXEntryActivity.this,BdPhoneActivity.class);
//                                                        intent.putExtra("logincode",logincode);
//                                                        intent.putExtra("type",1+"");
//                                                        intent.putExtra("accountId",accountId);
//                                                        startActivity(intent);
//                                                    }
//                                                }).create();
//                                        alertDialog.show();
//                                    }else {
//                                        Intent intent = new Intent(WXEntryActivity.this, MainTwoBanActivity.class);
//                                        Toast.makeText(WXEntryActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
//                                        startActivity(intent);
//                                        finish();
//                                    }
//
//                                }else if (code.equals("0")){
//                                    Toast.makeText(WXEntryActivity.this, msg+"", Toast.LENGTH_SHORT).show();
//                                }
//
//                            } catch (JSONException e) {
//                                e.printStackTrace();
//                            }
//
//
//
//
//                        }
//                    });
//        }else {
//
//
//            OkHttpUtils.get()
//                    .url(ApiManager.ALL_URL + "api/account/weiXinAccount.jhtml")
//                    .addHeader("token", SPUtils.getInstance().getString(SPConstants.KEY_USER_TOKEN))
//                    .addParams("code", weixinCode)
//                    .build().execute(new StringCallback() {
//                                @Override
//                                public void onError(Request request, Exception e) {
//
//                                }
//
//                                @Override
//                                public void onResponse(String response) {
//                                    Log.e("weixin绑定的地方", response);
//                                    try {
//                                        JSONObject jsonObject = new JSONObject(response);
//                                        String code = jsonObject.getString("code");
//                                        if (code.equals("1"))
//                                        {
//                                            finish();
//                                        }
//                                    } catch (JSONException e) {
//                                        e.printStackTrace();
//                                    }
//                                }
//                            });


//
//            OkHttpUtils.get().url(ApiManager.WEIXIN_LOGIN)
//                    //weixinCode
//                    .addParams("code",weixinCode)
//                    .build()
//                    .execute(new StringCallback() {
//                        @Override
//                        public void onError(Request request, Exception e) {
//
//                        }
//
//                        @Override
//                        public void onResponse(String response) {
//
//                            Log.e("微信登录",response);
//
//                            JSONObject jsonObject = null;
//                            try {
//                                jsonObject = new JSONObject(response);
//                                String msg = jsonObject.getString("msg");
//                                String code = jsonObject.getString("code");
//                            } catch (JSONException e) {
//                                e.printStackTrace();
//                            }
//
//
//                        }
//                    });
        }









    /**
     * 获取openid accessToken值用于后期操作
     * @param code 请求码
     */
    private void getAccess_token(final String code) {

        String path = "https://api.weixin.qq.com/sns/oauth2/access_token?appid="
                + WEIXIN_APP_ID
                + "&secret="
                + APP_SECRET
                + "&code="
                + code
                + "&grant_type=authorization_code";
      //  LogUtils.log("getAccess_token：" + path);
        //网络请求，根据自己的请求方式
//        OkHttpUtils.get(this, path, "getAccess_token", false, null, new OkHttpUtils.Callback() {
//            @Override
//            public void onSuccess(String result) {
//                LogUtils.log("getAccess_token_result:" + result);
//                JSONObject jsonObject = null;
//                try {
//                    jsonObject = new JSONObject(result);
//                    String openid = jsonObject.getString("openid").toString().trim();
//                    String access_token = jsonObject.getString("access_token").toString().trim();
//                    getUserMesg(access_token, openid);
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//
//            }
//
//            @Override
//            public void onError(String errorMessage) {
//
//            }
//        });

        OkHttpUtils.get().url(path)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Request request, Exception e) {

                    }
                    @Override
                    public void onResponse(String response) {
                        Log.e("------weixin-",response);
                        if (response.contains("access_token")){
                            try {
                                JSONObject jsonObject = new JSONObject(response);
                                String access_token = jsonObject.getString("access_token");

//                                JSONObject dataObject = jsonObject.getJSONObject("data");
//                                String token = dataObject.getString("token");
//                                SPUtils.getInstance().put(SPConstants.KEY_USER_TOKEN, token);






//                                if (msg.equals("登录成功")){
//                                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
//                                    Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
//                                    startActivity(intent);
//                                }else if (msg.equals("您输入的用户名或密码错误,请重新输入!")){
//                                    // Toast.makeText(application, "您输入的用户名或密码错误,请重新输入!", Toast.LENGTH_SHORT).show();
//                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }


    }
});


    }


    /**
     * 获取微信的个人信息
     * @param access_token
     * @param openid
     */
    private void getUserMesg(final String access_token, final String openid) {
        String path = "https://api.weixin.qq.com/sns/userinfo?access_token="
                + access_token
                + "&openid="
                + openid;
       // LogUtils.log("getUserMesg：" + path);
        //网络请求，根据自己的请求方式
//        VolleyRequest.get(this, path, "getAccess_token", false, null, new VolleyRequest.Callback() {
//            @Override
//            public void onSuccess(String result) {
//                LogUtils.log("getUserMesg_result:" + result);
//                JSONObject jsonObject = null;
//                try {
//                    jsonObject = new JSONObject(result);
//                    String nickname = jsonObject.getString("nickname");
//                    int sex = Integer.parseInt(jsonObject.get("sex").toString());
//                    String headimgurl = jsonObject.getString("headimgurl");
//
////                    LogUtils.log("用户基本信息:");
////                    LogUtils.log("nickname:" + nickname);
////                    LogUtils.log("sex:" + sex);
////                    LogUtils.log("headimgurl:" + headimgurl);
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//                finish();
//            }
//
//            @Override
//            public void onError(String errorMessage) {
//
//            }
//        });
    }
}
