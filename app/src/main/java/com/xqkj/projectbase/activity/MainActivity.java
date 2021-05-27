package com.xqkj.projectbase.activity;

import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;


import com.xqkj.baselibrary.maintab.TabBaseEntity;
import com.xqkj.baselibrary.net.BaseBean;
import com.xqkj.baselibrary.net.HttpCallBack;
import com.xqkj.baselibrary.net.HttpRequest;
import com.xqkj.baselibrary.utils.UIHelper;
import com.xqkj.baselibrary.view.MainTabView;
import com.xqkj.projectbase.R;
import com.xqkj.projectbase.base.BaseActivity;
import com.xqkj.projectbase.base.HttpBaseBean;
import com.xqkj.projectbase.config.ApiUrl;
import com.xqkj.projectbase.fragment.HomeFragment;
import com.xqkj.projectbase.helper.WellComeHelper;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {
    @BindView(R.id.iv_add)
    ImageView iv_add;
    @BindView(R.id.main_tab_view)
    MainTabView mainTabView;

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
        mainTabView.addTab(new TabBaseEntity("首页", R.mipmap.icon_home_t, R.mipmap.icon_home_f, new HomeFragment()))
                .addTab(new TabBaseEntity("社区", R.mipmap.icon_community_t, R.mipmap.icon_community_f, new HomeFragment()))
                .addTab(new TabBaseEntity("", 0, 0, new HomeFragment()))
                .addTab(new TabBaseEntity("消息", R.mipmap.icon_message_t, R.mipmap.icon_message_f, new HomeFragment()))
                .addTab(new TabBaseEntity("我的", R.mipmap.icon_mine_t, R.mipmap.icon_mine_f, new HomeFragment()))
                .setFragmentManager(getSupportFragmentManager())
                .setTextColor(0,0)
                .build();

        HttpBaseBean bean = new HttpBaseBean();
        bean.setCode("200");
        bean.setMsg("haha");
        BaseBean baseBean = bean;
        Log.e("===***","code:"+baseBean.getCode());
    }

    @OnClick({R.id.iv_add})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_add:
                UIHelper.showCommonBundleActivity(this, CenterAddActivity.class);
                break;
        }
    }

    @Override
    protected void initData() {
        Map<String,String> map = new HashMap<>();
        map.put("platform","1");
        map.put("version","3.5.3");
        HttpRequest.init(this).get(ApiUrl.testUrl).setMap(map).excute(new HttpCallBack() {
            @Override
            public void success(Object object) {
                Log.e("===success","===");
            }

            @Override
            public void failed(String code, String info) {
                Log.e("===faild","code:"+code+"-info:"+info);
            }
        });


    }

}
