package com.xqkj.baselibrary.splashscreen;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Messenger;

import androidx.annotation.NonNull;

import com.xqkj.baselibrary.config.EventCode;
import com.xqkj.baselibrary.utils.EventBusUtil;
import com.xqkj.baselibrary.utils.UIHelper;

public class SplashScreenHelper {
    public final static int INIT_VIEW = 0x001;
    public final static int INIT_DATA = 0x002;
    private Context mContext;
    private int countTime = 5;
    public SplashScreenHelper(Context context){
        this.mContext = context;
    }

    public void setCoutTime(int count){
        countTime = count;
    }

    //跳转到闪屏页
    public SplashScreenHelper toIntent(){
        Bundle bundle = new Bundle();
        bundle.putParcelable("messenger",new Messenger(handler));
        bundle.putInt("count_time",countTime);
        UIHelper.showCommonBundleActivity(mContext,bundle,WellComeActivity.class);
        return this;
    }

    Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case INIT_VIEW:
                    initView();
                    break;
                case INIT_DATA:
                    initData();
                    break;
            }
        }
    };

    protected void initView(){};
    protected void initData(){};

    public void notifyShowImage(int res,String webUrl,String webTitile){
        EventBusUtil.sendEvent(new EventBusUtil.MessageEvent(EventCode.SPLASH_IMG,new SplashImgeBean(res,webUrl,webTitile)));
    }
    public void notifyShowImage(String url,String webUrl,String webTitile){
        EventBusUtil.sendEvent(new EventBusUtil.MessageEvent(EventCode.SPLASH_IMG,new SplashImgeBean(url,webUrl,webTitile)));
    }
}
