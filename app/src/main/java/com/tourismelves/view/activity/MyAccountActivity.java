package com.tourismelves.view.activity;

import android.Manifest;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.FileProvider;
import android.util.Base64;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;

import com.tourismelves.R;
import com.tourismelves.model.bean.UserBean;
import com.tourismelves.model.net.OkHttpUtils;
import com.tourismelves.utils.PhotoUtils;
import com.tourismelves.utils.common.ToastUtil;
import com.tourismelves.utils.log.LogUtil;
import com.tourismelves.utils.system.SPUtils;

import com.tourismelves.view.activity.base.StateBaseActivity;
import com.tourismelves.view.widget.loadlayout.State;


import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.List;

import static com.tourismelves.app.constant.UrlConstants.userinfo;
import static com.tourismelves.utils.system.PermissionUtil.getDeniedPermissions;

public class MyAccountActivity extends StateBaseActivity {


    TextView tv_name, tv_phone, tv_xb, top_name;
    LinearLayout mLinear_sex, mLinear_pass, mLinear_phone, mLinear_head;
    UserBean userBean;
    Dialog dialog;


    @Override
    protected void setContentLayout() {
        setContentView(R.layout.activity_my_account);

        initView();
    }

    private void initView() {
        ActivityCompat.requestPermissions(MyAccountActivity.this, new String[]{Manifest.permission.CAMERA}, CODE_CAMERA_REQUEST);
        outputUri = Uri.fromFile(new File(getCacheDir(), "cropImage.jpeg"));
        mLinear_head = findViewById(R.id.changeHeadLayout);
        top_name = findViewById(R.id.account_topname);
        mLinear_phone = findViewById(R.id.llaccount_phone);
        mLinear_pass = findViewById(R.id.llaccount_pass);
        mLinear_sex = findViewById(R.id.llaccount_xb);
        tv_name = findViewById(R.id.account_name);
        tv_phone = findViewById(R.id.account_phone);
        tv_xb = findViewById(R.id.account_xb);
        //修改头像
        mLinear_head.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog = new Dialog(MyAccountActivity.this, R.style.ActionSheetDialogStyle);
                View inflate = LayoutInflater.from(MyAccountActivity.this).inflate(R.layout.changehead_item, null);
                //将布局设置给Dialog
                dialog.setContentView(inflate);

                inflate.findViewById(R.id.xiangce)
                        .setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                openPhoto();
                            }
                        });
                inflate.findViewById(R.id.paizhao)
                        .setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                takePhoto();
                            }
                        });

                //获取当前Activity所在的窗体
                Window dialogWindow = dialog.getWindow();
                //设置Dialog从窗体底部弹出
                dialogWindow.setGravity(Gravity.BOTTOM);
                //获得窗体的属性
                WindowManager.LayoutParams lp = dialogWindow.getAttributes();
                lp.width = MyAccountActivity.this.getResources().getDisplayMetrics().widthPixels; // 宽度
                dialogWindow.setGravity(Gravity.BOTTOM);
                //       将属性设置给窗体
                dialogWindow.setAttributes(lp);
                dialog.show();//显示对话框
            }
        });
        //修改昵称
        tv_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MyAccountActivity.this, SetNameActivity.class);
                startActivity(intent);
            }
        });
        //修改性别
        mLinear_sex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MyAccountActivity.this, SexActivity.class);
                intent.putExtra("type", userBean.getDataList().get(0).getGender());
                startActivity(intent);
            }
        });
        //修改密码
        mLinear_pass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MyAccountActivity.this, ChangePassActivity.class);

                startActivity(intent);
            }
        });
        //修改手机号
        mLinear_phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MyAccountActivity.this, ChangePhoneActivity.class);

                startActivity(intent);
            }
        });

    }

    @Override
    protected void initControls() {
        // setStatusBar(R.id.select_city_status);
        showStateLayout(1);
        setBaseTitle("我的账户");
        showStateRightView(2);
        setStatusUi();
    }

    @Override
    protected void obtainData() {
        getLoadLayout().setLayoutState(State.LOADING);
        // UserInfo();
    }

    @Override
    protected void initEvent() {

    }

    @Override
    public void onResume() {
        super.onResume();
        UserInfo();
    }

    //获取个人信息
    private void UserInfo() {
        String userid = SPUtils.getInstance(MyAccountActivity.this).getString("putInt");  //取出这个int值
        Log.e("用户id", userid);
        OkHttpUtils.get(String.format(userinfo, userid + ""),
                new OkHttpUtils.ResultCallback<String>() {
                    @Override
                    public void onSuccess(String response) {

                        Log.e("USER", response);
                        Gson gson = new Gson();

                        userBean = gson.fromJson(response, UserBean.class);
                        top_name.setText(userBean.getDataList().get(0).getNickName());
                        tv_name.setText(userBean.getDataList().get(0).getNickName());
                        tv_phone.setText(userBean.getDataList().get(0).getMobile());
                        tv_xb.setText(userBean.getDataList().get(0).getGender());

                        getLoadLayout().setLayoutState(State.SUCCESS);

                    }

                    @Override
                    public void onFailure(Exception e) {
                        getLoadLayout().setLayoutState(State.FAILED);
                    }
                }
        );
    }
