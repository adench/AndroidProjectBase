package com.xqkj.baselibrary.net;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.RequestBody;

public class HttpUtils {
    public static RequestBody getRequestBody(Map<String, Object> hashMap) {
        JSONObject jsonObject = new JSONObject();
        if (hashMap != null && hashMap.size() > 0) {
            Iterator iter = hashMap.entrySet().iterator();
            while (iter.hasNext()) {
                Map.Entry entry = (Map.Entry) iter.next();
                String key = (String) entry.getKey();
                Object val = entry.getValue();
                try {
                    jsonObject.put(key,val);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
        return getRequestBody(jsonObject.toString());
    }

    private static RequestBody getRequestBody(String jsonStr) {
        RequestBody requestBody =
                RequestBody.create(MediaType.parse("application/json; charset=utf-8"),jsonStr);
        return requestBody;
    }
}
