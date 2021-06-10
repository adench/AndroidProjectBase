package com.xqkj.baselibrary.pay.wechat;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.xqkj.baselibrary.base.BaseApplication;


public class WXEntryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        BaseApplication.wxapi.handleIntent(getIntent(), iwxapiEventHandler);
    }

    IWXAPIEventHandler iwxapiEventHandler = new IWXAPIEventHandler() {
        @Override
        public void onReq(BaseReq baseReq) {
        }

        @Override
        public void onResp(BaseResp resp) {
            Log.d("resp--", resp.errCode + "--Err:" + resp.getType());
            switch (resp.errCode) {
                case BaseResp.ErrCode.ERR_OK:
                    if (resp instanceof SendAuth.Resp) {
                        SendAuth.Resp newResp = (SendAuth.Resp) resp;
                        //获取微信传回的code
                        String code = newResp.code;
                        //登录回调处理
                        Log.d("wx_code", code);
//                    AuthUtils.auth(this, Contans.PAY_TYPE_WECHAT,code);
//                    EventBusUtil.sendEvent(new EventBusUtil.MessageEvent(Event.ACCOUNT_BIND_WECHAT, code));
                    }
                    finish();
                    break;
                default:
                    finish();
                    break;
            }
        }
    };


    public void finish(){
        this.finish();
    }

}
