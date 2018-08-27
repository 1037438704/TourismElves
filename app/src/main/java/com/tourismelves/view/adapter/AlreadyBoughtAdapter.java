package com.tourismelves.view.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tourismelves.R;
import com.tourismelves.model.bean.AlreadyBoughtBean;
import com.tourismelves.utils.glide.ShowImageUtils;
import com.tourismelves.view.adapter.base.RecyclerBaseAdapter;
import com.tourismelves.view.adapter.base.ViewHolder;

import java.util.List;

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
    protected void bindDataForView(ViewHolder holder, AlreadyBoughtBean.DataListBean dataListBean, int position) {
        AppCompatImageView icon = holder.getView(R.id.i_already_bought_icon);
        AppCompatTextView name = holder.getView(R.id.i_already_bought_name);
        AppCompatTextView content = holder.getView(R.id.i_already_bought_content);
        AppCompatTextView time = holder.getView(R.id.i_already_bought_time);


        time.setText("购买时间: " + dataListBean.getPaymentTime());
        if (dataListBean.getOrgList() != null && dataListBean.getOrgList().size() > 0) {
            AlreadyBoughtBean.DataListBean.OrgListBean orgListBean = dataListBean.getOrgList().get(0);
            ShowImageUtils.showImageView(getContext(), port + orgListBean.getImage(),w,w,icon);
            name.setText(orgListBean.getName());
            String s = orgListBean.getSummary().equals("") ? orgListBean.getDescription() : orgListBean.getSummary();
            content.setText(s);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(getContext()).inflate(R.layout.item_already_bought, parent, false);
        return new ViewHolder(inflate);
    }
}
