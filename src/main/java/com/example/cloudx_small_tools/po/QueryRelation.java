package com.example.cloudx_small_tools.po;

import java.io.Serializable;

/**
 * @Description: 查询表关联配置
 * @author: 10191
 * @date:2022/4/20 11:41
 **/
public class QueryRelation implements Serializable {

    private static final long serialVersionUID = -7747332002949917300L;

    private Long id;
    private String base_table;
    private String association_table;
    private String base_field;
    private String association_field;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBase_table() {
        return base_table;
    }

    public void setBase_table(String base_table) {
        this.base_table = base_table;
    }

    public String getAssociation_table() {
        return association_table;
    }

    public void setAssociation_table(String association_table) {
        this.association_table = association_table;
    }

    public String getBase_field() {
        return base_field;
    }

    public void setBase_field(String base_field) {
        this.base_field = base_field;
    }

    public String getAssociation_field() {
        return association_field;
    }

    public void setAssociation_field(String association_field) {
        this.association_field = association_field;
    }
}
