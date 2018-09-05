package com.tourismelves.view.activity;

import android.Manifest;
import android.os.Process;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.view.KeyEvent;
import android.view.View;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.tourismelves.R;
import com.tourismelves.model.event.SelectCityBus;
import com.tourismelves.model.event.TabSelectBus;
import com.tourismelves.utils.common.EventBusUtil;
import com.tourismelves.utils.common.ToastUtil;
import com.tourismelves.utils.log.LogUtil;
import com.tourismelves.utils.system.PermissionUtil;
import com.tourismelves.view.activity.base.CheckPermissionsActivity;
import com.tourismelves.view.adapter.FragmentAdapter;
import com.tourismelves.view.fragment.ElfSaidFragment;
import com.tourismelves.view.fragment.HomeFragment;
import com.tourismelves.view.fragment.MyFragment;
import com.tourismelves.view.fragment.ScenicSpotFragment;
import com.tourismelves.view.widget.viewpager.NoScrollViewPager;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

import static com.tourismelves.app.constant.CommentConstants.address;
import static com.tourismelves.app.constant.CommentConstants.latitude;
import static com.tourismelves.app.constant.CommentConstants.longitude;

/**
 * 主页
 */
public class MainActivity extends CheckPermissionsActivity {
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
    private List<AppCompatTextView> tabText;
    private HomeFragment homeFragment;
    private ScenicSpotFragment scenicSpotFragment;
    private ElfSaidFragment elfSaidFragment;
    private MyFragment myFragment;
    public static String city = "";

    private AMapLocationClient locationClient = null;
    private AMapLocationClientOption locationOption = null;


