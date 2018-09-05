package com.tourismelves.view.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatTextView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tourismelves.R;
import com.tourismelves.view.adapter.base.RecyclerBaseAdapter;
import com.tourismelves.view.adapter.base.ViewHolder;

import java.util.List;

public class NearScenicSpotTypeAdapter extends RecyclerBaseAdapter<String> {

    public NearScenicSpotTypeAdapter(@NonNull Context context, @NonNull List<String> mDataList) {
        super(context, mDataList);
    }

    @Override
    protected void bindDataForView(ViewHolder holder, String s, int position) {
        AppCompatTextView name = holder.getView(R.id.i_near_scenic_spot_type_name);
        name.setText(s);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(getContext()).inflate(R.layout.item_near_scenic_spot_type, parent, false);
        return new ViewHolder(inflate);
    }
}
