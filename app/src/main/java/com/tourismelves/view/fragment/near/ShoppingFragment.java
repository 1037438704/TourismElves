package com.tourismelves.view.fragment.near;

import android.view.View;

import com.tourismelves.R;
import com.tourismelves.view.fragment.base.BaseFragment;
import com.tourismelves.view.widget.loadlayout.State;

/**
 * 购物
 */

public class ShoppingFragment extends BaseFragment {
    @Override
    protected int setContentLayout() {
        return R.layout.fragment_shopping;
    }

    @Override
    protected void initView(View view) {

    }

    @Override
    protected void obtainData() {
getLoadLayout().setLayoutState(State.NO_DATA);
    }

    @Override
    protected void initEvent() {

    }
}
