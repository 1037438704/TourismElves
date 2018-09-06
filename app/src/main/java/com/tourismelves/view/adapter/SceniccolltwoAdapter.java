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
import com.tourismelves.model.bean.CollectBean;
import com.tourismelves.model.bean.ElfsaidBean;

import java.util.List;

/**
 * Created by fanhui on 2018/9/6.
 */

public class SceniccolltwoAdapter extends RecyclerView.Adapter<SceniccolltwoAdapter.MyViewHolder> {

    Context context;
    List<CollectBean.DataListBean.OrganizationBean.PhotoListBean> listBeen;

    public SceniccolltwoAdapter(Context context, List<CollectBean.DataListBean.OrganizationBean.PhotoListBean> listBeen) {
        this.context = context;
        this.listBeen = listBeen;
    }

    public void replaceData(@NonNull List<CollectBean.DataListBean.OrganizationBean.PhotoListBean> list) {
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
    public SceniccolltwoAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.scencolltwo_item,parent,false);
        SceniccolltwoAdapter.MyViewHolder myViewHolder = new SceniccolltwoAdapter.MyViewHolder(view);
        return myViewHolder;
        //     return view;
    }

    @Override
    public void onBindViewHolder(final SceniccolltwoAdapter.MyViewHolder holder, final int position) {




        if (onItemClickListener!=null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.OnItem(holder.itemView, position);
                }
            });

        }

        try {

            Glide.with(context).load("http://211.157.162.2/"+listBeen.get(position+1).getPhotoPath())
                    .into(holder.im_shop);

        }catch (Exception e){
            e.printStackTrace();
        }



    }

    @Override
    public int getItemCount() {
        if (listBeen.size()==1){
            return 0;
        }
        if (listBeen.size()>4){
            return 4;
        }
        return listBeen.size();
    }
    public class MyViewHolder extends RecyclerView.ViewHolder {





        ImageView im_shop;
        public MyViewHolder(final View itemView) {
            super(itemView);


            im_shop = itemView.findViewById(R.id.two_shop);
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
