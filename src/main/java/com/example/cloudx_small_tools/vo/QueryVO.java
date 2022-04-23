package com.example.cloudx_small_tools.vo;

import java.io.Serializable;
import java.util.List;

/**
 * @Description: SQL生成 查询VO
 * @author: 10191
 * @date:2022/4/20 11:29
 **/
public class QueryVO implements Serializable {

    private static final long serialVersionUID = -4271776627306209682L;

    private List<Long> configIds;
    private List<Long> relationIds;
    private String base_table;
    private List<WhereVO> where;

    public List<Long> getConfigIds() {
        return configIds;
    }

    public void setConfigIds(List<Long> configIds) {
        this.configIds = configIds;
    }

    public List<Long> getRelationIds() {
        return relationIds;
    }

    public void setRelationIds(List<Long> relationIds) {
        this.relationIds = relationIds;
    }

    public String getBase_table() {
        return base_table;
    }

    public void setBase_table(String base_table) {
        this.base_table = base_table;
    }

    public List<WhereVO> getWhere() {
        return where;
    }

    public void setWhere(List<WhereVO> where) {
        this.where = where;
    }
}
