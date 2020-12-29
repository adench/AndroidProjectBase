package com.xqkj.projectbase.activity;

import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

import com.xqkj.baselibrary.utils.UIHelper;
import com.xqkj.projectbase.R;
import com.xqkj.projectbase.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class WellComeActivity extends BaseActivity {
    @BindView(R.id.tv_count)
    TextView tv_count;

    private int interval = 1000;
    private int countTime = 5*interval;
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
        tv_count.setText(countTime/interval+"");
    }

    @OnClick({R.id.ll_skip,R.id.iv_advert})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.ll_skip://跳过
                skipAfter();
                break;
            case R.id.iv_advert://广告
                UIHelper.showWebActivity(this,"牛年大吉","https://www.baidu.com/");
                skipAfter();
                break;
        }
    }

    public void skipAfter(){
        this.finish();
    }
    @Override
    protected void initData() {
        countDownTimer.start();
    }

    //倒计时
    CountDownTimer countDownTimer = new CountDownTimer(countTime,interval) {
        @Override
        public void onTick(long millisUntilFinished) {
            long l = millisUntilFinished / interval;
            if(l > 0) {
                tv_count.setText(l+"");
            }else{
                tv_count.setVisibility(View.GONE);
            }
        }

        @Override
        public void onFinish() {
            skipAfter();
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        countDownTimer.cancel();
    }
}
