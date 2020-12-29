package com.xqkj.baselibrary.utils;

import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class GlideUtils {
    public static void showImg(ImageView imageView, String url){
        Glide.with(imageView.getContext()).load(url).into(imageView);
    }
    public static void showImg(ImageView imageView, int resourse){
        Glide.with(imageView.getContext()).load(resourse).into(imageView);
    }
}
