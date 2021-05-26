package com.xqkj.baselibrary.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.lifecycle.LifecycleOwner;

import com.xqkj.baselibrary.banner.BannerData;
import com.xqkj.baselibrary.banner.BannerListener;
import com.xqkj.baselibrary.utils.GlideUtils;
import com.youth.banner.Banner;
import com.youth.banner.adapter.BannerImageAdapter;
import com.youth.banner.holder.BannerImageHolder;
import com.youth.banner.indicator.CircleIndicator;
import com.youth.banner.listener.OnBannerListener;

import java.util.List;

/**
 * 轮播图
 * https://github.com/youth5201314/banner
 */
public class BannerView extends RelativeLayout {
    private Banner banner;
    private LifecycleOwner lifecycleOwner;
    private boolean isSingleImg = true;//是否纯图
    private BannerListener onBannerListener;

    public BannerView(Context context) {
        super(context);
        initView();
    }

    public BannerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public BannerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        banner = new Banner(getContext());
        this.addView(banner, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
    }

    public BannerView setLifecycleObserver(LifecycleOwner lifecycleOwner) {
        this.lifecycleOwner = lifecycleOwner;
        return this;
    }

    public BannerView setIsSingleImg(boolean isSingleImg) {
        this.isSingleImg = isSingleImg;
        return this;
    }

    public BannerView setOnBannerListener(BannerListener onBannerListener){
        this.onBannerListener = onBannerListener;
        return this;
    }



    public void create() {
        if (isSingleImg)
            singlePictureBanner();
    }

    //单纯图片轮播
    private void singlePictureBanner() {
        this.banner.setAdapter(new BannerImageAdapter<BannerData>(null) {
            @Override
            public void onBindView(BannerImageHolder holder, BannerData data, int position, int size) {
                if (data.isNetData()) {
                    GlideUtils.showCircleConnerImg(holder.imageView, data.getUrl(), 0);
                } else {
                    GlideUtils.showCircleConnerImg(holder.imageView, data.getDrawableRes(), 0);
                }
            }
        })
                .addBannerLifecycleObserver(lifecycleOwner)//添加生命周期观察者
                .setIndicator(new CircleIndicator(getContext()))
                .setOnBannerListener(new OnBannerListener() {
                    @Override
                    public void OnBannerClick(Object data, int position) {
                        if(onBannerListener != null)
                            onBannerListener.onBannerClick((BannerData) data,position);
                    }
                });
    }

    public void setDatas(List<BannerData> data) {
        this.banner.setDatas(data);
    }

    private void useBanner() {
        //--------------------------简单使用-------------------------------
//        banner.setAdapter(new BannerExampleAdapter(DataBean.getTestData()))
//                .setIndicator(new CircleIndicator(mContext));

//        //—————————————————————————如果你想偷懒，而又只是图片轮播————————————————————————
//        Banner banner = this.banner.setAdapter(new BannerImageAdapter<String>(DataBean.getTestData3()) {
//            @Override
//            public void onBindView(BannerImageHolder holder, DataBean data, int position, int size) {
//                //图片加载自己实现
//                Glide.with(holder.itemView)
//                        .load(data.imageUrl)
//                        .apply(RequestOptions.bitmapTransform(new RoundedCorners(30)))
//                        .into(holder.imageView);
//            }
//        })
//                .addBannerLifecycleObserver(this)//添加生命周期观察者
//                .setIndicator(new CircleIndicator(this));
    }


    public void setTime(){
//        banner.setScrollTime()
    }

    /**
     * banner_loop_time	integer	轮播间隔时间，默认3000
     * banner_auto_loop	boolean	是否自动轮播，默认true
     * banner_infinite_loop	boolean	是否支持无限循环（即首尾直接过渡），默认true
     * banner_orientation	enum	轮播方向：horizontal（默认） or vertical
     * banner_radius	dimension	banner圆角半径，默认0（不绘制圆角）
     * banner_indicator_normal_width	dimension	指示器默认的宽度，默认5dp （对RoundLinesIndicator无效）
     * banner_indicator_selected_width	dimension	指示器选中的宽度，默认7dp
     * banner_indicator_normal_color	color	指示器默认颜色，默认0x88ffffff
     * banner_indicator_selected_color	color	指示器选中颜色，默认0x88000000
     * banner_indicator_space	dimension	指示器之间的间距，默认5dp （对RoundLinesIndicator无效）
     * banner_indicator_gravity	dimension	指示器位置，默认center
     * banner_indicator_margin	dimension	指示器的margin,默认5dp，不能和下面的同时使用
     * banner_indicator_marginLeft	dimension	指示器左边的margin
     * banner_indicator_marginTop	dimension	指示器上边的margin
     * banner_indicator_marginRight	dimension	指示器右边的margin
     * banner_indicator_marginBottom	dimension	指示器下边的margin
     * banner_indicator_height	dimension	指示器高度（对CircleIndicator无效）
     * banner_indicator_radius	dimension	指示器圆角（对CircleIndicator无效）
     * banner_round_top_left	boolean	设置要绘制的banner圆角方向（如果都不设置默认全部）
     * banner_round_top_right	boolean	设置要绘制的banner圆角方向（如果都不设置默认全部）
     * banner_round_bottom_left	boolean	设置要绘制的banner圆角方向（如果都不设置默认全部）
     * banner_round_bottom_right	boolean	设置要绘制的banner圆角方向（如果都不设置默认全部）
     */
}
