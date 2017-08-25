package com.logibeat.cloud.common.utils;

import com.google.common.base.Strings;
import org.apache.commons.lang3.StringUtils;

import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class Params {
    private Params(){}

    /**
     * 过滤 Map value 中的 NULL 或 空白字符串
     */
    public static <K, V> Map<K, V> filterNullOrBlank(Map<K, V> criteria) {
        Map<K, V> result = new HashMap<K, V>();
        for (Map.Entry<K, V> entry : criteria.entrySet()) {
            V v = entry.getValue();
            if (v instanceof CharSequence) {
                if (StringUtils.isNotBlank((CharSequence) v)) result.put(entry.getKey(), v);
            } else {
                if (v != null) result.put(entry.getKey(), v);
            }
        }
        return result;
    }

    public static Map<String, Object> objToMap(Object obj) {
        return JsonMapper.NON_EMPTY.getMapper().convertValue(obj, Map.class);
    }

    public static String trimToNull(String str) {
        return str != null ? Strings.emptyToNull(str.trim()) : null;
    }

    public static String trimToNull(Object obj) {
        return obj != null ? trimToNull(obj.toString()) : null;
    }

    public static boolean isAllNullOrBlank(Object... obj) {
        for (Object o : obj) {
            if (o != null) {
                if (o instanceof CharSequence) {
                    if (StringUtils.isNotBlank((CharSequence) o)) return false;
                } else return false;
            }
        }
        return true;
    }

    public static boolean anyNullOrBlank(Object... obj) {
        for (Object o : obj) {
            if (o == null) return true;
            if (o instanceof CharSequence && StringUtils.isBlank((CharSequence) o)) return true;
        }
        return false;
    }

    public static boolean isAllNullOrBlankField(Object obj) {
        try {
            for (PropertyDescriptor pd : Introspector.getBeanInfo(obj.getClass()).getPropertyDescriptors()) {
                Method reader = pd.getReadMethod();
                if (reader != null) {
                    if (!reader.isAccessible()) {
                        reader.setAccessible(Boolean.TRUE);
                    }
                    if (!isAllNullOrBlank(reader.invoke(obj))) return false;
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return true;
    }

    public static boolean anyNullOrBlankField(Object obj) {
        try {
            for (PropertyDescriptor pd : Introspector.getBeanInfo(obj.getClass()).getPropertyDescriptors()) {
                Method reader = pd.getReadMethod();
                if (reader != null) {
                    if (!reader.isAccessible()) {
                        reader.setAccessible(Boolean.TRUE);
                    }
                    if (anyNullOrBlank(reader.invoke(obj))) return true;
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return false;
    }
}
