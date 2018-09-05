package com.tourismelves.view.activity;

import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.tourismelves.R;
import com.tourismelves.model.bean.AttractionsBean;
import com.tourismelves.model.net.OkHttpUtils;
import com.tourismelves.utils.common.EventBusUtil;
import com.tourismelves.utils.common.ToastUtil;
import com.tourismelves.utils.glide.ShowImageUtils;
import com.tourismelves.utils.system.ResolutionUtil;
import com.tourismelves.utils.system.SPUtils;
import com.tourismelves.view.activity.base.StateBaseActivity;
import com.tourismelves.view.adapter.InterpretationDetailsAdapter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

import static com.tourismelves.app.constant.UrlConstants.delFavorite;
import static com.tourismelves.app.constant.UrlConstants.isFavorite;
import static com.tourismelves.app.constant.UrlConstants.port;
import static com.tourismelves.app.constant.UrlConstants.saveFavorite;

/**
 * 讲解详情
 */

public class InterpretationDetailsActivity extends StateBaseActivity {
    @BindView(R.id.interpretation_details_fuzzy)
    AppCompatImageView interpretationDetailsFuzzy;
    @BindView(R.id.interpretation_details_icon)
    AppCompatImageView interpretationDetailsIcon;
    @BindView(R.id.interpretation_details_recycler)
    RecyclerView interpretationDetailsRecycler;
    @BindView(R.id.interpretation_details_name)
    AppCompatTextView interpretationDetailsName;
    @BindView(R.id.interpretation_details_content)
    AppCompatTextView interpretationDetailsContent;
    @BindView(R.id.interpretation_details_content_sc)
    NestedScrollView interpretationDetailsContentSc;
    @BindView(R.id.interpretation_details_name2)
    AppCompatTextView interpretationDetailsName2;
    @BindView(R.id.interpretation_details_content2)
    AppCompatTextView interpretationDetailsContent2;
    @BindView(R.id.interpretation_details_content_sc2)
    NestedScrollView interpretationDetailsContentSc2;
    @BindView(R.id.interpretation_details_card2)
    CardView interpretationDetailsCard2;
    private ArrayList<AttractionsBean> attractionsBeans;
    private InterpretationDetailsAdapter interpretationDetailsAdapter;
    private String userId;
    private int ordId;


    @Override
    protected void setContentLayout() {
        setContentView(R.layout.activity_interpretation_details);
    }

    @Override
    protected void initControls() {
        showStateLayout(1);
        setBaseTitle("讲解详情");
        setBaseRightImage(R.drawable.icon_like_select);
        userId = SPUtils.getInstance(getContext()).getString("putInt");
        interpretationDetailsRecycler.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
    }

    @Override
    protected void obtainData() {
        attractionsBeans = getIntent().getParcelableArrayListExtra("attractionsBeans");
        interpretationDetailsAdapter = new InterpretationDetailsAdapter(getContext(), attractionsBeans);
        interpretationDetailsRecycler.setAdapter(interpretationDetailsAdapter);

        AttractionsBean attractionsBean = attractionsBeans.get(getIntent().getIntExtra("position", 0));
        ordId = attractionsBeans.get(0).getPhotoList().get(0).getOrgId();

        String photoPath = "";
        if (attractionsBean.getPhotoList() != null && attractionsBean.getPhotoList().size() > 0) {
            photoPath = port + attractionsBean.getPhotoList().get(0).getPhotoPath();
        }

        interpretationDetailsName.setText(attractionsBean.getName());
        interpretationDetailsContent.setText(attractionsBean.getDescription());
        interpretationDetailsName2.setText(attractionsBean.getName());
        interpretationDetailsContent2.setText(attractionsBean.getDescription());
        ShowImageUtils.showRounded(getContext(), photoPath,
                (int) getResources().getDimension(R.dimen.dp295),
                (int) getResources().getDimension(R.dimen.dp190),
                interpretationDetailsIcon, 10);
        ShowImageUtils.showFuzzyRounded(getContext(), photoPath,
                ResolutionUtil.getInstance(getContext()).getWidth(),
                (int) getResources().getDimension(R.dimen.dp220),
                interpretationDetailsFuzzy);

        isFavorite();
    }

