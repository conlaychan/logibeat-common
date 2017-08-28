package com.logibeat.cloud.common.model;

import com.google.common.collect.BoundType;
import com.google.common.collect.Range;

import java.io.Serializable;
import java.util.Date;

/**
 * 二次封装 com.google.common.collect.Range<br>
 * 搞出这个货来是为了在 mybatis mapper 中使用的，因为原生的类型中没有符合规范的 getter
 */
public class DateRange implements Serializable {
    private static final long serialVersionUID = -7728458293895524175L;

    private final Range<Date> range;

    private DateRange(Range<Date> range) {
        this.range = range;
    }

    public Range<Date> getGuavaRange() {
        return this.range;
    }

    public static DateRange openClosed(Date lower, Date upper) {
        return new DateRange(Range.openClosed(lower, upper));
    }

    public static DateRange open(Date lower, Date upper) {
        return new DateRange(Range.open(lower, upper));
    }

    public static DateRange closed(Date lower, Date upper) {
        return new DateRange(Range.closed(lower, upper));
    }

    public static DateRange closedOpen(Date lower, Date upper) {
        return new DateRange(Range.closedOpen(lower, upper));
    }

    public static DateRange range(Date lower, BoundType lowerType, Date upper, BoundType upperType) {
        return new DateRange(Range.range(lower, lowerType, upper, upperType));
    }

    public static DateRange lessThan(Date endpoint) {
        return new DateRange(Range.lessThan(endpoint));
    }

    public static DateRange atMost(Date endpoint) {
        return new DateRange(Range.atMost(endpoint));
    }

    public static DateRange greaterThan(Date endpoint) {
        return new DateRange(Range.greaterThan(endpoint));
    }

    public static DateRange atLeast(Date endpoint) {
        return new DateRange(Range.atLeast(endpoint));
    }

    public boolean hasLowerBound() {
        return range.hasLowerBound();
    }

    public boolean hasUpperBound() {
        return range.hasUpperBound();
    }

    public Date getLowerEndpoint() {
        return range.lowerEndpoint();
    }

    public Date getUpperEndpoint() {
        return range.upperEndpoint();
    }

    public String getLowerBoundType() {
        return range.lowerBoundType().name();
    }

    public String getUpperBoundType() {
        return range.upperBoundType().name();
    }
}
