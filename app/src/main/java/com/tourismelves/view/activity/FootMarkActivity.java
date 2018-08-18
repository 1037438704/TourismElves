package com.tourismelves.view.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.widget.TextView;

import com.tourismelves.R;
import com.tourismelves.view.activity.base.StateBaseActivity;
import com.tourismelves.view.adapter.SubscribeAdapter;
import com.tourismelves.view.fragment.ScenicFragment;
import com.tourismelves.view.fragment.WaitPayFragment;

import java.util.ArrayList;
import java.util.List;

public class FootMarkActivity extends StateBaseActivity {

    TextView tv_title;
    TabLayout tab_layout;
    ViewPager viewpager;
    List<Fragment> fragments = new ArrayList<>();
    List<String> list=new ArrayList<>();

    @Override
    protected void setContentLayout() {
        setContentView(R.layout.activity_foot_mark);
        initView();
    }


    private void initView() {
        tab_layout = (TabLayout) findViewById(R.id.order_layout);
        viewpager = (ViewPager) findViewById(R.id.order_viewpager);
        tv_title = findViewById(R.id.title_name);
        tv_title.setText("足迹");
        fragments.add(new ScenicFragment());
        fragments.add(new WaitPayFragment());
        list.add("景区");
        list.add("精灵说");
        SubscribeAdapter subscribeAdapter = new SubscribeAdapter(getSupportFragmentManager(),fragments,list);
        viewpager.setAdapter(subscribeAdapter);
        tab_layout.setupWithViewPager(viewpager);
        tab_layout.setTabsFromPagerAdapter(subscribeAdapter);
    }

    @Override
    protected void initControls() {
     //   setStatusBar(R.id.select_city_status);
        setStatusUi();
    }

    @Override
    protected void obtainData() {

    }

    @Override
    protected void initEvent() {

    }
}
