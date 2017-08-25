package com.logibeat.cloud.common.utils;

import com.google.common.collect.Range;
import com.logibeat.cloud.common.constant.DateTimePattern;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;

import java.util.Date;

public class DateTimeUtil {

    private DateTimeUtil(){}

    /**
     * 获取系统当前日期
     * @return 格式为 yyyy-MM-dd HH:mm:ss
     */
    public static String currentTime() {
        return DateTime.now().toString(DateTimePattern.COMMON_DATE_TIME);
    }

    /**
     * 字符串转为 java.util.Date
     * @param pattern {@link DateTimePattern}
     */
    public static Date parse(String source, String pattern){
        return DateTime.parse(source, DateTimeFormat.forPattern(pattern)).toDate();
    }

    /**
     * 格式化 java.util.Date
     * @param pattern {@link DateTimePattern}
     */
    public static String format(Date datetime, String pattern){
        return new DateTime(datetime).toString(pattern);
    }

    /**
     * 获取日期相差毫秒数<br>
     * 请注意两个参数的顺序，此方法将用第二个参数减第一个参数来计算
     */
    public static long distanceMillis(Date start, Date end) {
        return end.getTime() - start.getTime();
    }

    /**
     * 获取日期相差秒数<br>
     * 请注意两个参数的顺序，此方法将用第二个参数减第一个参数来计算，不足一秒不计算
     */
    public static long distanceSeconds(Date start, Date end) {
        return distanceMillis(start, end) / 1000;
    }

    /**
     * 获取日期相差分钟数<br>
     * 请注意两个参数的顺序，此方法将用第二个参数减第一个参数来计算，不足一分钟不计算
     */
    public static long distanceMinutes(Date start, Date end) {
        return distanceMillis(start, end) / 60000;
    }

    /**
     * 获取日期相差小时数<br>
     * 请注意两个参数的顺序，此方法将用第二个参数减第一个参数来计算，不足一小时不计算
     */
    public static long distanceHours(Date start, Date end) {
        return distanceMillis(start, end) / 3600000;
    }

    /**
     * 获取日期相差天数<br>
     * 请注意两个参数的顺序，此方法将用第二个参数减第一个参数来计算，不足一天不计算
     */
    public static long distanceDays(Date start, Date end) {
        return distanceMillis(start, end) / 86400000;
    }

    /**
     * 判断参数 java.util.Date 是否在指定区间之内
     */
    public static boolean between(Date time, Range<Date> range){
        return range.contains(time);
    }

    /**
     * 判断系统当前时间是否在指定区间之内
     */
    public static boolean between(Range<Date> range){
        return range.contains(new Date());
    }


}
