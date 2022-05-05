package com.book.common.units;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.commons.lang3.time.DateUtils;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
public class StringUtil {

    private static final Pattern PATTERN_UNDERLINE = Pattern.compile("([A-Za-z\\d]+)(_)?");
    private static final Pattern PATTERN_CAMEL = Pattern.compile("[A-Z]([a-z\\d]+)?");


    public static String getSqlNotNullStrValue(String str) {
        return "";
    }

    public static String getNotNullStrValue(String str) {
        if (str == null) {
            return "";
        } else {
            return getNotNullStrValue(str, "");
        }
    }

    public static String getNotNullStrValue(Object obj) {
        if (obj == null) {
            return "";
        } else {
            return getNotNullStrValue(obj.toString());
        }
    }

    public static String getNullStrToZero(String str) {
        return getNotNullStrValue(str, "0");
    }

    public static String getNotNullStrValue(String str, String defStr) {
        if (StringUtils.isNotEmpty(str) && !str.trim().equals(""))
            return str.trim();
        return defStr;
    }

    public static int getNotNullIntValue(String str) {
        int intNum = 0;
        try {
            intNum = Integer.parseInt(str);
        } catch (NumberFormatException ignored) {
        }
        return intNum;
    }

    public static int getNotNullIntValue(String str, int defValue) {
        int intNum = getNotNullIntValue(str);
        if (intNum == 0)
            intNum = defValue;
        return intNum;
    }

    public static long getNotNullLongValue(String str) {
        long longNum = 0;
        try {
            if (StringUtils.isNotEmpty(str) && StringUtils.isNumeric(str))
                longNum = Long.parseLong(str);
        } catch (NumberFormatException ignored) {
        }
        return longNum;
    }

    public static BigDecimal getNotNullBigDecimalValue(String str) {
        if (StringUtils.isNotEmpty(str) && NumberUtils.isNumber(str)) {
            return new BigDecimal(str);
        }
        return new BigDecimal(0);
    }

    public static long getNotNullLongValue(String str, long defValue) {
        long longNum = getNotNullLongValue(str);
        if (longNum == 0)
            longNum = defValue;
        return longNum;
    }

    public static String getDecimalFormartNum(Number number) {
        DecimalFormat formater = new DecimalFormat("###.##");
        return formater.format(number);
    }

    public static BigDecimal getBigDecimalNotNull(BigDecimal bigDecimal) {
        if (bigDecimal == null) {
            return new BigDecimal(0);
        } else {
            return bigDecimal;
        }
    }

    public static BigDecimal formatNumber(BigDecimal number) {
        DecimalFormat formater = new DecimalFormat("###.##");
        return new BigDecimal(formater.format(number));
    }

    public static boolean isEmpty(String str) {
        return null == str || "".equals(str);
    }

    public static boolean isNotEmpty(Object obj) {
        if (obj instanceof String) {
            return !isEmpty(String.valueOf(obj));
        } else {
            return null != obj;
        }
    }

    public static String encoderStringDefault(String str) {
        return encodeStr(str, "gbk");
    }

    public static String decoderStringDefault(String str) {
        return decodeStr(str, "gbk");
    }

    public static String encodeStr(String str, String code) {
        String resultStr = "";
        try {
            resultStr = URLEncoder.encode(str, code);
        } catch (Exception e) {
            log.error("字符串加密失败：", e);
        }
        return resultStr;
    }

    public static String decodeStr(String str, String code) {
        String resultStr = "";
        try {
            resultStr = URLDecoder.decode(str, code);
        } catch (Exception e) {
            log.error("字符串解码失败：", e);
        }
        return resultStr;
    }

    public static String encodeStr(String str) {
        String resultStr = "";
        try {
            resultStr = URLEncoder.encode(str, "UTF-8");
        } catch (Exception e) {
            log.error("字符串加密失败：", e);
        }
        return resultStr;
    }

    public static String decodeStr(String str) {
        String resultStr = "";
        try {
            resultStr = URLDecoder.decode(str, "UTF-8");
        } catch (Exception e) {
            log.error("字符串解码失败：", e);
        }
        return resultStr;
    }

