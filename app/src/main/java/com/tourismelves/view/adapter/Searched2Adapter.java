package com.tourismelves.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatTextView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.amap.api.maps.model.LatLng;
import com.tourismelves.R;
import com.tourismelves.model.res.SearchedRes;
import com.tourismelves.view.activity.InterpretationListActivity;
import com.tourismelves.view.adapter.base.RecyclerBaseAdapter;
import com.tourismelves.view.adapter.base.ViewHolder;

import java.util.List;

public class Searched2Adapter extends RecyclerBaseAdapter<SearchedRes.DataListBean> {
    private int type = 0;

    public Searched2Adapter(@NonNull Context context, @NonNull List<SearchedRes.DataListBean> mDataList, int type) {
        super(context, mDataList);
        this.type = type;
    }

    @Override
    protected void bindDataForView(ViewHolder holder, final SearchedRes.DataListBean dataListBean, int position) {
        AppCompatTextView name = holder.getView(R.id.i_searched2_name);
        View view = holder.getView(R.id.i_searched2_line);
        view.setVisibility(position == getItemCount() - 1 ? View.GONE : View.VISIBLE);
        name.setText(dataListBean.getName());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (type == 0) {
                    Intent intent = new Intent(getContext(), InterpretationListActivity.class);
                    intent.putExtra("ordId", dataListBean.getOrgId());
                    intent.putExtra("name", dataListBean.getName());
                    intent.putExtra("distance", dataListBean.getArea().getParentArea().getName() + " " + dataListBean.getArea().getName());
                    intent.putExtra("latlng", new LatLng(dataListBean.getLatitude(), dataListBean.getLongitude()));
                    getContext().startActivity(intent);
                } else {
//                    Intent intent = new Intent(getContext(), ElverSayDetailsActivity.class);
//                    intent.putExtra("titlename", dataListBean.getShortTitle());
//                    intent.putExtra("acticleid", dataListBean.getArticleId() + "");
//                    getContext().startActivity(intent);
                }
            }
        });
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(getContext()).inflate(R.layout.item_searched2, parent, false);
        return new ViewHolder(inflate);
    }
}
