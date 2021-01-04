package com.xqkj.baselibrary.splashscreen;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xqkj.baselibrary.R;
import com.xqkj.baselibrary.R2;
import com.xqkj.baselibrary.base.BaseActivity;
import com.xqkj.baselibrary.config.EventCode;
import com.xqkj.baselibrary.utils.EventBusUtil;
import com.xqkj.baselibrary.utils.GlideUtils;
import com.xqkj.baselibrary.utils.ToastUtil;
import com.xqkj.baselibrary.web.WebHelper;

import butterknife.BindView;
import butterknife.OnClick;

import static com.xqkj.baselibrary.splashscreen.SplashScreenHelper.INIT_DATA;
import static com.xqkj.baselibrary.splashscreen.SplashScreenHelper.INIT_VIEW;

public class WellComeActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R2.id.tv_count)
    TextView tv_count;
    @BindView(R2.id.iv_advert)
    ImageView iv_advert;
    @BindView(R2.id.ll_skip)
    LinearLayout ll_skip;

    private int interval = 1000;
    private int countTime = 5 * interval;
    protected String advertTitle, advertUrl;
    private Messenger messenger;
    private CountDownTimer countDownTimer;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_well_come;
    }

    @Override
    protected boolean isImmersionBar() {
        return true;
    }

    @Override
    protected void initView() {
        Bundle bundle = getBundle();
        if (bundle != null) {
            messenger = bundle.getParcelable("messenger");
            countTime = bundle.getInt("count_time") * interval;
        }

        sendMessage(INIT_VIEW, null);
        tv_count.setText(countTime / interval + "");
    }

    public void sendMessage(int whatCode, Object object) {
        if (messenger == null) return;
        Message msg = new Message();
        msg.what = whatCode;
        if (object != null)
            msg.obj = object;
        try {
            messenger.send(msg);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @OnClick({R2.id.ll_skip,R2.id.iv_advert})
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.ll_skip) {//跳过
            skipAfter();
        } else if (id == R.id.iv_advert) {//广告
            if(!TextUtils.isEmpty(advertUrl)) {
                WebHelper.show(this, advertTitle, advertUrl);
                skipAfter();
            }
        }
    }

    public void skipAfter() {
        this.finish();
    }

    @Override
    protected void initData() {
        sendMessage(INIT_DATA, null);
        setCountDown();
    }

    /**
     * 显示图片 资源图片
     */
    public void showAdertImage(int imgRes) {
        if (imgRes > 0) {
            iv_advert.setImageResource(imgRes);
        }
    }

    /**
     * 显示图片 网络图片
     */
    public void showAdertImage(String imgUrl) {
        if (TextUtils.isEmpty(imgUrl)) return;
        GlideUtils.showImg(iv_advert, imgUrl);
    }

    //倒计时
    public void setCountDown() {
        countDownTimer = new CountDownTimer(countTime, interval) {
            @Override
            public void onTick(long millisUntilFinished) {
                long l = millisUntilFinished / interval;
                if (l > 0) {
                    tv_count.setText(l + "");
                } else {
                    tv_count.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFinish() {
                skipAfter();
            }
        };
        countDownTimer.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (countDownTimer != null)
            countDownTimer.cancel();
    }

    @Override
    protected void receiveEvent(EventBusUtil.MessageEvent event) {
        super.receiveEvent(event);
        switch (event.getCode()) {
            case EventCode.SPLASH_IMG:
                SplashImgeBean bean = (SplashImgeBean) event.getData();
                if (bean != null) {
                    advertUrl = bean.getWebUrl();
                    advertTitle = bean.getWebTitle();
                    if (bean.isNet()) {
                        showAdertImage(bean.getUrl());
                    } else {
                        showAdertImage(bean.getRes());
                    }
                }
                break;
        }
    }

}
