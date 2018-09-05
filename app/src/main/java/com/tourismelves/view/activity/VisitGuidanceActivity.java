package com.tourismelves.view.activity;

import android.os.Bundle;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.amap.api.maps.model.LatLng;
import com.tourismelves.R;
import com.tourismelves.model.net.OkHttpUtils;
import com.tourismelves.model.res.VisitGuidanceRes;
import com.tourismelves.utils.common.ToastUtil;
import com.tourismelves.utils.glide.ShowImageUtils;
import com.tourismelves.utils.system.LocationUtil;
import com.tourismelves.utils.system.ResolutionUtil;
import com.tourismelves.view.activity.base.CheckPermissionsActivity;
import com.tourismelves.view.widget.custom.CustomScrollView;
import com.tourismelves.view.widget.loadlayout.State;

import java.util.List;

import butterknife.BindView;

import static com.tourismelves.app.constant.CommentConstants.latitude;
import static com.tourismelves.app.constant.CommentConstants.longitude;
import static com.tourismelves.app.constant.UrlConstants.port;
import static com.tourismelves.app.constant.UrlConstants.scenicSpotInfo;

/**
 * 游玩指引
 */

public class VisitGuidanceActivity extends CheckPermissionsActivity {
    @BindView(R.id.visit_guidance_scroll)
    CustomScrollView visitGuidanceScroll;
    @BindView(R.id.visit_guidance_img)
    AppCompatImageView visitGuidanceImg;
    @BindView(R.id.visit_guidance_distance)
    AppCompatTextView visitGuidanceDistance;
    @BindView(R.id.visit_guidance_price)
    AppCompatTextView visitGuidancePrice;
    @BindView(R.id.visit_guidance_openingHours)
    AppCompatTextView visitGuidanceOpeningHours;
    @BindView(R.id.visit_guidance_favouredPolicy)
    AppCompatTextView visitGuidanceFavouredPolicy;
    @BindView(R.id.visit_guidance_eason)
    AppCompatTextView visitGuidanceEason;
    @BindView(R.id.visit_guidance_playtime)
    AppCompatTextView visitGuidancePlaytime;
    @BindView(R.id.visit_guidance_address)
    AppCompatTextView visitGuidanceAddress;
    @BindView(R.id.visit_guidance_tip)
    AppCompatTextView visitGuidanceTip;
    @BindView(R.id.visit_guidance_name)
    AppCompatTextView visitGuidanceName;

    private LatLng start, end;
    private int orgId;
    private VisitGuidanceRes visitGuidanceRes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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

        orgId = getIntent().getIntExtra("orgId", 0);
        String name = getIntent().getStringExtra("name");
        String address = getIntent().getStringExtra("distance");
        end = getIntent().getParcelableExtra("latlng");

        int distance = (int) (LocationUtil.getInstance(getContext()).getDistance(end.longitude, end.latitude, longitude, latitude) / 1000);

        visitGuidanceDistance.setText(address + " " + String.format(getContext().getString(R.string.distance), distance + ""));
        visitGuidanceName.setText(name + "游玩指引");
    }


    @Override
    protected void obtainData() {
        scenicSpotInfo();
    }

    @Override
    protected void initEvent() {

    }

    private void scenicSpotInfo() {
        OkHttpUtils.get(scenicSpotInfo + "orgId=" + orgId,
                new OkHttpUtils.ResultCallback<String>() {
                    @Override
                    public void onSuccess(String response) {
                        JSONObject object = JSON.parseObject(response);
                        Integer code = object.getInteger("code");
                        if (code == 200) {
                            JSONArray dataList = object.getJSONArray("dataList");
                            if (dataList != null && dataList.size() > 0) {
                                String string = dataList.getJSONObject(0).toString();
                                visitGuidanceRes = JSON.parseObject(string, VisitGuidanceRes.class);

                                List<VisitGuidanceRes.ImagelistBean> imagelist = visitGuidanceRes.getImagelist();
                                if (imagelist != null && imagelist.size() > 0)
                                    ShowImageUtils.showImageView(getContext(), port + imagelist.get(0).getImagePath(),
                                            ResolutionUtil.getInstance(getContext()).getWidth(),
                                            (int) getResources().getDimension(R.dimen.dp196), visitGuidanceImg);

                                visitGuidanceAddress.setText("地址：" + visitGuidanceRes.getAddress());
                                visitGuidanceEason.setText("适宜游玩季节：" + visitGuidanceRes.getSeason());
                                visitGuidanceFavouredPolicy.setText("优惠政策：" + visitGuidanceRes.getFavouredPolicy());
                                visitGuidanceOpeningHours.setText("开放时间：" + visitGuidanceRes.getOpeningHours());
                                visitGuidancePrice.setText("门票：¥" + visitGuidanceRes.getPrice());
                                visitGuidanceTip.setText("温馨提示：" + visitGuidanceRes.getTips());
                                visitGuidancePlaytime.setText("建议游玩时间：" + visitGuidanceRes.getPlaytime());
                            } else {
                                getLoadLayout().setLayoutState(State.NO_DATA);
                            }
                        } else {
                            getLoadLayout().setLayoutState(State.NO_DATA);
                            ToastUtil.show(object.getString("message"));
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
