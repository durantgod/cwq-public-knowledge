package org.java.util;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.flink.shaded.netty4.io.netty.util.internal.StringUtil;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * <p>
 * 对日期java.util.Date的操作
 * </p>
 */
public class DateUtil {

	/** yyyy-MM-dd HH:mm:ss */
	public final static String YYYYMMDDHHMMSS = "yyyy-MM-dd HH:mm:ss";
	/** yyyy-MM-dd HH:mm:ss.SSS */
	public final static String YYYYMMDDHHMMSS_SSS_MILLIS = "yyyy-MM-dd HH:mm:ss.SSS";
	/** yyyy-MM-dd'T'HH:mm:ss.SSS'Z' */
	public final static String YYYYMMDDHHMMSS_UTC = "yyyy-MM-dd'T'HH:mm:ss.SSSZ";
	/** YYYY/M/d H:m:s */
	public final static String YYYYMDHMS = "YYYY/M/d H:m:s";
	/** yyyyMMddHHmmss */
	public final static String YYYYMMDDHHMMSS_SHORT = "yyyyMMddHHmmss";
	/** yyMMddHHmmss */
	public final static String YYMMDDHHMMSS = "yyMMddHHmmss";
	/** yyyyMMddHHmmssSSS */
	public final static String YYYYMMDDHHMMSS_MILLIS = "yyyyMMddHHmmssSSS";
	/** HH:mm:ss */
	public final static String HHMMSS = "HH:mm:ss";
	/*HH:mm*/
	public final static String HHMM = "HH:mm";
	/** yyyy-MM-dd HH */
	public final static String YYYYMMDDHH = "yyyy-MM-dd HH";
	/** yyyyMMddHH */
	public final static String YYYYMMDDHH_SHORT = "yyyyMMddHH";
	/** yyyyMM */
	public final static String YYYYMM = "yyyyMM";
	/**yyyy-MM*/
	public final static String YYYYMM_STANDARD = "yyyy-MM";
	/** yyyyMMdd */
	public final static String YYYYMMDD_SHORT = "yyyyMMdd";
	/** yyyy-MM-dd */
	public final static String YYYYMMDD = "yyyy-MM-dd";

	public final static String YYYY = "yyyy";

	/**
	 * yyyy-MM-dd 格式
	 */
	public static final String DEFAULT_DATE_PATTERN = "yyyy-MM-dd";

	/** yyyy-MM-dd 23:59:59*/
	public final static String YYYYMMDD_23_59_59 = "yyyy-MM-dd 23:59:59";
	/** yyyy-MM-dd 00:00:00*/
	public final static String YYYYMMDD_00_00_00 = "yyyy-MM-dd 00:00:00";
	/** yyyy-MM-dd HH:59:59*/
	public final static String YYYYMMDD_HH_59_59 = "yyyy-MM-dd HH:59:59";
	/** yyyy-MM-dd HH:00:00*/
	public final static String YYYYMMDD_HH_00_00 = "yyyy-MM-dd HH:00:00";
	/** HH:00 */
	public final static String HH_00 = "HH:00";
	/** HH:mm */
	public final static String HH_MM = "HH:mm";
	/** MM/dd */
	public final static String MM_DD = "MM/dd";
	/** MM-dd */
	public final static String MM_DD2 = "MM-dd";
	/** MM.dd */
	public final static String MM_DD3 = "MM.dd";
	/** HH */
	public final static String HH = "HH";

	public final static String IOS8601 = "yyyy-MM-dd'T'HH:mm:ssXXX";

	/**
	 * 小时对应的毫秒值
	 */
	private final static long HOUR = 1000 * 60 * 60L;

	/**
	 * 小时对应的毫秒值
	 */
	private final static long DAY = 1000 * 24 * 60 * 60L;

	/**
	 * 分钟对应的毫秒值
	 */
	private final static long MINUTE = 1000 * 60L;

	/**
	 * 秒对应的毫秒值
	 */
	private final static long SECOND = 1000L;

	/** yyyy-MM-dd'T'HH:mm:ss'ZZ' */
	public final static String YYYYMMDDHHMMSS_IOS8601 = "yyyy-MM-dd'T'HH:mm:ssZZ";

	/** yyyy-MM-dd'T'HH:mm:ss */
	public final static String YYYYMMDD_T_HHMMSS = "yyyy-MM-dd'T'HH:mm:ss";

	public static final String YYYYMMDDHHMM = "yyyy-MM-dd HH:mm";

	public static final String CN_DATE_STANDARD = "yyyy年MM月dd日";

	public static final DateTimeFormatter localDateTimeFormatter = DateTimeFormatter.ofPattern(YYYYMMDDHHMMSS);
	public static final DateTimeFormatter localDateFormatter = DateTimeFormatter.ofPattern(YYYYMMDD);
	public static final DateTimeFormatter localTimeFormatter = DateTimeFormatter.ofPattern(HHMMSS);
	public static final DateTimeFormatter localTimeMinuteFormatter = DateTimeFormatter.ofPattern(HH_MM);
	public static final DateTimeFormatter localDateCnStandardFormatter = DateTimeFormatter.ofPattern(CN_DATE_STANDARD);

	public static final ZoneOffset beijingZoneOffset = ZoneOffset.ofHours(8);

	/**
	 * <p>
	 * 用"yyyy-MM-dd HH:mm:ss"格式格式化日期时间
	 * </p>
	 *
	 * @param date
	 *            待格式化的日期
	 * @return
	 */
	public static String date2string(Date date) {
		if (null == date) {
			return null;
		}
		return DateFormatUtils.format(date, YYYYMMDDHHMMSS);
	}

	/**
	 * <p>
	 * 用"yyyy-MM-dd HH:mm:ss"格式格式化日期时间
	 * </p>
	 *
	 * @param date
	 *            待格式化的日期
	 * @return
	 */
	public static String date3string(Date date) {
		if (null == date) {
			return null;
		}
		return DateFormatUtils.format(date, YYMMDDHHMMSS);
	}

	/**
	 * <p>
	 * 用"yyyy-MM-dd HH:mm:ss"格式格式化日期时间
	 * </p>
	 *
	 * @param date
	 *            待格式化的日期
	 * @return
	 */
	public static String date2string(long date) {
		return DateFormatUtils.format(date, YYYYMMDDHHMMSS);
	}

	/**
	 * <p>
	 * 用指定格式格式化日期时间
	 * </p>
	 *
	 * @param date
	 *            待格式化的日期
	 * @return
	 */
	public static String date2string(Date date, String format) {
		if (date == null){
			return "";
		}
		return DateFormatUtils.format(date, format);
	}

	/**
	 * <p>
	 * 用指定格式格式化日期时间
	 * </p>
	 *
	 * @param date
	 *            待格式化的long型日期
	 * @return
	 */
	public static String date2string(long date, String format) {
		return DateFormatUtils.format(date, format);
	}

	/**
	 * <p>
	 * 取得系统的当前时间
	 * </p>
	 *
	 * @return
	 */
	public static long currentTimeMillis() {
		return System.currentTimeMillis();
	}

	/**
	 *            日期字符串 例如：0000-00-00 00:00:00
	 *            格式化字符串，例如：yyyy-MM-dd HH:mm:ss
	 * @return Date 日期类型
	 */
	public static Date string2Date(String date, String format) {
		Date d = null;
		SimpleDateFormat sf = null;
		if (StringUtils.isEmpty(date)) {
			return null;
		}
		try {
			sf = new SimpleDateFormat(format);
			d = sf.parse(date);
		} catch (Exception e) {
			d = null;
		}
		return d;
	}

	/**
	 * 获取下周指定日期的时间
	 *
	 * @param weekIndex
	 *            星期一：1，星期二：2，星期三：3，星期四：4。。。
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public static Date getNextWeekDay(int weekIndex) {
		Calendar cd = Calendar.getInstance();
		int mondayPlus = 0;
		// 获得今天是一周的第几天，星期日是第一天，星期一是第二天......
		// 因为按中国礼拜一作为第一天所以这里减1
		int dayOfWeek = cd.get(Calendar.DAY_OF_WEEK) - 1;
		if (dayOfWeek != 1) {
			mondayPlus = 1 - dayOfWeek;
		}

		GregorianCalendar currentDate = new GregorianCalendar();
		currentDate.add(GregorianCalendar.DATE, mondayPlus + 6 + weekIndex);
		return currentDate.getTime();
	}

	/**
	 * <一句话功能简述> <功能详细描述>
	 *
	 * @param weekIndex
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public static String getNextWeekDayString(int weekIndex, String format) {
		Date monday = getNextWeekDay(weekIndex);
		DateFormat df = new SimpleDateFormat(format);
		String preMonday = df.format(monday);

		return preMonday;
	}

	private static boolean validDateFormat(String date) {
		// 如果参数为null、格式化字符串为null,返回null
		if (null == date) {
			return false;
		}
		// 如果时间参数不是有效的格式，返回null
		if (!Pattern.matches("\\d{4}-\\d{1,2}-\\d{1,2}\\s\\d{2}:\\d{2}:\\d{2}",
				date)) {
			return false;
		}
		return Boolean.TRUE;
	}
	/**
	 *            日期字符串 例如：0000-00-00 00:00:00
	 *            格式化字符串，例如：yyyy-MM-dd HH:mm:ss
	 * @return Date 日期类型
	 */
	public static Date string2DateByYMDHMS(String date) {
		// 格式校验
		if (!validDateFormat(date)) {
			return null;
		}
		// 上一步的正则表达式的判断，时间格式可能是 yyyy-M-d HH:mm:ss 的情况，因此，给这种情况的月、日 加上0
		// 更新后为 yyyy-0M-0d HH:mm:ss
		String tmpDate = date;
		if (DateUtil.YYYYMMDDHHMMSS.length() > date.length()) {
			String yearTail = date.substring(0, 5);
			String timeTail = date.substring(date.length() - 9);
			String middDate = date.substring(5, date.length() - 9);
			String[] md = middDate.split("-");
			StringBuilder sb = new StringBuilder();
			sb.append(yearTail);
			if (md[0].length() == 1) {
				sb.append("0");
			}
			sb.append(md[0]);
			sb.append("-");
			if (md[1].length() == 1) {
				sb.append("0");
			}
			sb.append(md[1]);
			sb.append(timeTail);
			tmpDate = sb.toString();
		}
		Date returnDate = null;
		SimpleDateFormat sf = null;

		try {
			sf = new SimpleDateFormat(DateUtil.YYYYMMDDHHMMSS);
			returnDate = sf.parse(tmpDate);
			// 将字符串格式化后的时间，再转成字符串
			String returnDate2Str = DateFormatUtils.format(returnDate,
					DateUtil.YYYYMMDDHHMMSS);
			// 比较2者是否相同，相同，说明日期格式正确，不相同，日期格式不正确
			if (!tmpDate.equals(returnDate2Str)) {
				returnDate = null;
			}
		} catch (ParseException e) {
			returnDate = null;
		}
		return returnDate;
	}

