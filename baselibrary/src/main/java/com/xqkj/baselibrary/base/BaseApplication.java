package com.xqkj.baselibrary.base;

import android.app.Application;
import android.content.Context;
import android.util.DisplayMetrics;
import android.util.Log;

import androidx.multidex.MultiDex;

import com.tencent.bugly.beta.Beta;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.xqkj.baselibrary.utils.ApplicationInitUtils;

public class BaseApplication extends Application {
    public static Context mContext;
    public static int widthPixels, heightPixels;
    public static IWXAPI wxapi;
    public static String WECHAT_APP_ID;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();

        //获取屏幕宽高
        DisplayMetrics dm = this.getResources().getDisplayMetrics();
        widthPixels = dm.widthPixels;
        heightPixels = dm.heightPixels;

        //设置bugly
        String[] bugly = bugly();
        if (bugly != null && bugly.length > 0) {
            String appid = bugly[0];
            String userId = "";
            if (bugly.length >= 2) {
                userId = bugly[1];
            }
            ApplicationInitUtils.bugly(mContext, appid, userId);

        }
        Log.e("===isUpdateApp", isUpdateApp() + "");
        Beta.autoCheckUpgrade = isUpdateApp();

        //微信
        WECHAT_APP_ID = getWechatAppId();
        wxapi = WXAPIFactory.createWXAPI(this, WECHAT_APP_ID, true);
        wxapi.registerApp(WECHAT_APP_ID);
    }


    public String[] bugly() {
        return null;
    }

    public boolean isUpdateApp() {
        return false;
    }

    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(newBase);
        MultiDex.install(this);
    }

    protected String getWechatAppId() {
        return "";
    }
}
