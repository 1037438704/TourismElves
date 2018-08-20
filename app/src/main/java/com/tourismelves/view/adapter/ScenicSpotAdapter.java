package com.tourismelves.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tourismelves.R;
import com.tourismelves.model.res.HomeRes;
import com.tourismelves.utils.glide.ShowImageUtils;
import com.tourismelves.utils.system.ResolutionUtil;
import com.tourismelves.view.activity.InterpretationListActivity;
import com.tourismelves.view.adapter.base.RecyclerBaseAdapter;
import com.tourismelves.view.adapter.base.ViewHolder;

import java.util.List;

import static com.tourismelves.app.constant.UrlConstants.port;

/**
 * 热门景区适配器
 */

public class ScenicSpotAdapter extends RecyclerBaseAdapter<HomeRes> {
    private String provinces;
    private int h, w;

    public ScenicSpotAdapter(@NonNull Context context, @NonNull List<HomeRes> mDataList, String provinces) {
        super(context, mDataList);
        this.provinces = provinces;
        h = (int) getContext().getResources().getDimension(R.dimen.dp170);
        w = (int) (ResolutionUtil.getInstance(getContext()).getWidth() - getContext().getResources().getDimension(R.dimen.dp20));
    }

    public void setProvinces(String provinces) {
        this.provinces = provinces;
    }

    @Override
    protected void bindDataForView(ViewHolder holder, HomeRes homeRes, int position) {
        AppCompatImageView img = holder.getView(R.id.i_scenic_spot_img);
        AppCompatTextView name = holder.getView(R.id.i_scenic_spot_name);
        AppCompatTextView address = holder.getView(R.id.i_scenic_spot_address);
        AppCompatTextView count = holder.getView(R.id.i_scenic_spot_count);
        AppCompatTextView content = holder.getView(R.id.i_scenic_spot_content);
        AppCompatTextView autoPlay = holder.getView(R.id.i_scenic_spot_auto_play);

        autoPlay.setVisibility(homeRes.getIsAutoplay() == 1 ? View.VISIBLE : View.GONE);
        ShowImageUtils.showRounded(getContext(), port + homeRes.getImage(), w, h, img, 0);
        name.setText(homeRes.getName());
        String sAddress = homeRes.getArea().getParentArea().getName() + " " + homeRes.getArea().getName();
        address.setText(sAddress + " " + String.format(getContext().getString(R.string.distance), homeRes.getDistance() + ""));
        count.setText(String.format(getContext().getString(R.string.scenic_spot_count), homeRes.getSceneryCount() + ""));
        content.setText(homeRes.getDescription());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), InterpretationListActivity.class);
                getContext().startActivity(intent);
            }
        });
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.item_scenic_spot, parent, false);
        return new ViewHolder(view);
    }
}
