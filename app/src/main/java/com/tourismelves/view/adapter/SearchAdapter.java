package com.tourismelves.view.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tourismelves.R;
import com.tourismelves.model.res.SelectCity2Res;
import com.tourismelves.view.adapter.base.RecyclerBaseAdapter;
import com.tourismelves.view.adapter.base.ViewHolder;
import com.tourismelves.view.widget.flowlayout.FlowLayout;
import com.tourismelves.view.widget.flowlayout.TagAdapter;
import com.tourismelves.view.widget.flowlayout.TagFlowLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * 搜索
 */

public class SearchAdapter extends RecyclerBaseAdapter<Object> {
    public ArrayList<String> historyData = new ArrayList<>();


    public SearchAdapter(@NonNull Context context, @NonNull List<Object> mDataList) {
        super(context, mDataList);
    }

    @Override
    protected void bindDataForView(ViewHolder holder, Object o, int position) {
        if (position == 0) {
            AppCompatTextView search_title = holder.getView(R.id.search_title);
            final TagFlowLayout search_flow = holder.getView(R.id.search_flow);

            search_title.setVisibility(historyData.size() == 0 ? View.GONE : View.VISIBLE);

            search_flow.setAdapter(new TagAdapter<String>(historyData) {
                @Override
                public View getView(FlowLayout parent, int position, String s) {
                    View inflate = LayoutInflater.from(getContext()).inflate(R.layout.item_hot_textview, search_flow, false);
                    AppCompatTextView tv = inflate.findViewById(R.id.hot_tv);
//                    if (position % 2 == 0) {
//                        Drawable drawable = getContext().getResources().getDrawable(R.mipmap.resou);
//                        tv.setCompoundDrawablesWithIntrinsicBounds(drawable, null, null, null);
//                    } else {
//                        tv.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
//                    }
                    tv.setText(s);
                    return inflate;
                }
            });
            search_flow.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
                @Override
                public boolean onTagClick(View view, int position, FlowLayout parent) {
                    if (onSearchListener != null) {
                        onSearchListener.onSearch(historyData.get(position));
                    }
                    return true;
                }
            });

        } else {
            SelectCity2Res selectCity2Res = (SelectCity2Res) o;
            AppCompatTextView search2_letter = holder.getView(R.id.search2_letter);
            RecyclerView search2_recycler = holder.getView(R.id.search2_recycler);

            search2_letter.setText(selectCity2Res.getRemark());

            search2_recycler.setLayoutManager(new GridLayoutManager(getContext(), 1));
            Search2Adapter search2Adapter = new Search2Adapter(getContext(), selectCity2Res.getCitys());
            search2_recycler.setAdapter(search2Adapter);
            search2Adapter.setOnClickSearch2Listener(new Search2Adapter.OnClickSearch2Listener() {
                @Override
                public void onSearch(String s) {
                    if (onSearchListener != null) {
                        onSearchListener.onSearch(s);
                    }
                }
            });
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        if (viewType == 0) {
            view = LayoutInflater.from(getContext()).inflate(R.layout.item_search, parent, false);
        } else {
            view = LayoutInflater.from(getContext()).inflate(R.layout.item_search2, parent, false);
        }
        return new ViewHolder(view);
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    /**
     * 根据分类的首字母的Char ascii值获取其第一次出现该首字母的位置
     */
    public int getPositionForSection(int section) {
        for (int i = 1; i < getItemCount(); i++) {
            String sortStr = ((SelectCity2Res) getDataList().get(i)).getRemark();
            char firstChar = sortStr.toUpperCase().charAt(0);
            if (firstChar == section) {
                return i;
            }
        }
        return -1;
    }

    private OnSearchListener onSearchListener;

    public void setOnSearchListener(OnSearchListener onSearchListener) {
        this.onSearchListener = onSearchListener;
    }

    public interface OnSearchListener {
        void onSearch(String search);
    }
}
