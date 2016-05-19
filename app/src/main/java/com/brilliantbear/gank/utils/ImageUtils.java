package com.brilliantbear.gank.utils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

/**
 * Created by Bear on 2016-5-19.
 */
public class ImageUtils {

    public static void display(Context context, ImageView imageView, String url) {
        Glide.with(context).load(url).diskCacheStrategy(DiskCacheStrategy.ALL).into(imageView);
    }
}
