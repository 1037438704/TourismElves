package com.tourismelves.view.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tourismelves.R;
import com.tourismelves.utils.glide.GlideCatchUtil;
import com.tourismelves.view.activity.base.StateBaseActivity;

public class SetupActivity extends StateBaseActivity {


    TextView tv_catch;
    LinearLayout ll_personal_qchc;

    @Override
    protected void setContentLayout() {
        setContentView(R.layout.activity_setup);
        initView();
    }
    private void initView() {

    }


    @Override
    protected void initControls() {
      //  setStatusBar(R.id.select_city_status);
        showStateLayout(1);
        setBaseTitle("设置");
        showStateRightView(2);
        setStatusUi();
    }

    @Override
    protected void obtainData() {

        ll_personal_qchc = findViewById(R.id.setup_qchc);
        tv_catch = findViewById(R.id.personal_qchc);


        ll_personal_qchc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog alertDialog = new AlertDialog.Builder(SetupActivity.this).setTitle("提示").setMessage("是否需要删除？")
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        }).setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                GlideCatchUtil.clearImageDiskCache(SetupActivity.this);
                                tv_catch.setText("0.0KB");

                            }
                        }).create();
                alertDialog.show();
            }
        });

    }

    @Override
    protected void initEvent() {

    }
}
