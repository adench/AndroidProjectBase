package com.xqkj.projectbase.activity;

import android.view.View;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;

import com.flyco.tablayout.CommonTabLayout;
import com.xqkj.baselibrary.maintab.MainTabHelper;
import com.xqkj.baselibrary.utils.ToastUtil;
import com.xqkj.baselibrary.utils.UIHelper;
import com.xqkj.baselibrary.weight.NoScrollViewPager;
import com.xqkj.projectbase.R;
import com.xqkj.projectbase.base.BaseActivity;
import com.xqkj.projectbase.fragment.HomeFragment;
import com.xqkj.projectbase.helper.WellComeHelper;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {
    @BindView(R.id.tablayout)
    CommonTabLayout tablayout;
    @BindView(R.id.viewpager)
    NoScrollViewPager mViewPager;
    @BindView(R.id.iv_add)
    ImageView iv_add;

    private String[] mTitles = {"首页","社区","","消息", "我的"};
    private int[] mIconUnselectIds = {
            R.mipmap.icon_home_f, R.mipmap.icon_community_f, 0,R.mipmap.icon_message_f, R.mipmap.icon_mine_f};
    private int[] mIconSelectIds = {
            R.mipmap.icon_home_t, R.mipmap.icon_community_t, 0,R.mipmap.icon_message_t, R.mipmap.icon_mine_t};
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

    @OnClick({R.id.iv_add})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.iv_add:
                UIHelper.showCommonBundleActivity(this,CenterAddActivity.class);
                break;
        }
    }


    public ArrayList<Fragment> getFragments(){
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new HomeFragment());
        fragments.add(new HomeFragment());
        fragments.add(new HomeFragment());
        fragments.add(new HomeFragment());
        fragments.add(new HomeFragment());
        return fragments;
    }


    @Override
    protected void initData() {

    }

}
