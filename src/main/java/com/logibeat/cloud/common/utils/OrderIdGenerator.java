package com.logibeat.cloud.common.utils;

import com.google.common.util.concurrent.AtomicLongMap;
import com.logibeat.cloud.common.enums.PayChannel;
import org.joda.time.DateTime;

import java.util.Timer;
import java.util.TimerTask;

/**
 * 订单id生成工具
 */
public class OrderIdGenerator {
    private OrderIdGenerator(){}

    private static final AtomicLongMap<String> incrPerSecond = AtomicLongMap.create(); // 每一秒内的自增id
    static {
        // 每秒执行一次，清除上一秒的自增id
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                DateTime pre = DateTime.now().minusSeconds(1);
                for (PayChannel payChannel : PayChannel.values()) {
                    incrPerSecond.remove(joinDateTime(payChannel, pre).toString());
                }
            }
        }, 0, 1000);
    }

    /**
     * 1：支付渠道，[0，z]，{@link PayChannel}<br>
     * 2-3：年，[00，99]<br>
     * 4：月，[1，c]<br>
     * 5：日，[1，v]<br>
     * 6：时，[0，n]<br>
     * 7-8：分，[0，59]<br>
     * 9-10：秒，[0，59]<br>
     * 11-14：自增
     * @param payChannel
     * @return 如果一秒内生成次数大于9999，将会自动扩增长度
     */
    public static String generate(PayChannel payChannel) {
        StringBuilder sb = joinDateTime(payChannel, DateTime.now());
        sb.append(autoIncrId(sb.toString()));
        return sb.toString();
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

    private static String autoIncrId(String key) {
        String id = Long.toString(incrPerSecond.incrementAndGet(key));
        switch (id.length()) {
            case 1: return "000" + id;
            case 2: return "00" + id;
            case 3: return "0" + id;
            default: return id;
        }
    }
}
