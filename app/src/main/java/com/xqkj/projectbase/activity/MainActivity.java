package com.xqkj.projectbase.activity;

import androidx.fragment.app.Fragment;

import com.flyco.tablayout.CommonTabLayout;
import com.xqkj.baselibrary.maintab.MainTabHelper;
import com.xqkj.baselibrary.weight.NoScrollViewPager;
import com.xqkj.projectbase.R;
import com.xqkj.projectbase.base.BaseActivity;
import com.xqkj.projectbase.fragment.HomeFragment;
import com.xqkj.projectbase.helper.WellComeHelper;

import java.util.ArrayList;

import butterknife.BindView;

public class MainActivity extends BaseActivity {
    @BindView(R.id.tablayout)
    CommonTabLayout tablayout;
    @BindView(R.id.viewpager)
    NoScrollViewPager mViewPager;

    private String[] mTitles = {"首页","消息", "我的"};
    private int[] mIconUnselectIds = {
            R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher};
    private int[] mIconSelectIds = {
            R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher};
    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected boolean isImmersionBar() {
        return true;
    }

    @Override
    protected boolean isExitApp() {
        return true;
    }

    @Override
    protected void beforeContentView() {
        //广告页
        new WellComeHelper(this).toIntent();
    }

    @Override
    protected void initView() {
        new MainTabHelper(this).init(mViewPager,tablayout,mTitles,mIconSelectIds,mIconUnselectIds,getFragments());
    }

    public ArrayList<Fragment> getFragments(){
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new HomeFragment());
        fragments.add(new HomeFragment());
        fragments.add(new HomeFragment());
        return fragments;
    }


    @Override
    protected void initData() {

    }

}
