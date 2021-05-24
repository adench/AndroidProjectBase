package com.xqkj.baselibrary.utils;

import android.text.TextUtils;

import java.text.NumberFormat;
import java.util.List;
import java.util.regex.Pattern;

public class DataUtils {

    //时间不足两位，0补齐
    public static String formatData(int str){
        return String.format("%2d", str).replace(" ", "0");
    }

    public static boolean isNumberic(String str){
        Pattern pattern = Pattern.compile("[0-9]*");
        return pattern.matcher(str).matches();
    }

    /**
     * 数组转文字串
     * @param listK
     * @return
     */
    public static String lis2String(List<?> listK, String tager){
        if(listK == null)return "";
        tager = TextUtils.isEmpty(tager)?",":tager;
        String str = listK.toString();
        str = str.replace("[","").replace("]","").replace(", ",tager);
        return str;
    }

    /**
     * 更换中间间隔符号
     */
    public static String replaceTag(String str, String oldTag, String newTag){
        return str.replace(oldTag,newTag);
    }

    /**
     * 格式化金额
     */
    public static String formatPrice(String str){
        if(TextUtils.isEmpty(str)){
            return "0";
        }
        NumberFormat nf = NumberFormat.getInstance();
        String numStr = nf.format(Double.parseDouble(str));
        numStr = numStr.replace(",","");
        return numStr;
    }
}
