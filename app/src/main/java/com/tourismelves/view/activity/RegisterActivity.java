package com.tourismelves.view.activity;

import android.os.Build;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.squareup.okhttp.Request;
import com.tourismelves.R;
import com.tourismelves.view.activity.base.StateBaseActivity;
import com.zhy.http.okhttp.callback.StringCallback;

public class RegisterActivity extends StateBaseActivity {

    TextView tv_title;
    EditText ed_phone,ed_code,ed_smscode,ed_password;
    TextView tv_smscode;
    Button bt_gister;
    String mobile,code;
    WebView webView ;



    @Override
    protected void setContentLayout() {
        setContentView(R.layout.activity_register);
        initView();
    }

    private void initView() {
        webView = findViewById(R.id.webview);
        ed_password = findViewById(R.id.ed_password);
        ed_smscode  = findViewById(R.id.ed_dxcode);
        bt_gister = findViewById(R.id.bt_login_register);
        tv_title = findViewById(R.id.title_name);
        tv_title.setText("手机注册");
        ed_phone = findViewById(R.id.ed_phone);
        tv_smscode = findViewById(R.id.tv_smscode);
        ed_code = findViewById(R.id.ed_smscode);
        webview();
    }

    @Override
    protected void initControls() {
        //setStatusBar(R.id.select_city_status);
        setStatusUi();
    }

    @Override
    protected void obtainData() {


        webView.loadUrl("http://211.157.162.2/lyjl/web/getAppVerifyCode.do?randomCode=123456");
        webView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                webView.loadUrl("http://211.157.162.2/lyjl/web/getAppVerifyCode.do?randomCode=123456");
                webView.reload();
            }
        });
//
//        tv_smscode.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                final String mobile = ed_phone.getText().toString();
//                final String code = ed_code.getText().toString();
//                OkHttpUtils.get(String.format("123456", mobile, code),
//                        new OkHttpUtils.ResultCallback<String>() {
//                            @Override
//                            public void onSuccess(String response) {
//                                Log.e("获取验证码",response);
//                            }
//
//                            @Override
//                            public void onFailure(Exception e) {
//
//
//                            }
//                        }
//                );
//            }
//        });


        com.zhy.http.okhttp.OkHttpUtils.post()
                .url("http://211.157.162.2/lyjl/web/getAppVerifyCode.do")
                .addParams("randomCode","123456")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Request request, Exception e) {

                    }

                    @Override
                    public void onResponse(String response) {

                        Log.e("图形验证码",response);
                    }
                });



        tv_smscode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 mobile = ed_phone.getText().toString();
                 code = ed_code.getText().toString();
                com.zhy.http.okhttp.OkHttpUtils.post()
                        .url("http://211.157.162.2/lyjl/web/getAppSMSCode.do")
                        .addParams("randomCode","123456")
                        .addParams("mobile",mobile)
                        .addParams("code",code)
                        .build()
                        .execute(new StringCallback() {
                            @Override
                            public void onError(Request request, Exception e) {

                            }

                            @Override
                            public void onResponse(String response) {

                                Log.e("获取验证码",response);
                            }
                        });
            }
        });

        bt_gister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // mobile = ed_phone.getText().toString();
                String smscode = ed_smscode.getText().toString();
                String password = ed_password.getText().toString();
                com.zhy.http.okhttp.OkHttpUtils.get()
                        .url("http://211.157.162.2/lyjl/web/getAppSMSCode.do")
                        .addParams("loginName",mobile)
                        .addParams("password",password)
                        .addParams("randomcode",smscode+"")
                        .addParams("channel","android")
                        .addParams("randomCode","123456")
                        .build()
                        .execute(new StringCallback() {
                            @Override
                            public void onError(Request request, Exception e) {
                            }
                            @Override
                            public void onResponse(String response) {
                                Log.e("账号注册",response);
                            }
                        });
            }
        });


    }

    @Override
    protected void initEvent() {

    }


    private void webview(){
        //声明WebSettings子类
        WebSettings webSettings = webView.getSettings();

//如果访问的页面中要与Javascript交互，则webview必须设置支持Javascript
        webSettings.setJavaScriptEnabled(true);
//设置自适应屏幕，两者合用
        webSettings.setUseWideViewPort(true); //将图片调整到适合webview的大小
        webSettings.setLoadWithOverviewMode(true); // 缩放至屏幕的大小
//
        //缩放操作
        webSettings.setSupportZoom(true); //支持缩放，默认为true。是下面那个的前提。
        webSettings.setBuiltInZoomControls(true); //设置内置的缩放控件。若为false，则该WebView不可缩放
        webSettings.setDisplayZoomControls(false); //隐藏原生的缩放控件

        //其他细节操作
        webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK); //关闭webview中缓存
        webSettings.setAllowFileAccess(true); //设置可以访问文件
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true); //支持通过JS打开新窗口
        webSettings.setLoadsImagesAutomatically(true); //支持自动加载图片
        webSettings.setDefaultTextEncodingName("utf-8");//设置编码格式
        //优先使用缓存:
        webView.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        //缓存模式如下：
        //LOAD_CACHE_ONLY: 不使用网络，只读取本地缓存数据
        //LOAD_DEFAULT: （默认）根据cache-control决定是否从网络上取数据。
        //LOAD_NO_CACHE: 不使用缓存，只从网络获取数据.
        //LOAD_CACHE_ELSE_NETWORK，只要本地有，无论是否过期，或者no-cache，都使用缓存中的数据。

        //不使用缓存:
        webView.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);


        WebSettings settings = webView.getSettings();
        //    settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        DisplayMetrics metrics = new DisplayMetrics();
        // getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int mDensity = metrics.densityDpi;


        if (mDensity == 120) {
            settings.setDefaultZoom(WebSettings.ZoomDensity.CLOSE);
        }else if (mDensity == 160) {
            settings.setDefaultZoom(WebSettings.ZoomDensity.MEDIUM);
        }else if (mDensity == 240) {
            settings.setDefaultZoom(WebSettings.ZoomDensity.FAR);
        }


        webView.setWebViewClient(new WebViewClient() {

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                //该方法在Build.VERSION_CODES.LOLLIPOP以前有效，从Build.VERSION_CODES.LOLLIPOP起，建议使用shouldOverrideUrlLoading(WebView, WebResourceRequest)} instead
                //返回false，意味着请求过程里，不管有多少次的跳转请求（即新的请求地址），均交给webView自己处理，这也是此方法的默认处理
                //返回true，说明你自己想根据url，做新的跳转，比如在判断url符合条件的情况下，我想让webView加载http://ask.csdn.net/questions/178242

                if (url.toString().contains("sina.cn")){
                    view.loadUrl("http://shop.rxmao.cn/rxm/index.html");
                    return true;
                }

                return false;
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request)
            {
                //返回false，意味着请求过程里，不管有多少次的跳转请求（即新的请求地址），均交给webView自己处理，这也是此方法的默认处理
                //返回true，说明你自己想根据url，做新的跳转，比如在判断url符合条件的情况下，我想让webView加载http://ask.csdn.net/questions/178242
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    if (request.getUrl().toString().contains("sina.cn")){
                        view.loadUrl("http://shop.rxmao.cn/rxm/index.html");
                        return true;
                    }
                }

                return false;
            }

        });

    }
}
