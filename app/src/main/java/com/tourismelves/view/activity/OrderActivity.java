package com.tourismelves.view.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.tourismelves.R;
import com.tourismelves.view.activity.base.StateBaseActivity;
import com.tourismelves.view.adapter.SubscribeAdapter;
import com.tourismelves.view.fragment.BuyFragment;
import com.tourismelves.view.fragment.WaitPayFragment;

import java.util.ArrayList;
import java.util.List;

public class OrderActivity extends StateBaseActivity {


    TabLayout tab_layout;
    ViewPager viewpager;
    List<Fragment> fragments = new ArrayList<>();
    List<String> list=new ArrayList<>();

    @Override
    protected void setContentLayout() {
        setContentView(R.layout.activity_order);
        initView();
    }

    private void initView() {
        tab_layout = (TabLayout) findViewById(R.id.order_layout);
        viewpager = (ViewPager) findViewById(R.id.order_viewpager);

        fragments.add(new WaitPayFragment());
        fragments.add(new BuyFragment());
//        list.add("待支付");
//        list.add("已购");
//        SubscribeAdapter subscribeAdapter = new SubscribeAdapter(getSupportFragmentManager(),fragments,list);
//        viewpager.setAdapter(subscribeAdapter);
//        tab_layout.setupWithViewPager(viewpager);
//        tab_layout.setTabsFromPagerAdapter(subscribeAdapter);
        SubscribeAdapter subscribeAdapter = new SubscribeAdapter(getSupportFragmentManager(),fragments,list);
        viewpager.setAdapter(subscribeAdapter);
        tab_layout.addTab(tab_layout.newTab().setCustomView(tab_icon("待支付",R.drawable.add_switch2)));
        tab_layout.addTab(tab_layout.newTab().setCustomView(tab_icon("已购",R.drawable.add_switch2)));
        //Tablayout自定义view绑定ViewPager
        viewpager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tab_layout));
        tab_layout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewpager));

    }

    @Override
    protected void initControls() {
      //  setStatusBar(R.id.select_city_status);
        showStateLayout(1);
        setBaseTitle("订单管理");
        showStateRightView(2);
        setStatusUi();
    }

    @Override
    protected void obtainData() {

    }

    @Override
    protected void initEvent() {

    }

    private View tab_icon(String name, int iconID) {
        View newtab = LayoutInflater.from(this).inflate(R.layout.footmark_item, null);
        TextView tv = newtab.findViewById(R.id.tabtext);
        tv.setText(name);
        ImageView im = newtab.findViewById(R.id.tabicon);
        im.setImageResource(iconID);
        return newtab;
    }
}
