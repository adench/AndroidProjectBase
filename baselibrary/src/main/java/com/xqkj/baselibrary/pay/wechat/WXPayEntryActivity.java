package com.xqkj.baselibrary.pay.wechat;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.xqkj.baselibrary.base.BaseApplication;
import com.xqkj.baselibrary.config.EventCode;
import com.xqkj.baselibrary.utils.EventBusUtil;
import com.xqkj.baselibrary.utils.ToastUtil;

public class WXPayEntryActivity extends AppCompatActivity{
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        BaseApplication.wxapi.handleIntent(getIntent(),iwxapiEventHandler);
    }


    IWXAPIEventHandler iwxapiEventHandler = new IWXAPIEventHandler() {
        @Override
        public void onReq(BaseReq baseReq) {

        }

        @Override
        public void onResp(BaseResp resp) {
            if (resp.getType() == ConstantsAPI.COMMAND_PAY_BY_WX) {
                int errCord = resp.errCode;
                if (errCord == 0) {
                    ToastUtil.showToast("支付成功");
                    EventBusUtil.sendEvent(new EventBusUtil.MessageEvent(EventCode.EVENT_PAY_SUCCESS));
                } else {
                    ToastUtil.showToast("支付取消");
                    EventBusUtil.sendEvent(new EventBusUtil.MessageEvent(EventCode.EVENT_PAY_FAILD));
                }
                finish();
            }
        }
    };

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        BaseApplication.wxapi.handleIntent(intent, iwxapiEventHandler);
    }

    public void finish(){
        this.finish();
    };

}
