package com.xqkj.baselibrary.net;

public abstract class HttpCallBack {
    public abstract void success(Object object);
    public abstract void failed(String code, String info);
    public void invalidLogin(){}//登录失效
    public void befor(){}
    public void after(){}
}
