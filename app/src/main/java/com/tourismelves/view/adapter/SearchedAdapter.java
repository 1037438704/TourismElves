package com.tourismelves.view.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tourismelves.R;
import com.tourismelves.model.res.SearchedRes;
import com.tourismelves.view.adapter.base.RecyclerBaseAdapter;
import com.tourismelves.view.adapter.base.ViewHolder;

import java.util.List;

public class SearchedAdapter extends RecyclerBaseAdapter<SearchedRes> {
    public SearchedAdapter(@NonNull Context context, @NonNull List<SearchedRes> mDataList) {
        super(context, mDataList);
    }

    @Override
    protected void bindDataForView(ViewHolder holder, SearchedRes searchedRes, int position) {
        AppCompatTextView name = holder.getView(R.id.i_searched_name);
        RecyclerView recyclerView = holder.getView(R.id.i_searched_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setFocusableInTouchMode(false);
        recyclerView.requestFocus();


        name.setText(searchedRes.getName());
        int type = searchedRes.getName().equals("景区") ? 0 : 1;
        recyclerView.setAdapter(new Searched2Adapter(getContext(), searchedRes.getDataList(),type));
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(getContext()).inflate(R.layout.item_searched, parent, false);
        return new ViewHolder(inflate);
    }
}
