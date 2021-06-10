package com.xqkj.baselibrary.pay.wechat;

import android.content.Context;

import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.xqkj.baselibrary.base.BaseApplication;
import com.xqkj.baselibrary.pay.lib.PayFactory;
import com.xqkj.baselibrary.utils.JsonUtils;
import com.xqkj.baselibrary.utils.ToastUtil;

public class WechatPay implements PayFactory {
    @Override
    public void toPay(Context context, String orderInfo) {
        WechatPayParams params = (WechatPayParams) JsonUtils.jsonParser(orderInfo,WechatPayParams.class);
        PayReq request = new PayReq();
        request.appId = BaseApplication.WECHAT_APP_ID;///你的微信appid
        request.partnerId = params.getPartnerid();//商户号
        request.prepayId = params.getPrepayid();//预支付交易会话ID
        request.packageValue = params.getPackages();
        request.nonceStr = params.getNonceStr();////随机字符串
        request.timeStamp = params.timestamp;//时间戳
        request.sign = params.getSignature();//签名
        BaseApplication.wxapi.sendReq(request);
    }

    @Override
    public void auth(Context context,String authInfo) {
        if (!BaseApplication.wxapi.isWXAppInstalled()) {
            ToastUtil.showToast("您还未安装微信客户端");
            return;
        }
        SendAuth.Req req = new SendAuth.Req();
        req.scope = "snsapi_userinfo";//snsapi_userinfo
        req.state = "base_library";
        BaseApplication.wxapi.sendReq(req);
    }
}
