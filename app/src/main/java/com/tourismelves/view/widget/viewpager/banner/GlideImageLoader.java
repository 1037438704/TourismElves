package com.tourismelves.view.widget.viewpager.banner;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.tourismelves.R;
import com.tourismelves.view.widget.viewpager.banner.loader.ImageLoader;


public class GlideImageLoader extends ImageLoader {
    @Override
    public void displayImage(final Context context, Object path, final ImageView imageView) {
//        imageView.setPadding((int) context.getResources().getDimension(R.dimen.dp15), 0,
//                (int) context.getResources().getDimension(R.dimen.dp15), 0);

        Glide.with(context)
                .load(path)
//                .asBitmap()
                .centerCrop() // 缩放图片让图片充满整个ImageView的边框，然后裁掉超出的部分
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .diskCacheStrategy(DiskCacheStrategy.RESULT) //设置缓存
                .into(imageView);
//                .into(new BitmapImageViewTarget(imageView) {
//                    @Override
//                    protected void setResource(Bitmap resource) {
//                        super.setResource(resource);
//
//                        RoundedBitmapDrawable circularBitmapDrawable =
//                                RoundedBitmapDrawableFactory.create(context.getResources(), resource);
//                        circularBitmapDrawable.setCornerRadius(ResolutionUtil.getInstance(context).px2dp2pxWidth(10)); //设置圆角弧度
//                        imageView.setImageDrawable(circularBitmapDrawable);
//                    }
//                });
    }

    @Override
    public ImageView createImageView(Context context) {
        //圆角
        return new ImageView(context);
    }
}
