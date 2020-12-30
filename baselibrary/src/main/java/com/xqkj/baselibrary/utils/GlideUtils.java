package com.xqkj.baselibrary.utils;

import android.app.Application;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.xqkj.baselibrary.base.BaseApplication;

import java.util.concurrent.ExecutionException;

public class GlideUtils {
    public static void showImg(ImageView imageView, String url){
        Glide.with(imageView.getContext()).load(url).into(imageView);
    }
    public static void showImg(ImageView imageView, int resourse){
        Glide.with(imageView.getContext()).load(resourse).into(imageView);
    }

    /**
     * 预加载
     */
    public static void preImg(String url){
        Glide.with(BaseApplication.mContext).load(url).preload();
    }
}
