package com.xqkj.baselibrary.net;


public class HttpSetting {
    //成功的状态码
    public static String SUCCESS_CODE = "200";

    //请求头
    public static HttpHeader headers = new HttpHeader();

    //登录失败状态码
    public static String[] LOGIN_FAILD_CODE = {};
    //登录过期回调
    public static HttpLoginInvalid invalidLogin;

    //基础数据类
    public static Class<?> BASE_BEAN = null;


}
