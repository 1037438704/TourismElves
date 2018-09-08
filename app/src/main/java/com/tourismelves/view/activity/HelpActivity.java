package com.tourismelves.view.activity;

import android.Manifest;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;


import com.bumptech.glide.Glide;
import com.google.gson.JsonObject;
import com.squareup.okhttp.Request;
import com.tourismelves.R;


import com.tourismelves.utils.LQRPhotoSelectUtils;
import com.tourismelves.utils.pinyin.ApiManager;
import com.tourismelves.utils.system.SPUtils;
import com.tourismelves.view.activity.base.StateBaseActivity;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.List;

import kr.co.namee.permissiongen.PermissionFail;
import kr.co.namee.permissiongen.PermissionGen;
import kr.co.namee.permissiongen.PermissionSuccess;
import okhttp3.internal.platform.Platform;

public class HelpActivity extends StateBaseActivity {

    LinearLayout ll_dialog;
    Dialog dialog;
    View inflate;
    GridView gridView;

    int selectPostion;
    int deletePosition;
    RelativeLayout add_rela;
    ImageView mIvPic;
    LQRPhotoSelectUtils mLqrPhotoSelectUtils;
    EditText ed_content;
    Button bt_submit;

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
       // gridView = findViewById(R.id.gridView);
        ed_content = findViewById(R.id.ed_content);
        ll_dialog = findViewById(R.id.help_dialog);
        add_rela = findViewById(R.id.addBtnLayout);
        mIvPic = findViewById(R.id.help_im);
        bt_submit = findViewById(R.id.petname_save);

        // 1、创建LQRPhotoSelectUtils（一个Activity对应一个LQRPhotoSelectUtils）
        mLqrPhotoSelectUtils = new LQRPhotoSelectUtils(this, new LQRPhotoSelectUtils.PhotoSelectListener() {
            @Override
            public void onFinish(File outputFile, Uri outputUri) {
                // 4、当拍照或从图库选取图片成功后回调

                Glide.with(HelpActivity.this).load(outputUri).into(mIvPic);
            }
        }, false);//true裁剪，false不裁剪
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




        add_rela.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog = new Dialog(HelpActivity.this, R.style.ActionSheetDialogStyle);
                View inflate = LayoutInflater.from(HelpActivity.this).inflate(R.layout.changehead_item, null);
                //将布局设置给Dialog
                dialog.setContentView(inflate);

                inflate.findViewById(R.id.xiangce)
                        .setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                takePhoto();
                            }
                        });
                inflate.findViewById(R.id.paizhao)
                        .setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                openPhoto();
                            }
                        });

                //获取当前Activity所在的窗体
                Window dialogWindow = dialog.getWindow();
                //设置Dialog从窗体底部弹出
                dialogWindow.setGravity(Gravity.BOTTOM);
                //获得窗体的属性
                WindowManager.LayoutParams lp = dialogWindow.getAttributes();
                lp.width = HelpActivity.this.getResources().getDisplayMetrics().widthPixels; // 宽度
                dialogWindow.setGravity(Gravity.BOTTOM);
                //       将属性设置给窗体
                dialogWindow.setAttributes(lp);
                dialog.show();//显示对话框
            }
        });

        bt_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String content = ed_content.getText().toString();
                OkHttpUtils.get()
                        .url(ApiManager.ALL_URL+"lyjl/app/saveOpinion.do")
                        .addParams("userId", SPUtils.getInstance(HelpActivity.this).getString("putInt")) //取出这个int值)
                        .addParams("content",content+"")
                        .addParams("type",1+"")
                        .build()
                        .execute(new StringCallback() {
                            @Override
                            public void onError(Request request, Exception e) {

                            }

                            @Override
                            public void onResponse(String response) {

                                Log.e("意见反馈",response);
                                try {
                                    JSONObject jsonObject = new JSONObject(response);
                                    String mess = jsonObject.getString("message");
                                    Toast.makeText(HelpActivity.this, mess+"", Toast.LENGTH_SHORT).show();
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        });
            }
        });



    }

    private void takePhoto() {
      // 3、调用从图库选取图片方法
        PermissionGen.needPermission(HelpActivity.this,
                LQRPhotoSelectUtils.REQ_SELECT_PHOTO,
                new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE}
        );
    }

    private void openPhoto() {

        PermissionGen.with(HelpActivity.this)
                .addRequestCode(LQRPhotoSelectUtils.REQ_TAKE_PHOTO)
                .permissions(Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.CAMERA
                ).request();
    }

    @Override
    protected void initEvent() {

    }

    @PermissionSuccess(requestCode = LQRPhotoSelectUtils.REQ_TAKE_PHOTO)
    private void takePhoto1() {
        mLqrPhotoSelectUtils.takePhoto();
    }

    @PermissionSuccess(requestCode = LQRPhotoSelectUtils.REQ_SELECT_PHOTO)
    private void selectPhoto() {
        mLqrPhotoSelectUtils.selectPhoto();
    }

    @PermissionFail(requestCode = LQRPhotoSelectUtils.REQ_TAKE_PHOTO)
    private void showTip1() {
        //        Toast.makeText(getApplicationContext(), "不给我权限是吧，那就别玩了", Toast.LENGTH_SHORT).show();
        showDialog();
    }

    @PermissionFail(requestCode = LQRPhotoSelectUtils.REQ_SELECT_PHOTO)
    private void showTip2() {
        //        Toast.makeText(getApplicationContext(), "不给我权限是吧，那就别玩了", Toast.LENGTH_SHORT).show();
        showDialog();
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        PermissionGen.onRequestPermissionsResult(this, requestCode, permissions, grantResults);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // 2、在Activity中的onActivityResult()方法里与LQRPhotoSelectUtils关联
        mLqrPhotoSelectUtils.attachToActivityForResult(requestCode, resultCode, data);
    }

    public void showDialog() {
        //创建对话框创建器
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        //设置对话框显示小图标
        builder.setIcon(android.R.drawable.ic_dialog_alert);
        //设置标题
        builder.setTitle("权限申请");
        //设置正文
        builder.setMessage("在设置-应用-虎嗅-权限 中开启相机、存储权限，才能正常使用拍照或图片选择功能");

        //添加确定按钮点击事件
        builder.setPositiveButton("去设置", new DialogInterface.OnClickListener() {//点击完确定后，触发这个事件

            @Override
            public void onClick(DialogInterface dialog, int which) {
                //这里用来跳到手机设置页，方便用户开启权限
                Intent intent = new Intent(android.provider.Settings.ACTION_MANAGE_WRITE_SETTINGS);
                intent.setData(Uri.parse("package:" + HelpActivity.this.getPackageName()));
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });

        //添加取消按钮点击事件
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });

        //使用构建器创建出对话框对象
        AlertDialog dialog = builder.create();
        dialog.show();//显示对话框
    }

}
