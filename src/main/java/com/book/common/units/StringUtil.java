package com.book.common.units;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class StringUtil {

	private static Logger logger = LogManager.getLogger(StringUtil.class);


	public static String getSqlNotNullStrValue(String str) {
		String r = "";
		if (str != null && !"".equals(str)) {
		}
		return r;
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
		} catch (NumberFormatException numForExc) {
			intNum = 0;
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
		} catch (NumberFormatException numForExc) {
			longNum = 0;
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
		if (null == str || "".equals(str)) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean isNotEmpty(Object obj) {
		if (obj instanceof String) {
			return !isEmpty(String.valueOf(obj));
		} else {
			if (null != obj) {
				return true;
			} else {
				return false;
			}
		}
	}

	public static String encoderStringDefault(String str){
		return encodeStr(str,"gbk");
	}
	
	public static String decoderStringDefault(String str){
		return decodeStr(str,"gbk");
	}

	public static String encodeStr(String str,String code){
		String resultStr = "";
		try{
			resultStr = URLEncoder.encode(str,code);
		}catch (Exception e) {
			logger.error("字符串加密失败：", e);
		}
		return resultStr;
	}
	
	public static String decodeStr(String str,String code){
		String resultStr = "";
		try{
			resultStr = URLDecoder.decode(str,code);
		}catch (Exception e) {
			logger.error("字符串解码失败：", e);
		}
		return resultStr;
	}
	
	public static String encodeStr(String str){
		String resultStr = "";
		try{
			resultStr = URLEncoder.encode(str,"UTF-8");
		}catch (Exception e) {
			logger.error("字符串加密失败：", e);
		}
		return resultStr;
	}
	
	public static String decodeStr(String str){
		String resultStr = "";
		try{
			resultStr = URLDecoder.decode(str,"UTF-8");
		}catch (Exception e) {
			logger.error("字符串解码失败：", e);
		}
		return resultStr;
	}
	
	public static Map<String, String> decodeMapValue(Map<String, String> param){
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

	public static String getTime(){
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return df.format(new Date());
	}

	public static String getCurrDate(){
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		return df.format(new Date());
	}

	public static String startPage(String page,String rows){
		String resultStr = "";
		try{
			if(page!=null&&rows!=null){
				int intPage = Integer.parseInt((page.trim() == null || page.trim() == "0") ? "1":page.trim());
				int start =  (intPage-1)*Integer.parseInt(rows.trim());
				resultStr = String.valueOf(start);
			}
		}catch (Exception e) {
			logger.error("获得页数失败：", e);
		}
		return resultStr;
	}

	public static int getDay(int year,int month){
		int day = 31;
		try{

			switch (month){
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
		        default:break;
	        }
		}catch (Exception e) {
			e.printStackTrace();
		}
		return day;
	}

	public static String getYearOrMonthOrDay(String value){
		String result = null;
		Calendar calendar = Calendar.getInstance();
		if("year".equals(value)){
			result = String.valueOf(calendar.get(Calendar.YEAR));
		}

		if("month".equals(value)){
			result = String.valueOf(calendar.get(Calendar.MONTH)+1);
		}

		if("day".equals(value)){
			result = String.valueOf(calendar.get(Calendar.DAY_OF_MONTH));
		}
		return result;
	}

	public static String getTimeFormat(String time) throws Exception{
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
	 * @param str
	 * @return
	 */
	public static Date stringToDate(String str) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		if (isNotEmpty(str)) {
			try {
				date = sdf.parse(str);
			} catch (ParseException e) {
				logger.info(e.getCause());
				e.printStackTrace();
			}
		}
		return date;
	}

	/**
	 * StringתDateTime
	 *
	 * @param str
	 * @return
	 */
	public static Date stringToDateTime(String str) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = null;
		if (isNotEmpty(str)) {
			try {
				date = sdf.parse(str);
			} catch (ParseException e) {
				logger.info(e.getCause());
				e.printStackTrace();
			}
		}
		return date;
	}

     /**
      * 获取当日的开始时间
      * @return
      */
     public static String getCurrentDayStartTime(){
    	 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
    	 return sdf.format(DateUtils.addDays(new Date(), 1));
     }

     /**
      * 获取当前时间是周几
      * @param date
      * @return
      */
	 public static String getWeekOfDate(Date date) {
		 String[] weekOfDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
		 Calendar calendar = Calendar.getInstance();
		 if(date != null){
			 calendar.setTime(date);
		 }
		 int w = calendar.get(Calendar.DAY_OF_WEEK) - 1;
		 if (w < 0){
			 w = 0;
		 }
		 return weekOfDays[w];
	}

	 /**
     * 获取搜索区域编码的区间值
     * @return String
     */
    public static String getAreaRegion(String areaId){
    	String areaRegion = "0";
    	if(areaId!=null){
    		if(areaId.endsWith("0000")){ //130000
    			areaRegion = String.valueOf((Integer.valueOf(areaId)+9999)); //139999
    		}else if(areaId.endsWith("00")){ //130100
    			areaRegion = String.valueOf((Integer.valueOf(areaId)+99)); //130199
    		}else{
    			areaRegion = areaId;
    		}
    	}
    	return areaRegion;
    }

	 /**
     * 判断一个字符是否是中文
     * @return String
     */
	public static boolean isChinese(char c) {
		return c >= 0x4E00 &&  c <= 0x9FA5;// 根据字节码判断
	}

	 /**
     * 判断一个字符串是否含有中文
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
     * @return String
     */
	public static Integer castToInteger(Object obj){
    	String objStr=String.valueOf(obj);
		Integer objInt;
    	try {
			objInt=Integer.parseInt(objStr);
		}catch (Exception e){
			objInt=0;
		}
    	return objInt;
	}

	/**
     * 获取今天的日期
     * @return String
     */
	public static String getToday(){
		Date day=new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		return df.format(day);
	}

	/**
     * 获取昨天的日期
     * @return String
     */
	public static String getYesterday(){
		Date d=new Date(System.currentTimeMillis()-1000*60*60*24);
		 SimpleDateFormat sp=new SimpleDateFormat("yyyy-MM-dd");
		 return sp.format(d);
	}
	
	/**
     * 获取两天之间差距天数
     * @return String
     */
	public static long  differenceDay(String sDate,String eDate){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        long m = 0;
        long t = 0;
		try {
			m = sdf.parse(sDate).getTime() - sdf.parse(eDate).getTime();
			t = ( m / (1000 * 60 * 60 * 24) );
		} catch (ParseException e) {
			e.printStackTrace();
			return 0;
		}
         return t;
	}

	/**
	* 获得该月第一天
	* @param year
	* @param month
	* @return
	*/
	public static String getFirstDayOfMonth(int year,int month){
	        Calendar cal = Calendar.getInstance();
	        //设置年份
	        cal.set(Calendar.YEAR,year);
	        //设置月份
	        cal.set(Calendar.MONTH, month-1);
	        //获取某月最小天数
	        int firstDay = cal.getActualMinimum(Calendar.DAY_OF_MONTH);
	        //设置日历中月份的最小天数
	        cal.set(Calendar.DAY_OF_MONTH, firstDay);
	        //格式化日期
	        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	        String firstDayOfMonth = sdf.format(cal.getTime());
	        return firstDayOfMonth;
	    }

	/**
	* 获得该月最后一天
	* @param year
	* @param month
	* @return
	*/
	public static String getLastDayOfMonth(int year,int month){
	        Calendar cal = Calendar.getInstance();
	        //设置年份
	        cal.set(Calendar.YEAR,year);
	        //设置月份
	        cal.set(Calendar.MONTH, month-1);
	        //获取某月最大天数
	        int lastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
	        //设置日历中月份的最大天数
	        cal.set(Calendar.DAY_OF_MONTH, lastDay);
	        //格式化日期
	        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	        String lastDayOfMonth = sdf.format(cal.getTime());
	        return lastDayOfMonth;
	}

	/**
	* 获得上个月份
	* @param year
	* @param month
	* @return
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
        c.setTime(date);
        c.add(Calendar.MONTH, -1);
        String lastDate = c.get(Calendar.YEAR) + "-"+ String.format("%02d",(c.get(Calendar.MONTH) + 1));
        return lastDate;
    }

	/**
	* 格式化 成   xxxx年xx月
	* @param year
	* @param month
	* @return
	*/
    public static String formatYearAndMonth_Zh(String date){
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
	* @param year
	* @param month
	* @return
	*/
    public static String formatYearAndMonthAndDate_Zh(String date){
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
	 * @Title: getNotLineStr
	 * @param str
	 * @return String
	 * @author Laihz
	 * @date Oct 10, 2016
	 */
	public static String getNotLineStr(String str) {
		String result = "";
		char s[] = str.toCharArray();
		if (s.length > 0) {
			for (int i = 0; i < s.length; i++) {
				if ('_' != s[i]) {
					result += s[i];
				}
			}
		}
		return result;
	}


	/**
	 * Description: 规范只有一个空格
	 * @Title: getStringByBlank
	 * @param str
	 * @return String
	 * @author Laihz
	 * @date Oct 11, 2016
	 */
	public static String getStringByBlank(String str) {
		String a = "";
		if (str != null && !"".equals(str)) {
			StringTokenizer s = new StringTokenizer(str.trim());
			while (s.hasMoreTokens()) {
				a += " " + s.nextToken();
			}
		}
		return a.trim();
	}

	/**
	 * 
	 * Description: 判断字符串str0是否在str里
	 * @Title: checkStr
	 * @param str
	 * @param str0
	 * @return boolean false 没有 TRUE 有
	 * @author Laihz
	 * @date Sep 17, 2016
	 */
	public static boolean checkStr(String str, String str0) {
		boolean result = false;
		if (str != null && !"".equals(str)) {
			str = getSplitString(str, ",");
			String[] a = str.split(",");
			if (a.length > 0) {
				for (int i = 0; i < a.length; i++) {
					String b = a[i];
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
	 * @Title: filtSqlStr
	 * @param str
	 * @return String
	 * @author Laihz
	 * @date Sep 17, 2016
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
	 * @author Laihz
	 * @date Nov 06,2016
	 * @param str  1,2,3
	 * @return	'1','2','3'
	 */
	public static String filtrateStringToSqlQuotes(String str) {
		if(str != null) {
			String strTemp[] = str.split(",");
			str = "";
			for(int i=0;i<strTemp.length;i++) {
				str += "'" + strTemp[i] + "'";
				if(i!=strTemp.length-1) {
					str += ",";
				}
			}
		} else {
			str = "";
		}
		return str;
	}

	/**
	 * Description: 转化为单引号 字符串
	 * @Title: getSqlStrBySlist
	 * @param str
	 * @return String
	 * @author Laihz
	 * @date Oct 11, 2016
	 */
	public static String getSqlStrBySlist(String str) {
		String result = "";
		if ("".equals(str.trim()) || str == null) {
			return result;
		} else {
			String a[] = str.split(",");
			if (a.length > 0) {
				result += "'" + a[0] + "'";
				for (int i = 1; i < a.length; i++) {
					result += ",'" + a[i] + "'";
				}
			}
		}
		return result;
	}

	/**
	 * Description: 去除数组重复元素
	 * @Title: getStringArray
	 * @param a 主数组
	 * @param b  对比数组
	 * @return String[]
	 * @author Laihz
	 * @date Aug 22, 2016
	 */
	public static String[] getStringArray(String[] a, String[] b) {
		if (a != null && b != null) {
			if (b.length > a.length) {
				throw new IllegalArgumentException("索引出界");
			} else {
				for (int i = 0; i < b.length; i++) {
					a = delArr(a, b[i]);
				}
			}
		}
		return a;
	}


	/**
	 * Description: 删除数组指定元素
	 * @Title: delArr
	 * @param arr
	 * @param index
	 * @return String[]
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
				if (arr != null && arr.length > 0 ) {
					for (int i = 0; i < arr.length; i++) {
						if (getNotNullStrValue(arr[i]).equals(index))
							continue;
						a[temp++] = arr[i];
					}
				}
				result = a;
			}
		}
		return result;
	}

	/**
	 * Description: 除去逗号分隔字符串中 指定字符
	 * @Title: delIndex
	 * @param str
	 * @param ind
	 * @return String
	 * @author Laihz
	 * @date Aug 3, 2016
	 */
	public static String delIndex(String str, String ind) {
		String a[] = str.split(",");
		a = delArr(a, ind);
		return getStrByArr(a);
	}

	/**
	 * Description: 将字符串数组转化为逗号分隔字符串
	 * @Title: getStrByArr
	 * @param arr
	 * @return String
	 * @author Laihz
	 * @date Aug 22, 2016
	 */
	public static String getStrByArr(String[] arr) {
		String result = "";
		if (arr == null) {
			return result;
		} else if (arr != null && arr.length > 0) {
			result = arr[0];
			for (int i = 1; i < arr.length; i++) {
				if (arr[i] != null) {
					result += "," + arr[i];
				}
			}
		}
		return result;
	}

	/**
	 * Description: 截取指定长度字符串,并替换尾字符
	 * @Title: substring
	 * @param str 原字符串
	 * @param toCount  长度
	 * @param more 替换符号
	 * @return String
	 * @author Laihz
	 * @date Jun 10, 2016
	 */
	public static String substring(String str, int toCount, String more) {
		int reInt = 0;
		String reStr = "";
		if (str == null) {
			return "";
		}
		char tempChar[] = str.toCharArray();
		if (more != null) {
			toCount -= more.length();
		}
		for (int kk = 0; kk < tempChar.length && toCount > reInt; kk++) {
			String s1 = String.valueOf(tempChar[kk]);
			byte b[] = s1.getBytes();
			reInt += b.length;
			reStr = (new StringBuilder(String.valueOf(reStr))).append(tempChar[kk]).toString();
		}
		if (toCount == reInt || toCount == reInt - 1) {
			reStr = (new StringBuilder(String.valueOf(reStr))).append(more).toString();
		}
		return reStr;
	}

	/**
	 * Description: 将字符串转化为分隔符 的字符串（去重复无顺序）
	 * @Title: getSplitString
	 * @param str  字符串
	 * @param type 分隔符
	 * @return String
	 * @author Laihz
	 * @date Sep 13, 2016
	 */
	public static String getSplitString(String str, String type) {
		String c = "";
		if ("".equals(type) || type == null) {
			type = ",";
		}
		if (str == null && "".equals(str)) {
			return "";
		} else {
			str = getSigleStr(str, type);
			String[] b = str.split(type);
			List list = new LinkedList();
			for (int i = 0; i < b.length; i++) {
				if (b[i] != null && !"".equals(b[i])) {
					list.add(b[i]);
				}
			}
			if (list.size() > 0) {
				c = (String) list.get(0);
				for (int i = 1; i < list.size(); i++) {
					c += type + list.get(i);
				}
			}
		}
		return c;
	}

	/**
	 * Description: 去重复
	 * @Title: getSigleStr
	 * @param Str
	 * @param type
	 * @return String
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
		String result = "";
		String s[] = Str.split(type);
		Map<String, Object> map = new HashMap<String, Object>();
		if (s.length > 0) {
			for (int i = 0; i < s.length; i++) {
				map.put(s[i], null);
			}
		}
		List<String> list = new ArrayList<String>();
		Iterator<String> it = map.keySet().iterator();
		while (it.hasNext()) {
			String ob = (String) it.next();
			list.add(ob);
		}
		Iterator<String> its = list.iterator();
		while (its.hasNext()) {
			String ob = (String) its.next();
			result += type + ob;
		}
		return result;
	}

	/**
	 * Description: Xml特殊字符串出来
	 * @Title: filterXml
	 * @param Str
	 * @param type
	 * @return String
	 * @author Laihz
	 * @date Sep 13, 2016
	 */
	public static String filterXml(String value) {
		if (value == null)
			return null;
		char content[] = new char[value.length()];
		value.getChars(0, value.length(), content, 0);
		StringBuffer result = new StringBuffer(content.length + 50);
		for (int i = 0; i < content.length; i++)
			switch (content[i]) {
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
				result.append(content[i]);
				break;
			}
		return result.toString();
	}
	
	

	/**
	 * Description: mapZ换成一个Bean
	 * @Title: mapToBean
	 * @param mpFrom
	 * @param objTo
	 * @return Object
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
			logger.error("map转为Bean失败：" + e.getMessage());
		}
		return objTo;
	}
	
	/**
	 * 逗号分隔的字符串数组转化为List
	 * @author laihz 2016-03-30
	 * @param str
	 * @return list
	 */
	public static List<String> str2List(String str){
		List<String> list = new ArrayList<String>();
		String[] strArray = str.split(",");
		for (int i = 0; i < strArray.length; i++) {
			list.add(strArray[i]);
		}
		return list;
	}
	
	/**
	 * 中午字符串转成ascii
	 * @author laihz 2016-04-21
	 * @param str
	 * @return list
	 */
     public static String gbEncoding(final String gbString) {   
     char[] utfBytes = gbString.toCharArray();   
           String unicodeBytes = "";   
            for (int byteIndex = 0; byteIndex < utfBytes.length; byteIndex++) {   
                 String hexB = Integer.toHexString(utfBytes[byteIndex]);   
                   if (hexB.length() <= 2) {   
                       hexB = "00" + hexB;   
                  }   
                   unicodeBytes = unicodeBytes + "\\u" + hexB;   
               }   
               System.out.println("unicodeBytes is: " + unicodeBytes);   
               return unicodeBytes;   
      }   
         
 	/**
 	 * ascii转成中文字符串
 	 * @author laihz 2016-04-21
 	 * @param str
 	 * @return list
 	 */
     public static String decodeUnicode(final String dataStr) {   
            int start = 0;   
              int end = 0;   
             final StringBuffer buffer = new StringBuffer();   
              while (start > -1) {   
                 end = dataStr.indexOf("\\u", start + 2);   
                  String charStr = "";   
                  if (end == -1) {   
                      charStr = dataStr.substring(start + 2, dataStr.length());   
                 } else {   
                     charStr = dataStr.substring(start + 2, end);   
                  }   
                  char letter = (char) Integer.parseInt(charStr, 16); // 16进制parse整形字符串。   
                buffer.append(new Character(letter).toString());   
                start = end;   
              }   
              return buffer.toString();   
    }   
     
     /**
      * 时间字符串 ---> 触发器表达式
      * dateStr ---> cronExpress
      * @author laihz
      * @param datetime
      * @return cronExpress
      */
     public static String dateTimeToCronExpression(String dateStr){
    	 /**
    	    1、如官方文档解释的那样，问号(?)的作用是指明该字段‘没有特定的值’；
			2、星号(*)和其它值，比如数字，都是给该字段指明特定的值，只不过用星号(*)代表所有可能值；
			3、cronExpression对日期和星期字段的处理规则是它们必须互斥，即只能且必须有一个字段有特定的值，另一个字段必须是‘没有特定的值’，只能在日或周字段上使用；
			4、问号(?)就是用来对日期和星期字段做互斥的。
    	  */
    	 String year,month,day,hour,minute,date,time,cronExpression;
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
    	 cronExpression ="0 "+minute+" "+hour+" "+day+" "+month+" ? "+year; //秒 分 时 日 月 周 年
    	 return cronExpression;
     }
     
     /**
      * 比较时间
      * dateTime1 < dateTime2 -->return true 
      * dateTime1 > dateTime2 -->return false 
      * @author laihz
      * @param dateTime1,dateTime2
      * @throws ParseException 
      */
     public static boolean compareDateTime(String dateStr1,String dateStr2) throws ParseException{
    	 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    	 Date date1 = sdf.parse(dateStr1);
    	 Date date2 = sdf.parse(dateStr2);
    	 if(date1.before(date2)){
    		 return true;
    	 }else{
    		 return false;
    	 }
     }
     
     /**
      * 判断升级任务开始时间是否在两周以内
      * @author laihz 2016-05-30
      * @param sourceDateStr
      * @return 0  距今超过两周
      * @return 1  距今两周内
      * @return -1 时间过时
      * @return -2 解析出错
      */
     public static int isDTTwoWeekLater(String sourceDateStr){
    	 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    	 try {
			Date sourceDate = sdf.parse(sourceDateStr);
			Date currDate = sdf.parse(sdf.format(new Date()));
			long cha = sourceDate.getTime() - currDate.getTime(); 
	        if(cha<0){
	          return -1; 
	        }
	        double result = cha * 1.0 / (1000 * 60 * 60);
	        if(result <= 24 * 14){ 
	             return 1; 
	        }else{ 
	             return 0; 
	        } 
		} catch (ParseException e) {
			e.printStackTrace();
			return -2;
		}
     }
     
     /**
      * 拼装升级结果字符串
      * @param str source
      * @param newStr destination
      * @param type true:逗号隔开的"成功"串     false:逗号隔开的"失败"串
      * @return
      */
     public static String replaceCharStr(String str,String newStr,boolean type){
    	 String result = "";
    	 String[] strArrary = str.split(",");
    	 if (type) {
    		 result = strArrary[0].toString().replaceAll("0", newStr)+","+strArrary[1];
    	 }else{
    		 result = strArrary[0]+","+strArrary[1].toString().replaceAll("0", newStr);
    	 }
    	 return result;
     }
     
     /**
      * 传入一个数组后获得数组中的最大值和最小值(不取0)
      * @param 
      * @param 
      * @param 
      * @return
      */
     public static Map<String, Integer> getArrayMaxAndMin(int array[]){
    	 Map<String, Integer> map = new HashMap<String, Integer>();
    	 int i,min = 0,max = 0;
    	 for (int j = 0; j < array.length; j++) {
			if (array[j] != 0) {
				min=max=array[j];
				break;
			}
    	 }
    	 for(i=0;i<array.length;i++){
	    	 if(array[i]>max && array[i] != 0){
	    		 // 判断最大值
	    		 max=array[i];
	    	 }
	    	 if(array[i]<min && array[i] != 0){
	    		 // 判断最小值
	    		 min=array[i];
	    	 }
    	 }
    	 map.put("max", max);
    	 map.put("min", min);
    	 return map;
     }
     
    /**
	 * @param 版本比较
 	 * @return true:ver1 < ver2    false:ver1 >= ver2
 	 */
 	public static boolean compareVersion(String ver1, String ver2){
 		if(!ver1.equals(ver2)){
 	 		String[] verArr1 = ver1.split("\\.");
 	 		String[] verArr2 = ver2.split("\\.");
 	 		int length = verArr1.length < verArr2.length ? verArr1.length : verArr2.length;
 	 		for (int i = 0; i < length; i++){
 	 			if (Integer.parseInt(verArr2[i]) > Integer.parseInt(verArr1[i])){
 	 				return true;
 	 			}else if (Integer.parseInt(verArr2[i]) < Integer.parseInt(verArr1[i])){
 	 				return false;
 	 			}
 	 		}
 	 		return true; 	 		
 		}else{
 			return false;
 		}
 	}
     
    /**
	 * @param 获取一个月的天数
	 * @return  
	 * */
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
	 * @param 格式化时间
	 * @return  
	 * */
    public static String dateForMater(){
        Date date = new  Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(date);
    }

    /**
	 * @param 将时间转换为时间戳
	 * @return  
	 * */
    public static String dateToStamp(String s) throws ParseException{
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = simpleDateFormat.parse(s);
        long ts = date.getTime();
        res = String.valueOf(ts);
        return res;
    }
    /***
	 * @param list 集合
	 * @return "1,2,3"
	 * ***/
    public static String collection2String(List<String> arr){
		StringBuffer sb=new StringBuffer();
    	for(String str:arr){
    		sb.append("'").append(str).append("'").append(",");
		}
		return sb.length()>0?sb.substring(0,sb.length()-1):null;
	}
    /**
	 * @param input 输入两位小数的浮点型字符串
	 * @return  返回有效字符串
	 * */
	public static String switchFloatReal(String input){//转换格式33.3-->33.3;33.0-->33
		int p=input.indexOf(".");
		if(p!=-1){
			String pStr=input.substring(p+1);
			if(pStr.equals("00")){
				input=input.substring(0,p-1);
			}else if("0".equals(pStr.substring(1))){
				input=input.substring(0,p+1);
			}
		}
		return input;

	}
	public static String lpad(String str,int num,String pad){
		String n_str=str;
		if(str==null)
			n_str= " ";
		if(!(str.length()>=num)){
			for(int i=str.length();i <num;i++){
				n_str=pad+n_str;
			}
		}
		return n_str;
	}

	public static String phoneCutEncrypt(String phone){
		return phone.replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2");
	}

	public static String identityCutEncrypt(String identity){
		return identity.replaceAll("(\\d{4})\\d{10}(\\d{4})", "$1**********$2");
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
