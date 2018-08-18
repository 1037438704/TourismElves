package com.tourismelves.view.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatTextView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tourismelves.R;
import com.tourismelves.model.res.BannerRes;
import com.tourismelves.model.res.HomeRes;
import com.tourismelves.view.adapter.base.RecyclerBaseAdapter;
import com.tourismelves.view.adapter.base.ViewHolder;
import com.tourismelves.view.widget.viewpager.banner.Banner;
import com.tourismelves.view.widget.viewpager.banner.GlideImageLoader;
import com.tourismelves.view.widget.viewpager.banner.OnBannerListener;

import java.util.List;

/**
 * 热门景区适配器
 */

public class HomeAdapter extends RecyclerBaseAdapter<Object> {

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

                        }
                    })
                    .start();

            AppCompatTextView near = holder.getView(R.id.home_near);
            AppCompatTextView code = holder.getView(R.id.home_activation_code);
            AppCompatTextView bought = holder.getView(R.id.home_already_bought);
            AppCompatTextView footprint = holder.getView(R.id.home_footprint);

            near.setOnClickListener(new View.OnClickListener() {//附近
                @Override
                public void onClick(View v) {

                }
            });

            code.setOnClickListener(new View.OnClickListener() {//激活码
                @Override
                public void onClick(View v) {

                }
            });

            bought.setOnClickListener(new View.OnClickListener() {//已购
                @Override
                public void onClick(View v) {

                }
            });

            footprint.setOnClickListener(new View.OnClickListener() {//足迹
                @Override
                public void onClick(View v) {

                }
            });
        } else {//item
            HomeRes homeRes = (HomeRes) object;


        }


//        ShowImageUtils.showTopRounded(getContext(), port + homeRes.getImage(), img, 10);
//        name.setText(homeRes.getName());
//        String sAddress = homeRes.getArea().getParentArea().getName() + "·" + homeRes.getArea().getName();
//        address.setText(sAddress);
//        distance.setText(String.format(getContext().getString(R.string.distance), homeRes.getDistance() + ""));
//        count.setText(String.format(getContext().getString(R.string.scenic_spot_count), homeRes.getSceneryCount() + ""));
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
}
