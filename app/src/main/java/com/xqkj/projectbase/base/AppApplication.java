package com.xqkj.projectbase.base;

import com.lzy.okgo.model.HttpHeaders;
import com.xqkj.baselibrary.base.BaseApplication;
import com.xqkj.baselibrary.net.HttpSetting;

public class AppApplication extends BaseApplication {
    @Override
    public void onCreate() {
        super.onCreate();

        //配置请求头
        HttpHeaders headers = HttpSetting.headers;
        HttpSetting.headers = headers;
        //配置请求成功状态码
        HttpSetting.SUCCESS_CODE = "";

    }

    @Override
    public String[] bugly() {
        return new String[]{""};
    }
}
