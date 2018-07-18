package com.tourismelves.utils.common;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;


/**
 * 自定义toast
 */
public class ToastUtil {
    private static Toast mToast;
    private static Context mContext = null;
    private static Handler handler;

    public static void init(Context context) {
        mContext = context;
        handler = new Handler(mContext.getMainLooper());
    }

    /**
     * 显示Toast,页面中重复Toast不会重复实例化Toast对象
     * 2000ms
     *
     * @param charSequence 字符串
     */
    public static void show(final CharSequence charSequence) {
        if (Looper.myLooper() != Looper.getMainLooper()) {
            handler.post(new Runnable() {
                @Override
                public void run() {
                    // 在这里执行你要想的操作 比如直接在这里更新ui或者调用回调在 在回调中更新ui
                    if (mToast == null) {
                        mToast = Toast.makeText(mContext, charSequence, Toast.LENGTH_SHORT);
                    } else {
                        mToast.setText(charSequence);
                        mToast.setDuration(Toast.LENGTH_SHORT);
                    }
//        mToast.getView().setBackgroundColor(mContext.getResources().getColor(R.color.theme_orange));

                    mToast.show();
                }
            });

        } else {
            if (mToast == null) {
                mToast = Toast.makeText(mContext, charSequence, Toast.LENGTH_SHORT);
            } else {
                mToast.setText(charSequence);
                mToast.setDuration(Toast.LENGTH_SHORT);
            }
//        mToast.getView().setBackgroundColor(mContext.getResources().getColor(R.color.theme_orange));

            mToast.show();
        }
    }

    /**
     * 显示Toast,页面中重复Toast不会重复实例化Toast对象
     * 3500ms
     *
     * @param charSequence 字符串
     */
    @SuppressLint("ShowToast")
    public static void showLong(final CharSequence charSequence) {
        if (Looper.myLooper() != Looper.getMainLooper()) {
            handler.post(new Runnable() {
                @Override
                public void run() {
                    // 在这里执行你要想的操作 比如直接在这里更新ui或者调用回调在 在回调中更新ui
                    if (mToast == null) {
                        mToast = Toast.makeText(mContext, charSequence, Toast.LENGTH_LONG);
                    } else {
                        mToast.setText(charSequence);
                        mToast.setDuration(Toast.LENGTH_LONG);
                    }
//        mToast.getView().setBackgroundColor(mContext.getResources().getColor(R.color.theme_orange));

                    mToast.show();
                }
            });

        } else {
            if (mToast == null) {
                mToast = Toast.makeText(mContext, charSequence, Toast.LENGTH_LONG);
            } else {
                mToast.setText(charSequence);
                mToast.setDuration(Toast.LENGTH_LONG);
            }
//        mToast.getView().setBackgroundColor(mContext.getResources().getColor(R.color.theme_orange));

            mToast.show();
        }
    }

    /**
     * 显示Toast,页面中重复Toast不会重复实例化Toast对象
     * 2000ms
     *
     * @param resId String资源ID
     */
    @SuppressLint("ShowToast")
    public static void show(final int resId) {

        if (Looper.myLooper() != Looper.getMainLooper()) {
            handler.post(new Runnable() {
                @Override
                public void run() {
                    // 在这里执行你要想的操作 比如直接在这里更新ui或者调用回调在 在回调中更新ui
                    try {
                        if (mToast == null) {
                            mToast = Toast.makeText(mContext, resId, Toast.LENGTH_SHORT);
                        } else {
                            mToast.setText(resId);
                            mToast.setDuration(Toast.LENGTH_SHORT);
                        }
                    } catch (Exception e) {
                        if (mToast == null) {
                            mToast = Toast.makeText(mContext, resId + "", Toast.LENGTH_SHORT);
                        } else {
                            mToast.setText(resId + "");
                            mToast.setDuration(Toast.LENGTH_SHORT);
                        }
                    }
                    mToast.show();
                }
            });

        } else {
            try {
                if (mToast == null) {
                    mToast = Toast.makeText(mContext, resId, Toast.LENGTH_SHORT);
                } else {
                    mToast.setText(resId);
                    mToast.setDuration(Toast.LENGTH_SHORT);
                }
            } catch (Exception e) {
                if (mToast == null) {
                    mToast = Toast.makeText(mContext, resId + "", Toast.LENGTH_SHORT);
                } else {
                    mToast.setText(resId + "");
                    mToast.setDuration(Toast.LENGTH_SHORT);
                }
            }

            mToast.show();
        }
    }

    /**
     * 显示Toast,页面中重复Toast不会重复实例化Toast对象
     * 3500ms
     *
     * @param resId String资源ID
     */
    @SuppressLint("ShowToast")
    public static void showLong(final int resId) {

        if (Looper.myLooper() != Looper.getMainLooper()) {
            handler.post(new Runnable() {
                @Override
                public void run() {
                    // 在这里执行你要想的操作 比如直接在这里更新ui或者调用回调在 在回调中更新ui
                    try {
                        if (mToast == null) {
                            mToast = Toast.makeText(mContext, resId, Toast.LENGTH_LONG);
                        } else {
                            mToast.setText(resId);
                            mToast.setDuration(Toast.LENGTH_LONG);
                        }
                    } catch (Exception e) {
                        if (mToast == null) {
                            mToast = Toast.makeText(mContext, resId, Toast.LENGTH_LONG);
                        } else {
                            mToast.setText(resId);
                            mToast.setDuration(Toast.LENGTH_LONG);
                        }
                    }

                    mToast.show();
                }
            });

        } else {
            try {
                if (mToast == null) {
                    mToast = Toast.makeText(mContext, resId, Toast.LENGTH_LONG);
                } else {
                    mToast.setText(resId);
                    mToast.setDuration(Toast.LENGTH_LONG);
                }
            } catch (Exception e) {
                if (mToast == null) {
                    mToast = Toast.makeText(mContext, resId, Toast.LENGTH_LONG);
                } else {
                    mToast.setText(resId);
                    mToast.setDuration(Toast.LENGTH_LONG);
                }
            }

            mToast.show();
        }
    }

    /**
     * 取消Toast显示
     */
    public static void cancelToast() {
        if (Looper.myLooper() != Looper.getMainLooper()) {
            handler.post(new Runnable() {
                @Override
                public void run() {
                    // 在这里执行你要想的操作 比如直接在这里更新ui或者调用回调在 在回调中更新ui
                    if (mToast != null) {
                        mToast.cancel();
                    }
                }
            });

        } else {
            if (mToast != null) {
                mToast.cancel();
            }
        }

    }

}