//
//    @Override
//    protected void authorizationSuccess(int type) {
//        if (photo == 0) {
//            openPhoto();
//        } else {
//            takePhoto();
//        }
//    }

    private int photo = 0;

    private File fileUri;    //拍照的文件
    private Uri imageUri;  //拍照图片的uri
    private Uri outputUri;  // 生成裁剪之后的文件

    private final int CODE_CAMERA_REQUEST = 0xa1;    //相机

    /**
     * 拍照
     */
    public void takePhoto() {
//        photo = 1;
//        List<String> deniedPermissions = getDeniedPermissions(this, cameras);
//        if (deniedPermissions != null) {
//            requestPermissions(camera, cameras);
//        } else {
//            List<String> permissions = getDeniedPermissions(this, externals);
//            if (permissions != null) {
//                requestPermissions(external, externals);
//            } else {
//                fileUri = new File(Environment.getExternalStorageDirectory().getPath(), System.currentTimeMillis() + ".jpg");
//                imageUri = Uri.fromFile(fileUri);
//                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
//                    //通过FileProvider创建一个content类型的Uri
//                    imageUri = FileProvider.getUriForFile(this, getApplicationContext().getPackageName() + ".FileProvider", fileUri);
//                PhotoUtils.takePicture(this, imageUri, CODE_CAMERA_REQUEST);
//            }
//        }
    }

    private final int CODE_GALLERY_REQUEST = 0xa0;   //相册


    /**
     * 知乎开源相册选择器
     */
    public void openPhoto() {
//        photo = 0;
//        List<String> permissions = getDeniedPermissions(this, externals);
//        if (permissions != null) {
//            requestPermissions(external, externals);
//        } else {
//            Matisse.from(this)
//                    .choose(MimeType.of(MimeType.JPEG, MimeType.PNG)) // 选择 mime 的类型
//                    .theme(R.style.Matisse_Dracula)//Zhihu（亮蓝色主题） Dracula（黑色主题）
//                    .countable(true)
//                    .maxSelectable(1) // 图片选择的最多数量
//                    .spanCount(3)//网格的规格
//                    .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED)//图像选择和预览活动所需的方向。
//                    .thumbnailScale(0.85f) // 缩略图的比例
//                    .imageEngine(new GlideEngine()) // 使用的图片加载引擎
//                    .forResult(CODE_GALLERY_REQUEST); // 设置作为标记的请求码
//        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (resultCode == RESULT_OK) {
//            switch (requestCode) {
//                case CODE_GALLERY_REQUEST://访问相册完成回调
//                    Uri newUri = Matisse.obtainResult(data).get(0);
//                    startCropActivity(newUri);
//                    break;
//                case CODE_CAMERA_REQUEST://拍照完成回调
//                    startCropActivity(imageUri);
//                    break;
//                case UCrop.REQUEST_CROP:    // 裁剪图片结果
//                    handleCropResult(data);
//                    break;
//                case UCrop.RESULT_ERROR:    // 裁剪图片错误
//                    handleCropError(data);
//                    break;
//            }
//        }
    }

    /**
     * 裁剪图片方法实现
     *
     * @param uri
     */
