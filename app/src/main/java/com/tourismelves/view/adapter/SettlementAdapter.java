package com.tourismelves.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.tourismelves.R;
import com.tourismelves.model.bean.ShopListBean;
import com.tourismelves.utils.glide.ShowImageUtils;
import com.tourismelves.view.activity.CouponActivity;
import com.tourismelves.view.adapter.base.RecyclerBaseAdapter;
import com.tourismelves.view.adapter.base.ViewHolder;

import java.util.List;

import static com.tourismelves.app.constant.UrlConstants.port;
import static com.tourismelves.view.fragment.MyFragment.isLogin;

/**
 * 结算
 */

public class SettlementAdapter extends RecyclerBaseAdapter<ShopListBean> {
    private OnSelectListener onSelectListener;
    public int couponsCount=0;

    public void setOnSelectListener(OnSelectListener onSelectListener) {
        this.onSelectListener = onSelectListener;
    }

    public SettlementAdapter(@NonNull Context context, @NonNull List<ShopListBean> mDataList) {
        super(context, mDataList);
    }

    @Override
    protected void bindDataForView(ViewHolder holder, final ShopListBean s, final int position) {
        AppCompatImageView img = holder.getView(R.id.i_settlement_img);
        final AppCompatImageView select = holder.getView(R.id.i_settlement_select);
        AppCompatTextView money = holder.getView(R.id.i_settlement_money);
        AppCompatTextView count = holder.getView(R.id.i_settlement_count);
        AppCompatTextView name = holder.getView(R.id.i_settlement_name);
        AppCompatTextView original_money = holder.getView(R.id.i_settlement_original_money);
        LinearLayout coupons_ll = holder.getView(R.id.i_settlement_coupons_ll);
        AppCompatTextView coupons = holder.getView(R.id.i_settlement_coupons);
        View line = holder.getView(R.id.i_settlement_line);

        original_money.setPaintFlags(original_money.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);

        select.setSelected(s.isSelect());

        ShowImageUtils.showImageView(getContext(), port + s.getImage(), img);
        count.setText(String.format(getContext().getString(R.string.scenic_spot_count), "1"));
        money.setText(Html.fromHtml("¥<b>" + (s.getPrice() - s.getPreferential()) + "</b>"));
        original_money.setText(Html.fromHtml("¥" + s.getPrice()));
        name.setText(s.getName());

        if (position == getItemCount() - 1) {
            coupons_ll.setVisibility(View.VISIBLE);
            coupons.setVisibility(View.VISIBLE);
            line.setVisibility(View.VISIBLE);

            coupons.setText(couponsCount+"张可用");
            coupons_ll.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!isLogin(getContext(), true)) {
                        return;
                    }
                    Intent intent = new Intent(getContext(), CouponActivity.class);
                    getContext().startActivity(intent);
                }
            });
        } else {
            coupons_ll.setVisibility(View.GONE);
            coupons.setVisibility(View.GONE);
            line.setVisibility(View.GONE);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                s.setSelect(!select.isSelected());
                select.setSelected(s.isSelect());
                if (onSelectListener != null) {
                    onSelectListener.onSelect(position);
                }
            }
        });
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(getContext()).inflate(R.layout.item_settlement, parent, false);
        return new ViewHolder(inflate);
    }

    public interface OnSelectListener {
        void onSelect(int position);
    }
}
