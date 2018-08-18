package com.tourismelves.view.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;
import com.tourismelves.R;
import com.tourismelves.model.bean.ScenicBean;

import java.util.List;

/**
 * Created by fanhui on 2018/8/14.
 */

public class ScenicAdaptermain extends RecyclerView.Adapter<ScenicAdaptermain.MyViewHolder> {

    Context context;

    List<ScenicBean.DataListBean> listBeen;


    public ScenicAdaptermain(Context context, List<ScenicBean.DataListBean> listBeen) {
        this.context = context;
        this.listBeen = listBeen;
    }

    @Override
    public ScenicAdaptermain.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        View view= LayoutInflater.from(context).inflate(R.layout.scenic_item,parent,null);
//
//        return myViewHolder;
        Fresco.initialize(context);
        View view = LayoutInflater.from(context).inflate(R.layout.scenic_item, null);
        ScenicAdaptermain.MyViewHolder myViewHolder = new ScenicAdaptermain.MyViewHolder(view);
        return myViewHolder;
        //     return view;
    }

    @Override
    public void onBindViewHolder(ScenicAdaptermain.MyViewHolder holder, final int position) {

//        holder.tv_money.setText(listBeen.get(position).getName());
//        holder.tv_moneytwo.setText("￥"+listBeen.get(position).getGold());
//        holder.tv_moneyzs.setText("再送"+listBeen.get(position).getGive()+"金币");

//        baseViewHolder.setText(R.id.name_item,dataListBean.getName());
//
//        Glide.with(mContext).load("http://211.157.162.2/"+dataListBean.getImage())
//                .into((ImageView)baseViewHolder.getView(R.id.image_item));

        Uri uri = Uri.parse("http://211.157.162.2/"+listBeen.get(position).getImage());
        holder.userAvatar.setImageURI(uri);
        holder.userAvatar.getLayoutParams().height = (position % 2)*100 + 400;
        holder.userName.setText(listBeen.get(position).getName());
        holder.tv_content.setText(listBeen.get(position).getDescription());

//        holder.tv_name.setText(listBeen.get(position).getName());
//        Glide.with(context).load("http://211.157.162.2/"+listBeen.get(position).getImage())
//                .into(holder.im_scenic);

    }
    @Override
    public int getItemCount() {
        return listBeen.size();
    }
    public class MyViewHolder extends RecyclerView.ViewHolder {
         SimpleDraweeView userAvatar;
         TextView userName;
         TextView tv_content;
        public MyViewHolder(final View itemView) {
            super(itemView);
            userAvatar =  itemView.findViewById(R.id.user_avatar);
            userName =  itemView.findViewById(R.id.user_name);
            tv_content = itemView.findViewById(R.id.scenic_content);
        }
    }
}
