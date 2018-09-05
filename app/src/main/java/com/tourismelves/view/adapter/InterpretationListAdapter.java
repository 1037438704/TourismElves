package com.tourismelves.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
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

import java.util.ArrayList;
import java.util.List;

import static com.tourismelves.app.constant.UrlConstants.port;


public class InterpretationListAdapter extends RecyclerBaseAdapter<AttractionsBean> {
    private boolean isEdit;

    public void setEdit(boolean edit) {
        isEdit = edit;
        for (AttractionsBean a : getDataList()) {
            a.setSelect(false);
        }
        notifyDataSetChanged();
    }

    public void selectAll(boolean flag) {
        for (AttractionsBean a : getDataList()) {
            a.setSelect(flag);
        }
        notifyDataSetChanged();
    }

    public InterpretationListAdapter(@NonNull Context context, @NonNull List<AttractionsBean> mDataList) {
        super(context, mDataList);
    }

    @Override
    protected void bindDataForView(ViewHolder holder, final AttractionsBean attractionsBean, final int position) {
        AppCompatTextView content = holder.getView(R.id.i_interpretation_content);
        AppCompatTextView name = holder.getView(R.id.i_interpretation_name);
        final AppCompatTextView select = holder.getView(R.id.i_interpretation_select);
        AppCompatImageView icon = holder.getView(R.id.i_interpretation_icon);
        AppCompatImageView play = holder.getView(R.id.i_interpretation_play);
        View locked = holder.getView(R.id.i_interpretation_locked);

        if (isEdit) {
            select.setVisibility(View.VISIBLE);
            select.setSelected(attractionsBean.isSelect());
        } else {
            select.setVisibility(View.GONE);
        }


        int unLocked = attractionsBean.getUnLocked();
        locked.setVisibility(unLocked == 0 ? View.VISIBLE : View.GONE);
        play.setVisibility(unLocked == 1 ? View.VISIBLE : View.GONE);

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
                if (isEdit) {
                    attractionsBean.setSelect(!attractionsBean.isSelect());
                    select.setSelected(attractionsBean.isSelect());
                } else {
                    Intent intent = new Intent(getContext(), InterpretationDetailsActivity.class);
                    intent.putParcelableArrayListExtra("attractionsBeans", (ArrayList<? extends Parcelable>) getDataList());
                    intent.putExtra("position", position);
                    getContext().startActivity(intent);
                }
            }
        });
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(getContext()).inflate(R.layout.item_fragment_interpretation_list, parent, false);
        return new ViewHolder(inflate);
    }

}
