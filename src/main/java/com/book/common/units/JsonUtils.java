package com.book.common.units;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.util.List;
import java.util.Map;

public class JsonUtils {

    /***
     * object 转成json
     * @param root
     * @return
     */
    public static String convertObjectToJson(Object root) {

        String resutlString = "";
        try {
            resutlString = JSON.toJSONString(root);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return resutlString;
    }


    /***
     * json转成object
     * @param
     * @return
     */
    public static<T> T convertJsonToObject(String json,Class<T> clazz) {
        T readValue = null;
        try {
            readValue = JSON.parseObject(json, clazz);
        } catch (Exception e) {
        }

        return readValue;
    }



    /**
     * 功能描述：把JSON数据转换成普通字符串列表
     *
     * @param jsonData
     *            JSON数据
     *
     * @return
     * @throws Exception
     *
     */
    public static List<String> convertJsonToObjectList(String jsonData) throws Exception {
        return JSON.parseArray(jsonData, String.class);
    }

    /**
     * 功能描述：把JSON数据转换成jsonObject
     *
     * @param jsonData
     *            JSON数据
     * @return
     * @throws Exception
     *
     */
    public static JSONObject convertJsonToObject(String jsonData) throws Exception {
        return JSON.parseObject(jsonData);
    }


    /**
     * 功能描述：把JSON数据转换成指定的java对象列表
     *
     * @param json
     *            JSON数据
     * @param clazz
     *            指定的java对象
     * @return
     * @throws Exception
     */
    public static <T> List<T> convertJsonToObjectObjList(String json, Class<T> clazz)
            throws Exception {
        return JSON.parseArray(json, clazz);
    }

    /**
     * 功能描述：把JSON数据转换成较为复杂的java对象列表
     *
     * @param json
     *            JSON数据
     * @return
     * @throws Exception
     */
    public static List<Map<String, Object>> convertJsonToObjectMapList(String json)
            throws Exception {
        return JSON.parseObject(json,
                new TypeReference<List<Map<String, Object>>>() {
                });
    }


    /**
     * 将网络请求下来的数据用fastjson处理空的情况，并将时间戳转化为标准时间格式
     * @param result
     * @return
     */
    public static String dealResponseResult(String result) {
        result = JSONObject.toJSONString(result,
                SerializerFeature.WriteClassName,
                SerializerFeature.WriteMapNullValue,
                SerializerFeature.WriteNullBooleanAsFalse,
                SerializerFeature.WriteNullListAsEmpty,
                SerializerFeature.WriteNullNumberAsZero,
                SerializerFeature.WriteNullStringAsEmpty,
                SerializerFeature.WriteDateUseDateFormat,
                SerializerFeature.WriteEnumUsingToString,
                SerializerFeature.WriteSlashAsSpecial,
                SerializerFeature.WriteTabAsSpecial);
        return result;
    }
}