package com.xqkj.baselibrary.maintab;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.xqkj.baselibrary.base.BaseActivity;

import java.util.ArrayList;

public class MainTabHelper {
    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();
    private BaseActivity activity;
    public MainTabHelper(BaseActivity activity){
        this.activity = activity;
    }
    public void init(final ViewPager mViewPager, CommonTabLayout tablayout, String[] mTitles, int[] mIconSelectIds, int[] mIconUnselectIds, ArrayList<Fragment> fragments){
        for (int i = 0; i < mTitles.length; i++) {
            mTabEntities.add(new TabEntity(mTitles[i], mIconSelectIds[i], mIconUnselectIds[i]));
        }
        mViewPager.setAdapter(new TabPagerAdapter(activity.getSupportFragmentManager(),mTitles,fragments));
        tablayout.setTabData(mTabEntities);
        tablayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                mViewPager.setCurrentItem(position);
            }

            @Override
            public void onTabReselect(int position) {
            }
        });

        mViewPager.setCurrentItem(0);
    }
}
