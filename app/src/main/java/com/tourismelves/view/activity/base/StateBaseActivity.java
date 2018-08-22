package com.tourismelves.view.activity.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.tourismelves.R;
import com.tourismelves.view.activity.SearchActivity;
import com.tourismelves.view.activity.SelectCityActivity;
import com.tourismelves.view.widget.loadlayout.LoadLayout;

import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * 加载布局（正文，加载中，加载失败，无数据）的activity基类
 * 子类不需要再绑定ButterKnife
 * 实现setContentLayout来设置布局ID，
 * 实现initView来做视图相关的初始化，
 * 实现obtainData来做数据的初始化
 * 实现initEvent来做事件监听的初始化
 */
public abstract class StateBaseActivity extends BaseActivity implements View.OnClickListener {

    private AppCompatTextView basePositioning;
    private AppCompatImageView baseWeather;
    private LinearLayout baseSearchLayout;
    private AppCompatTextView baseTitle;
    private View baseStatus;
    private AppCompatImageView baseRight;
    private AppCompatTextView baseRightTv;
    private LinearLayout baseSearch;
    private AppCompatImageView baseBack;
    private RelativeLayout baseTitleLayout;
    private LoadLayout mLoadLayout;//加载布局，可以显示各种状态的布局, 如加载中，加载成功, 加载失败, 无数据
    private Unbinder bind;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void setContentView(int layoutResId) {
        super.setContentView(R.layout.activity_base_toolbar);
        addViewToContainer(layoutResId);
        init();
    }

    //将布局加入到LoadLayout中
    public void addViewToContainer(int layoutResId) {
        mLoadLayout = findViewById(R.id.base_content_layout);
        View view = getLayoutInflater().inflate(layoutResId, null);
        mLoadLayout.removeAllViews();
        mLoadLayout.addSuccessView(view);
    }

    public void init() {
        bind = ButterKnife.bind(this, this);
        setStatusBar(R.id.base_status);
        basePositioning = findViewById(R.id.base_positioning);
        baseWeather = findViewById(R.id.base_weather);
        baseStatus = findViewById(R.id.base_status);
        baseSearchLayout = findViewById(R.id.base_search_layout);
        baseTitle = findViewById(R.id.base_title);
        baseRight = findViewById(R.id.base_right);
        baseRightTv = findViewById(R.id.base_right_tv);
        baseTitleLayout = findViewById(R.id.base_title_layout);
        baseSearch = findViewById(R.id.base_search);
        baseBack = findViewById(R.id.base_back);

        basePositioning.setOnClickListener(this);
        baseSearch.setOnClickListener(this);
        baseBack.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.base_positioning:
                startActivity(new Intent(this, SelectCityActivity.class));
                break;
            case R.id.base_search:
                startActivity(new Intent(this, SearchActivity.class));
                break;
            case R.id.base_back:
                finish();
                break;
        }
    }

    /**
     * 获取加载布局，从而设置各种加载状态
     */
    public LoadLayout getLoadLayout() {
        return mLoadLayout;
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mLoadLayout != null) {
            mLoadLayout.closeAnim();
        }
    }

    @Override
    public String getResourceString(int stringId) {
        return super.getResourceString(stringId);
    }

    @Override
    protected void onDestroy() {
        if (bind != null)
            bind.unbind();
        super.onDestroy();
    }


    /**
     * 显示那种标题栏
     * 0:搜索  1:标题  -1:显示状态栏  其他:全部隐藏
     */
    protected void showStateLayout(int state) {
        baseSearchLayout.setVisibility(state == 0 ? View.VISIBLE : View.GONE);
        baseTitleLayout.setVisibility(state == 1 ? View.VISIBLE : View.GONE);

        baseStatus.setVisibility((state == 1 || state == 0||state==-1) ? View.VISIBLE : View.GONE);
    }

    /**
     * 显示右边类型
     * 0:TextView  1:ImageView
     */
    protected void showStateRightView(int state) {
        baseRightTv.setVisibility(state == 0 ? View.VISIBLE : View.GONE);
        baseRight.setVisibility(state == 1 ? View.VISIBLE : View.GONE);
    }

    /**
     * 设置右边按钮事件
     */
    protected void setBaseRightListener(View.OnClickListener listener) {
        baseRight.setOnClickListener(listener);
    }

    protected void setBaseRightTvListener(View.OnClickListener listener) {
        baseRightTv.setOnClickListener(listener);
    }

    /**
     * 设置右边按钮图片
     */
    protected void setBaseRightImage(int resImg) {
        baseRight.setImageResource(resImg);
    }

    protected void setBaseTitle(@StringRes int resStr) {
        baseTitle.setText(getString(resStr));
    }

    protected void setBaseTitle(@NonNull String resStr) {
        baseTitle.setText(resStr);
    }

    protected void setBaseRightTv(@NonNull String resStr) {
        baseRightTv.setText(resStr);
    }

    protected void setBaseRightTv(@StringRes int resStr) {
        baseRightTv.setText(getString(resStr));
    }

    public void setBasePositioning(@NonNull String resStr) {
        basePositioning.setText(resStr.equals("") ? "定位" : resStr);
    }

    public String getBasePositioning() {
        return basePositioning.getText().toString().equals("定位") ? "" : basePositioning.getText().toString();
    }
}
