package com.xqkj.baselibrary.utils;

import android.animation.ObjectAnimator;
import android.view.View;

public class AnimatorUtils {

    //旋转
    public static ObjectAnimator rotation(View view,int angleStart,int angleEnd,int duration){
        ObjectAnimator animator = ObjectAnimator.ofFloat(view, "rotation", angleStart, angleEnd);
        animator.setDuration(duration);
        animator.start();
        return animator;
    }
}