	/**
	 * 将时间字符串转换成Date类型时间
	 *
	 * @param date
	 *            yyyy-MM-dd HH:mm:ss 类型的时间字符串
	 * @return 字符串对应的Date类型时间
	 */
	public static Date string2Date(String date) {
		return string2Date(date, YYYYMMDDHHMMSS);
	}

	/**
	 * 将时间字符串转换成时间对应的毫秒值
	 *
	 * @param date
	 *            时间字符串
	 * @param format
	 *            字符串格式
	 * @return 字符串对应的时间毫秒值
	 */
	public static long string2TimeMillis(String date, String format) {
		long d = 0;
		Date dt = string2Date(date, format);
		if (dt != null) {
			d = dt.getTime();
		}
		return d;
	}

	/**
	 * 获取两个时间差
	 *
	 * @param end   结束时间
	 * @param start 开始时间
	 * @return 时间差  xx 天xx 小时 xx 分钟 xx 秒
	 */
	public static String getTime(Date end, Date start) {
		long dif = end.getTime() - start.getTime();
		return tranceTime(dif);
	}

	private static String tranceTime(long dif) {
		String result = "";
		if (dif / DAY != 0) {
			result = dif / DAY + "天";
		}
		if (dif % DAY / HOUR != 0) {
			result = result + dif % DAY / HOUR + "小时";
		}
		if ((dif % DAY % HOUR / MINUTE) != 0) {
			result = result + (dif % DAY % HOUR / MINUTE) + "分钟";

		}
		if ((dif % DAY % HOUR % MINUTE / SECOND) != 0) {
			result = result + (dif % DAY % HOUR % MINUTE / SECOND) + "秒";
		}
		return result;
	}

	/**
	 * 获取两个时间差
	 *
	 * @param dif   结束时间
	 * @return 时间差  xx 天xx 小时 xx 分钟 xx 秒
	 */
	public static String getTime(long dif) {
		return tranceTime(dif);
	}

	/**
	 * 将时间字符串转换成时间对应的毫秒值
	 *
	 * @param date
	 *            yyyy-MM-dd HH:mm:ss 类型的时间字符串
	 * @return 字符串对应的时间毫秒值
	 */
	public static long string2TimeMillis(String date) {
		return string2TimeMillis(date, YYYYMMDDHHMMSS);
	}

	/**
	 * 获取当前时间
	 *
	 * @param format
	 *            yyyy-MM-dd 转换格式
	 * @return 字符串对应的日期
	 */
	public static String getCurrentDate(String format) {

		SimpleDateFormat formatter = new SimpleDateFormat(format);
		// 获取当前时间
		Date curDate = new Date(System.currentTimeMillis());
		String str = formatter.format(curDate);
		return str;
	}

	public static String getNowTime() {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(date);
	}

	public static String getNowHour() {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH");
		return sdf.format(date);
	}

	/**
	 *
	 * 方法getCurrentWekkFirstDay的作用:获取当前时间所在周的指定第一天 修改记录:
	 *
	 * @param startWeekDay
	 * @return
	 * @Exception 异常对象
	 * @version 1.0[修改时小数点后+1]
	 */
	public static Date getCurrentWeekFirstDay(int startWeekDay) {

		// 获取当前时间
		Calendar curremtCalendar = Calendar.getInstance(Locale.getDefault());
		curremtCalendar.setFirstDayOfWeek(startWeekDay);
		curremtCalendar.setTimeInMillis(System.currentTimeMillis());
		curremtCalendar.set(curremtCalendar.get(Calendar.YEAR), curremtCalendar
						.get(Calendar.MONTH), curremtCalendar.get(Calendar.DATE), 0, 0,
				0);

		// 获取本周一时间作为开始时间
		curremtCalendar.set(Calendar.DAY_OF_WEEK, startWeekDay);

		return DateUtil.format(DateUtil.date2string(curremtCalendar.getTime(),
				DateUtil.YYYYMMDD));
	}

