package com.xqkj.projectbase.base;


import com.xqkj.projectbase.R;

public abstract class BaseActivity extends com.xqkj.baselibrary.base.BaseActivity {

    @Override
    public void baseInitView() {
        super.baseInitView();
        //返回键图片设置
        setBackImage(R.mipmap.icon_back);
    }

}
