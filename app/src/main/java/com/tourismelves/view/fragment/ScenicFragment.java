package com.tourismelves.view.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.tourismelves.R;
import com.tourismelves.model.bean.ScenicBean;
import com.tourismelves.model.net.OkHttpUtils;
import com.tourismelves.view.adapter.ScenicAdaptermain;
import com.tourismelves.view.fragment.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ScenicFragment extends BaseFragment {

    RecyclerView recyclerView;
    ScenicBean scenicBean;
    List<ScenicBean.DataListBean> listBeen;
//    ScenicAdapter scenicAdapter;
    ScenicAdaptermain scenicAdaptermain;
    RecyclerView.LayoutManager mLayoutManager;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_scenic, container, false);
        initView(view);
        return view;
    }

    @Override
    protected int setContentLayout() {

      return   R.layout.fragment_scenic;
    }

    @Override
    protected void initView(View view) {
        listBeen = new ArrayList<>();
        recyclerView = view.findViewById(R.id.scenic_recy);

    }



    @Override
    protected void obtainData() {

        ScenicInfo();
    }



    @Override
    protected void initEvent() {

    }


    private void ScenicInfo() {

        OkHttpUtils.get(String.format("http://211.157.162.2/lyjl/app/getUserHistory.do?userId=2"),
                new OkHttpUtils.ResultCallback<String>() {
                    @Override
                    public void onSuccess(String response) {
                        Log.e("景区",response);
                        Gson gson = new Gson();
                        scenicBean = gson.fromJson(response,ScenicBean.class);
                        listBeen.addAll(scenicBean.getDataList());
//                        //使用瀑布流布局,第一个参数 spanCount 列数,第二个参数 orentation 排列方向
//                        StaggeredGridLayoutManager recyclerViewLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
//                        //线性布局Manager
//                        // LinearLayoutManager recyclerViewLayoutManager = new LinearLayoutManager(getActivity());
//                        //  recyclerViewLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
//                        // 网络布局Manager
//                        //  GridLayoutManager recyclerViewLayoutManager = new GridLayoutManager(getActivity(), 3);
//                        // 给recyclerView设置LayoutManager
//                        recyclerView.setLayoutManager(recyclerViewLayoutManager);
//                      //  scenicAdapter = new ScenicAdapter(R.layout.scenic_item,listBeen);
                        mLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
                        recyclerView.setLayoutManager(mLayoutManager);
                        scenicAdaptermain = new ScenicAdaptermain(getActivity(),listBeen);
                        recyclerView.setAdapter(scenicAdaptermain);




                    }

                    @Override
                    public void onFailure(Exception e) {



                    }
                });

    }
}
