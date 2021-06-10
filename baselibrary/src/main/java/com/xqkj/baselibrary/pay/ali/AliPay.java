package com.xqkj.baselibrary.pay.ali;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;

import com.alipay.sdk.app.AuthTask;
import com.alipay.sdk.app.PayTask;
import com.tencent.bugly.proguard.A;
import com.xqkj.baselibrary.config.EventCode;
import com.xqkj.baselibrary.pay.lib.PayFactory;
import com.xqkj.baselibrary.utils.EventBusUtil;
import com.xqkj.baselibrary.utils.ToastUtil;

import java.util.Map;

public class AliPay implements PayFactory {
    public static AliPay instance;
    public static AliPay getInstance() {
        if (instance == null) {
            instance = new AliPay();
        }
        return instance;
    }

    private final int SDK_PAY_FLAG = 0x0011;
    private final int FLAG_ALIPAY_LOGIN = 0x0012;


    @Override
    public void toPay(final Context context, final String orderInfo) {
        if (TextUtils.isEmpty(orderInfo)) {
            ToastUtil.showToast("请检查支付签名信息");
            return;
        }

        Runnable payRunnable = new Runnable() {

            @Override
            public void run() {
                PayTask alipay = new PayTask((Activity) context);
                Map<String, String> result = alipay.payV2(orderInfo, true);

                Message msg = new Message();
                msg.what = SDK_PAY_FLAG;
                msg.obj = result;
                mHandler.sendMessage(msg);
            }
        };
        // 必须异步调用
        Thread payThread = new Thread(payRunnable);
        payThread.start();
    }

    @Override
    public void auth(final Context context, final String alipay_str) {
        Runnable authRunnable = new Runnable() {
            @Override
            public void run() {
                // 构造AuthTask 对象
                AuthTask authTask = new AuthTask((Activity) context);
                // 调用授权接口，获取授权结果
                Map<String, String> result = authTask.authV2(alipay_str, true);
                Message msg = new Message();
                msg.what = FLAG_ALIPAY_LOGIN;
                msg.obj = result;
                mHandler.sendMessage(msg);
            }
        };

        // 必须异步调用
        Thread authThread = new Thread(authRunnable);
        authThread.start();
    }

    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SDK_PAY_FLAG:
                    @SuppressWarnings("unchecked")
                    PayResult payResult = new PayResult((Map<String, String>) msg.obj);
                    /**
                     对于支付结果，请商户依赖服务端的异步通知结果。同步通知结果，仅作为支付结束的通知。
                     */
                    String resultInfo = payResult.getResult();// 同步返回需要验证的信息
                    String resultStatus = payResult.getResultStatus();
                    // 判断resultStatus 为9000则代表支付成功
                    if (TextUtils.equals(resultStatus, "9000")) {
                        // 该笔订单是否真实支付成功，需要依赖服务端的异步通知。
                        //这里是支付成功，做后续操作
                        ToastUtil.showToast("支付成功");
                        EventBusUtil.sendEvent(new EventBusUtil.MessageEvent(EventCode.EVENT_PAY_SUCCESS));
                    } else {
                        // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
                        ToastUtil.showToast("支付取消或者支付失败");
                        EventBusUtil.sendEvent(new EventBusUtil.MessageEvent(EventCode.EVENT_PAY_FAILD));
                    }
                    break;
                case FLAG_ALIPAY_LOGIN:
                    @SuppressWarnings("unchecked")
                    PayResult authResult = new PayResult((Map<String, String>) msg.obj);
                    String result = authResult.getResultStatus();
                    // 判断resultStatus 为“9000”且result_code为“200”则代表授权成功，
                    if (TextUtils.equals(result, "9000")) {
                        // 获取alipay_open_id，调支付时作为参数extern_token 的value传入，则支付账户为该授权账户，这个支付先不做
//                        thirdLogin(authResult.getAuthCode());//开发者自己的方法，把code传给后台同事，他们拿code换token
                        String authInfo = authResult.getResult();
                        //分割字符串
                        String[] split = authInfo.split("&");
                        String s = split[6];
                        s = s.substring(8, 24);
//                        AuthUtils.auth(mContext, Contans.PAY_TYPE_ALIPAY,s);
                    } else {
                        ToastUtil.showToast("授权取消");
//                        if (TextUtils.isEmpty(authResult.getAuthCode())) {
//                            ToastUtil.showToast("授权取消");
//                        } else
//                            ToastUtil.showToast(String.format("授权失败_authCode:%s"));
                    }
                    break;
            }

        }

        ;
    };
}
