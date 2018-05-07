package com.myschool.utils;

import javax.xml.datatype.XMLGregorianCalendar;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 日期操作辅助类
 */
public final class DateUtil {

	private DateUtil() {
	}

	public static String PATTERN = "yyyy-MM-dd";

	public static String PATTERN_DATE_TIME = "yyyy-MM-dd HH:mm:ss";

	/**
	 * 字符串转换成日期
	 * @param str
	 * @return
	 */
	public static Date strToDate1(String str){
		return strToDate(str , PATTERN_DATE_TIME);
	}



	/**
	 * 格式化日期
	 * 
	 * @param date
	 * @return
	 */
	public static final String format(Object date) {
		return format(date, PATTERN);
	}

	/**
	 * 日期计算,并格式化
	 * @param date
	 * @param amount
	 * @param pattern
     * @return
     */
	public static final String addDateFormate(Date date,int amount,String pattern) {
		if (date == null) {
			return null;
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DATE, amount);
		return format(calendar.getTime(),pattern);
	}

	/**
	 * 北京时间转换为UTC时间
	 * @param d
	 * @return
	 */
	public static Date getUtcDateFromBeijingDate(Date d){
		String formatStr = "yyyy-MM-dd HH:mm:ss";
		return getUtcDateFromDate(d,formatStr);
	}

	/**
	 * 格式化日期
	 * 
	 * @param date
	 * @param pattern
	 * @return
	 */
	public static final String format(Object date, String pattern) {
		if (date == null) {
			return null;
		}
		if (pattern == null) {
			return format(date);
		}
		return new SimpleDateFormat(pattern).format(date);
	}

	public static Date strToDateByDay(String str){
		str = str.replace("T", " ");
		String formatStr = "yyyy-MM-dd";
		return strToDate(str, formatStr);
	}

	/**
	 * 将“2014-09-16T14:54:34”格式的时间转换成Date类型
	 * @param str
	 * @return
	 */
	public static Date strToDate2(String str){
		str = str.replace("T", " ");
		String formatStr = "yyyy-MM-dd HH:mm:ss";
		return strToDate(str, formatStr);
	}

	/**
	 * 获取日期
	 * 
	 * @return
	 */
	public static final String getDate() {
		return format(new Date());
	}

	/**
	 * 获取当前日期时间
	 * @return
	 */
	public static final String getDateTime() {
		return format(new Date(),PATTERN_DATE_TIME);
	}

	/**
	 * 获得今天的日期
	 * @return
	 */
	public static String getTodayDateStr(){
		Date date = new Date();
		SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
		String d = dateformat.format(date) + " 00:00:00";
		return d;
	}

	public static Date getTodayDate(){
		Date date = new Date();
		SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
		String d = dateformat.format(date) + " 00:00:00";
		try {
			date = dateformat.parse(d);
		} catch (Exception ignore) {
		}
		return date;
	}

	public static Date xmlGregorianCalendarToDate(XMLGregorianCalendar cal){
		GregorianCalendar ca = cal.toGregorianCalendar();
		return ca.getTime();
	}

	/**
	 * 根据指定日期获取yyyy-MM-dd HH:mm:ss日期时间
	 * @param date
	 * @return
     */
	public static final String getDateTime(Date date) {
		return format(date,PATTERN_DATE_TIME);
	}



	/**
	 * 获取日期
	 * 
	 * @param pattern
	 * @return
	 */
	public static final String getDateTime(String pattern) {
		return format(new Date(), pattern);
	}

