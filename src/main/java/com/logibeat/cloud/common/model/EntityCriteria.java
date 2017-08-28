package com.logibeat.cloud.common.model;

import java.util.Date;

public class EntityCriteria {
    private Date updateAt;
    private Date createAt;
    private DateRange updateRange;
    private DateRange createRange;

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

    public DateRange getUpdateRange() {
        return updateRange;
    }

    public void setUpdateRange(DateRange updateRange) {
        this.updateRange = updateRange;
    }

    public DateRange getCreateRange() {
        return createRange;
    }

    public void setCreateRange(DateRange createRange) {
        this.createRange = createRange;
    }
}
