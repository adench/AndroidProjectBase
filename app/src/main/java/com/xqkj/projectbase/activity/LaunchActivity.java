package com.xqkj.projectbase.activity;



import com.xqkj.baselibrary.Helper.LaunchHelper;
import com.xqkj.projectbase.R;

public class LaunchActivity extends LaunchHelper {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_launch;
    }

    @Override
    public Class<?> mainActivity() {
        return MainActivity.class;
    }
}
