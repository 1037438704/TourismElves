package com.tourismelves.view.adapter;

import android.content.Context;
import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tourismelves.R;
import com.tourismelves.model.bean.SettlementBean;
import com.tourismelves.utils.glide.ShowImageUtils;
import com.tourismelves.view.adapter.base.RecyclerBaseAdapter;
import com.tourismelves.view.adapter.base.ViewHolder;

import java.util.List;

import static com.tourismelves.app.constant.UrlConstants.port;

/**
 * 结算
 */

public class SettlementAdapter extends RecyclerBaseAdapter<SettlementBean> {

    public SettlementAdapter(@NonNull Context context, @NonNull List<SettlementBean> mDataList) {
        super(context, mDataList);
    }

    @Override
    protected void bindDataForView(ViewHolder holder, SettlementBean s, int position) {
        AppCompatImageView img = holder.getView(R.id.i_settlement_img);
        AppCompatTextView  money= holder.getView(R.id.i_settlement_money);
        AppCompatTextView count = holder.getView(R.id.i_settlement_count);
        AppCompatTextView name = holder.getView(R.id.i_settlement_name);
        AppCompatTextView original_money = holder.getView(R.id.i_settlement_original_money);

        original_money.setPaintFlags(original_money.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);

        ShowImageUtils.showImageView(getContext(), port + s.getImgSrc(), img);
        count.setText(String.format(getContext().getString(R.string.scenic_spot_count), s.getSceneryCount() + ""));
        money.setText(Html.fromHtml("¥<b>" + s.getMoney() + "</b>"));
        original_money.setText(Html.fromHtml("¥" + s.getOriginalMoney()));
        name.setText(s.getName());
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(getContext()).inflate(R.layout.item_settlement, parent, false);
        return new ViewHolder(inflate);
    }
}
