package com.tourismelves.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.amap.api.maps.model.LatLng;
import com.tourismelves.R;
import com.tourismelves.model.bean.AlreadyBoughtBean;
import com.tourismelves.utils.glide.ShowImageUtils;
import com.tourismelves.utils.system.LocationUtil;
import com.tourismelves.view.activity.InterpretationListActivity;
import com.tourismelves.view.adapter.base.RecyclerBaseAdapter;
import com.tourismelves.view.adapter.base.ViewHolder;

import java.util.List;

import static com.tourismelves.app.constant.CommentConstants.latitude;
import static com.tourismelves.app.constant.CommentConstants.longitude;
import static com.tourismelves.app.constant.UrlConstants.port;

/**
 * 已购
 */
public class AlreadyBoughtAdapter extends RecyclerBaseAdapter<AlreadyBoughtBean.DataListBean> {

    private int w;

    public AlreadyBoughtAdapter(@NonNull Context context, @NonNull List<AlreadyBoughtBean.DataListBean> mDataList) {
        super(context, mDataList);
        w = (int) getContext().getResources().getDimension(R.dimen.dp105);
    }

    @Override
    protected void bindDataForView(ViewHolder holder, final AlreadyBoughtBean.DataListBean dataListBean, int position) {
        AppCompatImageView icon = holder.getView(R.id.i_already_bought_icon);
        AppCompatTextView name = holder.getView(R.id.i_already_bought_name);
        AppCompatTextView content = holder.getView(R.id.i_already_bought_content);
        AppCompatTextView time = holder.getView(R.id.i_already_bought_time);

        time.setText("购买时间: " + dataListBean.getPaymentTime());
        if (dataListBean.getOrgList() != null && dataListBean.getOrgList().size() > 0) {
            final AlreadyBoughtBean.DataListBean.OrgListBean orgListBean = dataListBean.getOrgList().get(0);
            ShowImageUtils.showImageView(getContext(), port + orgListBean.getImage(), w, w, icon);
            name.setText(orgListBean.getName());
            String s = orgListBean.getSummary().equals("") ? orgListBean.getDescription() : orgListBean.getSummary();
            content.setText(s);

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getContext(), InterpretationListActivity.class);
                    intent.putExtra("ordId", orgListBean.getOrgId());
                    intent.putExtra("name", orgListBean.getName());
                    int distance = (int) LocationUtil.getInstance(getContext()).getDistance(orgListBean.getLongitude(), orgListBean.getLatitude(), longitude, latitude);
                    String sAddress = orgListBean.getArea().getParentArea().getName() + " " + orgListBean.getArea().getName() + " " + String.format(getContext().getString(R.string.distance), distance + "");
                    intent.putExtra("distance", sAddress);
                    intent.putExtra("latlng", new LatLng(orgListBean.getLatitude(), orgListBean.getLongitude()));
                    getContext().startActivity(intent);
                }
            });
        }


    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(getContext()).inflate(R.layout.item_already_bought, parent, false);
        return new ViewHolder(inflate);
    }
}
