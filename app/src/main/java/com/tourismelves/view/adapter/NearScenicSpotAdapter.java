package com.tourismelves.view.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.core.PoiItem;
import com.amap.api.services.poisearch.Photo;
import com.amap.api.services.poisearch.PoiItemExtension;
import com.tourismelves.R;
import com.tourismelves.utils.MapUtils;
import com.tourismelves.utils.glide.ShowImageUtils;
import com.tourismelves.utils.system.LocationUtil;
import com.tourismelves.view.adapter.base.RecyclerBaseAdapter;
import com.tourismelves.view.adapter.base.ViewHolder;
import com.tourismelves.view.widget.RatingStar;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.tourismelves.app.constant.CommentConstants.latitude;
import static com.tourismelves.app.constant.CommentConstants.longitude;

/**
 * 附近 景区适配器
 */
public class NearScenicSpotAdapter extends RecyclerBaseAdapter<PoiItem> {

    private final int w;

    public NearScenicSpotAdapter(@NonNull Context context, @NonNull List<PoiItem> mDataList) {
        super(context, mDataList);
        w = (int) getContext().getResources().getDimension(R.dimen.dp105);
    }

    @Override
    protected void bindDataForView(ViewHolder holder, PoiItem item, int position) {
        AppCompatImageView i_near_scenic_spot_img = holder.getView(R.id.i_near_scenic_spot_img);

        AppCompatTextView i_near_scenic_spot_name = holder.getView(R.id.i_near_scenic_spot_name);
        AppCompatTextView i_near_scenic_spot_distance = holder.getView(R.id.i_near_scenic_spot_distance);
        AppCompatTextView i_near_scenic_spot_score = holder.getView(R.id.i_near_scenic_spot_score);
        AppCompatTextView i_near_scenic_spot_ticket_name = holder.getView(R.id.i_near_scenic_spot_ticket_name);
        AppCompatTextView i_near_scenic_spot_ticket_money = holder.getView(R.id.i_near_scenic_spot_ticket_money);

        RatingStar i_near_scenic_spot_rating = holder.getView(R.id.i_near_scenic_spot_rating);
        RelativeLayout i_near_scenic_spot_auto_play = holder.getView(R.id.i_near_scenic_spot_auto_play);
        RecyclerView recyeler = holder.getView(R.id.i_near_scenic_spot_types);

        recyeler.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        String[] split = item.getTypeDes().split(";");
        List<String> types = new ArrayList<>();
        Collections.addAll(types, split);
        recyeler.setAdapter(new NearScenicSpotTypeAdapter(getContext(), types));

        PoiItemExtension poiExtension = item.getPoiExtension();
        String s = poiExtension.getmRating().equals("") ? "0" : poiExtension.getmRating();
        Float score = Float.valueOf(s);
        i_near_scenic_spot_rating.setCurRating(score);
        i_near_scenic_spot_score.setText(s);

        i_near_scenic_spot_ticket_name.setText(item.getTitle());
        i_near_scenic_spot_name.setText(item.getTitle());

        i_near_scenic_spot_ticket_money.setText("¥" + 0);
        i_near_scenic_spot_ticket_money.setVisibility(View.GONE);

        LatLonPoint llp = item.getLatLonPoint();
        final double lon = llp.getLongitude();
        final double lat = llp.getLatitude();
        int distance = (int) (LocationUtil.getInstance(getContext()).getDistance(lon, lat, longitude, latitude) / 1000);
        i_near_scenic_spot_distance.setText(String.format(getContext().getString(R.string.distance), distance + ""));

        String url = "";
        List<Photo> photos = item.getPhotos();
        if (photos != null && photos.size() > 0)
            url = photos.get(0).getUrl();
        ShowImageUtils.showImageView(getContext(), url, w, w, i_near_scenic_spot_img);


        i_near_scenic_spot_auto_play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MapUtils.getInstance(getContext()).openMap(lat, lon);
            }
        });

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.item_near_scenic_spot, parent, false);
        return new ViewHolder(view);
    }
}