    @Override
    protected void initEvent() {
        interpretationDetailsAdapter.setOnClickItemListener(new InterpretationDetailsAdapter.OnClickItemListener() {
            @Override
            public void onClickItem(int position) {
                AttractionsBean attractionsBean = attractionsBeans.get(position);

                String photoPath = "";
                if (attractionsBean.getPhotoList() != null && attractionsBean.getPhotoList().size() > 0) {
                    photoPath = port + attractionsBean.getPhotoList().get(0).getPhotoPath();
                }

                String audioPath="";
                if (attractionsBean.getAudioList() != null && attractionsBean.getAudioList().size() > 0) {
                    audioPath = port + attractionsBean.getAudioList().get(0).getAudioPath();
                }


                interpretationDetailsName.setText(attractionsBean.getName());
                interpretationDetailsContent.setText(attractionsBean.getDescription());
                interpretationDetailsContentSc.scrollTo(0, 0);
                interpretationDetailsName2.setText(attractionsBean.getName());
                interpretationDetailsContent2.setText(attractionsBean.getDescription());
                interpretationDetailsContentSc2.scrollTo(0, 0);
                ShowImageUtils.showRounded(getContext(), photoPath,
                        (int) getResources().getDimension(R.dimen.dp295),
                        (int) getResources().getDimension(R.dimen.dp190),
                        interpretationDetailsIcon, 10);
                ShowImageUtils.showFuzzyRounded(getContext(), photoPath,
                        ResolutionUtil.getInstance(getContext()).getWidth(),
                        (int) getResources().getDimension(R.dimen.dp220),
                        interpretationDetailsFuzzy);
            }
        });

        setBaseRightListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean selected = getBaseRight().isSelected();
                if (selected) {
                    delFavorite();
                } else {
                    saveFavorite();
                }
            }
        });
    }


    /**
     * 收藏部分
     */
    private void saveFavorite() {    //收藏
        OkHttpUtils.get(saveFavorite + "userId=" + userId + "&contentId=" + ordId + "&type=" + 0,
                new OkHttpUtils.ResultCallback<String>() {
                    @Override
                    public void onSuccess(String response) {
                        JSONObject object = JSON.parseObject(response);
                        String message = object.getString("message");
                        ToastUtil.show(message);
                        if (object.getInteger("code") == 200) {
                            getBaseRight().setSelected(true);
                            EventBusUtil.postEvent(true);
                        }
                    }

                    @Override
                    public void onFailure(Exception e) {
                        ToastUtil.show(R.string.no_found_network);
                    }
                });
    }

    private void delFavorite() {    //删除收藏
        OkHttpUtils.get(delFavorite + "userId=" + userId + "&contentId=" + ordId + "&type=" + 0,
                new OkHttpUtils.ResultCallback<String>() {
                    @Override
                    public void onSuccess(String response) {
                        JSONObject object = JSON.parseObject(response);
                        String message = object.getString("message");
                        ToastUtil.show(message);
                        if (object.getInteger("code") == 200) {
                            getBaseRight().setSelected(false);
                            EventBusUtil.postEvent(false);
                        }
                    }

                    @Override
                    public void onFailure(Exception e) {
                        ToastUtil.show(R.string.no_found_network);
                    }
                });
    }

    private void isFavorite() {    //是否收藏
        OkHttpUtils.get(isFavorite + "userId=" + userId + "&contentId=" + ordId + "&type=" + 0,
                new OkHttpUtils.ResultCallback<String>() {
                    @Override
                    public void onSuccess(String response) {
                        JSONObject object = JSON.parseObject(response);
                        Integer code = object.getInteger("code");
                        if (code == 200) {
                            Boolean isLike = Boolean.valueOf(object.getString("pk_id"));
                            getBaseRight().setSelected(isLike);
                        } else {
                            ToastUtil.show(object.getString("message"));
                        }
                    }

                    @Override
                    public void onFailure(Exception e) {
                        ToastUtil.show(R.string.no_found_network);
                    }
                });
    }

    @OnClick({R.id.interpretation_details_show, R.id.interpretation_details_show2})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.interpretation_details_show:
                interpretationDetailsCard2.setVisibility(View.VISIBLE);
                break;
            case R.id.interpretation_details_show2:
                interpretationDetailsCard2.setVisibility(View.GONE);
                break;
        }
    }

}
