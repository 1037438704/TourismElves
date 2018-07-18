package com.tourismelves.view.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.tourismelves.R;
import com.tourismelves.model.res.HomeRes;
import com.tourismelves.utils.glide.ShowImageUtils;
import com.tourismelves.view.adapter.base.RecyclerBaseAdapter;
import com.tourismelves.view.adapter.base.ViewHolder;

import java.util.List;

import static com.tourismelves.app.constant.UrlConstants.port;

/**
 * 热门景区适配器
 */

public class HomeAdapter extends RecyclerBaseAdapter<HomeRes> {

    public HomeAdapter(@NonNull Context context, @NonNull List<HomeRes> mDataList) {
        super(context, mDataList);
    }

    @Override
    protected void bindDataForView(ViewHolder holder, HomeRes homeRes, int position) {
        RelativeLayout title = holder.getView(R.id.i_home_title);
        RelativeLayout details = holder.getView(R.id.i_home_details);
        AppCompatImageView img = holder.getView(R.id.i_home_img);
        AppCompatImageView shop = holder.getView(R.id.i_home_shop);
        AppCompatTextView name = holder.getView(R.id.i_home_name);
        AppCompatTextView address = holder.getView(R.id.i_home_address);
        AppCompatTextView count = holder.getView(R.id.i_home_count);
        AppCompatTextView auto = holder.getView(R.id.i_home_auto);
        AppCompatTextView distance = holder.getView(R.id.i_home_distance);

        if (position == 0) {
            title.setVisibility(View.VISIBLE);
        } else {
            title.setVisibility(View.GONE);
        }

        ShowImageUtils.showImageView(getContext(), port + homeRes.getImage(), img);
        name.setText(homeRes.getName());
        String sAddress = homeRes.getArea().getParentArea().getName() + "·" + homeRes.getArea().getName();
        address.setText(sAddress);
        distance.setText(String.format(getContext().getString(R.string.distance), homeRes.getDistance() + ""));
        count.setText(String.format(getContext().getString(R.string.scenic_spot_count), homeRes.getSceneryCount() + ""));
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.item_home, parent, false);
        return new ViewHolder(view);
    }
}
