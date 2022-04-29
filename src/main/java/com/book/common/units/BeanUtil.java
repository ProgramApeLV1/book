package com.book.common.units;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.cglib.beans.BeanMap;
import org.springframework.util.CollectionUtils;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;
import java.util.function.BiFunction;

/**
 * Bean工具
 *
 * @author wyh
 */
@Slf4j
public class BeanUtil {

    public static final String CLASS = "class";
    public static final char UNDERLINE = '_';
    private static final int UNDERLINE_TO_CAMEL = 2;

    public static <T> T convertBean2Bean(Object from, Class<T> clazz) {
        T res = null;
        try {
            res = clazz.newInstance();
            BeanUtils.copyProperties(from, res);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return res;

    }

    /**
     * 将map装换为javabean对象
     *
     * @param map
     * @param bean
     * @return
     */
    public static <T> T map2Bean(Map<String, Object> map, T bean) {
        BeanMap beanMap = BeanMap.create(bean);
        beanMap.putAll(map);
        return bean;
    }

    /**
     * 实体转map
     *
     * @param bean
     * @return
     * @throws Exception
     */
    public static Map bean2Map(Object bean) throws Exception {
        Class type = bean.getClass();
        Map returnMap = new HashMap(16);
        BeanInfo beanInfo = Introspector.getBeanInfo(type);
        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
        for (PropertyDescriptor descriptor : propertyDescriptors) {
            String propertyName = descriptor.getName();
            if (!CLASS.equals(propertyName)) {
                Method readMethod = descriptor.getReadMethod();
                Object result = readMethod.invoke(bean);
                if (result != null) {
                    returnMap.put(propertyName, result);
                } else {
                    returnMap.put(propertyName, "");
                }
            }
        }
        return returnMap;
    }

    /**
     * 将Bean对象进行转换
     *
     * @param from
     * @param to
     * @return
     */
    public static Object convertBean2Bean(Object from, Object to) {
        BeanUtils.copyProperties(from, to);
        return to;
    }

    /**
     * 将list对象进行转换
     *
     * @param list
     * @param classz
     * @return
     */
    public static <T> List<T> convertList2List(List list, Class<T> classz) {
        List resultList = new ArrayList();
        if (list == null) {
            return resultList;
        }
        for (Object obj : list) {
            T res = convertBean2Bean(obj, classz);
            resultList.add(res);
        }
        return resultList;
    }

    /**
     * 将map的list改为小写的
     *
     * @param list
     * @return
     */
    public static List<Map<String, Object>> lowerCaseList(List<Map<String, Object>> list) {
        List<Map<String, Object>> result = new ArrayList<>();
        for (Map<String, Object> map : list) {
            Map<String, Object> m = new HashMap<>(16);
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                Object value = entry.getValue();
                if (value instanceof List) {
                    List<Object> temp = (List<Object>) value;
                    if (null != temp && !temp.isEmpty()) {
                        if (temp.get(0) instanceof Map) {
                            value = lowerCaseList((List<Map<String, Object>>) value);
                        }
                    }
                }
                if (isFirstUpperCase(entry.getKey())) {
                    m.put(entry.getKey().toLowerCase(), value);
                } else {
                    m.put(entry.getKey(), value);
                }
            }
            result.add(m);
        }
        return result;
    }

