package com.tourismelves.view.adapter;

import android.content.Context;
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

import com.tourismelves.R;
import com.tourismelves.model.bean.SettlementBean;
import com.tourismelves.model.event.TabSelectBus;
import com.tourismelves.model.res.BannerRes;
import com.tourismelves.model.res.HomeRes;
import com.tourismelves.utils.common.EventBusUtil;
import com.tourismelves.utils.glide.ShowImageUtils;
import com.tourismelves.utils.log.LogUtil;
import com.tourismelves.view.activity.AlreadyBoughtActivity;
import com.tourismelves.view.activity.FootMarkActivity;
import com.tourismelves.view.activity.NearScenicSpotActivity;
import com.tourismelves.view.activity.SettlementActivity;
import com.tourismelves.view.adapter.base.RecyclerBaseAdapter;
import com.tourismelves.view.adapter.base.ViewHolder;
import com.tourismelves.view.dialog.ActivityCodeDialog;
import com.tourismelves.view.widget.viewpager.banner.Banner;
import com.tourismelves.view.widget.viewpager.banner.GlideImageLoader;
import com.tourismelves.view.widget.viewpager.banner.OnBannerListener;

import java.util.List;

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
            holder.getView(R.id.home_already_bought).setOnClickListener(this);
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
            holder.getView(R.id.home_look_all).setOnClickListener(this);

            AppCompatImageView home_attractions_img = holder.getView(R.id.home_attractions_img);
            AppCompatTextView home_attractions_name = holder.getView(R.id.home_attractions_name);
            AppCompatTextView home_attractions_content = holder.getView(R.id.home_attractions_content);
            AppCompatTextView home_attractions_count = holder.getView(R.id.home_attractions_count);
            AppCompatTextView home_attractions_money = holder.getView(R.id.home_attractions_money);
            AppCompatTextView home_auto_play = holder.getView(R.id.home_auto_play);

            home_attractions_money.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {//支付
                    Intent intent = new Intent(getContext(), SettlementActivity.class);
                    intent.putExtra("SettlementBean", new SettlementBean(homeRes.getName(), homeRes.getPrice(), homeRes.getPrice(), homeRes.getImage(), homeRes.getSceneryCount()));
                    getContext().startActivity(intent);
                }
            });

            home_auto_play.setVisibility(homeRes.getIsAutoplay() == 1 ? View.VISIBLE : View.GONE);
            ShowImageUtils.showImageView(getContext(), port + homeRes.getImage(),
                    (int) getContext().getResources().getDimension(R.dimen.dp105), (int) getContext().getResources().getDimension(R.dimen.dp105),
                    home_attractions_img);
            home_attractions_count.setText(String.format(getContext().getString(R.string.scenic_spot_count), homeRes.getSceneryCount() + ""));
            home_attractions_money.setText(Html.fromHtml("¥<b>" + homeRes.getPrice() + "</b>"));
            home_attractions_content.setText(homeRes.getSummary());
            home_attractions_name.setText(homeRes.getName());

            AppCompatImageView home_attractions2_img = holder.getView(R.id.home_attractions2_img);
            AppCompatTextView home_attractions2_name = holder.getView(R.id.home_attractions2_name);
            AppCompatTextView home_attractions2_content = holder.getView(R.id.home_attractions2_content);

            if (homeRes.getArticleList() != null && homeRes.getArticleList().size() != 0) {
                home_attractions2_content.setVisibility(View.VISIBLE);
                home_attractions2_name.setVisibility(View.VISIBLE);
                ShowImageUtils.showRounded(getContext(), port + homeRes.getImage(),
                        (int) getContext().getResources().getDimension(R.dimen.dp45), (int) getContext().getResources().getDimension(R.dimen.dp45),
                        home_attractions2_img, 10);
                home_attractions2_content.setText(homeRes.getSummary());
                home_attractions2_name.setText(homeRes.getName());
            } else {
                home_attractions2_content.setVisibility(View.GONE);
                home_attractions2_name.setVisibility(View.GONE);
            }

            AppCompatImageView home_attractions3_img = holder.getView(R.id.home_attractions3_img);
            AppCompatTextView home_attractions3_address = holder.getView(R.id.home_attractions3_address);
            AppCompatTextView home_attractions3_name = holder.getView(R.id.home_attractions3_name);
            AppCompatTextView home_attractions3_content = holder.getView(R.id.home_attractions3_content);

//            ShowImageUtils.showRounded(getContext(), port + homeRes.getImage(), home_attractions3_img, 10);
            String sAddress = homeRes.getArea().getParentArea().getName() + " " + homeRes.getArea().getName() + " " + String.format(getContext().getString(R.string.distance), homeRes.getDistance() + "");
            home_attractions3_address.setText(sAddress);
//            String content3 = homeRes.getSummary().trim().equals("") ? homeRes.getDescription() : homeRes.getSummary();
//            home_attractions3_content.setText(content3);
//            home_attractions3_name.setText(homeRes.getName());
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
            case R.id.home_already_bought://已购
                if (!isLogin(getContext(), true)) {
                    return;
                }
                getContext().startActivity(new Intent(getContext(), AlreadyBoughtActivity.class));
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
            case R.id.home_scenic_spot_details://景区详情
                LogUtil.i("景区详情");
                break;
            case R.id.home_attractions_details://景点详情
                LogUtil.i("景点详情");
                break;
            case R.id.home_elf_said_details://精灵说详情
                LogUtil.i("精灵说详情");
                break;
        }
    }
}
