package com.xqkj.projectbase.fragment;

import android.text.TextUtils;
import android.view.View;

import com.xqkj.baselibrary.banner.BannerData;
import com.xqkj.baselibrary.banner.BannerListener;
import com.xqkj.baselibrary.base.BaseFragment;
import com.xqkj.baselibrary.view.BannerView;
import com.xqkj.baselibrary.web.WebHelper;
import com.xqkj.projectbase.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class HomeFragment extends BaseFragment implements BannerListener {
    @BindView(R.id.banner_view)
    BannerView banner;
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home_layout;
    }

    @Override
    protected void initView(View view) {
        banner.setLifecycleObserver(this)//banner生命周期管理
                .setLoopTime(5000)
                .setOnBannerListener(this)//点击
                .setIsSingleImg(true)//纯图
                .create();
        banner.setIndicatorStyle(BannerView.INDICATOR_STYLE_RECTANGLE)
                .initRectangleIndicator();
        List<BannerData> bannerList = new ArrayList<>();
        bannerList.add(new BannerData("http://pic346.nipic.com/file/20201126/32357757_184530234082_2.jpg","百度","https://www.baidu.com/"));
        bannerList.add(new BannerData("https://picnew15.photophoto.cn/20201203/zhongguofengniunianqidongyeh5shejitupian-39968250_1.jpg"));
        bannerList.add(new BannerData("http://pic346.nipic.com/file/20201126/32357757_184530234082_2.jpg","百度","https://www.baidu.com/"));
        bannerList.add(new BannerData("https://picnew15.photophoto.cn/20201203/zhongguofengniunianqidongyeh5shejitupian-39968250_1.jpg"));
        bannerList.add(new BannerData("http://pic346.nipic.com/file/20201126/32357757_184530234082_2.jpg","百度","https://www.baidu.com/"));
        bannerList.add(new BannerData("https://picnew15.photophoto.cn/20201203/zhongguofengniunianqidongyeh5shejitupian-39968250_1.jpg"));
        banner.setDatas(bannerList);

        banner.setOnBannerListener(new BannerListener() {
            @Override
            public void onBannerClick(BannerData data, int position) {
                if(!TextUtils.isEmpty(data.getWebUrl())){
                    WebHelper.show(getContext(),data.getWebTitle(),data.getWebUrl());
                }
            }
        });
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onBannerClick(BannerData data, int position) {
        //banner click
    }
}
