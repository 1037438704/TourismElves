package com.tourismelves.view.activity;

import android.location.Address;
import android.location.Location;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.AppCompatImageView;
import android.view.View;

import com.tourismelves.R;
import com.tourismelves.utils.log.LogUtil;
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

/**
 * 主页
 */
public class MainActivity extends StateBaseActivity {
    @BindView(R.id.main_viewpager)
    NoScrollViewPager mainViewpager;
    @BindView(R.id.main_icon_home)
    AppCompatImageView mainIconHome;
    @BindView(R.id.main_icon_scenic_spot)
    AppCompatImageView mainIconScenicSpot;
    @BindView(R.id.main_icon_ely_said)
    AppCompatImageView mainIconElySaid;
    @BindView(R.id.main_icon_my)
    AppCompatImageView mainIconMy;
    //存储底部导航icon
    private List<AppCompatImageView> imageViews;
    //存储Fragment
    private List<Fragment> fragments;
    private HomeFragment homeFragment;
    private ScenicSpotFragment scenicSpotFragment;
    private ElfSaidFragment elfSaidFragment;
    private MyFragment myFragment;
    private LocationUtil instance;
    private Location location;


    @Override
    protected void setContentLayout() {
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void initControls() {
        showStateLayout(0);
        homeFragment = new HomeFragment();
        scenicSpotFragment = new ScenicSpotFragment();
        elfSaidFragment = new ElfSaidFragment();
        myFragment = new MyFragment();
        imageViews = new ArrayList<>();
        fragments = new ArrayList<>();

        instance = LocationUtil.getInstance(getContext());
        location = instance.showLocation();
        new Thread() {
            @Override
            public void run() {
                super.run();
                //获取位置
                List<Address> address = instance.getAddress(getContext(), location);
                LogUtil.i(address);
                if (address != null) {
                    if (address.size() > 0) {
                        Address address1 = address.get(0);
                        String adminArea = address1.getAdminArea();
                        LogUtil.i(address1);
                        setBasePositioning(adminArea);
                    }
                }
            }
        }.start();
    }

    @Override
    protected void obtainData() {
        imageViews.add(mainIconHome);
        imageViews.add(mainIconScenicSpot);
        imageViews.add(mainIconElySaid);
        imageViews.add(mainIconMy);

        fragments.add(homeFragment);
        fragments.add(scenicSpotFragment);
        fragments.add(elfSaidFragment);
        fragments.add(myFragment);

        FragmentAdapter fragmentAdapter = new FragmentAdapter(fm, fragments);
        mainViewpager.setAdapter(fragmentAdapter);
        mainViewpager.setOffscreenPageLimit(fragments.size());

        selectTab(0);

    }

    @Override
    protected void initEvent() {
        mainViewpager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position == 3) {
                    showStateLayout(2);
                } else {
                    showStateLayout(0);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @OnClick({R.id.main_home, R.id.main_scenic_spot, R.id.main_elf_said, R.id.main_my})
    public void onViewClicked(View view) {
        switch (view.getId()) {
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
        for (int i = 0; i < imageViews.size(); i++) {
            imageViews.get(i).setSelected(false);
        }
        //设置选中的样式
        imageViews.get(position).setSelected(true);

        //切换Fragment 无动画
        mainViewpager.setCurrentItem(position, false);
    }
}
