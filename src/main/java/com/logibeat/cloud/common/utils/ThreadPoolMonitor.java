package com.logibeat.cloud.common.utils;

import com.logibeat.cloud.common.model.ThreadPoolState;

import java.lang.reflect.Field;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 监控线程池工具类
 */
public class ThreadPoolMonitor {

    private ThreadPoolMonitor(){}

    /**
     * @param threadPool
     * @param name       名字随便给，只是为了你自己方便
     * @return
     */
    public static ThreadPoolState getState(ThreadPoolExecutor threadPool, String name) {
        ThreadPoolState state = new ThreadPoolState();
        state.setName(name);
        state.setScheduled(threadPool.getTaskCount()); // 已提交给线程池的任务数量
        state.setCompleted(threadPool.getCompletedTaskCount()); // 已完成的任务数量
        state.setCurrentPoolSize(threadPool.getPoolSize()); // 池中当前线程数
        state.setActiveSize(threadPool.getActiveCount()); // 正在执行任务的线程数
        state.setMaximumPoolSize(threadPool.getMaximumPoolSize());
        return state;
    }

    /**
     * 通过反射获取对象中的线程池，适用于没有get方法的对象
     *
     * @param threadPoolName 名字随便给，只是为了你自己方便
     */
    public static ThreadPoolState getState(Object object, String fieldName, String threadPoolName) {
        ThreadPoolExecutor executor;
        try {
            Field field = object.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            executor = (ThreadPoolExecutor) field.get(object);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return getState(executor, threadPoolName);
    }

}
