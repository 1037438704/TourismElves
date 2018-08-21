package com.tourismelves.view.activity;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.tourismelves.R;
import com.tourismelves.model.net.OkHttpUtils;
import com.tourismelves.utils.common.ToastUtil;
import com.tourismelves.utils.system.SPUtils;
import com.tourismelves.view.activity.base.StateBaseActivity;
import com.tourismelves.view.widget.loadlayout.State;

import static com.tourismelves.app.constant.UrlConstants.orderList;

/**
 * 已购
 */

public class AlreadyBoughtActivity extends StateBaseActivity {
    @Override
    protected void setContentLayout() {
        setContentView(R.layout.activity_already_bought);
    }

    @Override
    protected void initControls() {
        showStateLayout(1);
        setBaseTitle("已购");
        showStateRightView(2);

    }

    @Override
    protected void obtainData() {
        orderList();
    }

    @Override
    protected void initEvent() {

    }

    private void orderList() {
        String userId = SPUtils.getInstance(getContext()).getString("putInt");
        OkHttpUtils.get(String.format(orderList, userId, 1),
                new OkHttpUtils.ResultCallback<String>() {
                    @Override
                    public void onSuccess(String response) {
                        JSONObject object = JSON.parseObject(response);
                        //获取请求结果的code码
                        Integer code = object.getInteger("code");
                        if (code == 200) {
                            JSONArray dataList = object.getJSONArray("dataList");
                            int size = dataList.size();
                            if (size>0){

                            }else {
                                getLoadLayout().setLayoutState(State.NO_DATA);
                            }
                        } else {
                            ToastUtil.show(object.getString("message"));
                        }
                    }

                    @Override
                    public void onFailure(Exception e) {
                        ToastUtil.show(R.string.no_found_network);
                        getLoadLayout().setLayoutState(State.FAILED);
                    }
                });
    }
}