    public static Map<String, String> decodeMapValue(Map<String, String> param) {
        Iterator<String> it = param.keySet().iterator();
        Map<String, String> newMap = new HashMap<String, String>();
        String key = "";
        while (it.hasNext()) {
            key = it.next();
            try {
                String value = URLDecoder.decode(param.get(key), "UTF-8");
                value = value.replaceAll("%", " ");
                // 放入新map中
                newMap.put(key, value);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return newMap;
    }

    public static String getTime() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return df.format(new Date());
    }

    public static String getCurrDate() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        return df.format(new Date());
    }

    public static String startPage(String page, String rows) {
        String resultStr = "";
        try {
            if (page != null && rows != null) {
                int intPage = Integer.parseInt(page.trim().equals("0") ? "1" : page.trim());
                int start = (intPage - 1) * Integer.parseInt(rows.trim());
                resultStr = String.valueOf(start);
            }
        } catch (Exception e) {
            log.error("获得页数失败：", e);
        }
        return resultStr;
    }

    public static int getDay(int year, int month) {
        int day = 31;
        try {

            switch (month) {
                case 1:
                case 3:
                case 5:
                case 7:
                case 8:
                case 10:
                case 12:
                    day = 31;
                    break;
                case 2:
                    if ((year % 4 == 0 && year % 100 != 0) || (year % 400 == 0)) {
                        day = 29;
                        break;
                    } else {
                        day = 28;
                        break;
                    }
                case 4:
                case 6:
                case 9:
                case 11:
                    day = 30;
                    break;
                default:
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return day;
    }

    public static String getYearOrMonthOrDay(String value) {
        String result = null;
        Calendar calendar = Calendar.getInstance();
        if ("year".equals(value)) {
            result = String.valueOf(calendar.get(Calendar.YEAR));
        }

        if ("month".equals(value)) {
            result = String.valueOf(calendar.get(Calendar.MONTH) + 1);
        }

        if ("day".equals(value)) {
            result = String.valueOf(calendar.get(Calendar.DAY_OF_MONTH));
        }
        return result;
    }

    public static String getTimeFormat(String time) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date_util = sdf.parse(time);
        java.sql.Date date_sql = new java.sql.Date(date_util.getTime());
        System.out.println(date_util);
        System.out.println(date_sql);
        String date = sdf.format(date_sql);
        System.out.println(date);
        date = sdf.format(date_util);
        System.out.println(date);
        return date;
    }

    /**
     * StringתDate
     *
     * @param str 时间字符串
     * @return 时间
     */
    public static Date stringToDate(String str) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        if (isNotEmpty(str)) {
            try {
                date = sdf.parse(str);
            } catch (ParseException e) {
                log.info(String.valueOf(e.getCause()));
                e.printStackTrace();
            }
        }
        return date;
    }

