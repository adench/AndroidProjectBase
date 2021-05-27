package com.xqkj.projectbase.base;

import com.xqkj.baselibrary.net.BaseBean;

public class HttpBaseBean<T> implements BaseBean {
    private String code;
    private String msg;
    private T data;

    public void setCode(String code) {
        this.code = code;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return msg;
    }

    @Override
    public Object getData() {
        return data;
    }

}
