package com.tourismelves.view.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.squareup.okhttp.Request;
import com.tourismelves.R;
import com.tourismelves.model.bean.ElversayBean;
import com.tourismelves.utils.pinyin.ApiManager;
import com.tourismelves.utils.system.SPUtils;
import com.tourismelves.view.activity.base.StateBaseActivity;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

public class ElverSayDetailsActivity extends StateBaseActivity {


    String id ;
    ElversayBean elversayBean;
    WebView webView;
    TextView tv_title,tv_love,tv_time;
    ImageView im_love;


    @Override
    protected void setContentLayout() {
        setContentView(R.layout.activity_elver_say_details);
    }

    @Override
    protected void initControls() {
        im_love = findViewById(R.id.details_love);
        tv_title = findViewById(R.id.say_details_title);
        tv_love = findViewById(R.id.elfsaid_collect);
        tv_time = findViewById(R.id.details_time);
        webView = findViewById(R.id.webview);
        Intent intent = getIntent();
        String name = intent.getStringExtra("titlename");
        id = intent.getStringExtra("acticleid");
        String time = intent.getStringExtra("time");
        String hot = intent.getStringExtra("hot");

        showStateLayout(1);
        setBaseTitle(name);
        tv_title.setText(name);
        tv_time.setText(time);
        tv_love.setText(hot);
        showStateRightView(2);

    }

    @Override
    protected void obtainData() {

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

        OkHttpUtils.get().url(ApiManager.ALL_URL+"lyjl/web/newDetails.do")
                .addParams("id",id)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Request request, Exception e) {

                    }

                    @Override
                    public void onResponse(String response) {

                        Log.e("精灵说详情",response);
                        Gson gson = new Gson();
                        elversayBean = gson.fromJson(response,ElversayBean.class);

                        webView.loadData("<!DOCTYPE html><html><head><meta charset=\"utf-8\"><meta name=\"viewport\" content=\"width=device-width,initial-scale=1,user-scalable=0\"><title>任性猫</title><style type=\"text/css\">img{width: 100%;}</style></head><body>"+elversayBean.getDataList().get(0).getContent()+"</body></html>","text/html; charset=UTF-8", null);

//
//                        tv_time.setText(elversayBean.getDataList().get(0).getPublishTime());
//                        tv_love.setText(elversayBean.getDataList().get(0).getPraise());
                    }
                });

        im_love.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OkHttpUtils.get().url(ApiManager.ALL_URL+"lyjl/app/praiseArticle.do")
                        .addParams("articleId",id+"")
                        .addParams("userId", SPUtils.getInstance(ElverSayDetailsActivity.this).getString("putInt"))
                        .build()
                        .execute(new StringCallback() {
                            @Override
                            public void onError(Request request, Exception e) {

                            }

                            @Override
                            public void onResponse(String response) {


                                Log.e("精灵说点赞",response);
                                im_love.setImageResource(R.mipmap.icon_16px_sc_1);
                                Toast.makeText(ElverSayDetailsActivity.this, "点赞成功", Toast.LENGTH_SHORT).show();


                            }
                        });
            }
        });
    }

    @Override
    protected void initEvent() {

    }


}
