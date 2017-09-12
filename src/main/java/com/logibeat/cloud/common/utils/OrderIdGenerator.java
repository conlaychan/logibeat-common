package com.logibeat.cloud.common.utils;

import com.logibeat.cloud.common.enums.PayChannel;
import org.joda.time.DateTime;
import redis.clients.jedis.Jedis;

/**
 * 订单id生成工具
 */
public class OrderIdGenerator {
    private OrderIdGenerator(){}

    /**
     * 1：支付渠道，[0，Z]，{@link PayChannel}<br>
     * 2-3：年，[00，99]<br>
     * 4：月，[1，C]<br>
     * 5：日，[1，V]<br>
     * 6：时，[0，N]<br>
     * 7-8：分，[0，59]<br>
     * 9-10：秒，[0，59]<br>
     * 11-14：自增
     * @param payChannel
     * @return 如果一秒内生成次数大于9999，将会自动扩增长度
     */
    public static String generate(PayChannel payChannel, Jedis jedis) {
        try {
            StringBuilder sb = joinDateTime(payChannel, DateTime.now());
            sb.append(autoIncrId(sb.toString(), jedis));
            return sb.toString();
        } finally {
            jedis.close();
        }
    }

    private static StringBuilder joinDateTime(PayChannel payChannel, DateTime time){
        return new StringBuilder()
                .append(payChannel.getCode())
                .append(getYear(time))
                .append(getMonth(time))
                .append(getDate(time))
                .append(getHour(time))
                .append(getMinute(time))
                .append(getSecond(time));
    }

    private static String getYear(DateTime time) {
        String year = Integer.toString(time.getYear());
        return year.substring(year.length() - 2);
    }

    private static final String POOL = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    private static char getMonth(DateTime time) {
        return POOL.charAt(time.getMonthOfYear());
    }

    private static char getDate(DateTime time) {
        return POOL.charAt(time.getDayOfMonth());
    }

    private static char getHour(DateTime time) {
        return POOL.charAt(time.getHourOfDay());
    }

    private static String getMinute(DateTime time) {
        int minute = time.getMinuteOfHour();
        return minute < 10 ? "0" + minute : Integer.toString(minute);
    }

    private static String getSecond(DateTime time) {
        int second = time.getSecondOfMinute();
        return second < 10 ? "0" + second : Integer.toString(second);
    }

    private static String autoIncrId(String key, Jedis jedis) {
        String id = jedis.incr(key).toString();
        if ("1".equals(id)) {
            jedis.expire(key, 3600); // 设置下过期时间1个小时，理论上本可以设置为1秒，但是考虑到分布式系统中各台服务器的系统时间可能不一致……
        }
        switch (id.length()) {
            case 1: return "000" + id;
            case 2: return "00" + id;
            case 3: return "0" + id;
            default: return id;
        }
    }
}