    /**
     * StringתDateTime
     *
     * @param str 时间字符串
     * @return 时间
     */
    public static Date stringToDateTime(String str) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;
        if (isNotEmpty(str)) {
            try {
                date = sdf.parse(str);
            } catch (ParseException e) {
                log.info(String.valueOf(e.getCause()));
                e.printStackTrace();
            }
        }
        return date;
    }

    /**
     * 获取当日的开始时间
     *
     * @return 时间字符串
     */
    public static String getCurrentDayStartTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
        return sdf.format(DateUtils.addDays(new Date(), 1));
    }

    /**
     * 获取当前时间是周几
     *
     * @param date 时间
     * @return 字符串
     */
    public static String getWeekOfDate(Date date) {
        String[] weekOfDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
        Calendar calendar = Calendar.getInstance();
        if (date != null) {
            calendar.setTime(date);
        }
        int w = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0) {
            w = 0;
        }
        return weekOfDays[w];
    }

    /**
     * 获取搜索区域编码的区间值
     *
     * @return String
     */
    public static String getAreaRegion(String areaId) {
        String areaRegion = "0";
        if (areaId != null) {
            if (areaId.endsWith("0000")) { //130000
                areaRegion = String.valueOf((Integer.parseInt(areaId) + 9999)); //139999
            } else if (areaId.endsWith("00")) { //130100
                areaRegion = String.valueOf((Integer.parseInt(areaId) + 99)); //130199
            } else {
                areaRegion = areaId;
            }
        }
        return areaRegion;
    }

    /**
     * 判断一个字符是否是中文
     *
     * @return String
     */
    public static boolean isChinese(char c) {
        return c >= 0x4E00 && c <= 0x9FA5;// 根据字节码判断
    }

    /**
     * 判断一个字符串是否含有中文
     *
     * @return String
     */
    public static boolean isChinese(String str) {
        if (str == null) return false;
        for (char c : str.toCharArray()) {
            if (isChinese(c)) return true;// 有一个中文字符就返回
        }
        return false;
    }

    /**
     * 转成一个INT类型
     *
     * @return String
     */
    public static Integer castToInteger(Object obj) {
        String objStr = String.valueOf(obj);
        int objInt;
        try {
            objInt = Integer.parseInt(objStr);
        } catch (Exception e) {
            objInt = 0;
        }
        return objInt;
    }

    /**
     * 获取今天的日期
     *
     * @return String
     */
    public static String getToday() {
        Date day = new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        return df.format(day);
    }

    /**
     * 获取昨天的日期
     *
     * @return String
     */
    public static String getYesterday() {
        Date d = new Date(System.currentTimeMillis() - 1000 * 60 * 60 * 24);
        SimpleDateFormat sp = new SimpleDateFormat("yyyy-MM-dd");
        return sp.format(d);
    }

    /**
     * 获取两天之间差距天数
     *
     * @return String
     */
    public static long differenceDay(String sDate, String eDate) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        long m = 0;
        long t = 0;
        try {
            m = sdf.parse(sDate).getTime() - sdf.parse(eDate).getTime();
            t = (m / (1000 * 60 * 60 * 24));
        } catch (ParseException e) {
            e.printStackTrace();
            return 0;
        }
        return t;
    }

    /**
     * 获得该月第一天
     *
     * @param year  年份
     * @param month 月份
     * @return 返回月初的时间字符串
     */
    public static String getFirstDayOfMonth(int year, int month) {
        Calendar cal = Calendar.getInstance();
        //设置年份
        cal.set(Calendar.YEAR, year);
        //设置月份
        cal.set(Calendar.MONTH, month - 1);
        //获取某月最小天数
        int firstDay = cal.getActualMinimum(Calendar.DAY_OF_MONTH);
        //设置日历中月份的最小天数
        cal.set(Calendar.DAY_OF_MONTH, firstDay);
        //格式化日期
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(cal.getTime());
    }

    /**
     * 获得该月最后一天
     *
     * @param year  年份
     * @param month 月份
     * @return 返回最后一天
     */
    public static String getLastDayOfMonth(int year, int month) {
        Calendar cal = Calendar.getInstance();
        //设置年份
        cal.set(Calendar.YEAR, year);
        //设置月份
        cal.set(Calendar.MONTH, month - 1);
        //获取某月最大天数
        int lastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        //设置日历中月份的最大天数
        cal.set(Calendar.DAY_OF_MONTH, lastDay);
        //格式化日期
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(cal.getTime());
    }

    /**
     * 获得上个月份
     *
     * @param currentDate 当前时间
     * @return 字符串
     */
    public static String getLastDate(String currentDate) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = sdf.parse(currentDate + "-" + "01");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar c = Calendar.getInstance();
        assert date != null;
        c.setTime(date);
        c.add(Calendar.MONTH, -1);
        return c.get(Calendar.YEAR) + "-" + String.format("%02d", (c.get(Calendar.MONTH) + 1));
    }

    /**
     * 格式化 成   xxxx年xx月
     *
     * @param date 日期
     * @return 字符串
     */
    public static String formatYearAndMonth_Zh(String date) {
        Date tDate = null;
        try {
            tDate = new SimpleDateFormat("yyyy-MM").parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return new SimpleDateFormat("yyyy年MM月").format(tDate);
    }

    /**
     * 格式化 成   xxxx年xx月
     *
     * @param date 日期
     * @return 字符串
     */
    public static String formatYearAndMonthAndDate_Zh(String date) {
        Date tDate = null;
        try {
            tDate = new SimpleDateFormat("yyyy-MM-dd").parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return new SimpleDateFormat("yyyy年MM月dd日").format(tDate);
    }

    /**
     * Description: 去掉字符串的下划线
     *
     * @param str 字符串
     * @return String
     * @Title: getNotLineStr
     */
    public static String getNotLineStr(String str) {
        StringBuilder result = new StringBuilder();
        char[] s = str.toCharArray();
        if (s.length > 0) {
            for (char c : s) {
                if ('_' != c) {
                    result.append(c);
                }
            }
        }
        return result.toString();
    }


    /**
     * Description: 规范只有一个空格
     *
     * @param str 字符串
     * @return String
     * @Title: getStringByBlank
     */
    public static String getStringByBlank(String str) {
        StringBuilder a = new StringBuilder();
        if (str != null && !"".equals(str)) {
            StringTokenizer s = new StringTokenizer(str.trim());
            while (s.hasMoreTokens()) {
                a.append(" ").append(s.nextToken());
            }
        }
        return a.toString().trim();
    }

    /**
     * Description: 判断字符串str0是否在str里
     *
     * @param str  字符串
     * @param str0 字符串
     * @return boolean false 没有 TRUE 有
     * @Title: checkStr
     */
    public static boolean checkStr(String str, String str0) {
        boolean result = false;
        if (str != null && !"".equals(str)) {
            str = getSplitString(str, ",");
            String[] a = str.split(",");
            if (a.length > 0) {
                for (String b : a) {
                    if (str0.equals(b)) {
                        result = true;
                        break;
                    }
                }
            }
        }
        return result;
    }

    /**
     * Description: oracle 转义字符
     *
     * @param str 字符串
     * @return String
     * @Title: filtSqlStr
     */
    public static String filtSqlStr(String str) {
        try {
            if ((str != null) && (!str.trim().equals(""))) {
                str = str.replaceAll("'", "''"); // '号
            }
            return str;
        } catch (Exception e) {
            return "";
        }
    }

    /*
     * 字符串的替换
     * @author Laihz
     * @date Nov 06,2018
     * @param str  1,2,3
     * @return	'1','2','3'
     */
    public static String filtrateStringToHtmlBYLOG(String str) {
        try {
            if ((str != null) && (!str.trim().equals(""))) {
                str = str.replaceAll("&", "&"); // &号
                str = str.replaceAll("\"", "&quot;"); // "号
                str = str.replaceAll("<", "&lt;"); // 正括号
                str = str.replaceAll(">", "&gt;"); // 反括号
                str = str.replaceAll("\n", "<br>"); // 回车
                str = str.replaceAll(" ", " "); // 空格
                str = str.replaceAll("\t", "    "); // TAB键
            }
            return str;
        } catch (Exception e) {
            return "";
        }
    }

    /*
     * 字符串的替换
     * @author Laihz
     * @date Nov 06,2018
     * @param str  1,2,3
     * @return	'1','2','3'
     */
    public static String filtrateStringToHtml(String str) {
        try {
            if ((str != null) && (!str.trim().equals(""))) {
                str = str.replaceAll("&", "&amp;"); // &号
                str = str.replaceAll("\"", "&quot;"); // "号
                str = str.replaceAll("<", "&lt;"); // 正括号
                str = str.replaceAll(">", "&gt;"); // 反括号
                str = str.replaceAll("\n", "<br>"); // 回车
                str = str.replaceAll(" ", "&nbsp;"); // 空格
                str = str.replaceAll("\t", "&nbsp;&nbsp;&nbsp;&nbsp;"); // TAB键
            }
            return str;
        } catch (Exception e) {
            return "";
        }
    }

    /*
     * 字符串的替换
     * @author Laihz
     * @date Nov 06,2018
     * @param str  1,2,3
     * @return	'1','2','3'
     */
    public static String filtrateHtmlToString(String str) {
        try {
            if ((str != null) && (!str.trim().equals(""))) {
                str = str.replaceAll("&amp;", "&"); // &号
                str = str.replaceAll("&quot;", "\""); // "号
                str = str.replaceAll("&lt;", "<"); // 正括号
                str = str.replaceAll("&gt;", ">"); // 反括号
                str = str.replaceAll("<br>", "\n"); // 回车
                str = str.replaceAll("&nbsp;", " "); // 空格
                str = str.replaceAll("&nbsp;&nbsp;&nbsp;&nbsp;", "\t"); // TAB键
            }
            return str;
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * 将用逗号组成的字符串转换成加上单引号，用于sql的条件
     *
     * @param str 1,2,3
     * @return '1','2','3'
     * @author Laihz
     * @date Nov 06,2016
     */
    public static String filtrateStringToSqlQuotes(String str) {
        if (str != null) {
            String[] strTemp = str.split(",");
            StringBuilder strBuilder = new StringBuilder();
            for (int i = 0; i < strTemp.length; i++) {
                strBuilder.append("'").append(strTemp[i]).append("'");
                if (i != strTemp.length - 1) {
                    strBuilder.append(",");
                }
            }
            str = strBuilder.toString();
        } else {
            str = "";
        }
        return str;
    }

    /**
     * Description: 转化为单引号 字符串
     *
     * @param str 字符串
     * @return String
     * @Title: getSqlStrBySlist
     * @author Laihz
     * @date Oct 11, 2016
     */
    public static String getSqlStrBySlist(String str) {
        StringBuilder result = new StringBuilder();
        if ("".equals(str.trim())) {
            return result.toString();
        } else {
            String[] a = str.split(",");
            if (a.length > 0) {
                result.append("'").append(a[0]).append("'");
                for (int i = 1; i < a.length; i++) {
                    result.append(",'").append(a[i]).append("'");
                }
            }
        }
        return result.toString();
    }

    /**
     * Description: 去除数组重复元素
     *
     * @param a 主数组
     * @param b 对比数组
     * @return String[]
     * @Title: getStringArray
     * @author Laihz
     * @date Aug 22, 2016
     */
    public static String[] getStringArray(String[] a, String[] b) {
        if (a != null && b != null) {
            if (b.length > a.length) {
                throw new IllegalArgumentException("索引出界");
            } else {
                for (String s : b) {
                    a = delArr(a, s);
                }
            }
        }
        return a;
    }


    /**
     * Description: 删除数组指定元素
     *
     * @param arr   数组
     * @param index 下标
     * @return String[]
     * @Title: delArr
     * @author Laihz
     * @date Aug 22, 2016
     */
    public static String[] delArr(String[] arr, String index) {
        index = getNotNullStrValue(index);
        String[] result = null;
        if (arr != null) {
            if (arr.length == 1) {
                return result;
            } else {
                String[] a = new String[arr.length];
                int temp = 0;
                if (arr.length > 0) {
                    for (String s : arr) {
                        if (getNotNullStrValue(s).equals(index))
                            continue;
                        a[temp++] = s;
                    }
                }
                result = a;
            }
        }
        return result;
    }

    /**
     * Description: 除去逗号分隔字符串中 指定字符
     *
     * @param str 字符串
     * @param ind 下标
     * @return String
     * @Title: delIndex
     * @author Laihz
     * @date Aug 3, 2016
     */
    public static String delIndex(String str, String ind) {
        String[] a = str.split(",");
        a = delArr(a, ind);
        return getStrByArr(a);
    }

    /**
     * Description: 将字符串数组转化为逗号分隔字符串
     *
     * @param arr 数组
     * @return String
     * @Title: getStrByArr
     * @author Laihz
     * @date Aug 22, 2016
     */
    public static String getStrByArr(String[] arr) {
        StringBuilder result = new StringBuilder();
        if (arr == null) {
            return result.toString();
        } else if (arr.length > 0) {
            result = new StringBuilder(arr[0]);
            for (int i = 1; i < arr.length; i++) {
                if (arr[i] != null) {
                    result.append(",").append(arr[i]);
                }
            }
        }
        return result.toString();
    }

    /**
     * Description: 截取指定长度字符串,并替换尾字符
     *
     * @param str     原字符串
     * @param toCount 长度
     * @param more    替换符号
     * @return String
     * @Title: substring
     * @author Laihz
     * @date Jun 10, 2016
     */
    public static String substring(String str, int toCount, String more) {
        int reInt = 0;
        String reStr = "";
        if (str == null) {
            return "";
        }
        char[] tempChar = str.toCharArray();
        if (more != null) {
            toCount -= more.length();
        }
        for (int kk = 0; kk < tempChar.length && toCount > reInt; kk++) {
            String s1 = String.valueOf(tempChar[kk]);
            byte b[] = s1.getBytes();
            reInt += b.length;
            reStr = String.valueOf(reStr) + tempChar[kk];
        }
        if (toCount == reInt || toCount == reInt - 1) {
            reStr = String.valueOf(reStr) + more;
        }
        return reStr;
    }

    /**
     * Description: 将字符串转化为分隔符 的字符串（去重复无顺序）
     *
     * @param str  字符串
     * @param type 分隔符
     * @return String
     * @Title: getSplitString
     * @author Laihz
     * @date Sep 13, 2016
     */
    public static String getSplitString(String str, String type) {
        StringBuilder c = new StringBuilder();
        if ("".equals(type) || type == null) {
            type = ",";
        }
        str = getSigleStr(str, type);
        String[] b = str.split(type);
        List list = new LinkedList();
        for (String s : b) {
            if (s != null && !"".equals(s)) {
                list.add(s);
            }
        }
        if (list.size() > 0) {
            c = new StringBuilder((String) list.get(0));
            for (int i = 1; i < list.size(); i++) {
                c.append(type).append(list.get(i));
            }
        }
        return c.toString();
    }

    /**
     * Description: 去重复
     *
     * @param Str  字符串
     * @param type 类型
     * @return String
     * @Title: getSigleStr
     * @author Laihz
     * @date Sep 13, 2016
     */
    public static String getSigleStr(String Str, String type) {
        if ("".equals(type) || type == null) {
            type = ",";
        }
        if (Str == null || "".equals(Str)) {
            return "";
        }
        StringBuilder result = new StringBuilder();
        String[] s = Str.split(type);
        Map<String, Object> map = new HashMap<String, Object>();
        if (s.length > 0) {
            for (String value : s) {
                map.put(value, null);
            }
        }
        List<String> list = new ArrayList<String>(map.keySet());
        for (String ob : list) {
            result.append(type).append(ob);
        }
        return result.toString();
    }

    /**
     * Description: Xml特殊字符串出来
     *
     * @param value 字符串
     * @return String
     * @Title: filterXml
     * @author Laihz
     * @date Sep 13, 2016
     */
    public static String filterXml(String value) {
        if (value == null)
            return null;
        char[] content = new char[value.length()];
        value.getChars(0, value.length(), content, 0);
        StringBuilder result = new StringBuilder(content.length + 50);
        for (char c : content)
            switch (c) {
                case 60: // '<'
                    result.append("&lt;");
                    break;
                case 62: // '>'
                    result.append("&gt;");
                    break;
                case 38: // '&'
                    result.append("&amp;");
                    break;
                case 34: // '"'
                    result.append("&quot;");
                    break;
                case 39: // '\''
                    result.append("&#39;");
                    break;
                default:
                    result.append(c);
                    break;
            }
        return result.toString();
    }


    /**
     * Description: mapZ换成一个Bean
     *
     * @param mpFrom 对象
     * @param objTo  对象
     * @return Object
     * @Title: mapToBean
     * @author Laihz
     * @date Sep 13, 2016
     */
    public static Object mapToBean(Map mpFrom, Object objTo) {
        Object[] objKeys = mpFrom.keySet().toArray();
        String strFieldName = "";

        try {
            for (Object objkey : objKeys) {
                strFieldName = objkey.toString();

                Field objField = objTo.getClass().getDeclaredField(strFieldName);
                objField.setAccessible(true);

                objField.set(objTo, mpFrom.get(strFieldName));
            }
        } catch (Exception e) {
            log.error("map转为Bean失败：" + e.getMessage());
        }
        return objTo;
    }

    /**
     * 逗号分隔的字符串数组转化为List
     *
     * @param str 字符串
     * @return list
     * @author laihz 2016-03-30
     */
    public static List<String> str2List(String str) {
        List<String> list = new ArrayList<String>();
        String[] strArray = str.split(",");
        Collections.addAll(list, strArray);
        return list;
    }

    /**
     * 中午字符串转成ascii
     *
     * @param gbString 字符串
     * @return list
     * @author laihz 2016-04-21
     */
    public static String gbEncoding(final String gbString) {
        char[] utfBytes = gbString.toCharArray();
        StringBuilder unicodeBytes = new StringBuilder();
        for (char utfByte : utfBytes) {
            String hexB = Integer.toHexString(utfByte);
            if (hexB.length() <= 2) {
                hexB = "00" + hexB;
            }
            unicodeBytes.append("\\u").append(hexB);
        }
        return unicodeBytes.toString();
    }

    /**
     * ascii转成中文字符串
     *
     * @param dataStr 时间字符串
     * @return list
     * @author laihz 2016-04-21
     */
    public static String decodeUnicode(final String dataStr) {
        int start = 0;
        int end = 0;
        final StringBuilder buffer = new StringBuilder();
        while (start > -1) {
            end = dataStr.indexOf("\\u", start + 2);
            String charStr = "";
            if (end == -1) {
                charStr = dataStr.substring(start + 2, dataStr.length());
            } else {
                charStr = dataStr.substring(start + 2, end);
            }
            char letter = (char) Integer.parseInt(charStr, 16); // 16进制parse整形字符串。
            buffer.append(Character.toString(letter));
            start = end;
        }
        return buffer.toString();
    }

    /**
     * 时间字符串 ---> 触发器表达式
     * dateStr ---> cronExpress
     *
     * @param dateStr 时间字符串
     * @return cronExpress
     * @author laihz
     */
    public static String dateTimeToCronExpression(String dateStr) {
        /*
         1、如官方文档解释的那样，问号(?)的作用是指明该字段‘没有特定的值’；
         2、星号(*)和其它值，比如数字，都是给该字段指明特定的值，只不过用星号(*)代表所有可能值；
         3、cronExpression对日期和星期字段的处理规则是它们必须互斥，即只能且必须有一个字段有特定的值，另一个字段必须是‘没有特定的值’，只能在日或周字段上使用；
         4、问号(?)就是用来对日期和星期字段做互斥的。
         */
        String year, month, day, hour, minute, date, time, cronExpression;
        String[] param = dateStr.split(" ");
        date = param[0];
        time = param[1];
        String[] param1 = date.split("-");
        String[] param2 = time.split(":");
        year = param1[0];
        month = param1[1];
        day = param1[2];
        hour = param2[0];
        minute = param2[1];
        cronExpression = "0 " + minute + " " + hour + " " + day + " " + month + " ? " + year; //秒 分 时 日 月 周 年
        return cronExpression;
    }

    /**
     * 比较时间
     * dateTime1 < dateTime2 -->return true
     * dateTime1 > dateTime2 -->return false
     *
     * @param dateStr1 时间字符串
     * @param dateStr2 时间字符串
     * @throws ParseException 异常
     */
    public static boolean compareDateTime(String dateStr1, String dateStr2) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date date1 = sdf.parse(dateStr1);
        Date date2 = sdf.parse(dateStr2);
        return date1.before(date2);
    }

    /**
     * 判断升级任务开始时间是否在两周以内
     *
     * @param sourceDateStr 源日志字符串
     * @return -2 解析出错
     * @author laihz 2016-05-30
     */
    public static int isDTTwoWeekLater(String sourceDateStr) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        try {
            Date sourceDate = sdf.parse(sourceDateStr);
            Date currDate = sdf.parse(sdf.format(new Date()));
            long cha = sourceDate.getTime() - currDate.getTime();
            if (cha < 0) {
                return -1;
            }
            double result = cha * 1.0 / (1000 * 60 * 60);
            if (result <= 24 * 14) {
                return 1;
            } else {
                return 0;
            }
        } catch (ParseException e) {
            e.printStackTrace();
            return -2;
        }
    }

    /**
     * 拼装升级结果字符串
     *
     * @param str    source
     * @param newStr destination
     * @param type   true:逗号隔开的"成功"串     false:逗号隔开的"失败"串
     * @return 字符串
     */
    public static String replaceCharStr(String str, String newStr, boolean type) {
        String result = "";
        String[] strArrary = str.split(",");
        if (type) {
            result = strArrary[0].toString().replaceAll("0", newStr) + "," + strArrary[1];
        } else {
            result = strArrary[0] + "," + strArrary[1].toString().replaceAll("0", newStr);
        }
        return result;
    }

    /***
     * 传入一个数组后获得数组中的最大值和最小值(不取0)
     * @param array 数组
     * @return map对象
     */
    public static Map<String, Integer> getArrayMaxAndMin(int array[]) {
        Map<String, Integer> map = new HashMap<String, Integer>();
        int i, min = 0, max = 0;
        for (int j = 0; j < array.length; j++) {
            if (array[j] != 0) {
                min = max = array[j];
                break;
            }
        }
        for (i = 0; i < array.length; i++) {
            if (array[i] > max && array[i] != 0) {
                // 判断最大值
                max = array[i];
            }
            if (array[i] < min && array[i] != 0) {
                // 判断最小值
                min = array[i];
            }
        }
        map.put("max", max);
        map.put("min", min);
        return map;
    }

    /***
     *
     * @param ver1 版本1
     * @param ver2 版本2
     * @return 比较结果
     */
    public static boolean compareVersion(String ver1, String ver2) {
        if (!ver1.equals(ver2)) {
            String[] verArr1 = ver1.split("\\.");
            String[] verArr2 = ver2.split("\\.");
            int length = Math.min(verArr1.length, verArr2.length);
            for (int i = 0; i < length; i++) {
                if (Integer.parseInt(verArr2[i]) > Integer.parseInt(verArr1[i])) {
                    return true;
                } else if (Integer.parseInt(verArr2[i]) < Integer.parseInt(verArr1[i])) {
                    return false;
                }
            }
            return true;
        } else {
            return false;
        }
    }

    /**
     * 获取一个月的天数
     *
     * @param months 月份
     * @return 列表
     */
    public static List<String> getArrByMonthStr(String months) {
        List<String> list = new ArrayList<String>();
        Pattern p = Pattern.compile("[0-9]{8}");
        Matcher m = p.matcher(months);
        while (m.find()) {
            list.add(m.group());
        }
        return list;
    }

    /**
     * 获取当天时间
     *
     * @return 字符串
     */
    public static String dateForMater() {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(date);
    }

    /**
     * 将时间转换为时间戳
     *
     * @param s 字符串
     * @return 字符串
     */
    public static String dateToStamp(String s) throws ParseException {
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = simpleDateFormat.parse(s);
        long ts = date.getTime();
        res = String.valueOf(ts);
        return res;
    }

    /***
     * @param arr 集合
     * @return "1,2,3"
     * ***/
    public static String collection2String(List<String> arr) {
        StringBuilder sb = new StringBuilder();
        for (String str : arr) {
            sb.append("'").append(str).append("'").append(",");
        }
        return sb.length() > 0 ? sb.substring(0, sb.length() - 1) : null;
    }

    /**
     * @param input 输入两位小数的浮点型字符串
     * @return 返回有效字符串
     */
    public static String switchFloatReal(String input) {//转换格式33.3-->33.3;33.0-->33
        int p = input.indexOf(".");
        if (p != -1) {
            String pStr = input.substring(p + 1);
            if (pStr.equals("00")) {
                input = input.substring(0, p - 1);
            } else if ("0".equals(pStr.substring(1))) {
                input = input.substring(0, p + 1);
            }
        }
        return input;

    }

    public static String lpad(String str, int num, String pad) {
        StringBuilder n_str = new StringBuilder(str);
        if (!(str.length() >= num)) {
            for (int i = str.length(); i < num; i++) {
                n_str.insert(0, pad);
            }
        }
        return n_str.toString();
    }

    public static String phoneCutEncrypt(String phone) {
        return phone.replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2");
    }

    public static String identityCutEncrypt(String identity) {
        return identity.replaceAll("(\\d{4})\\d{10}(\\d{4})", "$1**********$2");
    }

    /***
     * 转化日期为 yyyy-MM-dd HH:mm:ss 格式的字符串
     * @param localDateTime 日期
     * @return 字符串
     */
    public static String localDateTimeStr(LocalDateTime localDateTime) {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return format.format(localDateTime);
    }

    /**
     * 下划线转驼峰法(默认小驼峰)
     *
     * @param line       源字符串
     * @param smallCamel 大小驼峰,是否为小驼峰(驼峰，第一个字符是大写还是小写)
     * @return 转换后的字符串
     */
    public static String underline2Camel(String line, boolean... smallCamel) {
        if (line == null || "".equals(line)) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        Matcher matcher = PATTERN_UNDERLINE.matcher(line);
        //匹配正则表达式
        while (matcher.find()) {
            String word = matcher.group();
            //当是true 或则是空的情况
            boolean empty = (smallCamel.length == 0 || smallCamel[0]) && matcher.start() == 0;
            if (empty) {
                sb.append(Character.toLowerCase(word.charAt(0)));
            } else {
                sb.append(Character.toUpperCase(word.charAt(0)));
            }

            int index = word.lastIndexOf('_');
            if (index > 0) {
                sb.append(word.substring(1, index).toLowerCase());
            } else {
                sb.append(word.substring(1).toLowerCase());
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
// 		new SimpleDateFormat("yyyy-MM-dd HH:00:00").format(new Date()); 
// 		 new Timestamp(Calendar.getInstance().getTimeInMillis())
// 		System.out.println(Calendar.getInstance().getTimeInMillis());
//        String[] secret = {"2","3","4","5","6"};
//        String[] input = {"2","3","4","5"};
//        System.out.println(Arrays.equals(secret,input));//true
        System.out.println(getArrByMonthStr("5"));
    }


}
