package com.xqkj.baselibrary.banner;

public class BannerData{
    private String url;
    private int drawableRes;
    private int viewType = 0;//0.图片 1.视频

    private boolean isNetdata = false;

    private String webUrl;//跳转url
    private String webTitle;//web标题

    private String advertId;//广告id
    private String condition;//跳转条件
    private String[] conditionContent;//条件内容

    public BannerData(){
    }

    public BannerData(String url){
        this(url,"","");
    }

    public BannerData(String url, String webTitle, String webUrl) {
        this(url,0,webTitle,webUrl);
    }

    public BannerData(String url, int viewType, String webTitle, String webUrl) {
        this.url = url;
        this.viewType = viewType;
        this.webUrl = webUrl;
        this.webTitle = webTitle;
        this.isNetdata = true;
    }

    public BannerData(int drawableRes){
        this(drawableRes,"","");
    }

    public BannerData(int drawableRes, String webTitle, String webUrl) {
        this(drawableRes,0,webTitle,webUrl);
    }

    public BannerData(int drawableRes, int viewType, String webTitle, String webUrl) {
        this.url = url;
        this.drawableRes = drawableRes;
        this.viewType = viewType;
        this.webUrl = webUrl;
        this.webTitle = webTitle;
        this.isNetdata = false;
    }

    public BannerData(String url, int viewType, String webTitle, String webUrl,String advertId,
                      String condition,String... conditionContent) {
        this(webUrl,viewType,webTitle,webUrl);
        this.advertId = advertId;
        this.condition = condition;
        this.conditionContent = conditionContent;
    }

    public BannerData(int drawableRes, int viewType, String webTitle, String webUrl,String advertId,
                      String condition,String... conditionContent) {
        this(webUrl,viewType,webTitle,webUrl);
        this.advertId = advertId;
        this.condition = condition;
        this.conditionContent = conditionContent;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getDrawableRes() {
        return drawableRes;
    }

    public void setDrawableRes(int drawableRes) {
        this.drawableRes = drawableRes;
    }

    public boolean isNetData() {
        return isNetdata;
    }

    public int getViewType() {
        return viewType;
    }

    public void setViewType(int viewType) {
        this.viewType = viewType;
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
