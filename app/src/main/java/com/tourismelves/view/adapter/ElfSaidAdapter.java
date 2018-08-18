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

import java.util.List;

/**
 * Created by fanhui on 2018/8/16.
 */

public class ElfSaidAdapter extends RecyclerView.Adapter<ElfSaidAdapter.MyViewHolder> {

    Context context;
    List<ElfsaidBean.DataListBean> listBeen;

    public ElfSaidAdapter(Context context, List<ElfsaidBean.DataListBean> listBeen) {
        this.context = context;
        this.listBeen = listBeen;
    }

    public void replaceData(@NonNull List<ElfsaidBean.DataListBean> list) {
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
    public ElfSaidAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.elfsaid_item,parent,false);
        ElfSaidAdapter.MyViewHolder myViewHolder = new ElfSaidAdapter.MyViewHolder(view);
        return myViewHolder;
        //     return view;
    }

    @Override
    public void onBindViewHolder(ElfSaidAdapter.MyViewHolder holder, final int position) {


        Glide.with(context).load("http://211.157.162.2/"+listBeen.get(position).getTitleImg())
                .into(holder.im_elfsaid);

        holder.tv_title.setText(listBeen.get(position).getTitle());
        holder.tv_colletc.setText(listBeen.get(position).getHot()+"");
        holder.tv_watch.setText(listBeen.get(position).getCommentNum()+"");
        holder.tv_content.setText(listBeen.get(position).getContent());

    }
    @Override
    public int getItemCount() {
        return listBeen.size();
    }
    public class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView im_elfsaid;
        TextView tv_title,tv_colletc,tv_content,tv_watch;

        public MyViewHolder(final View itemView) {
            super(itemView);
            im_elfsaid = itemView.findViewById(R.id.im_elfsaid);
            tv_title = itemView.findViewById(R.id.tv_title);
            tv_colletc = itemView.findViewById(R.id.elfsaid_collect);
            tv_content = itemView.findViewById(R.id.elfsaid_content);
            tv_watch = itemView.findViewById(R.id.elfsaid_watch);

        }
    }
}
