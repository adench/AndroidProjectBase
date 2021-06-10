package com.xqkj.baselibrary.pay.wechat;

import com.alibaba.fastjson.annotation.JSONField;

public class WechatPayParams {
    String partnerid;
    String prepayid;
    String packages;
    String timestamp;
    String nonceStr;
    String signature;

    public WechatPayParams() {
    }

    public WechatPayParams(String partnerid, String prepayid, String packages, String timestamp, String nonceStr, String signature) {
        this.partnerid = partnerid;
        this.prepayid = prepayid;
        this.packages = packages;
        this.timestamp = timestamp;
        this.nonceStr = nonceStr;
        this.signature = signature;
    }

    public String getPartnerid() {
        return partnerid;
    }

    public void setPartnerid(String partnerid) {
        this.partnerid = partnerid;
    }

    public String getPrepayid() {
        return prepayid;
    }

    public void setPrepayid(String prepayid) {
        this.prepayid = prepayid;
    }

    public String getPackages() {
        return packages;
    }

    public void setPackages(String packages) {
        this.packages = packages;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getNonceStr() {
        return nonceStr;
    }

    public void setNonceStr(String nonceStr) {
        this.nonceStr = nonceStr;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }
}
