package com.tourismelves.view.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.tourismelves.R;
import com.tourismelves.view.activity.base.StateBaseActivity;
import com.tourismelves.view.adapter.InterpretationListAdapter;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * 讲解列表 详情
 */
public class InterpretationList2Activity extends StateBaseActivity {
    @BindView(R.id.interpretation_list2_recycler)
    RecyclerView interpretationList2Recycler;
    private InterpretationListAdapter interpretationListAdapter;

    @Override
    protected void setContentLayout() {
        setContentView(R.layout.activity_interpretation_list2);
    }

    @Override
    protected void initControls() {
        showStateLayout(1);
        setBaseTitle("讲解列表");
        setBaseRightImage(R.mipmap.xiazai);
        interpretationList2Recycler.setLayoutManager(new LinearLayoutManager(getContext()));
        interpretationListAdapter = new InterpretationListAdapter(getContext(), new ArrayList<String>());
        interpretationList2Recycler.setAdapter(interpretationListAdapter);
    }

    @Override
    protected void obtainData() {

    }

    @Override
    protected void initEvent() {
        setBaseRightListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

}
