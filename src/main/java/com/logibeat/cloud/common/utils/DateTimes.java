package com.logibeat.cloud.common.utils;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;

import java.util.Date;

public enum DateTimes {
    COMMON_DATE_TIME("yyyy-MM-dd HH:mm:ss"),
    COMMON_DATE_MINUTE("yyyy-MM-dd HH:mm"),
    COMMON_DATE("yyyy-MM-dd"),
    COMMON_MONTH("yyyy-MM"),
    COMMON_TIME("HH:mm:ss"),
    COMMON_MINUTE("HH:mm"),

    NUMERIC_DATE_TIME("yyyyMMddHHmmss"),
    NUMERIC_DATE_MINUTE("yyyyMMddHHmm"),
    NUMERIC_DATE("yyyyMMdd"),
    NUMERIC_MONTH("yyyyMM"),
    NUMERIC_TIME("HHmmss"),
    NUMERIC_MINUTE("HHmm"),

    CHN_DATE_TIME("yyyy年MM月dd日 HH时mm分ss秒"),
    CHN_DATE_MINUTE("yyyy年MM月dd日 HH时mm分"),
    CHN_DATE("yyyy年MM月dd日"),
    CHN_MONTH("yyyy年MM月"),
    CHN_TIME("HH时mm分ss秒"),
    CHN_MINUTE("HH时mm分");

    private final String pattern;
    
    DateTimes(String pattern){
        this.pattern = pattern;
    }

    public String getPattern(){
        return pattern;
    }

    /**
     * 获取系统当前时间并格式化
     */
    public String currentTime() {
        return DateTime.now().toString(pattern);
    }

    /**
     * 字符串转为 java.util.Date
     */
    public Date parse(String source){
        return DateTime.parse(source, DateTimeFormat.forPattern(pattern)).toDate();
    }

    /**
     * 格式化 java.util.Date
     */
    public String format(Date datetime){
        return new DateTime(datetime).toString(pattern);
    }
}
