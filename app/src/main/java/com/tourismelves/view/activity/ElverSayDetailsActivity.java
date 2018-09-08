package com.tourismelves.view.activity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.squareup.okhttp.Request;
import com.tourismelves.R;
import com.tourismelves.model.bean.ElversayBean;
import com.tourismelves.utils.ManagedMediaPlayer;
import com.tourismelves.utils.common.ToastUtil;
import com.tourismelves.utils.log.LogUtil;
import com.tourismelves.utils.pinyin.ApiManager;
import com.tourismelves.utils.system.SPUtils;
import com.tourismelves.view.activity.base.StateBaseActivity;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;
import com.zhy.http.okhttp.utils.Exceptions;

import java.io.File;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.tourismelves.utils.TimeUtil.calculateTime;

public class ElverSayDetailsActivity extends StateBaseActivity {


    String id ;
    ElversayBean elversayBean;
    WebView webView;
    TextView tv_title,tv_love,tv_time;
    ImageView im_love;
    String newContent;
    private ManagedMediaPlayer mMediaPlayer;
    SeekBar interpretationDetailsSeekbar;
    private boolean isTouchBar = false;
    String audioPath;
    AppCompatTextView interpretationDetailsTime;
    AppCompatImageView interpretationDetailsPlay;
    private Timer timer;
    LinearLayout ll_bottem;


    @Override
    protected void setContentLayout() {
        setContentView(R.layout.activity_elver_say_details);
    }

    @Override
    protected void initControls() {
        ll_bottem = findViewById(R.id.interpretation_details_ll);
        mMediaPlayer = new ManagedMediaPlayer();
        interpretationDetailsPlay = findViewById(R.id.interpretation_details_play);
        interpretationDetailsTime = findViewById(R.id.interpretation_details_time);
        interpretationDetailsSeekbar = findViewById(R.id.interpretation_details_seekbar);
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

                        String content =elversayBean.getDataList().get(0).getContent();
                        StringBuilder builder = new StringBuilder(content);
                        Pattern p= Pattern.compile("<audio.*src=\"(.*)\"></audio>");
                        Matcher matcher = p.matcher(content);
                        while (matcher.find()){
                            audioPath = matcher.group(1);
                            File file = new File(audioPath);
                            newContent = builder.delete(matcher.start(), matcher.end()).toString();
                            if (audioPath.equals("")){
                                ll_bottem.setVisibility(View.GONE);
                            }
                            play();
                        }
                        webView.loadData("<!DOCTYPE html><html><head><meta charset=\"utf-8\"><meta name=\"viewport\" content=\"width=device-width,initial-scale=1,user-scalable=0\"><title>任性猫</title><style type=\"text/css\">img{width: 100%;}</style></head><body>"+newContent+"</body></html>","text/html; charset=UTF-8", null);

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



        interpretationDetailsSeekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                isTouchBar = true;
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                isTouchBar = false;
                //首先获取seekbar拖动后的位置
                int progress = interpretationDetailsSeekbar.getProgress();
                if (mMediaPlayer != null) {
                    //跳转到某个位置播放
                    if (!audioPath.equals("")) {
                        mMediaPlayer.seekTo(progress);
                        setTime();
                    }
                }
                if (audioPath.equals("")) {
                    interpretationDetailsSeekbar.setProgress(0);
                }
            }
        });

        mMediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                cancelTimer();
                setTime();
                interpretationDetailsSeekbar.setProgress(0);
            }
        });
        interpretationDetailsPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    if (audioPath.equals("")) {
                        ToastUtil.show("播放失败，地址为空");
                        return;
                    }

                    if (mMediaPlayer.isPlaying()) {
                        mMediaPlayer.pause();
                        cancelTimer();
                    } else {
                        start();
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }

            }
        });
    }

    /**
     * 音乐播放地方
     */



    private void start() {
        interpretationDetailsPlay.setImageResource(R.mipmap.zantingbofang);
        mMediaPlayer.start();
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        setTime();
                        if (!isTouchBar) {
                            if (interpretationDetailsSeekbar != null) {
                                interpretationDetailsSeekbar.setMax(getDuration());
                                interpretationDetailsSeekbar.setProgress(getCurrentPosition());
                            }
                        }
                    }
                });
            }
        }, 0, 50);
    }

    private void cancelTimer() {
        if (interpretationDetailsPlay != null) {
            interpretationDetailsPlay.setImageResource(R.mipmap.bofangqi);
            interpretationDetailsTime.setText("0:00/0:00");
            interpretationDetailsSeekbar.setProgress(0);
        }
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
    }



    public int getCurrentPosition() {
        if (mMediaPlayer != null) {
            return mMediaPlayer.getCurrentPosition();
        }
        return 0;
    }

    public int getDuration() {
        if (mMediaPlayer != null) {
            return mMediaPlayer.getDuration();
        }
        return 0;
    }

    private void setTime() {
        if (interpretationDetailsTime != null) {
            String s = calculateTime(getDuration() / 1000);
            String s1 = calculateTime(getCurrentPosition() / 1000);
            interpretationDetailsTime.setText(s1 + "/" + s);
        }
    }

    public void play() {
        LogUtil.i(audioPath);
        try {
            if (audioPath.equals("")) {
                if (mMediaPlayer != null && mMediaPlayer.isPlaying()) {
                    mMediaPlayer.stop();
                    cancelTimer();
                }
                ToastUtil.show("播放失败，地址为空");
                return;
            }
            mMediaPlayer.reset();
            mMediaPlayer.setDataSource(audioPath);
            mMediaPlayer.prepareAsync();
            mMediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    start();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    protected void onDestroy() {
        mMediaPlayer.pause();
        mMediaPlayer.release();
        mMediaPlayer = null;
        cancelTimer();
        super.onDestroy();
    }

}
