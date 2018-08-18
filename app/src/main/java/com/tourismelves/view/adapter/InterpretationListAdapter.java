package com.tourismelves.view.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tourismelves.R;
import com.tourismelves.view.adapter.base.RecyclerBaseAdapter;
import com.tourismelves.view.adapter.base.ViewHolder;

import java.util.List;


public class InterpretationListAdapter extends RecyclerBaseAdapter<String> {

    public InterpretationListAdapter(@NonNull Context context, @NonNull List<String> mDataList) {
        super(context, mDataList);
    }

    @Override
    protected void bindDataForView(ViewHolder holder, String s, int position) {

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(getContext()).inflate(R.layout.item_fragment_interpretation_list, parent, false);
        return new ViewHolder(inflate);
    }

    @Override
    public int getItemCount() {
        return 30;
    }
}
