package com.xqkj.projectbase.helper;

import android.content.Context;

import com.xqkj.baselibrary.banner.BannerData;
import com.xqkj.baselibrary.splashscreen.SplashScreenHelper;
import com.xqkj.baselibrary.web.WebHelper;

import java.util.ArrayList;
import java.util.List;

public class WellComeHelper extends SplashScreenHelper {
    private Context mContext;
    public WellComeHelper(Context context) {
        super(context);
        this.mContext = context;
    }

    @Override
    protected int getCoutTime() {
        ///设置跳过时间 默认5s
        return super.getCoutTime();
    }

    @Override
    protected void initData() {
        List<BannerData> bannerData = new ArrayList<>();
        bannerData.add(new BannerData("http://pic346.nipic.com/file/20201126/32357757_184530234082_2.jpg",
                "启动广告页","https://www.baidu.com/"));
        notifyShowImage(bannerData);
    }

    @Override
    protected void onBannerClick(BannerData data, int position) {
        WebHelper.show(mContext,data.getWebTitle(),data.getWebUrl());
    }


}
