package com.xqkj.baselibrary.pay.lib;

import android.content.Context;

public interface PayFactory {
    void toPay(Context context, String singnInfo);
    void auth(Context context,String authInfo);
}
