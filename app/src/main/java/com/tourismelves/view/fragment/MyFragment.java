package com.tourismelves.view.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.tourismelves.R;
import com.tourismelves.model.bean.UserBean;
import com.tourismelves.model.net.OkHttpUtils;
import com.tourismelves.utils.common.EventBusUtil;
import com.tourismelves.utils.system.SPConstants;
import com.tourismelves.utils.system.SPUtils;
import com.tourismelves.view.activity.CooperationActivity;
import com.tourismelves.view.activity.CouponActivity;
import com.tourismelves.view.activity.FootMarkActivity;
import com.tourismelves.view.activity.InviteActivity;
import com.tourismelves.view.activity.LoginActivity;
import com.tourismelves.view.activity.MyAccountActivity;
import com.tourismelves.view.activity.OrderActivity;
import com.tourismelves.view.activity.RechargeActivity;
import com.tourismelves.view.activity.SetupActivity;
import com.tourismelves.view.dialog.ActivityCodeDialog;
import com.tourismelves.view.fragment.base.BaseFragment;

import static com.tourismelves.app.constant.UrlConstants.login;
import static com.tourismelves.app.constant.UrlConstants.userinfo;

/**
 * 我的
 */
public class MyFragment extends BaseFragment {

    LinearLayout mLinearAccount,mLinearCoupon,mLinearTrack,mLinearSetup,mLinearCooper,mLinearInvite,mLinearCode;
    TextView tv_order,tv_loginname,tv_recharge;
    TextView tv_title;
    TextView tv_name;

    @Override
    protected int setContentLayout() {
        return R.layout.fragment_my;
    }

    @Override
    protected void initView(View view) {
       // showStateLayout(2);
        //EventBusUtil.register(this);
        //setStatusBar(R.id.select_city_status);
        tv_title = view.findViewById(R.id.title_name);
        tv_title.setText("我的");
        //我的账户
        mLinearAccount  = view.findViewById(R.id.ll_acount);
        mLinearAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isLogin(getActivity(),true)){
                    return;
                }
                Intent intent = new Intent(getActivity(), MyAccountActivity.class);
                startActivity(intent);
            }
        });
        //我的优惠券
        mLinearCoupon = view.findViewById(R.id.ll_coupon);
        mLinearCoupon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isLogin(getActivity(),true)){
                    return;
                }
                Intent intent = new Intent(getActivity(), CouponActivity.class);
                startActivity(intent);
            }
        });
        //我的足迹
        mLinearTrack = view.findViewById(R.id.ll_track);
        mLinearTrack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isLogin(getActivity(),true)){
                    return;
                }
                Intent intent = new Intent(getActivity(), FootMarkActivity.class);
                startActivity(intent);
            }
        });
        //设置
        mLinearSetup = view.findViewById(R.id.ll_set);
        mLinearSetup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isLogin(getActivity(),true)){
                    return;
                }
                Intent intent = new Intent(getActivity(), SetupActivity.class);
                startActivity(intent);
            }
        });
        //商务合作
        mLinearCooper = view.findViewById(R.id.ll_cooper);
        mLinearCooper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isLogin(getActivity(),true)){
                    return;
                }
                Intent intent = new Intent(getActivity(), CooperationActivity.class);
                startActivity(intent);
            }
        });
        //订单管理
        tv_order = view.findViewById(R.id.tv_order);
        tv_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isLogin(getActivity(),true)){
                    return;
                }
                Intent intent = new Intent(getActivity(), OrderActivity.class);
                startActivity(intent);
            }
        });
//        //登录
//        tv_loginname = view.findViewById(R.id.tv_loginname);
//        tv_loginname.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(getActivity(), LoginActivity.class);
//                startActivity(intent);
//            }
//        });
        //姓名
        tv_name = view.findViewById(R.id.my_name);

        //充值金币
        tv_recharge = view.findViewById(R.id.tv_recharge);
        tv_recharge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isLogin(getActivity(),true)){
                    return;
                }
                Intent intent = new Intent(getActivity(), RechargeActivity.class);
                startActivity(intent);
            }
        });
        //邀请好友
        mLinearInvite = view.findViewById(R.id.ll_yqhy);
        mLinearInvite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isLogin(getActivity(),true)){
                    return;
                }
                Intent intent = new Intent(getActivity(), InviteActivity.class);
                startActivity(intent);
            }
        });
        //激活码
        mLinearCode = view.findViewById(R.id.linear_code);
        mLinearCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new ActivityCodeDialog().show(((AppCompatActivity) getContext()).getSupportFragmentManager());
            }
        });

    }



    @Override
    protected void obtainData() {
//        if (!isLogin(getActivity(),true)){
//            tv_name.setText("请登录");
//            return;
//        }
        UserInfo();
    }

    @Override
    protected void initEvent() {

    }

    //获取个人信息
    private void UserInfo(){
        String userid = SPUtils.getInstance(getActivity()).getString("putInt");  //取出这个int值
        Log.e("用户id",userid);
        OkHttpUtils.get(String.format(userinfo, userid+""),
                new OkHttpUtils.ResultCallback<String>() {
                    @Override
                    public void onSuccess(String response) {

                        Log.e("USER",response);
                        Gson gson = new Gson();
                        UserBean userBean;
                        userBean = gson.fromJson(response,UserBean.class);
                        if (!userBean.getDataList().isEmpty()){
                            tv_name.setText("请登录");
                            tv_name.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    Intent intent = new Intent(getActivity(),LoginActivity.class);
                                    startActivity(intent);
                                }
                            });
                            return;
                        }
                        tv_name.setText(userBean.getDataList().get(0).getNickName());

                    }

                    @Override
                    public void onFailure(Exception e) {

                    }
                }
        );
    }

    /**
     * 检查用户是否登录
     * @param context
     * @param isNeedToLoginView true 表示需要跳转登录界面   false 只检查用户是否登录
     * @return
     */
    public static boolean isLogin(Context context, boolean isNeedToLoginView){
        String token =  SPUtils.getInstance(context).getString("putInt");  //取出这个int值
       // Log.e("个人中心---------------------",token);
        if (!TextUtils.isEmpty(token)){
            return true;
        }
        if (isNeedToLoginView) {
            Intent intent = new Intent(context,LoginActivity.class);
            context.startActivity(intent);
            return false;
        }
        return false;
    }

}
