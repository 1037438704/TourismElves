package com.tourismelves.view.activity.base;

import android.os.Bundle;
import android.view.View;

import com.tourismelves.R;
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
public abstract class StateBaseActivity extends BaseActivity {

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
}
