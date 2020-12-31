package com.xqkj.baselibrary.splashscreen;

public class SplashImgeBean {
    private boolean isNet;
    private int res;
    private String url;

    private String webUrl;
    private String webTitle;

    public SplashImgeBean(int res,String webUrl,String webTitle){
        this.isNet = false;
        this.res = res;
        this.webUrl = webUrl;
        this.webTitle = webTitle;
    }
    public SplashImgeBean(String url,String webUrl,String webTitle){
        this.isNet = true;
        this.url = url;
        this.webUrl = webUrl;
        this.webTitle = webTitle;
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

    public String getWebUrl() {
        return webUrl;
    }

    public void setWebUrl(String webUrl) {
        this.webUrl = webUrl;
    }

    public String getWebTitle() {
        return webTitle;
    }

    public void setWebTitle(String webTitle) {
        this.webTitle = webTitle;
    }
}
