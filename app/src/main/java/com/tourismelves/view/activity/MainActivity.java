package com.tourismelves.view.activity;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;
import android.widget.RelativeLayout;

import com.tourismelves.R;
import com.tourismelves.utils.system.LocationUtil;
import com.tourismelves.view.activity.base.StateBaseActivity;
import com.tourismelves.view.adapter.FragmentAdapter;
import com.tourismelves.view.fragment.ElfSaidFragment;
import com.tourismelves.view.fragment.HomeFragment;
import com.tourismelves.view.fragment.MyFragment;
import com.tourismelves.view.fragment.ScenicSpotFragment;
import com.tourismelves.view.widget.viewpager.NoScrollViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends StateBaseActivity {
    @BindView(R.id.main_search_layout)
    RelativeLayout mainSearchLayout;
    @BindView(R.id.main_viewpager)
    NoScrollViewPager mainViewpager;
    @BindView(R.id.main_home)
    AppCompatTextView mainHome;
    @BindView(R.id.main_scenic_spot)
    AppCompatTextView mainScenicSpot;
    @BindView(R.id.main_elf_said)
    AppCompatTextView mainElfSaid;
    @BindView(R.id.main_my)
    AppCompatTextView mainMy;
    //存储Fragment
    private List<Fragment> fragments;
    //存储底部tab
    private List<AppCompatTextView> tabs;
    private HomeFragment homeFragment;
    private ScenicSpotFragment scenicSpotFragment;
    private ElfSaidFragment elfSaidFragment;
    private MyFragment myFragment;


    @Override
    protected void setContentLayout() {
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void initControls() {
        homeFragment = new HomeFragment();
        scenicSpotFragment = new ScenicSpotFragment();
        elfSaidFragment = new ElfSaidFragment();
        myFragment = new MyFragment();
        fragments = new ArrayList<>();
        tabs = new ArrayList<>();

        //请求获取经纬度
        LocationUtil.getInstance(getContext());
    }

    @Override
    protected void obtainData() {
        fragments.add(homeFragment);
        fragments.add(scenicSpotFragment);
        fragments.add(elfSaidFragment);
        fragments.add(myFragment);

        tabs.add(mainHome);
        tabs.add(mainScenicSpot);
        tabs.add(mainElfSaid);
        tabs.add(mainMy);
        FragmentAdapter fragmentAdapter = new FragmentAdapter(fm, fragments);
        mainViewpager.setAdapter(fragmentAdapter);
        mainViewpager.setOffscreenPageLimit(fragments.size());

        selectTab(0);
    }

    @Override
    protected void initEvent() {

    }

    @OnClick({R.id.main_search, R.id.main_home, R.id.main_scenic_spot, R.id.main_elf_said, R.id.main_my})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.main_search://搜索
                startActivity(new Intent(getContext(), SearchActivity.class));
                break;
            case R.id.main_home:        //首页
                selectTab(0);
                break;
            case R.id.main_scenic_spot: //景区
                selectTab(1);
                break;
            case R.id.main_elf_said:
                selectTab(2);   //精灵说
                break;
            case R.id.main_my:
                selectTab(3);   //我的
                break;
        }
    }

    private void selectTab(int position) {
        //初始化
        for (int i = 0; i < tabs.size(); i++) {
            AppCompatTextView appCompatTextView = tabs.get(i);
            appCompatTextView.setSelected(false);
            appCompatTextView.setTextColor(0xff000000);
        }
        //设置选中的样式
        AppCompatTextView appCompatTextView = tabs.get(position);
        appCompatTextView.setSelected(true);
        appCompatTextView.setTextColor(0xff4585f5);

        //是否隐藏搜索栏
        if (position == 3) {
            mainSearchLayout.setVisibility(View.GONE);
        } else {
            mainSearchLayout.setVisibility(View.VISIBLE);
        }

        //切换Fragment 无动画
        mainViewpager.setCurrentItem(position, false);
    }
}
