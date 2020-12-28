package com.xqkj.projectbase.activity;


import android.os.Handler;

import com.xqkj.baselibrary.base.BaseActivity;
import com.xqkj.baselibrary.utils.UIHelper;
import com.xqkj.projectbase.R;

public class LaunchActivity extends BaseActivity {

    private Handler handler = new Handler();
    private int waitTime = 3000;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_launch;
    }

    @Override
    protected void initView() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                UIHelper.showCommonBundleActivity(LaunchActivity.this,WellComeActivity.class);
            }
        },waitTime);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacksAndMessages(null);
    }
}
