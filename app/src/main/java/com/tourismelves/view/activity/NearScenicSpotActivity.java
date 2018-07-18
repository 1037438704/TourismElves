package com.tourismelves.view.activity;

import android.location.Location;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.tourismelves.R;
import com.tourismelves.model.net.OkHttpUtils;
import com.tourismelves.utils.common.ToastUtil;
import com.tourismelves.utils.system.LocationUtil;
import com.tourismelves.view.activity.base.StateBaseActivity;
import com.tourismelves.view.widget.loadlayout.State;

import static com.tourismelves.app.constant.UrlConstants.nearOrganizationList;

/**
 * 附近景区
 */

public class NearScenicSpotActivity extends StateBaseActivity {

    private Location location;
    //当前页数
    private int page = 1;
    //总页数
    private int totalPage = 1;

    @Override
    protected void setContentLayout() {
        setContentView(R.layout.activity_near_scenic_spot);
    }

    @Override
    protected void initControls() {
        //获取当前经纬度
        location = LocationUtil.getInstance(getContext()).showLocation();
    }

    @Override
    protected void obtainData() {
        nearOrganizationList();
    }

    @Override
    protected void initEvent() {

    }

    private void nearOrganizationList() {
        OkHttpUtils.get(String.format(nearOrganizationList, location.getLatitude(), location.getLongitude(), 20, page),
                new OkHttpUtils.ResultCallback<String>() {
                    @Override
                    public void onSuccess(String response) {
                        JSONObject object = JSON.parseObject(response);
                        //获取请求结果的code码
                        Integer code = object.getInteger("code");
                        if (code == 200) {

                        }
                    }

                    @Override
                    public void onFailure(Exception e) {
                        getLoadLayout().setLayoutState(State.FAILED);
                        ToastUtil.show(R.string.no_found_network);
                    }
                });
    }
}
