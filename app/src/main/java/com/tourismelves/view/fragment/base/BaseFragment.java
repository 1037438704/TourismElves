package com.tourismelves.view.fragment.base;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.res.ResourcesCompat;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.tourismelves.R;
import com.tourismelves.view.activity.base.BaseActivity;
import com.tourismelves.view.widget.loadlayout.LoadLayout;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseFragment extends Fragment implements IBaseFragment {

    protected BaseActivity mActivity;
    //加载布局，可用于设置各种加载状态，也是根布局视图
    private LoadLayout mLoadLayout;
    //根布局视图
    protected View mContentView;

    protected int mWidth;
    protected int mHeight;
    protected FragmentManager fm;

    //用于butterknife解绑
    private Unbinder unbinder;
    private boolean isLoaded;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (mContentView == null) {
            mContentView = inflater.inflate(R.layout.fragment_base, container, false);
            View view = inflater.inflate(setContentLayout(), null);
            mLoadLayout = (LoadLayout) mContentView;
            mLoadLayout.addSuccessView(view);
            unbinder = ButterKnife.bind(this, mContentView);
        }

        WindowManager manager = getActivity().getWindowManager();
        DisplayMetrics outMetrics = new DisplayMetrics();
        manager.getDefaultDisplay().getMetrics(outMetrics);
        mWidth = outMetrics.widthPixels;
        mHeight = outMetrics.heightPixels;
        fm = getChildFragmentManager();
        return mContentView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (!isLoaded) {
            isLoaded = true;
            initView(getView());
            obtainData();
            initEvent();
        }
    }

    //设置并返回布局ID
    protected abstract int setContentLayout();

    //做视图相关的初始化
    protected abstract void initView(View view);

    //来做数据的初始化
    protected abstract void obtainData();

    //做事件监听的初始化
    protected abstract void initEvent();


    //获取加载布局，从而设置各种加载状态
    public LoadLayout getLoadLayout() {
        return mLoadLayout;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = (BaseActivity) context;
    }

    /**
     * 这个函数用于移除根视图
     * 因为已经有过父布局的View是不能再次添加到另一个新的父布局上面的
     */
    @Override
    public void onDestroyView() {
        if (mContentView != null) {
            ViewGroup parent = (ViewGroup) mContentView.getParent();
            if (parent != null) {
                parent.removeView(mContentView);
            }
        }
        //保存数据
        super.onDestroyView();
        //ButterKnife解绑
        if (unbinder != null) unbinder.unbind();
    }

    public void onResume() {
        super.onResume();
    }

    public void onPause() {
        super.onPause();
    }


    @Override
    public int getResourceColor(@ColorRes int colorId) {
        return isAdded() ? ResourcesCompat.getColor(getResources(), colorId, null) : 0;
    }

    @Override
    public String getResourceString(@StringRes int stringId) {
        return isAdded() ? getResources().getString(stringId) : null;
    }

    @Override
    public Drawable getResourceDrawable(@DrawableRes int id) {
        return isAdded() ? ResourcesCompat.getDrawable(getResources(), id, null) : null;
    }

}
