package com.tourismelves.view.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.squareup.okhttp.Request;
import com.tourismelves.R;
import com.tourismelves.model.bean.CollectBean;
import com.tourismelves.utils.pinyin.ApiManager;
import com.tourismelves.utils.system.SPUtils;
import com.tourismelves.view.adapter.SceniccollAdapter;
import com.tourismelves.view.fragment.base.BaseFragment;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class SceniccollFragment extends BaseFragment {



    CollectBean collectBean;
    List<CollectBean.DataListBean> listBeen;
    RecyclerView recyclerView;
    SceniccollAdapter sceniccollAdapter;




    @Override
    protected int setContentLayout() {
        return R.layout.fragment_sceniccoll;
    }

    @Override
    protected void initView(View view) {

        recyclerView = view.findViewById(R.id.scenicc_recy);


    }

    @Override
    protected void obtainData() {

        OkHttpUtils.get().url(ApiManager.ALL_URL+"lyjl/app/getUserFavorite.do")
                .addParams("type",0+"")
                .addParams("userId", SPUtils.getInstance(getActivity()).getString("putInt"))
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Request request, Exception e) {

                    }

                    @Override
                    public void onResponse(String response) {
                        Log.e("我的收藏",response);
                        Gson gson = new Gson();
                        collectBean = gson.fromJson(response,CollectBean.class);
                        listBeen = collectBean.getDataList();
                       // listBeen.addAll(collectBean.getDataList());
                        sceniccollAdapter = new SceniccollAdapter(getActivity(),listBeen);
                        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                        recyclerView.setAdapter(sceniccollAdapter);
                        sceniccollAdapter.notifyDataSetChanged();
                    }
                });
    }

    @Override
    protected void initEvent() {

    }

}
