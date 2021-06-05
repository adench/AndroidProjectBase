package com.xqkj.baselibrary.net;

import com.lzy.okgo.model.HttpHeaders;

public class HttpHeader extends HttpHeaders {

    public HttpHeader addParams(String key, String value) {
        put(key, value);
        return this;
    }
}
