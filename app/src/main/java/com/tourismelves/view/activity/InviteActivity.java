package com.tourismelves.view.activity;

import android.widget.ImageView;

import com.squareup.okhttp.Request;
import com.tourismelves.R;
import com.tourismelves.utils.pinyin.ApiManager;
import com.tourismelves.utils.system.SPUtils;
import com.tourismelves.view.activity.base.StateBaseActivity;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

public class InviteActivity extends StateBaseActivity {
    ImageView im_shop;
    @Override
    protected void setContentLayout() {
        setContentView(R.layout.activity_invite);

    }

    @Override
    protected void initControls() {

        showStateLayout(1);
        setBaseTitle("邀请好友");
        showStateRightView(2);
    }

    @Override
    protected void obtainData() {
        im_shop = findViewById(R.id.invite_im);
    }

    @Override
    protected void initEvent() {
        OkHttpUtils.get().url(ApiManager.ALL_URL+"app/getShareImage.do")
                .addParams("userId", SPUtils.getInstance(InviteActivity.this).getString("putInt"))
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Request request, Exception e) {

                    }

                    @Override
                    public void onResponse(String response) {

                    }
                });

    }
}
