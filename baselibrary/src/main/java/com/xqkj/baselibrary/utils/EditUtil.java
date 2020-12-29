package com.xqkj.baselibrary.utils;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import static android.content.Context.INPUT_METHOD_SERVICE;

public class EditUtil {
    public static String getEditText(EditText editText){
        return editText.getText().toString().trim();
    }
    public static String getEditText(TextView editText){
        return editText.getText().toString().trim();
    }

    /**
     * 显示键盘
     *
     * @param et 输入焦点
     */
    public static void showInput(Context context, final EditText et) {
        et.requestFocus();
        InputMethodManager imm = (InputMethodManager) context.getSystemService(INPUT_METHOD_SERVICE);
        imm.showSoftInput(et, InputMethodManager.SHOW_IMPLICIT);
    }

    /**
     * 隐藏键盘
     */
    public static void hideInput(Context context) {
        View view = ((Activity)context).getWindow().peekDecorView();
        if (view != null) {
            InputMethodManager inputmanger = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
            inputmanger.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
    /**
     * 隐藏键盘 dialog
     */
    public static void hideDialogInput(Context context, Dialog dialog) {
        View view =dialog.getWindow().peekDecorView();//注意：这里要根据EditText位置来获取
        if (view != null) {
            InputMethodManager inputmanger = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
            inputmanger.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    /**
     * view显示软键盘
     */
    public static void showKeyboard(View view){
        InputMethodManager imm = (InputMethodManager) view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        if(imm != null){
            view.requestFocus();
            imm.showSoftInput(view,0);
        }
    }
}
