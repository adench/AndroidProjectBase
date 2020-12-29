package com.xqkj.baselibrary.utils;

import com.alibaba.fastjson.JSON;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by SCYDB on 2018/6/27.
 */
public class JsonUtils {

    public static Object jsonParser(String jsonStr, Class<?> clazz){
        if(isJsonArray(jsonStr)){
            return jsonParserArray(jsonStr,clazz);
        }else{
            return jsonParserObject(jsonStr,clazz);
        }
    }

    /**
     * 解析转成对象
     * @param jsonStr
     * @param clazz
     * @return
     */
    private static Object jsonParserObject (String jsonStr, Class<?> clazz){
        Object objectClass = null;
        try {
            objectClass = JSON.parseObject(JSON.parse(jsonStr).toString(), clazz);
        }catch (Exception e){
            e.printStackTrace();
        }
        return objectClass;
    }

    /**
     * 解析转成数组
     * @param jsonStr
     * @param clazz
     * @return
     */
    private static <T> List<T> jsonParserArray (String jsonStr, Class<T> clazz){
        List<T> listData = new ArrayList<>();
        try {
            listData = JSON.parseArray(JSON.parse(jsonStr).toString(), clazz);
        }catch (Exception e){
            e.printStackTrace();
        }
        return listData;
    }

    private static boolean isJsonArray(String jsonStr){
        Object typeObject = null;
        try {
            typeObject = new JSONTokener(jsonStr).nextValue();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if (typeObject instanceof JSONArray) {
            return true;
        } else if (typeObject instanceof JSONObject) {
            return false;
        }
        return false;
    }

    /**
     * map集合转json
     * @param map
     * @return
     */
    public static String mapToJson(Map<String, Object> map){
        com.alibaba.fastjson.JSONObject jsonStr = com.alibaba.fastjson.JSONObject.parseObject(JSON.toJSONString(map));
        return jsonStr.toJSONString();
    }

    public static Map<String, Object> orderFields(Map<String, Object>... maps){
        List<Map<String, Object>> list = new ArrayList<>();
        for(Map<String, Object> map : maps){
            list.add(map);
        }
        Map<String, Object> orderMap = new HashMap<>();
        orderMap.put("orderFields",list);
        return orderMap;
    }
}
