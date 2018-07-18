package com.tourismelves.view.dialog.base;

import android.app.Dialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.tourismelves.R;
import com.tourismelves.utils.log.LogUtil;
import com.tourismelves.utils.system.PreferenceUtil;
import com.tourismelves.utils.system.ResolutionUtil;

import static com.tourismelves.app.constant.BaseConstants.APP_SHARE_USER;


public abstract class BaseDialog extends DialogFragment {
    protected SharedPreferences userPreference;
    private OnDismissListener onDismissListener;
    private OnShowListener onShowListener;

    public boolean isDestroy = false;

    public void setOnShowListener(OnShowListener onShowListener) {
        this.onShowListener = onShowListener;
    }

    public void setOnDismissListener(OnDismissListener onDismissListener) {
        this.onDismissListener = onDismissListener;
    }

    protected boolean getCanceledOnTouchOutside() {
        return true;
    }

    protected int getDialogGravity() {
        return Gravity.BOTTOM;
    }

    protected int getDialogHeight() {
        return (int) (ResolutionUtil.getInstance(getContext()).getHeight() * 0.5f);
    }

    protected int getDialogStyle() {
        return R.style.NormalDialogStyle;
    }

    protected int getDialogWight() {
        return (int) (ResolutionUtil.getInstance(getContext()).getWidth());
    }

    public abstract int getLayoutId();

    @Nullable
    public View getView() {
        return super.getView();
    }

    public abstract void initView(View view);

    public abstract void initData();

    public abstract void initEvent();

    @NonNull
    public Dialog onCreateDialog(Bundle paramBundle) {
        getActivity().getWindow().setSoftInputMode(32);
        View localObject = getActivity().getLayoutInflater().inflate(getLayoutId(), null);
        Dialog dialog = new Dialog(getActivity(), getDialogStyle());
        dialog.setContentView(localObject);
        dialog.setCanceledOnTouchOutside(getCanceledOnTouchOutside());
        dialog.setCancelable(getCanceledOnTouchOutside());
        Window window = dialog.getWindow();

        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        WindowManager.LayoutParams localLayoutParams = (window).getAttributes();
        localLayoutParams.gravity = getDialogGravity();
        localLayoutParams.width = getDialogWight();
        localLayoutParams.height = getDialogHeight();
        window.setAttributes(localLayoutParams);
        return dialog;
    }

    @Nullable
    public View onCreateView(LayoutInflater paramLayoutInflater, @Nullable ViewGroup paramViewGroup, @Nullable Bundle paramBundle) {
        getActivity().getWindow().setSoftInputMode(32);
        View inflate = paramLayoutInflater.inflate(getLayoutId(), paramViewGroup);
        this.userPreference = PreferenceUtil.getPreference(getContext(), APP_SHARE_USER);
        initView(inflate);
        initData();
        initEvent();
        return inflate;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (onShowListener != null) {
            onShowListener.onShow();
        }
    }

    public void show(FragmentManager paramFragmentManager) {
        LogUtil.i(getTag() + "_" + this.toString());
        if (getTag() != null) {
            if (!getTag().contains(this.toString()) &&
                    !this.toString().contains(getTag())) {
                FragmentTransaction ft = paramFragmentManager.beginTransaction();
                show(ft, this.toString());
            }
        } else {
            FragmentTransaction ft = paramFragmentManager.beginTransaction();
            show(ft, this.toString());
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        isDestroy = true;
        if (onDismissListener != null) {
            onDismissListener.onDismiss();
        }
    }
}
