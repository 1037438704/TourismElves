package com.tourismelves.view.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatImageView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tourismelves.R;
import com.tourismelves.model.bean.AttractionsBean;
import com.tourismelves.utils.glide.ShowImageUtils;
import com.tourismelves.view.adapter.base.RecyclerBaseAdapter;
import com.tourismelves.view.adapter.base.ViewHolder;

import java.util.List;

import static com.tourismelves.app.constant.UrlConstants.port;

/**
 * 解说详情
 */

public class InterpretationDetailsAdapter extends RecyclerBaseAdapter<AttractionsBean> {
    private OnClickItemListener onClickItemListener;

    public void setOnClickItemListener(OnClickItemListener onClickItemListener) {
        this.onClickItemListener = onClickItemListener;
    }

    public InterpretationDetailsAdapter(@NonNull Context context, @NonNull List<AttractionsBean> mDataList) {
        super(context, mDataList);
    }

    @Override
    protected void bindDataForView(ViewHolder holder, AttractionsBean attractionsBean, final int position) {
        AppCompatImageView icon = holder.getView(R.id.i_interpretation_details_icon);

        if (attractionsBean.getPhotoList() != null && attractionsBean.getPhotoList().size() > 0) {
            String photoPath = port + attractionsBean.getPhotoList().get(0).getPhotoPath();
            ShowImageUtils.showImageView(getContext(), photoPath,
                    (int)  getContext().getResources().getDimension(R.dimen.dp150),
                    (int)  getContext().getResources().getDimension(R.dimen.dp84),
                    icon);
        } else {
            ShowImageUtils.showImageView(getContext(), R.mipmap.ic_launcher, icon);
        }

        icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onClickItemListener != null) {
                    onClickItemListener.onClickItem(position);
                }
            }
        });
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.item_interpretation_details, parent, false);
        return new ViewHolder(view);
    }

    public interface OnClickItemListener {
        void onClickItem(int position);
    }
}
