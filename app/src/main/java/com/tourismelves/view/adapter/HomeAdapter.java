package com.tourismelves.view.adapter;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.amap.api.maps.model.LatLng;
import com.tourismelves.R;
import com.tourismelves.model.event.TabSelectBus;
import com.tourismelves.model.net.OkHttpUtils;
import com.tourismelves.model.res.BannerRes;
import com.tourismelves.model.res.HomeRes;
import com.tourismelves.utils.common.EventBusUtil;
import com.tourismelves.utils.common.ToastUtil;
import com.tourismelves.utils.glide.ShowImageUtils;
import com.tourismelves.utils.system.SPUtils;
import com.tourismelves.view.activity.AlreadyBoughtActivity;
import com.tourismelves.view.activity.FootMarkActivity;
import com.tourismelves.view.activity.InterpretationList2Activity;
import com.tourismelves.view.activity.InterpretationListActivity;
import com.tourismelves.view.activity.NearScenicSpotActivity;
import com.tourismelves.view.activity.SettlementActivity;
import com.tourismelves.view.adapter.base.RecyclerBaseAdapter;
import com.tourismelves.view.adapter.base.ViewHolder;
import com.tourismelves.view.dialog.ActivityCodeDialog;
import com.tourismelves.view.widget.viewpager.banner.Banner;
import com.tourismelves.view.widget.viewpager.banner.GlideImageLoader;
import com.tourismelves.view.widget.viewpager.banner.OnBannerListener;

import java.util.List;

import okhttp3.Call;

import static com.tourismelves.app.constant.UrlConstants.addCart;
import static com.tourismelves.app.constant.UrlConstants.port;
import static com.tourismelves.view.fragment.MyFragment.isLogin;

/**
 * 热门景区适配器
 */

public class HomeAdapter extends RecyclerBaseAdapter<Object> implements View.OnClickListener {

    public HomeAdapter(@NonNull Context context, @NonNull List<Object> mDataList) {
        super(context, mDataList);
    }

