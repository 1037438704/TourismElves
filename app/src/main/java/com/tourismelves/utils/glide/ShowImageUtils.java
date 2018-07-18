package com.tourismelves.utils.glide;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.widget.ImageView;

import com.bumptech.glide.DrawableTypeRequest;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.tourismelves.R;

import jp.wasabeef.glide.transformations.CropCircleTransformation;

public class ShowImageUtils {
    private static final int errorimg = R.mipmap.ic_launcher_round;

    /**
     * 显示图片Imageview
     */
    public static void showImageView(Context context, String url,
                                     ImageView imgeview) {
        Glide.with(context).load(url)// 加载图片
                .error(errorimg)// 设置错误图片
                .crossFade()// 设置淡入淡出效果，默认300ms，可以传参
                .placeholder(errorimg)// 设置占位图
                .diskCacheStrategy(DiskCacheStrategy.RESULT)// 缓存修改过的图片
                .into(imgeview);
    }

    public static void showImageView(Context context, String url,
                                     ImageView imgeview, float sizeMultiplier) {
        Glide.with(context).load(url)// 加载图片
                .error(errorimg)// 设置错误图片
                .placeholder(errorimg)
                .crossFade()// 设置淡入淡出效果，默认300ms，可以传参
                .diskCacheStrategy(DiskCacheStrategy.ALL)// 缓存修改过的图片
                .thumbnail(sizeMultiplier) //缩略图
                .into(imgeview);
    }

    public static void showImageView(Context context, int errorimg, String url,
                                     ImageView imgeview) {
        Glide.with(context).load(url)// 加载图片
                .error(errorimg)// 设置错误图片
                .placeholder(errorimg)
                .crossFade()// 设置淡入淡出效果，默认300ms，可以传参
                .diskCacheStrategy(DiskCacheStrategy.ALL)// 缓存修改过的图片
                .into(imgeview);
    }

    public static void showImageView(Context context, int res,
                                     ImageView imgeview) {
        Glide.with(context).load(res)// 加载图片
                .crossFade()// 设置淡入淡出效果，默认300ms，可以传参
                .into(imgeview);
    }

    /**
     * 圆形图片
     */
    @SuppressLint("NewApi")
    public static void showImageViewToCircle(Context context, String url, ImageView imgeview) {
        if (!((Activity) context).isDestroyed()) {
            Glide.with(context)
                    .load(url)
                    .error(errorimg)// 设置错误图片
                    .placeholder(errorimg)
                    .bitmapTransform(new CropCircleTransformation(context))
                    .into(imgeview);
        }
    }

    /**
     * 圆角图片
     */
    //加载网络图片
    @SuppressLint("NewApi")
    public static void showImageViewToRoundedCorners(Context context, String url, ImageView imgeview, int dp) {
        if (context != null) {
            if (!((Activity) context).isDestroyed()) {
                DrawableTypeRequest<String> builder = Glide.with(context).load(url);
                builder.transform(new CenterCrop(context), new GlideRoundImage(context, Resources.getSystem().getDisplayMetrics().density * dp))
                        .error(errorimg)
                        .placeholder(errorimg)
                        .diskCacheStrategy(DiskCacheStrategy.RESULT)
                        .into(imgeview);
            }
        }
    }


}