    /**
     * 判断字符串的首字母是否大写
     *
     * @param str
     * @return
     */
    public static boolean isFirstUpperCase(String str) {
        if (str.length() > 0) {
            char c = str.charAt(0);
            if (Character.isUpperCase(c)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 将下划线改为驼峰
     *
     * @param list
     * @return
     */
    public static List<Map<String, Object>> camelList(List<Map<String, Object>> list) {
        if (CollectionUtils.isEmpty(list)) {
            return list;
        }
        List<Map<String, Object>> result = new ArrayList<>();
        for (Map<String, Object> map : list) {
            Map<String, Object> m = new HashMap<>(16);
            if (!map.isEmpty()) {
                for (Map.Entry<String, Object> entry : map.entrySet()) {
                    Object value = entry.getValue();
                    if (value instanceof List) {
                        List<Object> temp = (List<Object>) value;
                        if (null != temp && !temp.isEmpty()) {
                            if (temp.get(0) instanceof Map) {
                                value = camelList((List<Map<String, Object>>) value);
                            }
                        }
                    }
                    if (entry.getKey().contains("_")) {
                        m.put(StringUtil.underline2Camel(entry.getKey()), value);
                    } else {
                        m.put(entry.getKey(), value);
                    }
                }
            }
            result.add(m);
        }
        return result;
    }


    /**
     * Map驼峰转下划线
     *
     * @param map
     * @return
     */
    public static Map<String, Object> mapTranferKeyToUnderline(Map<String, Object> map) {
        HashSet<String> ignoreSets = new HashSet();
        return mapTranferKeyToUnderlineOrCamel(map, ignoreSets, 1);
    }

    /**
     * Map下划线转驼峰
     *
     * @param map
     * @return
     */
    public static Map<String, Object> mapTranferKeyToCamel(Map<String, Object> map) {
        HashSet<String> ignoreSets = new HashSet();
        return mapTranferKeyToUnderlineOrCamel(map, ignoreSets, 2);
    }

    /**
     * list驼峰转下划线
     *
     * @param list
     * @return
     */
    public static List<Map<String, Object>> listTranferKeyToUnderline(List<Map<String, Object>> list) {
        return listTranferKeyToUnderlineOrCamel(list, 1);
    }

    /**
     * list下划线转驼峰
     *
     * @param list
     * @return
     */
    public static List<Map<String, Object>> listTranferKeyToCamel(List<Map<String, Object>> list) {
        return listTranferKeyToUnderlineOrCamel(list, 2);
    }


    /**
     * 针对map的key的下划线和驼峰互转
     *
     * @param map
     * @param ignoreKeys
     * @param type       值为1时驼峰转下划线,值为2时下划线转驼峰
     * @return
     */
    public static Map<String, Object> mapTranferKeyToUnderlineOrCamel(Map<String, Object> map, Set<String> ignoreKeys, Integer type) {
        Set<Map.Entry<String, Object>> entries = map.entrySet();
        Map<String, Object> resultMap = new HashMap<String, Object>(16);
        for (Map.Entry<String, Object> entry : entries) {
            String key = entry.getKey();
            Object value = entry.getValue();
            if (ignoreKeys.contains(key)) {
                resultMap.put(key, value);
                continue;
            }
            String newkey = "";
            //驼峰转下划线
            if (type == 1) {
                newkey = camelToUnderline(key);
            }
            //下划线转驼峰
            else if (type == UNDERLINE_TO_CAMEL) {
                newkey = underlineToCamel(key);
            }
            if (value instanceof List) {
                List valList = buildValueList((List) value, ignoreKeys, (m, keys) -> mapTranferKeyToUnderlineOrCamel(m, keys, type));
                resultMap.put(newkey, valList);
            } else if (value instanceof Map) {
                Map<String, Object> subResultMap = mapTranferKeyToUnderlineOrCamel((Map) value, ignoreKeys, type);
                resultMap.put(newkey, subResultMap);
            } else {
                resultMap.put(newkey, value);
            }
        }
        return resultMap;
    }

    public static List buildValueList(List valList, Set<String> ignoreKeys, BiFunction<Map, Set, Map> transferFunc) {
        if (valList == null || valList.size() == 0) {
            return valList;
        }
        Object first = valList.get(0);
        if (!(first instanceof List) && !(first instanceof Map)) {
            return valList;
        }
        List newList = new ArrayList();
        for (Object val : valList) {
            Map<String, Object> subResultMap = transferFunc.apply((Map) val, ignoreKeys);
            newList.add(subResultMap);
        }
        return newList;
    }


    public static List<Map<String, Object>> listTranferKeyToUnderlineOrCamel(List<Map<String, Object>> list, Integer type) {
        List<Map<String, Object>> list1 = new ArrayList<>();
        //驼峰转下划线
        if (type == 1) {
            for (Map<String, Object> map : list) {
                list1.add(mapTranferKeyToUnderline(map));
            }
        }
        //下划线转驼峰

        else if (type == UNDERLINE_TO_CAMEL) {
            for (Map<String, Object> map : list) {
                list1.add(mapTranferKeyToCamel(map));
            }
        }
        return list1;
    }

    /**
     * 驼峰转下划线
     *
     * @param param
     * @return
     */
    public static String camelToUnderline(String param) {
        if (StringUtils.isEmpty(param)) {
            return "";
        }
        int len = param.length();
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            char c = param.charAt(i);
            if (Character.isUpperCase(c)) {
                if (i != 0) {
                    sb.append(UNDERLINE);
                }
                sb.append(Character.toLowerCase(c));
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    /**
     * 下划线转驼峰
     *
     * @param param
     * @return
     */
    public static String underlineToCamel(String param) {
        if (StringUtils.isEmpty(param)) {
            return "";
        }
        //先转成小写
        param = StringUtils.lowerCase(param);
        int len = param.length();
        StringBuilder sb = new StringBuilder(len);
        // "_" 后转大写标志,默认字符前面没有"_"
        Boolean flag = false;
        for (int i = 0; i < len; i++) {
            char c = param.charAt(i);
            if (c == UNDERLINE) {
                flag = true;
                //标志设置为true,跳过
                continue;
            } else if (flag) {
                //表示当前字符前面是"_" ,当前字符转大写
                sb.append(Character.toUpperCase(param.charAt(i)));
                //重置标识
                flag = false;
            } else {
                sb.append(param.charAt(i));
            }
        }
        return sb.toString();
    }

    /**
     * 将List<Bean>转为List<Map>
     *
     * @param list
     * @param toUnderLine 将key转为下划线
     * @param filterNull  过滤值为null的
     * @param <T>
     * @return
     */
    public static <T> List<Map<String, Object>> beanList2MapList(List<T> list, boolean toUnderLine, boolean filterNull) {
        List<Map<String, Object>> result = new ArrayList<>();
        if (list == null) {
            return null;
        }
        if (list.size() == 0) {
            return result;
        }
        for (T t : list) {
            Class clazz = t.getClass();
            Field[] fields = clazz.getDeclaredFields();
            Map<String, Object> row = new HashMap<>(16);
            for (Field field : fields) {
                Method med;
                String name = field.getName();
                if ("serialVersionUID".equalsIgnoreCase(name)) {
                    continue;
                }
                try {
                    med = clazz.getMethod("get" + name.substring(0, 1).toUpperCase() + name.substring(1));
                    Object res = med.invoke(t);
                    if (filterNull && res == null) {
                        continue;
                    }
                    if (toUnderLine) {
                        name = camelToUnderline(name);
                    }
                    row.put(name, res);
                } catch (Exception e) {
                    log.warn(e.getMessage(), e);
                }
            }
            result.add(row);
        }
        return result;
    }

    public static <T> List<Map<String, Object>> beanList2MapList(List<T> list, boolean toUnderLine) {
        return beanList2MapList(list, toUnderLine, false);
    }

    public static <T> List<Map<String, Object>> beanList2MapList(List<T> list) {
        return beanList2MapList(list, false, false);
    }
}