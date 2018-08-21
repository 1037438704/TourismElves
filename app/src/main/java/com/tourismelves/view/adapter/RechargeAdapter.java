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

    }

    @Override
    public void onBindViewHolder(final RechargeAdapter.MyViewHolder holder, final int position) {

        if (onItemClickListener!=null) {
            holder.tv_cz.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.OnItem(holder.itemView, position);
                }
            });

        }
        holder.tv_money.setText(listBeen.get(position).getName()+"+再送"+listBeen.get(position).getGive()+"金币");
        holder.tv_moneytwo.setText("￥"+listBeen.get(position).getGold());



    }
    @Override
    public int getItemCount() {
        return listBeen.size();
    }
    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tv_money,tv_moneytwo,tv_cz;

        public MyViewHolder(final View itemView) {
            super(itemView);
            tv_money = itemView.findViewById(R.id.recharge_money);
            tv_moneytwo = itemView.findViewById(R.id.recharge_moneytwo);
            tv_cz = itemView.findViewById(R.id.recharge_cz);

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
