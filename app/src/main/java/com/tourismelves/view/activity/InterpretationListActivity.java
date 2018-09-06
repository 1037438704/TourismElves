package com.tourismelves.view.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
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
import com.amap.api.maps.model.CameraPosition;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.model.MyLocationStyle;
import com.tourismelves.R;
import com.tourismelves.model.bean.AttractionsBean;
import com.tourismelves.model.net.OkHttpUtils;
import com.tourismelves.model.res.ApkDownloadInfoRes;
import com.tourismelves.utils.common.EventBusUtil;
import com.tourismelves.utils.common.ToastUtil;
import com.tourismelves.utils.log.LogUtil;
import com.tourismelves.utils.system.SPUtils;
import com.tourismelves.view.activity.base.CheckPermissionsActivity;
import com.tourismelves.view.adapter.InterpretationListAdapter;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

import static com.tourismelves.app.constant.UrlConstants.delFavorite;
import static com.tourismelves.app.constant.UrlConstants.isFavorite;
import static com.tourismelves.app.constant.UrlConstants.port;
import static com.tourismelves.app.constant.UrlConstants.saveFavorite;
import static com.tourismelves.app.constant.UrlConstants.sceneryList;

/**
 * 讲解列表 地图
 */

public class InterpretationListActivity extends CheckPermissionsActivity implements LocationSource, AMapLocationListener, AMap.OnMapLoadedListener {
    @BindView(R.id.interpretation_list_like_btn)
    AppCompatTextView interpretationListLikeBtn;
    @BindView(R.id.interpretation_list_name)
    AppCompatTextView interpretationListName;
    @BindView(R.id.map)
    MapView mMapView;
    private AppCompatImageView interpretationListComments;
    private LinearLayout interpretationListBottom;
    private int lastY = 0, lastY2 = 0;

    private BottomSheetDialog mBottomSheetDialog;
    private int ordId;
    private String name = "";
    private ArrayList<AttractionsBean> attractionsBeans;


