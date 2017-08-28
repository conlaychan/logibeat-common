package com.logibeat.cloud.common.model;

import java.util.Date;

public class EntityCriteria {
    private Date updateAt;
    private Date createAt;
    private Range<Date> updateRange;
    private Range<Date> createRange;

    public Date getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Date updateAt) {
        this.updateAt = updateAt;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public Range<Date> getUpdateRange() {
        return updateRange;
    }

    public void setUpdateRange(Range<Date> updateRange) {
        this.updateRange = updateRange;
    }

    public Range<Date> getCreateRange() {
        return createRange;
    }

    public void setCreateRange(Range<Date> createRange) {
        this.createRange = createRange;
    }
}
