package com.xqkj.baselibrary.net;

import com.lzy.okgo.model.HttpHeaders;

import java.util.Map;

public abstract class HttpHeader {

    public HttpHeaders getHttpHeaders() {
        HttpHeaders httpHeader = new HttpHeaders();
        Map<String, String> params = getParams();
        if (params != null && params.size() > 0) {
            for (Map.Entry<String, String> entry : params.entrySet()) {
                httpHeader.put(entry.getKey(), entry.getValue());
            }
        }
        return httpHeader;
    }

    public abstract Map<String, String> getParams();
}
