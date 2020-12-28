package com.xqkj.projectbase.activity;


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
    protected void initData() {
        
    }
}
