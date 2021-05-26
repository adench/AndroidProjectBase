package com.xqkj.baselibrary.splashscreen;

import com.xqkj.baselibrary.banner.BannerData;

public class MessageClickBean {
    private BannerData data;
    private int position;

    public MessageClickBean(BannerData data, int position) {
        this.data = data;
        this.position = position;
    }

    public BannerData getData() {
        return data;
    }

    public void setData(BannerData data) {
        this.data = data;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}
