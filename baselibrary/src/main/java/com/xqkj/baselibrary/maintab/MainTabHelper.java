package com.xqkj.baselibrary.maintab;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;

import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.xqkj.baselibrary.utils.DataUtils;

import java.util.ArrayList;
import java.util.List;

public class MainTabHelper {
    private FragmentManager fragmentManager;

    public MainTabHelper(FragmentManager fragmentManager) {
        this.fragmentManager = fragmentManager;
    }

    public void init(final ViewPager mViewPager, CommonTabLayout tablayout,
                     ArrayList<TabEntity> mTabEntities,
                      int textSelectColor, int textDefaultColor) {
        List<String> mTitles = new ArrayList<>();
        ArrayList<Fragment> fragments = new ArrayList<>();

        for(TabEntity tabEntity : mTabEntities){
            mTitles.add(tabEntity.getTabTitle());
            fragments.add(tabEntity.getFragment());
        }

        mViewPager.setAdapter(new TabPagerAdapter(fragmentManager, DataUtils.listToArrayString(mTitles), fragments));
        ArrayList<CustomTabEntity> tabData = new ArrayList<>();
        tabData.addAll(mTabEntities);
        tablayout.setTabData(tabData);
        if (textSelectColor > 0) {
            tablayout.setTextSelectColor(textSelectColor);
        }
        if (textDefaultColor > 0) {
            tablayout.setTextUnselectColor(textDefaultColor);
        }
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