    /**
     * 用于显示当前的位置
     * <p>
     * 示例中是为了显示当前的位置，在实际使用中，单独的地理围栏可以不使用定位接口
     * </p>
     */
    private AMapLocationClient mlocationClient;
    private LocationSource.OnLocationChangedListener mListener;
    private AMapLocationClientOption mLocationOption;
    private AMap mAMap;
    private String userId;
    private String distance;
    private LatLng latlng;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mMapView.onCreate(savedInstanceState);

    }

    @Override
    protected void setContentLayout() {
        setContentView(R.layout.activity_interpretation_list);
    }

    @Override
    protected void initControls() {
        EventBusUtil.register(this);
        showStateLayout(1);
        setBaseTitle("讲解列表");
        interpretationListComments = findViewById(R.id.interpretation_list_comments);
        interpretationListBottom = findViewById(R.id.interpretation_list_bottom);

        userId = SPUtils.getInstance(getContext()).getString("putInt");
        if (mAMap == null) {
            mAMap = mMapView.getMap();
            mAMap.moveCamera(CameraUpdateFactory.zoomBy(6));
            setUpMap();
        }
    }

    @Override
    protected void obtainData() {
        Intent intent = getIntent();
        ordId = intent.getIntExtra("ordId", 0);
        name = intent.getStringExtra("name");
        distance = intent.getStringExtra("distance");
        latlng = intent.getParcelableExtra("latlng");

        interpretationListName.setText(name);
        sceneryList(ordId + "");

        isFavorite();
//        initMarket();
    }

    @Override
    protected void initEvent() {

        interpretationListComments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBottomSheetDialog.show();
            }
        });

        interpretationListBottom.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int dy = 0;
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        //将点下的点的坐标保存
                        lastY2 = (int) event.getRawY();
                        return true;

                    case MotionEvent.ACTION_MOVE:
                        //计算出需要移动的距离
                        dy = (int) event.getRawY() - lastY2;
                        if (dy <= 0) {
                            if (mBottomSheetDialog != null) {
                                mBottomSheetDialog.show();
                            }
                        }
                        return true;
                }
                return true;
            }
        });
    }

    /**
     * 讲解列表
     */
    @SuppressLint("ClickableViewAccessibility")
    private void setBehaviorCallback() {
        mBottomSheetDialog = new BottomSheetDialog(this, R.style.CustomBottomSheetDialogTheme);
        View view = LayoutInflater.from(this).inflate(R.layout.dialog_fragment_interpretation_list, null, false);
        mBottomSheetDialog.setContentView(view);

        AppCompatImageView commentsOff = (AppCompatImageView) view.findViewById(R.id.interpretation_list_comments_off);
        AppCompatTextView tvName = (AppCompatTextView) view.findViewById(R.id.interpretation_list_name);
        LinearLayout ll = (LinearLayout) view.findViewById(R.id.interpretation_ll);
        RelativeLayout noData = (RelativeLayout) view.findViewById(R.id.layout_null_data);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.interpretation_list_recycler);
        if (attractionsBeans.size() > 0) {
            recyclerView.setVisibility(View.VISIBLE);
            noData.setVisibility(View.GONE);
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            InterpretationListAdapter interpretationListAdapter = new InterpretationListAdapter(getContext(), attractionsBeans,name);
            recyclerView.setAdapter(interpretationListAdapter);
        } else {
            recyclerView.setVisibility(View.GONE);
            noData.setVisibility(View.VISIBLE);
        }

        tvName.setText(name);
        commentsOff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBottomSheetDialog.dismiss();
            }
        });

        View rootView = mBottomSheetDialog.getDelegate().findViewById(android.support.design.R.id.design_bottom_sheet);
        rootView.setBackgroundColor(0x00ffffff);

        final BottomSheetBehavior bottomSheetBehavior = BottomSheetBehavior.from(rootView);
        bottomSheetBehavior.setPeekHeight((int) getResources().getDimension(R.dimen.dp364));
        bottomSheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                if (newState == BottomSheetBehavior.STATE_HIDDEN) {
                    mBottomSheetDialog.dismiss();
                    bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                }
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {
            }
        });

        ll.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int dy = 0;
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        //将点下的点的坐标保存
                        lastY = (int) event.getRawY();
                        LogUtil.i("ACTION_DOWN_" + lastY);
                        return true;

                    case MotionEvent.ACTION_MOVE:
                        //计算出需要移动的距离
                        LogUtil.i("ACTION_MOVE_" + event.getRawY());
                        dy = (int) event.getRawY() - lastY;
                        if (dy <= 0) {
                            mBottomSheetDialog.dismiss();
                            Intent intent = new Intent(getContext(), InterpretationList2Activity.class);
                            intent.putParcelableArrayListExtra("attractionsBeans", attractionsBeans);
                            intent.putExtra("name", name);
                            startActivity(intent);
                        }
                        return true;
                    case MotionEvent.ACTION_UP:
                        LogUtil.i("ACTION_UP_" + event.getRawY());
                        return true;
                    case MotionEvent.ACTION_CANCEL:
                        //按钮弹起逻辑
                        LogUtil.i("ACTION_CANCEL_" + event.getRawY());
                        return true;
                }
                return true;
            }
        });
    }

    @OnClick({R.id.interpretation_list_like_btn, R.id.interpretation_list_visit_guidance_btn, R.id.interpretation_list_search_around_comments_btn,
            R.id.interpretation_list_facebook_btn, R.id.iv_zoom_small, R.id.iv_zoom_large, R.id.iv_map_restore})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.interpretation_list_like_btn:
                boolean selected = interpretationListLikeBtn.isSelected();
                if (selected) {
                    delFavorite();
                } else {
                    saveFavorite();
                }
                break;
            case R.id.interpretation_list_visit_guidance_btn:
                Intent intent = new Intent(this, VisitGuidanceActivity.class);
                intent.putExtra("orgId", ordId);
                intent.putExtra("name", name);
                intent.putExtra("distance", distance);
                intent.putExtra("latlng", latlng);
                startActivity(intent);
                break;
            case R.id.interpretation_list_search_around_comments_btn:
                Intent intent1 = new Intent(this, NearScenicSpotActivity.class);
                startActivity(intent1);
                break;
            case R.id.interpretation_list_facebook_btn:
                startActivity(new Intent(this, HelpActivity.class));
                break;
            case R.id.iv_zoom_small:
                scaleLargeMap(0);
                break;
            case R.id.iv_zoom_large:
                scaleLargeMap(1);
                break;
            case R.id.iv_map_restore:
                mAMap.moveCamera(CameraUpdateFactory.changeLatLng(latlng));
                break;
        }
    }

    public void scaleLargeMap(float scaleValue) {
        CameraPosition cameraPosition = mAMap.getCameraPosition();
        float mapZoom = cameraPosition.zoom;
        LatLng mapTarget = cameraPosition.target;
        if (scaleValue == 0)
            mAMap.animateCamera(CameraUpdateFactory.newLatLngZoom(mapTarget, --mapZoom));
        else
            mAMap.animateCamera(CameraUpdateFactory.newLatLngZoom(mapTarget, ++mapZoom));
//        aMap.moveCamera();
    }

    /**
     * 景点列表
     */
    private void sceneryList(String ordId) {
        attractionsBeans = new ArrayList<>();
        OkHttpUtils.get(String.format(sceneryList, ordId, userId),
                new OkHttpUtils.ResultCallback<String>() {
                    @Override
                    public void onSuccess(final String response) {
                        new Thread() {
                            @Override
                            public void run() {
                                super.run();
                                JSONObject object = JSON.parseObject(response);
                                //获取请求结果的code码
                                Integer code = object.getInteger("code");
                                if (code == 200) {
                                    //获取当前数据源集合
                                    JSONArray dataList = object.getJSONArray("dataList");
                                    int size = dataList.size();
                                    for (int i = 0; i < size; i++) {
                                        String string = dataList.getJSONObject(i).toString();
                                        AttractionsBean attractionsBean = JSON.parseObject(string, AttractionsBean.class);

                                        List<AttractionsBean.AudioListBean> audioList = attractionsBean.getAudioList();
                                        if (audioList != null && audioList.size() > 0) {
                                            ApkDownloadInfoRes apkDownloadInfoRes = new ApkDownloadInfoRes();
                                            String photoPath = "";
                                            if (attractionsBean.getPhotoList() != null && attractionsBean.getPhotoList().size() > 0)
                                                photoPath = port + attractionsBean.getPhotoList().get(0).getPhotoPath();

                                            apkDownloadInfoRes.setImageUrl(photoPath);
                                            apkDownloadInfoRes.setDownKey(attractionsBean.getName());
                                            apkDownloadInfoRes.setContent(attractionsBean.getDescription());
                                            apkDownloadInfoRes.setName(attractionsBean.getName());
                                            apkDownloadInfoRes.setUrl(port + audioList.get(0).getAudioPath());
                                            attractionsBean.setApkDownloadInfoRes(apkDownloadInfoRes);

                                        } else
                                            attractionsBean.setApkDownloadInfoRes(null);
                                        attractionsBeans.add(attractionsBean);
                                    }
                                } else {
                                    ToastUtil.show(object.getString("message"));
                                }

                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        initMarket();
                                        setBehaviorCallback();
                                    }
                                });
                            }
                        }.start();
                    }

                    @Override
                    public void onFailure(Exception e) {
                        ToastUtil.show(R.string.no_found_network);
                    }
                });
    }

    /**
     * 收藏部分
     */
    private void saveFavorite() {    //收藏
        OkHttpUtils.get(saveFavorite + "userId=" + userId + "&contentId=" + ordId + "&type=" + 0,
                new OkHttpUtils.ResultCallback<String>() {
                    @Override
                    public void onSuccess(String response) {
                        JSONObject object = JSON.parseObject(response);
                        String message = object.getString("message");
                        ToastUtil.show(message);
                        if (object.getInteger("code") == 200) {
                            interpretationListLikeBtn.setSelected(true);
                        }
                    }

                    @Override
                    public void onFailure(Exception e) {
                        ToastUtil.show(R.string.no_found_network);
                    }
                });
    }

    private void delFavorite() {    //删除收藏
        OkHttpUtils.get(delFavorite + "userId=" + userId + "&contentId=" + ordId + "&type=" + 0,
                new OkHttpUtils.ResultCallback<String>() {
                    @Override
                    public void onSuccess(String response) {
                        JSONObject object = JSON.parseObject(response);
                        String message = object.getString("message");
                        ToastUtil.show(message);
                        if (object.getInteger("code") == 200) {
                            interpretationListLikeBtn.setSelected(false);
                        }
                    }

                    @Override
                    public void onFailure(Exception e) {
                        ToastUtil.show(R.string.no_found_network);
                    }
                });
    }

    private void isFavorite() {    //是否收藏
        OkHttpUtils.get(isFavorite + "userId=" + userId + "&contentId=" + ordId + "&type=" + 0,
                new OkHttpUtils.ResultCallback<String>() {
                    @Override
                    public void onSuccess(String response) {
                        JSONObject object = JSON.parseObject(response);
                        Integer code = object.getInteger("code");
                        if (code == 200) {
                            Boolean isLike = Boolean.valueOf(object.getString("pk_id"));
                            interpretationListLikeBtn.setSelected(isLike);
                        } else {
                            ToastUtil.show(object.getString("message"));
                        }
                    }

                    @Override
                    public void onFailure(Exception e) {
                        ToastUtil.show(R.string.no_found_network);
                    }
                });
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getIsLike(Boolean is) {
        interpretationListLikeBtn.setSelected(is);
    }

    /* *******************************************************************
     * ******************************* 地图 *******************************
     * ******************************************************************/

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
        EventBusUtil.unregister(this);
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
        mAMap.setOnMapLoadedListener(this);
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

            } else {
                String errText = "定位失败," + amapLocation.getErrorCode() + ": "
                        + amapLocation.getErrorInfo();
                LogUtil.e(errText);
            }
        }
    }

    private void initMarket() {
        int size = attractionsBeans.size();
        for (int i = 0; i < size; i++) {
            MarkerOptions markerOption = new MarkerOptions();
            AttractionsBean attractionsBean = attractionsBeans.get(i);
            //坐标
            LatLng latLng = new LatLng(attractionsBean.getLatitude(), attractionsBean.getLongitude());
            markerOption.position(latLng);
            markerOption.title(attractionsBean.getName());
            markerOption.draggable(true);
            //是否解锁
            boolean isLocked = attractionsBean.getUnLocked() == 1;
            BitmapDescriptor bitmapDescriptor = BitmapDescriptorFactory.fromBitmap(
                    BitmapFactory.decodeResource(getResources(), isLocked ? R.mipmap.jingdian_jiesuo : R.mipmap.jingdian_daijiesuo));
            markerOption.icon(bitmapDescriptor);
            mAMap.addMarker(markerOption);


            List<AttractionsBean.DoorListBean> doorList = attractionsBean.getDoorList();
            if (doorList != null && doorList.size() > 0) {
                int size1 = doorList.size();
                for (int j = 0; j < size1; j++) {
                    AttractionsBean.DoorListBean doorListBean = doorList.get(j);
                    MarkerOptions markerOption2 = new MarkerOptions();
                    //坐标
                    LatLng latLng2 = new LatLng(doorListBean.getLatitude(), doorListBean.getLongitude());
                    markerOption.position(latLng2);
                    markerOption.title(doorListBean.getName());
                    markerOption.draggable(true);
                    BitmapDescriptor bitmapDescriptor2 = BitmapDescriptorFactory.fromBitmap(
                            BitmapFactory.decodeResource(getResources(), R.mipmap.jingdian_jiesuo));
                    markerOption.icon(bitmapDescriptor2);
                    mAMap.addMarker(markerOption);
                }
            }
        }

        mAMap.setInfoWindowAdapter(new AMap.InfoWindowAdapter() {
            View infoWindow = null;

            @Override
            public View getInfoWindow(final Marker marker) {
                if (infoWindow == null) {
                    infoWindow = LayoutInflater.from(getContext()).inflate(R.layout.layout_map_icon, null);
                }
                AppCompatTextView name = infoWindow.findViewById(R.id.map_name);
                name.setText(marker.getTitle());
                marker.showInfoWindow();
                marker.setFlat(false);
                infoWindow.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        marker.hideInfoWindow();
                        ToastUtil.show(marker.getTitle());
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
     * 监听amap地图加载成功事件回调
     */
    @Override
    public void onMapLoaded() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mAMap.moveCamera(CameraUpdateFactory.changeLatLng(latlng));
            }
        }, 500);
    }
}
