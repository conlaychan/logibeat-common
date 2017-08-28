package com.logibeat.cloud.common.model;

import java.io.Serializable;
import java.util.Date;

public class EntityCriteria implements Serializable {
    private static final long serialVersionUID = -1846402828650498375L;

    private Date updateAt;
    private Date createAt;
    private DateRange updateRange;
    private DateRange createRange;

    private String orderBy;

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

    /**
     * order by 字句，将直接拼接到 sql 后面<br>
     * 赋值样例： user_name, login_time desc, update_at asc <br>
     * 注意：字段名称使用库表的列（column）的名称
     */
    public String getOrderBy() {
        return orderBy;
    }

    /**
     * order by 字句，将直接拼接到 sql 后面<br>
     * 赋值样例： user_name, login_time desc, update_at asc <br>
     * 注意：字段名称使用库表的列（column）的名称
     */
    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }
}
