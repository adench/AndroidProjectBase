package com.xqkj.baselibrary.Helper;

import android.os.Handler;

import com.xqkj.baselibrary.base.BaseActivity;
import com.xqkj.baselibrary.utils.UIHelper;

public abstract class LaunchHelper extends BaseActivity {
    private Handler handler = new Handler();
    private int waitTime = 2000;

    @Override
    protected boolean isImmersionBar() {
        return true;
    }
    @Override
    protected void initView() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                UIHelper.showCommonBundleActivity(LaunchHelper.this,mainActivity());
                LaunchHelper.this.finish();
            }
        },waitTime());
    }

    @Override
    protected void initData() {
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacksAndMessages(null);
    }

    public abstract Class<?> mainActivity();
    public int waitTime(){return waitTime;}

}
