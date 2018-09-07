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
import com.tourismelves.model.bean.ElfsaidBean;
import com.tourismelves.model.bean.WaitpayBean;

import java.util.List;

/**
 * Created by fanhui on 2018/9/6.
 */

public class BuyAdapter extends RecyclerView.Adapter<BuyAdapter.MyViewHolder> {

    Context context;
    List<WaitpayBean.DataListBean> listBeen;

    public BuyAdapter(Context context, List<WaitpayBean.DataListBean> listBeen) {
        this.context = context;
        this.listBeen = listBeen;
    }

    public void replaceData(@NonNull List<WaitpayBean.DataListBean> list) {
        if (listBeen == null) {
            return;
        }

//        if (list == null || list.isEmpty()) {
//            LogUtil.e(TAG, "插入的数据集为空或长度小于等于零, 请检查你的数据集!");
//            return;
//        }

        listBeen = list;
        notifyDataSetChanged();
    }

    //从最底下插入一组数据
    public void insertItems(@NonNull List<ElfsaidBean.DataListBean> list) {
        this.insertItems(list);
    }



    @Override
    public BuyAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.buyorder_item,parent,false);
        BuyAdapter.MyViewHolder myViewHolder = new BuyAdapter.MyViewHolder(view);
        return myViewHolder;
        //     return view;
    }

    @Override
    public void onBindViewHolder(final BuyAdapter.MyViewHolder holder, final int position) {




        if (onItemClickListener!=null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.OnItem(holder.itemView, position);
                }
            });

        }



//        try {
//            Glide.with(context).load("http://211.157.162.2/"+listBeen.get(position).getOrgList().get(0).getImage());
//        }catch (Exception e){
//            e.printStackTrace();
//        }
        Glide.with(context).load("http://211.157.162.2/"+listBeen.get(position).getOrgList().get(0).getImage())
                .into(holder.im_shop);
        holder.tv_name1.setText(listBeen.get(position).getOrgList().get(0).getName());
        holder.tv_money.setText(listBeen.get(position).getOrgList().get(0).getInsertTime()+"");
        holder.tv_content.setText(listBeen.get(position).getOrgList().get(0).getDescription());




    }

    @Override
    public int getItemCount() {
        return listBeen.size();
    }
    public class MyViewHolder extends RecyclerView.ViewHolder {


        ImageView im_shop;
        TextView tv_name1,tv_money,tv_content;

        public MyViewHolder(final View itemView) {
            super(itemView);
            tv_content = itemView.findViewById(R.id.buyorder_content);
            im_shop = itemView.findViewById(R.id.waitpay_im);
            tv_name1 = itemView.findViewById(R.id.waitpay_name);
            tv_money = itemView.findViewById(R.id.waitpay_money);


        }
    }


    public interface OnItemClickListener{
        void OnItem(View view,int position);

    }
    private OnItemClickListener onItemClickListener;
    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }
}
