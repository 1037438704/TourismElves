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
import com.tourismelves.view.widget.RatingStar;

import java.util.List;

/**
 * 附近 景区适配器
 */
public class NearScenicSpotAdapter extends RecyclerBaseAdapter<HomeRes> {

    private final int w;

    public NearScenicSpotAdapter(@NonNull Context context, @NonNull List<HomeRes> mDataList) {
        super(context, mDataList);
        w = (int) getContext().getResources().getDimension(R.dimen.dp105);
    }

    @Override
    protected void bindDataForView(ViewHolder holder, HomeRes homeRes, int position) {
        AppCompatImageView i_near_scenic_spot_img = holder.getView(R.id.i_near_scenic_spot_img);
        AppCompatImageView i_near_scenic_spot_ticket_priority = holder.getView(R.id.i_near_scenic_spot_ticket_priority);

        AppCompatTextView i_near_scenic_spot_name = holder.getView(R.id.i_near_scenic_spot_name);
        AppCompatTextView i_near_scenic_spot_distance = holder.getView(R.id.i_near_scenic_spot_distance);
        AppCompatTextView i_near_scenic_spot_score = holder.getView(R.id.i_near_scenic_spot_score);
        AppCompatTextView i_near_scenic_spot_ticket_name = holder.getView(R.id.i_near_scenic_spot_ticket_name);
        AppCompatTextView i_near_scenic_spot_ticket_money = holder.getView(R.id.i_near_scenic_spot_ticket_money);

        RatingStar i_near_scenic_spot_rating = holder.getView(R.id.i_near_scenic_spot_rating);
        RelativeLayout i_near_scenic_spot_auto_play = holder.getView(R.id.i_near_scenic_spot_auto_play);


        i_near_scenic_spot_auto_play.setVisibility(homeRes.getIsAutoplay() == 1 ? View.VISIBLE : View.GONE);
        i_near_scenic_spot_rating.setCurRating(homeRes.getScore());
        i_near_scenic_spot_score.setText(homeRes.getScore() + "");
        i_near_scenic_spot_ticket_name.setText(homeRes.getName() + "门票");
        i_near_scenic_spot_name.setText(homeRes.getName());
        i_near_scenic_spot_ticket_money.setText("¥" + homeRes.getPrice());
        i_near_scenic_spot_distance.setText(String.format(getContext().getString(R.string.distance), homeRes.getDistance() + ""));
        ShowImageUtils.showImageView(getContext(), homeRes.getImage(), w, w, i_near_scenic_spot_img);

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.item_near_scenic_spot, parent, false);
        return new ViewHolder(view);
    }
}
