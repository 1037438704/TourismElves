package com.tourismelves.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tourismelves.R;
import com.tourismelves.model.bean.RechargeBean;

import java.util.List;


/**
 * Created by fanhui on 2018/8/13.
 */

public class RechargeAdapter extends RecyclerView.Adapter<RechargeAdapter.MyViewHolder> {

    Context context;

    List<RechargeBean.DataListBean> listBeen;


    public RechargeAdapter(Context context, List<RechargeBean.DataListBean> listBeen) {
        this.context = context;
        this.listBeen = listBeen;
    }




    @Override
    public RechargeAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.recharge_item,parent,false);
        RechargeAdapter.MyViewHolder myViewHolder = new RechargeAdapter.MyViewHolder(view);
        return myViewHolder;
   //     return view;
    }

    @Override
    public void onBindViewHolder(RechargeAdapter.MyViewHolder holder, final int position) {

        holder.tv_money.setText(listBeen.get(position).getName());
        holder.tv_moneytwo.setText("￥"+listBeen.get(position).getGold());
        holder.tv_moneyzs.setText("再送"+listBeen.get(position).getGive()+"金币");


    }
    @Override
    public int getItemCount() {
        return listBeen.size();
    }
    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tv_money,tv_moneytwo,tv_moneyzs;
        public MyViewHolder(final View itemView) {
            super(itemView);
            tv_money = itemView.findViewById(R.id.recharge_money);
            tv_moneytwo = itemView.findViewById(R.id.recharge_moneytwo);
            tv_moneyzs = itemView.findViewById(R.id.recharge_zs);
        }
    }
}
