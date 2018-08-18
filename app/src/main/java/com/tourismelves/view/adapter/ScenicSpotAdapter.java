package com.tourismelves.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tourismelves.R;
import com.tourismelves.model.res.HomeRes;
import com.tourismelves.utils.glide.ShowImageUtils;
import com.tourismelves.view.activity.InterpretationListActivity;
import com.tourismelves.view.adapter.base.RecyclerBaseAdapter;
import com.tourismelves.view.adapter.base.ViewHolder;

import java.util.List;

import static com.tourismelves.app.constant.UrlConstants.port;

/**
 * 热门景区适配器
 */

public class ScenicSpotAdapter extends RecyclerBaseAdapter<HomeRes> {
    private String provinces;

    public ScenicSpotAdapter(@NonNull Context context, @NonNull List<HomeRes> mDataList, String provinces) {
        super(context, mDataList);
        this.provinces = provinces;
    }

    public void setProvinces(String provinces) {
        this.provinces = provinces;
    }

    @Override
    protected void bindDataForView(ViewHolder holder, HomeRes homeRes, int position) {
        AppCompatImageView img = holder.getView(R.id.i_scenic_spot_img);
//        AppCompatImageView more = holder.getView(R.id.i_scenic_spot_more);
//        AppCompatTextView elfSaid = holder.getView(R.id.i_scenic_spot_elf_said);
        AppCompatTextView name = holder.getView(R.id.i_scenic_spot_name);
        AppCompatTextView address = holder.getView(R.id.i_scenic_spot_address);
        AppCompatTextView count = holder.getView(R.id.i_scenic_spot_count);
        AppCompatTextView money = holder.getView(R.id.i_scenic_spot_money);
        AppCompatTextView content = holder.getView(R.id.i_scenic_spot_content);
//        RecyclerView elfSaidList = holder.getView(R.id.i_scenic_spot_elf_said_list);
//        RelativeLayout elfSaidLayout = holder.getView(R.id.i_scenic_spot_layout);

//        if (position == 0) {
//            hot.setVisibility(View.VISIBLE);
//            elfSaid.setVisibility(View.VISIBLE);
//
//            hot.setText(provinces.equals("") ? homeRes.getArea().getParentArea().getName() : provinces);
//        } else {
//            hot.setVisibility(View.INVISIBLE);
//            elfSaid.setVisibility(View.GONE);
//        }


        ShowImageUtils.showTopRounded(getContext(), port + homeRes.getImage(), img, 0);
        name.setText(homeRes.getName());
        String sAddress = homeRes.getArea().getParentArea().getName() + " " + homeRes.getArea().getName();
        address.setText(sAddress+" "+String.format(getContext().getString(R.string.distance), homeRes.getDistance() + ""));
        count.setText(String.format(getContext().getString(R.string.scenic_spot_count), homeRes.getSceneryCount() + ""));
        content.setText(homeRes.getDescription());
//        List<HomeRes.ArticleListBean> articleList = homeRes.getArticleList();
//        if (articleList.size() == 0) {
//            elfSaidLayout.setVisibility(View.GONE);
//            elfSaidList.setVisibility(View.GONE);
//        } else {
//            elfSaidLayout.setVisibility(View.VISIBLE);
//            elfSaidList.setVisibility(View.VISIBLE);
//            elfSaidList.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
//            Home2Adapter home2Adapter = new Home2Adapter(getContext(), articleList);
//            elfSaidList.setAdapter(home2Adapter);
//        }


//        more.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //TODO 更多点击事件
//            }
//        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), InterpretationListActivity.class);
                getContext().startActivity(intent);
            }
        });
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.item_scenic_spot, parent, false);
        return new ViewHolder(view);
    }
}