    @Override
    public void onResume() {
        needPermissions = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.READ_PHONE_STATE};
        super.onResume();
    }

    @Override
    protected void setContentLayout() {
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void initControls() {
        EventBusUtil.register(this);
        showStateLayout(0);
        homeFragment = new HomeFragment();
        scenicSpotFragment = new ScenicSpotFragment();
        elfSaidFragment = new ElfSaidFragment();
        myFragment = new MyFragment();
        imageViews = new ArrayList<>();
        fragments = new ArrayList<>();
        tabText = new ArrayList<>();

        locationOption = new AMapLocationClientOption();
        //初始化定位
        initLocation();

        boolean b = PermissionUtil.requestPerssions(this, 99, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION);
        if (b) {
            startLocation();
        }
    }

    @Override
    protected void obtainData() {
        tabText.add((AppCompatTextView) findViewById(R.id.main_tv_home));
        tabText.add((AppCompatTextView) findViewById(R.id.main_tv_scenic_spot));
        tabText.add((AppCompatTextView) findViewById(R.id.main_tv_ely_said));
        tabText.add((AppCompatTextView) findViewById(R.id.main_tv_my));

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
                    showStateLayout(-1);
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
            tabText.get(i).setTextColor(0xff909090);
        }
        //设置选中的样式
        imageViews.get(position).setSelected(true);
        tabText.get(position).setTextColor(0xff3F8DD9);

        //切换Fragment 无动画
        mainViewpager.setCurrentItem(position, false);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PermissionUtil.onRequestPermissionsResult(requestCode, permissions, grantResults, new PermissionUtil.OnRequestPermissionsResultCallbacks() {
            @Override
            public void onPermissionsGranted(int requestCode, List<String> perms, boolean isAllGranted) {
                if (requestCode == 99) {
                    startLocation();
                }
            }

            @Override
            public void onPermissionsDenied(int requestCode, List<String> perms, boolean isAllDenied) {
                if (requestCode == 99) {

                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        destroyLocation();
        super.onDestroy();
        EventBusUtil.unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getTabSelectBus(TabSelectBus tabSelectBus) {
        selectTab(tabSelectBus.getMainTabIndex());
    }

    /**
     * 开始定位
     *
     * @author hongming.wang
     * @since 2.8.0
     */
    private void startLocation() {
        // 设置定位参数
        locationClient.setLocationOption(locationOption);
        // 启动定位
        locationClient.startLocation();
    }


    /**
     * 初始化定位
     *
     * @author hongming.wang
     * @since 2.8.0
     */
    private void initLocation() {
        //初始化client
        locationClient = new AMapLocationClient(this.getApplicationContext());
        locationOption = getDefaultOption();
        //设置定位参数
        locationClient.setLocationOption(locationOption);
        // 设置定位监听
        locationClient.setLocationListener(locationListener);
    }


    /**
     * 默认的定位参数
     *
     * @author hongming.wang
     * @since 2.8.0
     */
    private AMapLocationClientOption getDefaultOption() {
        AMapLocationClientOption mOption = new AMapLocationClientOption();
        mOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);//可选，设置定位模式，可选的模式有高精度、仅设备、仅网络。默认为高精度模式
        mOption.setGpsFirst(false);//可选，设置是否gps优先，只在高精度模式下有效。默认关闭
        mOption.setHttpTimeOut(30000);//可选，设置网络请求超时时间。默认为30秒。在仅设备模式下无效
        mOption.setInterval(1000 * 60);//可选，设置定位间隔。默认为60秒
        mOption.setNeedAddress(true);//可选，设置是否返回逆地理地址信息。默认是true
        mOption.setOnceLocation(true);//可选，设置是否单次定位。默认是false
        mOption.setOnceLocationLatest(false);//可选，设置是否等待wifi刷新，默认为false.如果设置为true,会自动变为单次定位，持续定位时不要使用
        AMapLocationClientOption.setLocationProtocol(AMapLocationClientOption.AMapLocationProtocol.HTTP);//可选， 设置网络请求的协议。可选HTTP或者HTTPS。默认为HTTP
        mOption.setSensorEnable(false);//可选，设置是否使用传感器。默认是false
        mOption.setWifiScan(true); //可选，设置是否开启wifi扫描。默认为true，如果设置为false会同时停止主动刷新，停止以后完全依赖于系统刷新，定位位置可能存在误差
        mOption.setLocationCacheEnable(true); //可选，设置是否使用缓存定位，默认为true
        mOption.setGeoLanguage(AMapLocationClientOption.GeoLanguage.DEFAULT);//可选，设置逆地理信息的语言，默认值为默认语言（根据所在地区选择语言）
        return mOption;
    }

    /**
     * 定位监听
     */
    AMapLocationListener locationListener = new AMapLocationListener() {
        @Override
        public void onLocationChanged(AMapLocation location) {
            if (null != location) {
                StringBuffer sb = new StringBuffer();
                //errCode等于0代表定位成功，其他的为定位失败，具体的可以参照官网定位错误码说明
                if (location.getErrorCode() == 0) {
                    sb.append("定位成功" + "\n");
                    sb.append("定位类型: " + location.getLocationType() + "\n");
                    longitude = location.getLongitude();
                    latitude = location.getLatitude();
                    sb.append("经    度    : " + longitude + "\n");
                    sb.append("纬    度    : " + latitude + "\n");
                    sb.append("精    度    : " + location.getAccuracy() + "米" + "\n");
                    sb.append("提供者    : " + location.getProvider() + "\n");

                    sb.append("速    度    : " + location.getSpeed() + "米/秒" + "\n");
                    sb.append("角    度    : " + location.getBearing() + "\n");
                    // 获取当前提供定位服务的卫星个数
                    sb.append("星    数    : " + location.getSatellites() + "\n");
                    sb.append("国    家    : " + location.getCountry() + "\n");
                    address=location.getProvince();
                    sb.append("省            : " + address+ "\n");
                    sb.append("市            : " + location.getCity() + "\n");
                    sb.append("城市编码 : " + location.getCityCode() + "\n");
                    sb.append("区            : " + location.getDistrict() + "\n");
                    sb.append("区域 码   : " + location.getAdCode() + "\n");
                    sb.append("地    址    : " + location.getAddress() + "\n");
                    sb.append("兴趣点    : " + location.getPoiName() + "\n");


                    EventBusUtil.postEvent(new SelectCityBus("天津市"));
                    setBasePositioning("天津市");
                    city = location.getProvince();
                } else {
                    //定位失败
                    sb.append("定位失败" + "\n");
                    sb.append("错误码:" + location.getErrorCode() + "\n");
                    sb.append("错误信息:" + location.getErrorInfo() + "\n");
                    sb.append("错误描述:" + location.getLocationDetail() + "\n");
                }

                //解析定位结果，
                String result = sb.toString();
                LogUtil.i(result);
            } else {
                LogUtil.i("定位失败，loc is null");
            }
        }
    };


    /**
     * 销毁定位
     *
     * @author hongming.wang
     * @since 2.8.0
     */
    private void destroyLocation() {
        if (null != locationClient) {
            /**
             * 如果AMapLocationClient是在当前Activity实例化的，
             * 在Activity的onDestroy中一定要执行AMapLocationClient的onDestroy
             */
            locationClient.onDestroy();
            locationClient = null;
            locationOption = null;
        }
    }

    private long mExitTime;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        //双击退出
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            long secondTime = System.currentTimeMillis();
            if (secondTime - mExitTime > 2000) {
                ToastUtil.show("再按一次退出程序");
                mExitTime = secondTime;
                return true;
            } else {
                Process.killProcess(Process.myPid());  //杀死进程
            }
        }
        return super.onKeyDown(keyCode, event);
    }


}
