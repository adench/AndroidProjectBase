package com.xqkj.projectbase.helper;

import android.content.Context;

import com.xqkj.baselibrary.net.HttpCallBack;
import com.xqkj.baselibrary.net.HttpRequest;
import com.xqkj.baselibrary.splashscreen.SplashScreenHelper;

public class WellComeHelper extends SplashScreenHelper {

    public WellComeHelper(Context context) {
        super(context);
        //设置跳过时间 默认5s
        setCoutTime(8);
    }

    @Override
    protected void initData() {
        super.initData();
        notifyShowImage("http://pic346.nipic.com/file/20201126/32357757_184530234082_2.jpg",
                "https://www.baidu.com/","百度官网");
    }
}
