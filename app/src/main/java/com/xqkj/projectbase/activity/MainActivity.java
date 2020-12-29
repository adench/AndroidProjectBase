package com.xqkj.projectbase.activity;


import android.os.Bundle;
import android.util.Log;

import com.xqkj.baselibrary.utils.UIHelper;
import com.xqkj.projectbase.R;
import com.xqkj.projectbase.base.BaseActivity;

public class MainActivity extends BaseActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected boolean isShowBacking() {
        return true;
    }

    @Override
    protected void initView() {
        setHeaderTitle("主页");
    }

    @Override
    protected void beforeContentView() {
        UIHelper.showCommonBundleActivity(this,WellComeActivity.class);
    }

    @Override
    protected void initData() {
        Log.e("-data-","=====");
    }

}
