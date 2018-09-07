package com.tourismelves.view.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.tourismelves.R;
import com.tourismelves.model.bean.CollectBean;
import com.tourismelves.model.bean.ElfsaidBean;

import java.util.List;

/**
 * Created by fanhui on 2018/9/4.
 */

public class SceniccollAdapter extends RecyclerView.Adapter<SceniccollAdapter.MyViewHolder> {

    Context context;
    List<CollectBean.DataListBean> listBeen;

    public SceniccollAdapter(Context context, List<CollectBean.DataListBean> listBeen) {
        this.context = context;
        this.listBeen = listBeen;
    }

    public void replaceData(@NonNull List<CollectBean.DataListBean> list) {
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
    public SceniccollAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.scencoll_item,parent,false);
        SceniccollAdapter.MyViewHolder myViewHolder = new SceniccollAdapter.MyViewHolder(view);
        return myViewHolder;
        //     return view;
    }

    @Override
    public void onBindViewHolder(final SceniccollAdapter.MyViewHolder holder, final int position) {




        if (onItemClickListener!=null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.OnItem(holder.itemView, position);
                }
            });

        }
        if (listBeen.get(position).getOrganization().getPhotoList()==null){
            holder.im_scen.setVisibility(View.GONE);
            return;
        }
        try {
            Glide.with(context).load("http://211.157.162.2/"+listBeen.get(position).getOrganization().getPhotoList().get(0).getPhotoPath())
                    .into(holder.im_scen);
           // holder.tv_hot.setText(listBeen.get(position).getOrganization().get);
            holder.tv_content.setText(listBeen.get(position).getOrganization().getDescription());
            holder.tv_title.setText(listBeen.get(position).getOrganization().getName());
            SceniccolltwoAdapter sceniccolltwoAdapter = new SceniccolltwoAdapter(context,listBeen.get(position).getOrganization().getPhotoList());
            holder.recyclerView.setLayoutManager(new GridLayoutManager(context,2));
            holder.recyclerView.setAdapter(sceniccolltwoAdapter);
        }catch (Exception e){
            e.printStackTrace();
        }



    }

    @Override
    public int getItemCount() {
        return listBeen.size();
    }
    public class MyViewHolder extends RecyclerView.ViewHolder {



        ImageView im_scen;
        TextView tv_title,tv_content,tv_hot;
        RecyclerView recyclerView;

        public MyViewHolder(final View itemView) {
            super(itemView);

            im_scen = itemView.findViewById(R.id.scencoll_im);
            tv_title =  itemView.findViewById(R.id.tv_title);
            tv_content = itemView.findViewById(R.id.elfsaid_content);
            tv_hot = itemView.findViewById(R.id.elfsaid_collect);
            recyclerView = itemView.findViewById(R.id.scencoll_recy);
        }
    }


    public interface OnItemClickListener{
        void OnItem(View view,int position);

    }
    private ElfSaidAdapter.OnItemClickListener onItemClickListener;
    public void setOnItemClickListener(ElfSaidAdapter.OnItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }
}

