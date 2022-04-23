package com.example.cloudx_small_tools.po;

import java.io.Serializable;

/**
 * @Description: 查询枚举配置
 * @author: 10191
 * @date:2022/4/20 10:46
 **/
public class QueryNum implements Serializable {

    private static final long serialVersionUID = 3250232797239240718L;

    private Long id;
    private Long num_id;
    private String num_value;
    private String num_comment;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getNum_id() {
        return num_id;
    }

    public void setNum_id(Long num_id) {
        this.num_id = num_id;
    }

    public String getNum_value() {
        return num_value;
    }

    public void setNum_value(String num_value) {
        this.num_value = num_value;
    }

    public String getNum_comment() {
        return num_comment;
    }

    public void setNum_comment(String num_comment) {
        this.num_comment = num_comment;
    }
}