	/**
	 * 日期计算
	 * 
	 * @param date
	 * @param field
	 * @param amount
	 * @return
	 */
	public static final Date addDate(Date date, int field, int amount) {
		if (date == null) {
			return null;
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(field, amount);
		return calendar.getTime();
	}

	/**
	 * 字符串转换为日期:不支持yyM[M]d[d]格式
	 * 
	 * @param date
	 * @return
	 */
	public static final Date stringToDate(String date) {
		if (date == null) {
			return null;
		}
		String separator = String.valueOf(date.charAt(4));
		String pattern = "yyyyMMdd";
		if (!separator.matches("\\d*")) {
			pattern = "yyyy" + separator + "MM" + separator + "dd";
			if (date.length() < 10) {
				pattern = "yyyy" + separator + "M" + separator + "d";
			}
		} else if (date.length() < 8) {
			pattern = "yyyyMd";
		}
		pattern += " HH:mm:ss.SSS";
		pattern = pattern.substring(0, Math.min(pattern.length(), date.length()));
		try {
			return new SimpleDateFormat(pattern).parse(date);
		} catch (ParseException e) {
			return null;
		}
	}

	public static Date strToDate(String dateStr,String formatStr){
		DateFormat dd=new SimpleDateFormat(formatStr);
		try {
			return dd.parse(dateStr);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 间隔天数
	 * 
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public static final Integer getDayBetween(Date startDate, Date endDate) {
		Calendar start = Calendar.getInstance();
		start.setTime(startDate);
		start.set(Calendar.HOUR_OF_DAY, 0);
		start.set(Calendar.MINUTE, 0);
		start.set(Calendar.SECOND, 0);
		start.set(Calendar.MILLISECOND, 0);
		Calendar end = Calendar.getInstance();
		end.setTime(endDate);
		end.set(Calendar.HOUR_OF_DAY, 0);
		end.set(Calendar.MINUTE, 0);
		end.set(Calendar.SECOND, 0);
		end.set(Calendar.MILLISECOND, 0);

		long n = end.getTimeInMillis() - start.getTimeInMillis();
		return (int) (n / (60 * 60 * 24 * 1000L));
	}

	/**
	 * 间隔月
	 * 
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public static final Integer getMonthBetween(Date startDate, Date endDate) {
		if (startDate == null || endDate == null || !startDate.before(endDate)) {
			return null;
		}
		Calendar start = Calendar.getInstance();
		start.setTime(startDate);
		Calendar end = Calendar.getInstance();
		end.setTime(endDate);
		int year1 = start.get(Calendar.YEAR);
		int year2 = end.get(Calendar.YEAR);
		int month1 = start.get(Calendar.MONTH);
		int month2 = end.get(Calendar.MONTH);
		int n = (year2 - year1) * 12;
		n = n + month2 - month1;
		return n;
	}

	/**
	 * 间隔月，多一天就多算一个月
	 * 
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public static final Integer getMonthBetweenWithDay(Date startDate, Date endDate) {
		if (startDate == null || endDate == null || !startDate.before(endDate)) {
			return null;
		}
		Calendar start = Calendar.getInstance();
		start.setTime(startDate);
		Calendar end = Calendar.getInstance();
		end.setTime(endDate);
		int year1 = start.get(Calendar.YEAR);
		int year2 = end.get(Calendar.YEAR);
		int month1 = start.get(Calendar.MONTH);
		int month2 = end.get(Calendar.MONTH);
		int n = (year2 - year1) * 12;
		n = n + month2 - month1;
		int day1 = start.get(Calendar.DAY_OF_MONTH);
		int day2 = end.get(Calendar.DAY_OF_MONTH);
		if (day1 <= day2) {
			n++;
		}
		return n;
	}

	/**
	 * 根据秒数返回Date
	 * @param second 秒
	 * @return Date
	 */
	public static Date getDateBySecond(long second){
		Calendar ca = Calendar.getInstance();
		ca.setTimeInMillis(second * 1000);
		return ca.getTime();
	}

	/**
	 * 将某个日期增加指定月数，并返回结果　如果传入负数，则为减
	 *
	 * @param date		 要操作的日期对象
	 * @param ammount	要增加月的数量
	 * @return 结果日期对象
	 */
	public static Date addMonth(final Date date, final int ammount) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.MONTH, ammount);
		return c.getTime();
	}

	/**
	 * 将某个日期增加指定天数，并返回结果   如果传入负数，则为减
	 * @param date   　要操作的日期对象
	 * @param ammount  要增加天的数
	 * @return 结果日期对象
	 */
	public static Date addDay(final Date date, final int ammount) {
		return  addDate(date,Calendar.DATE,ammount);
	}

	/**
	 * 将某个日期增加指定小时，并返回结果   如果传入负数，则为减
	 *
	 * @param date   　要操作的日期对象
	 * @param ammount  要增加小时的数
	 * @return 结果日期对象
	 */
	public static Date addHour(final Date date, final int ammount) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.HOUR, ammount);
		return c.getTime();
	}

	/**
	 * 将某个日期增加指定分钟，并返回结果   如果传入负数，则为减
	 *
	 * @param date   　要操作的日期对象
	 * @param ammount  要增加秒数
	 * @return 结果日期对象
	 */
	public static Date addMinute(final Date date, final int ammount) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.MINUTE, ammount);
		return c.getTime();
	}

	public static String getUtcStrFromDate(Date d, String formatStr) {
		if (d == null) {
			return null;
		}
		SimpleDateFormat utcFormater = new SimpleDateFormat(formatStr);
		utcFormater.setTimeZone(TimeZone.getTimeZone("UTC"));
		return utcFormater.format(d.getTime());
	}

	/**
	 * 北京时间转换为UTC时间
	 * @param d
	 * @return
	 */
	public static String getUtcISODateFromBeijingDate(Date d){
		if(d==null){
			return null;
		}
		String formatStr = "yyyy-MM-dd'T'HH:mm:ss";
		return getUtcStrFromDate(d,formatStr);
	}


	public static Date getUtcDateFromDate(Date d, String formatStr) {
		return stringToDate(getUtcStrFromDate(d, formatStr));
	}

	public static String dateToStrWithTime(Date date) {
		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(date);
	}

	public static String dateToStrWithTime(Date date, String pattern) {
		DateFormat sdf = new SimpleDateFormat(pattern);
		return sdf.format(date);
	}

    public static String dateToEnglishStr(Date date){
        DateFormat sdf = new SimpleDateFormat("MMM d, yyyy", Locale.ENGLISH);
        return sdf.format(date);
    }

	public static String dateToEnglishStr(Date date, String formatStr){
		DateFormat sdf = new SimpleDateFormat(formatStr, Locale.ENGLISH);
		return sdf.format(date);
	}



	/**
	 * 比较两个日期的大小
	 * date1 > date2 返回	1
	 * date1 < date2 返回	-1
	 * date1 = date2 返回	0
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static int compareDate(Date date1, Date date2){
		if(date1 != null && date2 != null){
			if (date1.getTime() > date2.getTime()) {
				return 1;
			} else if (date1.getTime() < date2.getTime()) {
				return -1;
			} else {
				return 0;
			}
		}
		return 0;
	}

	/**
	 * 计算两个日期之间相差的天数
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static int daysBetween(Date date1, Date date2){
		Calendar cal = Calendar.getInstance();
		cal.setTime(date1);
		long time1 = cal.getTimeInMillis();
		cal.setTime(date2);
		long time2 = cal.getTimeInMillis();
		long between_days=(time2-time1)/(1000*3600*24);

		return Integer.parseInt(String.valueOf(between_days));
	}

	/**
	 * 得到月份的最大天数
	 * @param year 年
	 * @param month 月
	 * @return int
	 */
	public static int getMonthMaxDays(int year, int month) {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.MONTH, month-1);

		return cal.getActualMaximum(Calendar.DATE);
	}

	public static String dateToStrWithFormat(Date date, String formatStr){
		DateFormat sdf = new SimpleDateFormat(formatStr);
		return sdf.format(date);
	}

	/**
	 * 获取当前日期是星期几
	 * @param dt        当前日期
	 * @return          当前日期是星期几
	 */
	public static String getWeekOfDate(Date dt) {
		String[] weekDays = {"7", "1", "2", "3", "4", "5", "6"};
		Calendar cal = Calendar.getInstance();
		cal.setTime(dt);
		int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
		if (w < 0){
			w = 0;
		}
		return weekDays[w];
	}
}
