package com.tourismelves.view.fragment;


import android.content.Intent;
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
import com.tourismelves.view.activity.PayActivity;
import com.tourismelves.view.adapter.ElfSaidAdapter;
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
    private List<WaitpayBean.DataListBean> list=new ArrayList<>();

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
        OkHttpUtils.get().url(ApiManager.ALL_URL+"lyjl/app/orderList.do")
                .addParams("userId", SPUtils.getInstance(getActivity()).getString("putInt"))
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Request request, Exception e) {

                    }

                    @Override
                    public void onResponse(String response) {

                        Log.e("订单详情1",response);
                        Gson gson = new Gson();
                        waitpayBean = gson.fromJson(response,WaitpayBean.class);
                        listBeen = waitpayBean.getDataList();



                        for (int i = 0; i<listBeen.size();i++)
                        {

                            if (listBeen.get(i).getOrderStatus()==2||listBeen.get(i).getOrderStatus()==0&&listBeen.get(i).getPayStatus()==0||listBeen.get(i).getPayStatus()==3){
                                list.add(listBeen.get(i));
                            }



                            Log.e("订单liebiao1",list+"");
//
                            WaitpayAdapter waitpayAdapter = new WaitpayAdapter(getActivity(),list);
                            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                            recyclerView.setAdapter(waitpayAdapter);
                            waitpayAdapter.setOnItemClickListener(new ElfSaidAdapter.OnItemClickListener() {
                                @Override
                                public void OnItem(View view, int position) {
                                    Intent intent = new Intent(getActivity(), PayActivity.class);
                                    intent.putExtra("pk_id",list.get(position).getOrderId());
                                    intent.putExtra("sumPrice",list.get(position).getGoodsAmount());
//                                    pk_id = getIntent().getStringExtra("pk_id");
//                                    sumPrice = getIntent().getDoubleExtra("sumPrice", 0.00);
                                    startActivity(intent);
                                }
                            });

                        }
                    }
                });
    }

    @Override
    protected void initEvent() {

    }

}
