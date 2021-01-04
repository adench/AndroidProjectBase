package com.xqkj.projectbase.base;


import com.xqkj.baselibrary.utils.ToastUtil;
import com.xqkj.projectbase.R;

public abstract class BaseActivity extends com.xqkj.baselibrary.base.BaseActivity {

    @Override
    public void baseInitView() {
        super.baseInitView();
        //返回键图片设置
        setBackImage(R.mipmap.icon_back);
    }

    //是否退出app
    protected boolean isExitApp(){
        return false;
    }
    private long current_time = 0;
    @Override
    public void onBackPressed() {
        if(isExitApp()) {
            if (current_time > 0 && (System.currentTimeMillis() - current_time) <= 3000) {
                super.onBackPressed();
            } else {
                ToastUtil.showToast("再按一次退出");
                current_time = System.currentTimeMillis();
            }
        }else{
            super.onBackPressed();
        }
    }
}
