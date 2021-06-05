package com.xqkj.baselibrary.net;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.HttpHeaders;
import com.lzy.okgo.model.HttpParams;

import org.json.JSONObject;

import java.util.Map;

public class OkGoUtil {
    public static void get(String url, Map<String, String> map, StringCallback stringCallback){
        OkGo.<String>get(url)
                .tag(url)
                .cacheKey("cacheGetKey")
                .cacheMode(CacheMode.NO_CACHE)
                .headers("Content-Type","application/json")
                .headers(new HttpHeaders())
                .params(map)
                .execute(stringCallback);
    }
    public static void post(String url, Map<String, String> map, StringCallback stringCallback){
        OkGo.<String>post(url)
                .tag(url)
                .cacheKey("cachePostKey")
                .headers("Content-Type","application/json")
                .headers(HttpSetting.headers)
                .upJson(new JSONObject(map))
                .execute(stringCallback);
    }

    public static void postJson(String url, String jsonStr, StringCallback stringCallback){
        OkGo.<String>post(url)
                .tag(url)
                .cacheKey("cachePostKey")
                .headers("Content-Type","application/json")
                .headers(HttpSetting.headers)
                .headers(new HttpHeaders())
                .upJson(jsonStr)
                .execute(stringCallback);
    }

    /**
     * 上传图片
     * @param url
     * @param params
     * @param stringCallback
     */
    public static void uploadFile(String url, HttpParams params, StringCallback stringCallback){
        OkGo.<String>post(url)
                .isMultipart(true)
                .tag(url)
                .cacheKey("cachePostKey")
                .cacheMode(CacheMode.NO_CACHE)
                .headers(HttpSetting.headers)
                .params(params)
                .execute(stringCallback);
    }

}
