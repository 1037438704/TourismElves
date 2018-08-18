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

import java.util.List;

/**
 * 选择城市的列表
 */

public class SelectCityAdapter extends RecyclerBaseAdapter<SelectCity2Res> {

    public SelectCityAdapter(@NonNull Context context, @NonNull List<SelectCity2Res> mDataList) {
        super(context, mDataList);
    }

    @Override
    protected void bindDataForView(ViewHolder holder, SelectCity2Res selectCityRes, int position) {
        AppCompatTextView i_select_city_letter = holder.getView(R.id.i_select_city_letter);
        RecyclerView i_select_city_list = holder.getView(R.id.i_select_city_list);

        i_select_city_letter.setText(selectCityRes.getRemark());

        i_select_city_list.setLayoutManager(new GridLayoutManager(getContext(), 3));
        i_select_city_list.setAdapter(new SelectCity2Adapter(getContext(), selectCityRes.getCitys()));
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.item_select_city, parent, false);
        return new ViewHolder(view);
    }

    /**
     * 根据ListView的当前位置获取分类的首字母的char ascii值
     */
    public int getSectionForPosition(int position) {
        return getDataList().get(position).getRemark().charAt(0);
    }


    /**
     * 根据分类的首字母的Char ascii值获取其第一次出现该首字母的位置
     */
    public int getPositionForSection(int section) {
        for (int i = 0; i < getItemCount(); i++) {
            String sortStr = getDataList().get(i).getRemark();
            char firstChar = sortStr.toUpperCase().charAt(0);
            if (firstChar == section) {
                return i;
            }
        }
        return -1;
    }
}
