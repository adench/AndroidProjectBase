package com.xqkj.projectbase.activity;

import com.xqkj.baselibrary.utils.UIHelper;
import com.xqkj.projectbase.R;
import com.xqkj.projectbase.base.BaseActivity;

public class MainActivity extends BaseActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected boolean isImmersionBar() {
        return true;
    }

    @Override
    protected void beforeContentView() {
        //广告页
        UIHelper.showCommonBundleActivity(this,WellComeActivity.class);
    }

    @Override
    protected void initView() {
    }



    @Override
    protected void initData() {

    }

}
