package com.tourismelves.view.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tourismelves.R;
import com.tourismelves.model.res.ApkDownloadInfoRes;
import com.tourismelves.utils.glide.ShowImageUtils;
import com.tourismelves.view.adapter.base.RecyclerBaseAdapter;
import com.tourismelves.view.adapter.base.ViewHolder;

import java.util.List;


public class AllDownload2Adapter extends RecyclerBaseAdapter<ApkDownloadInfoRes> {


    public AllDownload2Adapter(@NonNull Context context, @NonNull List<ApkDownloadInfoRes> mDataList) {
        super(context, mDataList);
    }

    @Override
    protected void bindDataForView(ViewHolder holder, final ApkDownloadInfoRes attractionsBean, final int position) {
        AppCompatTextView content = holder.getView(R.id.i_interpretation_content);
        AppCompatTextView name = holder.getView(R.id.i_interpretation_name);
        AppCompatImageView icon = holder.getView(R.id.i_interpretation_icon);
        AppCompatImageView play = holder.getView(R.id.i_interpretation_play);
        View locked = holder.getView(R.id.i_interpretation_locked);


        int unLocked = 1;
        locked.setVisibility(unLocked == 0 ? View.VISIBLE : View.GONE);
        play.setVisibility(unLocked == 1 ? View.VISIBLE : View.GONE);

        name.setText(attractionsBean.getName());
        String photoPath = attractionsBean.getImageUrl()
                + "_" + (int) getContext().getResources().getDimension(R.dimen.dp75) + "x" +
                (int) getContext().getResources().getDimension(R.dimen.dp75) + ".jpg";
        ShowImageUtils.showImageView(getContext(), photoPath, icon);

        content.setText(attractionsBean.getContent());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(getContext()).inflate(R.layout.item_fragment_interpretation_list, parent, false);
        return new ViewHolder(inflate);
    }

}
