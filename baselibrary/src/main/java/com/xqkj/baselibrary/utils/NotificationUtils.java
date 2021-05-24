package com.xqkj.baselibrary.utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;

import androidx.core.app.NotificationManagerCompat;

import com.afollestad.materialdialogs.MaterialDialog;
import com.xqkj.baselibrary.listener.DialogCommitListener;

public class NotificationUtils {
    public static void setNotification(final Context context) {
        if (isNotificationEnabled(context)) return;
        MaterialDialog dialog = DialogHelper.showContentDialog(context, "开启通知", "开启通知后，方便接到消息通知", new DialogCommitListener() {
            @Override
            public void commit() {
                NotificationUtils.gotoSetNotification(context);
            }
        });
    }

    public static boolean isNotificationEnabled(Context context) {
        return NotificationManagerCompat.from(context).areNotificationsEnabled();
//        return (JPushInterface.isNotificationEnabled(context) == 1) ? true : false;
    }

    //跳转设置权限
    private static void gotoSetNotification(Context context) {
        Intent intent = new Intent();
        if (Build.VERSION.SDK_INT >= 26) {
            // android 8.0引导
            intent.setAction("android.settings.APP_NOTIFICATION_SETTINGS");
            intent.putExtra("android.provider.extra.APP_PACKAGE", context.getPackageName());
        } else if (Build.VERSION.SDK_INT >= 21) {
            // android 5.0-7.0
            intent.setAction("android.settings.APP_NOTIFICATION_SETTINGS");
            intent.putExtra("app_package", context.getPackageName());
            intent.putExtra("app_uid", context.getApplicationInfo().uid);
        } else {
            // 其他
            intent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
            intent.setData(Uri.fromParts("package", context.getPackageName(), null));
        }
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }
}
