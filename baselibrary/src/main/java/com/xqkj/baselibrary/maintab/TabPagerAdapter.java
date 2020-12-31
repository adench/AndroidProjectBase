package com.xqkj.baselibrary.maintab;


import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;


public class TabPagerAdapter extends FragmentPagerAdapter {
    private String[] mTitles;
    private ArrayList<Fragment> fragments;
    public TabPagerAdapter(FragmentManager fm, String[] mTitles, ArrayList<Fragment> fragments) {
        super(fm);
        this.mTitles = mTitles;
        this.fragments = fragments;
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles[position];
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }
}
