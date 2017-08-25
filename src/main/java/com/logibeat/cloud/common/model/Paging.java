package com.logibeat.cloud.common.model;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

public class Paging<T> implements Serializable {
    private static final long serialVersionUID = 5896849156895026769L;

    private final Long total;
    private final List<T> data;

    public Paging() {
        this(0L, Collections.<T>emptyList());
    }

    public Paging(Long total, List<T> data) {
        this.data = data;
        this.total = total;
    }

    public List<T> getData() {
        return data;
    }

    public Long getTotal() {
        return total;
    }

    public Boolean isEmpty() {
        return total == null || total <= 0 || data == null || data.isEmpty();
    }

    public static <T> Paging<T> empty() {
        return new Paging<T>();
    }

}