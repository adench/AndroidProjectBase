package com.xqkj.baselibrary.view;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
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
import com.youth.banner.indicator.RectangleIndicator;
import com.youth.banner.indicator.RoundLinesIndicator;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.util.BannerUtils;

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
    private float radius = 0f;

    public static int INDICATOR_STYLE_CIRCLE = 1;
    public static int INDICATOR_STYLE_RECTANGLE = 2;
    public static int INDICATOR_STYLE_ROUNDLINES = 3;

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
        banner.setIndicator(new CircleIndicator(getContext()));
        this.addView(banner, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
    }

    public BannerView initRectangleIndicator() {
        //indicator 默认设置
        banner.setIndicatorNormalColor(Color.parseColor("#33000000"));
        banner.setIndicatorSelectedColor(Color.parseColor("#ffffff"));
        banner.setIndicatorSpace((int) BannerUtils.dp2px(5));
        banner.setIndicatorRadius(15);
        banner.setIndicatorHeight(BannerUtils.dp2px(4));
        banner.setIndicatorNormalWidth(BannerUtils.dp2px(10));
        banner.setIndicatorSelectedWidth(BannerUtils.dp2px(12));
        return this;
    }

    private BannerView initCircleIndicator() {
        banner.setIndicatorNormalColor(Color.parseColor("#33000000"));
        banner.setIndicatorSelectedColor(Color.parseColor("#ffffff"));
        banner.setIndicatorSpace((int) BannerUtils.dp2px(5));
        banner.setIndicatorNormalWidth(BannerUtils.dp2px(7));
        banner.setIndicatorSelectedWidth(BannerUtils.dp2px(8));
        return this;
    }

    public BannerView setLifecycleObserver(LifecycleOwner lifecycleOwner) {
        this.lifecycleOwner = lifecycleOwner;
        return this;
    }

    public BannerView setIsSingleImg(boolean isSingleImg) {
        this.isSingleImg = isSingleImg;
        return this;
    }

    public BannerView setOnBannerListener(BannerListener onBannerListener) {
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
                    GlideUtils.showCircleConnerImg(holder.imageView, data.getUrl(), (int) radius);
                } else {
                    GlideUtils.showCircleConnerImg(holder.imageView, data.getDrawableRes(), (int) radius);
                }
            }
        })
                .addBannerLifecycleObserver(lifecycleOwner)//添加生命周期观察者
                .setOnBannerListener(new OnBannerListener() {
                    @Override
                    public void OnBannerClick(Object data, int position) {
                        if (onBannerListener != null)
                            onBannerListener.onBannerClick((BannerData) data, position);
                    }
                });
        //需放到adapter后
        initCircleIndicator();
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


    /**
     * 轮播间隔时间
     *
     * @param time 默认3000
     * @return
     */
    public BannerView setLoopTime(int time) {
        banner.setLoopTime(time);
        return this;
    }

    /**
     * banner圆角半径
     *
     * @param radius 默认0
     * @return
     */
    public BannerView setRadius(float radius) {
        this.radius = BannerUtils.dp2px(radius);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            banner.setBannerRound2(BannerUtils.dp2px(radius));
        }else{
            banner.setBannerRound(BannerUtils.dp2px(radius));
        }
        return this;
    }

    /**
     * 设置指示器样式
     *
     * @param type 1.圆形 2.圆角长形 3.线形
     * @return
     */
    public BannerView setIndicatorStyle(int type) {
        if (type == INDICATOR_STYLE_CIRCLE) {
            banner.setIndicator(new CircleIndicator(getContext()));
        } else if (type == INDICATOR_STYLE_RECTANGLE) {
            banner.setIndicator(new RectangleIndicator(getContext()));
        } else if (type == INDICATOR_STYLE_ROUNDLINES) {
            banner.setIndicator(new RoundLinesIndicator(getContext()));
        }
        return this;
    }

    /**
     * 设置指示器颜色
     *
     * @param selectColor 选择时颜色
     * @param normalColor 默认颜色
     * @return
     */
    public BannerView setIndicatorColor(int selectColor, int normalColor) {
        banner.setIndicatorNormalColor(normalColor)
                .setIndicatorSelectedColor(selectColor);
        return this;
    }

    /**
     * 设置指示器宽度
     *
     * @param selectWidth 选中宽
     * @param normalWidth 默认宽
     * @return
     */
    public BannerView setIndicatorWidth(int selectWidth, int normalWidth) {
        banner.setIndicatorNormalWidth(BannerUtils.dp2px(normalWidth))
                .setIndicatorSelectedWidth(BannerUtils.dp2px(selectWidth));
        return this;
    }

    /**
     * 长形指示器 设置弧度 高度
     *
     * @param radius 弧度
     * @param height 高度
     * @return
     */
    public BannerView setIndicatorRectangle(int radius, int height) {
        banner.setIndicatorRadius(radius)
                .setIndicatorHeight(BannerUtils.dp2px(height));
        return this;
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
