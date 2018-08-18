package com.tourismelves.view.activity;

import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tourismelves.R;
import com.tourismelves.model.net.OkHttpUtils;
import com.tourismelves.utils.common.ToastUtil;
import com.tourismelves.view.activity.base.StateBaseActivity;
import com.tourismelves.view.widget.flowlayout.FlowLayout;
import com.tourismelves.view.widget.flowlayout.TagAdapter;
import com.tourismelves.view.widget.flowlayout.TagFlowLayout;
import com.tourismelves.view.widget.loadlayout.State;

import butterknife.BindView;
import butterknife.OnClick;

import static com.tourismelves.app.constant.UrlConstants.searchOrganizationOrArticle;

/**
 * 搜索
 */

public class SearchActivity extends StateBaseActivity {
    @BindView(R.id.search_edit)
    AppCompatEditText searchEdit;
    @BindView(R.id.search_hot)
    TagFlowLayout searchHot;
    @BindView(R.id.search_hot_layout)
    LinearLayout searchHotLayout;
    @BindView(R.id.search_recycler)
    RecyclerView searchRecycler;

    private String[] mVals = new String[]
            {"故宫博物院", "恭王府", "景山", "故宫博物院", "景山", "故宫博物院"};

    @Override
    protected void setContentLayout() {
        setContentView(R.layout.activity_search);
    }

    @Override
    protected void initControls() {
        setStatusBar(R.id.search_status);
        setStatusUi();
        searchRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    @Override
    protected void obtainData() {
        searchHot.setAdapter(new TagAdapter<String>(mVals) {
            @Override
            public View getView(FlowLayout parent, int position, String s) {
                TextView tv = (TextView) LayoutInflater.from(getContext()).inflate(R.layout.item_hot_textview, searchHot, false);
                tv.setText(s);
                return tv;
            }
        });

    }

    @Override
    protected void initEvent() {
        searchHot.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
            @Override
            public boolean onTagClick(View view, int position, FlowLayout parent) {
                ToastUtil.show(mVals[position]);
                return true;
            }
        });
    }

    @OnClick({R.id.search_image, R.id.search_cancel})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.search_image:
                searchOrganizationOrArticle(searchEdit.getText().toString());
                break;
            case R.id.search_cancel:
                finish();
                break;
        }
    }


    /**
     * 请求景区
     */
    private void searchOrganizationOrArticle(String strAddress) {
        OkHttpUtils.get(String.format(searchOrganizationOrArticle, strAddress, 0, 0, 20, 1),
                new OkHttpUtils.ResultCallback<String>() {
                    @Override
                    public void onSuccess(final String response) {

                    }

                    @Override
                    public void onFailure(Exception e) {
                        getLoadLayout().setLayoutState(State.LOADING);
                        ToastUtil.show(R.string.no_found_network);
                    }
                });
    }

}
