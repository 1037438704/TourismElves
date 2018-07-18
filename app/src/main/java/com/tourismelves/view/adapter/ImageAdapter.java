package com.tourismelves.view.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.AppCompatImageView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tourismelves.R;
import com.tourismelves.model.res.BannerRes;
import com.tourismelves.utils.glide.ShowImageUtils;

import java.util.List;

import static com.tourismelves.app.constant.UrlConstants.port;

public class ImageAdapter extends PagerAdapter {
    private Context context;
    private List<BannerRes> banner;

    public ImageAdapter(Context context, List<BannerRes> banner) {
        this.context = context;
        this.banner = banner;
    }

    @Override
    public int getCount() {
        return banner.size() == 1 ? 1 : banner.size() * 100000;
    }

    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {
        return arg0 == arg1;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    /**
     * 载入图片进去，用当前的position 除以 图片数组长度取余数是关键
     */
    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_image, null);
        AppCompatImageView imgView = view.findViewById(R.id.item_image);
        imgView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null) {
                    listener.onItemClick(banner.get(position % banner.size()));
                }
            }
        });
        String link = port + banner.get(position % banner.size()).getPath();

        ShowImageUtils.showImageView(context, link, imgView);
        container.addView(view);
        return view;
    }


    public interface OnItemClickListener {
        void onItemClick(BannerRes position);
    }

    private OnItemClickListener listener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public void replaceData(List<BannerRes> banner) {
        this.banner = banner;
        notifyDataSetChanged();
    }
}