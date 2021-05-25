package com.xqkj.baselibrary.utils;

import android.text.TextUtils;
import android.view.Gravity;
import android.widget.Toast;

import com.xqkj.baselibrary.base.BaseApplication;

public class ToastUtil {

    public static void showToast(String showStr){
        showCenterToast(showStr);
    }

    public static void showCenterToast(String showStr){
        if(TextUtils.isEmpty(showStr))return;
        Toast toast = Toast.makeText(BaseApplication.mContext, showStr, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER,0,0);
        toast.show();
    }

    public static void showBottomToast(String showStr){
        if(TextUtils.isEmpty(showStr))return;
        Toast toast = Toast.makeText(BaseApplication.mContext, showStr, Toast.LENGTH_SHORT);
        toast.show();
    }

}
