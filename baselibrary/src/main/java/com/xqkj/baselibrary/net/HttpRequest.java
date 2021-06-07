package com.xqkj.baselibrary.net;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;
import com.xqkj.baselibrary.listener.HttpLoginInvalidLinstener;
import com.xqkj.baselibrary.utils.JsonUtils;
import com.xqkj.baselibrary.waitdialog.WaitDialog;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class HttpRequest {
    public final static String POST = "post";
    public final static String GET = "get";
    public final static String PUT = "put";
    public final static String DELETE = "delete";
    public final static String POST_JSON = "post_json";

    private Class<?> clazz = BaseBean.class;
    private String apiurl = "";
    private Map<String, String> map = new HashMap<>();
    private HttpCallBack callback;
    private String MODEl = POST;
    private static HttpRequest request;
    private Context mContext;
    private String jsonStr;
    private boolean isShowDialog = true;

    public static HttpRequest init(Context context) {
        request = new HttpRequest(context);
        return request;
    }

    public HttpRequest(Context context) {
        this.mContext = context;
    }

    public HttpRequest get(String apiurl) {
        MODEl = GET;
        this.apiurl = apiurl;
        return request;
    }

    public HttpRequest post(String apiurl) {
        MODEl = POST;
        this.apiurl = apiurl;
        return request;
    }

    public HttpRequest postJson(String apiurl, Map<String, Object> map) {
        MODEl = POST_JSON;
        this.apiurl = apiurl;
        this.jsonStr = JsonUtils.mapToJson(map);
        return request;
    }

    public HttpRequest setClazz(Class<?> clazz) {
        this.clazz = clazz;
        return this;
    }

    public HttpRequest setMap(Map<String, String> map) {
        this.map = map;
        return this;
    }

    public HttpRequest setShowDialog(boolean showDialog) {
        isShowDialog = showDialog;
        return this;
    }

    public void excute(HttpCallBack callback) {
        request(MODEl, callback);
    }

    private void request(String model, final HttpCallBack callback) {
        StringCallback stringCallback = new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                Log.d("json", response.body());
                if (response != null) {
                    requestDealWith(response.body(), clazz, callback);
                }
            }

            @Override
            public void onError(Response<String> response) {
                super.onError(response);
                if (response != null) {
                    requestDealWith(response.body(), clazz, callback);
                }
            }

            @Override
            public void onStart(Request<String, ? extends Request> request) {
                super.onStart(request);
                if (mContext != null && isShowDialog)
                    WaitDialog.show(mContext, "加载中...");
                callback.befor();
            }

            @Override
            public void onFinish() {
                super.onFinish();
                WaitDialog.dismiss();
                callback.after();
            }

        };

        Log.e("http-url", apiurl);
        if (model.equals(POST)) {
            Log.e("http-map", map.toString());
            OkGoUtil.post(apiurl, map, stringCallback);
        } else if (model.equals(GET)) {
            Log.e("http-map", map.toString());
            OkGoUtil.get(apiurl, map, stringCallback);
        } else if (model.equals(POST_JSON)) {
            Log.e("http-json", jsonStr);
            OkGoUtil.postJson(apiurl, jsonStr, stringCallback);
        }

    }

    //请求后处理
    public void requestDealWith(String s, Class<?> clazz, HttpCallBack callback) {
        if (TextUtils.isEmpty(s)) {
            Log.d("http_request", "没有json返回,请检查请求方式");
            return;
        }
        if (HttpSetting.BASE_BEAN == null) {
            Log.d("http_request", "请在application中设置基础类");
            return;
        }
        BaseBean resultBean = (BaseBean) JsonUtils.jsonParser(s, HttpSetting.BASE_BEAN);
        if (resultBean.getCode().equals(HttpSetting.SUCCESS_CODE)) {
            if (clazz != null && clazz != BaseBean.class) {
                if(resultBean.getData() != null) {
                    Object object = JsonUtils.jsonParser(resultBean.getData().toString(), clazz);
                    callback.success(object);
                }else{
                    callback.success(null);
                }
            } else {
                callback.success(null);
            }
        } else {
            if(Arrays.binarySearch(HttpSetting.LOGIN_FAILD_CODE,resultBean.getCode()) > 0){
                if(!callback.invalidLogin()) {
                    //登录过期
                    HttpLoginInvalid invalidLogin = HttpSetting.invalidLogin;
                    if (invalidLogin != null) {
                        invalidLogin.onCall();
                    }
                }
            }else {
                callback.failed(resultBean.getCode(), resultBean.getMessage());
            }
        }
    }

    public static void cancleRequest(String tag) {
        OkGo.getInstance().cancelTag(tag);
    }
}
