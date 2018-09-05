package com.tourismelves.view.adapter;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.amap.api.maps.model.LatLng;
import com.tourismelves.R;
import com.tourismelves.model.net.OkHttpUtils;
import com.tourismelves.model.res.HomeRes;
import com.tourismelves.utils.common.ToastUtil;
import com.tourismelves.utils.glide.ShowImageUtils;
import com.tourismelves.utils.system.ResolutionUtil;
import com.tourismelves.utils.system.SPUtils;
import com.tourismelves.view.activity.InterpretationListActivity;
import com.tourismelves.view.activity.SettlementActivity;
import com.tourismelves.view.adapter.base.RecyclerBaseAdapter;
import com.tourismelves.view.adapter.base.ViewHolder;

import java.util.List;

import okhttp3.Call;

import static com.tourismelves.app.constant.UrlConstants.addCart;
import static com.tourismelves.app.constant.UrlConstants.port;
import static com.tourismelves.view.fragment.MyFragment.isLogin;

/**
 * 热门景区适配器
 */

public class ScenicSpotAdapter extends RecyclerBaseAdapter<HomeRes> {
    private String provinces;
    private int h, w;

    public ScenicSpotAdapter(@NonNull Context context, @NonNull List<HomeRes> mDataList, String provinces) {
        super(context, mDataList);
        this.provinces = provinces;
        h = (int) getContext().getResources().getDimension(R.dimen.dp170);
        w = (int) (ResolutionUtil.getInstance(getContext()).getWidth() - getContext().getResources().getDimension(R.dimen.dp20));
    }

    public void setProvinces(String provinces) {
        this.provinces = provinces;
    }

    @Override
    protected void bindDataForView(ViewHolder holder, final HomeRes homeRes, int position) {
        AppCompatImageView img = holder.getView(R.id.i_scenic_spot_img);
        AppCompatTextView name = holder.getView(R.id.i_scenic_spot_name);
        AppCompatTextView address = holder.getView(R.id.i_scenic_spot_address);
        AppCompatTextView count = holder.getView(R.id.i_scenic_spot_count);
        AppCompatTextView content = holder.getView(R.id.i_scenic_spot_content);
        AppCompatTextView autoPlay = holder.getView(R.id.i_scenic_spot_auto_play);
        AppCompatTextView money = holder.getView(R.id.i_scenic_spot_money);

        autoPlay.setVisibility(homeRes.getIsAutoplay() == 1 ? View.VISIBLE : View.GONE);
        ShowImageUtils.showRounded(getContext(), port + homeRes.getImage(), w, h, img, 0);
        name.setText(homeRes.getName());
        final String sAddress = homeRes.getArea().getParentArea().getName() + " " + homeRes.getArea().getName();
        address.setText(sAddress + " " + String.format(getContext().getString(R.string.distance), homeRes.getDistance() + ""));
        count.setText(String.format(getContext().getString(R.string.scenic_spot_count), homeRes.getSceneryCount() + ""));
        content.setText(homeRes.getDescription());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), InterpretationListActivity.class);
                intent.putExtra("ordId", homeRes.getOrgId());
                intent.putExtra("name", homeRes.getName());
                intent.putExtra("distance", sAddress);
                intent.putExtra("latlng", new LatLng(homeRes.getLatitude(), homeRes.getLongitude()));
                getContext().startActivity(intent);
            }
        });

        money.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {//支付
                addCart(homeRes.getOrgId());
            }
        });

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.item_scenic_spot, parent, false);
        return new ViewHolder(view);
    }

    private Call call = null;

    /**
     * 添加购物车
     */
    private void addCart(int orgId) {
        if (!isLogin(getContext(), true)) {
            return;
        }
        final ProgressDialog dialog = new ProgressDialog(getContext());
        dialog.setMessage("正在加载中");
        dialog.show();
        dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                if (call != null) {
                    call.cancel();
                }
            }
        });

        call = OkHttpUtils.get(addCart + "userId=" + SPUtils.getInstance(getContext()).getString("putInt") + "&orgId=" + orgId,
                new OkHttpUtils.ResultCallback<String>() {
                    @Override
                    public void onSuccess(String response) {
                        JSONObject object = JSON.parseObject(response);
                        ToastUtil.show(object.getString("message"));
                        Intent intent = new Intent(getContext(), SettlementActivity.class);
                        getContext().startActivity(intent);
                        call = null;
                        dialog.dismiss();
                    }

                    @Override
                    public void onFailure(Exception e) {
                        call = null;
                        ToastUtil.show(e.getMessage());
                        dialog.dismiss();
                    }
                });
    }
}
