package com.tourismelves.view.activity;

import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.LocationSource;
import com.amap.api.maps.MapView;
import com.amap.api.maps.UiSettings;
import com.amap.api.maps.model.BitmapDescriptor;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.model.MyLocationStyle;
import com.tourismelves.R;
import com.tourismelves.utils.common.ToastUtil;
import com.tourismelves.utils.log.LogUtil;
import com.tourismelves.view.activity.base.CheckPermissionsActivity;
import com.tourismelves.view.widget.custom.CustomScrollView;
import com.tourismelves.view.widget.custom.MapContainer;

import butterknife.BindView;

/**
 * 游玩指引
 */

public class VisitGuidanceActivity extends CheckPermissionsActivity implements LocationSource, AMapLocationListener {
    @BindView(R.id.visit_guidance_map)
    MapView mMapView;
    @BindView(R.id.visit_guidance_scroll)
    CustomScrollView visitGuidanceScroll;
    @BindView(R.id.visit_guidance_map_rl)
    MapContainer visitGuidanceMapRl;

    private AMapLocationClient mlocationClient;
    private LocationSource.OnLocationChangedListener mListener;
    private AMapLocationClientOption mLocationOption;
    private AMap mAMap;
    private LatLng latLng, latLng2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mMapView.onCreate(savedInstanceState);

    }

    @Override
    protected void setContentLayout() {
        setContentView(R.layout.activity_visit_guidance);
    }

    @Override
    protected void initControls() {
        showStateLayout(1);
        setBaseTitle("游玩指引");
        showStateRightView(2);

        visitGuidanceMapRl.setScrollView(visitGuidanceScroll);

        if (mAMap == null) {
            mAMap = mMapView.getMap();
            mAMap.moveCamera(CameraUpdateFactory.zoomBy(12));
            setUpMap();
        }
    }

    @Override
    protected void obtainData() {

    }

    @Override
    protected void initEvent() {
        mAMap.setInfoWindowAdapter(new AMap.InfoWindowAdapter() {
            View infoWindow = null;

            @Override
            public View getInfoWindow(final Marker marker) {
                if (infoWindow == null) {
                    infoWindow = LayoutInflater.from(getContext()).inflate(R.layout.layout_map_icon, null);
                }
                marker.showInfoWindow();
                marker.setFlat(false);
                infoWindow.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        marker.hideInfoWindow();
                        ToastUtil.show("mAMap");
                    }
                });
                return infoWindow;
            }

            @Override
            public View getInfoContents(Marker marker) {
                return null;
            }

        });
    }

    /**
     * 方法必须重写
     */
    @Override
    public void onResume() {
        super.onResume();
        mMapView.onResume();
    }

    /**
     * 方法必须重写
     */
    @Override
    public void onPause() {
        super.onPause();
        mMapView.onPause();
        deactivate();
    }

    /**
     * 方法必须重写
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mMapView.onSaveInstanceState(outState);
    }

    /**
     * 方法必须重写
     */
    @Override
    protected void onDestroy() {
        mMapView.onDestroy();
        if (null != mlocationClient) {
            mlocationClient.onDestroy();
        }
        super.onDestroy();
    }


    /**
     * 激活定位
     */
    @Override
    public void activate(OnLocationChangedListener listener) {
        mListener = listener;
        if (mlocationClient == null) {
            mlocationClient = new AMapLocationClient(this);
            mLocationOption = new AMapLocationClientOption();
            // 设置定位监听
            mlocationClient.setLocationListener(this);
            // 设置为高精度定位模式
            mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
            // 只是为了获取当前位置，所以设置为单次定位
            mLocationOption.setOnceLocation(true);
//            // 设置定位参数
            mlocationClient.setLocationOption(mLocationOption);
            mlocationClient.startLocation();

        }
    }


    /**
     * 停止定位
     */
    public void deactivate() {
        mListener = null;
        if (mlocationClient != null) {
            mlocationClient.stopLocation();
            mlocationClient.onDestroy();
        }
        mlocationClient = null;
    }

    /**
     * 设置一些amap的属性
     */
    private void setUpMap() {
        mAMap.setLocationSource(this);// 设置定位监听
        // 自定义系统定位蓝点
        MyLocationStyle myLocationStyle = new MyLocationStyle();
        // 自定义定位蓝点图标
        myLocationStyle.myLocationIcon(BitmapDescriptorFactory.fromResource(R.mipmap.feiji));
        // 自定义精度范围的圆形边框颜色
        myLocationStyle.strokeColor(Color.argb(0, 0, 0, 0));
        // 自定义精度范围的圆形边框宽度
        myLocationStyle.strokeWidth(0);
        // 设置圆形的填充颜色
        myLocationStyle.radiusFillColor(Color.argb(0, 0, 0, 0));
        // 将自定义的 myLocationStyle 对象添加到地图上
        mAMap.setMyLocationStyle(myLocationStyle);
        mAMap.setMyLocationEnabled(true);// 设置为true表示显示定位层并可触发定位，false表示隐藏定位层并不可触发定位，默认是false
        // 设置定位的类型为定位模式 ，可以由定位、跟随或地图根据面向方向旋转几种
        mAMap.setMyLocationType(AMap.LOCATION_TYPE_LOCATE);

        mAMap.setOnMarkerClickListener(new AMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                LogUtil.i("点击了 marker");
                return false;
            }
        });

        UiSettings uiSettings = mAMap.getUiSettings();
        uiSettings.setMyLocationButtonEnabled(false);// 设置默认定位按钮是否显示
        uiSettings.setZoomInByScreenCenter(false);
        uiSettings.setZoomControlsEnabled(false);

    }

    /**
     * 定位成功后回调函数
     */
    @Override
    public void onLocationChanged(AMapLocation amapLocation) {
        if (mListener != null && amapLocation != null) {

            if (amapLocation != null && amapLocation.getErrorCode() == 0) {
                mListener.onLocationChanged(amapLocation);// 显示系统小蓝点
                latLng = new LatLng(amapLocation.getLatitude(), amapLocation.getLongitude());
                latLng2 = new LatLng(39.9179400000, 116.3971400000);
                setMarket();
            } else {
                String errText = "定位失败," + amapLocation.getErrorCode() + ": "
                        + amapLocation.getErrorInfo();
                LogUtil.e(errText);
            }
        }
    }

    private void setMarket() {

        MarkerOptions markerOption2 = new MarkerOptions();
        markerOption2.position(latLng2);
        markerOption2.title("目的地");
        markerOption2.draggable(true);
        BitmapDescriptor bitmapDescriptor2 = BitmapDescriptorFactory.fromBitmap(BitmapFactory.decodeResource(getResources(), R.mipmap.feiji_dian));
        markerOption2.icon(bitmapDescriptor2);
        markerOption2.setFlat(true);
        mAMap.addMarker(markerOption2);

    }
}
