package com.xqkj.baselibrary.net;

import com.lzy.okgo.model.HttpHeaders;

public class HttpHeader {
    HttpHeaders httpHeader;

    public HttpHeader() {
        httpHeader = new HttpHeaders();
    }

    public HttpHeader addParams(String key, String value) {
        httpHeader.put(key, value);
        return this;
    }
}
