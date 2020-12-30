package com.xqkj.baselibrary.web;

import android.content.Context;

import com.xqkj.baselibrary.utils.UIHelper;

public class WebHelper {
    public static void show(Context context,String title,String url){
        UIHelper.showWebActivity(context,title,url);
    }
}
