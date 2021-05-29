package com.xqkj.baselibrary.utils;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import androidx.fragment.app.FragmentActivity;

import com.gyf.immersionbar.ImmersionBar;

public class StatusBarUtil {

    //沉浸式状态栏
    public static void setImmersionBar(Context context){
        ImmersionBar.with((FragmentActivity)context)
                .statusBarDarkFont(false, 0.2f)
                .init();
    }

    public static void setImmersionBar(Context context,boolean isDark){
        ImmersionBar.with((FragmentActivity)context)
                .statusBarDarkFont(isDark, 0.2f)
                .init();
    }

    //设置状态栏颜色
    public static void setStatusBarColor(Context context,int colorRes){
        ImmersionBar.with((FragmentActivity)context)
                .statusBarDarkFont(true, 0.2f)
                .fitsSystemWindows(true)
                .statusBarColor(colorRes)
                .keyboardEnable(false)
                .init();
    }

    //设置状态栏高度
    public static void setHeight(Context context, View view){
        ViewGroup.LayoutParams layoutParams = (ViewGroup.LayoutParams) view.getLayoutParams();
        layoutParams.height = ImmersionBar.getStatusBarHeight((Activity) context);
        view.setLayoutParams(layoutParams);
    }

    //获取状态栏高度
    public static int getStatusBarHeight(Context context){
        return ImmersionBar.getStatusBarHeight((Activity) context);
    }

    // 隐藏状态栏
    public static void hideStatusBar(Activity activity) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
            activity.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
        } else {
            View decorView = activity.getWindow().getDecorView();
            activity.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
            int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_IMMERSIVE | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
            decorView.setSystemUiVisibility(uiOptions);
        }
    }

    // 恢复为不全屏状态
    public static void setDecorVisible(Activity activity) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
            activity.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        } else {
            View decorView = activity.getWindow().getDecorView();
            activity.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
            int uiOptions = View.SYSTEM_UI_FLAG_VISIBLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN |
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
            decorView.setSystemUiVisibility(uiOptions);
        }
    }


    public static void hideNavigationBar(Activity activity){
        int uiOptions=activity.getWindow().getDecorView().getSystemUiVisibility();
        activity.getWindow().getDecorView().setSystemUiVisibility(uiOptions|View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
    }

}
