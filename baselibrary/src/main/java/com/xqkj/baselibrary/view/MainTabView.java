package com.xqkj.baselibrary.view;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;

import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;

import com.flyco.tablayout.CommonTabLayout;
import com.xqkj.baselibrary.R;
import com.xqkj.baselibrary.R2;
import com.xqkj.baselibrary.maintab.MainTabHelper;
import com.xqkj.baselibrary.maintab.TabBaseEntity;
import com.xqkj.baselibrary.utils.LayoutInflaterUtils;
import com.xqkj.baselibrary.weight.NoScrollViewPager;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainTabView extends RelativeLayout {
    public MainTabView(Context context) {
        super(context);
        initView();
    }

    public MainTabView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public MainTabView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    @BindView(R2.id.tablayout)
    CommonTabLayout tablayout;
    @BindView(R2.id.viewpager)
    NoScrollViewPager mViewPager;
    @BindView(R2.id.shadow_line)
    View shadow_line;

    private FragmentManager fragmentManager;
    private int textSelectColor;
    private int textDefaultColor;
    private ArrayList<TabBaseEntity> mTabEntities = new ArrayList<>();
    private void initView(){
        View view = LayoutInflaterUtils.from(getContext(), R.layout.main_tab_layout);
        ButterKnife.bind(this,view);
        addView(view);
    }

    public void build() {
        new MainTabHelper(fragmentManager).init(mViewPager,tablayout,
                mTabEntities,
                textSelectColor,textDefaultColor);
    }

    public MainTabView addTab(TabBaseEntity tabEntity){
        mTabEntities.add(tabEntity);
        return this;
    }

    public MainTabView setFragmentManager(FragmentManager fragmentManager) {
        this.fragmentManager = fragmentManager;
        return this;
    }

    public MainTabView setTextColor(int textSelectColor,int textDefaultColor) {
        this.textSelectColor = textSelectColor;
        this.textDefaultColor = textDefaultColor;
        return this;
    }

    public CommonTabLayout getTabLayout(){
        return tablayout;
    }

    public ViewPager getViewPager(){
        return mViewPager;
    }

    private void setShowLineColor(){
        if(textSelectColor != 0) {
            int[] colorsTwo = {textSelectColor,Color.parseColor("#00FFFFFF")};
            GradientDrawable mGradientDrawable = (GradientDrawable) shadow_line.getBackground();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                mGradientDrawable.setColors(colorsTwo);
            }
        }
    }

    /**
     * 设置tab
     * @param position
     */
    public MainTabView setCurrentTab(int position){
        tablayout.setCurrentTab(position);
        mViewPager.setCurrentItem(position);
        return this;
    }

}
