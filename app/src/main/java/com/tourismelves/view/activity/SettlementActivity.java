package com.tourismelves.view.activity;

import android.content.Intent;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.tourismelves.R;
import com.tourismelves.model.bean.ShopListBean;
import com.tourismelves.model.net.OkHttpUtils;
import com.tourismelves.utils.common.ToastUtil;
import com.tourismelves.utils.system.SPUtils;
import com.tourismelves.view.activity.base.StateBaseActivity;
import com.tourismelves.view.adapter.SettlementAdapter;
import com.tourismelves.view.widget.loadlayout.State;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

import static com.tourismelves.app.constant.UrlConstants.cartList;
import static com.tourismelves.app.constant.UrlConstants.settlement;

/**
 * 结算
 */

public class SettlementActivity extends StateBaseActivity {
    @BindView(R.id.settlement_recycler)
    RecyclerView settlementRecycler;
    @BindView(R.id.settlement_money)
    AppCompatTextView settlementMoney;
    @BindView(R.id.settlement_preferential)
    AppCompatTextView settlementPreferential;
    private SettlementAdapter settlementAdapter;

    private double sumPreferential = 0;
    private double sumPrice = 0;
    private List<Integer> cartIds;

    @Override
    protected void setContentLayout() {
        setContentView(R.layout.activity_settlement);
    }

    @Override
    protected void initControls() {
        showStateLayout(1);
        setBaseTitle("结算");
        showStateRightView(0);
        setBaseRightTv("添加");

        cartIds = new ArrayList<>();
        settlementRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        settlementAdapter = new SettlementAdapter(getContext(), new ArrayList<ShopListBean>());
        settlementRecycler.setAdapter(settlementAdapter);


    }

    @Override
    protected void obtainData() {

    }

    @Override
    public void onResume() {
        super.onResume();
        getLoadLayout().setLayoutState(State.LOADING);
        cartList();
    }

    @Override
    protected void initEvent() {
        setBaseRightTvListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @OnClick(R.id.settlement_pay)
    public void onViewClicked() {
        settlement(cartIds.toString().substring(1, cartIds.toString().length() - 1));
    }

    /**
     * 购物车列表
     *
     * @return {"cartId":51,"userId":37,"orgId":68,"name":"故宫博物馆","image":"M00/00/18/052iAltNboOAWdG2AAQAZ_aEKmg926.jpg","price":10.0,"preferential":0.0}
     * {
     * "cartId": 51,
     * "userId": 37,
     * "orgId": 68,
     * "name": "故宫博物馆",
     * "image": "M00/00/18/052iAltNboOAWdG2AAQAZ_aEKmg926.jpg",
     * "price": 10.0,
     * "preferential": 0.0
     * }
     */
    private void cartList() {
        OkHttpUtils.get(cartList + "userId=" + SPUtils.getInstance(this).getString("putInt"),
                new OkHttpUtils.ResultCallback<String>() {
                    @Override
                    public void onSuccess(String response) {
                        JSONObject object = JSON.parseObject(response);
                        Integer code = object.getInteger("code");
                        if (code == 200) {
                            JSONArray dataList = object.getJSONArray("dataList");
                            int size = dataList.size();
                            for (int i = 0; i < size; i++) {
                                String string = dataList.getJSONObject(i).toString();
                                ShopListBean shopListBean = JSON.parseObject(string, ShopListBean.class);
                                settlementAdapter.insertItem(shopListBean);
                                sumPrice += (shopListBean.getPrice() - shopListBean.getPreferential());
                                sumPreferential += shopListBean.getPreferential();
                                cartIds.add(shopListBean.getCartId());
                            }
                            settlementMoney.setText("¥" + sumPrice);
                            settlementPreferential.setText("已优惠¥" + sumPreferential);

                            if (size > 0) {
                                getLoadLayout().setLayoutState(State.SUCCESS);
                            } else {
                                getLoadLayout().setLayoutState(State.NO_DATA);
                            }
                        } else {
                            ToastUtil.show(JSON.parseObject(response).getString("message"));
                        }

                    }

                    @Override
                    public void onFailure(Exception e) {
                        getLoadLayout().setLayoutState(State.FAILED);
                        ToastUtil.show(R.string.no_found_network);
                    }
                });
    }

    /**
     * 购物车结算
     *
     * @param cartIds 1,2
     * @return {"type":"json","code":200,"pk_id":"571"}
     * pk_id 商家生成的订单id
     */
    private void settlement(String cartIds) {
        OkHttpUtils.get(settlement + "userId=" + SPUtils.getInstance(this).getString("putInt") + "&cartIds=" + cartIds,
                new OkHttpUtils.ResultCallback<String>() {
                    @Override
                    public void onSuccess(String response) {
                        JSONObject object = JSON.parseObject(response);
                        Integer code = object.getInteger("code");
                        if (code == 200) {
                            Intent intent = new Intent(getContext(), PayActivity.class);
                            intent.putExtra("pk_id", object.getString("pk_id"));
                            startActivity(intent);
                        } else {
                            ToastUtil.show(JSON.parseObject(response).getString("message"));
                        }

                    }

                    @Override
                    public void onFailure(Exception e) {
                        ToastUtil.show(R.string.no_found_network);
                    }
                });
    }

}
