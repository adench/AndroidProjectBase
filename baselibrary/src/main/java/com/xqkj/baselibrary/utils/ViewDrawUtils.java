package com.xqkj.baselibrary.utils;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;

import com.xqkj.baselibrary.listener.OnViewTreeObserver;

public class ViewDrawUtils {

    //根据宽度设置高度
    public static void viewWidthFindHeight(final View view, final double scale) {
        view.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                view.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                int viewWidth = view.getWidth();
                if (scale > 0) {
                    int height = (int) (viewWidth / scale);
                    ViewGroup.LayoutParams viewParams = view.getLayoutParams();
                    viewParams.height = height;
                    view.setLayoutParams(viewParams);
                }
            }
        });
    }

    //设置最高高度
    public static void viewMaxHeight(final View view, final int height) {
        view.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                view.getViewTreeObserver().removeOnPreDrawListener(this);
                int viewheight = view.getHeight();
                if (viewheight > height) {
                    ViewGroup.LayoutParams viewParams = view.getLayoutParams();
                    viewParams.height = DensityUtils.dp2px(height);
                    view.setLayoutParams(viewParams);
                }
                return true;
            }
        });
    }

    //设置图片宽高
    public static void viewScreenWidthFindHeight(final View view, final int screenWidth, final double scale) {
        view.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                view.getViewTreeObserver().removeOnPreDrawListener(this);

                int sjHeight = (int) (screenWidth / scale);
                ViewGroup.LayoutParams viewParams = view.getLayoutParams();
                viewParams.width = screenWidth;
                viewParams.height = sjHeight;
                view.setLayoutParams(viewParams);
                return true;
            }
        });
    }

    //获取控件高度
    public static void getViewHeight(final View view,final OnViewTreeObserver onViewTreeObserver){
        view.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                view.getViewTreeObserver().removeOnPreDrawListener(this);
                int height = view.getHeight();
                onViewTreeObserver.onHeight(height);
                return false;
            }
        });
    }

    //获取控件宽度
    public static void getViewWidth(final View view,final OnViewTreeObserver onViewTreeObserver){
        view.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                view.getViewTreeObserver().removeOnPreDrawListener(this);
                int width = view.getWidth();
                onViewTreeObserver.onWidth(width);
                return false;
            }
        });
    }

    //获取控件top
    public static void getViewTop(final View view,final OnViewTreeObserver onViewTreeObserver){
        view.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                view.getViewTreeObserver().removeOnPreDrawListener(this);
                int top = view.getTop();
                onViewTreeObserver.onTop(top);
                return false;
            }
        });
    }

    //获取控件bottom
    public static void getViewBottom(final View view,final OnViewTreeObserver onViewTreeObserver){
        view.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                view.getViewTreeObserver().removeOnPreDrawListener(this);
                int bottom = view.getBottom();
                onViewTreeObserver.onBottom(bottom);
                return false;
            }
        });
    }

}
