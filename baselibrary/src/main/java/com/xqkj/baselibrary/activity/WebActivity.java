package com.xqkj.baselibrary.activity;

import android.os.Bundle;
import android.widget.LinearLayout;

import com.just.agentweb.AgentWeb;
import com.xqkj.baselibrary.R;
import com.xqkj.baselibrary.R2;
import com.xqkj.baselibrary.base.BaseActivity;

import butterknife.BindView;

public class WebActivity extends BaseActivity {
    @BindView(R2.id.ll_web)
    LinearLayout ll_web;

    private AgentWeb mAgentWeb;
    private String title,url;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_web;
    }

    @Override
    protected boolean isShowBacking() {
        return true;
    }

    @Override
    protected void initView() {
        Bundle bundle = getBundle();
        if(bundle != null){
            title = bundle.getString("title");
            url = bundle.getString("url");
        }

        setHeaderTitle(title);

        mAgentWeb = AgentWeb.with(this)
                .setAgentWebParent((LinearLayout) ll_web, new LinearLayout.LayoutParams(-1, -1))
                .useDefaultIndicator()
                .createAgentWeb()
                .ready()
                .go(url);
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onBackPressed() {
        if (!mAgentWeb.back()){
            super.onBackPressed();
        }else{
            mAgentWeb.back();
        }
    }

    @Override
    protected void onPause() {
        mAgentWeb.getWebLifeCycle().onPause();
        super.onPause();

    }

    @Override
    protected void onResume() {
        mAgentWeb.getWebLifeCycle().onResume();
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        mAgentWeb.getWebLifeCycle().onDestroy();
        super.onDestroy();
    }
}
