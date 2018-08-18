package com.tourismelves.view.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatTextView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tourismelves.R;
import com.tourismelves.model.event.SelectCityBus;
import com.tourismelves.utils.common.EventBusUtil;
import com.tourismelves.view.adapter.base.RecyclerBaseAdapter;
import com.tourismelves.view.adapter.base.ViewHolder;

import java.util.List;

/**
 * 选择城市每个子条目的列表
 */

public class SelectCity2Adapter extends RecyclerBaseAdapter<String> {

    public SelectCity2Adapter(@NonNull Context context, @NonNull List<String> mDataList) {
        super(context, mDataList);
    }

    @Override
    protected void bindDataForView(ViewHolder holder, final String s, int position) {
        AppCompatTextView i_select_city2_city = holder.getView(R.id.i_select_city2_city);
        i_select_city2_city.setText(s);
        i_select_city2_city.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBusUtil.postEvent(new SelectCityBus(s));
                ((Activity) getContext()).finish();
            }
        });
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.item_select_city2, parent, false);
        return new ViewHolder(view);
    }
}
