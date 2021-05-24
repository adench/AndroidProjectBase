package com.xqkj.baselibrary.base;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.gyf.immersionbar.ImmersionBar;
import com.xqkj.baselibrary.R;
import com.xqkj.baselibrary.R2;
import com.xqkj.baselibrary.utils.ActivityManager;
import com.xqkj.baselibrary.utils.EventBusUtil;
import com.xqkj.baselibrary.utils.StatusBarUtil;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import butterknife.BindView;
import butterknife.ButterKnife;


public abstract class BaseActivity extends AppCompatActivity {
    private static final String TAG = BaseActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (Build.VERSION.SDK_INT == Build.VERSION_CODES.O && isTranslucentOrFloating()) {
            boolean result = fixOrientation();
        }
        super.onCreate(savedInstanceState);
        ActivityManager.getAppManager().addActivity(this);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);//竖屏
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);//键盘模式
        //加载view前操作
        beforeContentView();
        setContentView(R.layout.activity_base);
        LinearLayout llView = (LinearLayout) findViewById(R.id.base_view);//这两句勿动
        llView.addView(LayoutInflater.from(this).inflate(getLayoutId(), llView, false), ViewGroup.LayoutParams.MATCH_PARENT);
        //设置状态栏
        setStatusBar();
        //底部 导航栏
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setNavigationBarColor(Color.parseColor("#ffffff"));
        }
        //------------------------------
        //Eventbus
        EventBusUtil.register(this);
        //view注解
        ButterKnife.bind(this);

        baseInitView();

        initView();
        initData();

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    protected Bundle getBundle(){
        return getIntent().getBundleExtra("bundle");
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (isShowBacking()) {
            if (mBackLayout != null) {
                mBackLayout.setVisibility(View.VISIBLE);
                rlHeader.setVisibility(View.VISIBLE);
            }
        } else {
            if (mBackLayout != null) {
                mBackLayout.setVisibility(View.GONE);
            }
        }

    }

    @BindView(R2.id.activity_header_layout)
    public RelativeLayout rlHeader;
    @BindView(R2.id.tv_title)
    public TextView mTitle;
    @BindView(R2.id.rl_back)
    public RelativeLayout mBackLayout;
    @BindView(R2.id.image_back)
    public ImageView mImageBack;
    @BindView(R2.id.view_line)
    public View viewLine;

    @BindView(R2.id.ll_right)
    public LinearLayout ll_right;//右边布局

    public void baseInitView() {
        mBackLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    /**
     * this activity layout res
     * 设置layout布局,在子类重写该方法.
     *
     * @return res layout xml id
     */
    protected abstract int getLayoutId();

    protected abstract void initView();

    protected abstract void initData();

    protected void beforeContentView(){};

    //设置标题
    protected void setHeaderTitle(String title) {
        if (!TextUtils.isEmpty(title)) {
            mTitle.setText(title);
            viewLine.setVisibility(View.VISIBLE);
            rlHeader.setVisibility(View.VISIBLE);
        } else {
            viewLine.setVisibility(View.GONE);
            rlHeader.setVisibility(View.GONE);
        }
    }

    protected void onRightBtnClickListener() {
    }

    //设置右边布局
    protected View setRightLayout(int layoutId) {
        View inflate = LayoutInflater.from(this).inflate(layoutId, ll_right, false);
        if (layoutId > 0) {
            ll_right.setVisibility(View.VISIBLE);
            ll_right.addView(inflate, ViewGroup.LayoutParams.MATCH_PARENT);
        } else {
            ll_right.setVisibility(View.GONE);
        }
        return inflate;
    }

    protected void setRightImage(int iconRes) {
        View view = setRightLayout(R.layout.base_right_image_layout);
        ImageView mImage = view.findViewById(R.id.iv_img);
        mImage.setImageResource(iconRes);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onRightBtnClickListener();
            }
        });
    }

    protected boolean isShowBacking() {
        return false;
    }
    protected boolean isImmersionBar(){return false;}

    protected boolean isShowMessageButton() {
        return false;
    }

    protected boolean isShowShareButton() {
        return false;
    }

    //设置返回按钮图片
    public void setBackImage(int backImage) {
        mImageBack.setImageResource(backImage);
    }

    //设置状态栏
    protected void setStatusBar() {
        if(isImmersionBar()){
            statusBarImmersion();
        }else{
            statusBarTitle();
        }
    }

    //带标题的bar
    private void statusBarTitle(){
        ImmersionBar.with(this)
                .statusBarDarkFont(true, 0.2f)
                .fitsSystemWindows(true)
                .statusBarColor(R.color.tool_color)
                .keyboardEnable(false)
                .init();
    }
    //沉浸式bar
    private void statusBarImmersion(){
        StatusBarUtil.setImmersionBar(this,true);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityManager.getAppManager().finishActivity(this);
        EventBusUtil.unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventBusCome(EventBusUtil.MessageEvent event) {
        if (event != null) {
            receiveEvent(event);
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void onStickyEventBusCome(EventBusUtil.MessageEvent event) {
        if (event != null) {
            receiveStickyEvent(event);
        }
    }

    /**
     * 接收到分发到事件 * * @param event 事件
     */
    protected void receiveEvent(EventBusUtil.MessageEvent event) {
    }

    /**
     * 接受到分发的粘性事件 * * @param event 粘性事件
     */
    protected void receiveStickyEvent(EventBusUtil.MessageEvent event) {
    }

    @Override
    public void finish() {
        super.finish();
    }

    @Override
    public void setRequestedOrientation(int requestedOrientation) {
        if (Build.VERSION.SDK_INT == Build.VERSION_CODES.O && isTranslucentOrFloating()) {
            return;
        }
        super.setRequestedOrientation(requestedOrientation);
    }

    private boolean isTranslucentOrFloating() {
        boolean isTranslucentOrFloating = false;
        try {
            int[] styleableRes = (int[]) Class.forName("com.android.internal.R$styleable").getField("Window").get(null);
            final TypedArray ta = obtainStyledAttributes(styleableRes);
            Method m = ActivityInfo.class.getMethod("isTranslucentOrFloating", TypedArray.class);
            m.setAccessible(true);
            isTranslucentOrFloating = (boolean) m.invoke(null, ta);
            m.setAccessible(false);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isTranslucentOrFloating;
    }

    private boolean fixOrientation() {
        try {
            Field field = Activity.class.getDeclaredField("mActivityInfo");
            field.setAccessible(true);
            ActivityInfo o = (ActivityInfo) field.get(this);
            o.screenOrientation = -1;
            field.setAccessible(false);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    private TextView tvTime;

}
