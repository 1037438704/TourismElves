package com.tourismelves.view.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.JsonObject;
import com.squareup.okhttp.Request;
import com.tourismelves.R;
import com.tourismelves.model.bean.ElfsaidBean;
import com.tourismelves.utils.pinyin.ApiManager;
import com.tourismelves.utils.system.SPUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

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
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.elfsaid_item,parent,false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
        //     return view;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {




        if (onItemClickListener!=null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.OnItem(holder.itemView, position);
                }
            });

        }

        Glide.with(context).load("http://211.157.162.2/"+listBeen.get(position).getTitleImg())
                .into(holder.im_elfsaid);

        holder.tv_title.setText(listBeen.get(position).getTitle());
        holder.tv_colletc.setText(listBeen.get(position).getPraise()+"");
        holder.tv_watch.setText(listBeen.get(position).getHot()+"");
       // if ()




        String contents = listBeen.get(position).getContent();
        String str=contents.replaceAll("[\t\" `a-zA-z0-9`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……& amp;*（）——+|{}【】‘；：”“’。，、？|-]", "");

        Log.e("str",str);
        holder.tv_content.setText(str);
        holder.im_coll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OkHttpUtils.get().url(ApiManager.ALL_URL+"lyjl/app/praiseArticle.do")
                        .addParams("articleId",listBeen.get(position).getArticleId()+"")
                        .addParams("userId", SPUtils.getInstance(context).getString("putInt"))
                        .build()
                        .execute(new StringCallback() {
                            @Override
                            public void onError(Request request, Exception e) {

                            }

                            @Override
                            public void onResponse(String response) {


                                Log.e("精灵说点赞",response);
                                holder.im_coll.setImageResource(R.mipmap.dianzan);
                                Toast.makeText(context, "点赞成功", Toast.LENGTH_SHORT).show();


                            }
                        });
            }
        });
    }
//    public static String format(String s){
//        s = listBeen.get(position).getContent()
//        String str=s.replaceAll("[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……& amp;*（）——+|{}【】‘；：”“’。，、？|-]", "");
//        return str;
//    }
    @Override
    public int getItemCount() {
        return listBeen.size();
    }
    public class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView im_elfsaid;
        TextView tv_title,tv_colletc,tv_content,tv_watch;
        ImageView im_coll;

        public MyViewHolder(final View itemView) {
            super(itemView);
            im_elfsaid = itemView.findViewById(R.id.im_elfsaid);
            tv_title = itemView.findViewById(R.id.tv_title);
            tv_colletc = itemView.findViewById(R.id.elfsaid_collect);
            tv_content = itemView.findViewById(R.id.elfsaid_content);
            tv_watch = itemView.findViewById(R.id.elfsaid_watch);
            im_coll = itemView.findViewById(R.id.elfsaid_collm);

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
