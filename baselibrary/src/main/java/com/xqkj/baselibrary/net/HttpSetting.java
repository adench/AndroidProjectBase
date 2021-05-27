package com.xqkj.baselibrary.net;

import com.lzy.okgo.model.HttpHeaders;

public class HttpSetting {
    //成功的状态码
    public static String SUCCESS_CODE = "200";

    //请求头
    public static HttpHeaders headers = new HttpHeaders();

    //登录失败状态码
    public static String[] LOGIN_FAILD_CODE = {};

    public static Class<?> BASE_BEAN = null;

}
