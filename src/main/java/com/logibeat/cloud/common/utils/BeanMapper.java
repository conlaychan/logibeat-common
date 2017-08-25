package com.logibeat.cloud.common.utils;

import org.dozer.DozerBeanMapper;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 简单封装Dozer, 实现深度转换Bean<->Bean的Mapper.实现:
 * <p/>
 * 1. 持有Mapper的单例.
 * 2. 泛型返回值转换.
 * 3. 批量转换Collection中的所有对象.
 * 4. 区分创建新的B对象与将对象A值复制到已存在的B对象两种函数.
 * <p/>
 */
public class BeanMapper {

    /**
     * 持有Dozer单例, 避免重复创建DozerMapper消耗资源.
     */
    private static final DozerBeanMapper dozer = new DozerBeanMapper();

    private BeanMapper() {
    }

    /**
     * 基于Dozer转换对象的类型.
     */
    public static <T> T map(Object source, Class<T> destinationClass) {
        return dozer.map(source, destinationClass);
    }

    /**
     * 基于Dozer转换Collection中对象的类型.
     */
    public static <T> List<T> mapList(Collection sourceList, Class<T> destinationClass) {
        List<T> destinationList = new ArrayList<T>();
        for (Object sourceObject : sourceList) {
            destinationList.add(dozer.map(sourceObject, destinationClass));
        }
        return destinationList;
    }

    /**
     * 基于Dozer将对象A的值拷贝到对象B中.
     */
    public static void copy(Object source, Object destinationObject) {
        dozer.map(source, destinationObject);
    }

    /**
     * 基于反射,将JAVA对象转换成Map<String, Object>
     */
    public static Map<String, Object> objToMap(Object obj) {
        Map<String, Object> objectAsMap = new HashMap<String, Object>();
        try {
            BeanInfo info = Introspector.getBeanInfo(obj.getClass());
            for (PropertyDescriptor pd : info.getPropertyDescriptors()) {
                Method reader = pd.getReadMethod();
                if (reader != null) {
                    if (!reader.isAccessible()) {
                        reader.setAccessible(Boolean.TRUE);
                    }
                    objectAsMap.put(pd.getName(), reader.invoke(obj));
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return objectAsMap;
    }

}
