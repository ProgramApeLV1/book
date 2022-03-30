package com.book.common.units;

import com.alibaba.fastjson.JSONObject;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

public abstract class Context {

    protected static ThreadLocal<JSONObject> context = ThreadLocal.withInitial(JSONObject::new);


    /**
     * 设置属性
     *
     * @param name
     * @param value
     * @return
     */
    public static void set(String name, Object value) {
        context.get().put(name, value);
    }

    /**
     * 移出属性
     *
     * @param name
     */
    public static void remove(String name) {
        context.get().remove(name);
    }


    /**
     * 释放资源
     */
    public static void release() {
        context.get().clear();
        context.remove();
    }


    public static <T> T getObj(String name, Class<T> t) {
        return context.get().getObject(name, t);
    }

    public static BigDecimal getBigDecimal(String name) {
        return context.get().getBigDecimal(name);
    }

    public static BigInteger getBigInteger(String name) {
        return context.get().getBigInteger(name);
    }

    public static Boolean getBoolean(String name) {
        return context.get().getBoolean(name);
    }

    public static boolean getBooleanValue(String name) {
        return context.get().getBooleanValue(name);
    }

    public static Byte getByte(String name) {
        return context.get().getByte(name);
    }

    public static byte[] getBytes(String name) {
        return context.get().getBytes(name);
    }

    public static byte getByteValue(String name) {
        return context.get().getByteValue(name);
    }

    public static Date getDate(String name) {
        return context.get().getDate(name);
    }

    public static Double getDouble(String name) {
        return context.get().getDouble(name);
    }

    public static double getDoubleValue(String name) {
        return context.get().getDoubleValue(name);
    }

    public static Float getFloat(String name) {
        return context.get().getFloat(name);
    }

    public static float getFloatValue(String name) {
        return context.get().getFloatValue(name);
    }

    public static Integer getInteger(String name) {
        return context.get().getInteger(name);
    }

    public static int getIntValue(String name) {
        return context.get().getIntValue(name);
    }

    public static Long getLong(String name) {
        return context.get().getLong(name);
    }

    public static long getLongValue(String name) {
        return context.get().getLongValue(name);
    }

    public static Short getShort(String name) {
        return context.get().getShort(name);
    }

    public static short getShortValue(String name) {
        return context.get().getShortValue(name);
    }

    public static String getString(String name) {
        return context.get().getString(name);
    }

}
