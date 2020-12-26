package com.xqkj.baselibrary.base;

import android.app.Application;
import android.util.DisplayMetrics;

public class BaseApplication extends Application {
    public static BaseApplication mContext;
    public static int widthPixels, heightPixels;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;

        //获取屏幕宽高
        DisplayMetrics dm = this.getResources().getDisplayMetrics();
        widthPixels = dm.widthPixels;
        heightPixels = dm.heightPixels;
    }
}
