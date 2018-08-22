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
import com.tourismelves.model.bean.AttractionsBean;
import com.tourismelves.utils.glide.ShowImageUtils;
import com.tourismelves.view.activity.InterpretationDetailsActivity;
import com.tourismelves.view.adapter.base.RecyclerBaseAdapter;
import com.tourismelves.view.adapter.base.ViewHolder;

import java.util.List;

import static com.tourismelves.app.constant.UrlConstants.port;


public class InterpretationListAdapter extends RecyclerBaseAdapter<AttractionsBean> {

    public InterpretationListAdapter(@NonNull Context context, @NonNull List<AttractionsBean> mDataList) {
        super(context, mDataList);
    }

    @Override
    protected void bindDataForView(ViewHolder holder, AttractionsBean attractionsBean, int position) {
        AppCompatTextView content = holder.getView(R.id.i_interpretation_content);
        AppCompatTextView name = holder.getView(R.id.i_interpretation_name);
        AppCompatImageView icon = holder.getView(R.id.i_interpretation_icon);

        name.setText(attractionsBean.getName());
        if (attractionsBean.getPhotoList() != null && attractionsBean.getPhotoList().size() > 0) {
            String photoPath = port + attractionsBean.getPhotoList().get(0).getPhotoPath();
            ShowImageUtils.showImageView(getContext(), photoPath, icon);
        } else {
            ShowImageUtils.showImageView(getContext(), R.mipmap.ic_launcher, icon);
        }

        content.setText(attractionsBean.getDescription());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getContext().startActivity(new Intent(getContext(), InterpretationDetailsActivity.class));
            }
        });
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(getContext()).inflate(R.layout.item_fragment_interpretation_list, parent, false);
        return new ViewHolder(inflate);
    }

}
