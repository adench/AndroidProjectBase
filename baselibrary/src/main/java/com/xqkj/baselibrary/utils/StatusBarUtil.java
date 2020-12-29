package com.xqkj.baselibrary.utils;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.gyf.immersionbar.ImmersionBar;

public class StatusBarUtil {
    public static void setHeight(Context context, View view){
        ViewGroup.LayoutParams layoutParams = (ViewGroup.LayoutParams) view.getLayoutParams();
        layoutParams.height = ImmersionBar.getStatusBarHeight((Activity) context);
        view.setLayoutParams(layoutParams);
    }
}
