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
import com.tourismelves.model.bean.WaitpayBean;
import com.tourismelves.utils.pinyin.ApiManager;
import com.tourismelves.utils.system.SPUtils;
import com.tourismelves.view.adapter.WaitpayAdapter;
import com.tourismelves.view.fragment.base.BaseFragment;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class WaitPayFragment extends BaseFragment {

    RecyclerView recyclerView;
    WaitpayBean waitpayBean;
    List<WaitpayBean.DataListBean> listBeen;
    List list = new ArrayList();

    @Override
    protected int setContentLayout() {
        return R.layout.fragment_wait_pay;
    }

    @Override
    protected void initView(View view) {

        recyclerView = view.findViewById(R.id.waitpay_recy);

    }

    @Override
    protected void obtainData() {
        OkHttpUtils.get().url(ApiManager.ALL_URL+"lyjl/app/orderList.do?userId=2")
              //  .addParams("userId", SPUtils.getInstance(getActivity()).getString("putInt"))
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Request request, Exception e) {

                    }

                    @Override
                    public void onResponse(String response) {

                        Log.e("订单详情",response);
                        Gson gson = new Gson();
                        waitpayBean = gson.fromJson(response,WaitpayBean.class);
                        listBeen = waitpayBean.getDataList();
                        for (int i = 0; i<listBeen.size();i++)
                        {
                           // list.add(listBeen.get(i).getOrderStatus()==1&&listBeen.get(i).getPayStatus()==0);

                          //  Log.e("订单列表",listBeen.get(i).getOrderStatus()==1&&listBeen.get(i).getPayStatus()==0)
                           // listBeen.addAll(list);
                            Log.e("订单list",list+"");
//
                            WaitpayAdapter waitpayAdapter = new WaitpayAdapter(getActivity(),listBeen);
                            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                            recyclerView.setAdapter(waitpayAdapter);
                        }











                    }
                });
    }

    @Override
    protected void initEvent() {

    }

}
