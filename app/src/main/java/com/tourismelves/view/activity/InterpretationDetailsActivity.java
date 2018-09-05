package com.tourismelves.view.activity;

import android.media.MediaPlayer;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.SeekBar;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.tourismelves.R;
import com.tourismelves.model.bean.AttractionsBean;
import com.tourismelves.model.net.OkHttpUtils;
import com.tourismelves.utils.ManagedMediaPlayer;
import com.tourismelves.utils.common.EventBusUtil;
import com.tourismelves.utils.common.ToastUtil;
import com.tourismelves.utils.glide.ShowImageUtils;
import com.tourismelves.utils.log.LogUtil;
import com.tourismelves.utils.system.ResolutionUtil;
import com.tourismelves.utils.system.SPUtils;
import com.tourismelves.view.activity.base.StateBaseActivity;
import com.tourismelves.view.adapter.InterpretationDetailsAdapter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.OnClick;

import static com.tourismelves.app.constant.UrlConstants.delFavorite;
import static com.tourismelves.app.constant.UrlConstants.isFavorite;
import static com.tourismelves.app.constant.UrlConstants.port;
import static com.tourismelves.app.constant.UrlConstants.saveFavorite;
import static com.tourismelves.utils.TimeUtil.calculateTime;

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
    @BindView(R.id.interpretation_details_play)
    AppCompatImageView interpretationDetailsPlay;
    @BindView(R.id.interpretation_details_seekbar)
    SeekBar interpretationDetailsSeekbar;
    @BindView(R.id.interpretation_details_time)
    AppCompatTextView interpretationDetailsTime;
    private ArrayList<AttractionsBean> attractionsBeans;
    private InterpretationDetailsAdapter interpretationDetailsAdapter;
    private String userId;
    private int ordId;

    private boolean isTouchBar = false;
    private Timer timer;
    private ManagedMediaPlayer mMediaPlayer;
    private String audioPath = "";


    @Override
    protected void setContentLayout() {
        setContentView(R.layout.activity_interpretation_details);
    }

    @Override
    protected void initControls() {
        showStateLayout(1);
        setBaseTitle("讲解详情");
        setBaseRightImage(R.drawable.icon_like_select);
        mMediaPlayer = new ManagedMediaPlayer();
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
        if (attractionsBean.getAudioList() != null && attractionsBean.getAudioList().size() > 0) {
            audioPath = port + attractionsBean.getAudioList().get(0).getAudioPath();
        }
        play();

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

                if (attractionsBean.getAudioList() != null && attractionsBean.getAudioList().size() > 0) {
                    audioPath = port + attractionsBean.getAudioList().get(0).getAudioPath();
                } else {
                    audioPath = "";
                }
                play();

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

        interpretationDetailsSeekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                isTouchBar = true;
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                isTouchBar = false;
                //首先获取seekbar拖动后的位置
                int progress = interpretationDetailsSeekbar.getProgress();
                if (mMediaPlayer != null) {
                    //跳转到某个位置播放
                    if (!audioPath.equals("")) {
                        mMediaPlayer.seekTo(progress);
                        setTime();
                    }
                }
                if (audioPath.equals("")) {
                    interpretationDetailsSeekbar.setProgress(0);
                }
            }
        });

        mMediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                cancelTimer();
                setTime();
                interpretationDetailsSeekbar.setProgress(0);
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

    @OnClick({R.id.interpretation_details_show, R.id.interpretation_details_show2, R.id.interpretation_details_play})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.interpretation_details_show:
                interpretationDetailsCard2.setVisibility(View.VISIBLE);
                break;
            case R.id.interpretation_details_show2:
                interpretationDetailsCard2.setVisibility(View.GONE);
                break;
            case R.id.interpretation_details_play:
                if (audioPath.equals("")) {
                    ToastUtil.show("播放失败，地址为空");
                    return;
                }

                if (mMediaPlayer.isPlaying()) {
                    mMediaPlayer.pause();
                    cancelTimer();
                } else {
                    start();
                }
                break;
        }
    }

    /**
     * 音乐播放地方
     */

    private void start() {
        interpretationDetailsPlay.setImageResource(R.mipmap.zantingbofang);
        mMediaPlayer.start();
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        setTime();
                        if (!isTouchBar) {
                            if (interpretationDetailsSeekbar != null) {
                                interpretationDetailsSeekbar.setMax(getDuration());
                                interpretationDetailsSeekbar.setProgress(getCurrentPosition());
                            }
                        }
                    }
                });
            }
        }, 0, 50);
    }

    private void cancelTimer() {
        if (interpretationDetailsPlay != null) {
            interpretationDetailsPlay.setImageResource(R.mipmap.bofangqi);
            interpretationDetailsTime.setText("0:00/0:00");
            interpretationDetailsSeekbar.setProgress(0);
        }
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
    }

    private void setTime() {
        if (interpretationDetailsTime != null) {
            String s = calculateTime(getDuration() / 1000);
            String s1 = calculateTime(getCurrentPosition() / 1000);
            interpretationDetailsTime.setText(s1 + "/" + s);
        }
    }

    public int getCurrentPosition() {
        if (mMediaPlayer != null) {
            return mMediaPlayer.getCurrentPosition();
        }
        return 0;
    }

    public int getDuration() {
        if (mMediaPlayer != null) {
            return mMediaPlayer.getDuration();
        }
        return 0;
    }

    public void play() {
        LogUtil.i(audioPath);
        try {
            if (audioPath.equals("")) {
                if (mMediaPlayer != null && mMediaPlayer.isPlaying()) {
                    mMediaPlayer.stop();
                    cancelTimer();
                }
                ToastUtil.show("播放失败，地址为空");
                return;
            }
            mMediaPlayer.reset();
            mMediaPlayer.setDataSource(audioPath);
            mMediaPlayer.prepareAsync();
            mMediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    start();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onDestroy() {
        mMediaPlayer.pause();
        mMediaPlayer.release();
        mMediaPlayer = null;
        cancelTimer();
        super.onDestroy();
    }

}
