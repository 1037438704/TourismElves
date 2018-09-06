package com.tourismelves.view.activity.base;

import android.Manifest;
import android.content.Intent;
import android.os.Handler;

import com.tbruyelle.rxpermissions2.Permission;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.tourismelves.utils.common.ToastUtil;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

import static com.tourismelves.utils.system.PermissionUtil.getDeniedPermissions;

/**
 * 权限类
 */

public abstract class PermissionsActivity extends StateBaseActivity {
    // 相机
    public final int camera = 2;
    public final String[] cameras = {Manifest.permission.CAMERA};
    //存储
    public final int external = 3;
    public final String[] externals = {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE};
    //定位
    public final int location = 4;
    public final String[] locations = {Manifest.permission.ACCESS_COARSE_LOCATION};

    private final int MANAGER_CAMERA = 0xbee1;    //授权管理界面
    private final int MANAGER_CONTACT = 0xbee2;    //授权管理界面通讯录
    private final int MANAGER_EXTERNAL = 0xbee3;    //授权管理界面

    protected final int no = -1;

    /**
     * 请求权限
     */
    public void requestPermissions(final int type, String... permissions) {
        List<String> deniedPermissions = getDeniedPermissions(this, permissions);
        if (deniedPermissions != null && deniedPermissions.size() > 0) {
            permissions = deniedPermissions.toArray(new String[deniedPermissions.size()]);

            final int[] isRequestSuccess = {0};
            RxPermissions rxPermissions = new RxPermissions(this);
            rxPermissions.requestEach(permissions)
                    .subscribe(new Observer<Permission>() {
                        @Override
                        public void onSubscribe(Disposable d) {
                            // 开始检测
                        }

                        @Override
                        public void onNext(Permission permission) {
                            if (permission.granted) {
                                // 用户已经同意该权限
                                isRequestSuccess[0] = 1;
                            } else if (permission.shouldShowRequestPermissionRationale) {
                                // 用户拒绝了该权限，没有选中『不再询问』（Never ask again）,那么下次再次启动时，还会提示请求权限的对话框
                                ToastUtil.show("授权失败");
                                isRequestSuccess[0] = -1;
                            } else {
                                // 用户拒绝了该权限，并且选中『不再询问』，提醒用户手动打开权限
                                isRequestSuccess[0] = -2;
                            }
                        }

                        @Override
                        public void onError(Throwable e) {
                            //检测出错
                            isRequestSuccess[0] = 400;
                            ToastUtil.show("授权错误");
                        }

                        @Override
                        public void onComplete() {
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    //检测完毕
                                    if (isRequestSuccess[0] == 1) {
                                        authorizationSuccess(type);
                                    } else if (isRequestSuccess[0] == -2) {
                                        permissionManager(type);
                                    }
                                }
                            }, 100);
                        }
                    });
        } else {
            authorizationSuccess(type);
        }
    }

    /**
     * 显示取权限管理设置的提示
     */
    protected void permissionManager(int type) {
        if (type == camera) {
            ToastUtil.show("前往权限管理界面开启相机权限");
        } else if (type == external) {
            ToastUtil.show("是否前往权限管理界面开启存储权限");
        }
    }

    /**
     * 已授权
     */
    protected void authorizationSuccess(int type) {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
         if (requestCode == MANAGER_CAMERA) {
            detection(camera, cameras);
        } else if (requestCode == MANAGER_EXTERNAL) {
            detection(external, externals);
        }
    }

    /**
     * 从管理界面回来时，检测是否授权
     */
    private void detection(final int type, String... permissions) {
        List<String> deniedPermissions = getDeniedPermissions(this, permissions);
        if (deniedPermissions != null && deniedPermissions.size() > 0) {
            ToastUtil.show("权限授取失败");
        } else {
            ToastUtil.show("权限授取成功");
            authorizationSuccess(type);
        }
    }

}
