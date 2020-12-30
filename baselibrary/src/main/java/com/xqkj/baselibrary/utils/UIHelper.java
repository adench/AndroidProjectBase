package com.xqkj.baselibrary.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.xqkj.baselibrary.web.WebActivity;

public class UIHelper {
    public static void showCommonBundleActivity(Context context, Bundle bundle, Class<?> mclass) {
        Intent intent = new Intent(context, mclass);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("bundle", bundle);
        context.startActivity(intent);
    }

    public static void showCommonBundleActivity(Context context, Class<?> mclass) {
        Intent intent = new Intent(context, mclass);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    public static void showCommonBundleActivity(Context context, Class<?> mclass, int requestCode){
        showCommonBundleActivity(context,null,mclass,requestCode);
    }
    public static void showCommonBundleActivity(Context context, Bundle bundle, Class<?> mclass, int requestCode) {
        Intent intent = new Intent(context, mclass);
        intent.putExtra("bundle", bundle);
        ((Activity)context).startActivityForResult(intent,requestCode);
    }

    public static void showWebActivity(Context context,String title,String url){
        Bundle bundle = new Bundle();
        bundle.putString("title",title);
        bundle.putString("url",url);
        showCommonBundleActivity(context,bundle, WebActivity.class);
    }

}
