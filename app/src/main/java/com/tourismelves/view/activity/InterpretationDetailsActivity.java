package com.tourismelves.view.activity;

import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.tourismelves.R;
import com.tourismelves.model.bean.AttractionsBean;
import com.tourismelves.utils.glide.ShowImageUtils;
import com.tourismelves.utils.system.ResolutionUtil;
import com.tourismelves.view.activity.base.StateBaseActivity;
import com.tourismelves.view.adapter.InterpretationDetailsAdapter;

import java.util.ArrayList;

import butterknife.BindView;

import static com.tourismelves.app.constant.UrlConstants.port;

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
    private ArrayList<AttractionsBean> attractionsBeans;
    private InterpretationDetailsAdapter interpretationDetailsAdapter;


    @Override
    protected void setContentLayout() {
        setContentView(R.layout.activity_interpretation_details);
    }

    @Override
    protected void initControls() {
        showStateLayout(1);
        setBaseTitle("讲解详情");
        setBaseRightImage(R.mipmap.shoucang);
        interpretationDetailsRecycler.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
    }

    @Override
    protected void obtainData() {
        attractionsBeans = getIntent().getParcelableArrayListExtra("attractionsBeans");
        interpretationDetailsAdapter = new InterpretationDetailsAdapter(getContext(), attractionsBeans);
        interpretationDetailsRecycler.setAdapter(interpretationDetailsAdapter);

        AttractionsBean attractionsBean = attractionsBeans.get(getIntent().getIntExtra("position",0));

        String photoPath = "";
        if (attractionsBean.getPhotoList() != null && attractionsBean.getPhotoList().size() > 0) {
            photoPath = port + attractionsBean.getPhotoList().get(0).getPhotoPath();
        }

        ShowImageUtils.showRounded(getContext(), photoPath,
                (int) getResources().getDimension(R.dimen.dp295),
                (int) getResources().getDimension(R.dimen.dp190),
                interpretationDetailsIcon, 10);
        ShowImageUtils.showFuzzyRounded(getContext(), photoPath,
                ResolutionUtil.getInstance(getContext()).getWidth(),
                (int) getResources().getDimension(R.dimen.dp220),
                interpretationDetailsFuzzy);
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
    }

}