    @Override
    protected void bindDataForView(ViewHolder holder, Object object, int position) {
        if (position == 0) {//banner
            final BannerRes bannerRes = (BannerRes) object;
            final List<BannerRes.BannerBean> bannerBeans = bannerRes.getBannerBeans();
            Banner banner = holder.getView(R.id.home_banner);

            //简单使用
            banner.setImages(bannerRes.getUrls())
                    .setImageLoader(new GlideImageLoader())
                    .setOnBannerListener(new OnBannerListener() {
                        @Override
                        public void OnBannerClick(int position) {
                            BannerRes.BannerBean bannerBean = bannerBeans.get(position);
                        }
                    })
                    .start();

            holder.getView(R.id.home_near).setOnClickListener(this);
            holder.getView(R.id.home_activation_code).setOnClickListener(this);
            holder.getView(R.id.home_already_bought).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!isLogin(getContext(), true)) {
                        return;
                    }
                    getContext().startActivity(new Intent(getContext(), AlreadyBoughtActivity.class));
                }
            });
            holder.getView(R.id.home_footprint).setOnClickListener(this);
        } else {//item
            final HomeRes homeRes = (HomeRes) object;
            RelativeLayout home_look_all_rl = holder.getView(R.id.home_look_all_rl);
            if (position == 1) {
                home_look_all_rl.setVisibility(View.VISIBLE);
            } else {
                home_look_all_rl.setVisibility(View.GONE);
            }
            //查看全部
            RelativeLayout details = holder.getView(R.id.home_elf_said_details);
            details.setOnClickListener(this);
            holder.getView(R.id.home_look_all).setOnClickListener(this);
            holder.getView(R.id.home_attractions_details).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {//景点详情
                    Intent intent = new Intent(getContext(), InterpretationList2Activity.class);
                    intent.putExtra("ordId", homeRes.getOrgId());
                    intent.putExtra("name", homeRes.getName());
                    getContext().startActivity(intent);
                }
            });

            AppCompatImageView home_attractions_img = holder.getView(R.id.home_attractions_img);
            AppCompatTextView home_attractions_name = holder.getView(R.id.home_attractions_name);
            AppCompatTextView home_attractions_content = holder.getView(R.id.home_attractions_content);
            AppCompatTextView home_attractions_count = holder.getView(R.id.home_attractions_count);
            AppCompatTextView home_attractions_money = holder.getView(R.id.home_attractions_money);
            AppCompatTextView home_auto_play = holder.getView(R.id.home_auto_play);

            home_attractions_money.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {//支付
                    addCart(homeRes.getOrgId());
                }
            });

            home_auto_play.setVisibility(homeRes.getIsAutoplay() == 1 ? View.VISIBLE : View.GONE);
            ShowImageUtils.showImageView(getContext(), port + homeRes.getImage()
                            + "_" + (int) getContext().getResources().getDimension(R.dimen.dp105) + "x" +
                            (int) getContext().getResources().getDimension(R.dimen.dp105) + ".jpg",
                    home_attractions_img);
            home_attractions_count.setText(String.format(getContext().getString(R.string.scenic_spot_count), homeRes.getSceneryCount() + ""));
            home_attractions_money.setText(Html.fromHtml("¥<b>" + homeRes.getPrice() + "</b>"));
            home_attractions_content.setText(homeRes.getSummary());
            home_attractions_name.setText(homeRes.getName());

            AppCompatImageView home_attractions2_img = holder.getView(R.id.home_attractions2_img);
            AppCompatTextView home_attractions2_name = holder.getView(R.id.home_attractions2_name);
            AppCompatTextView home_attractions2_content = holder.getView(R.id.home_attractions2_content);

            if (homeRes.getArticleList() != null && homeRes.getArticleList().size() != 0) {
                details.setVisibility(View.VISIBLE);
                ShowImageUtils.showRounded(getContext(), port + homeRes.getImage()
                        + "_" + (int) getContext().getResources().getDimension(R.dimen.dp45) + "x" +
                                (int) getContext().getResources().getDimension(R.dimen.dp45) + ".jpg",
                        home_attractions2_img, 10);
                home_attractions2_content.setText(homeRes.getSummary());
                home_attractions2_name.setText(homeRes.getName());
            } else {
                details.setVisibility(View.GONE);
            }

            AppCompatImageView home_attractions3_img = holder.getView(R.id.home_attractions3_img);
            AppCompatTextView home_attractions3_address = holder.getView(R.id.home_attractions3_address);
            AppCompatTextView home_attractions3_name = holder.getView(R.id.home_attractions3_name);
            AppCompatTextView home_attractions3_content = holder.getView(R.id.home_attractions3_content);

            ShowImageUtils.showRounded(getContext(), port + homeRes.getImage()
                    + "_-" +(int) getContext().getResources().getDimension(R.dimen.dp180) + ".jpg", home_attractions3_img, 10);
            final String sAddress = homeRes.getArea().getParentArea().getName() + " " + homeRes.getArea().getName() + " " + String.format(getContext().getString(R.string.distance), homeRes.getDistance() + "");
            home_attractions3_address.setText(sAddress);
            home_attractions3_content.setText(homeRes.getDescription());
            home_attractions3_name.setText(homeRes.getName());


            holder.getView(R.id.home_scenic_spot_details).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {//景区详情
                    Intent intent = new Intent(getContext(), InterpretationListActivity.class);
                    intent.putExtra("ordId", homeRes.getOrgId());
                    intent.putExtra("name", homeRes.getName());
                    intent.putExtra("distance", homeRes.getArea().getParentArea().getName() + " " + homeRes.getArea().getName());
                    intent.putExtra("latlng", new LatLng(homeRes.getLatitude(), homeRes.getLongitude()));
                    getContext().startActivity(intent);
                }
            });
        }


    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        if (viewType == 0) {
            view = LayoutInflater.from(getContext()).inflate(R.layout.item_home_banner, parent, false);
        } else {
            view = LayoutInflater.from(getContext()).inflate(R.layout.item_home, parent, false);
        }
        return new ViewHolder(view);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.home_near://附近
                getContext().startActivity(new Intent(getContext(), NearScenicSpotActivity.class));
                break;
            case R.id.home_activation_code://激活码
                if (!isLogin(getContext(), true)) {
                    return;
                }
                new ActivityCodeDialog().show(((AppCompatActivity) getContext()).getSupportFragmentManager());
                break;
            case R.id.home_footprint://足迹
                if (!isLogin(getContext(), true)) {
                    return;
                }
                Intent intent = new Intent(getContext(), FootMarkActivity.class);
                getContext().startActivity(intent);
                break;
            case R.id.home_look_all://查看全部
                EventBusUtil.postEvent(new TabSelectBus(1));
                break;
            case R.id.home_elf_said_details://精灵说详情
                EventBusUtil.postEvent(new TabSelectBus(2));
                break;
        }
    }

    private Call call = null;

    /**
     * 添加购物车
     */
    private void addCart(int orgId) {
        if (!isLogin(getContext(), true)) {
            return;
        }
        final ProgressDialog dialog = new ProgressDialog(getContext());
        dialog.setMessage("正在加载中");
        dialog.show();
        dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                if (call != null) {
                    call.cancel();
                }
            }
        });

        call = OkHttpUtils.get(addCart + "userId=" + SPUtils.getInstance(getContext()).getString("putInt") + "&orgId=" + orgId,
                new OkHttpUtils.ResultCallback<String>() {
                    @Override
                    public void onSuccess(String response) {
                        JSONObject object = JSON.parseObject(response);
                        ToastUtil.show(object.getString("message"));
                        Intent intent = new Intent(getContext(), SettlementActivity.class);
                        getContext().startActivity(intent);
                        call = null;
                        dialog.dismiss();
                    }

                    @Override
                    public void onFailure(Exception e) {
                        call = null;
                        ToastUtil.show(e.getMessage());
                        dialog.dismiss();
                    }
                });
    }
}
