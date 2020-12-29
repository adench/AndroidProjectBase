package com.xqkj.baselibrary.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

public class LayoutInflaterUtils {
    public static View from(Context context, int layoutId){
        return LayoutInflater.from(context).inflate(layoutId, null, false);
    }

    public static View spaceView(Context context, float height){
        LinearLayout layout = new LinearLayout(context);
        LinearLayout.LayoutParams layoutParams =  new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, DensityUtils.dp2px(height));
        layout.setLayoutParams(layoutParams);
        return layout;
    }

}
