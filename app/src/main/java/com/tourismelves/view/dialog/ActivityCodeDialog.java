package com.tourismelves.view.dialog;

import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.tourismelves.R;
import com.tourismelves.model.net.OkHttpUtils;
import com.tourismelves.model.net.ParamObject;
import com.tourismelves.utils.common.ToastUtil;
import com.tourismelves.utils.system.SPUtils;
import com.tourismelves.view.dialog.base.BaseDialog;

import java.util.ArrayList;
import java.util.List;

import static com.tourismelves.app.constant.UrlConstants.useActivationCode;

/**
 * 激活码
 */

public class ActivityCodeDialog extends BaseDialog {
    private AppCompatEditText editText;
    private AppCompatTextView textView;

    @Override
    public int getLayoutId() {
        return R.layout.activity_activity_code;
    }

    @Override
    public void initView(View view) {
        editText = view.findViewById(R.id.code_et);
        textView = view.findViewById(R.id.code_sure);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initEvent() {
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String string = editText.getText().toString();
                useActivationCode(string);
                dismiss();
            }
        });
    }

    private void useActivationCode(String code) {
        String userId = SPUtils.getInstance(getActivity()).getString("putInt");
        List<ParamObject> paramObjects = new ArrayList<>();
        paramObjects.add(new ParamObject("userId", userId));
        paramObjects.add(new ParamObject("activationCode", code));

        OkHttpUtils.postJson(useActivationCode, paramObjects,
                new OkHttpUtils.ResultCallback<String>() {
                    @Override
                    public void onSuccess(String response) {
                        JSONObject object = JSON.parseObject(response);
                        String message = object.getString("message");
                        ToastUtil.show(message);
                    }

                    @Override
                    public void onFailure(Exception e) {
                        ToastUtil.show(R.string.no_found_network);
                    }
                });
    }
}
