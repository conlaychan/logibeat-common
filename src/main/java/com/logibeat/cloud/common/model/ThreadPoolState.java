package com.logibeat.cloud.common.model;

import java.io.Serializable;

/**
 * 线程池状态
 */
public class ThreadPoolState implements Serializable {
    private static final long serialVersionUID = 7927840622219315165L;

    private String name;

    /**
     * 已提交给线程池的任务数量
     */
    private long scheduled;

    /**
     * 已完成的任务数量
     */
    private long completed;

    /**
     * 此线程池最大允许的线程数
     */
    private int maximumPoolSize;

    /**
     * 池中当前线程数
     */
    private int currentPoolSize;

    /**
     * 当前正在执行任务的线程数
     */
    private int activeSize;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getScheduled() {
        return scheduled;
    }

    public void setScheduled(long scheduled) {
        this.scheduled = scheduled;
    }

    public long getCompleted() {
        return completed;
    }

    public void setCompleted(long completed) {
        this.completed = completed;
    }

    public int getMaximumPoolSize() {
        return maximumPoolSize;
    }

    public void setMaximumPoolSize(int maximumPoolSize) {
        this.maximumPoolSize = maximumPoolSize;
    }

    public int getCurrentPoolSize() {
        return currentPoolSize;
    }

    public void setCurrentPoolSize(int currentPoolSize) {
        this.currentPoolSize = currentPoolSize;
    }

    public int getActiveSize() {
        return activeSize;
    }

    public void setActiveSize(int activeSize) {
        this.activeSize = activeSize;
    }
}
