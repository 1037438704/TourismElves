package com.tourismelves.view.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.tourismelves.R;
import com.tourismelves.model.bean.CouponBean;
import com.tourismelves.model.bean.ElfsaidBean;

import java.util.List;

/**
 * Created by fanhui on 2018/8/19.
 */

public class CouponAdapter extends RecyclerView.Adapter<CouponAdapter.MyViewHolder> {

    Context context;
    List<CouponBean.DataListBean> listBeen;


    public CouponAdapter(Context context, List<CouponBean.DataListBean> listBeen) {
        this.context = context;
        this.listBeen = listBeen;
    }

    public void replaceData(@NonNull List<ElfsaidBean.DataListBean> list) {
//        if (listBeen == null) {
//            return;
//        }
//
////        if (list == null || list.isEmpty()) {
////            LogUtil.e(TAG, "插入的数据集为空或长度小于等于零, 请检查你的数据集!");
////            return;
////        }
//
//        listBeen = list;
//        notifyDataSetChanged();
    }

    //从最底下插入一组数据
    public void insertItems(@NonNull List<ElfsaidBean.DataListBean> list) {
        this.insertItems(list);
    }



    @Override
    public CouponAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.coupon_item,parent,false);
        CouponAdapter.MyViewHolder myViewHolder = new CouponAdapter.MyViewHolder(view);
        return myViewHolder;
        //     return view;
    }

    @Override
    public void onBindViewHolder(CouponAdapter.MyViewHolder holder, final int position) {

        holder.tv_name.setText(listBeen.get(position).getName());
        holder.tv_content.setText(listBeen.get(position).getDescription());
        holder.tv_cond.setText(listBeen.get(position).getConditions());
        holder.tv_time.setText(listBeen.get(position).getCreateDate()+"-"+listBeen.get(position).getExpiryTime());




    }
    @Override
    public int getItemCount() {
        return listBeen.size();
    }
    public class MyViewHolder extends RecyclerView.ViewHolder {


        TextView tv_name,tv_price,tv_content,tv_cond,tv_time;
        public MyViewHolder(final View itemView) {
            super(itemView);

            tv_name = itemView.findViewById(R.id.coupon_name);
            tv_content = itemView.findViewById(R.id.coupon_content);
            tv_cond = itemView.findViewById(R.id.coupon_cond);
            tv_time = itemView.findViewById(R.id.coupon_time);

        }
    }
}
