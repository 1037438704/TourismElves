package com.tourismelves.view.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatTextView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tourismelves.R;
import com.tourismelves.view.adapter.base.RecyclerBaseAdapter;
import com.tourismelves.view.adapter.base.ViewHolder;

import java.util.List;

public class Search2Adapter extends RecyclerBaseAdapter<String> {
    private OnClickSearch2Listener onClickSearch2Listener;

    public void setOnClickSearch2Listener(OnClickSearch2Listener onClickSearch2Listener) {
        this.onClickSearch2Listener = onClickSearch2Listener;
    }

    public Search2Adapter(@NonNull Context context, @NonNull List<String> mDataList) {
        super(context, mDataList);
    }

    @Override
    protected void bindDataForView(ViewHolder holder, final String s, int position) {
        AppCompatTextView search2_name = holder.getView(R.id.search2_name);
        View search2_line = holder.getView(R.id.search2_line);
        search2_line.setVisibility(position == getItemCount() - 1 ? View.GONE : View.VISIBLE);
        search2_name.setText(s);
        search2_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onClickSearch2Listener != null) {
                    onClickSearch2Listener.onSearch(s);
                }
            }
        });
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.item_search2_item, parent, false);
        return new ViewHolder(view);
    }

    public interface OnClickSearch2Listener {
        void onSearch(String s);
    }
}
