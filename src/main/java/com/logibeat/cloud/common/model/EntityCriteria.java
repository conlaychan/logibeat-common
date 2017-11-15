package com.logibeat.cloud.common.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Data
public class EntityCriteria implements Serializable {
    private static final long serialVersionUID = -1846402828650498375L;

    private Date updateAt;
    private Date createAt;
    private DateRange updateRange;
    private DateRange createRange;

    /**
     * order by 字句，将直接拼接到 sql 后面<br>
     * 赋值样例： user_name, login_time desc, update_at asc <br>
     * 注意：字段名称使用库表的列（column）的名称
     */
    private String orderBy;

    /**
     * offset 是分页查询用的
     */
    @Setter(AccessLevel.NONE)
    private Integer offset;
    /**
     * limit 是分页查询用的
     */
    @Setter(AccessLevel.NONE)
    private Integer limit;

    /**
     * offset 和 limit 是分页查询用的
     */
    public void buildOffsetLimit(Integer pageNo, Integer pageSize) {
        pageNo = pageNo == null ? 1 : pageNo;
        pageSize = pageSize == null ? 20 : pageSize;
        limit = pageSize > 0 ? pageSize : 20;
        int offset = (pageNo - 1) * pageSize;
        this.offset = offset > 0 ? offset : 0;
    }

    public Integer getOffset() {
        return offset;
    }

    public Integer getLimit() {
        return limit;
    }
}
