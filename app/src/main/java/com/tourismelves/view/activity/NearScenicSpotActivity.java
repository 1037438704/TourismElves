package com.tourismelves.view.activity;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.tourismelves.R;
import com.tourismelves.utils.system.ResolutionUtil;
import com.tourismelves.view.activity.base.StateBaseActivity;
import com.tourismelves.view.adapter.FragmentAdapter;
import com.tourismelves.view.fragment.near.ScenicSpotFragment;
import com.tourismelves.view.fragment.near.ShoppingFragment;
import com.tourismelves.view.widget.SlidingTabLayout;
import com.tourismelves.view.widget.viewpager.NoScrollViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 附近景区
 */

public class NearScenicSpotActivity extends StateBaseActivity {

    @BindView(R.id.near_tab)
    SlidingTabLayout nearTab;
    @BindView(R.id.near_viewpager)
    NoScrollViewPager nearViewpager;

    private List<String> strings;
    private List<Fragment> fragments;
    private int tabWidth;

    @Override
    protected void setContentLayout() {
        setContentView(R.layout.activity_near_scenic_spot);
    }

    @Override
    protected void initControls() {
        showStateLayout(1);
        setBaseTitle("附近");
        setBaseRightImage(R.mipmap.search);
        tabWidth = (int) (ResolutionUtil.getInstance(getContext()).getWidth() / 2.2);

        strings = new ArrayList<>();
        fragments = new ArrayList<>();

        nearViewpager.setScroll(true);
    }

    @Override
    protected void obtainData() {
        strings.add("购物");
        strings.add("景区");

        nearTab.setTabWhite(tabWidth);

        fragments.add(new ShoppingFragment());
        fragments.add(new ScenicSpotFragment());


        nearViewpager.setAdapter(new FragmentAdapter(getSupportFragmentManager(), fragments, strings));
        nearViewpager.setOffscreenPageLimit(fragments.size());
        //两者关联
        nearTab.setupWithViewPager(nearViewpager);
    }

    @Override
    protected void initEvent() {
        setBaseRightListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), SearchActivity.class));
            }
        });

        nearViewpager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                nearTab.redrawIndicator(position, positionOffset);  //自定义指示器的滑动
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
}
