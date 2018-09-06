package com.tourismelves.view.activity;

import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.tourismelves.R;
import com.tourismelves.view.activity.base.StateBaseActivity;

import okhttp3.internal.platform.Platform;

public class HelpActivity extends StateBaseActivity {

    LinearLayout ll_dialog;
    Dialog dialog;
    View inflate;

    @Override
    protected void setContentLayout() {
        setContentView(R.layout.activity_help);
    }

    @Override
    protected void initControls() {

        showStateLayout(1);
        setBaseTitle("帮助与反馈");
        showStateRightView(2);
        setStatusUi();
        setStatusUi();
        ll_dialog = findViewById(R.id.help_dialog);
    }

    @Override
    protected void obtainData() {

        ll_dialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog = new Dialog(HelpActivity.this,R.style.ActionSheetDialogStyle);
                inflate = LayoutInflater.from(HelpActivity.this).inflate(R.layout.help_item, null);
                dialog.setContentView(inflate);
                //获取当前Activity所在的窗体
                Window dialogWindow = dialog.getWindow();
                //设置Dialog从窗体底部弹出
                dialogWindow.setGravity( Gravity.BOTTOM);
                //获得窗体的属性
                WindowManager.LayoutParams lp = dialogWindow.getAttributes();
                lp.width = HelpActivity.this.getResources().getDisplayMetrics().widthPixels; // 宽度
                dialogWindow.setGravity(Gravity.BOTTOM);
                //       将属性设置给窗体
                dialogWindow.setAttributes(lp);
                dialog.show();//显示对话框
            }
        });
    }

    @Override
    protected void initEvent() {

    }


}
