package com.tourismelves.view.activity;

import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.tourismelves.R;
import com.tourismelves.model.bean.SettlementBean;
import com.tourismelves.view.activity.base.StateBaseActivity;
import com.tourismelves.view.adapter.SettlementAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

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

    @Override
    protected void setContentLayout() {
        setContentView(R.layout.activity_settlement);
    }

    @Override
    protected void initControls() {
        showStateLayout(1);
        setBaseTitle("结算");
        showStateRightView(2);

        settlementRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        settlementAdapter = new SettlementAdapter(getContext(), new ArrayList<SettlementBean>());
        settlementRecycler.setAdapter(settlementAdapter);


    }

    @Override
    protected void obtainData() {
        SettlementBean settlementBean = (SettlementBean) getIntent().getSerializableExtra("SettlementBean");
        List<SettlementBean> settlementBeanList = new ArrayList<>();
        settlementBeanList.add(settlementBean);
        settlementAdapter.replaceData(settlementBeanList);

        settlementMoney.setText("¥" + settlementBean.getMoney());
        settlementPreferential.setText("已优惠¥" + (settlementBean.getMoney() - settlementBean.getOriginalMoney()));
    }

    @Override
    protected void initEvent() {

    }

    @OnClick(R.id.settlement_pay)
    public void onViewClicked() {
    }
}
