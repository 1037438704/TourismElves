package com.tourismelves.utils.glide;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.AppCompatImageView;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.tourismelves.R;
import com.tourismelves.utils.system.ResolutionUtil;

import jp.wasabeef.glide.transformations.CropCircleTransformation;
import jp.wasabeef.glide.transformations.CropTransformation;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

public class ShowImageUtils {
    private static final int errorimg = R.mipmap.ic_launcher;

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

    @SuppressLint("NewApi")
    public static void showImageView(Context context, String url, int w, int h, ImageView imgeview) {
        if (!((Activity) context).isDestroyed()) {
            Glide.with(context)
                    .load(url)
                    .bitmapTransform(new CropTransformation(context, w, h, CropTransformation.CropType.CENTER))
                    .into(imgeview);
        }
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

    @SuppressLint("NewApi")
    public static void showTopRounded(Context context, String url, AppCompatImageView imgeview, int px) {
        if (context != null) {
            if (!((Activity) context).isDestroyed()) {
                Glide.with(context)
                        .load(url)
                        .error(errorimg)
                        .bitmapTransform(new CenterCrop(context), new RoundedCornersTransformation(context,
                                ResolutionUtil.getInstance(context).px2dp2pxWidth(px), 0,
                                RoundedCornersTransformation.CornerType.TOP))
                        .diskCacheStrategy(DiskCacheStrategy.RESULT)
                        .into(imgeview);
            }
        }
    }
    @SuppressLint("NewApi")
    public static void showRounded(Context context, String url, AppCompatImageView imgeview, int px) {
        if (context != null) {
            if (!((Activity) context).isDestroyed()) {
                Glide.with(context)
                        .load(url)
                        .error(errorimg)
                        .bitmapTransform(new CenterCrop(context), new RoundedCornersTransformation(context,
                                ResolutionUtil.getInstance(context).px2dp2pxWidth(px), 0,
                                RoundedCornersTransformation.CornerType.ALL))
                        .diskCacheStrategy(DiskCacheStrategy.RESULT)
                        .into(imgeview);
            }
        }
    }
    @SuppressLint("NewApi")
    public static void showRounded(Context context, String url, int w, int h, AppCompatImageView imgeview, int px) {
        if (context != null) {
            if (!((Activity) context).isDestroyed()) {
                Glide.with(context)
                        .load(url)
                        .error(errorimg)
                        .bitmapTransform(new CropTransformation(context, w, h, CropTransformation.CropType.CENTER),
                                new RoundedCornersTransformation(context,
                                ResolutionUtil.getInstance(context).px2dp2pxWidth(px), 0,
                                RoundedCornersTransformation.CornerType.ALL))
                        .diskCacheStrategy(DiskCacheStrategy.RESULT)
                        .into(imgeview);
            }
        }
    }


}
