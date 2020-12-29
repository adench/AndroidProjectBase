package com.xqkj.baselibrary.utils;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegularUtils {

    public static boolean reg(String content, String rule){
        Pattern p = Pattern.compile(rule);
        Matcher m = p.matcher(content);
        return m.matches();
    }

    //手机号
    public static boolean phone(String phone){
        return reg(phone,"^1(3|4|5|6|7|8|9)\\d{9}$");
    }
}
