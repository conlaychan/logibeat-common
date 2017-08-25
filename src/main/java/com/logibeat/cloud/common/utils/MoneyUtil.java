package com.logibeat.cloud.common.utils;

import java.math.BigDecimal;

/**
 * 金钱计算工具
 */
public class MoneyUtil {

    /**
     * 将以“分”为单位的货币除以100后以字符串表示
     * @param price 以“分”为单位的货币
     * @return 除以100后的字符串表示（必带有两位小数）
     */
    public static String formatPrice(Long price){
        return new BigDecimal(price.toString()).divide(new BigDecimal("100"), 2, BigDecimal.ROUND_UNNECESSARY).toString();
    }

    public static String formatPrice(Integer price){
        return formatPrice(Long.valueOf(price));
    }
}
