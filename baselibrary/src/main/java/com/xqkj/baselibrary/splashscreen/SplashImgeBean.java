package com.xqkj.baselibrary.splashscreen;

public class SplashImgeBean {
    private boolean isNet;
    private int res;
    private String url;

    public SplashImgeBean(int res){
        this.isNet = false;
        this.res = res;
    }
    public SplashImgeBean(String url){
        this.isNet = true;
        this.url = url;
    }

    public boolean isNet() {
        return isNet;
    }

    public void setNet(boolean net) {
        isNet = net;
    }

    public int getRes() {
        return res;
    }

    public void setRes(int res) {
        this.res = res;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
