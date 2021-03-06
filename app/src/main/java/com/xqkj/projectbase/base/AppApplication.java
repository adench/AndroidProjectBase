package com.xqkj.projectbase.base;

import com.xqkj.baselibrary.base.BaseApplication;
import com.xqkj.baselibrary.net.HttpHeader;
import com.xqkj.baselibrary.net.HttpSetting;

import java.util.Map;

public class AppApplication extends BaseApplication {
    @Override
    public void onCreate() {
        super.onCreate();

        //配置请求成功状态码
        HttpSetting.SUCCESS_CODE = "200";
        //基础类
        HttpSetting.BASE_BEAN = HttpBaseBean.class;
        //添加请求头
        HttpSetting.headers = new HttpHeader() {
            @Override
            public Map<String, String> getParams() {
                return null;
            }
        };
    }

    /**
     * 设置bugly
     * 第一个参数 appid
     * 第二个参数 用户id
     * @return
     */
    @Override
    public String[] bugly() {
        return new String[]{"e31bb68e36"};
    }

    /**
     * 版本更新 true更新
     * @return
     */
    @Override
    public boolean isUpdateApp() {
        return false;
    }

    //微信appid
    @Override
    protected String getWechatAppId() {
        return "wx1d9518066a2bde92";
    }
}
