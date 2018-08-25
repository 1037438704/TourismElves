package com.tourismelves.view.fragment;

import android.content.Intent;
import android.location.Location;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;

import com.google.gson.Gson;
import com.tourismelves.R;
import com.tourismelves.app.constant.CommentConstants;
import com.tourismelves.model.bean.ElfsaidBean;
import com.tourismelves.model.bean.RechargeBean;
import com.tourismelves.model.event.SelectCityBus;
import com.tourismelves.model.net.OkHttpUtils;
import com.tourismelves.utils.common.EventBusUtil;
import com.tourismelves.utils.common.ToastUtil;
import com.tourismelves.view.activity.ElverSayDetailsActivity;
import com.tourismelves.view.adapter.ElfSaidAdapter;
import com.tourismelves.view.adapter.ScenicSpotAdapter;
import com.tourismelves.view.fragment.base.BaseFragment;
import com.tourismelves.view.widget.swipetoloadlayout.OnLoadMoreListener;
import com.tourismelves.view.widget.swipetoloadlayout.OnRefreshListener;
import com.tourismelves.view.widget.swipetoloadlayout.SwipeToLoadLayout;


import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

import static com.tourismelves.app.constant.UrlConstants.elfsaidinfo;
import static com.tourismelves.app.constant.UrlConstants.userinfo;
import static com.tourismelves.view.widget.loadlayout.State.FAILED;
import static com.tourismelves.view.widget.loadlayout.State.SUCCESS;

/**
 * 景区
 */
public class ElfSaidFragment extends BaseFragment {
    @BindView(R.id.swipe_target)
    RecyclerView swipeTarget;
    @BindView(R.id.swipeToLoadLayout)
    SwipeToLoadLayout swipeToLoadLayout;

    AppCompatImageView homeQr;
    @BindView(R.id.layout_null_data)
    RelativeLayout layoutNullData;



    //当前选择的地方
  //  private String strAddress = CommentConstants.curAddress;
    //获取当前经纬度
    private Location location;

    ElfsaidBean elfsaidBean;
    List<ElfsaidBean.DataListBean> listBeen;
    ElfSaidAdapter elfSaidAdapter;
    //当前页数
    private int page = 1;
    //总页数
    private int totalPage = 1;

    @Override
    protected int setContentLayout() {
        return R.layout.fragment_elf_said;
    }

    @Override
    protected void initView(View view) {
        EventBusUtil.register(this);
        listBeen = new ArrayList<>();
       // setStatusBar(R.id.home_status);
       // homeQr.setVisibility(View.GONE);

    }

    private void setAddress() {
      // strAddress = CommentConstants.curAddress;
//        homeAdapter.setProvinces(strAddress);
//        if (strAddress.equals("")) {
//            homeAddress.setText(R.string.provinces);
//            homeAddress.setTextColor(0xff333333);
//        } else {
//            homeAddress.setText(strAddress);
//            homeAddress.setTextColor(0xff16adff);
//        }
    }

    @Override
    protected void obtainData() {
        swipeTarget.setLayoutManager(new LinearLayoutManager(getActivity()));
        elfSaidAdapter = new ElfSaidAdapter(getActivity(),listBeen);
        swipeTarget.setAdapter(elfSaidAdapter);

        ElfSaidInfo(true);
    }

    @Override
    protected void initEvent() {

        //刷新数据
        swipeToLoadLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh() {
                page = 1;
                listBeen.clear();
                ElfSaidInfo(true);
            }
        });
        //加载更多
        swipeToLoadLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                page++;
                ElfSaidInfo(false);
            }
        });
    }

    //获取精灵说信息
    private void ElfSaidInfo(final boolean isRefresh) {

        OkHttpUtils.get(String.format(elfsaidinfo,20,page),
                new OkHttpUtils.ResultCallback<String>() {
                    @Override
                    public void onSuccess(String response) {

                        Log.e("精灵说",response);
                        Gson gson = new Gson();
                        elfsaidBean = gson.fromJson(response,ElfsaidBean.class);
                        listBeen.addAll(elfsaidBean.getDataList());

                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                //是否为刷新操作
                                if (isRefresh) {
                                    //替换适配器的数据源
                                    elfSaidAdapter.replaceData(listBeen);
                                    //停止头部刷新动画
                                    swipeToLoadLayout.setRefreshing(false);

                                    if (listBeen.size() == 0) {
                                        layoutNullData.setVisibility(View.VISIBLE);
                                    } else {
                                        layoutNullData.setVisibility(View.GONE);
                                    }
                                } else {
                                    //添加适配器的数据源
                                    elfSaidAdapter.insertItems(listBeen);
                                    //停止底部加载动画
                                    swipeToLoadLayout.setLoadingMore(false);
                                }

                                //如果总页数超过一条，开启加载监听
                                if (totalPage > 1) {
                                    swipeToLoadLayout.setLoadMoreEnabled(true);
                                }
                                //如果当前访问的是最后一页，关闭加载监听
                                if (totalPage == page) {
                                    swipeToLoadLayout.setLoadMoreEnabled(false);
                                }
                            }
                        });


                    }
                    @Override
                    public void onFailure(Exception e) {
                        ToastUtil.show(R.string.no_found_network);
                        getLoadLayout().setLayoutState(FAILED);
                    }
                });
        elfSaidAdapter.setOnItemClickListener(new ElfSaidAdapter.OnItemClickListener() {
            @Override
            public void OnItem(View view, int position) {
                Intent intent = new Intent(getActivity(), ElverSayDetailsActivity.class);
                intent.putExtra("titlename",listBeen.get(position).getShortTitle());
                intent.putExtra("acticleid",listBeen.get(position).getArticleId()+"");
                startActivity(intent);

            }
        });
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getSelectCityBus(final SelectCityBus selectCityBus) {
        setAddress();
        swipeToLoadLayout.setRefreshing(true);
    }
}
