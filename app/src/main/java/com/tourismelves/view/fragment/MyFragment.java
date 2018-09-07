package com.tourismelves.view.fragment;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.squareup.okhttp.Request;
import com.tencent.mm.opensdk.modelmsg.SendMessageToWX;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
import com.tencent.mm.opensdk.modelmsg.WXTextObject;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.tourismelves.R;
import com.tourismelves.model.bean.OrderNumBean;
import com.tourismelves.model.bean.UserBean;
import com.tourismelves.model.net.OkHttpUtils;
import com.tourismelves.utils.pinyin.ApiManager;
import com.tourismelves.utils.system.SPUtils;
import com.tourismelves.view.activity.CollectActivity;
import com.tourismelves.view.activity.CooperationActivity;
import com.tourismelves.view.activity.CouponActivity;
import com.tourismelves.view.activity.DownloadingActivity;
import com.tourismelves.view.activity.FootMarkActivity;
import com.tourismelves.view.activity.HelpActivity;
import com.tourismelves.view.activity.InviteActivity;
import com.tourismelves.view.activity.LoginActivity;
import com.tourismelves.view.activity.MyAccountActivity;
import com.tourismelves.view.activity.OrderActivity;
import com.tourismelves.view.activity.RechargeActivity;
import com.tourismelves.view.activity.SetupActivity;
import com.tourismelves.view.dialog.ActivityCodeDialog;
import com.tourismelves.view.fragment.base.BaseFragment;
import com.zhy.http.okhttp.callback.StringCallback;

import static com.tourismelves.app.constant.UrlConstants.userinfo;

/**
 * 我的
 */
public class MyFragment extends BaseFragment {

    LinearLayout mLinearAccount,mLinearCoupon,mLinearTrack,mLinearSetup,mLinearCooper,mLinearInvite,mLinearCode,exit_login,ll_help;
    TextView tv_order,tv_loginname,tv_recharge,tv_download;
    TextView tv_title;
    TextView tv_name;
    TextView tv_collect,tv_ordernum,tv_xiazai,tv_money,tv_collectbo;
    UserBean userBean;
    IWXAPI api;


    @Override
    protected int setContentLayout() {
        return R.layout.fragment_my;
    }

    @Override
    protected void initView(View view) {
       // showStateLayout(2);
        //EventBusUtil.register(this);
        //setStatusBar(R.id.select_city_status);
        api = WXAPIFactory.createWXAPI(getActivity(), "wx127d8dd53cf7aabc", false);
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
        //金币
        tv_recharge = view.findViewById(R.id.tv_recharge);
        tv_recharge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//
//                WXTextObject textObject = new WXTextObject();
//                textObject.text = "hello";//你要分享出去的文本
//                WXMediaMessage msg = new WXMediaMessage();
//                msg.mediaObject = textObject;
//                msg.description = "hello";
//
//                SendMessageToWX.Req req = new SendMessageToWX.Req();
//                req.transaction = buildTransaction("text");// 唯一标识一个请求
//                req.message = msg;
//                // 发送到聊天界面——WXSceneSession
//                // 发送到朋友圈——WXSceneTimeline
//                // 添加到微信收藏——WXSceneFavorite
//                req.scene = SendMessageToWX.Req.WXSceneSession;
//                api.sendReq(req);
                if (!isLogin(getActivity(),true)){
                    return;
                }
                Intent intent = new Intent(getActivity(), RechargeActivity.class);
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

        //下载
        tv_download = view.findViewById(R.id.tv_download);
        tv_download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), DownloadingActivity.class);
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
                if (!isLogin(getActivity(),true)){
                    return;
                }
                new ActivityCodeDialog().show(((AppCompatActivity) getContext()).getSupportFragmentManager());
            }
        });

        tv_collect = view.findViewById(R.id.my_collect);
        tv_ordernum = view.findViewById(R.id.my_order);
        tv_xiazai = view.findViewById(R.id.my_xiazai);
        tv_money = view.findViewById(R.id.my_money);
        //退出登录
        exit_login = view.findViewById(R.id.my_exitlogin);
        exit_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SPUtils.getInstance(getActivity()).clear();

                Intent intent =new Intent(getActivity(),LoginActivity.class);
                startActivity(intent);
                Toast.makeText(getActivity(), "退出成功", Toast.LENGTH_SHORT).show();
            }
        });
        //我的收藏
        tv_collectbo = view.findViewById(R.id.my_collectc);
        tv_collectbo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isLogin(getActivity(),true)){
                    return;
                }
                Intent intent = new Intent(getActivity(), CollectActivity.class);
                startActivity(intent);
            }
        });
        //帮助与反馈
        ll_help = view.findViewById(R.id.ll_help);
        ll_help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isLogin(getActivity(),true)){
                    return;
                }
                Intent intent = new Intent(getActivity(), HelpActivity.class);
                startActivity(intent);
            }
        });

    }

    private String buildTransaction(String text) {
        return null;
    }


    @Override
    protected void obtainData() {

        UserInfo();
        OrderNum();
    }

    @Override
    protected void initEvent() {

    }

    //获取个人信息
    private void UserInfo(){
        String userid = SPUtils.getInstance(getActivity()).getString("putInt");  //取出这个int值
        Log.e("用户id",userid);
        if (userid.equals("")){
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
        OkHttpUtils.get(String.format(userinfo, userid+""),
                new OkHttpUtils.ResultCallback<String>() {
                    @Override
                    public void onSuccess(String response) {

                        Log.e("USER",response);
                        Gson gson = new Gson();

                        userBean = gson.fromJson(response,UserBean.class);
                       // tv_money.setText(userBean.getDataList().get(0).getGold()+"");
//                        if (userBean.getDataList().size()<=0){




//
//                        }
                        tv_name.setText(userBean.getDataList().get(0).getNickName());
                        tv_money.setText(userBean.getDataList().get(0).getGold()+"");
                    }
                    @Override
                    public void onFailure(Exception e) {

                    }
                }
        );
    }

    //获取订单数量等
    private void OrderNum(){
        com.zhy.http.okhttp.OkHttpUtils.get().url(ApiManager.ALL_URL+"lyjl/app/getUserCount.do")
                .addParams("userId",SPUtils.getInstance(getActivity()).getString("putInt"))
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Request request, Exception e) {

                    }

                    @Override
                    public void onResponse(String response) {
                        Gson gson = new Gson();
                        OrderNumBean orderNumBean;
                        orderNumBean = gson.fromJson(response,OrderNumBean.class);
                        Log.e("用户购物信息",response);
                        tv_collect.setText(orderNumBean.getDataList().get(0).getFavoriteNum()+"");
                        tv_ordernum.setText(orderNumBean.getDataList().get(0).getPayedOrderNum()+orderNumBean.getDataList().get(0).getUnPayOrderNum()+"");
                    }
                });
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