	/**
	 *
	 * 方法getDayOfWeek的作用:获取日期所在的周位置 修改记录: [时间_修改人_修改描述]
	 *
	 * @param day
	 * @return
	 * @Exception 异常对象
	 * @version 1.0[修改时小数点后+1]
	 */
	public static int getDayOfWeek(Date day) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(day);
		return cal.get(Calendar.DAY_OF_WEEK);
	}

	public static Date getDateTime(Date date, boolean isBegin) {
		Calendar startCalendar = Calendar.getInstance();
		startCalendar.setTime(date);
		startCalendar.set(Calendar.HOUR, isBegin ? 0 : 23);
		startCalendar.set(Calendar.MINUTE, isBegin ? 0 : 59);
		startCalendar.set(Calendar.SECOND, isBegin ? 0 : 59);
		startCalendar.set(Calendar.MILLISECOND, isBegin ? 0 : 999);
		return startCalendar.getTime();
	}

	/**
	 * 获取日期部分数据
	 *
	 * @param date
	 * @return
	 */
	public static Date getTruncDate(Date date) {
		try {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			String dateStr=format.format(date);
			return format.parse(dateStr);
		} catch (Exception ex) {
		}
		return null;
	}

	public static Date getWeekFirstDayByDate(Date date) {
		Date targetDate = null;

		Calendar cal = Calendar.getInstance(Locale.getDefault());
		cal.setTime(date);

		cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal
				.get(Calendar.DATE), 0, 0, 0);
		int weekInt = cal.get(Calendar.DAY_OF_WEEK);

		if (weekInt == 1) {
			targetDate = DateUtil.raiseDay(date, -6);
		} else if (weekInt == 2) {
			targetDate = DateUtil.raiseDay(date, 0);
		} else if (weekInt == 3) {
			targetDate = DateUtil.raiseDay(date, -1);
		} else if (weekInt == 4) {
			targetDate = DateUtil.raiseDay(date, -2);
		} else if (weekInt == 5) {
			targetDate = DateUtil.raiseDay(date, -3);
		} else if (weekInt == 6) {
			targetDate = DateUtil.raiseDay(date, -4);
		} else if (weekInt == 7) {
			targetDate = DateUtil.raiseDay(date, -5);
		}

		return DateUtil.format(DateUtil.date2string(targetDate.getTime(),
				DateUtil.YYYYMMDD));
	}

	/**
	 * 日期比较
	 *
	 * @param beginTime
	 *            开始时间
	 * @param endTime
	 *            结束时间
	 * @return true：开始时间大于结束时间返回true, false：返回false
	 */
	public static boolean dateComparison(Date beginTime, Date endTime) {

		if (beginTime == null || endTime == null) {
			return false;
		}
		try {
			if (beginTime.getTime() >= endTime.getTime()) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * 日期比较
	 *
	 * @param beginTime
	 *            开始时间
	 * @param endTime
	 *            结束时间
	 * @return true：开始时间小于等于结束时间返回true, 大于：返回false
	 */
	public static boolean dateComparison(String beginTime, String endTime) {

		if (beginTime == null || endTime == null) {
			return false;
		}

		Date date1 = string2Date(beginTime);
		Date date2 = string2Date(endTime);
		try {
			if (date1.getTime() <= date2.getTime()) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * 日期比较，可指定日期格式；
	 *
	 * @param beginTime
	 *            开始时间
	 * @param endTime
	 *            结束时间
	 * @param format 时间格式
	 * @return true：开始时间小于等于结束时间返回true, 大于：返回false
	 */
	public static boolean dateComparisonWithFormat(String beginTime, String endTime, String format) {

		if (beginTime == null || endTime == null) {
			return false;
		}

		Date date1 = string2Date(beginTime, format);
		Date date2 = string2Date(endTime, format);
		try {
			if (date1.getTime() <= date2.getTime()) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * 日期字符串 0000-01-01 00:00:00 或 0000-01-01 或 00-1-1 0:0:0 或 00-1-1 兼容 00-1-32
	 * [Java时间兼容]
	 *
	 * @param dateStr
	 *            日期字符串 0000-00-00 00:00:00
	 * @return Date 日期类型
	 */
	public static Date format(String dateStr) {
		if (dateStr == null) {
			return null;
		}
		dateStr = dateStr.trim();
		String longFormat = "\\d{4}-\\d{1,2}-\\d{1,2}\\s\\d{1,2}:\\d{1,2}:\\d{1,2}";
		String shortFormat = "\\d{4}-\\d{1,2}-\\d{1,2}";
		String longFormatWithShortYear = "\\d{2}-\\d{1,2}-\\d{1,2}\\s\\d{1,2}:\\d{1,2}:\\d{1,2}";
		String shortFormatWithShortYear = "\\d{2}-\\d{1,2}-\\d{1,2}";

		SimpleDateFormat sf = null;
		if (Pattern.matches(longFormat, dateStr)) {
			sf = new SimpleDateFormat(YYYYMMDDHHMMSS);
		} else if (Pattern.matches(shortFormat, dateStr)) {
			sf = new SimpleDateFormat(YYYYMMDD);
		} else if (Pattern.matches(longFormatWithShortYear, dateStr)) {
			dateStr = "20" + dateStr;
			sf = new SimpleDateFormat(YYYYMMDDHHMMSS);
		} else if (Pattern.matches(shortFormatWithShortYear, dateStr)) {
			dateStr = "20" + dateStr;
			sf = new SimpleDateFormat(YYYYMMDD);
		} else {
			return null;
		}
		Date dd = null;
		try {
			// 精准校验 2012.04.01 luliangsong
			sf.setLenient(false);
			dd = sf.parse(dateStr);
		} catch (Exception e) {
			dd = null;
		}
		return dd;
	}

	/**
	 * @param dateStr
	 *            日期字符串 0000-00-00
	 * @return Date 日期类型
	 */
	public static Date formatDay(String dateStr) {
		if (dateStr == null || dateStr.trim().length() < 1) {
			return null;
		}
		SimpleDateFormat sf = new SimpleDateFormat(YYYYMMDD);
		Date dd = null;
		try {
			String compare = "yy-MM-dd";
			if (null != dateStr && dateStr.length() == compare.length()) {
				dateStr = "20" + dateStr;
			}
			dd = sf.parse(dateStr);
		} catch (Exception e) {
			e.printStackTrace();
			dd = null;
		}
		return dd;
	}

	/**
	 * 把字符型日期转换成Timestamp型日期
	 *            字符型日期，格式：2005-01-05 hh:mm:ss
	 * @return Timestamp
	 */
	public static Timestamp stringToTimestamp(String inTime) {

		Timestamp retValue = null;
		SimpleDateFormat sdf = new SimpleDateFormat(YYYYMMDDHHMMSS);
		try {
			Date tempDate = sdf.parse(inTime);
			retValue = new Timestamp(tempDate.getTime());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return retValue;
	}

	/**
	 * 将日期转换成字符串
	 *
	 * @param date
	 * @param format
	 * @return
	 */
	public static String date2String(Date date, String format) {
		if (date == null){
			return "";
		}
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		String result = null;
		sdf.setLenient(false);
		try {
			result = sdf.format(date);
		} catch (Exception e) {

		}
		return result;
	}

	/**
	 * 将日期转换成字符串,默认格式：yyyy-MM-dd HH:mm:ss
	 *
	 * @param date
	 * @return
	 */
	public static String date2Str(Date date) {
		return date2String(date, DateUtil.YYYYMMDDHHMMSS);
	}

	/**
	 * 将当前时区时间转换成UTC"yyyy-MM-ddTHH:mm:ss.fffZ"格式的字符串
	 *
	 * @param date
	 * @return String [返回类型说明]
	 * @exception throws [违例类型] [违例说明]
	 * @see [类、类#方法、类#成员]
	 */
	public static String datetoUtcString(Date date) {
		String dateStr = DateFormatUtils.formatUTC(date,
				"yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
		return dateStr;
	}

	/**
	 * 计算当前日期 间隔num天(分、时、天、月、年)的日期
	 *
	 * @param date
	 * @param num
	 * @param type 时间类型：分(1)、时(2)、天(3)、月(4)、年(5)
	 * @return
	 */
	public static Date raiseDate(Date date, int num, String type) {
		Date newDate = null;
		if (date == null) {
			return newDate;
		}

		switch (type) {
			case "1":
				newDate = raiseMinutes(date, num);
				break;
			case "2":
				newDate = raiseHour(date, num);
				break;
			case "3":
				newDate = raiseDay(date, num);
				break;
			case "4":
				newDate = raiseMonth(date, num);
				break;
			case "5":
				newDate = raiseYear(date, num);
				break;
			default:
				newDate = raiseDay(date, num);
		}

		return newDate;
	}

	/**
	 * 计算当前日期 间隔num天的日期
	 *
	 * @param date
	 * @param num
	 * @return
	 */
	public static Date raiseDay(Date date, int num) {
		if (date == null) {
			return null;
		}

		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DAY_OF_MONTH, num);

		return cal.getTime();
	}
	/**
	 * 计算当前日期 间隔num月的日期
	 *
	 * @param date
	 * @param num
	 * @return
	 */
	public static Date raiseMonth(Date date, int num) {
		if (date == null) {
			return null;
		}

		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MONTH, num);

		return cal.getTime();
	}

	/**
	 * 计算当前日期 间隔num年的年份
	 *
	 * @param date
	 * @param num
	 * @return
	 */
	public static Date raiseYear(Date date, int num) {
		if (date == null) {
			return null;
		}

		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.YEAR, num);

		return cal.getTime();
	}

	/**
	 * 计算当前日期 间隔num天的日期
	 *
	 * @param date
	 * @param num
	 * @return
	 */
	public static String raiseDay(Date date, int num, String format) {
		if (date == null) {
			return null;
		}

		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DAY_OF_MONTH, num);
		Date newDate = cal.getTime();
		String dateStr = DateUtil.date2string(newDate, format);
		return dateStr;
	}
	/**
	 * 计算当前时间间隔num小时的时间
	 * @param date
	 * @param num
	 * @return
	 */
	public static Date raiseHour(Date date, int num) {
		if (date == null) {
			return null;
		}
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.HOUR_OF_DAY, num);

		return cal.getTime();
	}

	/**
	 * 计算当前日期 间隔num天的日期
	 *
	 * @param date
	 * @param num 分钟
	 * @return
	 */
	public static String raiseDayString(Date date, int num) {
		if (date == null) {
			date = new Date();
		}

		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DAY_OF_MONTH, num);

		Date newDate = cal.getTime();
		String dateStr = DateUtil.date2string(newDate, DateUtil.YYYYMMDDHHMMSS);
		return dateStr;
	}

	/**
	 * 给指定时间增加固定分钟数
	 *
	 * @param date
	 * @param min
	 * @return
	 */
	public static Date raiseMinutes(Date date, Integer min) {
		if (date == null) {
			return null;
		}
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(date.getTime() + (long) min * 60000);
		return cal.getTime();
	}

	/**
	 * 给指定时间增加固定分钟数
	 * @param date
	 * @param num
	 * @return
	 */
	public static String raiseMinutesString(Date date, int num) {
		if (date == null) {
			date = new Date();
		}

		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MINUTE, num);

		Date newDate = cal.getTime();
		String dateStr = DateUtil.date2string(newDate, DateUtil.YYYYMMDDHHMMSS);
		return dateStr;
	}

	/**
	 * 给指定时间增加固定秒数
	 *
	 * @param date
	 * @param second
	 * @return
	 */
	public static Date raiseSeconds(Date date, Integer second) {
		if (date == null) {
			return null;
		}
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(date.getTime() + (long) (second * 1000));
		return cal.getTime();
	}

	/**
	 * 添加分钟
	 * @param date 时间
	 * @param minute 分钟数
	 * @return
	 */
	public static Date addMinute(Date date, int minute) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.MINUTE, minute);
		c.set(Calendar.MILLISECOND, 0);
		return c.getTime();
	}

	/**
	 * 添加小时
	 * @param date 时间
	 * @param hour 小时数
	 * @return
	 */
	public static Date addHour(Date date, int hour) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.HOUR_OF_DAY, hour);
		return c.getTime();
	}

	/*public static void main(String[] args) {
		System.out.println(addHour(new Date(), 8));
	}*/

	/**
	 * 将 "yyyy-MM-dd HH:mm:ss"格式装换成"yyyyMMddhhmmss"格式
	 */
	public static String convertTimeToString(String programTime) {

		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = null;
		String formatdate=null;
		try {
			date = sdf1.parse(programTime);
			SimpleDateFormat sdf2 = new SimpleDateFormat("yyyyMMddhhmmss");
			formatdate = sdf2.format(date);
		} catch (Exception e) {
		}
		return formatdate;

	}


	/**
	 * 将某个时间字符串转换为指定格式的时间字符串
	 * @param sourceTime 待转换时间
	 * @param sourceTimeFormat 待转换时间的时间格式
	 * @param format 需要返回的时间格式
	 * @return resultTime 最终返回的时间
	 */
	public static String convertTimeFormatToString(String sourceTime,String sourceTimeFormat,String format) {

		Date date = null;
		String resultTime=null;
		SimpleDateFormat sourceSimpleDateFormat = new SimpleDateFormat(sourceTimeFormat);
		try {
			date = sourceSimpleDateFormat.parse(sourceTime);
			SimpleDateFormat resultSimpleDateFormat = new SimpleDateFormat(format);
			resultTime = resultSimpleDateFormat.format(date);
		} catch (Exception e) {
		}
		return resultTime;

	}

	/**
	 * 获取某月份第一天的日期
	 * @param year
	 * @param month
	 * @return
	 */
	public static String getFirstDayOfMonth(int year, int month) {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.MONTH, month-1);
		cal.set(Calendar.DAY_OF_MONTH,cal.getMinimum(Calendar.DATE));
		return new SimpleDateFormat( "yyyy-MM-dd ").format(cal.getTime());
	}
	/**
	 * 获取某月份最后一天的日期
	 * @param year
	 * @param month
	 * @return
	 */
	public static String getLastDayOfMonth(int year, int month) {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.MONTH, month-1);
		cal.set(Calendar.DAY_OF_MONTH,cal.getActualMaximum(Calendar.DATE));
		return new SimpleDateFormat( "yyyy-MM-dd ").format(cal.getTime());
	}

	/**
	 * 获取指定时间上几个月的最后一天的结束时间，比如传入date = 2020-03-18 15:26:22，dateNum = -2，返回2020-01-31 23:59:59
	 *
	 * @param date    时间
	 * @param dateNum 时间单位数值:间隔几个月
	 * @return java.util.Date
	 */
	public static Date getLastDayAndTimeOfMonthByDateNum(Date date, int dateNum) {
		Calendar instance = Calendar.getInstance();
		if (date != null) {
			instance.setTime(date);
		}
		instance.add(Calendar.MONTH, dateNum);
		instance.set(Calendar.DAY_OF_MONTH, instance.getActualMaximum(Calendar.DAY_OF_MONTH));
		instance.set(Calendar.HOUR_OF_DAY, instance.getActualMaximum(Calendar.HOUR_OF_DAY));
		instance.set(Calendar.MINUTE, instance.getActualMaximum(Calendar.MINUTE));
		instance.set(Calendar.SECOND, instance.getActualMaximum(Calendar.SECOND));
		instance.set(Calendar.MILLISECOND, instance.getActualMaximum(Calendar.MILLISECOND));
		return instance.getTime();
	}

	/**
	 * 获取指定时间上几个月的最后一天结束时间，比如传入date = 2020-03-18 15:26:22，dateNum = -2，返回2020-01-31 23:59:59
	 *
	 * @param dateTime    时间
	 * @param dateNum 时间单位数值：间隔几个月
	 * @return java.util.Date
	 */
	public static String getLastDayAndTimeOfMonthByDateNum(String dateTime, int dateNum) {
		Calendar instance = Calendar.getInstance();
		Date date = string2Date(dateTime);
		if (date != null) {
			instance.setTime(date);
		}
		instance.add(Calendar.MONTH, dateNum);
		instance.set(Calendar.DAY_OF_MONTH, instance.getActualMaximum(Calendar.DAY_OF_MONTH));
		instance.set(Calendar.HOUR_OF_DAY, instance.getActualMaximum(Calendar.HOUR_OF_DAY));
		instance.set(Calendar.MINUTE, instance.getActualMaximum(Calendar.MINUTE));
		instance.set(Calendar.SECOND, instance.getActualMaximum(Calendar.SECOND));
		instance.set(Calendar.MILLISECOND, instance.getActualMaximum(Calendar.MILLISECOND));
		return date2string(instance.getTime());
	}

	/**
	 * 获取指定日期上几个月日期的第一天，比如传入date = 2020-03-18 15:26:22，dateNum = -2，返回2020-01-01 00:00:00
	 * @param date
	 * @param dateNum
	 * @return
	 */
	public static Date getFirstDayAndTimeOfMonthDayNum(Date date,int dateNum){
		Calendar calendar = Calendar.getInstance();
		if (date != null) {
			calendar.setTime(date);
		}
		calendar.add(Calendar.MONTH,dateNum);
		calendar.set(Calendar.DAY_OF_MONTH,calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
		calendar.set(Calendar.HOUR_OF_DAY,calendar.getActualMinimum(Calendar.HOUR_OF_DAY));
		calendar.set(Calendar.MINUTE,calendar.getActualMinimum(Calendar.MINUTE));
		calendar.set(Calendar.SECOND,calendar.getActualMinimum(Calendar.SECOND));
		calendar.set(Calendar.MILLISECOND,calendar.getActualMinimum(Calendar.MILLISECOND));
		return calendar.getTime();
	}

	/**
	 * 获取指定日期上几个月日期的第一天，比如传入date = 2020-03-18 15:26:22，dateNum = -2，返回2020-01-01 00:00:00
	 * @param dateTime
	 * @param dateNum
	 * @return
	 */
	public static String getFirstDayAndTimeOfMonthDayNum(String dateTime,int dateNum){
		Calendar calendar = Calendar.getInstance();
		Date date = string2Date(dateTime);
		if (date != null) {
			calendar.setTime(date);
		}
		calendar.add(Calendar.MONTH,dateNum);
		calendar.set(Calendar.DAY_OF_MONTH,calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
		calendar.set(Calendar.HOUR_OF_DAY,calendar.getActualMinimum(Calendar.HOUR_OF_DAY));
		calendar.set(Calendar.MINUTE,calendar.getActualMinimum(Calendar.MINUTE));
		calendar.set(Calendar.SECOND,calendar.getActualMinimum(Calendar.SECOND));
		calendar.set(Calendar.MILLISECOND,calendar.getActualMinimum(Calendar.MILLISECOND));
		return date2string(calendar.getTime());
	}

	/**
	 * 获取前几天的日期
	 * @param index
	 * @return
	 */
	public static String getLastDay(int index){
		Date date=new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DATE, index);
		date = calendar.getTime();
		SimpleDateFormat format= new SimpleDateFormat("yyyy-MM-dd");
		return format.format(date);
	}

	public static String getFirstDayOfMonth(){
		Calendar cale = Calendar.getInstance();
		SimpleDateFormat format= new SimpleDateFormat("yyyy-MM-dd");
		cale.add(Calendar.MONTH, 0);
		cale.set(Calendar.DAY_OF_MONTH, 1);
		return format.format(cale.getTime());
	}

	public static String getLastDayOfMonth(){
		Calendar cale = Calendar.getInstance();
		SimpleDateFormat format= new SimpleDateFormat("yyyy-MM-dd");
		cale.add(Calendar.MONTH, 1);
		cale.set(Calendar.DAY_OF_MONTH, 0);
		return format.format(cale.getTime());
	}

	public static String getCurrentFirstTime(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY,0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		date = calendar.getTime();
		return sdf.format(date);
	}
	/**
	 * 获取前几天0点时间
	 * @param date 时间
	 * @return
	 */
	public static String getCurrentBeforeTime(Date date,int i) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DATE,-i);
		calendar.set(Calendar.HOUR_OF_DAY,0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		date = calendar.getTime();
		return sdf.format(date);
	}

	/**
	 * 获取前几小时0点时间
	 * @param date 时间
	 * @return
	 */
	public static String getCurrentBeforeHourTime(Date date,int i) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.HOUR_OF_DAY,i);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		date = calendar.getTime();
		return sdf.format(date);
	}

	public static String getCurrentLastTime(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DATE, +1);
		calendar.set(Calendar.HOUR_OF_DAY,0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		date = calendar.getTime();
		return sdf.format(date);
	}

	public static String getAppointTime(Date date,int hour) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY,hour);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		date = calendar.getTime();
		return sdf.format(date);
	}
	/**
	 * 获取前几个月0点时间
	 * @param date 时间
	 * @return
	 */
	public static String getCurrentBeforeMonth(Date date,int i) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH,-i);
		calendar.set(Calendar.HOUR_OF_DAY,0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		date = calendar.getTime();
		return sdf.format(date);
	}
	/**
	 * 获取前几个月0点时间
	 * @param date 时间
	 * @return
	 */
	public static String getCurrentBeforeMonth(Date date,int i,int initDay,int initHour,int initMinute,int initSecond) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH,-i);
		calendar.add(Calendar.DAY_OF_MONTH,initDay);
		calendar.set(Calendar.HOUR_OF_DAY,initHour);
		calendar.set(Calendar.SECOND, initSecond);
		calendar.set(Calendar.MINUTE, initMinute);
		calendar.set(Calendar.MILLISECOND, 0);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		date = calendar.getTime();
		return sdf.format(date);
	}
	/**
	 * 返回两个时间段的时间差（毫秒）
	 */
	public static Long getTimeLag(String beginTime,String endTime){
		Date begin = string2Date(beginTime,"yyyy-MM-dd HH:mm:ss");
		Date end = string2Date(endTime,"yyyy-MM-dd HH:mm:ss");
		// 获得两个时间的毫秒时间差异
		long timeLog = end.getTime() - begin.getTime();
		return timeLog;
	}
	/**
	 * 获取当前时间在当天的分钟数
	 */
	public static int getCurrentTimeToMin(String time){
		Date date = new Date();
		if(!time.isEmpty()){
			date = string2Date(time,"yyyy-MM-dd HH:mm:ss");
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int hour = calendar.get(Calendar.HOUR_OF_DAY);
		int min = calendar.get(Calendar.MINUTE);
		return hour*60+min;
	}

	/**
	 * 判断两个字符串时间是否在同一分钟 	 *
	 */
	public static boolean isSameMin(String time1,String time2){
		Date d1 = string2Date(time1,"yyyy-MM-dd HH:mm:ss");
		Date d2 = string2Date(time2,"yyyy-MM-dd HH:mm:ss");
		long lag = d2.getTime() - d1.getTime();
		return lag / 1000 / 60 < 1;
	}

	/**
	 * 获取指定月份的天数,当为本月时,只计算到当天
	 */
	public static int getDaysOfMonth(String day) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
		String thisDate=df.format(new Date());//获取当前日期的年份和月份
		String aa = thisDate.substring(0, 7);
		String bb = day.substring(0, 7);
		if(thisDate.substring(0, 7).equals(day.substring(0, 7))){
			return Calendar.getInstance().get(Calendar.DATE) - 1;
		}else{
			Date date = string2Date(day,"yyyy-MM-dd");
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		}
	}

	/**
	 * 获取指定月份的天数
	 */
	public static int getDaysOfMonth(int year,int month) {
		Calendar calendar = Calendar.getInstance();
		calendar.clear();
		calendar.set(Calendar.YEAR,year);
		//year年
		calendar.set(Calendar.MONTH,month-1);
		//Calendar对象默认一月为0,month月
		return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);//本月份的天数
	}

	/**
	 * 获取两个日期之间的天数
	 * @param d1
	 * @param d2
	 * @return
	 */
	public static int getDayBetweenTwoDays(String d1,String d2){
		if (StringUtil.isNullOrEmpty(d1) || StringUtil.isNullOrEmpty(d2))
			return 0;
		//若结束日期大于
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date endDate = null;
		Date thisDate = null;
		try {
			endDate = df.parse(d2);
			thisDate = df.parse(df.format(new Date()));
			if(thisDate.before(endDate) || endDate.getTime() == thisDate.getTime()){
				d2 = df.format(df.parse(getLastDay(-1)));
			}
		} catch (ParseException e) {
		}

		Date smdate = string2Date(d1,"yyyy-MM-dd");
		Date bdate = string2Date(d2,"yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		cal.setTime(smdate);
		long time1 = cal.getTimeInMillis();
		cal.setTime(bdate);
		long time2 = cal.getTimeInMillis();
		long between_days=(time2-time1)/(1000*3600*24);
		return Integer.parseInt(String.valueOf(between_days));
	}

	/**
	 * 获取后一天的日期
	 */
	public static String getAfterDate(String d,int index){
		Date date = string2Date(d,"yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DATE, index);
		date = calendar.getTime();
		SimpleDateFormat format= new SimpleDateFormat("yyyy-MM-dd");
		return format.format(date);
	}

	/**
	 * 获取当前时间的小时数
	 * @return
	 */
	public static int getCurrentHourOfDay() {
		Date date = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.HOUR_OF_DAY);
	}

	/**
	 * 比较cTime是否在dStart和dEnd之间
	 * @param cTime
	 * @param dStart
	 * @param dEnd
	 * @return
	 */
	public static boolean compareTime(Date cTime, Date dStart, Date dEnd) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(cTime);
		long cTimeLong = calendar.getTimeInMillis();
		calendar.setTime(dStart);
		long dStartLong = calendar.getTimeInMillis();
		calendar.setTime(dEnd);
		long dEndLong = calendar.getTimeInMillis();
		return cTimeLong>=dStartLong && cTimeLong<dEndLong;
	}

	public static String getLastMonthYM() {
		Calendar calendar = Calendar.getInstance();
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH) + 1;
		if(month==1){
			year--;
			month = 12;
		}
		else{
			month--;
		}
		if(month<10){
			return year + "-0" + month;
		}
		else{
			return year + "-" + month;
		}
	}

	/**
	 * 获取当前时间上个月或当前月的第一天和最后一天日期
	 * @param i 0:表示当前月，-1上个月
	 * @return
	 */
	public static String[] getLastMonthSE(int i) {
		String [] time = new String [2];
		Calendar calendar = Calendar.getInstance();
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH) + 1;
		if(i!=0){
			if(month==1){
				year--;
				month = 12;
			}
			else{
				month--;
			}
		}
		int daySize = getDaysOfMonth(year,month);
		if(month<10){
			time[0] = year + "-0" + month + "-01";
			time[1] = year + "-0" + month + "-" + daySize;
		}
		else{
			time[0] = year + "-" + month + "-01";
			time[1] = year + "-" + month + "-" + daySize;
		}
		return time;
	}

	/**
	 * 返回相对现在间隔n小时的开始时间和结束时间
	 * @return
	 */
	public static Map<String, String> getLastHoursTime(int start, int end){
		Map<String, String> result = new HashMap<String, String>();
		String startTime = date2string(raiseHour(new Date(), start).getTime(), DateUtil.YYYYMMDD_HH_00_00);
		String endTime = date2string(raiseHour(new Date(), end).getTime(), DateUtil.YYYYMMDD_HH_59_59);
		result.put("startTime", startTime);
		result.put("endTime", endTime);
		return result;
	}

	/**
	 * 返回相对现在间隔n天的开始时间和结束时间
	 * @return
	 */
	public static Map<String, String> getLastDaysTime(int start, int end){
		Map<String, String> result = new HashMap<String, String>();
		String startTime = date2string(raiseDay(new Date(), start).getTime(), DateUtil.YYYYMMDD_00_00_00);
		String endTime = date2string(raiseDay(new Date(), end).getTime(), DateUtil.YYYYMMDD_23_59_59);
		result.put("startTime", startTime);
		result.put("endTime", endTime);
		return result;
	}

	/**
	 * 返回相对某个时间字符串间隔多少小时的时间字符串，只是小时变化
	 * @param dateTime 变化前的时间
	 * @param num 间隔小时数
	 * @return
	 */
	public static String getIntervalHourTime(String dateTime, int num){
		if (dateTime == null || "".equals(dateTime.trim()) || "null".equalsIgnoreCase(dateTime)) {
			return null;
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(string2Date(dateTime));
		calendar.add(Calendar.HOUR_OF_DAY,num);
		return date2string(calendar.getTime(),YYYYMMDDHHMMSS);
	}


	/**
	 * 返回相对某个时间字符串间隔多少小时的开始时间或结束时间字符串
	 * @param dateTime 变化前的时间
	 * @param num 间隔小时数
	 * @return
	 */
	public static String getIntervalHourTime(String dateTime, int num,Boolean isBegin){
		if (dateTime == null || "".equals(dateTime.trim()) || "null".equalsIgnoreCase(dateTime)) {
			return null;
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(string2Date(dateTime));
		calendar.add(Calendar.HOUR_OF_DAY,num);
		calendar.set(Calendar.MINUTE, isBegin ? calendar.getActualMinimum(Calendar.MINUTE) : calendar.getActualMaximum(Calendar.MINUTE));
		calendar.set(Calendar.SECOND, isBegin ? calendar.getActualMinimum(Calendar.SECOND) : calendar.getActualMaximum(Calendar.SECOND));
		calendar.set(Calendar.MILLISECOND, isBegin ? calendar.getActualMinimum(Calendar.MILLISECOND) : calendar.getActualMaximum(Calendar.MILLISECOND));
		return date2string(calendar.getTime(),YYYYMMDDHHMMSS);
	}

	/**
	 * 功能描述: 返回相对某个时间间隔n天的开始时间和结束时间
	 * @author wanzhu
	 * @param dateTime 变化前的时间
	 * @param num 间隔天数
	 * @param isBegin 是否是返回开始时间：true 返回开始时间，false 返回结束时间
	 * @return
	 */
	public static String getIntervalDayTime(Date dateTime, int num, Boolean isBegin){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(dateTime);
		calendar.add(Calendar.DAY_OF_MONTH,num);
		calendar.set(Calendar.HOUR_OF_DAY, isBegin ? calendar.getActualMinimum(Calendar.HOUR_OF_DAY) : calendar.getActualMaximum(Calendar.HOUR_OF_DAY));
		calendar.set(Calendar.MINUTE, isBegin ? calendar.getActualMinimum(Calendar.MINUTE) : calendar.getActualMaximum(Calendar.MINUTE));
		calendar.set(Calendar.SECOND, isBegin ? calendar.getActualMinimum(Calendar.SECOND) : calendar.getActualMaximum(Calendar.SECOND));
		calendar.set(Calendar.MILLISECOND, isBegin ? calendar.getActualMinimum(Calendar.MILLISECOND) : calendar.getActualMaximum(Calendar.MILLISECOND));
		return date2string(calendar.getTime(),YYYYMMDDHHMMSS);
	}

	/**
	 * 功能描述: 返回相对某个时间间隔n天的开始时间和结束时间
	 * @author wanzhu
	 * @param dateTime 变化前的时间
	 * @param num 间隔天数
	 * @param isBegin 是否是返回开始时间：true 返回开始时间，false 返回结束时间
	 * @return
	 */
	public static String getIntervalDayTime(String dateTime, int num, Boolean isBegin){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(string2Date(dateTime));
		calendar.add(Calendar.DAY_OF_MONTH,num);
		calendar.set(Calendar.HOUR_OF_DAY, isBegin ? calendar.getActualMinimum(Calendar.HOUR_OF_DAY) : calendar.getActualMaximum(Calendar.HOUR_OF_DAY));
		calendar.set(Calendar.MINUTE, isBegin ? calendar.getActualMinimum(Calendar.MINUTE) : calendar.getActualMaximum(Calendar.MINUTE));
		calendar.set(Calendar.SECOND, isBegin ? calendar.getActualMinimum(Calendar.SECOND) : calendar.getActualMaximum(Calendar.SECOND));
		calendar.set(Calendar.MILLISECOND, isBegin ? calendar.getActualMinimum(Calendar.MILLISECOND) : calendar.getActualMaximum(Calendar.MILLISECOND));
		return date2string(calendar.getTime(),YYYYMMDDHHMMSS);
	}

	/**
	 *  获取当前时间的年月日：yyyy-MM-dd
	 * @return
	 */
	public static String getNowDay(){

		SimpleDateFormat sdf = new SimpleDateFormat(YYYYMMDD);
		String format = sdf.format(new Date());
		return format;
	}

	/**
	 *  获取当前时间的年月：yyyy-MM
	 * @return
	 */
	public static String getNowMonth(){

		SimpleDateFormat sdf = new SimpleDateFormat(YYYYMM_STANDARD);
		String format = sdf.format(new Date());
		return format;
	}
	/**
	 * 获取间隔多少天前的日期
	 * @return
	 */
	public static String getGapDay(String date,Integer gap){

		SimpleDateFormat sdf = new SimpleDateFormat(YYYYMMDD);
		long newTime = DateUtil.string2TimeMillis(date, YYYYMMDD) - 1000L*3600*24*gap;
		String format = sdf.format(new Date(newTime));
		return format;
	}

	public static Object StringToLongDate(String date, String formt) {
		long l = 0;
		if (!StringUtils.isEmpty(date)) {
			l = DateUtil.string2TimeMillis(date, formt);
		}
		return l;
	}

	/**
	 * 获取ISO8601格式时间
	 * @param date
	 * @return
	 */
	public static String getIso8601Time(String date) {
		DateFormat df = new SimpleDateFormat(IOS8601);
		df.setTimeZone(TimeZone.getTimeZone("UTC"));
		return df.format(string2Date(date, YYYYMMDDHHMMSS));
	}

	/**
	 * time + " 00:00:00"
	 *
	 * @param date
	 * @return
	 */
	public static Date getStartTime(Date date) {
		Calendar instance = Calendar.getInstance();
		instance.setTime(date);
		instance.set(Calendar.HOUR_OF_DAY, 0);
		instance.set(Calendar.MINUTE, 0);
		instance.set(Calendar.SECOND, 0);
		instance.set(Calendar.MILLISECOND, 0);
		return instance.getTime();
	}

	/**
	 * time + " 00:00:00"
	 *
	 * @param date
	 * @return
	 */
	public static String getStartTimeStr(Date date) {
		return date2Str(getStartTime(date));
	}

	/**
	 * 把时间的秒数变成0，2020-03-12 14:45:15 -> 2020-03-12 14:45:00
	 *
	 * @param date
	 * @return java.util.Date
	 * @author wanzhu
	 */
	public static Date getStartHour(Date date) {
		Calendar instance = Calendar.getInstance();
		instance.setTime(date);
		instance.set(Calendar.MINUTE, 0);
		instance.set(Calendar.SECOND, 0);
		instance.set(Calendar.MILLISECOND, 0);
		return instance.getTime();
	}

	/**
	 * 把时间的秒数变成0，2020-03-12 14:45:15 -> 2020-03-12 14:45:00
	 *
	 * @param date
	 * @return java.lang.String
	 * @author wanzhu
	 */
	public static String getStartHourStr(Date date) {
		return date2Str(getStartHour(date));
	}

	/**
	 * time + " 23:59:59"
	 *
	 * @param date
	 * @return java.util.Date
	 * @author wanzhu
	 */
	public static Date getEndTime(Date date) {
		Calendar instance = Calendar.getInstance();
		instance.setTime(date);
		instance.set(Calendar.HOUR_OF_DAY, 23);
		instance.set(Calendar.MINUTE, 59);
		instance.set(Calendar.SECOND, 59);
		instance.set(Calendar.MILLISECOND, 999);
		return instance.getTime();
	}

	/**
	 * time + " 23:59:59"
	 *
	 * @param date
	 * @return java.util.Date
	 * @author wanzhu
	 */
	public static String getEndTimeStr(Date date) {
		return date2Str(getEndTime(date));
	}

	/**
	 * 把时间的秒数变成59，2020-03-12 14:45:15 -> 2020-03-12 14:45:59
	 *
	 * @param date
	 * @return java.util.Date
	 * @author wanzhu
	 */
	public static Date getEndHour(Date date) {
		Calendar instance = Calendar.getInstance();
		instance.setTime(date);
		instance.set(Calendar.MINUTE, 59);
		instance.set(Calendar.SECOND, 59);
		instance.set(Calendar.MILLISECOND, 999);
		return instance.getTime();
	}

	/**
	 * 把时间的秒数变成59，2020-03-12 14:45:15 -> 2020-03-12 14:45:59
	 *
	 * @param date
	 * @return java.lang.String
	 * @author wanzhu
	 */
	public static String getEndHourStr(Date date) {
		return date2Str(getStartHour(date));
	}

	/**
	 * 获得连续月份list 如(2015-01,2015-04)返回 2015-01,2015-02,2015-03,2015-04
	 * @param beginDate yyyy-MM
	 * @param endDate yyyy-MM
	 * @return
	 */
	public static List<String> getMonthFormatList(String beginDate, String endDate) {
		List<String> list = new ArrayList<String>();
		Date curDate = DateUtil.string2Date(beginDate, DateUtil.YYYYMM_STANDARD);
		while (curDate.compareTo(DateUtil.string2Date(endDate, DateUtil.YYYYMM_STANDARD))<=0) {
			list.add(DateUtil.date2String(curDate,DateUtil.YYYYMM_STANDARD));
			Calendar instance = Calendar.getInstance();
			instance.setTime(curDate);
			instance.add(Calendar.MONTH, 1);
			curDate = instance.getTime();
		}
		return list;
	}

	/**
	 * 获得连续小时list 返回格式yyyy-MM-dd HH:mm:ss
	 * @param beginDate yyyy-MM-dd HH:mm:ss
	 * @param endDate yyyy-MM-dd HH:mm:ss
	 * @return
	 */

	public static List<String> getHourFormatList(String beginDate, String endDate) {
		List<String> list = new ArrayList<String>();
		Date curDate = DateUtil.string2Date(beginDate);
		while (curDate.compareTo(DateUtil.string2Date(endDate))<=0) {
			list.add(DateUtil.date2String(curDate,DateUtil.YYYYMMDDHHMMSS));
			Calendar instance = Calendar.getInstance();
			instance.setTime(curDate);
			instance.add(Calendar.HOUR, 1);
			curDate = instance.getTime();
		}
		return list;
	}

	/**
	 * 获得连续小时list 返回格式yyyy-MM-dd HH:mm:ss
	 *
	 * @param beginDate yyyy-MM-dd HH:mm:ss
	 * @param endDate   yyyy-MM-dd HH:mm:ss
	 * @param interval 时间间隔
	 * @return
	 */
	public static List<String> getHourFormatList(String beginDate, String endDate, int interval) {
		List<String> list = new ArrayList<String>();
		Date curDate = DateUtil.string2Date(beginDate);
		while (curDate.compareTo(DateUtil.string2Date(endDate)) <= 0) {
			list.add(DateUtil.date2String(curDate, DateUtil.YYYYMMDDHHMMSS));
			Calendar instance = Calendar.getInstance();
			instance.setTime(curDate);
			instance.add(Calendar.HOUR, interval);
			curDate = instance.getTime();
		}
		return list;
	}

	public static List<String> getHourFormatListExpression(String beginDate, String endDate,String expression) {
		List<String> list = new ArrayList<String>();
		Date curDate = DateUtil.string2Date(beginDate);
		while (curDate.compareTo(DateUtil.string2Date(endDate))<=0) {
			list.add(DateUtil.date2String(curDate,expression));
			Calendar instance = Calendar.getInstance();
			instance.setTime(curDate);
			instance.add(Calendar.HOUR, 1);
			curDate = instance.getTime();
		}
		return list;
	}

	public static List<String> getMinuteFormatList(String beginDate, String endDate, int interval) {
		List<String> list = new ArrayList<String>();
		Date curDate = DateUtil.string2Date(beginDate);
		while (curDate.compareTo(DateUtil.string2Date(endDate))<=0) {
			list.add(DateUtil.date2String(curDate,DateUtil.YYYYMMDDHHMMSS));
			Calendar instance = Calendar.getInstance();
			instance.setTime(curDate);
			instance.add(Calendar.MINUTE, interval);
			curDate = instance.getTime();
		}
		return list;
	}
	public static List<String> getSecondFormatList(String beginDate, String endDate, int interval) {
		List<String> list = new ArrayList<String>();
		Date curDate = DateUtil.string2Date(beginDate);
		while (curDate.compareTo(DateUtil.string2Date(endDate))<=0) {
			list.add(DateUtil.date2String(curDate,DateUtil.YYYYMMDDHHMMSS));
			Calendar instance = Calendar.getInstance();
			instance.setTime(curDate);
			instance.add(Calendar.SECOND, interval);
			curDate = instance.getTime();
		}
		return list;
	}

	/**
	 * 获得连续天list 返回格式yyyy-MM-dd
	 * @param beginDate yyyy-MM-dd
	 * @param endDate yyyy-MM-dd
	 * @return
	 */
	public static List<String> getDayFormatList(String beginDate, String endDate) {
		List<String> list = new ArrayList<String>();
		Date curDate = DateUtil.string2Date(beginDate,DateUtil.YYYYMMDD);
		while (curDate.compareTo(DateUtil.string2Date(endDate,DateUtil.YYYYMMDD))<=0) {
			list.add(DateUtil.date2String(curDate,DateUtil.YYYYMMDD));
			Calendar instance = Calendar.getInstance();
			instance.setTime(curDate);
			instance.add(Calendar.DAY_OF_MONTH, 1);
			curDate = instance.getTime();
		}
		return list;
	}

	/**
	 * 获得连续天list 返回格式yyyy-MM-dd
	 * @param beginDate yyyy-MM-dd
	 * @param endDate yyyy-MM-dd
	 * @return
	 */
	public static List<String> getDayFormatListByExpression(String beginDate, String endDate,String expression) {
		List<String> list = new ArrayList<String>();
		Date curDate = DateUtil.string2Date(beginDate,expression);
		while (curDate.compareTo(DateUtil.string2Date(endDate,expression))<=0) {
			list.add(DateUtil.date2String(curDate,expression));
			Calendar instance = Calendar.getInstance();
			instance.setTime(curDate);
			instance.add(Calendar.DAY_OF_MONTH, 1);
			curDate = instance.getTime();
		}
		return list;
	}

	public static String getWeekNumByNum(int num){
		Map<Integer,String> date = new HashMap<Integer,String>();
		date.put(1,"周日");
		date.put(2,"周一");
		date.put(3,"周二");
		date.put(4,"周三");
		date.put(5,"周四");
		date.put(6,"周五");
		date.put(7,"周六");
		return date.get(num);
	}
	/**
	 * 获取某时间段内的所有日期字符串列表
	 * @param startDateStr 开始时间 格式：yyyy-MM-dd
	 * @param endDateStr 结束时间 格式：yyyy-MM-dd
	 * @return
	 */
	public static List<String> getDateList(String startDateStr, String endDateStr,String format) {
		List<String> dateList = new ArrayList<>();
		Date startDate = DateUtil.string2Date(startDateStr, DateUtil.YYYYMMDD);
		Date endDate = DateUtil.string2Date(endDateStr, DateUtil.YYYYMMDD);
		while(startDate.getTime() <= endDate.getTime()) {
			dateList.add(DateUtil.date2String(startDate, format));
			startDate = DateUtil.raiseDay(startDate, 1);
		}
		return dateList;
	}

	/**
	 * 获取某时间段内的所有日期字符串列表
	 * @param startDateStr 开始时间
	 * @param endDateStr 结束时间
	 * @param format 时间格式
	 * @param num 间隔 1天(分、时等)
	 * @param timeType 时间类型：分(1)、时(2)、天(3)、月(4)、年(5)
	 * @return
	 */
	public static List<String> getDateListWithFormat(String startDateStr, String endDateStr,String format, int num, String timeType) {
		List<String> dateList = new ArrayList<>();
		Date startDate = DateUtil.string2Date(startDateStr, format);
		Date endDate = DateUtil.string2Date(endDateStr, format);
		while(startDate.getTime() <= endDate.getTime()) {
			dateList.add(DateUtil.date2String(startDate, format));
			startDate = DateUtil.raiseDate(startDate, num, timeType);
		}
		return dateList;
	}

	/**
	 * @Description  获取某时间的分钟数
	 * @author wanzhu
	 * @Date: 2019/10/23 15:04
	 * @param: [date]
	 * @return:
	 */
	public static int getMinute(Date date){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.MINUTE);
	}

	public static Date getMonthFirstDay(){
		// 获取当月第一天
		SimpleDateFormat format = new SimpleDateFormat(YYYYMMDD_00_00_00);
		// 获取前月的第一天
		Calendar cale = Calendar.getInstance();
		cale.add(Calendar.MONTH, 0);
		cale.set(Calendar.DAY_OF_MONTH, 1);
		cale.set(Calendar.HOUR_OF_DAY, 0);
		cale.set(Calendar.MINUTE, 0);
		cale.set(Calendar.SECOND, 0);
		return cale.getTime();
	}

	/**
	 * 获得参数月份的上一个月  格式yyyy-MM
	 * @param month
	 * @return
	 */
	public static String getLastMonth(String month) {
		Calendar cale = Calendar.getInstance();
		cale.setTime(string2Date(month,YYYYMM_STANDARD));
		cale.add(Calendar.MONTH,-1);
		return date2string(cale.getTime(),YYYYMM_STANDARD);
	}

	public static Date getCurrentDateZeroSeconde() {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.set(Calendar.SECOND, 0);
		Date zero = calendar.getTime();
		return zero;
	}

	/**
	 * 获取日期的零时零分零秒零毫秒的时间
	 * @param date
	 * @return
	 */
	public static Date getDateZero(Date date){
		date = DateUtils.setHours(date, 0);
		date = DateUtils.setMinutes(date, 0);
		date = DateUtils.setSeconds(date, 0);
		date = DateUtils.setMilliseconds(date, 0);
		return date;
	}

	public static Date asDate(LocalDate localDate) {
		return Date.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
	}

	//LocalDateTime -> Date
	public static Date asDate(LocalDateTime localDateTime) {
		return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
	}

	//Date -> LocalDate
	public static LocalDate asLocalDate(Date date) {
		if (date == null){
			return null;
		}
		return Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
	}

	//String -> LocalDate
	public static LocalDate asLocalDate(String date) {
		if (date == null){
			return null;
		}
		DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		return LocalDate.parse(date, fmt);
	}

	//Date -> LocalDateTime
	public static LocalDateTime asLocalDateTime(Date date) {
		if (date == null){
			return null;
		}
		return Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDateTime();
	}

	/**
	 * 获取今年是哪一年
	 *
	 * @return 年份
	 */
	public static Integer getCurrentYear() {
		Date date = new Date();
		GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
		gc.setTime(date);
		return gc.get(Calendar.YEAR);
	}

	/**
	 * 获取本月是哪一月
	 *
	 * @return 月份
	 */
	public static int getCurrentMonth() {
		Date date = new Date();
		GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
		gc.setTime(date);
		return gc.get(Calendar.MONTH) + 1;
	}

	/**
	 * 获取某个日期的开始时间
	 *
	 * @param d 时间
	 * @return Timestamp
	 */
	public static Timestamp getDayStartTime(Date d) {
		Calendar calendar = Calendar.getInstance();
		if (null != d) {
			calendar.setTime(d);
		}
		calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH),
				0, 0, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return new Timestamp(calendar.getTimeInMillis());
	}

	/**
	 * 指定日获取当月日期
	 *
	 * @param num 指定号
	 * @return 当月日期
	 */
	public static Timestamp getDateByNum(int num) {
		//获取本月的开始时间
		Calendar calendar = Calendar.getInstance();
		calendar.set(getCurrentYear(), getCurrentMonth() -1, num);
		return getDayStartTime(calendar.getTime());
	}

	/**
	 * 判断两个日期是否同一天
	 *
	 * @param date1 时间一
	 * @param date2 时间二
	 * @return true/false
	 */
	public static boolean isSameDate(Date date1, Date date2) {
		Calendar cal1 = Calendar.getInstance();
		cal1.setTime(date1);
		Calendar cal2 = Calendar.getInstance();
		cal2.setTime(date2);
		boolean isSameYear = cal1.get(Calendar.YEAR) == cal2
				.get(Calendar.YEAR);
		boolean isSameMonth = isSameYear
				&& cal1.get(Calendar.MONTH) == cal2.get(Calendar.MONTH);
		return isSameMonth && cal1.get(Calendar.DAY_OF_MONTH) == cal2
				.get(Calendar.DAY_OF_MONTH);
	}

	/**
	 * 获取指定日期的上个月
	 *
	 * @param date 指定时间
	 * @return 上个月的时间
	 */
	public static Timestamp getLastMonthByDate(Date date) {
		Calendar calendar = Calendar.getInstance();
		if (null != date) {
			calendar.setTime(date);
		}
		calendar.add(Calendar.MONTH, -1);
		calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH),
				0, 0, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return new Timestamp(calendar.getTimeInMillis());
	}

	/**
	 * 获取指定日期的前一天
	 *
	 * @param date 指定时间
	 * @return 上个月的时间
	 */
	public static Timestamp getYesterdayByDate(Date date) {
		Calendar calendar = Calendar.getInstance();
		if (null != date) {
			calendar.setTime(date);
		}
		calendar.add(Calendar.DATE, -1);
		calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH),
				23, 59, 59);
		calendar.set(Calendar.MILLISECOND, 0);
		return new Timestamp(calendar.getTimeInMillis());
	}


	/**
	 * 时间戳转UTC
	 *
	 * @param time 时间
	 * @return utc时间
	 */
	public static String getUTCDate(String time) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
		Date date = new Date(Long.parseLong(time));
		return format.format(date);
	}

	/**
	 * UTC 转 时间
	 * @param utcStr UTC时间 yyyy-MM-dd'T'HH:mm:ss
	 * @return 时间
	 * @throws ParseException ParseException
	 */
	public static String UTCToCST(String utcStr) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
		Date date = sdf.parse(utcStr);
		sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(date);
	}

	/**
	 * 获取指定日期的后一天
	 *
	 * @param date 指定时间
	 * @return 上个月的时间
	 */
	public static String getTomorrowByDate(String date) {
		date = date + " 00:00:00";
		Calendar calendar = Calendar.getInstance();
		if (null != date) {
			calendar.setTime(string2Date(date));
		}
		calendar.add(Calendar.DATE, +1);
		calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH),
				0, 0, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		Date timestamp = new Timestamp(calendar.getTimeInMillis());
		return date2string(timestamp, DateUtil.YYYYMMDD);
	}

	/**
	 * 指定的日期是星期几
	 *
	 * @param d 指定日期
	 * @return 星期几
	 */
	public static String getTheDayOfWeek(String d) {
		String date = d + " 00:00:00";
		Calendar c = Calendar.getInstance();
		c.setTime(string2Date(date));
		boolean isFirstSunday = (c.getFirstDayOfWeek() == Calendar.SUNDAY);
		int weekDay = c.get(Calendar.DAY_OF_WEEK);
		if (isFirstSunday) {
			weekDay = weekDay - 1;
			if (weekDay == 0) {
				weekDay = 7;
			}
		}
		return String.valueOf(weekDay);
	}

	/**
	 * 比较日期的大小，yyyy-mm-dd
	 *
	 * @param date1 时间1
	 * @param date2 时间2
	 * @return DATE1>=DATE2,返回true
	 */
	public static String compareDate(String date1, String date2) {
		if (StringUtil.isNullOrEmpty(date2)) {
			return date1;
		}
		try {
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			Date dt1 = df.parse(date1);
			Date dt2 = df.parse(date2);
			//dt1在dt2后
			if (dt1.getTime() >= dt2.getTime()) {
				return date1;
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date2;
	}

	/**
	 * 获取昨天
	 *
	 * @return 上个月的时间
	 */
	public static Timestamp getYesterday() {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.add(Calendar.DATE, -1);
		calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
		calendar.set(Calendar.MILLISECOND, 0);
		return new Timestamp(calendar.getTimeInMillis());
	}

	/**
	 * 获取上个月的今天
	 *
	 * @return 上个月的今天
	 */
	public static String getTodayLastMonth() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		Calendar calendar = Calendar.getInstance();
		// 设置为当前时间
		calendar.setTime(date);
		// 设置为上一个月
		calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) - 1);
		date = calendar.getTime();
		return dateFormat.format(date);
	}


	/**
	 * 获取当前日（dd）
	 *
	 * @return 当前日（dd）
	 */
	public static Integer getDay() {
		Calendar now = Calendar.getInstance();
		return now.get(Calendar.DAY_OF_MONTH);
	}

	/**
	 * 获取当前时间本月的日期列表
	 *
	 * @param today 当前时间
	 * @return 本月的日期列表
	 */
	public static List<LocalDate> getDateList(LocalDate today) {
		LocalDate firstDay = today.with(TemporalAdjusters.firstDayOfMonth());
		LocalDate lastDay = today.with(TemporalAdjusters.lastDayOfMonth());
		return getDateListBetween(firstDay, lastDay);
	}

	/**
	 * 获取指定日期范围内的日期列表
	 *
	 * @param firstDay 开始日期
	 * @param lastDay 结束日期
	 * @return 日期列表
	 */
	public static List<LocalDate> getDateListBetween(LocalDate firstDay, LocalDate lastDay) {
		long dayNum = ChronoUnit.DAYS.between(firstDay, lastDay) + 1;
		return IntStream.iterate(0, i -> i + 1).limit(dayNum).mapToObj(firstDay::plusDays).collect(Collectors.toList());
	}


	/**
	 * 获取当天时间戳
	 * @param hour 精确到小时 如9点10分，返回的是9点整的时间戳
	 * @param minute 精确到分
	 * @param second 精确到秒
	 * @return
	 */
	public static int getTodayStartTime(boolean hour,boolean minute,boolean second) {
		Calendar calendar=Calendar.getInstance();
		calendar.setTime(new Date());
		if(!hour) {
			calendar.set(Calendar.HOUR_OF_DAY, 0);
		}
		if(!minute) {
			calendar.set(Calendar.MINUTE, 0);
		}
		if(!second) {
			calendar.set(Calendar.SECOND, 0);
		}
		return (int) (calendar.getTime().getTime() / 1000);
	}



	/**
	 *  java 获取 获取某年某月 所有日期（yyyy-mm-dd格式字符串）
	 * @param year
	 * @param month
	 * @return
	 */
	public static List<String> getMonthFullDay(int year , int month){
		SimpleDateFormat dateFormatYYYYMMDD = new SimpleDateFormat(YYYYMMDD);
		List<String> fullDayList = new ArrayList<>(32);
		// 获得当前日期对象
		Calendar cal = Calendar.getInstance();
		cal.clear();// 清除信息
		cal.set(Calendar.YEAR, year);
		// 1月从0开始
		cal.set(Calendar.MONTH, month - 1);
		// 当月1号
		cal.set(Calendar.DAY_OF_MONTH, 1);
		int count = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		for (int j = 1; j <= count ; j++) {
			fullDayList.add(dateFormatYYYYMMDD.format(cal.getTime()));
			cal.add(Calendar.DAY_OF_MONTH,1);
		}
		return fullDayList;
	}

	/**
	 * 获取指定年份的所有年月
	 *
	 * @return 所有年月
	 * @throws ParseException 解析异常
	 */
	public static List<String> getMonthBetWeen(String startDate, String endDate) throws ParseException {
		ArrayList<String> result = new ArrayList<>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");//格式化为年月

		Calendar min = Calendar.getInstance();
		Calendar max = Calendar.getInstance();

		min.setTime(sdf.parse(startDate));
		min.set(min.get(Calendar.YEAR), min.get(Calendar.MONTH), 1);

		max.setTime(sdf.parse(endDate));
		max.set(max.get(Calendar.YEAR), max.get(Calendar.MONTH), 2);

		Calendar curr = min;
		while (curr.before(max)) {
			result.add(sdf.format(curr.getTime()));
			curr.add(Calendar.MONTH, 1);
		}
		return result;
	}

	/**
	 * 获取当前月的上一个月
	 */
	public static String getLastMonth() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
		Date date = new Date();
		Calendar calendar = Calendar.getInstance();
		// 设置为当前时间
		calendar.setTime(date);
		// 设置为上一个月
		calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) - 1);
		date = calendar.getTime();
		return format.format(date);
	}

	/**
	 * 获取上月第一天
	 *
	 * @return 上月第一天
	 */
	public static String getLastMonthFirstDay() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		//获取当前日期
		Calendar cal_1 = Calendar.getInstance();
		cal_1.add(Calendar.MONTH, -1);
		//设置为1号,当前日期既为本月第一天
		cal_1.set(Calendar.DAY_OF_MONTH,1);
		return format.format(cal_1.getTime());
	}

	/**
	 * 获取指定日期的开始时间
	 */
	public static String getStartTimeByDate(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar todayStart = Calendar.getInstance();
		todayStart.setTime(date);
		todayStart.set(Calendar.HOUR_OF_DAY, 0);
		todayStart.set(Calendar.MINUTE, 0);
		todayStart.set(Calendar.SECOND, 0);
		todayStart.set(Calendar.MILLISECOND, 0);
		return sdf.format(todayStart.getTime());
	}

	/**
	 * 获取指定日期的结束时间
	 */
	public static String getEndTimeByDate(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar todayEnd = Calendar.getInstance();
		todayEnd.setTime(date);
		todayEnd.set(Calendar.HOUR_OF_DAY, 23);
		todayEnd.set(Calendar.MINUTE, 59);
		todayEnd.set(Calendar.SECOND, 59);
		todayEnd.set(Calendar.MILLISECOND, 999);
		return sdf.format(todayEnd.getTime());
	}

	public static String formatDate(Integer year, Integer month, Integer day, Integer hour) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar date = Calendar.getInstance();
		date.set(Calendar.YEAR, year);
		date.set(Calendar.MONTH, month - 1);
		date.set(Calendar.DAY_OF_MONTH, day);
		date.set(Calendar.HOUR_OF_DAY, hour);
		date.set(Calendar.MINUTE, 0);
		date.set(Calendar.SECOND, 0);
		date.set(Calendar.MILLISECOND, 0);
		return sdf.format(date.getTime());
	}
	/**
	 * 获取当天整点时间列表
	 * @return
	 */
	public static List<LocalTime> getToDayHourDateList(){
		List<LocalTime> list = new ArrayList<>();
		Date date = DateUtil.getStartTime(new Date());
		SimpleDateFormat simpleDateFormat  = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		DateTimeFormatter fmt =DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		list.add(LocalTime.parse(simpleDateFormat.format(date),fmt));
		for (int i=0; i<23; i++){
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			calendar.add(Calendar.HOUR,1);
			date = calendar.getTime();
			list.add(LocalTime.parse(simpleDateFormat.format(date),fmt));
		}
		return list;
	}


}
