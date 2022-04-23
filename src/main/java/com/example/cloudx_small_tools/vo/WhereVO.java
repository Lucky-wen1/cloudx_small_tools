package com.example.cloudx_small_tools.vo;

import java.io.Serializable;

/**
 * @Description: 查询条件VO
 * @author: 10191
 * @date:2022/4/20 14:18
 **/
public class WhereVO implements Serializable {

    private static final long serialVersionUID = 5424958403243625799L;

    private String field;
    private Object value;
    private String operator;

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }
}
