package com.xqkj.projectbase.activity;

import android.view.View;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;

import com.flyco.tablayout.CommonTabLayout;
import com.xqkj.baselibrary.maintab.MainTabHelper;
import com.xqkj.baselibrary.maintab.TabEntity;
import com.xqkj.baselibrary.utils.ToastUtil;
import com.xqkj.baselibrary.utils.UIHelper;
import com.xqkj.baselibrary.weight.MainTabView;
import com.xqkj.baselibrary.weight.NoScrollViewPager;
import com.xqkj.projectbase.R;
import com.xqkj.projectbase.base.BaseActivity;
import com.xqkj.projectbase.fragment.HomeFragment;
import com.xqkj.projectbase.helper.WellComeHelper;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {
    @BindView(R.id.iv_add)
    ImageView iv_add;
    @BindView(R.id.main_tab_view)
    MainTabView main_tab_view;

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
        main_tab_view.addTab(new TabEntity("首页", R.mipmap.icon_home_t, R.mipmap.icon_home_f, new HomeFragment()))
                .addTab(new TabEntity("社区", R.mipmap.icon_community_t, R.mipmap.icon_community_f, new HomeFragment()))
                .addTab(new TabEntity("", 0, 0, new HomeFragment()))
                .addTab(new TabEntity("消息", R.mipmap.icon_message_t, R.mipmap.icon_message_f, new HomeFragment()))
                .addTab(new TabEntity("我的", R.mipmap.icon_mine_t, R.mipmap.icon_mine_f, new HomeFragment()))
                .setFragmentManager(getSupportFragmentManager())
                .build();
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

    }

}