//    public void startCropActivity(Uri uri) {
//        UCrop.of(uri, outputUri)
//                .withAspectRatio(1, 1)
//                .withMaxResultSize(512, 512)
//                .withTargetActivity(CropActivity.class)
//                .start(this);
//    }

//    /**
//     * 处理剪切失败的返回值
//     */
//    private void handleCropError(Intent result) {
//        if (fileUri != null && fileUri.exists() && fileUri.isFile()) {
//            fileUri.delete();
//        }
//        final Throwable cropError = UCrop.getError(result);
//        if (cropError != null) {
//            LogUtil.i(cropError);
//            ToastUtil.show(cropError.getMessage());
//        } else {
//            ToastUtil.show("无法剪切选择图片");
//        }
//    }

    /**
     * 处理剪切成功的返回值
     */
    private void handleCropResult(Intent result) {
        if (fileUri != null && fileUri.exists() && fileUri.isFile()) {
            fileUri.delete();
        }
     //   final Uri resultUri = UCrop.getOutput(result);
//        if (null != resultUri) {
//            LogUtil.i(resultUri);
//            String base64 = bitmapToBase64(getSmallBitmap(resultUri.getPath()));
//            List<String> url = new ArrayList<>();
//            url.add(resultUri.getPath());
//            List<ParamString> paramObjects = new ArrayList<>();
//            paramObjects.add(new ParamString("image", base64));
//            paramObjects.add(new ParamString("token", "1f4b7545-22af-4f68-80ad-76d380c19b36"));
//            paramObjects.add(new ParamString("userId", SPUtils.getInstance(getContext()).getString("putInt")));
//            OkHttpUtils.postFile(updateUserInfo,
//                    new OkHttpUtils.ResultCallback<String>() {
//                        @Override
//                        public void onSuccess(String response) {
//                            JSONObject object = JSON.parseObject(response);
//                            Integer code = object.getInteger("code");
//                            if (code == 200) {
//
//                            } else {
//                                ToastUtil.show(object.getString("message"));
//                            }
//                        }
//
//                        @Override
//                        public void onFailure(Exception e) {
//                            ToastUtil.show(R.string.no_found_network);
//                        }
//                    }, new ProgressListener() {
//                        @Override
//                        public void onProgress(long currentBytes, long contentLength, boolean done) {
//                            LogUtil.i(currentBytes);
//                        }
//                    }, paramObjects, "image", url);
//        } else {
//            ToastUtil.show("无法剪切选择图片");
//        }
    }

    // 根据路径获得图片并压缩，返回bitmap用于显示
    public static Bitmap getSmallBitmap(String filePath) {
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(filePath, options);

        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, 512, 512);

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;

        return BitmapFactory.decodeFile(filePath, options);
    }

    //计算图片的缩放值
    public static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {
            final int heightRatio = Math.round((float) height / (float) reqHeight);
            final int widthRatio = Math.round((float) width / (float) reqWidth);
            inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;
        }
        return inSampleSize;
    }

    //将bitmap转成Base64字符串
    public static String bitmapToBase64(Bitmap bitmap) {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 90, bos);
        byte[] bytes = bos.toByteArray();
        return Base64.encodeToString(bytes, Base64.DEFAULT);
    }
}
