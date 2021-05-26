package com.xqkj.baselibrary.splashscreen;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Messenger;

import androidx.annotation.NonNull;

import com.xqkj.baselibrary.banner.BannerData;
import com.xqkj.baselibrary.config.EventCode;
import com.xqkj.baselibrary.utils.EventBusUtil;
import com.xqkj.baselibrary.utils.UIHelper;

import java.util.List;

public abstract class SplashScreenHelper {
    public final static int INIT_VIEW = 0x001;
    public final static int INIT_DATA = 0x002;
    public final static int BANNER_CLICK = 0x003;
    private Context mContext;
    private int countTime = 5;
    public SplashScreenHelper(Context context){
        this.mContext = context;
    }

    //跳转到闪屏页
    public SplashScreenHelper toIntent(){
        Bundle bundle = new Bundle();
        bundle.putParcelable("messenger",new Messenger(handler));
        bundle.putInt("count_time",getCoutTime());
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
                case BANNER_CLICK:
                    MessageClickBean clickBean = (MessageClickBean) msg.obj;
                    onBannerClick(clickBean.getData(),clickBean.getPosition());
                    break;
            }
        }
    };

    protected int getCoutTime(){return countTime;}
    protected void initView(){};

    protected abstract void initData();
    protected abstract void onBannerClick(BannerData data,int position);

    /**
     * 设置数据
     * @param data
     */
    public void notifyShowImage(List<BannerData> data){
        EventBusUtil.sendEvent(new EventBusUtil.MessageEvent(EventCode.SPLASH_IMG,data));
    }

}
