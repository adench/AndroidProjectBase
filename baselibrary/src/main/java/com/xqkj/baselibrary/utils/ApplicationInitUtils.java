package com.xqkj.baselibrary.utils;

import android.content.Context;
import android.text.TextUtils;

import com.tencent.bugly.Bugly;
import com.tencent.bugly.crashreport.CrashReport;

public class ApplicationInitUtils {

    /**
     * 设置bugly
     * @param context
     * @param appid
     * @param userId
     */
    public static void bugly(Context context, String appid, String userId){
        // 获取当前包名
        String packageName = context.getPackageName();
        // 获取当前进程名
        String processName = DeviceUtils.getProcessName(android.os.Process.myPid());
        // 设置是否为上报进程
        CrashReport.UserStrategy strategy = new CrashReport.UserStrategy(context);
        strategy.setUploadProcess(processName == null || processName.equals(packageName));
        // 初始化Bugly
//        CrashReport.initCrashReport(context, appid, true, strategy);
        Bugly.init(context, appid, true,strategy);
        if(!TextUtils.isEmpty(userId)) {
            CrashReport.setUserId(userId);
        }
    }
}
