package com.example.cloudx_small_tools.po;

import java.io.Serializable;

/**
 * @Description: 查询配置表
 * @author: 10191
 * @date:2022/4/16 17:23
 **/
public class QueryConfiguration implements Serializable {

    private static final long serialVersionUID = 4846602864577139404L;
    private Long id;
    private String query_table;
    private String query_field;
    private String field_comment;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQuery_table() {
        return query_table;
    }

    public void setQuery_table(String query_table) {
        this.query_table = query_table;
    }

    public String getQuery_field() {
        return query_field;
    }

    public void setQuery_field(String query_field) {
        this.query_field = query_field;
    }

    public String getField_comment() {
        return field_comment;
    }

    public void setField_comment(String field_comment) {
        this.field_comment = field_comment;
    }
}
